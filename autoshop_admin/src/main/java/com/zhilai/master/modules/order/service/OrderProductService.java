package com.zhilai.master.modules.order.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.order.dao.OrderProductDao;
import com.zhilai.master.modules.order.entity.OrderProduct;

@Service
@Transactional(readOnly = true)
public class OrderProductService extends CrudService<OrderProductDao, OrderProduct> {
	
	@Override
	public OrderProduct get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<OrderProduct> findPage(Page<OrderProduct> page, OrderProduct orderProduct) {
		return super.findPage(page, orderProduct);
	}
	
	@Override
	public List<OrderProduct> findList(OrderProduct orderProduct) {
		return super.findList(orderProduct);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(OrderProduct orderProduct) {
		if (StringUtils.isBlank(orderProduct.getLogid())){
			orderProduct.setCreate_time(DateUtil.getNow());
		}
		super.save(orderProduct);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(OrderProduct orderProduct) {
		super.delete(orderProduct);
	}
}

