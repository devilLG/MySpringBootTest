package com.zhilai.master.modules.workOrder.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;
import com.zhilai.master.modules.sys.utils.UserUtils;

/**
 * 
 *Title:WorkChange
 *Description:工单状态信息实体类
 *@author ChengJiawei
 *@date 2018年4月19日 上午10:36:27
 *
 */
public class WorkChange extends DataEntity<WorkChange>{
	private static final long serialVersionUID = 4625668973528380386L;

	private String logid;	//记录编号
	private String corpId;	//公司编号
	private String corpName;	//公司名称
	private String workId;	//工单编号
	private String workTitle;	//工单标题
	private String operAction;	//操作动作 01:创建 02:受理 03:派单 04:接单 05:退回 06:已处理 07:已完成 08:撤单
	private String operTime;	//工单状态时间
	private String operId;	//操作人编号
	private String operName;	//操作人名称
	private String perCont;	//操作内容
	private String createTime;	//创建时间
	
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	@ExcelField(title="公司编号", sort=4)
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	@ExcelField(title="公司名称", sort=3)
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	@ExcelField(title="工单编号", sort=2)
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
	@ExcelField(title="操作动作", sort=5,dictType="workOper_action")
	public String getOperAction() {
		return operAction;
	}
	public void setOperAction(String operAction) {
		this.operAction = operAction;
	}
	@ExcelField(title="操作时间", sort=6)
	public String getOperTime() {
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	@ExcelField(title="操作人名称", sort=7)
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	@ExcelField(title="操作内容", sort=8)
	public String getPerCont() {
		return perCont;
	}
	public void setPerCont(String perCont) {
		this.perCont = perCont;
	}
	@ExcelField(title="创建时间", sort=9)
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
