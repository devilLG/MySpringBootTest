package com.zhilai.master.modules.notice.entity;	import com.zhilai.master.common.persistence.DataEntity;import com.zhilai.master.common.utils.DateUtils;import com.zhilai.master.common.utils.excel.annotation.ExcelField;/** *  *Title:NoticeTemplate *Description:公共通知模板实体类 *@author ChengJiawei *@date 2018年4月20日 上午10:33:35 * */public class NoticeTemplate extends DataEntity<NoticeTemplate> {		private static final long serialVersionUID = 1L;		private String logid;	//	记录编号	private String temp_id;	//	模板编号	private String temp_name;	//	模板名称	private String main_type;	//	业务类型 01:销售订单 02:站点监控 03:工单服务 04:站点库存 05:站点补货 06:站点换货 07:站点盘点 08:人员注册以及更新 09:对账单  	private String sub_type;	//	业务二级类型 各种订单状态，用后缀表示 比如业务订单的已锁定状态是0102	private String notice_type;	//	通知类型 0:短信系统 1:邮件系统 2:核心管理台 3:补货app 4:微信公众号 5:支付系统	private String role_id;	//	角色编号	private String role_name;	//	角色名称	private int send_num;	//	发送最大次数(如果失败)	private String cur_state;	//	状态 0:正常 1:失效	private String interval_time;	//	同种业务如果通知后未处理 则可指定次时间允许多次通知，例如客户未取业务 第一次通知后 在规定的时间内未取可配置第二期通知，如第一次通知0小时则立即通知 第二次配置3小时则在客户3小时未取 则发起第二次通知	private String content;	//	模板内容	private String description;	//	描述	private String level;	//	发送等级 数字  1为最高等级 5:最低等级 默认等级 3	private String create_time;	//	创建时间		public String getLogid() {		return logid;	}	public void setLogid(String logid) {		this.logid = logid == null ? null : logid.trim();	}	public String getTemp_id() {		return temp_id;	}	public void setTemp_id(String temp_id) {		this.temp_id = temp_id;	}	@ExcelField(title="模板名称", sort=1)	public String getTemp_name() {		return temp_name;	}	public void setTemp_name(String temp_name) {		this.temp_name = temp_name;	}	@ExcelField(title="业务类型", sort=2 , dictType="mainType")	public String getMain_type() {		return main_type;	}	public void setMain_type(String main_type) {		this.main_type = main_type;	}	public String getSub_type() {		return sub_type;	}	public void setSub_type(String sub_type) {		this.sub_type = sub_type;	}	@ExcelField(title="通知类型", sort=3 , dictType="noticeType")	public String getNotice_type() {		return notice_type;	}	public void setNotice_type(String notice_type) {		this.notice_type = notice_type;	}	public String getRole_id() {		return role_id;	}	public void setRole_id(String role_id) {		this.role_id = role_id;	}	@ExcelField(title="角色名称", sort=4)	public String getRole_name() {		return role_name;	}	public void setRole_name(String role_name) {		this.role_name = role_name;	}	@ExcelField(title="发送最大次数", sort=5)	public int getSend_num() {		return send_num;	}	public void setSend_num(int send_num) {		this.send_num = send_num;	}	@ExcelField(title="状态", sort=6 , dictType="Cur_state")	public String getCur_state() {		return cur_state;	}	public void setCur_state(String cur_state) {		this.cur_state = cur_state;	}	@ExcelField(title="通知间隔(H)", sort=7)	public String getInterval_time() {		return interval_time;	}	public void setInterval_time(String interval_time) {		this.interval_time = interval_time;	}	@ExcelField(title="模板内容", sort=8)	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}	@ExcelField(title="描述", sort=9)	public String getDescription() {		return description;	}	public void setDescription(String description) {		this.description = description;	}	@ExcelField(title="发送等级", sort=10)	public String getLevel() {		return level;	}	public void setLevel(String level) {		this.level = level;	}	@ExcelField(title="创建时间", sort=11)	public String getCreate_time() {		return create_time;	}	public void setCreate_time(String create_time) {		this.create_time = create_time;	}	public static long getSerialversionuid() {		return serialVersionUID;	}			/**	 * 插入之前执行方法，需要手动调用	 */	@Override	public void preInsert(){		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID		if (!this.isNewRecord){			setLogid(System.nanoTime()+"");			setTemp_id(System.nanoTime()+"");		}		this.create_time = DateUtils.getDateTime();	}		}