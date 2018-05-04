package com.zhilai.master.modules.sys.entity;

import com.zhilai.master.common.persistence.DataEntity;

public class SequenceId extends DataEntity<SequenceId> {
	private static final long serialVersionUID = -8814159625316869266L;
	private int id;
	private String corp_id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCorp_id() {
		return corp_id;
	}
	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
  
}
