package com.zhilai.master.modules.inventory.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.inventory.dao.WarehouseInfoDao;
import com.zhilai.master.modules.inventory.entity.WarehouseInfo;

@Service
@Transactional(readOnly = true)
public class WarehouseInfoService extends CrudService<WarehouseInfoDao, WarehouseInfo> {
	
	@Override
	public WarehouseInfo get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<WarehouseInfo> findPage(Page<WarehouseInfo> page, WarehouseInfo warehouseInfo) {
		return super.findPage(page, warehouseInfo);
	}
	
	@Override
	public List<WarehouseInfo> findList(WarehouseInfo warehouseInfo) {
		return super.findList(warehouseInfo);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(WarehouseInfo warehouseInfo) {
		if (StringUtils.isBlank(warehouseInfo.getLogid())){
			warehouseInfo.setCreate_time(DateUtil.getNow());
		}
		super.save(warehouseInfo);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(WarehouseInfo warehouseInfo) {
		super.delete(warehouseInfo);
	}
}

