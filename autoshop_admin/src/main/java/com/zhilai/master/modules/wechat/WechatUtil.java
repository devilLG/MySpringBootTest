package com.zhilai.master.modules.wechat;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.weixin4j.Configuration;
import org.weixin4j.OAuth2;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.WeixinSupport;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.OAuth2Token;
import org.weixin4j.http.OAuthToken;
import org.weixin4j.http.Response;
import org.weixin4j.pay.JsApiTicket;

import com.alibaba.fastjson.JSONObject;
import com.zhilai.master.modules.utils.GParameter;

public class WechatUtil extends WeixinSupport {
	private static final Log log = LogFactory.getLog(WechatUtil.class);
	private OAuthToken oAuthToken = null;//微信平台用户凭证
	private JsApiTicket jsApiTicket = null;
	
	private static WechatUtil instance = new WechatUtil();
	private WechatUtil(){}
	public static WechatUtil getInstance(){
		return instance;
	}
	
	/**
	 * 获取微信平台用户凭证
	 * @return OAuthToken
	 */
	public OAuthToken getOAuthToken(){
		if(oAuthToken == null || oAuthToken.isExprexpired()){
			Weixin weixin = new Weixin();
			try {
				oAuthToken = weixin.login(GParameter.APPID, GParameter.SECRET);
			} catch (WeixinException e) {
				log.error("获取微信平台用户凭证失败", e);
			}
		}
		return oAuthToken;
	}
	
	/**
	 * 获取网页授权对象
	 * @param code
	 * @return
	 */
	public OAuth2Token getOAuth2Token(String code){
		OAuth2 oAuth2 = new OAuth2();
		try {
			return oAuth2.login(GParameter.APPID, GParameter.SECRET, code);
		} catch (WeixinException e) {
			log.error("获取微信平台用户凭证失败", e);
			return null;
		}
	}
	
	/**
	 * 获取jsapi_ticket
	 * @return
	 */
	public JsApiTicket getJsApiTicket(){
		if(jsApiTicket == null || jsApiTicket.isExprexpired()){
			HttpsClient http = new HttpsClient();
			//调用获取jsapi_ticket接口
			Response res;
			try {
				res = http.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + getOAuthToken().getAccess_token() + "&type=jsapi");
				//根据请求结果判定，是否验证成功
				JSONObject jsonObj = res.asJSONObject();
				//成功返回如下JSON:
				//{"errcode":0,"errmsg":"ok","ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA","expires_in":7200}
				if (jsonObj != null) {
					if (Configuration.isDebug()) {
						log.debug("获取jsapi_ticket返回json：" + jsonObj.toString());
					}
					Object errcode = jsonObj.get("errcode");
					if (errcode != null && !errcode.toString().equals("0")) {
						//返回异常信息
						log.error("获取jsapi_ticket失败："+getCause(Integer.parseInt(errcode.toString())));
					} else {
						jsApiTicket = new JsApiTicket(jsonObj.getString("ticket"), jsonObj.getIntValue("expires_in"));
					}
				}
			} catch (WeixinException e) {
				log.error("获取微信平台用户凭证失败", e);
			}
			
		}
		return jsApiTicket;
	}
	
	/**
	 * 签名算法
	 * @param url
	 * @return
	 */
	public Map<String, String> sign(String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";
		String jsapi_ticket = getJsApiTicket().getTicket();

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
				+ "&timestamp=" + timestamp + "&url=" + url;
		log.debug("加密字符串string1："+string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
			log.debug("signature:"+signature);
		} catch (Exception e) {
			log.error("签名算法失败", e);
		}

		ret.put("url", url);
		ret.put("appId", GParameter.APPID);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
