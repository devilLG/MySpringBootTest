package com.zhilai.master.modules.report.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.report.dao.ProductSaleDayDao;
import com.zhilai.master.modules.report.entity.ProductSaleDay;

@Service
@Transactional(readOnly = true)
public class ProductSaleDayService extends CrudService<ProductSaleDayDao, ProductSaleDay> {
	
	@Override
	public ProductSaleDay get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ProductSaleDay> findPage(Page<ProductSaleDay> page, ProductSaleDay productSaleDay) {
		return super.findPage(page, productSaleDay);
	}
}

