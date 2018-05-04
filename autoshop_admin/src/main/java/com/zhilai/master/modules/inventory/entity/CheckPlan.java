package com.zhilai.master.modules.inventory.entity;	import com.zhilai.master.common.persistence.DataEntity;import com.zhilai.master.common.utils.excel.annotation.ExcelField;public class CheckPlan extends DataEntity<CheckPlan> {		private static final long serialVersionUID = 1L;		private String logid;	//	记录编号	private String planId;	//	计划编号	private String corpId;	//	公司编号(业主)	private String corpName;	//	公司名称(业主)	private String planType;	//	计划对象 01:站点 02:区域	private String targetId;	//	计划对象编号	private String targetName;	//	计划对象名称	private String cycleType;	//	周期类型 1:按每周 2:按每月	private String cycleValue;	//	按每周星期几，每月几号执行	private String checkType;	//	  盘点方式 1:全盘 2:抽盘	private String checkObj;	//	盘点对象 1:货道 2:商品	private String curState;	//	状态 1:正常 2:失效	private String stateTime;	//	状态时间	private String createTime;	//	创建时间		public String getLogid() {		return logid;	}	public void setLogid(String logid) {		this.logid = logid == null ? null : logid.trim();	}		public String getPlanId() {		return planId;	}	public void setPlanId(String planId) {		this.planId = planId == null ? null : planId.trim();	}		public String getCorpId() {		return corpId;	}	public void setCorpId(String corpId) {		this.corpId = corpId == null ? null : corpId.trim();	}		@ExcelField(title="所属公司" ,sort=1)	public String getCorpName() {		return corpName;	}	public void setCorpName(String corpName) {		this.corpName = corpName == null ? null : corpName.trim();	}		@ExcelField(title="计划对象类型" ,sort=2, dictType="planType")	public String getPlanType() {		return planType;	}	public void setPlanType(String planType) {		this.planType = planType == null ? null : planType.trim();	}	@ExcelField(title="计划对象编号" ,sort=3)	public String getTargetId() {		return targetId;	}	public void setTargetId(String targetId) {		this.targetId = targetId == null ? null : targetId.trim();	}	@ExcelField(title="计划对象名称" ,sort=4)	public String getTargetName() {		return targetName;	}	public void setTargetName(String targetName) {		this.targetName = targetName == null ? null : targetName.trim();	}	@ExcelField(title="周期类型" ,sort=5, dictType="cycleType")	public String getCycleType() {		return cycleType;	}	public void setCycleType(String cycleType) {		this.cycleType = cycleType ;	}	@ExcelField(title="周期值" ,sort=6)	public String getCycleValue() {		return cycleValue;	}	public void setCycleValue(String cycleValue) {		this.cycleValue = cycleValue ;	}	@ExcelField(title="盘点方式" ,sort=7, dictType="checkType")	public String getCheckType() {		return checkType;	}	public void setCheckType(String checkType) {		this.checkType = checkType == null ? null : checkType.trim();	}	@ExcelField(title="盘点对象" ,sort=8, dictType="checkObj")	public String getCheckObj() {		return checkObj;	}	public void setCheckObj(String checkObj) {		this.checkObj = checkObj == null ? null : checkObj.trim();	}	@ExcelField(title="状态" ,sort=9, dictType="Cur_state")	public String getCurState() {		return curState;	}	public void setCurState(String curState) {		this.curState = curState == null ? null : curState.trim();	}	public String getStateTime() {		return stateTime;	}	public void setStateTime(String stateTime) {		this.stateTime = stateTime == null ? null : stateTime.trim();	}	@ExcelField(title="创建时间" ,sort=11)	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime == null ? null : createTime.trim();	}		private String site_id; //站点编号	private String site_name;//站点名称	private String area_code;//区域编号	private String area_name;//区域名称	public String getSite_id() {		return site_id;	}	public void setSite_id(String site_id) {		this.site_id = site_id;	}	public String getSite_name() {		return site_name;	}	public void setSite_name(String site_name) {		this.site_name = site_name;	}	public String getArea_code() {		return area_code;	}	public void setArea_code(String area_code) {		this.area_code = area_code;	}	public String getArea_name() {		return area_name;	}	public void setArea_name(String area_name) {		this.area_name = area_name;	}	}