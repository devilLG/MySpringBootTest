package com.zhilai.master.task.service;

import java.util.Date;

import org.apache.commons.putils.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zhilai.master.common.config.Global;

/**
 * 任务调度管理类
 * @author Administrator
 *
 */
@Service 
@Lazy(false)
@Component
public class TaskManagementService {
	private static Logger log = Logger.getLogger(TaskManagementService.class);
	public static boolean is0TaskOnOff=Global.isReceiveTaskOnOff();
	
	@Autowired
	private TaskCheckSiteStateService taskCheckSiteStateService;
	@Autowired
	private TaskBoxNumCheckService taskBoxNumCheckService;
	@Autowired
	private TaskProductFavourableStartService taskProductFavourableStartService;
	@Autowired
	private TaskProductFavourableEndService taskProductFavourableEndService;
	@Autowired
	private TaskProductOverdueService taskProductOverdueService;
	@Autowired
	private TaskMsgSendService taskMsgSendService;
	@Autowired
	private TaskPayOverdueService taskPayOverdueService;
	@Autowired
	private TaskCallProcedureService taskCallProcedureService;
	@Autowired
	private TaskAccountCheckService taskAccountCheckService;
	@Autowired
	private TaskCreateNoticeService taskCreateNoticeService;
	@Autowired
	private TaskNoticeSendService taskNoticeSendService;
	@Autowired
	private TaskTakePayManageInfoService takePayManageInfoService;
	@Autowired
	private TaskTakePaymentInfoService takePaymentInfoService;
	/**
	 * 站点状态检查
	 */
	@Scheduled(cron = "0 */5 * * * ?")
	public void taskCheckSiteState(){
		if(!is0TaskOnOff){
			return;
		}
		taskCheckSiteStateService.execute(new Date());
	}
	
	/**
	 * 站点缺货检查
	 */
	@Scheduled(cron = "0 */5 * * * ?")
	public void taskBoxNumCheck(){
		if(!is0TaskOnOff){
			return;
		}
		taskBoxNumCheckService.execute(new Date());
	}
	
	/**
	 * 商品优惠开始检查
	 */
//	@Scheduled(cron = "0 0 23 * * ?")
	@Scheduled(cron = "0 */5 * * * ?")
	public void taskProductFavourableStart(){
		if(!is0TaskOnOff){
			return;
		}
		taskProductFavourableStartService.execute(new Date());
	}
	
	/**
	 * 商品优惠结束检查
	 */
//	@Scheduled(cron = "0 0 23 * * ?")
	@Scheduled(cron = "0 */5 * * * ?")
	public void taskProductFavourableEnd(){
		if(!is0TaskOnOff){
			return;
		}
		taskProductFavourableEndService.execute(new Date());
	}
	
	/**
	 * 商品过期检查
	 */
//	@Scheduled(cron = "0 0 */1 * * ?")
	@Scheduled(cron = "0 */5 * * * ?")
	public void taskProductOverdue(){
		if(!is0TaskOnOff){
			return;
		}
		taskProductOverdueService.execute(new Date());
	}
	
//	/**
//	 * 发送通知
//	 */
//	@Scheduled(cron = "0 */2 * * * ?")
//	public void taskMsgSend(){
//		if(!is0TaskOnOff){
//			return;
//		}
//		taskMsgSendService.execute(new Date());
//	}
	
	/**
	 * 支付超期检查
	 */
	@Scheduled(cron = "0 */5 * * * ?")
	public void taskPayOverdue(){
		if(!is0TaskOnOff){
			return;
		}
		taskPayOverdueService.execute(new Date());
	}
	
	/**
	 * 公司销售日报表
	 */
	@Scheduled(cron = "0 */5 * * * ?")
	public void taskCorpSaleDay(){
		if(!is0TaskOnOff){
			return;
		}
		String task_code = "1013";
//		String report_date = DateUtil.formatDate(DateUtil.getDate(new Date(), -1));
		String report_date = DateUtil.getDate();
		taskCallProcedureService.executeCall(task_code, report_date);
	}
	
	/**
	 * 公司销售月报表
	 */
	@Scheduled(cron = "0 */5 * * * ?")
	public void taskCorpSaleMonth(){
		if(!is0TaskOnOff){
			return;
		}
		String task_code = "1014";
		String report_date = DateUtil.getDate();
		if(report_date.substring(8).equals("01")){
			report_date = DateUtil.formatDate(DateUtil.getDate(new Date(), -1));
		}
		taskCallProcedureService.executeCall(task_code, report_date);
	}
	
	/**
	 * 公司销售年报表
	 */
	@Scheduled(cron = "0 */5 * * * ?")
	public void taskCorpSaleYear(){
		if(!is0TaskOnOff){
			return;
		}
		String task_code = "1015";
		String report_date = DateUtil.getDate();
		if(report_date.substring(5).equals("01-01")){
			report_date = DateUtil.formatDate(DateUtil.getDate(new Date(), -1));
		}
		taskCallProcedureService.executeCall(task_code, report_date);
	}
	
	/**
	 * 记录管理台账单
	 */
	@Scheduled(cron = "0 5 0 * * ?")
	public void taskTakePayManageInfo() {
		if(!is0TaskOnOff){
			return;
		}
		takePayManageInfoService.execute();
	}
	
	/**
	 * 记录支付平台账单
	 */
	@Scheduled(cron = "0 5 0 * * ?")
	public void taskTakePaymentInfo() {
		if(!is0TaskOnOff){
			return;
		}
		takePaymentInfoService.execute();
	}
	
	/**
	 * 系统对账
	 */
	@Scheduled(cron = "0 30 0 * * ?")
	public void taskAccountCheck() {
		if(!is0TaskOnOff){
			return;
		}
		taskAccountCheckService.execute();
	}
	
	/**
	 * 创建通知
	 */
	//@Scheduled(cron = "0 5 0 * * ?")
	public void taskCreateNotice() {
		if(!is0TaskOnOff){
			return;
		}
		taskCreateNoticeService.execute();
	}
	
	/**
	 * 发送通知
	 */
	//@Scheduled(cron = "0 0/5 8-20 * * ?")
	public void taskSendNotice() {
		if(!is0TaskOnOff){
			return;
		}
		taskNoticeSendService.execute();
	}

}
