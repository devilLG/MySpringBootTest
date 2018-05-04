package com.zhilai.master.modules.inventory.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.inventory.entity.InventoryGoods;

@MyBatisDao
public interface InventoryGoodsDao extends CrudDao<InventoryGoods> {
//	public List<InventoryGoods> findExist(InventoryGoods inventoryGoods);
	
	public InventoryGoods findExist(InventoryGoods inventoryGoods);
	
	public List<InventoryGoods> findWarn(InventoryGoods inventoryGoods);
	
	public List<InventoryGoods> findWarnByInventoryState(InventoryGoods inventoryGoods);
}
