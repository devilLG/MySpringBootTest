/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.standard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;

import com.zhilai.master.modules.standard.dao.CabinetAttrDao;
import com.zhilai.master.modules.standard.entity.CabinetAttr;

/**
 * 站点Service
 * @author zhilai
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class CabinetAttrService extends CrudService<CabinetAttrDao, CabinetAttr> {
	@Autowired
	private CabinetAttrDao cabinetAttrDao;

	public Page<CabinetAttr> findPage(Page<CabinetAttr> page, CabinetAttr cab) {
		cab.setPage(page);
		page.setList(super.findList(cab));
		return page;
		
		//return super.findPage(page, site);
	}

	@Transactional(readOnly = false)
	public void save(CabinetAttr cab) {
		String id = cab.getAttr_id();
		CabinetAttr site = super.get(id);
		if (site == null) {
			cab.preInsert();
			cabinetAttrDao.insert(cab);
		} else {
			// 更新数据
			cab.preUpdate();
			cabinetAttrDao.update(cab);
		}
		//super.save(site);
	}
	
	@Transactional(readOnly = false)
	public void delete(CabinetAttr box) {
		super.delete(box);
	}
	
}
