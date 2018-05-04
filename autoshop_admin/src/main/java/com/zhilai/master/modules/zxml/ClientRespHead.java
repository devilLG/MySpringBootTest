package com.zhilai.master.modules.zxml;


import net.sf.json.JSONObject;

import org.dom4j.Document;

abstract public class ClientRespHead {
	private String charSet="utf-8";
	protected  String retcode;
	protected String retmsg;
	protected int totnum;
	protected int curnum;
	protected JSONObject jsonObject;
	protected Document document;
	
	protected  int parseHead(){
	  if(null==jsonObject) return -1;
	  JSONObject jObject = jsonObject.getJSONObject("ZMSG").getJSONObject("ZHEAD");
      retcode = jObject.getString("RetCode");
      retmsg= jObject.getString("RetMsg");
      totnum=Integer.parseInt(jObject.getString("TotNum"));
      curnum=Integer.parseInt(jObject.getString("CurNum"));
	  if(!retcode.equals("0000"))  return -1;
	  return 0;
	}
	abstract public String parseXml( );
	
	public String getCharSet() {
		return charSet;
	}
	public void setCharSet(String charSet) {
		this.charSet = charSet;
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
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	

}
