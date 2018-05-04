/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.log.dao.AccessLogDao;
import com.zhilai.master.modules.log.dao.PkgLogDao;
import com.zhilai.master.modules.log.entity.AccessLog;
import com.zhilai.master.modules.log.entity.PkgLog;

/**
 * 访问日志Service
 * 
 * @author guowei
 * @version 2017-12-28
 */
@Service
@Transactional(readOnly = true)
public class AccessLogService extends CrudService<AccessLogDao, AccessLog> {
	@Autowired
	private AccessLogDao pkDao;

	public Page<AccessLog> findPage(Page<AccessLog> page, AccessLog pk) {
		// 执行分页查询
		// 设置分页参数
		pk.setPage(page);
		page.setList(super.findList(pk));
		return page;

		// return super.findPage(page, site);
	}

	@Transactional(readOnly = false)
	public void save(AccessLog pk) {
		AccessLog log = super.get(pk.getLog_id());
		if (log == null) {
			pk.preInsert();
			pkDao.insert(pk);
		}
		else {
			// 更新数据
			pk.preUpdate();
			pkDao.update(pk);
		}
	}

}
