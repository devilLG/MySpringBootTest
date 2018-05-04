package com.zhilai.master.task.service;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.SpringContextHolder;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.sys.utils.UserUtils;

public class TaskRestart extends StatefulMethodInvokingJob {
	private static Logger log = Logger.getLogger(TaskRestart.class);	
	//private TaskServer taskServer=SpringContextHolder.getBean(TaskServer.class);
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		//taskServer.task();
		
		String report_date=DateUtils.DateToString();
		log.info("executeInternal TaskRestart report_date="+report_date);
	}

	
}
