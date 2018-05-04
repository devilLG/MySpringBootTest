package com.zhilai.master.modules.zxml;

import javax.jws.WebService;

@WebService(name = "WebsiteService", targetNamespace ="http://post.lmxf.com")
public interface WebsiteService {
	public String apply(String auth_name,String auth_id, String apply_xml);

}
