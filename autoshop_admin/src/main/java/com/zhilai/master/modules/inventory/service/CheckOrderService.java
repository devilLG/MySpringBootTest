package com.zhilai.master.modules.inventory.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.inventory.dao.CheckOrderDao;
import com.zhilai.master.modules.inventory.entity.CheckOrder;

@Service
@Transactional(readOnly = true)
public class CheckOrderService extends CrudService<CheckOrderDao, CheckOrder> {
	
	@Override
	public CheckOrder get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<CheckOrder> findPage(Page<CheckOrder> page, CheckOrder checkOrder) {
		return super.findPage(page, checkOrder);
	}
	
	@Override
	public List<CheckOrder> findList(CheckOrder checkOrder) {
		return super.findList(checkOrder);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(CheckOrder checkOrder) {
		if (StringUtils.isBlank(checkOrder.getLogid())){
			checkOrder.setCreate_time(DateUtil.getNow());
		}
		super.save(checkOrder);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(CheckOrder checkOrder) {
		super.delete(checkOrder);
	}
}

