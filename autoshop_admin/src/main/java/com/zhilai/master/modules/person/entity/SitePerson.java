 package com.zhilai.master.modules.person.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 站点人员Entity
 * 
 * @author guowei
 * 
 */
public class SitePerson extends DataEntity<SitePerson> {
	private static final long serialVersionUID = 115723358076704876L;
	private String logid; // 记录编号
	private String config_id; // 记录编号
	private String corp_id; // 公司编号
	private String corp_name; // 公司名称
	private String site_id; // 站点编号
	private String site_name; // 站点名称
	private String login_id; // 登录账号
	private String login_name; // 用户名
	private String mobile; // 电话号
	private String email; // 邮箱
	private String Emp_type; // 人员类型 1:客户 2:商品维护员3:终端管理员
	private String Pass_word; // 登录密码
	private String encode_type; // 加密方式
	private String salt; // 盐值
	private String Cur_state; // 状态 0: 正常1: 失效
	private String dis_time; // 停用时间
	private String down_state; // 下发状态 0:需要下发 1:已下发 2:已确认
	private String down_time; // 下发时间
	private String create_time; // 创建时间
	private String notice_state; // 通知状态 1:待通知 2：已通知 3：不必通知

	public String getLogid() {
		return logid;
	}
	@ExcelField(title="手机号", sort=60)
	public String getMobile() {
		return mobile;
	}
	@ExcelField(title="邮箱地址", sort=70)
	public String getEmail() {
		return email;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCorp_id() {
		return corp_id;
	}

	public String getConfig_id() {
		return config_id;
	}

	public void setConfig_id(String config_id) {
		this.config_id = config_id;
	}

	public String getCorp_name() {
		return corp_name;
	}
	@ExcelField(title="站点编号", sort=40)
	public String getSite_id() {
		return site_id;
	}
	@ExcelField(title="站点名称", sort=50)
	public String getSite_name() {
		return site_name;
	}
	@ExcelField(title="帐户编号", sort=10)
	public String getLogin_id() {
		return login_id;
	}
	@ExcelField(title="用户名称", sort=20)
	public String getLogin_name() {
		return login_name;
	}
	@ExcelField(title="人员类型", sort=30, dictType="Emp_type")
	public String getEmp_type() {
		return Emp_type;
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
	@ExcelField(title="状态", sort=72, dictType="Cur_state")
	public String getCur_state() {
		return Cur_state;
	}

	public String getDis_time() {
		return dis_time;
	}
	@ExcelField(title="下发状态", sort=75, dictType="down_state")
	public String getDown_state() {
		return down_state;
	}
	@ExcelField(title="下发时间", sort=80)
	public String getDown_time() {
		return down_time;
	}
	@ExcelField(title="创建时间", sort=90)
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

	public void setEmp_type(String emp_type) {
		Emp_type = emp_type;
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

	public void setDown_state(String down_state) {
		this.down_state = down_state;
	}

	public void setDown_time(String down_time) {
		this.down_time = down_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getNotice_state() {
		return notice_state;
	}
	public void setNotice_state(String notice_state) {
		this.notice_state = notice_state;
	}

}
