package com.zhilai.master.modules.wechat;

import java.util.HashMap;
import java.util.Map;

import org.weixin4j.WeixinException;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.Response;

import com.alibaba.fastjson.JSONObject;

public class TemplateMsgUtil {

	/**
	 * 发送微信模板消息
	 * @param msgContent
	 * @return
	 * @throws WeixinException
	 */
	public static Map<String,String> sendTemplateMsg(JSONObject msgJson) throws WeixinException{
		HttpsClient http = new HttpsClient();
        Response res = http.post("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + WechatUtil.getInstance().getOAuthToken().getAccess_token(), msgJson);
        Map<String,String> ret = new HashMap<String, String>();
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        if (jsonObj != null) {
            String errcode = jsonObj.get("errcode").toString();
            String errmsg = jsonObj.get("errmsg").toString();
            String msgid = jsonObj.get("msgid").toString();
            ret.put("errcode", errcode);
            ret.put("errmsg", errmsg);
            ret.put("msgid", msgid);
        }
        return ret;
	}
	
	public static Map<String,String> sendCustomMsg(JSONObject msgJson) throws WeixinException{
		HttpsClient http = new HttpsClient();
        Response res = http.post("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + WechatUtil.getInstance().getOAuthToken().getAccess_token(), msgJson);
        Map<String,String> ret = new HashMap<String, String>();
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        if (jsonObj != null) {
            String errcode = jsonObj.get("errcode").toString();
            String errmsg = jsonObj.get("errmsg").toString();
            ret.put("errcode", errcode);
            ret.put("errmsg", errmsg);
            ret.put("msgid", "");
        }
        return ret;
	}
}
