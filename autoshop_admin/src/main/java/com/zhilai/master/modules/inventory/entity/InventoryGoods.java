package com.zhilai.master.modules.inventory.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

public class InventoryGoods extends DataEntity<InventoryGoods> {
	private static final long serialVersionUID = -4577355887846611799L;
	private String logid;
	private String invengoods_id;
	private String corp_id;
	private String corp_name;
	private String site_id;
	private String site_name;
	private String cabinet_id;
	private String cloumn_id;
	private String layer_num;
	private String box_id;
	private String temper_type;
	private String product_id;
	private String product_name;
	private String bar_code;
	private String inventory_max;
	private int inventory_total;
	private int inventory_num;
	private int inventory_transit;
	private int overdue_num;
	private String state_time;
	private String cur_state;
	private String inventory_state;
	private String warn_num;
	private String overdue_time;
	private String is_overdue;
	private String down_state;
	private String down_time;
	private String create_time;
	private int inventory_recovery;
	
	//虚拟参数
	private double sale_price;
	private double nomarl_price;
	private String product_title;
	private String product_desc;
	private String detail_url;
	private String productType_id;
	private String productType_name;
	private String type;

	@ExcelField(title="最大库存数", sort=75)
	public String getInventory_max() {
		return inventory_max;
	}

	public void setInventory_max(String inventory_max) {
		this.inventory_max = inventory_max;
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getInvengoods_id() {
		return invengoods_id;
	}

	public void setInvengoods_id(String invengoods_id) {
		this.invengoods_id = invengoods_id;
	}
	@ExcelField(title="公司编号", sort=20)
	public String getCorp_id() {
		return corp_id;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	@ExcelField(title="公司名称", sort=25)
	public String getCorp_name() {
		return corp_name;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}
	@ExcelField(title="站点编号", sort=10)
	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	@ExcelField(title="站点简称", sort=15)
	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	@ExcelField(title="柜号", sort=30)
	public String getCabinet_id() {
		return cabinet_id;
	}

	public void setCabinet_id(String cabinet_id) {
		this.cabinet_id = cabinet_id;
	}
	@ExcelField(title="列数", sort=35)
	public String getCloumn_id() {
		return cloumn_id;
	}

	public void setCloumn_id(String cloumn_id) {
		this.cloumn_id = cloumn_id;
	}
	@ExcelField(title="层数", sort=40)
	public String getLayer_num() {
		return layer_num;
	}

	public void setLayer_num(String layer_num) {
		this.layer_num = layer_num;
	}
	@ExcelField(title="货道号", sort=45)
	public String getBox_id() {
		return box_id;
	}

	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}
	@ExcelField(title="温度环境", sort=50, dictType="temper_type")
	public String getTemper_type() {
		return temper_type;
	}

	public void setTemper_type(String temper_type) {
		this.temper_type = temper_type;
	}
	@ExcelField(title="商品编码", sort=55)
	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	@ExcelField(title="商品名称", sort=60)
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	@ExcelField(title="条码", sort=65)
	public String getBar_code() {
		return bar_code;
	}

	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}
	@ExcelField(title="库存总数", sort=85)
	public int getInventory_total() {
		return inventory_total;
	}

	public void setInventory_total(int inventory_total) {
		this.inventory_total = inventory_total;
	}
	@ExcelField(title="可售库存数", sort=90)
	public int getInventory_num() {
		return inventory_num;
	}

	public void setInventory_num(int inventory_num) {
		this.inventory_num = inventory_num;
	}
	@ExcelField(title="在途数量", sort=95)
	public int getInventory_transit() {
		return inventory_transit;
	}

	public void setInventory_transit(int inventory_transit) {
		this.inventory_transit = inventory_transit;
	}
	@ExcelField(title="过期数量", sort=100)
	public int getOverdue_num() {
		return overdue_num;
	}

	public void setOverdue_num(int overdue_num) {
		this.overdue_num = overdue_num;
	}
	@ExcelField(title="状态时间", sort=115)
	public String getState_time() {
		return state_time;
	}

	public void setState_time(String state_time) {
		this.state_time = state_time;
	}
	@ExcelField(title="货道状态", sort=46, dictType="cur_state")
	public String getCur_state() {
		return cur_state;
	}

	public void setCur_state(String cur_state) {
		this.cur_state = cur_state;
	}
	@ExcelField(title="库存状态", sort=110, dictType="inventory_state")
	public String getInventory_state() {
		return inventory_state;
	}

	public void setInventory_state(String inventory_state) {
		this.inventory_state = inventory_state;
	}
	@ExcelField(title="缺货临界值", sort=80)
	public String getWarn_num() {
		return warn_num;
	}

	public void setWarn_num(String warn_num) {
		this.warn_num = warn_num;
	}
	@ExcelField(title="过期时间", sort=70)
	public String getOverdue_time() {
		return overdue_time;
	}

	public void setOverdue_time(String overdue_time) {
		this.overdue_time = overdue_time;
	}
	@ExcelField(title="有过期商品", sort=120, dictType="is_overdue")
	public String getIs_overdue() {
		return is_overdue;
	}

	public void setIs_overdue(String is_overdue) {
		this.is_overdue = is_overdue;
	}

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
	@ExcelField(title="创建时间", sort=125)
	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public double getSale_price() {
		return sale_price;
	}

	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}

	public double getNomarl_price() {
		return nomarl_price;
	}

	public void setNomarl_price(double nomarl_price) {
		this.nomarl_price = nomarl_price;
	}

	public String getProduct_title() {
		return product_title;
	}

	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}

	public String getProduct_desc() {
		return product_desc;
	}

	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}

	public String getDetail_url() {
		return detail_url;
	}

	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}

	public String getProductType_id() {
		return productType_id;
	}

	public void setProductType_id(String productType_id) {
		this.productType_id = productType_id;
	}

	public String getProductType_name() {
		return productType_name;
	}

	public void setProductType_name(String productType_name) {
		this.productType_name = productType_name;
	}
	@ExcelField(title="回收数量", sort=105)
	public int getInventory_recovery() {
		return inventory_recovery;
	}

	public void setInventory_recovery(int inventory_recovery) {
		this.inventory_recovery = inventory_recovery;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
