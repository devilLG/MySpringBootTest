package com.zhilai.master.modules.utils;

import java.util.List;

public class Zbody {
	private String auth_id;//	是 Yes	String	30	授权码 Authorization code
	private String auth_name;//	是 Yes	String	50	授权名 Authorization name
	private String site_id;//	是 Yes	String	30	地点编号
	private String site_name;//	是 Yes	String	50	地点名称
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private List<Zissued> zmsg;

	public List<Zissued> getZmsg() {
		return zmsg;
	}

	public void setZmsg(List<Zissued> msg) {
		this.zmsg = msg;
	}

	public String getAuth_id() {
		return auth_id;
	}

	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}

	public String getAuth_name() {
		return auth_name;
	}

	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	
	
	
}
