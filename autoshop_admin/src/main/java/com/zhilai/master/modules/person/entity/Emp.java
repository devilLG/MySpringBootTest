package com.zhilai.master.modules.person.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * 人员信息Entity
 * 
 * @author guowei
 * 
 */
public class Emp extends DataEntity<Emp> {
	private static final long serialVersionUID = 115723358076704876L;
	private String logid; // 记录编号
	private String corp_id; // 公司编号
	private String corp_name; // 公司名称
	private String site_id; // 站点编号
	private String site_name; // 站点名称
	private String login_id; // 登录账号
	private String login_name; // 用户名
	private String gender; // 性别 0：女 1:男
	private String mobile; // 手机号
	private String email; // 邮件地址
	private String parent_id; // 父级公司编号
	private String parent_name; // 父级公司名称
	private String Pass_word; // 登录密码
	private String encode_type; // 加密方式
	private String salt; // 盐值
	private String Descrption; // 描述
	private String create_time; // 创建时间

	public String getLogid() {
		return logid;
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

	public String getGender() {
		return gender;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public String getParent_id() {
		return parent_id;
	}

	public String getParent_name() {
		return parent_name;
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

	public String getDescrption() {
		return Descrption;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setLogid(String logid) {
		this.logid = logid;
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

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
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

	public void setDescrption(String descrption) {
		Descrption = descrption;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
