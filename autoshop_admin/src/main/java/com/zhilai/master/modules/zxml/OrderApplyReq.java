package com.zhilai.master.modules.zxml;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.putils.utils.DateUtil;

import com.zhilai.master.modules.order.entity.OrderApply;
import com.zhilai.master.modules.order.entity.OrderProduct;
import com.zhilai.master.modules.utils.GConstent;

public class OrderApplyReq extends ClientReqHead {
	private final static Log log = LogFactory.getLog(OrderApplyReq.class);
	private OrderApply orderApply;
	private List<OrderProduct> orderProductList;

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
			JSONbox.put("SiteId", orderApply.getSite_id()+"");
			JSONbox.put("SaleChannel", orderApply.getSale_channel()+"");
			JSONbox.put("LoginId", orderApply.getLogin_id()+"");
			JSONbox.put("LoginName", orderApply.getLogin_name()+"");
			JSONbox.put("ApplyTime", DateUtil.getNow());
			JSONbox.put("PayType",orderApply.getPay_type()+"");
			JSONbox.put("PayPrice",orderApply.getSale_total()+"");
			JSONArray JSONDetailArray = new JSONArray();
			for (OrderProduct orderProduct : orderProductList) {
				JSONObject JSONDetail = new JSONObject();
				JSONDetail.put("ProductId", orderProduct.getProduct_id()+"");
				JSONDetail.put("Num", orderProduct.getSale_num()+"");
				JSONDetail.put("Price", orderProduct.getSale_price()+"");
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
	
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
}