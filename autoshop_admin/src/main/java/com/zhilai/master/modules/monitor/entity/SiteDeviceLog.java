package com.zhilai.master.modules.monitor.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 
 *Title:SiteDeviceLog
 *Description:站点日志实体类
 *@author ChengJiawei
 *@date 2018年4月13日 上午8:26:51
 *
 */
public class SiteDeviceLog extends DataEntity<SiteDeviceLog>{
	private static final long serialVersionUID = -567434193505559695L;
	
	private String logid; //记录编号
	private String sitedeviceId; //站点配件编号
	private String siteId; //站点编号
	private String siteName; //站点名称
	private String corpId; //公司编号
	private String corpName; //公司名称
	private String deviceCode; //设备编码
	private String deviceName; //设备名称
	private String sendCmd; //操作发出内容
	private String acceptCmd; //操作结果内容
	private String operState; //操作状态 1:正常 2:异常
	private String logLevel; //日志等级 1:debug 2:info 3:warn 4:error
	private String operId; //操作人名称
	private String operName; //操作者名称
	private String cont; //描述
	private String generateTime; //日志生成时间
	private String createTime; //创建时间
	
	//虚拟字段
	private String generateTimeStart; //日志生成开始时间
	private String generateTimeEnd; //日志生成结束时间
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
	@ExcelField(title="发出内容",sort=4)
	public String getSendCmd() {
		return sendCmd;
	}
	public void setSendCmd(String sendCmd) {
		this.sendCmd = sendCmd;
	}
	@ExcelField(title="结果内容",sort=5)
	public String getAcceptCmd() {
		return acceptCmd;
	}
	public void setAcceptCmd(String acceptCmd) {
		this.acceptCmd = acceptCmd;
	}
	@ExcelField(title="操作状态", sort=6, dictType="oper_state")
	public String getOperState() {
		return operState;
	}
	public void setOperState(String operState) {
		this.operState = operState;
	}
	@ExcelField(title="日志等级", sort=7, dictType="log_level")
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	@ExcelField(title="操作人名称",sort=9)
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	@ExcelField(title="操作内容",sort=8)
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	@ExcelField(title="日志生成时间",sort=10)
	public String getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(String generateTime) {
		this.generateTime = generateTime;
	}
	@ExcelField(title="创建时间",sort=11)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
	public String getGenerateTimeStart() {
		return generateTimeStart;
	}
	public void setGenerateTimeStart(String generateTimeStart) {
		this.generateTimeStart = generateTimeStart;
	}
	public String getGenerateTimeEnd() {
		return generateTimeEnd;
	}
	public void setGenerateTimeEnd(String generateTimeEnd) {
		this.generateTimeEnd = generateTimeEnd;
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
