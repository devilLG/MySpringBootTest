package com.zhilai.master.modules.version.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * @author guowei 平台版本
 * @date 2017-12-21 下午6:17:18
 * 
 */
public class PlatVersion extends DataEntity<PlatVersion> {
	private static final long serialVersionUID = 115723358076704876L;
	private String logid; // 记录编号
	private String plat_code; // 平台代码
	private String plat_name; // 平台名称
	private String plat_version; // 平台版本
	private String down_url; // 下载地址
	private String upgrade_log; // 升级日志信息:01:开启 02:更新
	private Integer upgrade_id; // 记录本次升级到最新版本
	private String upgrade_install; // 是否强制安装
	private String valid_stime; // 版本有效开始时间
	private String valid_etime; // 有效结束时间
	private String cur_state; // 版本状态
	private String refrest_time; // 更新时间
	private String create_time; // 创建时间
	private String doc_name; // 升级文件名称
	private String doc_size; // 升级文件容量

	public String getLogid() {
		return logid;
	}
	@ExcelField(title="平台代码", sort=10)
	public String getPlat_code() {
		return plat_code;
	}
	@ExcelField(title="平台名称", sort=20)
	public String getPlat_name() {
		return plat_name;
	}
	@ExcelField(title="平台版本", sort=30)
	public String getPlat_version() {
		return plat_version;
	}
	@ExcelField(title="下载地址", sort=40)
	public String getDown_url() {
		return down_url;
	}
	@ExcelField(title="开启日志", sort=42, dictType="is_log")
	public String getUpgrade_log() {
		return upgrade_log;
	}
	@ExcelField(title="升级标识", sort=43)
	public Integer getUpgrade_id() {
		return upgrade_id;
	}
	@ExcelField(title="强制安装", sort=50, dictType="upgrade_install")
	public String getUpgrade_install() {
		return upgrade_install;
	}
	@ExcelField(title="有效开始时间", sort=70)
	public String getValid_stime() {
		return valid_stime;
	}
	@ExcelField(title="有效结束时间", sort=80)
	public String getValid_etime() {
		return valid_etime;
	}
	@ExcelField(title="版本状态", sort=60, dictType="use_state")
	public String getCur_state() {
		return cur_state;
	}
	@ExcelField(title="刷新时间", sort=85)
	public String getRefrest_time() {
		return refrest_time;
	}
	@ExcelField(title="创建时间", sort=90)
	public String getCreate_time() {
		return create_time;
	}
	@ExcelField(title="升级文件名称", sort=92)
	public String getDoc_name() {
		return doc_name;
	}
	@ExcelField(title="文件大小", sort=93)
	public String getDoc_size() {
		return doc_size;
	}

	public void setLogid(String logid) {
		this.logid = logid;
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

	public void setDown_url(String down_url) {
		this.down_url = down_url;
	}

	public void setUpgrade_log(String upgrade_log) {
		this.upgrade_log = upgrade_log;
	}

	public void setUpgrade_id(Integer upgrade_id) {
		this.upgrade_id = upgrade_id;
	}

	public void setUpgrade_install(String upgrade_install) {
		this.upgrade_install = upgrade_install;
	}

	public void setValid_stime(String valid_stime) {
		this.valid_stime = valid_stime;
	}

	public void setValid_etime(String valid_etime) {
		this.valid_etime = valid_etime;
	}

	public void setCur_state(String cur_state) {
		this.cur_state = cur_state;
	}

	public void setRefrest_time(String refrest_time) {
		this.refrest_time = refrest_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public void setDoc_size(String doc_size) {
		this.doc_size = doc_size;
	}

}
