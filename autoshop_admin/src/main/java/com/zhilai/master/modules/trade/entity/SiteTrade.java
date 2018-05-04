package com.zhilai.master.modules.trade.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * @author guowei 交易地点授权
 * @date 2017-12-26 下午7:03:44
 * 
 */
public class SiteTrade extends DataEntity<SiteTrade> {
	private static final long serialVersionUID = 1080254379586922398L;
	private String logid; // 日志编号 VARCHAR(36)
	private String trade_code; // 交易编号 VARCHAR(10)
	private String trade_name;
	private String trade_desc;
	private String site_id; // 地点编号 VARCHAR(10)
	private String site_name; // 地点名称 VARCHAR(40)
	private String owner_id;
	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
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

	public String getTrade_desc() {
		return trade_desc;
	}

	public void setTrade_desc(String trade_desc) {
		this.trade_desc = trade_desc;
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

}
