package com.zhilai.master.modules.monitor.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.DateUtils;

/**
 * 
 *Title:SiteEnv
 *Description:站点设备运行状态实体类
 *@author ChengJiawei
 *@date 2018年4月11日 下午5:49:33
 *
 */
public class SiteEnv extends DataEntity<SiteEnv>{
	private static final long serialVersionUID = -3632563882733893378L;

	private String logid; //记录编号
	private String sdeviceId; //站点配件编号
	private String siteId; //站点编号
	private String siteName; //站点名称
	private String corpId; //公司编号
	private String corpName; //公司名称
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
	private String runValue; //当前实际运行参数值
	private String curState; //参数状态 1:正常 2:警报 3:故障
	private String stateTime; //状态时间
	private String createTime; //创建时间
	
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getSdeviceId() {
		return sdeviceId;
	}
	public void setSdeviceId(String sdeviceId) {
		this.sdeviceId = sdeviceId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
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
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
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
	public String getRunValue() {
		return runValue;
	}
	public void setRunValue(String runValue) {
		this.runValue = runValue;
	}
	public String getCurState() {
		return curState;
	}
	public void setCurState(String curState) {
		this.curState = curState;
	}
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
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
		}
		this.createTime = DateUtils.getDateTime();
	}
}
