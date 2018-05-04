package com.zhilai.master.modules.sys.entity;

/**
 * 字典Entity
 * @author zhilai
 * @version 2017-08-04
 */
public class AreaData {
	
	private String y;;
	private double item1;
	private int item2;
	public AreaData(){}
	public AreaData(String title,double d,int item1){
		this.y=title;
		this.item1=d;
		this.item2=item1;
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
	public int getItem2() {
		return item2;
	}
	public void setItem2(int item2) {
		this.item2 = item2;
	}
 
	
	
}