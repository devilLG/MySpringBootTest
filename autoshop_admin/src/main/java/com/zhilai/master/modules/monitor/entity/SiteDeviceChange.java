package com.zhilai.master.modules.monitor.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 
 *Title:SiteDeviceChange
 *Description:站点设备状态记录实体类
 *@author ChengJiawei
 *@date 2018年4月12日 下午4:41:20
 *
 */
public class SiteDeviceChange extends DataEntity<SiteDeviceChange>{
	private static final long serialVersionUID = -4326020060237927661L;
	
	private String logid; //记录编号
	private String sitedeviceId; //站点配件编号
	private String siteId; //站点编号
	private String siteName; //站点名称
	private String corpId; //公司编号
	private String corpName; //公司名称
	private String deviceCode; //设备编码
	private String deviceName; //设备名称
	private String operAction; //操作动作 01:报警 02:故障 03:已修复 04:等待检查 05:检查正常
	private String operTime; //操作时间
	private String operId; //操作人编号
	private String operName; //操作人名称
	private String cont; //描述
	private String createTime; //创建时间

	//虚拟字段
	private String createTimeStart; //创建开始时间
	private String createTimeEnd; //创建结束时间
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getSitedeviceId() {
		return sitedeviceId;
	}
	public void setSitedeviceId(String sitedeviceId) {
		this.sitedeviceId = sitedeviceId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	@ExcelField(title="站点名称",sort=1)
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	@ExcelField(title="公司名称",sort=2)
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	@ExcelField(title="设备名称", sort=3, dictType="device_info")
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	@ExcelField(title="操作动作", sort=4, dictType="oper_action")
	public String getOperAction() {
		return operAction;
	}
	public void setOperAction(String operAction) {
		this.operAction = operAction;
	}
	@ExcelField(title="操作时间", sort=5)
	public String getOperTime() {
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	@ExcelField(title="操作人", sort=6)
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	@ExcelField(title="操作内容", sort=7)
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	@ExcelField(title="创建时间", sort=8)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	public String getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	
}
