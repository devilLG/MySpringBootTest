package com.zhilai.master.modules.site.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * @author guowei 站点信息
 * @date 2017-12-7 上午10:01:31
 * 
 */
public class AsSite extends DataEntity<AsSite> {
	private static final long serialVersionUID = 1080254379586922398L;
	private String logid;// 逻辑编号
	private String tree_id;// 区域编号
	private String tree_name;// 区域名称
	private String owner_id;// 站点归属公司编号
	private String owner_name;// 站点归属公司名称
	private String site_id;// 地点编号
	private String site_code;// 地点代码
	private String site_name;// 地点简称
	private String fullname;// 地点全称
	private String auth_name;// 授权名
	private String auth_id;// 授权编号
	private String latitude;// 经度
	private String longitude;// 纬度
	private String addr;// 详细地址
	private String searcher;// 特征信息
	private String site_state;// 状态 0:在线 1：离线 2:不可用
	private String service_type;// 服务状态 1:正常服务 2:停用服务 3:停购买服务
	private String state_time;// 状态时间
	private String comm_type;// 通讯方式 1:udp 2:http 3:soap 4:demo
	private String is_replenishment;// 是否需要补货 0:否 1:是
	private String boxlack_num;// 缺货箱格数临界值
	private String prolack_num;// 产品少于百分比临界值
	private String warn_type;// 警报类型 01:无 02:机械故障 03:货道故障04:缺货 05:未知
	private String warn_cont;// 警报内容
	private String warn_time;// 警报时间
	private String cur_temperature;// 当前温度(冷藏区)
	private String config_stemperature;// 标准最低温度
	private String config_etemperature;// 标准最高温度
	private String temperature_state;// 温度状态 1:正常 2:低过 3:超过
	private String down_state;// 下发状态0:未下发 1:已下发 2:已确认
	private String cabinet_type;// 柜子类型
	private String cabinet_type_name;// 柜子类型名称
	private String down_time;// 下发更新时间
	private String create_time;// 创建时间

	private String cabinetStandard_id;//配置标识
	private String cabinetStandard_name;
	private String warn_state;//警报状态 1:正常 2:报警
	
	private String corp_id;
	
	
	public String getCabinetStandard_id() {
		return cabinetStandard_id;
	}

	public String getCabinetStandard_name() {
		return cabinetStandard_name;
	}

	public String getWarn_state() {
		return warn_state;
	}

	public void setWarn_state(String warn_state) {
		this.warn_state = warn_state;
	}

	public void setCabinetStandard_id(String cabinetStandard_id) {
		this.cabinetStandard_id = cabinetStandard_id;
	}

	public void setCabinetStandard_name(String cabinetStandard_name) {
		this.cabinetStandard_name = cabinetStandard_name;
	}

	public String getCabinet_type() {
		return cabinet_type;
	}

	public String getCabinet_type_name() {
		return cabinet_type_name;
	}

	public void setCabinet_type(String cabinet_type) {
		this.cabinet_type = cabinet_type;
	}

	public void setCabinet_type_name(String cabinet_type_name) {
		this.cabinet_type_name = cabinet_type_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@ExcelField(title="区域名称", sort=30)
	public String getTree_name() {
		return tree_name;
	}

	public void setTree_name(String tree_name) {
		this.tree_name = tree_name;
	}

	public String getLogid() {
		return logid;
	}

	public String getTree_id() {
		return tree_id;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public String getOwner_name() {
		return owner_name;
	}
	@ExcelField(title="站点编号", sort=10)
	public String getSite_id() {
		return site_id;
	}

	public String getSite_code() {
		return site_code;
	}
	@ExcelField(title="站点简称", sort=20)
	public String getSite_name() {
		return site_name;
	}

	public String getFullname() {
		return fullname;
	}

	public String getAuth_name() {
		return auth_name;
	}

	public String getAuth_id() {
		return auth_id;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getAddr() {
		return addr;
	}

	public String getSearcher() {
		return searcher;
	}
	@ExcelField(title="在线状态", sort=70, dictType="site_state")
	public String getSite_state() {
		return site_state;
	}

	public String getService_type() {
		return service_type;
	}

	public String getState_time() {
		return state_time;
	}

	public String getComm_type() {
		return comm_type;
	}
	@ExcelField(title="是否需要补货", sort=65, dictType="is_replenishment")
	public String getIs_replenishment() {
		return is_replenishment;
	}

	public String getBoxlack_num() {
		return boxlack_num;
	}

	public String getProlack_num() {
		return prolack_num;
	}
	@ExcelField(title="警报类型", sort=55, dictType="warn_type")
	public String getWarn_type() {
		return warn_type;
	}
	@ExcelField(title="警报内容", sort=60)
	public String getWarn_cont() {
		return warn_cont;
	}

	public String getWarn_time() {
		return warn_time;
	}
	@ExcelField(title="当前温度(℃)", sort=40)
	public String getCur_temperature() {
		return cur_temperature;
	}

	public String getConfig_stemperature() {
		return config_stemperature;
	}

	public String getConfig_etemperature() {
		return config_etemperature;
	}
	@ExcelField(title="温度状态", sort=50, dictType="temperature_state")
	public String getTemperature_state() {
		return temperature_state;
	}

	public String getDown_state() {
		return down_state;
	}

	public String getDown_time() {
		return down_time;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public void setTree_id(String tree_id) {
		this.tree_id = tree_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public void setSite_code(String site_code) {
		this.site_code = site_code;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}

	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setSearcher(String searcher) {
		this.searcher = searcher;
	}

	public void setSite_state(String site_state) {
		this.site_state = site_state;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	public void setState_time(String state_time) {
		this.state_time = state_time;
	}

	public void setComm_type(String comm_type) {
		this.comm_type = comm_type;
	}

	public void setIs_replenishment(String is_replenishment) {
		this.is_replenishment = is_replenishment;
	}

	public void setBoxlack_num(String boxlack_num) {
		this.boxlack_num = boxlack_num;
	}

	public void setProlack_num(String prolack_num) {
		this.prolack_num = prolack_num;
	}

	public void setWarn_type(String warn_type) {
		this.warn_type = warn_type;
	}

	public void setWarn_cont(String warn_cont) {
		this.warn_cont = warn_cont;
	}

	public void setWarn_time(String warn_time) {
		this.warn_time = warn_time;
	}

	public void setCur_temperature(String cur_temperature) {
		this.cur_temperature = cur_temperature;
	}

	public void setConfig_stemperature(String config_stemperature) {
		this.config_stemperature = config_stemperature;
	}

	public void setConfig_etemperature(String config_etemperature) {
		this.config_etemperature = config_etemperature;
	}

	public void setTemperature_state(String temperature_state) {
		this.temperature_state = temperature_state;
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

	public String getCorp_id() {
		return corp_id;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}

}
