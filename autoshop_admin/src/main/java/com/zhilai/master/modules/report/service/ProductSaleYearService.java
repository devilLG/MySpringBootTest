package com.zhilai.master.modules.report.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.report.dao.ProductSaleDayDao;
import com.zhilai.master.modules.report.dao.ProductSaleMonthDao;
import com.zhilai.master.modules.report.dao.ProductSaleYearDao;
import com.zhilai.master.modules.report.entity.ProductSaleDay;
import com.zhilai.master.modules.report.entity.ProductSaleMonth;
import com.zhilai.master.modules.report.entity.ProductSaleYear;

@Service
@Transactional(readOnly = true)
public class ProductSaleYearService extends CrudService<ProductSaleYearDao, ProductSaleYear> {
	
	@Override
	public ProductSaleYear get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ProductSaleYear> findPage(Page<ProductSaleYear> page, ProductSaleYear productSaleYear) {
		return super.findPage(page, productSaleYear);
	}
}

