package com.zhilai.master.modules.sys.entity;

/**
 * 智莱云设备在线占比
 * 
 * @author add
 * 
 */
public class SeriesData {
	private String name;
	private int[] data;

	public SeriesData() {
		super();
	}

	public SeriesData(String name, int[] data) {
		super();
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

}
