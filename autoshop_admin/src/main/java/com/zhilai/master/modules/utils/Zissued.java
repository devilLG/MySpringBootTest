package com.zhilai.master.modules.utils;

public class Zissued {
	private String login_id;//	是 Yes	String	30	账户编号
	private String password;//	是 Yes	String	70	密码 password
	private String encrypt_type;//	是 Yes	String	2	加密类型 00:none 01:3des 02:aes 03:rsa
	private String Encryption;// type 1: clear text  2:3des  3:aes
	private String salt;//	是 Yes	String	32	加密盐值 Encrypted salt value
	private String issued_id;//		是 Yes	String	30	下发号 
	private String person_info;//		是 Yes	String	15	未下发人员信息
	private String r_state;//	01：确认成功 02确认失败 03未确认04无效单号 05:已确认
	private String role_name;//		是 Yes	String	50	角色名称
	private String plat_code;//		是 Yes	String	30	平台代码
	private String plat_version;//		是 Yes	String	30	平台版本
	private String upgrade_id;//		是 Yes	String	10	升级标识
	private String upgrade_type;//		是 Yes	String	2	更新类型
	private String role_info;
		
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncrypt_type() {
		return encrypt_type;
	}
	public void setEncrypt_type(String encrypt_type) {
		this.encrypt_type = encrypt_type;
	}
	public String getEncryption() {
		return Encryption;
	}
	public void setEncryption(String encryption) {
		Encryption = encryption;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getIssued_id() {
		return issued_id;
	}
	public void setIssued_id(String issued_id) {
		this.issued_id = issued_id;
	}
	public String getPerson_info() {
		return person_info;
	}
	public void setPerson_info(String person_info) {
		this.person_info = person_info;
	}
	public String getR_state() {
		return r_state;
	}
	public void setR_state(String r_state) {
		this.r_state = r_state;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getPlat_code() {
		return plat_code;
	}
	public void setPlat_code(String plat_code) {
		this.plat_code = plat_code;
	}
	public String getPlat_version() {
		return plat_version;
	}
	public void setPlat_version(String plat_version) {
		this.plat_version = plat_version;
	}
	public String getUpgrade_id() {
		return upgrade_id;
	}
	public void setUpgrade_id(String upgrade_id) {
		this.upgrade_id = upgrade_id;
	}
	public String getUpgrade_type() {
		return upgrade_type;
	}
	public void setUpgrade_type(String upgrade_type) {
		this.upgrade_type = upgrade_type;
	}
	public String getRole_info() {
		return role_info;
	}
	public void setRole_info(String role_info) {
		this.role_info = role_info;
	}
}
