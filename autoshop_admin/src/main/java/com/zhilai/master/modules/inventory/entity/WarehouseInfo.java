package com.zhilai.master.modules.inventory.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

public class WarehouseInfo extends DataEntity<WarehouseInfo> {
	private static final long serialVersionUID = -6515427887402206068L;
	private String logid;
	private String warehouse_id;
	private String corp_id;
	private String corp_name;
	private String warehouse_name;
	private String warehouse_code;
	private String longitude;
	private String latitude;
	private String address;
	private int classify_total;
	private int inventory_total;
	private int inventory_num;
	private int inventory_transit;
	private int inventory_renum;
	private int overdue_num;
	private String create_time;

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}
	@ExcelField(title="仓库编号", sort=20)
	public String getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(String warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	@ExcelField(title="公司编号", sort=10)
	public String getCorp_id() {
		return corp_id;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	@ExcelField(title="公司名称", sort=15)
	public String getCorp_name() {
		return corp_name;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}
	@ExcelField(title="仓库名称", sort=25)
	public String getWarehouse_name() {
		return warehouse_name;
	}

	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}
	@ExcelField(title="仓库代码", sort=30)
	public String getWarehouse_code() {
		return warehouse_code;
	}

	public void setWarehouse_code(String warehouse_code) {
		this.warehouse_code = warehouse_code;
	}
	@ExcelField(title="仓库经度", sort=35)
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@ExcelField(title="仓库维度", sort=40)
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@ExcelField(title="仓库详细地址", sort=45)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@ExcelField(title="商品种类", sort=50)
	public int getClassify_total() {
		return classify_total;
	}

	public void setClassify_total(int classify_total) {
		this.classify_total = classify_total;
	}
	@ExcelField(title="库存总数", sort=55)
	public int getInventory_total() {
		return inventory_total;
	}

	public void setInventory_total(int inventory_total) {
		this.inventory_total = inventory_total;
	}
	@ExcelField(title="正常库存数", sort=60)
	public int getInventory_num() {
		return inventory_num;
	}

	public void setInventory_num(int inventory_num) {
		this.inventory_num = inventory_num;
	}
	@ExcelField(title="在途库存数", sort=65)
	public int getInventory_transit() {
		return inventory_transit;
	}

	public void setInventory_transit(int inventory_transit) {
		this.inventory_transit = inventory_transit;
	}
	@ExcelField(title="回收库存数", sort=70)
	public int getInventory_renum() {
		return inventory_renum;
	}

	public void setInventory_renum(int inventory_renum) {
		this.inventory_renum = inventory_renum;
	}
	@ExcelField(title="过期库存数", sort=75)
	public int getOverdue_num() {
		return overdue_num;
	}

	public void setOverdue_num(int overdue_num) {
		this.overdue_num = overdue_num;
	}
	@ExcelField(title="创建时间", sort=80)
	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
