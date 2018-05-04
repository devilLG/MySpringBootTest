package com.zhilai.master.modules.monitor.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.DateUtils;

/**
 * 
 *Title:DeviceEnv
 *Description:设备运行参数配置实体类
 *@author ChengJiawei
 *@date 2018年4月2日 上午9:36:09
 *
 */

public class DeviceEnv extends DataEntity<DeviceEnv>{
	private static final long serialVersionUID = 1L;
	
	private String logid; //记录编号
	private String envId; //环境编号
	private String deviceId; //设备编号
	private String deviceCode; //设备编码
	private String deviceName; //设备名称
	private String deviceModel; //设备型号
	private String envCode; //运行参数代码
	private String envName; //运行参数名称
	private String envSvalue; //运行参数最低正常值
	private String envEvalue; //运行参数最高正常值
	private String envWsvalue; //运行参数警报最低正常值
	private String envWevalue; //运行参数警报最高正常值
	private String unitId; //参数单位编码(数据字典)
	private String unitName; //参数单位名称(数据字典)
	private String errorLevel; //是否触发设备故障状态 1:否 2:是
	private String createTime; //创建时间
	
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getEnvId() {
		return envId;
	}
	public void setEnvId(String envId) {
		this.envId = envId;
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
	public String getEnvCode() {
		return envCode;
	}
	public void setEnvCode(String envCode) {
		this.envCode = envCode;
	}
	public String getEnvName() {
		return envName;
	}
	public void setEnvName(String envName) {
		this.envName = envName;
	}
	public String getEnvSvalue() {
		return envSvalue;
	}
	public void setEnvSvalue(String envSvalue) {
		this.envSvalue = envSvalue;
	}
	public String getEnvEvalue() {
		return envEvalue;
	}
	public void setEnvEvalue(String envEvalue) {
		this.envEvalue = envEvalue;
	}
	public String getEnvWsvalue() {
		return envWsvalue;
	}
	public void setEnvWsvalue(String envWsvalue) {
		this.envWsvalue = envWsvalue;
	}
	public String getEnvWevalue() {
		return envWevalue;
	}
	public void setEnvWevalue(String envWevalue) {
		this.envWevalue = envWevalue;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getErrorLevel() {
		return errorLevel;
	}
	public void setErrorLevel(String errorLevel) {
		this.errorLevel = errorLevel;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			setLogid(System.nanoTime()+"");
			setEnvId(System.nanoTime()+"");
		}
		this.createTime = DateUtils.getDateTime();
	}

}
