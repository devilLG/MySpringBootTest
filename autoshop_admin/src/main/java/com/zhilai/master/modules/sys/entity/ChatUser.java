package com.zhilai.master.modules.sys.entity;

/**
 * 微信或小程序用户Entity
 * @author zhiwu.zheng
 * @version 2017-11-15
 */
public class ChatUser{
	private String user_no;
	private String loginId;
	private String login_name;
	private String passwd;
	private String site_id;
	private String site_name;
	private String encrypt_type;
	private String salt;
	private String corp_id;
	private String corp_name;
	private String mobile;
	private String email;
	private String reg_time;
	private String state;
	private String disTime;
	private String rolename;
	private String person_info;
	private String openid;
	private String boxpwd;
	private String remark;
	/**开箱密码*/
	public String getBoxpwd() {
		return boxpwd;
	}
	/**开箱密码*/
	public void setBoxpwd(String boxpwd) {
		this.boxpwd = boxpwd;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String login_id) {
		this.loginId = login_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String password) {
		this.passwd = password;
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
	public String getEncrypt_type() {
		return encrypt_type;
	}
	public void setEncrypt_type(String encrypt_type) {
		this.encrypt_type = encrypt_type;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getCorp_id() {
		return corp_id;
	}
	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	public String getCorp_name() {
		return corp_name;
	}
	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}
	public String getState() {
		return state;
	}
	public void setState(String sate) {
		this.state = sate;
	}
	public String getDisTime() {
		return disTime;
	}
	public void setDisTime(String dis_time) {
		this.disTime = dis_time;
	}
	/**角色0000admin0001customer*/
	public String getRolename() {
		if(null==rolename||(!"0000".equals(rolename)&&!"0001".equals(rolename))){
			return "0001";
		}
		return rolename;
	}
	/**角色0000admin0001customer*/
	public void setRolename(String role_info) {
		this.rolename = role_info;
	}
	public String getPerson_info() {
		return person_info;
	}
	public void setPerson_info(String person_info) {
		this.person_info = person_info;
	}
	
}