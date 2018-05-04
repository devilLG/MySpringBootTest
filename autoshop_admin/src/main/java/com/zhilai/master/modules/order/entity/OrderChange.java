package com.zhilai.master.modules.order.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

public class OrderChange extends DataEntity<OrderChange> {
	private static final long serialVersionUID = 8382972370895266273L;
	private String logid;
	private String change_id;
	private String corp_id;
	private String order_id;
	private String torder_id;
	private String bar_code;
	private String oper_id;
	private String oper_name;
	private String site_id;
	private String site_name;
	private String oper_action;
	private String oper_time;
	private String oper_cont;
	private String create_time;
	private String poc_state;
	private int poc_times;
	private String poc_result;
	
	//虚拟字段
	private String beginTime;
	private String endTime;

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getChange_id() {
		return change_id;
	}

	public void setChange_id(String change_id) {
		this.change_id = change_id;
	}

	public String getCorp_id() {
		return corp_id;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	@ExcelField(title="订单编号", sort=10)
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	@ExcelField(title="终端订单编号", sort=15)
	public String getTorder_id() {
		return torder_id;
	}

	public void setTorder_id(String torder_id) {
		this.torder_id = torder_id;
	}
	@ExcelField(title="条码", sort=30)
	public String getBar_code() {
		return bar_code;
	}

	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}
	@ExcelField(title="操作人编号", sort=40)
	public String getOper_id() {
		return oper_id;
	}

	public void setOper_id(String oper_id) {
		this.oper_id = oper_id;
	}
	@ExcelField(title="操作人名称", sort=45)
	public String getOper_name() {
		return oper_name;
	}

	public void setOper_name(String oper_name) {
		this.oper_name = oper_name;
	}
	@ExcelField(title="站点编号", sort=20)
	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	@ExcelField(title="站点简称", sort=25)
	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	@ExcelField(title="操作状态", sort=35, dictType="order_change_state")
	public String getOper_action() {
		return oper_action;
	}

	public void setOper_action(String oper_action) {
		this.oper_action = oper_action;
	}
	@ExcelField(title="操作时间", sort=50)
	public String getOper_time() {
		return oper_time;
	}

	public void setOper_time(String oper_time) {
		this.oper_time = oper_time;
	}
	@ExcelField(title="操作内容", sort=55)
	public String getOper_cont() {
		return oper_cont;
	}

	public void setOper_cont(String oper_cont) {
		this.oper_cont = oper_cont;
	}
	@ExcelField(title="创建时间", sort=75)
	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	@ExcelField(title="推送状态", sort=60, dictType="orderChange_push_state")
	public String getPoc_state() {
		return poc_state;
	}

	public void setPoc_state(String poc_state) {
		this.poc_state = poc_state;
	}
	@ExcelField(title="推送次数", sort=65)
	public int getPoc_times() {
		return poc_times;
	}

	public void setPoc_times(int poc_times) {
		this.poc_times = poc_times;
	}
	@ExcelField(title="推送结果", sort=70)
	public String getPoc_result() {
		return poc_result;
	}

	public void setPoc_result(String poc_result) {
		this.poc_result = poc_result;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
