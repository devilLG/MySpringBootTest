package com.zhilai.master.task.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.putils.mail.util.MailSenderInfo;
import org.apache.commons.putils.mail.util.SimpleMailSender;
import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.weixin4j.WeixinException;

import com.alibaba.fastjson.JSONObject;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.IdGen;
import com.zhilai.master.modules.notice.dao.CorpNoticeTemplateDao;
import com.zhilai.master.modules.notice.dao.NoticeOverDao;
import com.zhilai.master.modules.notice.dao.NoticeQueueDao;
import com.zhilai.master.modules.notice.entity.CorpNoticeTemplate;
import com.zhilai.master.modules.notice.entity.NoticeOver;
import com.zhilai.master.modules.notice.entity.NoticeQueue;
import com.zhilai.master.modules.parmter.entity.MsgSend;
import com.zhilai.master.modules.sys.dao.UserDao;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.wechat.TemplateMsgUtil;
import com.zhilai.master.modules.zxml.ClientCallSoap;
import com.zhilai.master.modules.zxml.MessageSendReq;
import com.zhilai.master.modules.zxml.MessageSendResp;

/**
 * 
 * @ClassName TaskNoticeSendService
 * @Description 发送通知
 * @author GuoXuegan
 * @data 2018年4月12日下午2:08:21
 *
 */
@Service
@Transactional(readOnly=true)
public class TaskNoticeSendService {

	@Autowired
	private NoticeQueueDao noticeQueueDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private NoticeOverDao noticeOverDao;
	@Autowired
	private CorpNoticeTemplateDao corpNoticeTemplateDao;
	
	@Transactional
	public void execute() {
		List<NoticeQueue> noticeQueues = noticeQueueDao.findBySendState(GParameter.noticeSendState_wait);
		for (NoticeQueue noticeQueue : noticeQueues) {
			User user = userDao.findOneById(noticeQueue.getLogin_id());
			if (user == null) {
				continue;
			}
			if (noticeQueue.getNotice_type().equals(GParameter.noticeType_mobile)) {
				MsgSend msgSend = new MsgSend();
				msgSend.setNotice_way(user.getMobile());
				msgSend.setCont(noticeQueue.getCont());
				msgSend.setPlan_time(noticeQueue.getCreate_time());
				msgSend.setCreate_time(noticeQueue.getCreate_time());
				boolean result = sendMessage(msgSend);
				// 处理发送结果
				disposeResult(result, noticeQueue);
			}else if (noticeQueue.getNotice_type().equals(GParameter.noticeType_email)) {
				MailSenderInfo email = new MailSenderInfo();
				email.setSubject("Warn Notification");
				email.setContent(noticeQueue.getCont());
				email.setToAddress(user.getEmail());
				boolean result = sendEmailOrLeter(email);
				// 处理发送结果
				disposeResult(result, noticeQueue);
			}else if (noticeQueue.getNotice_type().equals(GParameter.noticeType_manager)) {
				// 用户登录自动推送
			}else if (noticeQueue.getNotice_type().equals(GParameter.noticeType_app)) {
				
			}else if (noticeQueue.getNotice_type().equals(GParameter.noticeType_wechat)) {
				Map<String,String> ret = null;
				try {
					ret = TemplateMsgUtil.sendCustomMsg(JSONObject.parseObject(noticeQueue.getCont()));
					String error_code = ret.get("errcode");
					if("0".equals(error_code)){
						disposeResult(true, noticeQueue);
					}else{
						disposeResult(false, noticeQueue);
					}
				} catch (WeixinException e) {
					disposeResult(false, noticeQueue);
				}
			}else if (noticeQueue.getNotice_type().equals(GParameter.noticeType_paySsystem)) {
				
			}
		}
	}
	
	/**
	 * 发邮件
	 * 
	 * @param obj
	 * @param updatePwd_type
	 */
	private boolean sendEmailOrLeter(MailSenderInfo ro) {	 
		ro.setValidate(true);
		ro.setMailServerHost("smtp.qiye.163.com");
		ro.setMailServerPort("25");
		ro.setUserName("service@szzhilai.com");
		ro.setPassword("Zhilai2014");
		ro.setFromAddress("service@szzhilai.com");
		boolean bflag =new SimpleMailSender().sendTextMail(ro);	
		//log.debug(ro.getToAddress()+" email send "+(bflag?"success":"fail"));
		System.out.println(ro.getToAddress()+" email send "+(bflag?"success":"fail"));
		return bflag;
	}
	
	/**
	 * 发送短信
	 * 
	 * @param mobile
	 * @param cont
	 */
	private boolean sendMessage(MsgSend msgSend) {
		boolean messageBoolean = false;
		try {
			MessageSendReq messageSendReq = new MessageSendReq();
			MessageSendResp messageSendResp = new MessageSendResp();
			msgSend.setBusi_type("1004");
			msgSend.setLevel("1");
			messageSendReq.setMsgSend(msgSend);
			String auth_name = "zhilai_web";
			String auth_id = "zhilai_web";
			messageSendReq.setTcode("1300");
			messageSendReq.setBcode("01");
			messageSendReq.setAuth_name(auth_name);
			messageSendReq.setAuth_id(auth_id);
			String req_xml = messageSendReq.CreateXml();
			ClientCallSoap clientCallSoap = new ClientCallSoap();
			String resp_xml = clientCallSoap.sendMsg(auth_name, auth_id, req_xml);
			messageSendResp.setXml(resp_xml);
			messageSendResp.parseHead();
			String retcode = messageSendResp.getRetcode();
			if (!"0000".equals(retcode)) {
				messageBoolean = false;
			} else {
				messageBoolean = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			messageBoolean = false;
		}
		return messageBoolean;
	}
	
	/**
	 * 
	 * @Title disposeResult
	 * @Description 处理发送结果, 如果发送成功则记录间隔下次发送
	 * @Author GuoXuegan
	 * @data 2018年4月13日下午5:21:28
	 * @param result
	 * @param noticeQueue
	 *
	 */
	private void disposeResult(boolean result, NoticeQueue noticeQueue) {
		if (result) {
			noticeQueue.setSend_state(GParameter.noticeSendState_success);
			noticeQueue.setState_time(DateUtil.getNow());					
			noticeQueueDao.update(noticeQueue);
			
			CorpNoticeTemplate cnt = corpNoticeTemplateDao.get(noticeQueue.getTemp_id());
			
			// 记录下次发送
			NoticeOver no = new NoticeOver();
			no.setLogid(IdGen.uuid());
			no.setMain_type(noticeQueue.getMain_type());
			no.setSub_type(noticeQueue.getSub_type());
			no.setOrder_id(noticeQueue.getOrder_id());
			no.setState_time(DateUtil.getNow());
			no.setState_ctime(DateUtil.getNow());
			no.setState_ntime(DateUtils.addMinute(Integer.parseInt(cnt.getInterval_time())));
			no.setInterval_time(cnt.getInterval_time());
			no.setCreate_time(DateUtil.getNow());
			noticeOverDao.insert(no);
		}else {
			noticeQueue.setFail_num(Integer.parseInt(noticeQueue.getFail_num())+1+"");
			if (Integer.parseInt(noticeQueue.getSend_num()) <= Integer.parseInt(noticeQueue.getFail_num())) {
				noticeQueue.setSend_state(GParameter.noticeSendState_defeat);
			}
			noticeQueue.setState_time(DateUtil.getNow());					
			noticeQueueDao.update(noticeQueue);
		}
	}
}
