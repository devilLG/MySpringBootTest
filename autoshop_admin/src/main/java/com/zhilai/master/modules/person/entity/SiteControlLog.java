package com.zhilai.master.modules.person.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * @author guowei 人员推送
 * @date 2017-12-7 上午10:23:16
 * 
 */
public class SiteControlLog extends DataEntity<SiteControlLog> {
	private static final long serialVersionUID = 3107247789734490155L;
	private String logid;// 逻辑编号
	private String site_id;// 站点ID引用as_site表的site_id
	private String site_name;// 站点名称
	private String trade_code;// 交易号
	private String trade_name;// 交易名称
	private String issued_key;// 推送主键
	private String cur_state;// 状态  01:心跳通知开始 02:未下发 03:已下发 03:完成下发 04:心跳通知结束
	private String create_time;// 创建时间
	private String frist_attr;
	
	//虚拟字段
	private String corp_id;// 

	public String getLogid() {
		return logid;
	}
	@ExcelField(title="站点编号", sort=10)
	public String getSite_id() {
		return site_id;
	}
	@ExcelField(title="站点名称", sort=20)
	public String getSite_name() {
		return site_name;
	}
	@ExcelField(title="交易号", sort=30)
	public String getTrade_code() {
		return trade_code;
	}
	@ExcelField(title="交易名称", sort=40)
	public String getTrade_name() {
		return trade_name;
	}
	@ExcelField(title="账户编号", sort=50)
	public String getIssued_key() {
		return issued_key;
	}
	@ExcelField(title="状态", sort=60, dictType="siteControlLog_state")
	public String getCur_state() {
		return cur_state;
	}
	@ExcelField(title="创建时间", sort=70)
	public String getCreate_time() {
		return create_time;
	}

	public String getFrist_attr() {
		return frist_attr;
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

	public void setIssued_key(String issued_key) {
		this.issued_key = issued_key;
	}

	public void setCur_state(String cur_state) {
		this.cur_state = cur_state;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public void setFrist_attr(String frist_attr) {
		this.frist_attr = frist_attr;
	}
	public String getCorp_id() {
		return corp_id;
	}
	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}

}
