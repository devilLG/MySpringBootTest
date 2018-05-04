package com.zhilai.master.modules.utils;


public class SendSMS {

	public int send(String mobile, String content) {
		//用户名
		String Uid = GParameter.Message_Uid;
		//接口安全秘钥
		String Key = GParameter.Message_Key;
		//手机号码
		String smsMob = mobile;
		//短信内容
		String smsText = content;
		//MsgSendService msgSendService = MsgSendService.getInstance();
		//int result = msgSendService.sendMsgUtf8(Uid, Key, smsText, smsMob);
		return 0;
	}
}
