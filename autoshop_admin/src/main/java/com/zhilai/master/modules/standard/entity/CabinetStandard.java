package com.zhilai.master.modules.standard.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * @author guowei 规格配置
 * @date 2017-12-11 下午6:17:18
 * 
 */
public class CabinetStandard extends DataEntity<CabinetStandard> {
	private static final long serialVersionUID = 115723358076704876L;
	private String logid; // 记录编号
	private String corp_id; // 公司编号
	private String cabconfig_id; // 柜子配置标识
	private String cabconfig_name; // 柜子配置名称
	private String cabinettype_id; // 柜子类型编号
	private String cabinettype_name; // 柜子类型名称
	private String cabinetTemp_type; // 柜子类型1:常温 2:冷藏 3:保鲜
	private Integer column_location; // 柜子所在的列数
	private String cabinet_type; // 柜子种类 0:主柜 1:副贵
	private String description; // 柜子类型描述
	private String create_time; // 创建时间

	public String getLogid() {
		return logid;
	}

	public String getCorp_id() {
		return corp_id;
	}
	@ExcelField(title="柜子配置标识", sort=10)
	public String getCabconfig_id() {
		return cabconfig_id;
	}
	@ExcelField(title="柜子配置名称", sort=20)
	public String getCabconfig_name() {
		return cabconfig_name;
	}
	@ExcelField(title="柜子类型编号", sort=30)
	public String getCabinettype_id() {
		return cabinettype_id;
	}
	@ExcelField(title="柜子类型名称", sort=40)
	public String getCabinettype_name() {
		return cabinettype_name;
	}
	@ExcelField(title="储存类型", sort=50)
	public String getCabinetTemp_type() {
		return cabinetTemp_type;
	}
	@ExcelField(title="所在列数", sort=60)
	public Integer getColumn_location() {
		return column_location;
	}
	@ExcelField(title="柜子种类", sort=70)
	public String getCabinet_type() {
		return cabinet_type;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@ExcelField(title="创建时间", sort=80)
	public String getCreate_time() {
		return create_time;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}

	public void setCabconfig_id(String cabconfig_id) {
		this.cabconfig_id = cabconfig_id;
	}

	public void setCabconfig_name(String cabconfig_name) {
		this.cabconfig_name = cabconfig_name;
	}

	public void setCabinettype_id(String cabinettype_id) {
		this.cabinettype_id = cabinettype_id;
	}

	public void setCabinettype_name(String cabinettype_name) {
		this.cabinettype_name = cabinettype_name;
	}

	public void setCabinetTemp_type(String cabinetTemp_type) {
		this.cabinetTemp_type = cabinetTemp_type;
	}

	public void setColumn_location(Integer column_location) {
		this.column_location = column_location;
	}

	public void setCabinet_type(String cabinet_type) {
		this.cabinet_type = cabinet_type;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
