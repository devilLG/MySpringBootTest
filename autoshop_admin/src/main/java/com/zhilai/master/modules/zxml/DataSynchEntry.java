package com.zhilai.master.modules.zxml;

import javax.jws.WebService;

@WebService(name = "DataSynchEntry", targetNamespace = WsConstants.NS)
public interface DataSynchEntry {
	public String report(String auth_name,String auth_id, String report_xml);
}
