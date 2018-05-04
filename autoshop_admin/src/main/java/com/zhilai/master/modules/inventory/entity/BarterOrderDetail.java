package com.zhilai.master.modules.inventory.entity;

import com.zhilai.master.common.persistence.DataEntity;

public class BarterOrderDetail extends DataEntity<BarterOrderDetail> {
	private static final long serialVersionUID = 2091817331968360639L;
	private String logid;
	private String detail_id;
	private String order_id;
	private String corp_id;
	private String corp_name;
	private String site_id;
	private String site_name;
	private String box_id;
	private String product_id;
	private String product_name;
	private int barter_num;
	private int inventory_num;
	private String bproduct_id;
	private String bproduct_name;
	private int surplus_num;
	private String barter_date;
	private String invalid_date;
	private String cur_state;
	private String state_time;
	private String create_time;
	private int warn_num;
	
	//虚拟字段
	private String temper_type;

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
	}

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

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public String getBox_id() {
		return box_id;
	}

	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getBarter_num() {
		return barter_num;
	}

	public void setBarter_num(int barter_num) {
		this.barter_num = barter_num;
	}

	public int getInventory_num() {
		return inventory_num;
	}

	public void setInventory_num(int inventory_num) {
		this.inventory_num = inventory_num;
	}

	public String getBproduct_id() {
		return bproduct_id;
	}

	public void setBproduct_id(String bproduct_id) {
		this.bproduct_id = bproduct_id;
	}

	public String getBproduct_name() {
		return bproduct_name;
	}

	public void setBproduct_name(String bproduct_name) {
		this.bproduct_name = bproduct_name;
	}

	public int getSurplus_num() {
		return surplus_num;
	}

	public void setSurplus_num(int surplus_num) {
		this.surplus_num = surplus_num;
	}

	public String getBarter_date() {
		return barter_date;
	}

	public void setBarter_date(String barter_date) {
		this.barter_date = barter_date;
	}

	public String getInvalid_date() {
		return invalid_date;
	}

	public void setInvalid_date(String invalid_date) {
		this.invalid_date = invalid_date;
	}

	public String getCur_state() {
		return cur_state;
	}

	public void setCur_state(String cur_state) {
		this.cur_state = cur_state;
	}

	public String getState_time() {
		return state_time;
	}

	public void setState_time(String state_time) {
		this.state_time = state_time;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public int getWarn_num() {
		return warn_num;
	}

	public void setWarn_num(int warn_num) {
		this.warn_num = warn_num;
	}

	public String getTemper_type() {
		return temper_type;
	}

	public void setTemper_type(String temper_type) {
		this.temper_type = temper_type;
	}

}
