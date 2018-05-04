package com.zhilai.master.modules.standard.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * @author 郭伟 货道
 * @date 2017-12-18 下午2:34:29
 * 
 */
public class Box extends DataEntity<Box> {
	private static final long serialVersionUID = 115723358076704876L;
	private String logid;
	private String site_id;//地点编号
	private String site_name;//地点名称
	private int cabinet_id;//柜子编号
	private String cabinet_type;//柜子类型
	private int box_id;//箱号
	private String box_type;//箱子类型
	private String box_typeid;//类型编号
	private String layer_type;//
	private String box_state;//箱子状态0锁定1空箱2使用3损坏
	private String refresh_state;//刷新状态
	private String refresh_time;//刷新时间
	private String error_time;//异常时间
	private String oper_time;//
	private String error_flag;//异常标识
	private String owner_id;
	private String article_id;
	private String use_type;

	// 虚拟字段
	private String refresh_flag;

	public String getLogid() {
		return logid;
	}

	public String getSite_id() {
		return site_id;
	}

	public String getSite_name() {
		return site_name;
	}

	public int getCabinet_id() {
		return cabinet_id;
	}

	public String getCabinet_type() {
		return cabinet_type;
	}

	public int getBox_id() {
		return box_id;
	}

	public String getBox_type() {
		return box_type;
	}

	public String getBox_typeid() {
		return box_typeid;
	}

	public String getLayer_type() {
		return layer_type;
	}

	public String getBox_state() {
		return box_state;
	}

	public String getRefresh_state() {
		return refresh_state;
	}

	public String getRefresh_time() {
		return refresh_time;
	}

	public String getError_time() {
		return error_time;
	}

	public String getOper_time() {
		return oper_time;
	}

	public String getError_flag() {
		return error_flag;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public String getArticle_id() {
		return article_id;
	}

	public String getUse_type() {
		return use_type;
	}

	public String getRefresh_flag() {
		return refresh_flag;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public void setCabinet_id(int cabinet_id) {
		this.cabinet_id = cabinet_id;
	}

	public void setCabinet_type(String cabinet_type) {
		this.cabinet_type = cabinet_type;
	}

	public void setBox_id(int box_id) {
		this.box_id = box_id;
	}

	public void setBox_type(String box_type) {
		this.box_type = box_type;
	}

	public void setBox_typeid(String box_typeid) {
		this.box_typeid = box_typeid;
	}

	public void setLayer_type(String layer_type) {
		this.layer_type = layer_type;
	}

	public void setBox_state(String box_state) {
		this.box_state = box_state;
	}

	public void setRefresh_state(String refresh_state) {
		this.refresh_state = refresh_state;
	}

	public void setRefresh_time(String refresh_time) {
		this.refresh_time = refresh_time;
	}

	public void setError_time(String error_time) {
		this.error_time = error_time;
	}

	public void setOper_time(String oper_time) {
		this.oper_time = oper_time;
	}

	public void setError_flag(String error_flag) {
		this.error_flag = error_flag;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}

	public void setUse_type(String use_type) {
		this.use_type = use_type;
	}

	public void setRefresh_flag(String refresh_flag) {
		this.refresh_flag = refresh_flag;
	}

}
