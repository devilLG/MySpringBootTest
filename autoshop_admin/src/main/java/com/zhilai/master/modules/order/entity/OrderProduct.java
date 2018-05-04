package com.zhilai.master.modules.order.entity;

import java.util.List;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.modules.inventory.entity.InventoryGoods;

public class OrderProduct extends DataEntity<OrderProduct> {
	private static final long serialVersionUID = 1101208892499973234L;
	private String logid;
	private String prodetail_id;
	private String corp_id;
	private String corp_name;
	private String order_id;
	private String torder_id;
	private String product_id;
	private String product_name;
	private String productType_id;
	private String productType_name;
	private String brand_id;
	private String brand_name;
	private String bar_code;
	private double normal_price;
	private double sale_price;
	private int sale_num;
	private int alrecovery_num;
	private double sale_total;
	private String create_time;

	//虚拟字段
	private String box_id;
	private List<InventoryGoods> inventoryGoodsList;
	
	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
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

	public String getCorp_name() {
		return corp_name;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}

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

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getBar_code() {
		return bar_code;
	}

	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}

	public double getNormal_price() {
		return normal_price;
	}

	public void setNormal_price(double normal_price) {
		this.normal_price = normal_price;
	}

	public double getSale_price() {
		return sale_price;
	}

	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}

	public int getSale_num() {
		return sale_num;
	}

	public void setSale_num(int sale_num) {
		this.sale_num = sale_num;
	}

	public int getAlrecovery_num() {
		return alrecovery_num;
	}

	public void setAlrecovery_num(int alrecovery_num) {
		this.alrecovery_num = alrecovery_num;
	}

	public double getSale_total() {
		return sale_total;
	}

	public void setSale_total(double sale_total) {
		this.sale_total = sale_total;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getBox_id() {
		return box_id;
	}

	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}

	public List<InventoryGoods> getInventoryGoodsList() {
		return inventoryGoodsList;
	}

	public void setInventoryGoodsList(List<InventoryGoods> inventoryGoodsList) {
		this.inventoryGoodsList = inventoryGoodsList;
	}
}
