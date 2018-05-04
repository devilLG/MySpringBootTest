package com.zhilai.master.modules.pay.entity;	import com.zhilai.master.common.persistence.DataEntity;import com.zhilai.master.common.utils.excel.annotation.ExcelField;/** *  *Title:PayManageInfo *Description:管理台账单实体类 *@author ChengJiawei *@date 2018年4月23日 下午2:00:38 * */public class PayManageInfo extends DataEntity<PayManageInfo> {		private static final long serialVersionUID = 1L;		private String logid;	//	公司编号	private String trade_day;	//	对账交易日期(按天进行对账)	private String corp_id;	//	公司编号	private String corp_name;	//	公司名称	private String order_id;	//	订单编号 *	private String pay_channel;	//	支付方式 1:微信 2:支付宝	private String trade_number;	//	交易流水号	private String trade_time;	//	交易时间	private double trade_sum;	//	交易总金额 *	private String currency;	//	币种(默认RMB) 01:人民币 02:美元 *	private String login_id;	//	客户编号	private String login_name;	//	客户名称	private String pay_state;	//	支付状态00等待支付01支付成功02支付失效03部分退款04全部退款05支付结算完成 *	private String cur_state;	//	对账状态 1:等待对账 2:完成对账 3:缺账 4:账赢 5:账亏 	private String state_time;	//	状态时间	private String create_time;	//	创建时间	public String getLogid() {		return logid;	}	public void setLogid(String logid) {		this.logid = logid;	}	@ExcelField(title="对账日期", sort=1)	public String getTrade_day() {		return trade_day;	}	public void setTrade_day(String trade_day) {		this.trade_day = trade_day;	}	public String getCorp_id() {		return corp_id;	}	public void setCorp_id(String corp_id) {		this.corp_id = corp_id;	}	@ExcelField(title="公司名称", sort=2)	public String getCorp_name() {		return corp_name;	}	public void setCorp_name(String corp_name) {		this.corp_name = corp_name;	}	public String getOrder_id() {		return order_id;	}	public void setOrder_id(String order_id) {		this.order_id = order_id;	}	@ExcelField(title="付款方式", sort=4,dictType="pay_channel")	public String getPay_channel() {		return pay_channel;	}	public void setPay_channel(String pay_channel) {		this.pay_channel = pay_channel;	}	@ExcelField(title="交易流水号", sort=5)	public String getTrade_number() {		return trade_number;	}	public void setTrade_number(String trade_number) {		this.trade_number = trade_number;	}	@ExcelField(title="交易时间", sort=6)	public String getTrade_time() {		return trade_time;	}	public void setTrade_time(String trade_time) {		this.trade_time = trade_time;	}	@ExcelField(title="交易总金额", sort=7)	public double getTrade_sum() {		return trade_sum;	}	public void setTrade_sum(double trade_sum) {		this.trade_sum = trade_sum;	}	@ExcelField(title="币种", sort=8,dictType="currency")	public String getCurrency() {		return currency;	}	public void setCurrency(String currency) {		this.currency = currency;	}	public String getLogin_id() {		return login_id;	}	public void setLogin_id(String login_id) {		this.login_id = login_id;	}	@ExcelField(title="客户名称", sort=3)	public String getLogin_name() {		return login_name;	}	public void setLogin_name(String login_name) {		this.login_name = login_name;	}	@ExcelField(title="支付状态", sort=9,dictType="pay_state")	public String getPay_state() {		return pay_state;	}	public void setPay_state(String pay_state) {		this.pay_state = pay_state;	}	@ExcelField(title="对账状态", sort=10,dictType="payment_state")	public String getCur_state() {		return cur_state;	}	public void setCur_state(String cur_state) {		this.cur_state = cur_state;	}	@ExcelField(title="状态时间", sort=11)	public String getState_time() {		return state_time;	}	public void setState_time(String state_time) {		this.state_time = state_time;	}	@ExcelField(title="创建时间", sort=12)	public String getCreate_time() {		return create_time;	}	public void setCreate_time(String create_time) {		this.create_time = create_time;	}	}