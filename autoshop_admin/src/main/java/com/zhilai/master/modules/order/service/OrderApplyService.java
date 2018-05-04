package com.zhilai.master.modules.order.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.order.dao.OrderApplyDao;
import com.zhilai.master.modules.order.entity.OrderApply;

@Service
@Transactional(readOnly = true)
public class OrderApplyService extends CrudService<OrderApplyDao, OrderApply> {
	
	@Override
	public OrderApply get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<OrderApply> findPage(Page<OrderApply> page, OrderApply orderApply) {
		return super.findPage(page, orderApply);
	}
	
	@Override
	public List<OrderApply> findList(OrderApply orderApply) {
		return super.findList(orderApply);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(OrderApply orderApply) {
		if (StringUtils.isBlank(orderApply.getLogid())){
			orderApply.setCreate_time(DateUtil.getNow());
		}
		super.save(orderApply);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(OrderApply orderApply) {
		super.delete(orderApply);
	}
}

