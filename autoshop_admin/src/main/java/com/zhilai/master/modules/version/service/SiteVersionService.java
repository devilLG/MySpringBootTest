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
import com.zhilai.master.modules.version.dao.SiteVersionDao;
import com.zhilai.master.modules.version.entity.SiteVersion;

/**
 * @author guowei
 * 终端版本
 * @date 2017-12-22 上午9:45:10
 * 
 */
@Service
@Transactional(readOnly = true)
public class SiteVersionService extends CrudService<SiteVersionDao, SiteVersion> {
	@Autowired
	private SiteVersionDao siteVersionDao;
	public Page<SiteVersion> findPage(Page<SiteVersion> page, SiteVersion siteVersion) {
		siteVersion.setPage(page);
		page.setList(super.findList(siteVersion));
		return page;
		
		//return super.findPage(page, site);
	}

	@Transactional(readOnly = false)
	public void save(SiteVersion siteVersion) {
		if (StringUtils.isBlank(siteVersion.getLogid())) {
			siteVersion.preInsert();
			
			siteVersion.setCreate_time(DateUtil.getNow());
			
			siteVersionDao.insert(siteVersion);
		} else {
			// 更新数据
			siteVersion.preUpdate();
			siteVersion.setUgstate_time(DateUtil.getNow());
			siteVersionDao.update(siteVersion);
		}
		//super.save(site);
	}
	
	@Transactional(readOnly = false)
	public void delete(SiteVersion siteVersion) {
		super.delete(siteVersion);
	}
	
}
