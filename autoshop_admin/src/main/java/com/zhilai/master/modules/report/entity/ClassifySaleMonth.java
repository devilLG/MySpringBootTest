package com.zhilai.master.modules.report.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 分类销售月报表实体类
 * @author zhangwei
 *
 */
public class ClassifySaleMonth extends DataEntity<ClassifySaleMonth> {
	private static final long serialVersionUID = -2848516103335230667L;
	private String logid;
	private String corp_id;
	private String corp_name;
	private String classify_id;
	private String classify_name;
	private double sale_money;
	private int sale_num;
	private String rpt_date;
	private String rpt_day;
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
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getClassify_id() {
		return classify_id;
	}
	public void setClassify_id(String classify_id) {
		this.classify_id = classify_id;
	}
	@ExcelField(title="分类名称", sort=20)
	public String getClassify_name() {
		return classify_name;
	}
	public void setClassify_name(String classify_name) {
		this.classify_name = classify_name;
	}
	@ExcelField(title="销售金额", sort=30)
	public double getSale_money() {
		return sale_money;
	}
	public void setSale_money(double sale_money) {
		this.sale_money = sale_money;
	}
	@ExcelField(title="销售数量", sort=40)
	public int getSale_num() {
		return sale_num;
	}
	public void setSale_num(int sale_num) {
		this.sale_num = sale_num;
	}
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
	@ExcelField(title="创建时间", sort=50)
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
