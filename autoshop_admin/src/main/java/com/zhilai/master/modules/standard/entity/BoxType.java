package com.zhilai.master.modules.standard.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;
 
/**
 * @author 郭伟
 * 货道类型
 * @date 2017-12-18 下午2:34:29
 * 
 */
public class BoxType extends DataEntity<BoxType>{
	private static final long serialVersionUID = 115723358076704876L;
	private String logid;//逻辑编号
	private String corp_id;//公司编号
	private String type_id;//类型编号
	private String type_name;//类型简称
	private String fullname;//类型全称
	private float   size_length;//长
	private float   size_width;//宽
	private float   size_height;//高
	private float    size_level;//深度
	private String  exceed_type;//大号规格
	 	
		
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
	@ExcelField(title="类型编号", sort=20)
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	@ExcelField(title="类型简称", sort=30)
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	@ExcelField(title="类型全称", sort=40)
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	@ExcelField(title="长度(cm)", sort=50)
	public float getSize_length() {
		return size_length;
	}
	public void setSize_length(float size_length) {
		this.size_length = size_length;
	}
	@ExcelField(title="宽度(cm)", sort=60)
	public float getSize_width() {
		return size_width;
	}
	public void setSize_width(float size_width) {
		this.size_width = size_width;
	}
	@ExcelField(title="高度(cm)", sort=70)
	public float getSize_height() {
		return size_height;
	}
	public void setSize_height(float size_height) {
		this.size_height = size_height;
	}
	@ExcelField(title="容积(cm³)", sort=80)
	public float getSize_level() {
		return size_level;
	}
	
	public void setSize_level(float size_level) {
		this.size_level = size_level;
	}
	@ExcelField(title="大号规格", sort=90)
	public String getExceed_type() {
		return exceed_type;
	}
	public void setExceed_type(String exceed_type) {
		this.exceed_type = exceed_type;
	}
	
}
