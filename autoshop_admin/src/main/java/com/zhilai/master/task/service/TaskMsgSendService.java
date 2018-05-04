package com.zhilai.master.task.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.putils.mail.util.MailSenderInfo;
import org.apache.commons.putils.mail.util.SimpleMailSender;
import org.apache.commons.putils.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.modules.parmter.dao.MsgSendDao;
import com.zhilai.master.modules.parmter.entity.MsgSend;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.zxml.ClientCallSoap;
import com.zhilai.master.modules.zxml.MessageSendReq;
import com.zhilai.master.modules.zxml.MessageSendResp;

@Service
@Transactional(readOnly = true)
public class TaskMsgSendService {
	private static Logger log = Logger.getLogger(TaskMsgSendService.class);
	@Autowired
	private MsgSendDao msgSendDao;

	@Transactional(readOnly = false)
	public String execute(Date date) {
		String retCode = "0";
		String send_num = "3";
		List<MsgSend> list = null;
		try {
			//查找待发送通知队列
			MsgSend msgSendP = new MsgSend();
			msgSendP.setProc_flag(GParameter.procFlag_no);
			list = msgSendDao.findList(msgSendP);
			if(list != null && list.size() > 0){
				for (MsgSend msgSend : list) {
					if(msgSend.getNotice_type().equals(GParameter.noticeType_mobile)){
						//发送短信
						boolean messageBoolean = sendMessage(msgSend);
						if(messageBoolean){
							msgSend.setFail_num(msgSend.getFail_num() + 1);
							msgSend.setProc_flag(GParameter.procFlag_success);
							msgSend.setProc_time(DateUtil.getNow());
							msgSendDao.update(msgSend);
						}else{
							msgSend.setFail_num(msgSend.getFail_num() + 1);
							if(msgSend.getFail_num() >= Integer.parseInt(send_num)){
								msgSend.setProc_flag(GParameter.procFlag_failed);
							}
							msgSend.setProc_time(DateUtil.getNow());
							msgSendDao.update(msgSend);
						}
					}else if(msgSend.getNotice_type().equals(GParameter.noticeType_email)){
						MailSenderInfo email = new MailSenderInfo();
						email.setSubject("Warn Notification");
						email.setContent(msgSend.getCont());
						email.setToAddress(msgSend.getNotice_way());
						boolean bflag =sendEmailOrLeter(email);
						if(bflag){
							msgSend.setFail_num(msgSend.getFail_num() + 1);
							msgSend.setProc_flag(GParameter.procFlag_success);
							msgSend.setProc_time(DateUtil.getNow());
							msgSendDao.update(msgSend);
						}else{
							msgSend.setFail_num(msgSend.getFail_num() + 1);
							if(msgSend.getFail_num() >= Integer.parseInt(send_num)){
								msgSend.setProc_flag(GParameter.procFlag_failed);
							}
							msgSend.setProc_time(DateUtil.getNow());
							msgSendDao.update(msgSend);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retCode;
	}
	
	/**
	 * 发送短信
	 * 
	 * @param mobile
	 * @param cont
	 */
	public static boolean sendMessage(MsgSend msgSend) {
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
	 * updatePwd_type=2叶发邮件，=3时发短信 发送短信或邮件
	 * 
	 * @param obj
	 * @param updatePwd_type
	 */
	public static boolean sendEmailOrLeter(MailSenderInfo ro) {	 
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
}
