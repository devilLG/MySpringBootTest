package com.zhilai.master.modules.site.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;


/**
 * @author guowei
 * 地点状态
 * @date 2017-12-11 下午6:17:18
 * 
 */
public class SiteState extends DataEntity<SiteState> {
	private static final long serialVersionUID = 115723358076704876L;
	private String logid;			//记录编号
	private String site_id;			//地点编号
	private String site_name;		//地点名称
	private int box_all;			//货道总数
	private int box_empty;			//当前货道空箱数量
	//private int box_using;          //使用中货道
	private int box_short;			//当前货道缺货数量
	private int box_damage;			//当前故障货道数量
	private String refresh_state; //刷新状态 0离线1在线2不可用
	private String refresh_time; //刷新时间
	private int refresh_num; //刷新次数
	private String compute_time;  //计算时间
	private String compute_state; //计算状态
	private String owner_id;  //归属公司编号
	
	
	public String getLogid() {
		return logid;
	}
	@ExcelField(title="站点编号", sort=10)
	public String getSite_id() {
		return site_id;
	}
	@ExcelField(title="站点简称", sort=15)
	public String getSite_name() {
		return site_name;
	}
	
	public int getBox_all() {
		return box_all;
	}
	
	public int getBox_empty() {
		return box_empty;
	}
	
	public int getBox_short() {
		return box_short;
	}
	
	public int getBox_damage() {
		return box_damage;
	}
	@ExcelField(title="网络状态", sort=20, dictType="refresh_state")
	public String getRefresh_state() {
		return refresh_state;
	}
	@ExcelField(title="刷新时间", sort=25)
	public String getRefresh_time() {
		return refresh_time;
	}
	@ExcelField(title="刷新次数", sort=30)
	public int getRefresh_num() {
		return refresh_num;
	}
	public String getCompute_time() {
		return compute_time;
	}
	public String getCompute_state() {
		return compute_state;
	}
	public String getOwner_id() {
		return owner_id;
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
	public void setBox_all(int box_all) {
		this.box_all = box_all;
	}
	public void setBox_empty(int box_empty) {
		this.box_empty = box_empty;
	}
	public void setBox_short(int box_short) {
		this.box_short = box_short;
	}
	public void setBox_damage(int box_damage) {
		this.box_damage = box_damage;
	}
	public void setRefresh_state(String refresh_state) {
		this.refresh_state = refresh_state;
	}
	public void setRefresh_time(String refresh_time) {
		this.refresh_time = refresh_time;
	}
	public void setRefresh_num(int refresh_num) {
		this.refresh_num = refresh_num;
	}
	public void setCompute_time(String compute_time) {
		this.compute_time = compute_time;
	}
	public void setCompute_state(String compute_state) {
		this.compute_state = compute_state;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
//	public int getBox_using() {
//		return box_using;
//	}
//	public void setBox_using(int box_using) {
//		this.box_using = box_using;
//	}
		
}
