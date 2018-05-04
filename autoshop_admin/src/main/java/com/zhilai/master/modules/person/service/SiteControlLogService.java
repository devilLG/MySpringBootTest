package com.zhilai.master.modules.person.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.person.dao.SiteControlLogDao;
import com.zhilai.master.modules.person.entity.SiteControlLog;

@Service
@Transactional(readOnly = true)
public class SiteControlLogService extends CrudService<SiteControlLogDao, SiteControlLog> {
	
	@Override
	public SiteControlLog get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<SiteControlLog> findPage(Page<SiteControlLog> page, SiteControlLog site) {
		return super.findPage(page, site);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(SiteControlLog site) {
		super.delete(site);
	}
}

