package com.zhilai.master.modules.monitor.entity;

import java.util.List;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 
 *Title:SiteDeviceConfig
 *Description:站点设备实体类
 *@author ChengJiawei
 *@date 2018年4月11日 下午5:16:36
 *
 */
public class SiteDeviceConfig extends DataEntity<SiteDeviceConfig>{
	private static final long serialVersionUID = -6119426613494486651L;

	private String logid; //记录编号
	private String sdeviceId; //站点配件编号
	private String siteId; //站点编号
	private String siteName; //站点名称
	private String deviceId; //设备编号
	private String deviceCode; //设备编码
	private String deviceName; //设备名称
	private String deviceModel; //设备型号
	private String deviceBard; //设备品牌
	private String deviceMfrs; //设备生产商
	private String errorLevel; //故障严重等级 1:严重 2:中级 3:一般
	private String curState; //设备状态 1:正常 2:警告 3:故障
	private String stateTime; //状态时间
	private String cont; //故障描述
	private Integer maintenance; //维护周期(天)
	private String pcheckTime; //上次检测日期
	private String lcheckTime; //下次检测日期
	private String checkState; //检测状态 1:等待检查 2:完成检查 3:检查超期
	private String createTime; //创建时间
	private String corp_id;
	
	//虚拟字段
	private List<SiteEnv> siteEnv; //设备运行参数配置
	
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
	@ExcelField(title="站点编码", sort=1)
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	@ExcelField(title="站点名称", sort=2)
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
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
	@ExcelField(title="设备名称", sort=3, dictType="device_info")
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	@ExcelField(title="设备型号", sort=4, dictType="device_model")
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	@ExcelField(title="设备品牌", sort=5, dictType="device_bard")
	public String getDeviceBard() {
		return deviceBard;
	}
	public void setDeviceBard(String deviceBard) {
		this.deviceBard = deviceBard;
	}
	@ExcelField(title="设备生产商", sort=6, dictType="device_mfrs")
	public String getDeviceMfrs() {
		return deviceMfrs;
	}
	public void setDeviceMfrs(String deviceMfrs) {
		this.deviceMfrs = deviceMfrs;
	}
	@ExcelField(title="故障等级", sort=7, dictType="error_level_info")
	public String getErrorLevel() {
		return errorLevel;
	}
	public void setErrorLevel(String errorLevel) {
		this.errorLevel = errorLevel;
	}
	@ExcelField(title="设备状态", sort=8, dictType="siteDevice_state")
	public String getCurState() {
		return curState;
	}
	public void setCurState(String curState) {
		this.curState = curState;
	}
	@ExcelField(title="状态时间", sort=9)
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}
	@ExcelField(title="故障描述", sort=10)
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	@ExcelField(title="维护周期(天)", sort=11)
	public Integer getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(Integer maintenance) {
		this.maintenance = maintenance;
	}
	@ExcelField(title="上次检测日期", sort=12)
	public String getPcheckTime() {
		return pcheckTime;
	}
	public void setPcheckTime(String pcheckTime) {
		this.pcheckTime = pcheckTime;
	}
	@ExcelField(title="下次检测日期", sort=13)
	public String getLcheckTime() {
		return lcheckTime;
	}
	public void setLcheckTime(String lcheckTime) {
		this.lcheckTime = lcheckTime;
	}
	@ExcelField(title="设备状态", sort=14, dictType="siteDeviceCheck_state")
	public String getCheckState() {
		return checkState;
	}
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	@ExcelField(title="创建时间", sort=15)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	

	public String getCorp_id() {
		return corp_id;
	}
	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	
	public List<SiteEnv> getSiteEnv() {
		return siteEnv;
	}
	public void setSiteEnv(List<SiteEnv> siteEnv) {
		this.siteEnv = siteEnv;
	}
	
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			setLogid(System.nanoTime()+"");
			setSdeviceId(System.nanoTime()+"");
		}
		this.createTime = DateUtils.getDateTime();
	}
	
}
