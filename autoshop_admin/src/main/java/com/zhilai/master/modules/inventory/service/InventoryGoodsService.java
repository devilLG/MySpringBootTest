package com.zhilai.master.modules.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.inventory.dao.InventoryGoodsDao;
import com.zhilai.master.modules.inventory.entity.InventoryGoods;

@Service
@Transactional(readOnly = true)
public class InventoryGoodsService extends CrudService<InventoryGoodsDao, InventoryGoods> {
	
	@Autowired
	private InventoryGoodsDao inventoryGoodsDao;
	
	@Override
	public InventoryGoods get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<InventoryGoods> findPage(Page<InventoryGoods> page, InventoryGoods inventoryGoods) {
		return super.findPage(page, inventoryGoods);
	}
	
	@Override
	public List<InventoryGoods> findList(InventoryGoods inventoryGoods) {
		return super.findList(inventoryGoods);
	}
	
//	public List<InventoryGoods> findExist(InventoryGoods inventoryGoods) {
//		return inventoryGoodsDao.findExist(inventoryGoods);
//	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(InventoryGoods inventoryGoods) {
		super.save(inventoryGoods);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(InventoryGoods inventoryGoods) {
		super.delete(inventoryGoods);
	}
}

