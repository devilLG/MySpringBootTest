package com.zhilai.master.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.IdGen;
import com.zhilai.master.modules.order.dao.OrderApplyDao;
import com.zhilai.master.modules.order.entity.OrderApply;
import com.zhilai.master.modules.pay.dao.PayManageInfoDao;
import com.zhilai.master.modules.pay.entity.PayManageInfo;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 * @ClassName TaskTakePayManageInfoService
 * @Description 记录管理台订单账单
 * 				从订单表获取然后插入管理台平台账单表，今天记录昨天的账单，对账日期设为今天
 * @author GuoXuegan
 * @data 2018年4月25日上午9:49:42
 * 
 */
@Service
@Transactional(readOnly=true)
public class TaskTakePayManageInfoService {

	@Autowired
	private OrderApplyDao orderApplyDao;
	@Autowired
	private PayManageInfoDao payManageInfoDao;
	
	@Transactional
	public void execute() {
		String date = DateUtils.AddDays(-1); // 以昨日基准获取昨日创建的订单
		String tradeDay = DateUtils.DateString();
		OrderApply orderApply = new OrderApply();
		orderApply.setCreate_time(date);
		List<OrderApply> orderApplies = orderApplyDao.findList(orderApply);
		for (OrderApply oa : orderApplies) {
			PayManageInfo pmi = new PayManageInfo();
			pmi.setLogid(IdGen.uuid());
			pmi.setTrade_day(tradeDay);
			pmi.setCorp_id(oa.getCorp_id());
			pmi.setCorp_name(oa.getCorp_name());
			pmi.setOrder_id(oa.getOrder_id());
			pmi.setPay_channel(oa.getPay_type());
			pmi.setTrade_number(oa.getOutTradeNo());
			pmi.setTrade_time(oa.getCreate_time());
			pmi.setTrade_sum(oa.getSale_total());
			pmi.setCurrency("01");
			pmi.setLogin_id(oa.getLogin_id());
			pmi.setLogin_name(oa.getLogin_name());
			pmi.setPay_state(oa.getPay_state());
			pmi.setCur_state(GParameter.accountCheckState_wait);
			pmi.setState_time(DateUtils.DateToString());
			pmi.setCreate_time(DateUtils.DateToString());
			payManageInfoDao.insert(pmi);
		}
	}
}
