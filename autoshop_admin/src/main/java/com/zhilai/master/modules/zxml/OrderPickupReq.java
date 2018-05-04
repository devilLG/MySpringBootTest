package com.zhilai.master.modules.zxml;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.putils.utils.DateUtil;

import com.zhilai.master.modules.order.entity.OrderApply;
import com.zhilai.master.modules.order.entity.OrderBox;
import com.zhilai.master.modules.utils.GConstent;

public class OrderPickupReq extends ClientReqHead {
	private final static Log log = LogFactory.getLog(OrderPickupReq.class);
	private OrderApply orderApply;
	private List<OrderBox> orderBoxList;

	@Override
	public String CreateXml() {
		try {
			JSONObject rootObject = new JSONObject();

			JSONObject JSONObject = new JSONObject();
			JSONObject JSONHeaderObject = new JSONObject();
			JSONHeaderObject.put("BCode", "03");
			JSONHeaderObject.put("TCode", this.tcode);
			JSONHeaderObject.put("IStart", "1");
			JSONHeaderObject.put("IEnd", "1");
			JSONHeaderObject.put("IFlag", "1");
			JSONObject.put(GConstent.ZxmlHead, JSONHeaderObject);

			rootObject.put(GConstent.ZxmlRoot, JSONObject);
			JSONObject JSONbox = new JSONObject();
			JSONbox.put("AuthId", this.auth_id);
			JSONbox.put("AuthName", this.auth_name);
			JSONbox.put("SiteId", orderApply.getSite_id()+"");
			JSONbox.put("OrderId", orderApply.getOrder_id()+"");
			JSONbox.put("SaleChannel", orderApply.getSale_channel()+"");
			JSONbox.put("OperTime", DateUtil.getNow());
			JSONArray JSONDetailArray = new JSONArray();
			for (OrderBox orderBox : orderBoxList) {
				JSONObject JSONDetail = new JSONObject();
				JSONDetail.put("ProductId", orderBox.getProduct_id()+"");
				JSONDetail.put("BoxId", orderBox.getBox_id()+"");
				JSONDetail.put("Num", orderBox.getAltake_num()+"");
				JSONDetailArray.add(JSONDetail);
			}
			JSONbox.put("ProductInfo", JSONDetailArray);

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
	
	public List<OrderBox> getOrderBoxList() {
		return orderBoxList;
	}

	public void setOrderBoxList(List<OrderBox> orderBoxList) {
		this.orderBoxList = orderBoxList;
	}
}
