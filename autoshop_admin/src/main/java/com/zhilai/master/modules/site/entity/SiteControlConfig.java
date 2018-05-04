package com.zhilai.master.modules.site.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * @author guowei
 * 地点下发控制配置
 * @date 2017-12-7 上午10:23:16
 * 
 */
public class SiteControlConfig extends DataEntity<SiteControlConfig> {
	private static final long serialVersionUID = 3107247789734490155L;
	private String logid;//逻辑编号
	private String site_id;//地点编号引用ns-site表中的site_id
	private String site_name;//地点名称
	private String trade_code;//交易码
	private String trade_name;//交易名is_log
	private String is_log;//是否记录 01:记录 02:不记录
	private String cur_state;//状态 01可用。02禁用
	private String create_time;//创建时间
	private String update_time;//更新时间
	private String oper_man;//操作人
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLogid() {
		return logid;
	}
	public String getSite_id() {
		return site_id;
	}
	public String getSite_name() {
		return site_name;
	}
	
	public String getOper_man() {
		return oper_man;
	}
	public void setOper_man(String oper_man) {
		this.oper_man = oper_man;
	}
	public String getTrade_code() {
		return trade_code;
	}
	public String getTrade_name() {
		return trade_name;
	}
	public String getCreate_time() {
		return create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	public void setTrade_code(String trade_code) {
		this.trade_code = trade_code;
	}
	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getIs_log() {
		return is_log;
	}
	public String getCur_state() {
		return cur_state;
	}
	public void setIs_log(String is_log) {
		this.is_log = is_log;
	}
	public void setCur_state(String cur_state) {
		this.cur_state = cur_state;
	}
		
}
