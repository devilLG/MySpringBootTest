package com.zhilai.master.modules.zxml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhilai.master.modules.pay.entity.PayPaymentInfo;

public class GetPaymentInfoResp extends ClientRespHead {
	private PayPaymentInfo payPaymentInfo = null;
	List<PayPaymentInfo> list = new ArrayList<PayPaymentInfo>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map parseXml(String report_json) {
		Map r = new HashMap();
		int ret = this.parseHead();
		if(ret!=0)  return r;
		JSONObject JSONBody = JSONUtils.getBody(report_json);
		JSONArray JSONArray = JSONBody.getJSONArray("order");
		if (JSONArray != null && JSONArray.size() > 0) {
			for (int i = 0; i < JSONArray.size(); i++) {
				JSONObject jSONObject = (JSONObject) JSONArray.get(i);
				payPaymentInfo = new PayPaymentInfo();
				payPaymentInfo.setCorp_id(jSONObject.getString("CorpId"));
				payPaymentInfo.setCorp_name(jSONObject.getString("CorpName"));
				payPaymentInfo.setOrder_id(jSONObject.getString("OrderID"));
				payPaymentInfo.setPay_id(jSONObject.getString("OutTradeNo"));
				payPaymentInfo.setPay_channel(jSONObject.getString("PayType"));
				payPaymentInfo.setFee_sum(Integer.parseInt(jSONObject.getString("TotalFee")));
				payPaymentInfo.setCurrency(jSONObject.getString("CurrencyType"));
				payPaymentInfo.setTrade_number(jSONObject.getString("OutTradeNo"));
				payPaymentInfo.setTrade_time(jSONObject.getString("OrderTime"));
				payPaymentInfo.setTrade_sum(Double.parseDouble(jSONObject.getString("TotalFee")));
				payPaymentInfo.setSite_id(jSONObject.getString("SiteID"));
				payPaymentInfo.setSite_name(jSONObject.getString("SiteName"));
				payPaymentInfo.setPay_state(jSONObject.getString("PayState"));
				list.add(payPaymentInfo);
			}
		}		
		
		r.put("payPaymentInfoList", list);
		r.put("code", 0);
		return r;
	}
	
	@Override
	public String parseXml() {
		return "0";
	}
 
}
