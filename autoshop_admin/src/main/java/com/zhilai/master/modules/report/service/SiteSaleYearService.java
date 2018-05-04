package com.zhilai.master.modules.report.service;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.report.dao.SiteSaleDayDao;
import com.zhilai.master.modules.report.dao.SiteSaleYearDao;
import com.zhilai.master.modules.report.entity.SiteSaleDay;
import com.zhilai.master.modules.report.entity.SiteSaleYear;

@Service
@Transactional(readOnly = true)
public class SiteSaleYearService extends CrudService<SiteSaleYearDao, SiteSaleYear> {
	
	@Override
	public SiteSaleYear get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<SiteSaleYear> findPage(Page<SiteSaleYear> page, SiteSaleYear siteSaleYear) {
		return super.findPage(page, siteSaleYear);
	}
	
}

