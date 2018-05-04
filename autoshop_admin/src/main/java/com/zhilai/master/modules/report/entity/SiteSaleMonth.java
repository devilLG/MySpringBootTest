package com.zhilai.master.modules.report.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 站点销售月报表实体类
 * @author zhangwei
 *
 */
public class SiteSaleMonth extends DataEntity<SiteSaleMonth> {
	private static final long serialVersionUID = 4587735083298058110L;
	private String logid;
	private String corp_id;
	private String corp_name;
	private String site_id;
	private String site_name;
	private double sale_money;//销售金额
	private int sale_num;//销售数量
	private String repment_num;//补货数量
	private String rement_time;//补货次数
	private String lack_num;//缺货数量(报表生成时间缺货情况)
	private String rpt_date;//报表日期
	private String rpt_month;//报表月份
	private String rpt_year;//报表年份
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
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	@ExcelField(title="站点编号", sort=20)
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	@ExcelField(title="站点简称", sort=30)
	public String getSite_name() {
		return site_name;
	}
	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	@ExcelField(title="销售金额", sort=40)
	public double getSale_money() {
		return sale_money;
	}
	public void setSale_money(double sale_money) {
		this.sale_money = sale_money;
	}
	@ExcelField(title="销售数量", sort=50)
	public int getSale_num() {
		return sale_num;
	}
	public void setSale_num(int sale_num) {
		this.sale_num = sale_num;
	}
	@ExcelField(title="补货数量", sort=60)
	public String getRepment_num() {
		return repment_num;
	}
	public void setRepment_num(String repment_num) {
		this.repment_num = repment_num;
	}
	@ExcelField(title="补货次数", sort=70)
	public String getRement_time() {
		return rement_time;
	}
	public void setRement_time(String rement_time) {
		this.rement_time = rement_time;
	}
	@ExcelField(title="缺货数量", sort=80)
	public String getLack_num() {
		return lack_num;
	}
	public void setLack_num(String lack_num) {
		this.lack_num = lack_num;
	}
	public String getRpt_date() {
		return rpt_date;
	}
	public void setRpt_date(String rpt_date) {
		this.rpt_date = rpt_date;
	}
	@ExcelField(title="报表月份", sort=10)
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
	@ExcelField(title="创建时间", sort=90)
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	

}
