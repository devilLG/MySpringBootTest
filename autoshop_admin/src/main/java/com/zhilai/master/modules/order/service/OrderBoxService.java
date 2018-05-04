package com.zhilai.master.modules.order.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.order.dao.OrderBoxDao;
import com.zhilai.master.modules.order.entity.OrderBox;

@Service
@Transactional(readOnly = true)
public class OrderBoxService extends CrudService<OrderBoxDao, OrderBox> {
	
	@Override
	public OrderBox get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<OrderBox> findPage(Page<OrderBox> page, OrderBox orderBox) {
		return super.findPage(page, orderBox);
	}
	
	@Override
	public List<OrderBox> findList(OrderBox orderBox) {
		return super.findList(orderBox);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(OrderBox orderBox) {
		if (StringUtils.isBlank(orderBox.getLogid())){
			orderBox.setCreate_time(DateUtil.getNow());
		}
		super.save(orderBox);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(OrderBox orderBox) {
		super.delete(orderBox);
	}
}

