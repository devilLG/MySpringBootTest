package com.zhilai.master.modules.inventory.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.inventory.dao.InventoryProductDao;
import com.zhilai.master.modules.inventory.entity.InventoryProduct;

@Service
@Transactional(readOnly = true)
public class InventoryProductService extends CrudService<InventoryProductDao, InventoryProduct> {
	
	@Autowired
	private InventoryProductDao inventoryProductDao;
	
	@Override
	public InventoryProduct get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<InventoryProduct> findPage(Page<InventoryProduct> page, InventoryProduct inventoryProduct) {
		return super.findPage(page, inventoryProduct);
	}
	
	@Override
	public List<InventoryProduct> findList(InventoryProduct inventoryProduct) {
		return super.findList(inventoryProduct);
	}
	
	public InventoryProduct findExist(InventoryProduct inventoryProduct) {
		return inventoryProductDao.findExist(inventoryProduct);
	}
	
	public List<InventoryProduct> findProductByCorpId(InventoryProduct inventoryProduct) {
		return inventoryProductDao.findProductByCorpId(inventoryProduct);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(InventoryProduct inventoryProduct) {
		if (StringUtils.isBlank(inventoryProduct.getLogid())){
			inventoryProduct.setCreate_time(DateUtil.getNow());
		}
		super.save(inventoryProduct);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(InventoryProduct inventoryProduct) {
		super.delete(inventoryProduct);
	}
}

