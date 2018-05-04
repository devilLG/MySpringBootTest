package com.zhilai.master.modules.report.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.report.dao.BrandSaleDayDao;
import com.zhilai.master.modules.report.dao.BrandSaleMonthDao;
import com.zhilai.master.modules.report.dao.BrandSaleYearDao;
import com.zhilai.master.modules.report.entity.BrandSaleDay;
import com.zhilai.master.modules.report.entity.BrandSaleMonth;
import com.zhilai.master.modules.report.entity.BrandSaleYear;


@Service
@Transactional(readOnly = true)
public class BrandSaleYearService extends CrudService<BrandSaleYearDao, BrandSaleYear> {
	
	@Override
	public BrandSaleYear get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<BrandSaleYear> findPage(Page<BrandSaleYear> page, BrandSaleYear brandSaleYear) {
		return super.findPage(page, brandSaleYear);
	}
}

