package com.zhilai.master.modules.inventory.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.inventory.dao.ReplenishmentOrderDao;
import com.zhilai.master.modules.inventory.entity.ReplenishmentOrder;

@Service
@Transactional(readOnly = true)
public class ReplenishmentOrderService extends CrudService<ReplenishmentOrderDao, ReplenishmentOrder> {
	
	@Override
	public ReplenishmentOrder get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ReplenishmentOrder> findPage(Page<ReplenishmentOrder> page, ReplenishmentOrder replenishmentOrder) {
		return super.findPage(page, replenishmentOrder);
	}
	
	@Override
	public List<ReplenishmentOrder> findList(ReplenishmentOrder replenishmentOrder) {
		return super.findList(replenishmentOrder);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(ReplenishmentOrder replenishmentOrder) {
		if (StringUtils.isBlank(replenishmentOrder.getLogid())){
			replenishmentOrder.setCreate_time(DateUtil.getNow());
		}
		super.save(replenishmentOrder);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(ReplenishmentOrder replenishmentOrder) {
		super.delete(replenishmentOrder);
	}
}

