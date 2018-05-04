package com.zhilai.master.modules.inventory.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.inventory.dao.BarterOrderDao;
import com.zhilai.master.modules.inventory.entity.BarterOrder;

@Service
@Transactional(readOnly = true)
public class BarterOrderService extends CrudService<BarterOrderDao, BarterOrder> {
	
	@Autowired
	private BarterOrderDao barterOrderDao;
	
	@Override
	public BarterOrder get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<BarterOrder> findPage(Page<BarterOrder> page, BarterOrder barterOrder) {
		return super.findPage(page, barterOrder);
	}
	
	@Override
	public List<BarterOrder> findList(BarterOrder barterOrder) {
		return super.findList(barterOrder);
	}
	
	public List<BarterOrder> findExist(BarterOrder barterOrder) {
		return barterOrderDao.findExist(barterOrder);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(BarterOrder barterOrder) {
		if (StringUtils.isBlank(barterOrder.getLogid())){
			barterOrder.setCreate_time(DateUtil.getNow());
		}
		super.save(barterOrder);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(BarterOrder barterOrder) {
		super.delete(barterOrder);
	}
}

