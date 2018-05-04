package com.zhilai.master.modules.zxml;

import javax.jws.WebService;

@WebService(name = "ApiEntry", targetNamespace = WsConstants.NS)
public interface ApiEntry {
	public String report(String auth_name,String auth_id, String init_xml);
}
