/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.standard.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.person.entity.SiteControlLog;
import com.zhilai.master.modules.standard.entity.BoxType;
import com.zhilai.master.modules.standard.entity.CabinetStandard;
import com.zhilai.master.modules.standard.dao.CabinetStandardDao;
/**
 * 站点Service
 * @author zhilai
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class CabinetStandardService extends CrudService<CabinetStandardDao, CabinetStandard> {
	@Autowired
	private CabinetStandardDao cabinetStandardDao;
	public Page<CabinetStandard> findPage(Page<CabinetStandard> page, CabinetStandard cabinetStandard) {
		cabinetStandard.setPage(page);
		page.setList(super.findList(cabinetStandard));
		return page;
		
		//return super.findPage(page, site);
	}

	@Transactional(readOnly = false)
	public void save(CabinetStandard cabinetStandard) {
		if (StringUtils.isBlank(cabinetStandard.getLogid())) {
			cabinetStandard.preInsert();
			
			cabinetStandard.setCreate_time(DateUtil.getNow());
			
			cabinetStandardDao.insert(cabinetStandard);
		} else {
			// 更新数据
			cabinetStandard.preUpdate();
			cabinetStandardDao.update(cabinetStandard);
		}
		//super.save(site);
	}
	
	@Transactional(readOnly = false)
	public void delete(CabinetStandard cabinetStandard) {
		super.delete(cabinetStandard);
	}
	
	public List<CabinetStandard> findAllList(CabinetStandard cabinetStandard) {
		return cabinetStandardDao.findAllList(cabinetStandard);
	}
	
}
