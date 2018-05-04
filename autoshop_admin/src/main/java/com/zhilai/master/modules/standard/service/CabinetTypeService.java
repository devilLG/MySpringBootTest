/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.standard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.standard.dao.CabinetTypeDao;
import com.zhilai.master.modules.standard.entity.CabinetType;
/**
 * 站点Service
 * @author zhilai
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class CabinetTypeService extends CrudService<CabinetTypeDao, CabinetType> {
	@Autowired
	private CabinetTypeDao cabinetTypeDao;
	public Page<CabinetType> findPage(Page<CabinetType> page, CabinetType cabinetType) {
		cabinetType.setPage(page);
		page.setList(super.findList(cabinetType));
		return page;
		
		//return super.findPage(page, site);
	}

	@Transactional(readOnly = false)
	public void save(CabinetType cabinetType) {
		if (StringUtils.isBlank(cabinetType.getLogid())) {
			cabinetType.preInsert();
			
			//cabinetStandard.setCreate_time(DateUtil.getNow());
			//site.setSite_id(IdGen.uuid());//应该递增，后续更改
			cabinetTypeDao.insert(cabinetType);
		} else {
			// 更新数据
			cabinetType.preUpdate();
			cabinetTypeDao.update(cabinetType);
		}
		//super.save(site);
	}
	
	@Transactional(readOnly = false)
	public void delete(CabinetType cabinetType) {
		super.delete(cabinetType);
	}
	@Transactional(readOnly = false)
	public CabinetType findByid(String id) {
		CabinetType Cabinet = new CabinetType();
		Cabinet = cabinetTypeDao.findByid(id);
		return Cabinet;
	}
	
}
