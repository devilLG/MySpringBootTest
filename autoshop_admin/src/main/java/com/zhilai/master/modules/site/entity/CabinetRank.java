package com.zhilai.master.modules.site.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * @author guowei
 * 柜子排列信息   记录柜子排列信息
 * @date 2017-12-7 上午10:23:16
 * 
 */
public class CabinetRank extends DataEntity<CabinetRank> {
	private static final long serialVersionUID = 3107247789734490155L;
	private String logid;//逻辑编号
	private String corp_id;//公司编号
	private String type_id;//类型编号
	private String type_name;//类型简称
	private String fullname;//类型全称
	private String arry_location;//柜子排列位置(从左往右)
	private String box_num;//箱子数大小
	private String create_time;//创建时间
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLogid() {
		return logid;
	}
	public String getCorp_id() {
		return corp_id;
	}
	public String getType_id() {
		return type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public String getFullname() {
		return fullname;
	}
	public String getArry_location() {
		return arry_location;
	}
	public String getBox_num() {
		return box_num;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public void setArry_location(String arry_location) {
		this.arry_location = arry_location;
	}
	public void setBox_num(String box_num) {
		this.box_num = box_num;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
}
