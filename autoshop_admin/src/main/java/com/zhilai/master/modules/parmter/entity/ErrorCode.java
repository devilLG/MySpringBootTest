package com.zhilai.master.modules.parmter.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * @author guowei 错误码
 * @date 2017-12-26 下午7:03:44
 * 
 */
public class ErrorCode extends DataEntity<ErrorCode> {
	private static final long serialVersionUID = 1080254379586922398L;
	private String logid; // 日志编号 varchar(40)
	private String err_id; // 错误编号 varchar(10)
	private String err_type; // 错误类型 varchar(20)
	private String err_code; // 错误代码 varchar(10)
	private String err_desc; // 错误现象 varchar(200)
	private int err_level; // 错误级别 int0=严重 1=中等 2=一般 3=轻度
	private String err_box;//产生错误代码是否加减可用箱子数量

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getErr_id() {
		return err_id;
	}

	public void setErr_id(String err_id) {
		this.err_id = err_id;
	}

	public String getErr_type() {
		return err_type;
	}

	public void setErr_type(String err_type) {
		this.err_type = err_type;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_desc() {
		return err_desc;
	}

	public void setErr_desc(String err_desc) {
		this.err_desc = err_desc;
	}

	public int getErr_level() {
		return err_level;
	}

	public void setErr_level(int err_level) {
		this.err_level = err_level;
	}

	public String getErr_box() {
		return err_box;
	}

	public void setErr_box(String err_box) {
		this.err_box = err_box;
	}

}
