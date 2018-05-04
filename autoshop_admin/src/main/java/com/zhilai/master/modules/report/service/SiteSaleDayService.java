package com.zhilai.master.modules.report.service;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;

import com.zhilai.master.modules.report.dao.SiteSaleDayDao;
import com.zhilai.master.modules.report.entity.SiteSaleDay;

@Service
@Transactional(readOnly = true)
public class SiteSaleDayService extends CrudService<SiteSaleDayDao, SiteSaleDay> {
	
	@Override
	public SiteSaleDay get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<SiteSaleDay> findPage(Page<SiteSaleDay> page, SiteSaleDay siteSaleDay) {
		return super.findPage(page, siteSaleDay);
	}
}

