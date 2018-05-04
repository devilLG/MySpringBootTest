package com.zhilai.master.task.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.IdGen;
import com.zhilai.master.modules.pay.dao.PayHandleDao;
import com.zhilai.master.modules.pay.dao.PayManageInfoDao;
import com.zhilai.master.modules.pay.dao.PayPaymentInfoDao;
import com.zhilai.master.modules.pay.dao.PayResultDao;
import com.zhilai.master.modules.pay.entity.PayHandle;
import com.zhilai.master.modules.pay.entity.PayManageInfo;
import com.zhilai.master.modules.pay.entity.PayPaymentInfo;
import com.zhilai.master.modules.pay.entity.PayResult;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 * @ClassName TaskAccountCheckService
 * @Description 对账处理，默认对账昨天的账单信息
 * @author GuoXuegan
 * @data 2018年4月17日上午9:21:49
 *
 */
@Service
@Transactional(readOnly = true)
public class TaskAccountCheckService {

	private Map<String, PayResult> payResultMap;
	Logger log = Logger.getLogger(TaskAccountCheckService.class);
	
	@Autowired
	private PayManageInfoDao payManageInfoDao;
	@Autowired
	private PayPaymentInfoDao payPaymentInfoDao;
	@Autowired
	private PayHandleDao payHandleDao;
	@Autowired
	private PayResultDao payResultDao;
	
	@Transactional
	public void execute() {
		log.info("Begin to check account.");
		payResultMap = new HashMap<String, PayResult>();
		String date = DateUtils.AddDays(-1);
		// check by manager
		checkByManager(date);
		// check by payment
		checkByPayment(date);
		
		//	记录对账结果
		this.takeResult();		
		log.info("Checking over!");
	}

	/**
	 * 
	 * @param date 
	 * @Title checkByManager
	 * @Description 从管理台对账支付平台
	 * @Author GuoXuegan
	 * @data 2018年4月17日上午9:27:29
	 *
	 */
	private void checkByManager(String date) {
		PayManageInfo payManageInfo = new PayManageInfo();
		payManageInfo.setTrade_day(date);
		payManageInfo.setCur_state("1");
		List<PayManageInfo> payManageInfos = payManageInfoDao.findList(payManageInfo);
		for (PayManageInfo pmi : payManageInfos) {
			boolean handle = false; // 默认对账正确
			double diffSum = 0.0;// 差异金额
			PayPaymentInfo ppi = payPaymentInfoDao.get(pmi.getTrade_number());
			if (ppi == null) {
				pmi.setCur_state(GParameter.accountCheckState_lack);
				handle = true;
				diffSum = Math.abs(pmi.getTrade_sum());
			}else {
				pmi.setCur_state(GParameter.accountCheckState_finish);
				String tips = checking(pmi, ppi);
				if (tips != null) {
					handle = true;
					if (tips.equals("win")) {
						tips = "管理台较支付平台总金额多。";
						pmi.setCur_state(GParameter.accountCheckState_win);
					}else if (tips.equals("loss")) {
						tips = "管理台较支付平台总金额少。";
						pmi.setCur_state(GParameter.accountCheckState_loss);
					}
					diffSum = Math.abs(pmi.getTrade_sum() - ppi.getTrade_sum());
					
					//	如果当前未插入异常，则新插入
					PayHandle payHandle = payHandleDao.get(pmi.getTrade_number());
					if (payHandle == null) {
						takeHandle(pmi, ppi, tips);
					}
				}
			}
			pmi.setState_time(DateUtils.getDateTime());			
			payManageInfoDao.update(pmi);
			// 记录对账结果
			this.checkResult(pmi, ppi, date, handle, diffSum);
		}
	}

	/**
	 * 
	 * @param date 
	 * @Title checkByPayment
	 * @Description 从支付平台对账管理台
	 * @Author GuoXuegan
	 * @data 2018年4月17日上午9:27:52
	 *
	 */
	private void checkByPayment(String date) {
		PayPaymentInfo paymentInfo = new PayPaymentInfo();
		paymentInfo.setTrade_day(date);
		paymentInfo.setCur_state("1");
		List<PayPaymentInfo> payPaymentInfos = payPaymentInfoDao.findList(paymentInfo);
		for (PayPaymentInfo ppi : payPaymentInfos) {
			PayManageInfo pmi = payManageInfoDao.get(ppi.getTrade_number());
			if (pmi == null) {
				ppi.setCur_state(GParameter.accountCheckState_lack);
			}else {
				ppi.setCur_state(GParameter.accountCheckState_finish);
				String tips = checking(pmi, ppi);
				if (tips != null) {
					if (tips.equals("loss")) {
						tips = "管理台较支付平台总金额多。";
						ppi.setCur_state(GParameter.accountCheckState_win);
					}else if (tips.equals("win")) {
						tips = "管理台较支付平台总金额少。";
						ppi.setCur_state(GParameter.accountCheckState_loss);
					}
					//	如果未插入异常则插入
					PayHandle payHandle = payHandleDao.get(ppi.getTrade_number());
					if (payHandle == null) {
						takeHandle(pmi, ppi, tips);
					}
				}
			}
			ppi.setState_time(DateUtils.getDateTime());			
			payPaymentInfoDao.update(ppi);
		}
	}
	
