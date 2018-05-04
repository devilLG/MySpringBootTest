/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.version.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.version.dao.UgradeLogDao;
import com.zhilai.master.modules.version.entity.UgradeLog;

/**
 * @author guowei
 * 版本升级
 * @date 2017-12-22 上午9:45:10
 * 
 */
@Service
@Transactional(readOnly = true)
public class UgradeLogService extends CrudService<UgradeLogDao, UgradeLog> {
	@Autowired
	private UgradeLogDao ugradeLogDao;
	public Page<UgradeLog> findPage(Page<UgradeLog> page, UgradeLog ugradeLog) {
		ugradeLog.setPage(page);
		page.setList(super.findList(ugradeLog));
		return page;
		
		//return super.findPage(page, site);
	}

	
}
