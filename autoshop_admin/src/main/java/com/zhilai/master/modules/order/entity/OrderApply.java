package com.zhilai.master.modules.order.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 
 * @author Administrator
 * 
 */
public class OrderApply extends DataEntity<OrderApply> {
	private static final long serialVersionUID = 4712427131723249666L;
	private String logid;
	private String order_id;
	private String torder_id;
	private String order_type;
	private String corp_id;
	private String corp_name;
	private String site_id;
	private String site_name;
	private String login_id;
	private String login_name;
	private String sale_channel;
	private int product_num;
	private double sale_total;
	private String pay_type;
	private String pay_state;
	private String pay_overtime;
	private String fetch_overTime;
	private String cur_state;
	private String recovery_type;
	private String pass_word;
	private String encryption_type;
	private String slat;
	private String state_time;
	private String down_state;
	private String down_time;
	private String create_time;
	private String abnormalType;
	private String OutTradeNo;
	
	//虚拟字段
	private String beginTime;
	private String endTime;
	private String lock_type;
	private String fetch_type;
	private String abnormal_type;

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}
	@ExcelField(title="订单编号", sort=10)
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

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	@ExcelField(title="客户名称", sort=25)
	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	@ExcelField(title="下单渠道", sort=35, dictType="sale_channel")
	public String getSale_channel() {
		return sale_channel;
	}

	public void setSale_channel(String sale_channel) {
		this.sale_channel = sale_channel;
	}

	public int getProduct_num() {
		return product_num;
	}

	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}

	@ExcelField(title="支付金额（元）", sort=50)
	public double getSale_total() {
		return sale_total;
	}
	public void setSale_total(double sale_total) {
		this.sale_total = sale_total;
	}
	@ExcelField(title="支付方式", sort=40, dictType="pay_type")
	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	@ExcelField(title="支付状态", sort=45, dictType="pay_state")
	public String getPay_state() {
		return pay_state;
	}

	public void setPay_state(String pay_state) {
		this.pay_state = pay_state;
	}
	@ExcelField(title="支付超期", sort=55)
	public String getPay_overtime() {
		return pay_overtime;
	}

	public void setPay_overtime(String pay_overtime) {
		this.pay_overtime = pay_overtime;
	}
	@ExcelField(title="取件超期", sort=80)
	public String getFetch_overTime() {
		return fetch_overTime;
	}

	public void setFetch_overTime(String fetch_overTime) {
		this.fetch_overTime = fetch_overTime;
	}
	@ExcelField(title="状态", sort=60, dictType="order_apply_state")
	public String getCur_state() {
		return cur_state;
	}

	public void setCur_state(String cur_state) {
		this.cur_state = cur_state;
	}

	@ExcelField(title="回收状态", sort=70, dictType="recovery_type")
	public String getRecovery_type() {
		return recovery_type;
	}
	public void setRecovery_type(String recovery_type) {
		this.recovery_type = recovery_type;
	}
	@ExcelField(title="取件密码", sort=75)
	public String getPass_word() {
		return pass_word;
	}

	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}

	public String getEncryption_type() {
		return encryption_type;
	}

	public void setEncryption_type(String encryption_type) {
		this.encryption_type = encryption_type;
	}

	public String getSlat() {
		return slat;
	}

	public void setSlat(String slat) {
		this.slat = slat;
	}

	@ExcelField(title="状态时间", sort=65)
	public String getState_time() {
		return state_time;
	}
	public void setState_time(String state_time) {
		this.state_time = state_time;
	}
	@ExcelField(title="下发状态", sort=85, dictType="down_state")
	public String getDown_state() {
		return down_state;
	}

	public void setDown_state(String down_state) {
		this.down_state = down_state;
	}
	@ExcelField(title="下发时间", sort=90)
	public String getDown_time() {
		return down_time;
	}

	public void setDown_time(String down_time) {
		this.down_time = down_time;
	}
	@ExcelField(title="下单时间", sort=30)
	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	@ExcelField(title="订单类型", sort=56, dictType="order_type")
	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
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

	public String getLock_type() {
		return lock_type;
	}

	public void setLock_type(String lock_type) {
		this.lock_type = lock_type;
	}

	public String getFetch_type() {
		return fetch_type;
	}

	public void setFetch_type(String fetch_type) {
		this.fetch_type = fetch_type;
	}

	public String getAbnormal_type() {
		return abnormal_type;
	}

	public void setAbnormal_type(String abnormal_type) {
		this.abnormal_type = abnormal_type;
	}
	@ExcelField(title="交易流水号", sort=51)
	public String getOutTradeNo() {
		if(OutTradeNo == null){
			return "";
		}else{
			return OutTradeNo;
		}
	}

	public void setOutTradeNo(String outTradeNo) {
		OutTradeNo = outTradeNo;
	}

	@ExcelField(title="异常类型", sort=57, dictType="order_abnormal_type")
	public String getAbnormalType() {
		if(abnormalType == null){
			return "";
		}else{
			return abnormalType;
		}
	}

	public void setAbnormalType(String abnormalType) {
		this.abnormalType = abnormalType;
	}

}
