package com.zhilai.master.modules.workOrder.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * 
 *Title:WorkDevice
 *Description:工单设备实体类
 *@author ChengJiawei
 *@date 2018年4月19日 上午10:39:09
 *
 */
public class WorkDevice extends DataEntity<WorkDevice>{
	private static final long serialVersionUID = -4381961024630098562L;
	
	private String logid;	//记录编号
	private String corpId;	//公司编号
	private String corpName;	//公司名称
	private String workId;	//工单编号
	private String deviceId;	//设备编号
	private String deviceCode;	//设备编码(引用数据字典)
	private String deviceName;	//设备名称(引用数据字典)
	private String deviceModel;	//设备型号
	private String deviceBard;	//设备品牌
	private String deviceSize;	//设备尺寸 长*宽*高
	private String devicePic;	//设备图片地址信息
	private String curState;	//设备状态 1:正常 2:警告 3:故障
	private String createTime;	//创建时间
	
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceBard() {
		return deviceBard;
	}
	public void setDeviceBard(String deviceBard) {
		this.deviceBard = deviceBard;
	}
	public String getDeviceSize() {
		return deviceSize;
	}
	public void setDeviceSize(String deviceSize) {
		this.deviceSize = deviceSize;
	}
	public String getDevicePic() {
		return devicePic;
	}
	public void setDevicePic(String devicePic) {
		this.devicePic = devicePic;
	}
	public String getCurState() {
		return curState;
	}
	public void setCurState(String curState) {
		this.curState = curState;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
