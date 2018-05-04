package com.zhilai.master.modules.zxml;


import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 短信发送
 * @author zzw
 *
 */
public class MessageSendResp{
	private final static Log log = LogFactory.getLog(MessageSendResp.class);
	private String charSet="utf-8";
	private  String retcode;
	private String retmsg;
	private int totnum;
	private int curnum;
	private String xml;
	private Document document;
	
	public  int parseHead(){
	  if(null==xml) return -1;
	  try {
	   document=DocumentHelper.parseText(xml); 
	   Element root = document.getRootElement();
	   Iterator iter = root.elementIterator("ZHEAD"); 
	   while (iter.hasNext()) {
		   Element recordE = (Element) iter.next();
		   retcode = recordE.elementTextTrim("retcode");
		   retmsg=recordE.elementTextTrim("retmsg");
		   totnum=Integer.parseInt(recordE.elementTextTrim("totnum"));
		   curnum=Integer.parseInt(recordE.elementTextTrim("curnum"));
	    }
	   } catch (DocumentException e) {
		   e.printStackTrace();
		  }
	  if(!retcode.equals("0000"))  return -1;
	  return 0;
	}
	
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
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}

}
