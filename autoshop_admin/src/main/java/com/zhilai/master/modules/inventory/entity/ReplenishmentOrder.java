package com.zhilai.master.modules.inventory.entity;

import java.util.List;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

public class ReplenishmentOrder extends DataEntity<ReplenishmentOrder> {
	private static final long serialVersionUID = 7329762937455194205L;
	private String logid;
	private String order_id;
	private String torder_id;
	private String corp_id;
	private String corp_name;
	private String site_id;
	private String site_name;
	private int replen_num;
	private int replen_box;
	private int replen_product;
	private int lack_num;
	private int lack_box;
	private int lack_product;
	private String replen_date;
	private String replener_id;
	private String replener_name;
	private String cur_state;
	private String order_type;
	private String state_time;
	private String create_time;
	
	//虚拟参数
	private List<ReplenishmentOrderDetail> replenishmentOrderDetailList;
	//虚拟字段
	private String beginTime;
	private String endTime;

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}
	@ExcelField(title="补货编号", sort=10)
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
	@ExcelField(title="补货商品数量", sort=50)
	public int getReplen_num() {
		return replen_num;
	}

	public void setReplen_num(int replen_num) {
		this.replen_num = replen_num;
	}
	@ExcelField(title="补货货道数量", sort=60)
	public int getReplen_box() {
		return replen_box;
	}

	public void setReplen_box(int replen_box) {
		this.replen_box = replen_box;
	}
	@ExcelField(title="补货种类数量", sort=55)
	public int getReplen_product() {
		return replen_product;
	}

	public void setReplen_product(int replen_product) {
		this.replen_product = replen_product;
	}
	@ExcelField(title="缺货商品数量", sort=35)
	public int getLack_num() {
		return lack_num;
	}

	public void setLack_num(int lack_num) {
		this.lack_num = lack_num;
	}
	@ExcelField(title="缺货货道数量", sort=45)
	public int getLack_box() {
		return lack_box;
	}

	public void setLack_box(int lack_box) {
		this.lack_box = lack_box;
	}
	@ExcelField(title="缺货种类数量", sort=40)
	public int getLack_product() {
		return lack_product;
	}

	public void setLack_product(int lack_product) {
		this.lack_product = lack_product;
	}
	@ExcelField(title="补货日期", sort=65)
	public String getReplen_date() {
		return replen_date;
	}

	public void setReplen_date(String replen_date) {
		this.replen_date = replen_date;
	}

	public String getReplener_id() {
		return replener_id;
	}

	public void setReplener_id(String replener_id) {
		this.replener_id = replener_id;
	}
	@ExcelField(title="补货人名称", sort=70)
	public String getReplener_name() {
		return replener_name;
	}

	public void setReplener_name(String replener_name) {
		this.replener_name = replener_name;
	}
	@ExcelField(title="状态", sort=80, dictType="replenishment_cur_state")
	public String getCur_state() {
		return cur_state;
	}

	public void setCur_state(String cur_state) {
		this.cur_state = cur_state;
	}
	@ExcelField(title="单据类型", sort=75, dictType="replenishment_order_type")
	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	@ExcelField(title="状态时间", sort=85)
	public String getState_time() {
		return state_time;
	}

	public void setState_time(String state_time) {
		this.state_time = state_time;
	}
	@ExcelField(title="创建时间", sort=90)
	public String getCreate_time() {
		return create_time;
	}
	
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public List<ReplenishmentOrderDetail> getReplenishmentOrderDetailList() {
		return replenishmentOrderDetailList;
	}

	public void setReplenishmentOrderDetailList(
			List<ReplenishmentOrderDetail> replenishmentOrderDetailList) {
		this.replenishmentOrderDetailList = replenishmentOrderDetailList;
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
