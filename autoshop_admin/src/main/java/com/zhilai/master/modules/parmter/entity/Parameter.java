package com.zhilai.master.modules.parmter.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * @author guowei 参数
 * @date 2017-12-26 下午7:03:44
 * 
 */
public class Parameter extends DataEntity<Parameter> {
	private static final long serialVersionUID = 1080254379586922398L;
	private String logid;// 日志编号 varchar(36)
	private String para_type;// 参数类型 varchar(20)
	private String name;// 参数名 varchar(40)
	private String value;// 参数值 varchar(80)
	private String description;// 参数描述 varchar(100)

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getPara_type() {
		return para_type;
	}

	public void setPara_type(String para_type) {
		this.para_type = para_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
