package com.zhilai.master.modules.notice.entity;	import com.zhilai.master.common.persistence.DataEntity;import com.zhilai.master.common.utils.DateUtils;import com.zhilai.master.common.utils.excel.annotation.ExcelField;import com.zhilai.master.modules.sys.utils.UserUtils;public class NoticeConfig extends DataEntity<NoticeConfig> {		private static final long serialVersionUID = 1L;		private String logid;	//	记录编号	private String corp_id;	//	公司编号	private String corp_name;	//	公司名称	private String config_type;	//	配置类型 1:公共 2:运营商	private String notice_type;	//	通知类型 0:短信系统 1:邮件系统 2:核心管理台 3:补货app 4:微信公众号 5:支付系统	private String notice_channel;	//	 通知渠道 1:HTTP	private String notice_address;	//	通知地址	private String cur_state;	//	状态 1:启用 2:失效	private String create_time;	//	创建时间		public String getLogid() {		return logid;	}	public void setLogid(String logid) {		this.logid = logid == null ? null : logid.trim();	}	public String getCorp_id() {		return corp_id;	}	public void setCorp_id(String corp_id) {		this.corp_id = corp_id;	}	@ExcelField(title="公司名称", sort=1)	public String getCorp_name() {		return corp_name;	}	public void setCorp_name(String corp_name) {		this.corp_name = corp_name;	}	@ExcelField(title="配置类型", sort=2, dictType="config_type")	public String getConfig_type() {		return config_type;	}	public void setConfig_type(String config_type) {		this.config_type = config_type;	}	@ExcelField(title="通知类型", sort=3 , dictType="noticeType")	public String getNotice_type() {		return notice_type;	}	public void setNotice_type(String notice_type) {		this.notice_type = notice_type;	}	@ExcelField(title="通知方式", sort=4 , dictType="notice_channel")	public String getNotice_channel() {		return notice_channel;	}	public void setNotice_channel(String notice_channel) {		this.notice_channel = notice_channel;	}	@ExcelField(title="通知地址", sort=5)	public String getNotice_address() {		return notice_address;	}	public void setNotice_address(String notice_address) {		this.notice_address = notice_address;	}	@ExcelField(title="状态", sort=6 , dictType="Cur_state")	public String getCur_state() {		return cur_state;	}	public void setCur_state(String cur_state) {		this.cur_state = cur_state;	}	@ExcelField(title="创建时间", sort=7)	public String getCreate_time() {		return create_time;	}	public void setCreate_time(String create_time) {		this.create_time = create_time;	}	public static long getSerialversionuid() {		return serialVersionUID;	}		/**	 * 插入之前执行方法，需要手动调用	 */	@Override	public void preInsert(){		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID		if (!this.isNewRecord){			setLogid(System.nanoTime()+"");			setCorp_id(UserUtils.getUser().getCorpId());			setCorp_name(UserUtils.getUser().getCompanyName());		}		this.create_time = DateUtils.getDateTime();	}	}