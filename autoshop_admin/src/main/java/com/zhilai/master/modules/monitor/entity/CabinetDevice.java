package com.zhilai.master.modules.monitor.entity;

import java.util.List;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 
 *Title:CabinetDevice
 *Description:柜子设备实体类
 *@author ChengJiawei
 *@date 2018年4月11日 上午9:08:38
 *
 */
public class CabinetDevice extends DataEntity<CabinetDevice>{
	private static final long serialVersionUID = 6502298773217180971L;
	
	private String logid; //记录编号
	private String cadeviceId; //柜子配件编号
	private String cabconfigId; //柜子配置标识
	private String cabconfigName; //柜子配置名称
	private String deviceId; //设备编号
	private String deviceCode; //设备编码
	private String deviceName; //设备名称
	private String deviceModel; //设备型号
	private String deviceBard; //设备品牌
	private String deviceMfrs; //设备生产商
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
	public String getCadeviceId() {
		return cadeviceId;
	}
	public void setCadeviceId(String cadeviceId) {
		this.cadeviceId = cadeviceId;
	}
	public String getCabconfigId() {
		return cabconfigId;
	}
	public void setCabconfigId(String cabconfigId) {
		this.cabconfigId = cabconfigId;
	}
	@ExcelField(title="柜子配置名称", sort=1)
	public String getCabconfigName() {
		return cabconfigName;
	}
	public void setCabconfigName(String cabconfigName) {
		this.cabconfigName = cabconfigName;
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
	@ExcelField(title="设备名称", sort=2, dictType="device_info")
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	@ExcelField(title="设备型号", sort=3, dictType="device_model")
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	@ExcelField(title="设备品牌", sort=4, dictType="device_bard")
	public String getDeviceBard() {
		return deviceBard;
	}
	public void setDeviceBard(String deviceBard) {
		this.deviceBard = deviceBard;
	}
	@ExcelField(title="设备生产商", sort=5, dictType="device_mfrs")
	public String getDeviceMfrs() {
		return deviceMfrs;
	}
	public void setDeviceMfrs(String deviceMfrs) {
		this.deviceMfrs = deviceMfrs;
	}
	@ExcelField(title="创建时间", sort=6)
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
	
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			setLogid(System.nanoTime()+"");
			setCadeviceId(System.nanoTime()+"");
		}
		this.createTime = DateUtils.getDateTime();
	}

}
