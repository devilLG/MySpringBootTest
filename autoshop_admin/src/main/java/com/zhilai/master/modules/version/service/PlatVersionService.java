/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.version.service;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.version.dao.PlatVersionDao;
import com.zhilai.master.modules.version.entity.PlatVersion;
/**
 * 平台版本Service
 * @author zhilai
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class PlatVersionService extends CrudService<PlatVersionDao, PlatVersion> {
	@Autowired
	private PlatVersionDao platVersionDao;
	public Page<PlatVersion> findPage(Page<PlatVersion> page, PlatVersion platVersion) {
		platVersion.setPage(page);
		page.setList(super.findList(platVersion));
		return page;
		
		//return super.findPage(page, site);
	}

	@Transactional(readOnly = false)
	public void save(PlatVersion platVersion) {
		if (StringUtils.isBlank(platVersion.getLogid())) {
			platVersion.preInsert();
			
			platVersion.setCreate_time(DateUtil.getNow());
			
			platVersionDao.insert(platVersion);
		} else {
			// 更新数据
			platVersion.preUpdate();
			platVersion.setRefrest_time(DateUtil.getNow());
			platVersionDao.update(platVersion);
		}
		//super.save(site);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlatVersion platVersion) {
		super.delete(platVersion);
	}
	
}
