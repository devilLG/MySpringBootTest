package com.zhilai.master.modules.standard.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * @author 郭伟 柜厢信息
 * @date 2017-12-18 下午2:34:29
 * 
 */
public class CabinetAttr extends DataEntity<CabinetAttr> {
	private static final long serialVersionUID = 115723358076704876L;
	private String logid;// 逻辑编号
	private String corp_id;// 公司编号
	private String attr_id;// 属性编号
	private String cabinettype_id;// 柜子类型编号
	private String boxtype_id;// 货道类型编号
	private Integer cloumn_id;// 列数
	private Integer box_id;// 货道编号
	
	private String layer_num;// 层数
	private String oper_time;// 操作时间
	private String state;// 状态0-有效 1-无效
	private Integer seq_id;//

	public String getLogid() {
		return logid;
	}

	public String getCorp_id() {
		return corp_id;
	}
	@ExcelField(title="属性编号", sort=10)
	public String getAttr_id() {
		return attr_id;
	}
	@ExcelField(title="柜子类型编号", sort=20)
	public String getCabinettype_id() {
		return cabinettype_id;
	}
	@ExcelField(title="货道类型编号", sort=30)
	public String getBoxtype_id() {
		return boxtype_id;
	}
	@ExcelField(title="货道列数", sort=40)
	public Integer getCloumn_id() {
		return cloumn_id;
	}
	
	@ExcelField(title="货道编号", sort=35)
	public Integer getBox_id() {
		return box_id;
	}
	@ExcelField(title="层数", sort=45)
	public String getLayer_num() {
		return layer_num;
	}

	public String getOper_time() {
		return oper_time;
	}
	@ExcelField(title="状态**批注**", sort=80, dictType="State")
	public String getState() {
		return state;
	}

	public Integer getSeq_id() {
		return seq_id;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}

	public void setAttr_id(String attr_id) {
		this.attr_id = attr_id;
	}

	public void setCabinettype_id(String cabinettype_id) {
		this.cabinettype_id = cabinettype_id;
	}

	public void setBoxtype_id(String boxtype_id) {
		this.boxtype_id = boxtype_id;
	}

	public void setCloumn_id(Integer cloumn_id) {
		this.cloumn_id = cloumn_id;
	}
	public void setBox_id(Integer box_id) {
		this.box_id = box_id;
	}
	public void setLayer_num(String layer_num) {
		this.layer_num = layer_num;
	}

	public void setOper_time(String oper_time) {
		this.oper_time = oper_time;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}

}
