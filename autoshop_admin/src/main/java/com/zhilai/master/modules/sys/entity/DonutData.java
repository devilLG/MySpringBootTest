package com.zhilai.master.modules.sys.entity;

/**
 * 字典Entity Sales饼图
 * @author add
 *
 */
public class DonutData {
	private String label;
	private int value;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public DonutData(String label, int value) {
		this.label = label;
		this.value = value;
	}
	public DonutData() {
		
	}

}
