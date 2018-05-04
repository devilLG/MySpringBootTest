package com.zhilai.master.modules.inventory.entity;

import java.util.List;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

public class BarterOrder extends DataEntity<BarterOrder> {
	private static final long serialVersionUID = 5734279913508976800L;
	private String logid;
	private String torder_id;
	private String order_id;
	private String corp_id;
	private String corp_name;
	private String site_id;
	private String site_name;
	private int barter_num;
	private int barter_box;
	private int barter_product;
	private String barter_date;
	private String barter_id;
	private String barter_name;
	private String barter_state;
	private String state_time;
	private String down_state;
	private String down_time;
	private String create_time;
	private String barter_type;
	
	//虚拟字段
	private String beginTime;
	private String endTime;

	private List<BarterOrderDetail> barterOrderDetailList;

	public List<BarterOrderDetail> getBarterOrderDetailList() {
		return barterOrderDetailList;
	}

	public void setBarterOrderDetailList(List<BarterOrderDetail> barterOrderDetailList) {
		this.barterOrderDetailList = barterOrderDetailList;
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}
	@ExcelField(title="换货编号", sort=10)
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getCorp_id() {
		return corp_id;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}

	public String getCorp_name() {
		return corp_name;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}
	@ExcelField(title="站点编号", sort=15)
	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	@ExcelField(title="站点简称", sort=20)
	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	@ExcelField(title="换货商品数量", sort=25)
	public int getBarter_num() {
		return barter_num;
	}

	public void setBarter_num(int barter_num) {
		this.barter_num = barter_num;
	}
	@ExcelField(title="换货货道数量", sort=35)
	public int getBarter_box() {
		return barter_box;
	}

	public void setBarter_box(int barter_box) {
		this.barter_box = barter_box;
	}
	@ExcelField(title="换货种类数量", sort=30)
	public int getBarter_product() {
		return barter_product;
	}

	public void setBarter_product(int barter_product) {
		this.barter_product = barter_product;
	}
	@ExcelField(title="换货日期", sort=40)
	public String getBarter_date() {
		return barter_date;
	}

	public void setBarter_date(String barter_date) {
		this.barter_date = barter_date;
	}

	public String getBarter_id() {
		return barter_id;
	}

	public void setBarter_id(String barter_id) {
		this.barter_id = barter_id;
	}
	@ExcelField(title="换货人名称", sort=45)
	public String getBarter_name() {
		return barter_name;
	}

	public void setBarter_name(String barter_name) {
		this.barter_name = barter_name;
	}
	@ExcelField(title="换货状态", sort=50, dictType="barter_state")
	public String getBarter_state() {
		return barter_state;
	}

	public void setBarter_state(String barter_state) {
		this.barter_state = barter_state;
	}
	@ExcelField(title="状态时间", sort=55)
	public String getState_time() {
		return state_time;
	}

	public void setState_time(String state_time) {
		this.state_time = state_time;
	}
	@ExcelField(title="下发状态", sort=60, dictType="down_state")
	public String getDown_state() {
		return down_state;
	}

	public void setDown_state(String down_state) {
		this.down_state = down_state;
	}
	@ExcelField(title="下发时间", sort=65)
	public String getDown_time() {
		return down_time;
	}

	public void setDown_time(String down_time) {
		this.down_time = down_time;
	}
	@ExcelField(title="创建时间", sort=70)
	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getTorder_id() {
		return torder_id;
	}

	public void setTorder_id(String torder_id) {
		this.torder_id = torder_id;
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

	public String getBarter_type() {
		return barter_type;
	}

	public void setBarter_type(String barter_type) {
		this.barter_type = barter_type;
	}

}
