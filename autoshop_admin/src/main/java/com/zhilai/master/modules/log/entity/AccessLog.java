package com.zhilai.master.modules.log.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * @author guowei 访问日志
 * @date 2017-12-26 下午7:03:44
 * 
 */
public class AccessLog extends DataEntity<AccessLog> {
	private static final long serialVersionUID = 1080254379586922398L;
	private String log_id;// 日志编号 varchar(36)
	private String access_time;// VARCHAR(20)
	private String auth_name;// VARCHAR(20)
	private String auth_id;// VARCHAR(64)
	private String ip_addr;// VARCHAR(20)
	private String hostname;// varchar(36)
	private String state;// VARCHAR(10)
	private String protocol;// VARCHAR(1)

	private String access_time_end;// VARCHAR(20)

	public String getLog_id() {
		return log_id;
	}

	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}

	public String getAccess_time() {
		return access_time;
	}

	public void setAccess_time(String access_time) {
		this.access_time = access_time;
	}

	public String getAuth_name() {
		return auth_name;
	}

	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}

	public String getAuth_id() {
		return auth_id;
	}

	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}

	public String getIp_addr() {
		return ip_addr;
	}

	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getAccess_time_end() {
		return access_time_end;
	}

	public void setAccess_time_end(String access_time_end) {
		this.access_time_end = access_time_end;
	}
}
