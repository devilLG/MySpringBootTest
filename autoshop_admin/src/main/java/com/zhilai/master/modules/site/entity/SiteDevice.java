package com.zhilai.master.modules.site.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * @author guowei
 * 地点柜子配置   配置每个地点下的柜子
 * @date 2017-12-7 上午10:23:16
 * 
 */
public class SiteDevice extends DataEntity<SiteDevice> {
	private static final long serialVersionUID = 3107247789734490155L;
	private String logid;//逻辑编号
	private String cabinet_seq;//柜子编号
	private String site_id;//地点编号引用ns-site表中的site_id
	private String site_name;//地点名称
	private String owner_id;//站点归属公司编号
	private Integer cabinet_location;//柜子位置
	private String cabinet_type;//柜子类型
	private String cabinet_batch_num;//
	
	private String bind_state;//绑定状态，0-有效 1-无效
	private String create_time;//创建时间
	
	
	private String cabinettype_id;//柜子类型编号
	private String cabinettype_name;//柜子类型简称
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCabinettype_id() {
		return cabinettype_id;
	}

	public String getCabinettype_name() {
		return cabinettype_name;
	}

	public void setCabinettype_id(String cabinettype_id) {
		this.cabinettype_id = cabinettype_id;
	}

	public void setCabinettype_name(String cabinettype_name) {
		this.cabinettype_name = cabinettype_name;
	}

	public String getCabinet_seq() {
		return cabinet_seq;
	}

	public void setCabinet_seq(String cabinet_seq) {
		this.cabinet_seq = cabinet_seq;
	}
	public String getLogid() {
		return logid;
	}
	@ExcelField(title="站点编号", sort=10)
	public String getSite_id() {
		return site_id;
	}
	@ExcelField(title="站点名称", sort=20)
	public String getSite_name() {
		return site_name;
	}
	
	public String getOwner_id() {
		return owner_id;
	}
	@ExcelField(title="排列位置", sort=40)
	public Integer getCabinet_location() {
		return cabinet_location;
	}
	@ExcelField(title="柜型", sort=50)
	public String getCabinet_type() {
		return cabinet_type;
	}
	@ExcelField(title="绑定状态", sort=60, dictType="bind_state")
	public String getBind_state() {
		return bind_state;
	}
	@ExcelField(title="创建时间", sort=70)
	public String getCreate_time() {
		return create_time;
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
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public void setCabinet_location(Integer cabinet_location) {
		this.cabinet_location = cabinet_location;
	}
	public void setCabinet_type(String cabinet_type) {
		this.cabinet_type = cabinet_type;
	}
	public void setBind_state(String bind_state) {
		this.bind_state = bind_state;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCabinet_batch_num() {
		return cabinet_batch_num;
	}

	public void setCabinet_batch_num(String cabinet_batch_num) {
		this.cabinet_batch_num = cabinet_batch_num;
	}
}
