package com.zhilai.master.modules.inventory.entity;

import com.zhilai.master.common.persistence.DataEntity;

public class CheckOrderDetail extends DataEntity<CheckOrderDetail> {
	private static final long serialVersionUID = -3716476794376981485L;
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
	private int check_num;
	private int tinventory_num;
	private int winventory_num;
	private int difference_num;
	private String check_type;
	private String cur_state;
	private String state_time;
	private String create_time;

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

	public int getCheck_num() {
		return check_num;
	}

	public void setCheck_num(int check_num) {
		this.check_num = check_num;
	}

	public int getTinventory_num() {
		return tinventory_num;
	}

	public void setTinventory_num(int tinventory_num) {
		this.tinventory_num = tinventory_num;
	}

	public int getWinventory_num() {
		return winventory_num;
	}

	public void setWinventory_num(int winventory_num) {
		this.winventory_num = winventory_num;
	}

	public int getDifference_num() {
		return difference_num;
	}

	public void setDifference_num(int difference_num) {
		this.difference_num = difference_num;
	}

	public String getCheck_type() {
		return check_type;
	}

	public void setCheck_type(String check_type) {
		this.check_type = check_type;
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

}
