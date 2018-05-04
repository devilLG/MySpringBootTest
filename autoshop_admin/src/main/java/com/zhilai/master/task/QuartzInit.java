package com.zhilai.master.task;

import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.JobDataMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 定时任务初始化,系统启动时执行
 * @author xufeng
 *
 */
@Service 
@Lazy(false)
@Component
public class QuartzInit implements InitializingBean{
	@Autowired
	private QuartzDao quartzDao;

	public void setQuartzDao(QuartzDao quartzDao) {
		this.quartzDao = quartzDao;
	}
	
	@PostConstruct
	public void init() throws ClassNotFoundException
	{
		System.out.println("----------定时任务开始加载1---------");
		//获取所有执行任务
//		List<QuartzTimerJob> list = quartzDao.findAllTask();
//		if (list == null || list.size() == 0)
//		{
//			return;
//		}
//		for (int i = 0; i < list.size(); i++)
//		{
//			//获取数据封装成任务类
//			QuartzTimerJob quartzTimerJob = list.get(i);
//
//			@SuppressWarnings("rawtypes")
//			//获取执行任务类的类全名全名
//			Class cls = Class.forName(quartzTimerJob.getClassName());
//			//赋值
//			quartzTimerJob.setStateFulljobExecuteClass(cls);
//			JobDataMap paramsMap = new JobDataMap();
//			QuartzManager.enableCronSchedule(quartzTimerJob, paramsMap, true);
//		}
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
	
	
}
