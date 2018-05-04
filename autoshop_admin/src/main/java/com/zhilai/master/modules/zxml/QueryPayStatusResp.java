package com.zhilai.master.modules.zxml;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QueryPayStatusResp extends ClientRespHead {
	private final static Log log = LogFactory.getLog(QueryPayStatusResp.class);	
   
	public Map parseXml(String report_json) {
		Map r = new HashMap();
		int ret=this.parseHead();
		if(ret!=0)  return r;
		JSONObject JSONBody = JSONUtils.getBody(report_json);
		r.put("pay_state", JSONBody.getString("PayState"));
		r.put("code", 0);
		return r;
	}
	
	@Override
	public String parseXml() {
		// TODO Auto-generated method stub
		return "0";
	}
 
}

