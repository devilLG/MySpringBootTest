package com.zhilai.master.modules.inventory.entity;

import java.util.List;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

public class CheckOrder extends DataEntity<CheckOrder> {
	private static final long serialVersionUID = 3018839574028876051L;
	private String logid;
	private String torder_id;
	private String order_id;
	private String corp_id;
	private String corp_name;
	private String site_id;
	private String site_name;
	private int check_num;
	private int check_box;
	private int check_product;
	private String check_date;
	private String check_id;
	private String check_name;
	private String check_state;
	private String state_time;
	private String create_time;
	
	//虚拟参数
	private List<CheckOrderDetail> checkOrderDetailList;
	//虚拟字段
	private String beginTime;
	private String endTime;

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}
	@ExcelField(title="盘点编号", sort=10)
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	@ExcelField(title="公司编号", sort=25)
	public String getCorp_id() {
		return corp_id;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	@ExcelField(title="公司名称", sort=30)
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
	@ExcelField(title="盘点商品数量", sort=35)
	public int getCheck_num() {
		return check_num;
	}

	public void setCheck_num(int check_num) {
		this.check_num = check_num;
	}
	@ExcelField(title="盘点货道数量", sort=45)
	public int getCheck_box() {
		return check_box;
	}

	public void setCheck_box(int check_box) {
		this.check_box = check_box;
	}
	@ExcelField(title="盘点种类数量", sort=40)
	public int getCheck_product() {
		return check_product;
	}

	public void setCheck_product(int check_product) {
		this.check_product = check_product;
	}
	@ExcelField(title="盘点日期", sort=50)
	public String getCheck_date() {
		return check_date;
	}

	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}

	public String getCheck_id() {
		return check_id;
	}

	public void setCheck_id(String check_id) {
		this.check_id = check_id;
	}
	@ExcelField(title="盘点人名称", sort=55)
	public String getCheck_name() {
		return check_name;
	}

	public void setCheck_name(String check_name) {
		this.check_name = check_name;
	}
	@ExcelField(title="状态", sort=60, dictType="check_state")
	public String getCheck_state() {
		return check_state;
	}

	public void setCheck_state(String check_state) {
		this.check_state = check_state;
	}
	@ExcelField(title="状态时间", sort=65)
	public String getState_time() {
		return state_time;
	}

	public void setState_time(String state_time) {
		this.state_time = state_time;
	}
	@ExcelField(title="创建时间", sort=70)
	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public List<CheckOrderDetail> getCheckOrderDetailList() {
		return checkOrderDetailList;
	}

	public void setCheckOrderDetailList(List<CheckOrderDetail> checkOrderDetailList) {
		this.checkOrderDetailList = checkOrderDetailList;
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

}
