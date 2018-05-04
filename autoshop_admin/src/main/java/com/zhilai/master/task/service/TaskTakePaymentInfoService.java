package com.zhilai.master.task.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.IdGen;
import com.zhilai.master.modules.pay.dao.PayPaymentInfoDao;
import com.zhilai.master.modules.pay.entity.PayPaymentInfo;
import com.zhilai.master.modules.sys.dao.OfficeDao;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.entity.Office;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.zxml.ClientCallSoap;
import com.zhilai.master.modules.zxml.GetPaymentInfoReq;
import com.zhilai.master.modules.zxml.GetPaymentInfoResp;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName TaskTakePaymentInfoService
 * @Description 获取支付平台订单信息，保存本地以供对账
 * @author GuoXuegan
 * @data 2018年4月25日下午2:23:20
 *
 */
@Service
@Transactional(readOnly=true)
public class TaskTakePaymentInfoService {

	@Autowired
	private PayPaymentInfoDao paymentInfoDao;
	@Autowired
	private OfficeDao officeDao;
	
	@SuppressWarnings("rawtypes")
	@Transactional
	public void execute() {
		String date_stime = DateUtils.AddDayStr(-1); // 统计开始时间
		String date_etime = DateUtils.InitDate();	// 统计结束时间
		String tradeDate = DateUtils.DateString(); // 对账时间
		List<Office> offices = officeDao.findAllCorp(); // 获取所有的公司
		int officeSize = offices.size();
		//List<Dict> orderType = DictUtils.getDictList("order_type"); // 订单类型 2
		List<Dict> payType = DictUtils.getDictList("pay_type");	// 支付类型 3
		List<Dict> orderState = DictUtils.getDictList("order_apply_state"); // 订单状态 n
		//for (Dict ot : orderType) {
			for (Dict pt : payType) {
				for (Dict os : orderState) {
					for (int i = 0; i < officeSize; i++) {
						Office office = offices.get(i);
						GetPaymentInfoReq paymentInfoReq = new GetPaymentInfoReq();
						paymentInfoReq.setBcode("05");
						paymentInfoReq.setTcode("2634");
						paymentInfoReq.setAuth_id(GParameter.zhilaiPay_authId);
						paymentInfoReq.setAuth_name(GParameter.zhilaiPay_authName);
						paymentInfoReq.setCorpId(office.getCorpId());
						paymentInfoReq.setOrderState(os.getValue());
						//paymentInfoReq.setOrderType(ot.getValue());
						paymentInfoReq.setPayType(pt.getValue());
						paymentInfoReq.setsTime(date_stime);
						paymentInfoReq.seteTime(date_etime);
						String req_xml = paymentInfoReq.CreateXml();
						if (req_xml == null) {
							continue;
						}
						String resp_xml = ClientCallSoap.callPay(office.getAuthId(), office.getAuthName(), req_xml);
						if (resp_xml == null) {
							continue;
						}
						JSONObject object = JSONObject.fromObject(resp_xml);
						GetPaymentInfoResp paymentInfoResp = new GetPaymentInfoResp();
						paymentInfoResp.setJsonObject(object);
						Map ret = paymentInfoResp.parseXml(resp_xml);
						if(!paymentInfoResp.getRetcode().equals("0000")){
					    	continue;
					    }
						@SuppressWarnings("unchecked")
						List<PayPaymentInfo> payPaymentInfoList = (List<PayPaymentInfo>) ret.get("payPaymentInfoList");
						if (payPaymentInfoList == null || payPaymentInfoList.size() <= 0) {
							continue;
						}
						// 保存支付平台订单数据
						int size = payPaymentInfoList.size();
						for (int j = 0; j < size; j++) {
							savePaymentInfo(tradeDate, payPaymentInfoList.get(j));
						}
					}
				}
			}
		//}
	}
	
	private void savePaymentInfo(String tradeDate, PayPaymentInfo ppi) {
		ppi.setLogid(IdGen.uuid());
		ppi.setTrade_day(tradeDate);
		ppi.setCur_state(GParameter.accountCheckState_wait);
		ppi.setState_time(DateUtils.DateToString());
		ppi.setCreate_time(DateUtils.DateToString());
		paymentInfoDao.insert(ppi);
	}
}
