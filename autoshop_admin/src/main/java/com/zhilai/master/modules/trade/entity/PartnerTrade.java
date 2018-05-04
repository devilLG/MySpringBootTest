/**
 * 
 */
package com.zhilai.master.modules.trade.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * 交易合作方授权信息
 * @author zhengzhiwu
 *
 */
public class PartnerTrade extends DataEntity<PartnerTrade> {
	private String logid;    //日志编号  VARCHAR(36)
	private String trade_code;    //交易代码  VARCHAR(10)
	private String trade_name;
	private String trade_desc;
	private String corp_id;    //合作方编号 VARCHAR(10)
	private String corp_name;    //合作方名称 VARCHAR(40)
	private String create_time; 
	/**
	 * 日志编号 
	 * @return the logid
	 */
	public String getLogid() {
		return logid;
	}
	/**
	 * 日志编号 
	 * @param logid the logid to set
	 */
	public void setLogid(String logid) {
		this.logid = logid;
	}
	/**
	 * 交易代码
	 * @return the trade_code
	 */
	public String getTrade_code() {
		return trade_code;
	}
	/**
	 * 交易代码
	 * @param trade_code the trade_code to set
	 */
	public void setTrade_code(String trade_code) {
		this.trade_code = trade_code;
	}
	/**
	 * 合作方编号
	 * @return the corp_id
	 */
	public String getCorp_id() {
		return corp_id;
	}
	/**
	 * 合作方编号
	 * @param corp_id the corp_id to set
	 */
	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	/**
	 * 合作方名称
	 * @return the corp_name
	 */
	public String getCorp_name() {
		return corp_name;
	}
	/**
	 * 合作方名称
	 * @param corp_name the corp_name to set
	 */
	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
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
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
    
}
