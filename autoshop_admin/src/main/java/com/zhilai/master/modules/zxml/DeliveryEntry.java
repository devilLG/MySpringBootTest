package com.zhilai.master.modules.zxml;

import javax.jws.WebService;

@WebService(name = "DeliveryEntry", targetNamespace = WsConstants.NS)
public interface DeliveryEntry {
	public String report(String auth_name,String auth_id, String termianl_xml);
}
