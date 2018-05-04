package com.zhilai.master.modules.log.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * @author guowei 终端日志
 * @date 2017-12-26 下午7:03:44
 * 
 */
public class UseLog extends DataEntity<UseLog> {
	private static final long serialVersionUID = 1080254379586922398L;
	private String logid;
	private String site_id;
	private String site_name;
	private String oper_level;
	private String oper_id;
	private String oper_name;
	private String oper_code;
	private String oper_action;
	private String oper_obj;
	private String oper_old;
	private String oper_new;
	private String oper_ret;
	private String oper_time;
	private String create_time;
	
	private String oper_time_end;
	

	public String getLogid() {
		return logid;
	}
	

	public String getOper_time_end() {
		return oper_time_end;
	}


	public void setOper_time_end(String oper_time_end) {
		this.oper_time_end = oper_time_end;
	}


	public void setLogid(String logid) {
		this.logid = logid;
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

	public String getOper_level() {
		return oper_level;
	}

	public void setOper_level(String oper_level) {
		this.oper_level = oper_level;
	}

	public String getOper_id() {
		return oper_id;
	}

	public void setOper_id(String oper_id) {
		this.oper_id = oper_id;
	}

	public String getOper_name() {
		return oper_name;
	}

	public void setOper_name(String oper_name) {
		this.oper_name = oper_name;
	}

	public String getOper_code() {
		return oper_code;
	}

	public void setOper_code(String oper_code) {
		this.oper_code = oper_code;
	}

	public String getOper_action() {
		return oper_action;
	}

	public void setOper_action(String oper_action) {
		this.oper_action = oper_action;
	}

	public String getOper_obj() {
		return oper_obj;
	}

	public void setOper_obj(String oper_obj) {
		this.oper_obj = oper_obj;
	}

	public String getOper_old() {
		return oper_old;
	}

	public void setOper_old(String oper_old) {
		this.oper_old = oper_old;
	}

	public String getOper_new() {
		return oper_new;
	}

	public void setOper_new(String oper_new) {
		this.oper_new = oper_new;
	}

	public String getOper_ret() {
		return oper_ret;
	}

	public void setOper_ret(String oper_ret) {
		this.oper_ret = oper_ret;
	}

	public String getOper_time() {
		return oper_time;
	}

	public void setOper_time(String oper_time) {
		this.oper_time = oper_time;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
