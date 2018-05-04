package com.zhilai.master.modules.report.service;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.report.dao.SiteSaleDayDao;
import com.zhilai.master.modules.report.dao.SiteSaleMonthDao;
import com.zhilai.master.modules.report.entity.SiteSaleDay;
import com.zhilai.master.modules.report.entity.SiteSaleMonth;

@Service
@Transactional(readOnly = true)
public class SiteSaleMonthService extends CrudService<SiteSaleMonthDao, SiteSaleMonth> {
	
	@Override
	public SiteSaleMonth get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<SiteSaleMonth> findPage(Page<SiteSaleMonth> page, SiteSaleMonth siteSaleMonth) {
		return super.findPage(page, siteSaleMonth);
	}
}

