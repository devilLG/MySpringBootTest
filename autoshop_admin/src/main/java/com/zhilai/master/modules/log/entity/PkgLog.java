package com.zhilai.master.modules.log.entity;
import com.zhilai.master.common.persistence.DataEntity;

/**
 * @author guowei 交易报文日志
 * @date 2017-12-26 下午7:03:44
 * 
 */
public class PkgLog extends DataEntity<PkgLog> {
	private static final long serialVersionUID = 1080254379586922398L;
	private String log_id;// 日志编号 varchar(36)
	private String auth_name;// 授权用户 VARCHAR(20)
	private String plat_code;// 平台代码 VARCHAR(10)
	private String trade_code;// 交易代码 VARCHAR(10)
	//表中没有trade_name字段
	private String trade_name;// 交易简称 VARCHAR(30)
	private String trade_time;// 开始时间 VARCHAR(24)
	private String pkg_up;// 包标识 VARCHAR(2048)
	private String pkg_down;// 包内容 VARCHAR(10240)
	private String protocol;// 协议 VARCHAR(1)
	
	
	private String trade_time_end;// 结束时间 VARCHAR(24)


	public String getLog_id() {
		return log_id;
	}


	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}


	public String getAuth_name() {
		return auth_name;
	}


	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}


	public String getPlat_code() {
		return plat_code;
	}


	public void setPlat_code(String plat_code) {
		this.plat_code = plat_code;
	}


	public String getTrade_code() {
		return trade_code;
	}


	public void setTrade_code(String trade_code) {
		this.trade_code = trade_code;
	}


	public String getTrade_name() {
		return trade_name;
	}


	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}


	public String getTrade_time() {
		return trade_time;
	}


	public void setTrade_time(String trade_time) {
		this.trade_time = trade_time;
	}


	public String getPkg_up() {
		return pkg_up;
	}


	public void setPkg_up(String pkg_up) {
		this.pkg_up = pkg_up;
	}


	public String getPkg_down() {
		return pkg_down;
	}


	public void setPkg_down(String pkg_down) {
		this.pkg_down = pkg_down;
	}


	public String getProtocol() {
		return protocol;
	}


	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}


	public String getTrade_time_end() {
		return trade_time_end;
	}


	public void setTrade_time_end(String trade_time_end) {
		this.trade_time_end = trade_time_end;
	}
	
	

	

}
