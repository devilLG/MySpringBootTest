package com.zhilai.master.task;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * 定时任务job定义
 * 
 * @author xufeng
 * 
 */
public class QuartzTimerJob extends DataEntity<QuartzTimerJob> {
	public static final String JS_ENABLED = "1"; // 任务启用状态
	public static final String JS_DISABLED = "0"; // 任务禁用状态
	public static final String JS_DELETE = "2"; // 任务已删除状态

	private String logid; // 标识
	private String taskCode;	// 任务编号
	private String taskName;	// 任务名称
	private String className; // 任务的Id，一般为所定义Bean的ID
//	private String jobName; // 任务的描述
	private String jobGroup = "post"; // 任务所属组的名称
	private String scron; // 定时任务运行时间表达式
	private String method; // 定时任务执行方法　
	private String state = JS_ENABLED;	// 任务的状态，1：启用；0：禁用；2：已删除
	private String description; // 任务描述
	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getScron() {
		return scron;
	}

	public void setScron(String scron) {
		this.scron = scron;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private Class<?> stateFulljobExecuteClass;//同步的执行类，需要从StatefulMethodInvokingJob继承
	private Class<?> jobExecuteClass;//异步的执行类，需要从MethodInvokingJob继承

	/**
	 * @return the jobGroup
	 */
	public String getJobGroup() {
		return "post";
	}

	/**
	 * @param jobGroup
	 *            the jobGroup to set
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}


	
	
	

	/**
	 * @return the stateFulljobExecuteClass
	 */
	public Class<?> getStateFulljobExecuteClass() {
		return stateFulljobExecuteClass;
	}

	/**
	 * @param stateFulljobExecuteClass the stateFulljobExecuteClass to set
	 */
	public void setStateFulljobExecuteClass(Class<?> stateFulljobExecuteClass) {
		this.stateFulljobExecuteClass = stateFulljobExecuteClass;
	}

	/**
	 * @return the jobExecuteClass
	 */
	public Class<?> getJobExecuteClass() {
		return jobExecuteClass;
	}

	/**
	 * @param jobExecuteClass the jobExecuteClass to set
	 */
	public void setJobExecuteClass(Class<?> jobExecuteClass) {
		this.jobExecuteClass = jobExecuteClass;
	}
}
