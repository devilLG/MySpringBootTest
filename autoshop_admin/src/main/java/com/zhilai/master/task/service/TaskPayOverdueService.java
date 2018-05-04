package com.zhilai.master.task.service;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.putils.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.modules.order.dao.OrderApplyDao;
import com.zhilai.master.modules.order.dao.OrderPayDao;
import com.zhilai.master.modules.order.entity.OrderApply;
import com.zhilai.master.modules.order.entity.OrderPay;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.zxml.ClientCallSoap;
import com.zhilai.master.modules.zxml.OrderCommonResp;
import com.zhilai.master.modules.zxml.OrderRefundReq;

@Service
@Transactional(readOnly = true)
public class TaskPayOverdueService {
	private static Logger log = Logger.getLogger(TaskPayOverdueService.class);
	@Autowired
	private OrderPayDao orderPayDao;
	@Autowired
	private OrderApplyDao orderApplyDao;

	@Transactional(readOnly = false)
	public String execute(Date date) {
		String retCode = "0";
		String push_num = DictUtils.getDictValue("payOverduePush_num", "payOverduePush", "3");
		try {
			String pay_overtime = DateUtil.getNow();
			List<OrderPay> orderPayList = orderPayDao.findPayOverdueList(pay_overtime);
			if(orderPayList != null && orderPayList.size() > 0){
				for(OrderPay orderPay : orderPayList){
					boolean result = true;
					String error_cont = "";
					OrderApply orderApply = new OrderApply();
					orderApply.setOrder_id(orderPay.getOrder_id());
					orderApply.setSale_total(orderPay.getSale_total());
					orderApply.setPay_state(GParameter.payState_invalid);
					orderApply.setOutTradeNo(orderPay.getOutTradeNo());
					try {
						OrderRefundReq orderRefundReq = new OrderRefundReq();
						orderRefundReq.setBcode("04");
						orderRefundReq.setTcode("1006");
						orderRefundReq.setAuth_name(ClientCallSoap.getAuthName());
						orderRefundReq.setAuth_id(ClientCallSoap.getAuthId());
						orderRefundReq.setOrderApply(orderApply);
						String req_xml = orderRefundReq.CreateXml();
						String resp_xml = ClientCallSoap.callPartner(ClientCallSoap.getAuthName(), ClientCallSoap.getAuthId(), req_xml);
					    JSONObject object = JSONObject.fromObject(resp_xml);
					    OrderCommonResp orderCommonResp = new OrderCommonResp();
					    orderCommonResp.setJsonObject(object);
					    String tips = orderCommonResp.parseXml();
						if(orderCommonResp.getRetcode().equals("0000")){
							error_cont = "设置支付超期成功";
						}else if(orderCommonResp.getRetcode().equals("1619")){
							error_cont = "支付超期已设置";
						}else{
							result = false;
							error_cont = "服务器返回错误码" + orderCommonResp.getRetcode() + "错误描述：" + orderCommonResp.getRetmsg();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
						result = false;
						error_cont = "服务器请求失败";
					}
					if(result){
						orderPay.setPay_state(GParameter.payState_invalid);
						orderPay.setPush_num(orderPay.getPush_num() + 1);
						orderPay.setPush_type(GParameter.pushType_success);
						orderPay.setError_cont(error_cont + "; " + orderPay.getError_cont());
						orderPayDao.update(orderPay);
					}else{
						orderPay.setPush_num(orderPay.getPush_num() + 1);
						if(orderPay.getPush_num() >= Integer.parseInt(push_num)){
							orderApply.setAbnormalType(GParameter.abnormalType_setPayOverFailed);
							orderApplyDao.updateAbnormalType(orderApply);
							orderPay.setPush_type(GParameter.pushType_failed);
						}
						orderPay.setError_cont(error_cont + "; " + orderPay.getError_cont());
						orderPayDao.update(orderPay);
					}
				}
			
			}
		} catch (Exception e) {
			log.error("TaskPayOverdueService is fail");
			e.printStackTrace();
		}
		return retCode;
	}
	
}
