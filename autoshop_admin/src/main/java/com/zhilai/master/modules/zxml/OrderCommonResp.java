package com.zhilai.master.modules.zxml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OrderCommonResp extends ClientRespHead {
	private final static Log log = LogFactory.getLog(OrderCommonResp.class);	
   
	@Override
	public String parseXml() {
		int ret=this.parseHead();
		String error;
		if(ret!=0){
			String errorCode = this.jsonObject.getJSONObject("ZMSG").getJSONObject("ZHEAD").getString("RetCode");
			error =  "错误代码:"+errorCode;
//					+"  错误描述:"+this.jsonObject.getJSONObject("ZMSG").getJSONObject("ZHEAD").getString("RetMsg");
			if(errorCode.equals("1615")){
				error = "当前状态下不允许进行此操作";
			}
			if(errorCode.equals("1608")){
				error = "存在未支付订单，请先完成支付";
			}
			if(errorCode.equals("1610")){
				error = "商品可售卖数不足";
			}
			return error;
		}
		return "0";
	}

 
}