package com.zhilai.master.modules.site.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * @author guowei 地点控制命令
 * @date 2017-12-7 上午10:01:31
 * 
 */
public class SiteCmd extends DataEntity<SiteCmd> {
	private static final long serialVersionUID = 1080254379586922398L;
	private String logid;// 逻辑编号
	private String cmd_id;// 命令编号
	private String corp_id;// 公司编号
	private String site_id;// 地点编号
	private String site_name;// 地点简称
	private String cmd_val;// 控制对象编号 商品编号|货道编号|站点编号
	private String cmd_obj;// 控制对象 控制对象 01:站点 02:货道 03:商品
	private String cmd_type;// 控制类型 0101:站点停止售卖 0102:站点恢复售卖 0201:货道停用 0202:货道启用
							// 0301:商品停售 0302:商品恢复售卖
	private String down_state;// 下发状态0:未下发 1:已下发 2:已确认
	private String down_time;// 下发更新时间
	private String create_time;// 创建时间

	public String getLogid() {
		return logid;
	}
	@ExcelField(title="命令编号", sort=10)
	public String getCmd_id() {
		return cmd_id;
	}
//	@ExcelField(title="公司编号", sort=20)
	public String getCorp_id() {
		return corp_id;
	}
	@ExcelField(title="站点编号", sort=25)
	public String getSite_id() {
		return site_id;
	}
	@ExcelField(title="站点简称", sort=30)
	public String getSite_name() {
		return site_name;
	}
	@ExcelField(title="控制对象编号", sort=31)
	public String getCmd_val() {
		return cmd_val;
	}
	@ExcelField(title="控制对象", sort=35, dictType="cmd_obj")
	public String getCmd_obj() {
		return cmd_obj;
	}
	@ExcelField(title="控制类型", sort=40, dictType="cmd_type")
	public String getCmd_type() {
		return cmd_type;
	}
	@ExcelField(title="下发状态", sort=45, dictType="down_state")
	public String getDown_state() {
		return down_state;
	}
	@ExcelField(title="下发时间", sort=50)
	public String getDown_time() {
		return down_time;
	}
	@ExcelField(title="创建时间", sort=55)
	public String getCreate_time() {
		return create_time;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public void setCmd_id(String cmd_id) {
		this.cmd_id = cmd_id;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public void setCmd_val(String cmd_val) {
		this.cmd_val = cmd_val;
	}

	public void setCmd_obj(String cmd_obj) {
		this.cmd_obj = cmd_obj;
	}

	public void setCmd_type(String cmd_type) {
		this.cmd_type = cmd_type;
	}

	public void setDown_state(String down_state) {
		this.down_state = down_state;
	}

	public void setDown_time(String down_time) {
		this.down_time = down_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
