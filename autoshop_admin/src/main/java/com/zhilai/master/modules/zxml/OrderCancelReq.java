package com.zhilai.master.modules.zxml;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhilai.master.modules.order.entity.OrderApply;
import com.zhilai.master.modules.utils.GConstent;

public class OrderCancelReq extends ClientReqHead {
	private final static Log log = LogFactory.getLog(OrderCancelReq.class);
	private OrderApply orderApply;

	@Override
	public String CreateXml() {
		try {
			JSONObject rootObject = new JSONObject();

			JSONObject JSONObject = new JSONObject();
			JSONObject JSONHeaderObject = new JSONObject();
			JSONHeaderObject.put("BCode", "04");
			JSONHeaderObject.put("TCode", this.tcode);
			JSONHeaderObject.put("IStart", "1");
			JSONHeaderObject.put("IEnd", "1");
			JSONHeaderObject.put("IFlag", "1");
			JSONObject.put(GConstent.ZxmlHead, JSONHeaderObject);

			rootObject.put(GConstent.ZxmlRoot, JSONObject);
			JSONObject JSONbox = new JSONObject();
			JSONbox.put("AuthId", this.auth_id);
			JSONbox.put("AuthName", this.auth_name);
			JSONbox.put("OrderId", orderApply.getOrder_id()+"");
			JSONObject JSONObjectBody = (JSONObject) rootObject.get(GConstent.ZxmlRoot);
			JSONObjectBody.put(GConstent.ZxmlBody, JSONbox);
			return rootObject.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			return null;
		}
	}

	public OrderApply getOrderApply() {
		return orderApply;
	}

	public void setOrderApply(OrderApply orderApply) {
		this.orderApply = orderApply;
	}
	
}