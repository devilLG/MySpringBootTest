package com.zhilai.master.modules.broadcast.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.sys.utils.UserUtils;

/**
 * 
 *Title:AdvertConfig
 *Description:广告配置实体类
 *@author ChengJiawei
 *@date 2018年4月16日 下午5:40:28
 *
 */

public class AdvertConfig extends DataEntity<AdvertConfig>{
	private static final long serialVersionUID = -418600141362992702L;

	private String logid; //记录编号
	private String configId; //配置编号
	private String configName; //配置名称
	private String corpId; //公司编号
	private String corpName; //公司名称
	private String isDefalut; //是否默认播放 1:是 2:否
	private String planStime; //计划开始播放日期(yyyy-MM-dd)
	private String planEtime; //计划停止播放日期(yyyy-MM-dd)
	private Integer areaNum; //显示屏分割区域播放数 如果是1则代表整块显示屏播放，2表示有两个独自的播放区域
	private Integer mediaNum; //播放媒体数量
	private String resolutionX; //屏幕显示像素x轴(数据字典里配置多种分辨率)
	private String resolutionY; //屏幕显示像素y轴(数据字典里配置多种分辨率)
	private String playTime; //单次播放时长
	private String description; //配置描述
	private String curState; //状态 0:生成 1:执行中 2:失效
	private String stateTime; //状态时间
	private String createTime; //创建时间
	
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	@ExcelField(title="配置名称", sort=1)
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	@ExcelField(title="公司名称", sort=2)
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	@ExcelField(title="是否默认播放", sort=3 , dictType="is_defalut")
	public String getIsDefalut() {
		return isDefalut;
	}
	public void setIsDefalut(String isDefalut) {
		this.isDefalut = isDefalut;
	}
	@ExcelField(title="计划开始播放日期", sort=4)
	public String getPlanStime() {
		return planStime;
	}
	public void setPlanStime(String planStime) {
		this.planStime = planStime;
	}
	@ExcelField(title="计划停止播放日期", sort=5)
	public String getPlanEtime() {
		return planEtime;
	}
	public void setPlanEtime(String planEtime) {
		this.planEtime = planEtime;
	}
	@ExcelField(title="显示屏分割区域播放数", sort=6 , dictType="area_num")
	public Integer getAreaNum() {
		return areaNum;
	}
	public void setAreaNum(Integer areaNum) {
		this.areaNum = areaNum;
	}
	@ExcelField(title="播放媒体数量", sort=7)
	public Integer getMediaNum() {
		return mediaNum;
	}
	public void setMediaNum(Integer mediaNum) {
		this.mediaNum = mediaNum;
	}
	@ExcelField(title="屏幕显示像素x轴", sort=8)
	public String getResolutionX() {
		return resolutionX;
	}
	public void setResolutionX(String resolutionX) {
		this.resolutionX = resolutionX;
	}
	@ExcelField(title="屏幕显示像素y轴", sort=9)
	public String getResolutionY() {
		return resolutionY;
	}
	public void setResolutionY(String resolutionY) {
		this.resolutionY = resolutionY;
	}
	@ExcelField(title="单次播放时长", sort=10)
	public String getPlayTime() {
		return playTime;
	}
	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	@ExcelField(title="配置描述", sort=11)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ExcelField(title="状态", sort=12 ,dictType="advertConfig_state")
	public String getCurState() {
		return curState;
	}
	public void setCurState(String curState) {
		this.curState = curState;
	}
	@ExcelField(title="状态时间", sort=13)
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}
	@ExcelField(title="创建时间", sort=14)
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
			setConfigId(System.nanoTime()+"");
			setCurState("0");
		}
		this.createTime = DateUtils.getDateTime();
	}
}
