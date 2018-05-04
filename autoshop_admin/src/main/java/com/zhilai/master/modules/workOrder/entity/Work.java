package com.zhilai.master.modules.workOrder.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;
import com.zhilai.master.modules.sys.utils.UserUtils;

/**
 * 
 *Title:Work
 *Description:工单信息实体类
 *@author ChengJiawei
 *@date 2018年4月19日 上午10:38:44
 *
 */
public class Work extends DataEntity<Work>{
	private static final long serialVersionUID = -7067328265947758019L;

	private String logid;	//记录编号
	private String corpId;	//公司编号
	private String corpName;	//公司名称
	private String workId;	//工单编号
	private String workTitle;	//工单标题
	private String workLevel;	//工单级别(0:一般 1:中等 2:紧急)
	private String workType;	//工单类型 01:维修工单 02:移机工单 03:巡检工单 04:客户投诉
	private String questionCont;	//问题描述
	private String handlePlan;	//初步问题处理方案描述
	private String handleResult;	//实际处理方案描述
	private String siteId;	//站点编号
	private String siteName;	//站点名称
	private String managerId;	//工单管理人员编号
	private String managerName;	//工单管理人员名称
	private String handlerId;	//当前处理人编号
	private String handlerName;	//当前处理人名称
	private String acceptTime;	//工单受理时间
	private String handleStime;	//工单处理开始时间
	private String handleEtime;	//工单处理结束时间
	private String curState;	//工单状态时间 01:创建 02:受理 03:派单 04:接单 05:退回 06:已处理 07:已完成 08:撤单
	private String stateTime;	//工单状态时间
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
	@ExcelField(title="工单标题", sort=1)
	public String getWorkTitle() {
		return workTitle;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	@ExcelField(title="工单级别", sort=3,dictType="work_level")
	public String getWorkLevel() {
		return workLevel;
	}
	public void setWorkLevel(String workLevel) {
		this.workLevel = workLevel;
	}
	@ExcelField(title="工单类型", sort=4,dictType="work_type")
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	@ExcelField(title="问题描述", sort=7)
	public String getQuestionCont() {
		return questionCont;
	}
	public void setQuestionCont(String questionCont) {
		this.questionCont = questionCont;
	}
	@ExcelField(title="初步问题处理方案描述", sort=8)
	public String getHandlePlan() {
		return handlePlan;
	}
	public void setHandlePlan(String handlePlan) {
		this.handlePlan = handlePlan;
	}
	@ExcelField(title="实际问题处理方案描述", sort=9)
	public String getHandleResult() {
		return handleResult;
	}
	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	@ExcelField(title="站点", sort=2)
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	@ExcelField(title="责任人", sort=5)
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getHandlerId() {
		return handlerId;
	}
	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}
	@ExcelField(title="处理人", sort=6)
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	public String getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}
	public String getHandleStime() {
		return handleStime;
	}
	public void setHandleStime(String handleStime) {
		this.handleStime = handleStime;
	}
	public String getHandleEtime() {
		return handleEtime;
	}
	public void setHandleEtime(String handleEtime) {
		this.handleEtime = handleEtime;
	}
	@ExcelField(title="工单状态", sort=10,dictType="workCur_state")
	public String getCurState() {
		return curState;
	}
	public void setCurState(String curState) {
		this.curState = curState;
	}
	@ExcelField(title="状态时间", sort=11)
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}
	@ExcelField(title="创建时间", sort=12)
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
			setCorpId(UserUtils.getUser().getCorpId());
			setCorpName(UserUtils.getUser().getCompanyName());
		}
		this.createTime = DateUtils.getDateTime();
	}
	
}
