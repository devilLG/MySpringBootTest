/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.log.dao.PkgLogDao;
import com.zhilai.master.modules.log.entity.PkgLog;

/**
 * 报文日志Service
 * 
 * @author guowei
 * @version 2017-12-28
 */
@Service
@Transactional(readOnly = true)
public class PkgLogService extends CrudService<PkgLogDao, PkgLog> {
	@Autowired
	private PkgLogDao pkgLogDao;

	public Page<PkgLog> findPage(Page<PkgLog> page, PkgLog pkgLog) {
		// 执行分页查询
		// 设置分页参数
		pkgLog.setPage(page);
		page.setList(super.findList(pkgLog));
		return page;

		// return super.findPage(page, site);
	}

	@Transactional(readOnly = false)
	public void save(PkgLog pkgLog) {
		PkgLog trade = super.get(pkgLog.getLog_id());
		if (trade == null) {
			pkgLog.preInsert();
			pkgLogDao.insert(pkgLog);
		}
		else {
			// 更新数据
			pkgLog.preUpdate();
			pkgLogDao.update(pkgLog);
		}
	}

	@Transactional(readOnly = false)
	public void delete(PkgLog pkgLog) {
		super.delete(pkgLog);
	}

}