	/**
	 * 
	 * @Title checking
	 * @Description 数据比对
	 * @Author GuoXuegan
	 * @data 2018年4月17日上午11:02:36
	 * @param pmi
	 * @param ppi
	 * @return
	 *
	 */
	private String checking(PayManageInfo pmi, PayPaymentInfo ppi) {
		/**
		 * 1. 订单编号
		 * 2. 币种
		 * 3. 交易金额
		 * 4. 支付状态
		 */
		if (!pmi.getOrder_id().equals(ppi.getOrder_id())) {
			return "交易号"+pmi.getTrade_number()+"订单编号不一致";
		}
		if (!pmi.getCurrency().equals(ppi.getCurrency())) {
			return "交易号"+pmi.getTrade_number()+"币种不一致";
		}
		/*if (pmi.getTradeSum() != ppi.getTradeSum()) {
			return "交易号"+pmi.getTradeNumber()+"交易总金额不一致";
		}*/
		if (pmi.getTrade_sum() < ppi.getTrade_sum()) {
			// 管理台账赢，支付平台账亏
			return "win";
		}
		if (pmi.getTrade_sum() > ppi.getTrade_sum()) {
			// 管理台账亏，支付平台账赢
			return "loss";
		}
		if (!pmi.getPay_state().equals(ppi.getPay_state())) {
			return "交易号"+pmi.getTrade_number()+"支付状态不一致";
		}
		return null;
	}
	
	/**
	 * 
	 * @Title takeHandle
	 * @Description 记录对账异常
	 * @Author GuoXuegan
	 * @data 2018年4月17日下午2:19:04
	 * @param pmi
	 * @param ppi
	 * @param reason 异常原因
	 *
	 */
	private void takeHandle(PayManageInfo pmi, PayPaymentInfo ppi, String reason) {
		PayHandle ph = new PayHandle();
		ph.setLogid(IdGen.uuid());
		ph.setError_id(System.nanoTime()+"");
		ph.setTrade_day(pmi.getTrade_day());
		ph.setCorp_id(pmi.getCorp_id());
		ph.setCorp_name(pmi.getCorp_name());
		ph.setPay_id(ppi.getPay_id());
		ph.setPay_channel(pmi.getPay_channel());
		ph.setFee_sum(ppi.getFee_sum());
		ph.setCurrency(pmi.getCurrency());
		ph.setTrade_number(pmi.getTrade_number());
		ph.setTrade_time(pmi.getTrade_time());
		ph.setOrder_id(pmi.getOrder_id());
		ph.setSite_id(ppi.getSite_id());
		ph.setSite_name(ppi.getSite_name());
		ph.setManage_money(pmi.getTrade_sum()+"");
		ph.setManage_state(pmi.getPay_state());
		ph.setPayment_money(ppi.getTrade_sum()+"");
		ph.setPayment_state(ppi.getPay_state());
		ph.setReason_info(reason);
		ph.setProc_status(GParameter.procStatus_wait);
		ph.setNotice_flag(GParameter.yon_no);
		ph.setCreate_time(DateUtils.getDateTime());
		payHandleDao.insert(ph);
	}

	// 插入对账结果
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void takeResult() {
		Iterator iterator = payResultMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, PayResult> object = (Entry<String, PayResult>) iterator.next();
			PayResult payResult = object.getValue();
			payResultDao.insert(payResult);
			
		}
	}

	// 检查并记录当前对账结果
	private void checkResult(PayManageInfo pmi, PayPaymentInfo ppi, String date, boolean handle, double diffSum) {
		PayResult pr = payResultMap.get(pmi.getCorp_id()+pmi.getCurrency());
		if (pr == null) {
			pr = new PayResult();
			pr.setLogid(IdGen.uuid());
			pr.setTrade_day(date);
			pr.setCorp_id(pmi.getCorp_id());
			pr.setCorp_name(pmi.getCorp_name());
			pr.setCurrency(pmi.getCurrency());
			pr.setCur_state("1");
			pr.setCreate_time(DateUtils.DateToString());
		}
		if (handle) {
			// 对账异常
			pr.setRecord_num(pr.getRecord_num()+1);
			pr.setFee_sum(pr.getFee_sum()+pmi.getTrade_sum());
			pr.setError_num(pr.getError_num()+1);
			pr.setDiff_sum(pr.getDiff_sum()+diffSum);
			pr.setCur_state("2");
		}else {
			pr.setRecord_num(pr.getRecord_num()+1);
			pr.setFee_sum(pr.getFee_sum()+pmi.getTrade_sum());
			pr.setCorrect_num(pr.getCorrect_num()+1);
			pr.setCorrect_fee(pr.getCorrect_fee()+pmi.getTrade_sum());
		}
		payResultMap.put(pmi.getCorp_id()+pmi.getCurrency(), pr);		
	}
}
