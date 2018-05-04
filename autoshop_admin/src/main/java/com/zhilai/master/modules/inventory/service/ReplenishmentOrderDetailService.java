package com.zhilai.master.modules.inventory.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.inventory.dao.ReplenishmentOrderDetailDao;
import com.zhilai.master.modules.inventory.entity.ReplenishmentOrderDetail;

@Service
@Transactional(readOnly = true)
public class ReplenishmentOrderDetailService extends CrudService<ReplenishmentOrderDetailDao, ReplenishmentOrderDetail> {
	
	@Override
	public ReplenishmentOrderDetail get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ReplenishmentOrderDetail> findPage(Page<ReplenishmentOrderDetail> page, ReplenishmentOrderDetail replenishmentOrderDetail) {
		return super.findPage(page, replenishmentOrderDetail);
	}
	
	@Override
	public List<ReplenishmentOrderDetail> findList(ReplenishmentOrderDetail replenishmentOrderDetail) {
		return super.findList(replenishmentOrderDetail);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(ReplenishmentOrderDetail replenishmentOrderDetail) {
		if (StringUtils.isBlank(replenishmentOrderDetail.getLogid())){
			replenishmentOrderDetail.setCreate_time(DateUtil.getNow());
		}
		super.save(replenishmentOrderDetail);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(ReplenishmentOrderDetail replenishmentOrderDetail) {
		super.delete(replenishmentOrderDetail);
	}
}

