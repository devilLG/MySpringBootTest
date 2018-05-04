package com.zhilai.master.modules.report.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 商品销售日报实体类
 * @author zhangwei
 *
 */
public class ProductSaleDay extends DataEntity<ProductSaleDay> {
	private static final long serialVersionUID = -5669349086459141056L;
	private String logid;
	private String corp_id;
	private String corp_name;
	private String product_id;
	private String product_name;
	private String bar_code;
	private double sale_money;
	private int sale_num;
	private int repment_num;
	private String rpt_date;
	private String rpt_day;
	private String rpt_week;
	private String rpt_month;
	private String rpt_year;
	private String create_time;
	
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
	public String getRpt_week() {
		return rpt_week;
	}
	public void setRpt_week(String rpt_week) {
		this.rpt_week = rpt_week;
	}
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	@ExcelField(title="商品编号", sort=20)
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	@ExcelField(title="商品名称", sort=30)
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	@ExcelField(title="商品条码", sort=40)
	public String getBar_code() {
		return bar_code;
	}
	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}
	@ExcelField(title="销售金额", sort=50)
	public double getSale_money() {
		return sale_money;
	}
	public void setSale_money(double sale_money) {
		this.sale_money = sale_money;
	}
	@ExcelField(title="销售数量", sort=60)
	public int getSale_num() {
		return sale_num;
	}
	public void setSale_num(int sale_num) {
		this.sale_num = sale_num;
	}
	@ExcelField(title="补货数量", sort=70)
	public int getRepment_num() {
		return repment_num;
	}
	public void setRepment_num(int repment_num) {
		this.repment_num = repment_num;
	}
	@ExcelField(title="报表日期", sort=10)
	public String getRpt_date() {
		return rpt_date;
	}
	
	public void setRpt_date(String rpt_date) {
		this.rpt_date = rpt_date;
	}
	public String getRpt_day() {
		return rpt_day;
	}
	public void setRpt_day(String rpt_day) {
		this.rpt_day = rpt_day;
	}
	public String getRpt_month() {
		return rpt_month;
	}
	public void setRpt_month(String rpt_month) {
		this.rpt_month = rpt_month;
	}
	public String getRpt_year() {
		return rpt_year;
	}
	public void setRpt_year(String rpt_year) {
		this.rpt_year = rpt_year;
	}
	@ExcelField(title="创建日期", sort=80)
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	

}
