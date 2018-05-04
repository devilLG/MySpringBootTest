package com.zhilai.master.modules.parmter.entity;

import com.zhilai.master.common.persistence.DataEntity;

public class MsgSend extends DataEntity<MsgSend> {
	private static final long serialVersionUID = -3033199307602616096L;
	private String logid;
	private String corp_id;
	private String corp_name;
	private String notice_obj; //通知对象 01订单 02地点
	private String notice_key; //通知主键
	private String notice_type;//通知类型 01：短信  02：邮件
	private String notice_way;//通知方式 （手机号或邮件地址)
	private String cont; //内容
	private String plan_time;//计划时间
	private int fail_num;//发送失败次数
	private String proc_flag;//处理状态 0:未处理 1:已处理
	private String proc_time;//处理时间
	private String create_time;//创建时间
	
	//虚拟字段
	private String busi_type;//处理时间
	private String level;//创建时间
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getCorp_id() {
		return corp_id;
	}
	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	public String getCorp_name() {
		return corp_name;
	}
	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}
	public String getNotice_obj() {
		return notice_obj;
	}
	public void setNotice_obj(String notice_obj) {
		this.notice_obj = notice_obj;
	}
	public String getNotice_key() {
		return notice_key;
	}
	public void setNotice_key(String notice_key) {
		this.notice_key = notice_key;
	}
	public String getNotice_type() {
		return notice_type;
	}
	public void setNotice_type(String notice_type) {
		this.notice_type = notice_type;
	}
	public String getNotice_way() {
		return notice_way;
	}
	public void setNotice_way(String notice_way) {
		this.notice_way = notice_way;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getPlan_time() {
		return plan_time;
	}
	public void setPlan_time(String plan_time) {
		this.plan_time = plan_time;
	}
	public int getFail_num() {
		return fail_num;
	}
	public void setFail_num(int fail_num) {
		this.fail_num = fail_num;
	}
	public String getProc_flag() {
		return proc_flag;
	}
	public void setProc_flag(String proc_flag) {
		this.proc_flag = proc_flag;
	}
	public String getProc_time() {
		return proc_time;
	}
	public void setProc_time(String proc_time) {
		this.proc_time = proc_time;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getBusi_type() {
		return busi_type;
	}
	public void setBusi_type(String busi_type) {
		this.busi_type = busi_type;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	

}
