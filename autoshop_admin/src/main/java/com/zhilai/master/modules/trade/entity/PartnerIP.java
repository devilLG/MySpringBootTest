/**
 * 
 */
package com.zhilai.master.modules.trade.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * @author zhengzhiwu
 *
 */
public class PartnerIP extends DataEntity<PartnerIP> {
	private static final long serialVersionUID = 1018635490418150751L;
	/**
	 * 日志编号
	 */
	private String 		logid;
	/**
	 * 公司编号
	 */
	private String 		corp_id;
	/**
	 * 公司名称
	 */
	private String 		corp_name;
	/**
	 * 授权账户
	 */
	private String 		auth_name;
	/**
	 * ip地址
	 */
	private String 		addr_ip;
	/**
	 * mac地址
	 */
	private String 		addr_mac;
	/**
	 * 授权状态
	 */
	private String 		auth_state;
	/**
	 * @return the logid
	 */
	public String getLogid() {
		return logid;
	}
	/**
	 * @param logid the logid to set
	 */
	public void setLogid(String logid) {
		this.logid = logid;
	}
	/**
	 * @return the corp_id
	 */
	public String getCorp_id() {
		return corp_id;
	}
	/**
	 * @param corp_id the corp_id to set
	 */
	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	/**
	 * @return the corp_name
	 */
	public String getCorp_name() {
		return corp_name;
	}
	/**
	 * @param corp_name the corp_name to set
	 */
	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}
	/**
	 * @return the auth_name
	 */
	public String getAuth_name() {
		return auth_name;
	}
	/**
	 * @param auth_name the auth_name to set
	 */
	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}
	/**
	 * @return the addr_ip
	 */
	public String getAddr_ip() {
		return addr_ip;
	}
	/**
	 * @param addr_ip the addr_ip to set
	 */
	public void setAddr_ip(String addr_ip) {
		this.addr_ip = addr_ip;
	}
	/**
	 * @return the addr_mac
	 */
	public String getAddr_mac() {
		return addr_mac;
	}
	/**
	 * @param addr_mac the addr_mac to set
	 */
	public void setAddr_mac(String addr_mac) {
		this.addr_mac = addr_mac;
	}
	/**
	 * @return the auth_state
	 */
	public String getAuth_state() {
		return auth_state;
	}
	/**
	 * @param auth_state the auth_state to set
	 */
	public void setAuth_state(String auth_state) {
		this.auth_state = auth_state;
	}
	
}
