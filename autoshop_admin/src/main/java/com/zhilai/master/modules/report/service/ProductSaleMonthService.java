package com.zhilai.master.modules.report.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.report.dao.ProductSaleDayDao;
import com.zhilai.master.modules.report.dao.ProductSaleMonthDao;
import com.zhilai.master.modules.report.entity.ProductSaleDay;
import com.zhilai.master.modules.report.entity.ProductSaleMonth;

@Service
@Transactional(readOnly = true)
public class ProductSaleMonthService extends CrudService<ProductSaleMonthDao, ProductSaleMonth> {
	
	@Override
	public ProductSaleMonth get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ProductSaleMonth> findPage(Page<ProductSaleMonth> page, ProductSaleMonth productSaleMonth) {
		return super.findPage(page, productSaleMonth);
	}
}

