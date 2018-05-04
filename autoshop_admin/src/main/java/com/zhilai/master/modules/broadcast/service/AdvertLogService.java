package com.zhilai.master.modules.broadcast.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.broadcast.dao.AdvertLogDao;
import com.zhilai.master.modules.broadcast.entity.AdvertLog;

/**
 * 
 *Title:AdvertLogService
 *Description:广告日志业务层
 *@author ChengJiawei
 *@date 2018年4月19日 上午9:11:11
 *
 */
@Service
@Transactional(readOnly = true)
public class AdvertLogService extends CrudService<AdvertLogDao, AdvertLog>{

	@Autowired
	private AdvertLogDao advertLogDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(AdvertLog advertLog){
		if (StringUtils.isBlank(advertLog.getLogid())) {
			advertLog.preInsert();
			advertLogDao.insert(advertLog);
		}else {
			advertLogDao.update(advertLog);
		}
	}
}
