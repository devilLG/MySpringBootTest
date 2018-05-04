package com.zhilai.master.modules.inventory.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

public class ClearProduct extends DataEntity<ClearProduct> {
	private static final long serialVersionUID = 3480050571549650878L;
	private String logid;
	private String order_id;
	private String torder_id;
	private String corp_id;
	private String corp_name;
	private String site_id;
	private String site_name;
	private String box_id;
	private String product_id;
	private String product_name;
	private String bar_code;
	private int clear_num;
	private int inventory_num;
	private String clear_id;
	private String clear_name;
	private String clear_date;
	private String create_time;
	
	//虚拟字段
	private String beginTime;
	private String endTime;

	public String getTorder_id() {
		return torder_id;
	}

	public void setTorder_id(String torder_id) {
		this.torder_id = torder_id;
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}
	@ExcelField(title="清除编号", sort=10)
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
	@ExcelField(title="货道号", sort=25)
	public String getBox_id() {
		return box_id;
	}

	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}
	@ExcelField(title="商品编码", sort=30)
	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	@ExcelField(title="商品名称", sort=35)
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	@ExcelField(title="条码", sort=40)
	public String getBar_code() {
		return bar_code;
	}

	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}
	@ExcelField(title="清除数量", sort=45)
	public int getClear_num() {
		return clear_num;
	}

	public void setClear_num(int clear_num) {
		this.clear_num = clear_num;
	}
	@ExcelField(title="终端库存", sort=50)
	public int getInventory_num() {
		return inventory_num;
	}

	public void setInventory_num(int inventory_num) {
		this.inventory_num = inventory_num;
	}

	public String getClear_id() {
		return clear_id;
	}

	public void setClear_id(String clear_id) {
		this.clear_id = clear_id;
	}
	@ExcelField(title="清除人名称", sort=60)
	public String getClear_name() {
		return clear_name;
	}

	public void setClear_name(String clear_name) {
		this.clear_name = clear_name;
	}
	@ExcelField(title="清除日期", sort=55)
	public String getClear_date() {
		return clear_date;
	}

	public void setClear_date(String clear_date) {
		this.clear_date = clear_date;
	}
	@ExcelField(title="创建时间", sort=65)
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
