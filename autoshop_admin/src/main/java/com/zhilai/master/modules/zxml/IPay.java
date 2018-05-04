package com.zhilai.master.modules.zxml;
import javax.jws.WebService;

@WebService(name = "IPayService", targetNamespace = WsConstants.NS)
public interface IPay {
	public String payApply(String auth_name,String auth_id, String message_xml);
}
 