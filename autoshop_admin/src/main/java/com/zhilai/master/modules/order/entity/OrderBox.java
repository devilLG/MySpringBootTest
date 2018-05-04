package com.zhilai.master.modules.order.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

public class OrderBox extends DataEntity<OrderBox> {
	private static final long serialVersionUID = -4706889371709236508L;
	private String logid;
	private String probox_id;
	private String prodetail_id;
	private String corp_id;
	private String corp_name;
	private String order_id;
	private String torder_id;
	private String site_id;
	private String site_name;
	private String box_id;
	private String box_type;
	private String product_id;
	private String product_name;
	private int take_num;
	private int altake_num;
	private String cur_state;
	private String state_time;
	private String create_time;
	
	//虚拟字段
	private String beginTime;
	private String endTime;

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getProbox_id() {
		return probox_id;
	}

	public void setProbox_id(String probox_id) {
		this.probox_id = probox_id;
	}

	public String getProdetail_id() {
		return prodetail_id;
	}

	public void setProdetail_id(String prodetail_id) {
		this.prodetail_id = prodetail_id;
	}

	public String getCorp_id() {
		return corp_id;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	@ExcelField(title="归属公司", sort=30)
	public String getCorp_name() {
		return corp_name;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
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
	@ExcelField(title="货道号", sort=50)
	public String getBox_id() {
		return box_id;
	}
	
	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}
	@ExcelField(title="货道类型", sort=55)
	public String getBox_type() {
		return box_type;
	}

	public void setBox_type(String box_type) {
		this.box_type = box_type;
	}
	@ExcelField(title="商品编号", sort=35)
	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	@ExcelField(title="商品名称", sort=40)
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	@ExcelField(title="取货数量", sort=45)
	public int getTake_num() {
		return take_num;
	}

	public void setTake_num(int take_num) {
		this.take_num = take_num;
	}
	@ExcelField(title="提货数量", sort=60)
	public int getAltake_num() {
		return altake_num;
	}

	public void setAltake_num(int altake_num) {
		this.altake_num = altake_num;
	}
	@ExcelField(title="状态", sort=65, dictType="order_box_state")
	public String getCur_state() {
		return cur_state;
	}

	public void setCur_state(String cur_state) {
		this.cur_state = cur_state;
	}
	@ExcelField(title="状态时间", sort=70)
	public String getState_time() {
		return state_time;
	}

	public void setState_time(String state_time) {
		this.state_time = state_time;
	}
	@ExcelField(title="创建时间", sort=75)
	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
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
