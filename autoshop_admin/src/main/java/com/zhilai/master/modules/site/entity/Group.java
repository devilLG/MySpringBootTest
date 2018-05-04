package com.zhilai.master.modules.site.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * 记录设备定义的分组	dc_group
 * @author add
 *
 */
public class Group extends DataEntity<Group> {
	private String grpId;// varchar(10) NOT NULL DEFAULT '1000' COMMENT '分组编号',
	private String grpName;// varchar(30) NOT NULL DEFAULT 'default group' COMMENT '分组名称',
	private String ename;// varchar(30) DEFAULT NULL COMMENT '英文名称',
	private String grpType;// varchar(2) DEFAULT NULL COMMENT '组别类型：00站点组别（区域） 01设备组别 02公司组别',
	private String description;// varchar(100) DEFAULT NULL COMMENT '组描述'
	private String updateTime;// timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	
	public String getGrpType() {
		return grpType;
	}
	public void setGrpType(String grpType) {
		this.grpType = grpType;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	public String getGrpName() {
		return grpName;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
