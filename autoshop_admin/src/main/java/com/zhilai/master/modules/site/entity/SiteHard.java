package com.zhilai.master.modules.site.entity;

import com.zhilai.master.common.persistence.DataEntity;

public class SiteHard  extends DataEntity<SiteHard> {
	private static final long serialVersionUID = 1080254379586922398L;
	//private String id;//用于数据库插入查询等的序号，不是服务器下发的
	private String siteId;//站点编号
	private String siteName;//站点名称
	private String cabinettypeId;//柜子类型编号
	private String location;//柜子的摆放位置从左至右
	private String cabinetId;//柜子的序列号，唯一编号 柜机编码
	private String sim;//SIM卡的信息
	private String state;//配置信息的有效性 0=有效；1=无效 The state of the configuration 0=valid; 1= invalid
	private String currTime;//配置时间
	private String updateTime;//修改时间 
	private String devId;// 设备编号
	private String devName;// 设备名称
	private String devType;// 设备类型
	private String devModel;// 设备型号
	private String devEdition;// 设备当前版本
	private String editionDate;// 当前版本日期
	private String devSeq;// 设备序列号
	private String loginId;// 用户编号01顺丰02丰巢
	private String loginName;// 用户名称
	private String devStu;// 设备状态:在线01离线02
	private String areaId;// 城市代码
	private String areaName;// 城市名称
	private String addr;// 详细地址
	private String latitude;// 经度
	private String longitude;// 纬度
	private String grpName;// 缺省组名(默认组)
	private String devDesc;// 设备描述 
	private String versionCode;// 
	private String clientId;
	
	
	
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
	public String getCabinettypeId() {
		return cabinettypeId;
	}
	public void setCabinettypeId(String cabinettypeId) {
		this.cabinettypeId = cabinettypeId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCabinetId() {
		return cabinetId;
	}
	public void setCabinetId(String cabinetId) {
		this.cabinetId = cabinetId;
	}
	public String getSim() {
		return sim;
	}
	public void setSim(String sim) {
		this.sim = sim;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCurrTime() {
		return currTime;
	}
	public void setCurrTime(String currTime) {
		this.currTime = currTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public String getDevType() {
		return devType;
	}
	public void setDevType(String devType) {
		this.devType = devType;
	}
	public String getDevModel() {
		return devModel;
	}
	public void setDevModel(String devModel) {
		this.devModel = devModel;
	}
	public String getDevEdition() {
		return devEdition;
	}
	public void setDevEdition(String devEdition) {
		this.devEdition = devEdition;
	}
	public String getEditionDate() {
		return editionDate;
	}
	public void setEditionDate(String editionDate) {
		this.editionDate = editionDate;
	}
	public String getDevSeq() {
		return devSeq;
	}
	public void setDevSeq(String devSeq) {
		this.devSeq = devSeq;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getDevStu() {
		return devStu;
	}
	public void setDevStu(String devStu) {
		this.devStu = devStu;
	}
	
	
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getGrpName() {
		return grpName;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	public String getDevDesc() {
		return devDesc;
	}
	public void setDevDesc(String devDesc) {
		this.devDesc = devDesc;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
}
