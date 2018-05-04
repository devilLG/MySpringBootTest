/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.log.dao.TradeLogDao;
import com.zhilai.master.modules.log.entity.TradeLog;

/**
 * 性能日志Service
 * 
 * @author guowei
 * @version 2017-12-28
 */
@Service
@Transactional(readOnly = true)
public class TradeLogService extends CrudService<TradeLogDao, TradeLog> {
	@Autowired
	private TradeLogDao tdDao;

	public Page<TradeLog> findPage(Page<TradeLog> page, TradeLog pk) {
		// 执行分页查询
		// 设置分页参数
		pk.setPage(page);
		page.setList(super.findList(pk));
		return page;

		// return super.findPage(page, site);
	}

	@Transactional(readOnly = false)
	public void save(TradeLog pk) {
		TradeLog log = super.get(pk.getLog_id());
		if (log == null) {
			pk.preInsert();
			tdDao.insert(pk);
		}
		else {
			// 更新数据
			pk.preUpdate();
			tdDao.update(pk);
		}
	}

}
