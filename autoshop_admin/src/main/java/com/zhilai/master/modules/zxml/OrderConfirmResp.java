package com.zhilai.master.modules.zxml;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OrderConfirmResp extends ClientRespHead {
	private final static Log log = LogFactory.getLog(OrderConfirmResp.class);	
   
	public Map parseXml(String report_json) {
		Map r = new HashMap();
		int ret=this.parseHead();
		if(ret!=0)  return r;
		JSONObject JSONBody = JSONUtils.getBody(report_json);
		JSONArray JSONArray = JSONBody.getJSONArray("OrderInfo");
		if (JSONArray != null && JSONArray.size() > 0) {
			JSONObject jSONObject = (JSONObject) JSONArray.get(0);
			r.put("status", jSONObject.getString("Status"));
		}
		r.put("code", 0);
		return r;
	}
	
	@Override
	public String parseXml() {
		// TODO Auto-generated method stub
		return "0";
	}
 
}
