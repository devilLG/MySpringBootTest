package com.zhilai.master.modules.inventory.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

public class InventoryProduct extends DataEntity<InventoryProduct> {
	private static final long serialVersionUID = -5565943772222210752L;
	private String logid;
	private String wproduct_id;
	private String corp_id;
	private String corp_name;
	private String site_id;
	private String site_name;
	private String warehouse_id;
	private String warehouse_name;
	private String warehouse_code;
	private String product_id;
	private String product_name;
	private String productType_id;
	private String productType_name;
	private String bar_code;
	private int inventory_total;
	private int inventory_num;
	private int inventory_transit;
	private int inventory_renum;
	private int overdue_num;
	private String create_time;
	
	//虚拟字段
	private String brand_name;
	private String normal_price;
	private String product_title;	//商品标题
	private String detail_url;		//商品介绍信息url地址
	private String product_model;	//商品型号
	private String temper_type;	    //温度环境
	private String type;	

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getWproduct_id() {
		return wproduct_id;
	}

	public void setWproduct_id(String wproduct_id) {
		this.wproduct_id = wproduct_id;
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
	@ExcelField(title="仓库编号", sort=30)
	public String getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(String warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	@ExcelField(title="仓库名称", sort=35)
	public String getWarehouse_name() {
		return warehouse_name;
	}

	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}
	@ExcelField(title="仓库代码", sort=40)
	public String getWarehouse_code() {
		return warehouse_code;
	}

	public void setWarehouse_code(String warehouse_code) {
		this.warehouse_code = warehouse_code;
	}
	@ExcelField(title="商品编码", sort=45)
	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	@ExcelField(title="商品名称", sort=50)
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProductType_id() {
		return productType_id;
	}

	public void setProductType_id(String productType_id) {
		this.productType_id = productType_id;
	}
	@ExcelField(title="商品分类", sort=60)
	public String getProductType_name() {
		return productType_name;
	}

	public void setProductType_name(String productType_name) {
		this.productType_name = productType_name;
	}
	@ExcelField(title="商品条码", sort=55)
	public String getBar_code() {
		return bar_code;
	}

	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}
	@ExcelField(title="库存总数", sort=65)
	public int getInventory_total() {
		return inventory_total;
	}

	public void setInventory_total(int inventory_total) {
		this.inventory_total = inventory_total;
	}
	@ExcelField(title="正常库存数", sort=70)
	public int getInventory_num() {
		return inventory_num;
	}

	public void setInventory_num(int inventory_num) {
		this.inventory_num = inventory_num;
	}
	@ExcelField(title="在途库存数", sort=75)
	public int getInventory_transit() {
		return inventory_transit;
	}

	public void setInventory_transit(int inventory_transit) {
		this.inventory_transit = inventory_transit;
	}
	@ExcelField(title="回收库存数", sort=80)
	public int getInventory_renum() {
		return inventory_renum;
	}

	public void setInventory_renum(int inventory_renum) {
		this.inventory_renum = inventory_renum;
	}
	@ExcelField(title="过期库存数", sort=85)
	public int getOverdue_num() {
		return overdue_num;
	}

	public void setOverdue_num(int overdue_num) {
		this.overdue_num = overdue_num;
	}
	@ExcelField(title="创建时间", sort=90)
	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
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

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getNormal_price() {
		return normal_price;
	}

	public void setNormal_price(String normal_price) {
		this.normal_price = normal_price;
	}

	public String getProduct_title() {
		return product_title;
	}

	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}

	public String getDetail_url() {
		return detail_url;
	}

	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}

	public String getProduct_model() {
		return product_model;
	}

	public void setProduct_model(String product_model) {
		this.product_model = product_model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTemper_type() {
		return temper_type;
	}

	public void setTemper_type(String temper_type) {
		this.temper_type = temper_type;
	}

}
