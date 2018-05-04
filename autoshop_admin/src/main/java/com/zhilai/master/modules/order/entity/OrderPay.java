package com.zhilai.master.modules.order.entity;

import com.zhilai.master.common.persistence.DataEntity;
/**
 * 
 * @author Administrator
 * 
 */
public class OrderPay extends DataEntity<OrderPay> {
	private static final long serialVersionUID = -9100835566163128788L;
	private String logid;
	private String site_id;
	private String order_id;
	private String torder_id;
	private double sale_total;
	private String pay_type;
	private String pay_state;
	private String pay_overtime;
	private int push_num;
	private String push_type;
	private String error_cont;
	private String create_time;
	private String OutTradeNo;
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getTorder_id() {
		return torder_id;
	}
	public void setTorder_id(String torder_id) {
		this.torder_id = torder_id;
	}
	public double getSale_total() {
		return sale_total;
	}
	public void setSale_total(double sale_total) {
		this.sale_total = sale_total;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getPay_state() {
		return pay_state;
	}
	public void setPay_state(String pay_state) {
		this.pay_state = pay_state;
	}
	public String getPay_overtime() {
		return pay_overtime;
	}
	public void setPay_overtime(String pay_overtime) {
		this.pay_overtime = pay_overtime;
	}
	public int getPush_num() {
		return push_num;
	}
	public void setPush_num(int push_num) {
		this.push_num = push_num;
	}
	public String getPush_type() {
		return push_type;
	}
	public void setPush_type(String push_type) {
		this.push_type = push_type;
	}
	public String getError_cont() {
		return error_cont;
	}
	public void setError_cont(String error_cont) {
		this.error_cont = error_cont;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getOutTradeNo() {
		return OutTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		OutTradeNo = outTradeNo;
	}
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

}
