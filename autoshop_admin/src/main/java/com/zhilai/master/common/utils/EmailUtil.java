package com.zhilai.master.common.utils;

import org.apache.commons.putils.mail.util.MailSenderInfo;
import org.apache.commons.putils.mail.util.SimpleMailSender;

public class EmailUtil {
 

	public static void main(String[] args) {
		MailSenderInfo obj = new MailSenderInfo();
		obj.setSubject("test1111");
		obj.setContent("zheng test11111");
		obj.setToAddress("zhengzhiwu@szzhilai.com");
		sendEmailOrLeter(obj);

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
		//ro.setToAddress("zhengzhiwu@szzhilai.com");
		//ro.setToAddress("954212942@qq.com");
		//ro.setToAddress("376973701@qq.com");
		boolean bflag =new SimpleMailSender().sendTextMail(ro);		
		return bflag;
	}
}
