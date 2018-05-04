package com.zhilai.master.modules.trade.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * @author guowei 交易定义
 * @date 2017-12-26 下午7:03:44
 * 
 */
public class TradeDefine extends DataEntity<TradeDefine> {
	private static final long serialVersionUID = 1080254379586922398L;
	private String logid;// 记录编号
	private String id;// 编号
	private String plat_code;// 平台代码
	private String trade_code;// 交易码
	private String trade_name;// 交易名称
	private String trade_desc; // 交易描述
	private String trade_state; // 交易状态
	private String trade_log;// 交易日志
	private String pkg_log; // 报文日志
	private String use_type;// 使用方式

	public String getLogid() {
		return logid;
	}

	public String getId() {
		return id;
	}

	public String getPlat_code() {
		return plat_code;
	}

	public String getTrade_code() {
		return trade_code;
	}

	public String getTrade_name() {
		return trade_name;
	}

	public String getTrade_desc() {
		return trade_desc;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public String getTrade_log() {
		return trade_log;
	}

	public String getPkg_log() {
		return pkg_log;
	}

	public String getUse_type() {
		return use_type;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPlat_code(String plat_code) {
		this.plat_code = plat_code;
	}

	public void setTrade_code(String trade_code) {
		this.trade_code = trade_code;
	}

	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}

	public void setTrade_desc(String trade_desc) {
		this.trade_desc = trade_desc;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public void setTrade_log(String trade_log) {
		this.trade_log = trade_log;
	}

	public void setPkg_log(String pkg_log) {
		this.pkg_log = pkg_log;
	}

	public void setUse_type(String use_type) {
		this.use_type = use_type;
	}

}
