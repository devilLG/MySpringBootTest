package com.zhilai.master.modules.utils.heartbeat;

public class HeartBeat {
	public String trade_code;
	private String trade_name;
	private String site_id;
	private String site_name;
	private String push_id;//推送编号
	private String update_time;
	private String create_time;
	private String issued_key;//下发主键
    private String cur_state;//日志动作类型
	public String getTrade_code() {
		return trade_code;
	}

	public void setTrade_code(String tradeCode) {
		trade_code = tradeCode;
	}

	public String getTrade_name() {
		return trade_name;
	}

	public void setTrade_name(String tradeName) {
		trade_name = tradeName;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String siteId) {
		site_id = siteId;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String siteName) {
		site_name = siteName;
	}

	public String getPush_id() {
		return push_id;
	}

	public void setPush_id(String pushId) {
		push_id = pushId;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String updateTime) {
		update_time = updateTime;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String createTime) {
		create_time = createTime;
	}

	public String getIssued_key() {
		return issued_key;
	}

	public void setIssued_key(String issuedKey) {
		issued_key = issuedKey;
	}

	public String getCur_state() {
		return cur_state;
	}

	public void setCur_state(String curState) {
		cur_state = curState;
	}
	
}
