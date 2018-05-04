package com.zhilai.master.modules.site.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * @author guowei 货道信息表
 * @date 2017-12-7 上午10:23:16
 * 
 */
public class SiteGoods extends DataEntity<SiteGoods> {
	private static final long serialVersionUID = 3107247789734490155L;
	private String logid;// 逻辑编号
	private String goods_id;// 编号
	private String corp_id;// 司编号(业主)
	private String corp_name;// 司名称(业主)
	private String owner_id;// 站点归属公司编号
	private String site_id;// 站点ID引用as_site表的site_id
	private String site_name;// 站点名称
	private Integer cabinet_id;// 柜号
	private Integer cloumn_id;// 列数
	private Integer layer_num;// 层数
	private String box_id;// 货道号
	private String box_type;// 货道类型
	private String temper_type;// 温度环境类型 1:常温 2:冷藏 3:保鲜
	private String state_time;// 状态时间
	private String cur_state;// 货道状态 1:正常 2:故障 3:停用
	private String down_state;// 下发状态0:未下发 1:已下发 2:已确认
	private String down_time;// 下发更新时间
	private String create_time;// 创建时间
	
	//虚拟字段
	private String[] box_ids;	

	public String getLogid() {
		return logid;
	}
	
	public String getGoods_id() {
		return goods_id;
	}
	
	public String getCorp_id() {
		return corp_id;
	}
	
	public String getCorp_name() {
		return corp_name;
	}

	public String getOwner_id() {
		return owner_id;
	}
	@ExcelField(title="站点编号", sort=30)
	public String getSite_id() {
		return site_id;
	}
	@ExcelField(title="站点简称", sort=35)
	public String getSite_name() {
		return site_name;
	}
	@ExcelField(title="柜号", sort=40)
	public Integer getCabinet_id() {
		return cabinet_id;
	}
	@ExcelField(title="列数", sort=45)
	public Integer getCloumn_id() {
		return cloumn_id;
	}
	@ExcelField(title="层数", sort=50)
	public Integer getLayer_num() {
		return layer_num;
	}
	@ExcelField(title="货道号", sort=55)
	public String getBox_id() {
		return box_id;
	}
	@ExcelField(title="货道类型", sort=60)
	public String getBox_type() {
		return box_type;
	}
	@ExcelField(title="温度环境", sort=67, dictType="cabinetTemp_type")
	public String getTemper_type() {
		return temper_type;
	}
	@ExcelField(title="状态时间", sort=70)
	public String getState_time() {
		return state_time;
	}
	@ExcelField(title="货道状态", sort=65, dictType="cur_state")
	public String getCur_state() {
		return cur_state;
	}
	
	public String getDown_state() {
		return down_state;
	}
	
	public String getDown_time() {
		return down_time;
	}
	@ExcelField(title="刷新时间", sort=70)
	public String getCreate_time() {
		return create_time;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public void setCabinet_id(Integer cabinet_id) {
		this.cabinet_id = cabinet_id;
	}

	public void setCloumn_id(Integer cloumn_id) {
		this.cloumn_id = cloumn_id;
	}

	public void setLayer_num(Integer layer_num) {
		this.layer_num = layer_num;
	}

	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}

	public void setBox_type(String box_type) {
		this.box_type = box_type;
	}

	public void setTemper_type(String temper_type) {
		this.temper_type = temper_type;
	}

	public void setState_time(String state_time) {
		this.state_time = state_time;
	}

	public void setCur_state(String cur_state) {
		this.cur_state = cur_state;
	}

	public void setDown_state(String down_state) {
		this.down_state = down_state;
	}

	public void setDown_time(String down_time) {
		this.down_time = down_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String[] getBox_ids() {
		return box_ids;
	}

	public void setBox_ids(String[] box_ids) {
		this.box_ids = box_ids;
	}

}
