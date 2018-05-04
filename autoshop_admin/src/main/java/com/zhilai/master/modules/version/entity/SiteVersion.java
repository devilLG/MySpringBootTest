package com.zhilai.master.modules.version.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * @author guowei 终端版本
 * @date 2017-12-21 下午6:17:18
 * 
 */
public class SiteVersion extends DataEntity<SiteVersion> {
	private static final long serialVersionUID = 115723358076704876L;
	private String logid; // 记录编号
	private String tree_id; // 区域编号
	private String tree_name; // 区域名称
	private String site_id; // 终端编号
	private String site_name; // 终端名称
	private String plat_code; // 平台代码
	private String plat_name; // 平台名称
	private String plat_version; // 平台版本
	private String upgrade_state; // 升级状态[01:准备升级 02:正在升级 03:升级成功 04:升级失败]
	private String upgrade_stime; // 升级开始时间
	private String upgrade_etime; // 升级结束时间
	private String allowUpg_stime; // 允许升级开始时间
	private String allowUpg_etime; // 允许升级结束时间
	private String ugstate_time; // 更新状态时间
	private String create_time; // 创建时间
	private String cur_state; // 状态:01可用 02:不可用
	private String state_time; // 状态时间

	public String getLogid() {
		return logid;
	}
	@ExcelField(title="区域编号", sort=10)
	public String getTree_id() {
		return tree_id;
	}
	@ExcelField(title="区域名称", sort=15)
	public String getTree_name() {
		return tree_name;
	}
	@ExcelField(title="站点编号", sort=20)
	public String getSite_id() {
		return site_id;
	}
	@ExcelField(title="站点名称", sort=25)
	public String getSite_name() {
		return site_name;
	}
	@ExcelField(title="平台代码", sort=30)
	public String getPlat_code() {
		return plat_code;
	}
	@ExcelField(title="平台名称", sort=35)
	public String getPlat_name() {
		return plat_name;
	}
	@ExcelField(title="平台版本", sort=40)
	public String getPlat_version() {
		return plat_version;
	}
	@ExcelField(title="升级状态", sort=45, dictType="upgrade_state")
	public String getUpgrade_state() {
		return upgrade_state;
	}

	public String getUpgrade_stime() {
		return upgrade_stime;
	}

	public String getUpgrade_etime() {
		return upgrade_etime;
	}
	@ExcelField(title="允许开始时间", sort=50)
	public String getAllowUpg_stime() {
		return allowUpg_stime;
	}
	@ExcelField(title="允许结束时间", sort=60)
	public String getAllowUpg_etime() {
		return allowUpg_etime;
	}

	public String getUgstate_time() {
		return ugstate_time;
	}

	public String getCreate_time() {
		return create_time;
	}
	@ExcelField(title="状态", sort=70, dictType="use_state")
	public String getCur_state() {
		return cur_state;
	}

	public String getState_time() {
		return state_time;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public void setTree_id(String tree_id) {
		this.tree_id = tree_id;
	}

	public void setTree_name(String tree_name) {
		this.tree_name = tree_name;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public void setPlat_code(String plat_code) {
		this.plat_code = plat_code;
	}

	public void setPlat_name(String plat_name) {
		this.plat_name = plat_name;
	}

	public void setPlat_version(String plat_version) {
		this.plat_version = plat_version;
	}

	public void setUpgrade_state(String upgrade_state) {
		this.upgrade_state = upgrade_state;
	}

	public void setUpgrade_stime(String upgrade_stime) {
		this.upgrade_stime = upgrade_stime;
	}

	public void setUpgrade_etime(String upgrade_etime) {
		this.upgrade_etime = upgrade_etime;
	}

	public void setAllowUpg_stime(String allowUpg_stime) {
		this.allowUpg_stime = allowUpg_stime;
	}

	public void setAllowUpg_etime(String allowUpg_etime) {
		this.allowUpg_etime = allowUpg_etime;
	}

	public void setUgstate_time(String ugstate_time) {
		this.ugstate_time = ugstate_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public void setCur_state(String cur_state) {
		this.cur_state = cur_state;
	}

	public void setState_time(String state_time) {
		this.state_time = state_time;
	}

}
