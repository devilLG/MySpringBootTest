package com.zhilai.master.task;
 
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
 
/**
 * 定时任务管理类，用于增删改查定时任务
 * 每个定时任务实现类要继承StatefulMethodInvokingJob
 * @author xufeng
 *
 */
public class QuartzManager {
	private static Scheduler scheduler;

	public void setScheduler(Scheduler scheduler) {
		QuartzManager.scheduler = scheduler;
	}
	public QuartzManager()
	{
		
	}
	
	/**
	 * 启动一个自定义的job
	 * 
	 * @param schedulingJob
	 *            自定义的job类
	 * @param paramsMap
	 *            传递给job执行的数据
	 * @param isStateFull
	 *            是否是一个同步定时任务，true：同步，false：异步
	 * @return 成功则返回true，否则返回false
	 */ 
	public static boolean enableCronSchedule(QuartzTimerJob schedulingJob,
			JobDataMap paramsMap, boolean isStateFull) {
		//如果任务类为空，直接返回。
		if (schedulingJob == null) {
			return false;
		}
		try {

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(schedulingJob.getTaskName(),
							schedulingJob.getJobGroup());
			if (null == trigger) {// 如果不存在该trigger则创建一个
				JobDetail jobDetail = null;
				if (isStateFull) {
					//将任务名称、任务所属组传入jobDetail
					jobDetail = new JobDetail(schedulingJob.getTaskName(),
							//任务所属组
							schedulingJob.getJobGroup(),
							//任务执行类
							schedulingJob.getStateFulljobExecuteClass());
				} else {
					jobDetail = new JobDetail(schedulingJob.getTaskName(),
							schedulingJob.getJobGroup(),
							schedulingJob.getJobExecuteClass());
				}
				jobDetail.setJobDataMap(paramsMap);
				trigger = new CronTrigger(schedulingJob.getTaskName(),
						schedulingJob.getJobGroup(),
						//定时任务的表达式
						schedulingJob.getScron());
				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				// Trigger已存在，那么更新相应的定时设置
				trigger.setCronExpression(schedulingJob.getScron());
				//启动任务
				scheduler.rescheduleJob(trigger.getName(), trigger.getGroup(),trigger);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 禁用一个job
	 * 
	 * 
	 * @param jobId
	 *            需要被禁用的job的ID
	 * @param jobGroupId
	 *            需要被警用的jobGroupId
	 * @return 成功则返回true，否则返回false
	 */
	public static boolean disableSchedule(String jobId) {
		if (jobId.equals("")) {
			return false;
		}
		try {
			Trigger trigger = getJobTrigger(jobId, "post");
			if (null != trigger) {
				scheduler.deleteJob(jobId, "post");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 得到job的详细信息
	 * 
	 * @param jobId
	 *            job的ID
	 * @param jobGroupId
	 *            job的组ID
	 * @return job的详细信息,如果job不存在则返回null
	 */
	public static JobDetail getJobDetail(String jobId, String jobGroupId) {
		if (jobId.equals("") || jobGroupId.equals("") || null == jobId
				|| jobGroupId == null) {
			return null;
		}
		try {
			return scheduler.getJobDetail(jobId, jobGroupId);
		} catch (SchedulerException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到job对应的Trigger
	 * 
	 * @param jobId
	 *            job的ID
	 * @param jobGroupId
	 *            job的组ID
	 * @return job的Trigger,如果Trigger不存在则返回null
	 */
	public static Trigger getJobTrigger(String jobId, String jobGroupId) {
		if (jobId.equals("") || jobGroupId.equals("") || null == jobId
				|| jobGroupId == null) {
			return null;
		}
		try {
			return scheduler.getTrigger(jobId, jobGroupId);
		} catch (SchedulerException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 暂停任务
	 * 停止一个job，如果希望停止一个任务而不删除，请调用这个方法。如果希望停止一个任务并从任务池中删除请调用disableSchedule
	 * taskName 被禁用的线程名字
	 * @param jobId
	 * @return
	 */
	public static boolean pauseScheduleByName(String taskName,String jobGroup) {
		if (taskName.equals("")) {
			return false;
		}
		try {
			//将任务名称、任务所属组传入CronTrigger
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(taskName,jobGroup);
			if (null != trigger) {
				scheduler.pauseJob(taskName, jobGroup);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
