package com.zhilai.master.modules.report.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.report.dao.BrandSaleDayDao;
import com.zhilai.master.modules.report.dao.BrandSaleMonthDao;
import com.zhilai.master.modules.report.entity.BrandSaleDay;
import com.zhilai.master.modules.report.entity.BrandSaleMonth;


@Service
@Transactional(readOnly = true)
public class BrandSaleMonthService extends CrudService<BrandSaleMonthDao, BrandSaleMonth> {
	
	@Override
	public BrandSaleMonth get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<BrandSaleMonth> findPage(Page<BrandSaleMonth> page, BrandSaleMonth brandSaleMonth) {
		return super.findPage(page, brandSaleMonth);
	}
}

