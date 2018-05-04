package com.zhilai.master.modules.broadcast.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 
 *Title:AdvertAuth
 *Description:播控权限实体类
 *@author ChengJiawei
 *@date 2018年4月18日 下午2:14:30
 *
 */
public class AdvertAuth extends DataEntity<AdvertAuth>{
	private static final long serialVersionUID = -5150007003347948142L;

	private String logid; //记录编号
	private String authId; //权限编号
	private String targetId; //站点或区域编号
	private String targetName; //站点或区域名称
	private String targetType; //类型 1:站点 2:区域
	private String configId; //播控配置编号
	private String configName; //配置名称
	private String planStime; //计划开始播放日期(yyyy-MM-dd)
	private String planEtime; //计划停止播放日期(yyyy-MM-dd)
	private String curState; //状态 0:生成 1:执行中 2:失效
	private String stateTime; //状态时间
	private String createTime; //创建时间
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	@ExcelField(title="地点名称", sort=1)
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	@ExcelField(title="类型", sort=2 , dictType="target_type")
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	@ExcelField(title="配置名称", sort=3)
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	@ExcelField(title="计划开始时间", sort=4)
	public String getPlanStime() {
		return planStime;
	}
	public void setPlanStime(String planStime) {
		this.planStime = planStime;
	}
	@ExcelField(title="计划结束时间", sort=5)
	public String getPlanEtime() {
		return planEtime;
	}
	public void setPlanEtime(String planEtime) {
		this.planEtime = planEtime;
	}
	@ExcelField(title="状态", sort=6,dictType="advertConfig_state")
	public String getCurState() {
		return curState;
	}
	public void setCurState(String curState) {
		this.curState = curState;
	}
	@ExcelField(title="状态时间", sort=7)
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}
	@ExcelField(title="创建时间", sort=8)
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
			setAuthId(System.nanoTime()+"");
			setCurState("0");
		}
		this.createTime = DateUtils.getDateTime();
	}
}
