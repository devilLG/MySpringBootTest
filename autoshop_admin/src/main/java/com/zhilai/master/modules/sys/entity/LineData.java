package com.zhilai.master.modules.sys.entity;

/**
 * 字典Entity
 * @author zhilai
 * @version 2017-08-04
 */
public class LineData {
	
	private String y;;
	private double item1;
	public LineData(){}
	public LineData(String title,double d){
		this.y=title;
		this.item1=d;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public double getItem1() {
		return item1;
	}
	public void setItem1(int item1) {
		this.item1 = item1;
	}
 
	
	
}