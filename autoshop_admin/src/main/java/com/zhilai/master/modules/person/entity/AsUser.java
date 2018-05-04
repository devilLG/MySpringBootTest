package com.zhilai.master.modules.person.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * 账户信息Entity
 * 
 * @author guowei
 * 
 */
public class AsUser extends DataEntity<AsUser> {
	private static final long serialVersionUID = 115723358076704876L;
	private String logid; // 记录编号
	private String User_id; // 人员编号
	private String corp_id; // 公司编号
	private String corp_name; // 公司名称
	private String site_id; // 站点编号
	private String site_name; // 站点名称
	private String login_id; // 登录账号
	private String login_name; // 用户名
	private String Pass_word; // 登录密码
	private String encode_type; // 加密方式
	private String salt; // 盐值
	private String Cur_state; // 状态 0: 正常1: 失效
	private String dis_time; // 停用时间
	private String State_time; // 状态时间
	private String create_time; // 创建时间

	public String getLogid() {
		return logid;
	}

	public String getUser_id() {
		return User_id;
	}

	public String getCorp_id() {
		return corp_id;
	}

	public String getCorp_name() {
		return corp_name;
	}

	public String getSite_id() {
		return site_id;
	}

	public String getSite_name() {
		return site_name;
	}

	public String getLogin_id() {
		return login_id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public String getPass_word() {
		return Pass_word;
	}

	public String getEncode_type() {
		return encode_type;
	}

	public String getSalt() {
		return salt;
	}

	public String getCur_state() {
		return Cur_state;
	}

	public String getDis_time() {
		return dis_time;
	}

	public String getState_time() {
		return State_time;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public void setUser_id(String user_id) {
		User_id = user_id;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public void setPass_word(String pass_word) {
		Pass_word = pass_word;
	}

	public void setEncode_type(String encode_type) {
		this.encode_type = encode_type;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setCur_state(String cur_state) {
		Cur_state = cur_state;
	}

	public void setDis_time(String dis_time) {
		this.dis_time = dis_time;
	}

	public void setState_time(String state_time) {
		State_time = state_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
