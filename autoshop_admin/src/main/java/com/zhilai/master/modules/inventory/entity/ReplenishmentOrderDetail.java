package com.zhilai.master.modules.inventory.entity;

import com.zhilai.master.common.persistence.DataEntity;

public class ReplenishmentOrderDetail extends DataEntity<ReplenishmentOrderDetail> {
	private static final long serialVersionUID = 4870910850658068469L;
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
	private int surplus_num;
	private int replen_num;
	private int sold_num;
	private int overdue_num;
	private String replen_date;
	private String invalid_date;
	private String cur_state;
	private String state_time;
	private String create_time;
	private String down_state;
	private String down_time;

	public String getDown_state() {
		return down_state;
	}

	public void setDown_state(String down_state) {
		this.down_state = down_state;
	}

	public String getDown_time() {
		return down_time;
	}

	public void setDown_time(String down_time) {
		this.down_time = down_time;
	}

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

	public int getSurplus_num() {
		return surplus_num;
	}

	public void setSurplus_num(int surplus_num) {
		this.surplus_num = surplus_num;
	}

	public int getReplen_num() {
		return replen_num;
	}

	public void setReplen_num(int replen_num) {
		this.replen_num = replen_num;
	}

	public int getSold_num() {
		return sold_num;
	}

	public void setSold_num(int sold_num) {
		this.sold_num = sold_num;
	}

	public int getOverdue_num() {
		return overdue_num;
	}

	public void setOverdue_num(int overdue_num) {
		this.overdue_num = overdue_num;
	}

	public String getReplen_date() {
		return replen_date;
	}

	public void setReplen_date(String replen_date) {
		this.replen_date = replen_date;
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

}
