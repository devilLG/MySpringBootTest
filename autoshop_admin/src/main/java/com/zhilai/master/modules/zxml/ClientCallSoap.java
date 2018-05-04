package com.zhilai.master.modules.zxml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.putils.service.ServiceException;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.zhilai.master.modules.sys.utils.DictUtils;

public class ClientCallSoap {
	private final static Log log = LogFactory.getLog(ClientCallSoap.class);
	
	public static String callPartner(String auth_name,String auth_id, String req_xml){
		log.debug("auth_name:"+auth_name+"  auth_id:"+auth_id);
		log.debug("req_xml:"+req_xml);
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		String webServiceUrl = DictUtils.getDictValue("WebServiceUrlInfo_url", "WebServiceUrlInfo", "http://192.168.1.203:8182/auto_server/cxf/soap");
		String apiUrl = webServiceUrl + "/apiService";
		factory.setAddress(apiUrl);
		factory.setServiceClass(ApiEntry.class);
		ApiEntry service = (ApiEntry) factory.create();
		String result = service.report(auth_name, auth_id, req_xml);
		return result;
	}
	
	public static String callDataSynch(String auth_name,String auth_id, String req_xml){
		log.debug("auth_name:"+auth_name+"  auth_id:"+auth_id);
		log.debug("req_xml:"+req_xml);
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		String webServiceUrl = DictUtils.getDictValue("WebServiceUrlInfo_url", "WebServiceUrlInfo", "http://192.168.1.203:8182/auto_server/cxf/soap");
		String dataSynchUrl = webServiceUrl + "/dataSynchService";
		factory.setAddress(dataSynchUrl);
		factory.setServiceClass(DataSynchEntry.class);
		DataSynchEntry service = (DataSynchEntry) factory.create();
		String result = service.report(auth_name, auth_id, req_xml);
		return result;
	}
	
	public static String callDelivery(String auth_name,String auth_id, String req_xml){
		log.debug("auth_name:"+auth_name+"  auth_id:"+auth_id);
		log.debug("req_xml:"+req_xml);
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		String webServiceUrl = DictUtils.getDictValue("WebServiceUrlInfo_url", "WebServiceUrlInfo", "http://192.168.1.203:8182/auto_server/cxf/soap");
		String deliveryUrl = webServiceUrl + "/deliveryService";
		factory.setAddress(deliveryUrl);
		factory.setServiceClass(DeliveryEntry.class);
		DeliveryEntry service = (DeliveryEntry) factory.create();
		String result = service.report(auth_name, auth_id, req_xml);
		return result;
	}
	
	public static String callPay(String auth_name,String auth_id, String req_xml){
		log.debug("auth_name:"+auth_name+"  auth_id:"+auth_id);
		log.debug("req_xml:"+req_xml);
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		String payUrl = DictUtils.getDictValue("payUrl_local", "payUrl", "http://114.215.185.2:8086/ZhilaiPayPlat/cxf/soap/payService");
		factory.setAddress(payUrl);
		factory.setServiceClass(IPay.class);
		IPay service = (IPay) factory.create();
		String result = service.payApply(auth_name, auth_id, req_xml);
		return result;
	}
	
	public String sendMsg(String auth_name, String auth_id, String req_xml) {
		log.debug("auth_name:" + auth_name + "  auth_id:" + auth_id);
		log.debug("req_xml:" + req_xml);
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			String webServiceUrl = DictUtils.getDictValue("WebServiceUrlInfo_msgSendUrl", "WebServiceUrlInfo", "http://112.124.21.88:9999/nsplat/cxf/soap/messagerService");
			factory.setAddress(webServiceUrl);
			factory.setServiceClass(WebsiteService.class);
			WebsiteService service = (WebsiteService) factory.create();
			String result = service.apply(auth_name, auth_id, req_xml);
			if (null != result)
				return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException("callApply is error, e");
		}
	return null;
	}
	
	public static String getAuthId(){
		return DictUtils.getDictValue("WebServiceAuthInfo_authId", "WebServiceAuthInfo", "zhilai_web_2018");
	}
	
	public static String getAuthName(){
		return DictUtils.getDictValue("WebServiceAuthInfo_authName", "WebServiceAuthInfo", "zhilai_web");
	}
	
}
