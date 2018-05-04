package com.zhilai.master.modules.version.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * @author guowei 升级日志
 * @date 2017-12-21 下午6:17:18
 * 
 */
public class UgradeLog extends DataEntity<UgradeLog> {
	private static final long serialVersionUID = 115723358076704876L;
	private String logid; // 记录编号
	private String tree_id; // 区域编号
	private String tree_name; // 区域名称
	private String site_id; // 终端编号
	private String site_name; // 终端名称
	private String plat_code; // 平台代码
	private String plat_name; // 平台名称
	private String plat_version; // 平台版本
	private String newp_version; // 需要升级平台版本
	private String action_type; // 终端日志记录[01:准备升级 02:正在升级 03:升级成功 04:升级失败05:文件下载失败
								// 06:终端进程无法结束 07:终端进程无法启动 08:恢复为原版本]
	private String create_time; // 创建时间
	private String ret_code; // 异常代码
	

	public String getLogid() {
		return logid;
	}
	@ExcelField(title="区域编号", sort=10)
	public String getTree_id() {
		return tree_id;
	}
	@ExcelField(title="区域名称", sort=20)
	public String getTree_name() {
		return tree_name;
	}
	@ExcelField(title="站点编号", sort=30)
	public String getSite_id() {
		return site_id;
	}
	@ExcelField(title="站点名称", sort=40)
	public String getSite_name() {
		return site_name;
	}
	@ExcelField(title="平台代码", sort=50)
	public String getPlat_code() {
		return plat_code;
	}
	@ExcelField(title="平台名称", sort=60)
	public String getPlat_name() {
		return plat_name;
	}
	@ExcelField(title="平台版本", sort=70)
	public String getPlat_version() {
		return plat_version;
	}
	@ExcelField(title="升级版本", sort=80)
	public String getNewp_version() {
		return newp_version;
	}
	@ExcelField(title="升级状态", sort=90)
	public String getAction_type() {
		return action_type;
	}
	@ExcelField(title="创建时间", sort=91)
	public String getCreate_time() {
		return create_time;
	}
	@ExcelField(title="异常代码", sort=95)
	public String getRet_code() {
		return ret_code;
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

	public void setNewp_version(String newp_version) {
		this.newp_version = newp_version;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}

}
