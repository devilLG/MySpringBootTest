package com.zhilai.master.modules.inventory.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.inventory.entity.InventoryProduct;

@MyBatisDao
public interface InventoryProductDao extends CrudDao<InventoryProduct> {
	public InventoryProduct findExist(InventoryProduct inventoryProduct);
	
	public List<InventoryProduct> findProductByCorpId(InventoryProduct inventoryProduct);
}
