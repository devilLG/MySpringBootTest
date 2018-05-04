package com.zhilai.master.modules.order.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.order.dao.OrderChangeDao;
import com.zhilai.master.modules.order.entity.OrderChange;

@Service
@Transactional(readOnly = true)
public class OrderChangeService extends CrudService<OrderChangeDao, OrderChange> {
	
	@Override
	public OrderChange get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<OrderChange> findPage(Page<OrderChange> page, OrderChange orderChange) {
		return super.findPage(page, orderChange);
	}
	
	@Override
	public List<OrderChange> findList(OrderChange orderChange) {
		return super.findList(orderChange);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(OrderChange orderChange) {
		if (StringUtils.isBlank(orderChange.getLogid())){
			orderChange.setCreate_time(DateUtil.getNow());
		}
		super.save(orderChange);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(OrderChange orderChange) {
		super.delete(orderChange);
	}
}

