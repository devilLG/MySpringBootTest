package com.zhilai.master.modules.utils;

public class Zhead {
	//req
	private String charSet="utf-8";
	private String bcode="";//03
	private String tcode="";//1609
	private String auth_name;
	private String auth_id;
	private int istart=1;
	private int iend=1;
	private int iflag=1;
	//resp
	private String retcode;
	private String retmsg;
	private int totnum;
	private int curnum;
	
	public String getCharSet() {
		return charSet;
	}
	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	public String getBcode() {
		return bcode;
	}
	public void setBcode(String bcode) {
		this.bcode = bcode;
	}
	/***业务编号,如1609**/
	public String getTcode() {
		return tcode;
	}
	/***业务编号,如1609**/
	public void setTcode(String tcode) {
		this.tcode = tcode;
	}
	public String getAuth_name() {
		return auth_name;
	}
	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}
	public String getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}
	public int getIstart() {
		return istart;
	}
	public void setIstart(int istart) {
		this.istart = istart;
	}
	public int getIend() {
		return iend;
	}
	public void setIend(int iend) {
		this.iend = iend;
	}
	public int getIflag() {
		return iflag;
	}
	public void setIflag(int iflag) {
		this.iflag = iflag;
	}
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getRetmsg() {
		return retmsg;
	}
	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}
	public int getTotnum() {
		return totnum;
	}
	public void setTotnum(int totnum) {
		this.totnum = totnum;
	}
	public int getCurnum() {
		return curnum;
	}
	public void setCurnum(int curnum) {
		this.curnum = curnum;
	}
}
