package com.zhilai.master.modules.site.entity;

import com.zhilai.master.common.persistence.DataEntity;

public class MqttSite extends DataEntity<MqttSite> {
	private static final long serialVersionUID = 3107247789734490155L;
	private String siteId;
	private String siteName;
	private String createTime;
	private String updateTime;
	private String state;//状态00可用01不可用02使用中
	private String location;//位置
	private String cityCode;//城市代码
	private String cityName;//城市名称
	private String corpId;//公司编号
	private String corpName;//公司名称
	private String groupId;//组编号
	private String groupName;//组名称
	private String latitude;//经度
	private String longitude;//纬度
	
	private String refreshState;//状态00在线 01离线02未启用
	private String devId;// varchar(10) DEFAULT NULL COMMENT '设备编号',
	private String devName;// varchar(20) DEFAULT NULL COMMENT '设备名称',
	
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

	public MqttSite(String siteId, String siteName, String createTime,
			String updateTime, String state, String location, String cityCode,
			String cityName, String corpId, String corpName, String groupId,
			String groupName, String latitude, String longitude,
			String refreshState, String devId, String devName) {
		super();
		this.siteId = siteId;
		this.siteName = siteName;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.state = state;
		this.location = location;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.corpId = corpId;
		this.corpName = corpName;
		this.groupId = groupId;
		this.groupName = groupName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.refreshState = refreshState;
		this.devId = devId;
		this.devName = devName;
	}

	public MqttSite() {
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	/**
	 * 状态00在线 01离线02未启用
	 * @return
	 */
	public String getRefreshState() {
		return refreshState;
	}
/**
 * 状态00在线 01离线02未启用
 * @param refreshState
 */
	public void setRefreshState(String refreshState) {
		this.refreshState = refreshState;
	}


	
}
