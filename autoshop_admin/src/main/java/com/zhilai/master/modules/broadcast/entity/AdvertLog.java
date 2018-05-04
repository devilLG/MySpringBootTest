package com.zhilai.master.modules.broadcast.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 
 *Title:AdvertLog
 *Description:广告日志实体类
 *@author ChengJiawei
 *@date 2018年4月19日 上午9:09:26
 *
 */
public class AdvertLog extends DataEntity<AdvertLog>{
	private static final long serialVersionUID = -1621310362063809709L;

	private String logid; //记录编号
	private String changeId; //日志编号
	private String configId; //配置编号
	private String configName; //配置名称
	private String corpId; //公司编号
	private String corpName; //公司名称
	private String siteId; //站点编号
	private String siteName; //站点名称
	private String operAction; //操作 0:生成 1:执行中 2:获取(站点获取播放信息动作，代表终端已执行) 3:失效
	private String operTime; //操作时间
	private String createTime; //创建时间
	
	//虚拟字段
	private String operTimeStart;//操作开始时间
	private String operTimeEnd;//操作结束时间
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getChangeId() {
		return changeId;
	}
	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}
	@ExcelField(title="配置编号", sort=2)
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
	@ExcelField(title="公司编号", sort=3)
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	@ExcelField(title="公司名称", sort=4)
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	@ExcelField(title="站点编号", sort=5)
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	@ExcelField(title="站点名称", sort=6)
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	@ExcelField(title="操作", sort=7 , dictType="advertOper_action")
	public String getOperAction() {
		return operAction;
	}
	public void setOperAction(String operAction) {
		this.operAction = operAction;
	}
	@ExcelField(title="操作时间", sort=8)
	public String getOperTime() {
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}
	@ExcelField(title="创建时间", sort=8)
	public String getCreateTime() {
		return createTime;
	}
	
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOperTimeStart() {
		return operTimeStart;
	}
	public void setOperTimeStart(String operTimeStart) {
		this.operTimeStart = operTimeStart;
	}
	public String getOperTimeEnd() {
		return operTimeEnd;
	}
	public void setOperTimeEnd(String operTimeEnd) {
		this.operTimeEnd = operTimeEnd;
	}
	
}
