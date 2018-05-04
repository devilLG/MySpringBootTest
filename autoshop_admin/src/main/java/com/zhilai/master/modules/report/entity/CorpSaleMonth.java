package com.zhilai.master.modules.report.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * 公司销售月报表实体类
 * 
 *
 */
public class CorpSaleMonth extends DataEntity<CorpSaleMonth> {
	private static final long serialVersionUID = 3920320161204360934L;
	private String logid;
	private String corp_id;// 公司编号
	private String corp_name;// 公司名称
	private int order_num;// 订单销售量
	private int wechat_num;// 微信销售订单量
	private int app_num;// app销售订单量
	private int terminal_num;// 终端销售订单量
	private int product_num;// 销售商品总数量
	private int refund_num;// 退款订单数量
	private int abnormal_num;// 异常订单数量
	private double sale_money;// 销售金额
	private double refund_money;// 退款金额
	private String rpt_date;// 报表日期
	private String rpt_day;// 报表日期(交易日期)
	private String rpt_month;// 报表月份
	private String rpt_year;// 报表年份
	private String create_time;// 创建时间

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
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

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	public int getWechat_num() {
		return wechat_num;
	}

	public void setWechat_num(int wechat_num) {
		this.wechat_num = wechat_num;
	}

	public int getApp_num() {
		return app_num;
	}

	public void setApp_num(int app_num) {
		this.app_num = app_num;
	}

	public int getTerminal_num() {
		return terminal_num;
	}

	public void setTerminal_num(int terminal_num) {
		this.terminal_num = terminal_num;
	}

	public int getProduct_num() {
		return product_num;
	}

	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}

	public int getRefund_num() {
		return refund_num;
	}

	public void setRefund_num(int refund_num) {
		this.refund_num = refund_num;
	}

	public int getAbnormal_num() {
		return abnormal_num;
	}

	public void setAbnormal_num(int abnormal_num) {
		this.abnormal_num = abnormal_num;
	}

	public double getSale_money() {
		return sale_money;
	}

	public void setSale_money(double sale_money) {
		this.sale_money = sale_money;
	}

	public double getRefund_money() {
		return refund_money;
	}

	public void setRefund_money(double refund_money) {
		this.refund_money = refund_money;
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

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
