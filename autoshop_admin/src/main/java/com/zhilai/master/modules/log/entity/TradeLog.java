package com.zhilai.master.modules.log.entity;


import com.zhilai.master.common.persistence.DataEntity;

/**
 * @author guowei 性能日志
 * @date 2017-12-26 下午7:03:44
 * 
 */
public class TradeLog extends DataEntity<TradeLog> {
	private static final long serialVersionUID = 1080254379586922398L;
	private String log_id;// 日志编号 varchar(36)
	private String auth_name;// 授权用户 VARCHAR(20)
	private String plat_code;// 平台代码 VARCHAR(10)
	private String trade_code;// 交易代码 VARCHAR(10)
	private String trade_name;// 交易简称 VARCHAR(30)
	private String begin_time;// 开始时间 VARCHAR(24)
	private String end_time;// 结束时间 VARCHAR(24)
	private String ret_code;// 返回代码 VARCHAR(10)
	private String exec_time;// 执行时间 FLOAT
	private String protocol;// 协议 VARCHAR(1)
    
	private String site_id;
	private String site_name;
	private String cur_state;
	private String state;
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getAuth_name() {
		return auth_name;
	}
	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}
	public String getPlat_code() {
		return plat_code;
	}
	public void setPlat_code(String plat_code) {
		this.plat_code = plat_code;
	}
	public String getTrade_code() {
		return trade_code;
	}
	public void setTrade_code(String trade_code) {
		this.trade_code = trade_code;
	}
	public String getTrade_name() {
		return trade_name;
	}
	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}
	public String getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getRet_code() {
		return ret_code;
	}
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	public String getExec_time() {
		return exec_time;
	}
	public void setExec_time(String exec_time) {
		this.exec_time = exec_time;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	public String getSite_name() {
		return site_name;
	}
	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	public String getCur_state() {
		return cur_state;
	}
	public void setCur_state(String cur_state) {
		this.cur_state = cur_state;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
