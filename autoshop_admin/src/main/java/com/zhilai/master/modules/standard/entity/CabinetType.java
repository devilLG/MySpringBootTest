package com.zhilai.master.modules.standard.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * @author guowei
 * 柜子类型   记录柜子的类型
 * @date 2017-12-7 上午10:23:16
 * 
 */
public class CabinetType extends DataEntity<CabinetType> {
	private static final long serialVersionUID = 3107247789734490155L;
	private String logid;//逻辑编号
	private String corp_id;//公司编号
	private String type_id;//类型编号
	private String type_name;//类型简称
	private String fullname;//类型全称
	private Integer box_num;//货道总数
	private Integer column_num;//列数
	private String color;//颜色
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLogid() {
		return logid;
	}
	public String getCorp_id() {
		return corp_id;
	}
	@ExcelField(title="类型编号", sort=10)
	public String getType_id() {
		return type_id;
	}
	@ExcelField(title="类型简称", sort=20)
	public String getType_name() {
		return type_name;
	}
	@ExcelField(title="类型全称", sort=30)
	public String getFullname() {
		return fullname;
	}
	@ExcelField(title="货道总数", sort=40)
	public Integer getBox_num() {
		return box_num;
	}
	@ExcelField(title="总列数", sort=50)
	public Integer getColumn_num() {
		return column_num;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public void setBox_num(Integer box_num) {
		this.box_num = box_num;
	}
	public void setColumn_num(Integer column_num) {
		this.column_num = column_num;
	}
	
}
