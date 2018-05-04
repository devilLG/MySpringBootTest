package com.zhilai.master.modules.report.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.report.dao.BrandSaleDayDao;
import com.zhilai.master.modules.report.entity.BrandSaleDay;


@Service
@Transactional(readOnly = true)
public class BrandSaleDayService extends CrudService<BrandSaleDayDao, BrandSaleDay> {
	
	@Override
	public BrandSaleDay get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<BrandSaleDay> findPage(Page<BrandSaleDay> page, BrandSaleDay brandSaleDay) {
		return super.findPage(page, brandSaleDay);
	}
}

