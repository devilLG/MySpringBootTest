package com.zhilai.master.modules.zxml;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhilai.master.modules.utils.GConstent;

public class GetPaymentInfoReq extends ClientReqHead {
	private final static Log log = LogFactory.getLog(GetPaymentInfoReq.class);
	private String corpId;
	//private String orderType;
	private String orderState;
	private String payType;
	private String sTime;
	private String eTime;

	@Override
	public String CreateXml() {
		try {
			JSONObject rootObject = new JSONObject();

			JSONObject JSONObject = new JSONObject();
			JSONObject JSONHeaderObject = new JSONObject();
			JSONHeaderObject.put("BCode", this.bcode);
			JSONHeaderObject.put("TCode", this.tcode);
			JSONHeaderObject.put("IStart", "1");
			JSONHeaderObject.put("IEnd", "1");
			JSONHeaderObject.put("IFlag", "1");
			JSONObject.put(GConstent.ZxmlHead, JSONHeaderObject);

			rootObject.put(GConstent.ZxmlRoot, JSONObject);
			JSONObject JSONbox = new JSONObject();
			JSONbox.put("AuthId", this.auth_id);
			JSONbox.put("AuthName", this.auth_name);
			
			JSONbox.put("CorpId", corpId);
			JSONbox.put("FeeType", "01");
			JSONbox.put("PatState", orderState);
			JSONbox.put("PayType", payType);
			JSONbox.put("StartTime", sTime);
			JSONbox.put("EndTime", eTime);
			JSONObject JSONObjectBody = (JSONObject) rootObject.get(GConstent.ZxmlRoot);
			JSONObjectBody.put(GConstent.ZxmlBody, JSONbox);
			return rootObject.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			return null;
		}
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	/*public void setOrderType(String orderType) {
		this.orderType = orderType;
	}*/

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public void setPayType(String payType) {
		if (payType.equals("00")) {
			this.payType = "2001";
		}else if (payType.equals("01")) {
			this.payType = "1001";
		}else if (payType.equals("02")) {
			this.payType = "1002";
		}
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public void seteTime(String eTime) {
		this.eTime = eTime;
	}
	
}
