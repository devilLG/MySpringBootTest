/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.log.dao.UseLogDao;
import com.zhilai.master.modules.log.entity.UseLog;

/**
 * 终端日志Service
 * 
 * @author guowei
 * @version 2017-12-28
 */
@Service
@Transactional(readOnly = true)
public class UseLogService extends CrudService<UseLogDao, UseLog> {
	@Autowired
	private UseLogDao useDao;

	public Page<UseLog> findPage(Page<UseLog> page, UseLog pk) {
		// 执行分页查询
		// 设置分页参数
		pk.setPage(page);
		page.setList(super.findList(pk));
		return page;

		// return super.findPage(page, site);
	}

	@Transactional(readOnly = false)
	public void save(UseLog use) {
		UseLog log = super.get(use.getLogid());
		if (log == null) {
			use.preInsert();
			useDao.insert(use);
		}
		else {
			// 更新数据
			use.preUpdate();
			useDao.update(use);
		}
	}

}
