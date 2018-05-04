package com.zhilai.master.modules.monitor.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.monitor.dao.SiteDeviceLogDao;
import com.zhilai.master.modules.monitor.entity.SiteDeviceLog;

/**
 * 
 *Title:SiteDeviceLogService
 *Description:站点日志业务层
 *@author ChengJiawei
 *@date 2018年4月13日 上午8:46:47
 *
 */
@Service
@Transactional(readOnly = true)
public class SiteDeviceLogService extends CrudService<SiteDeviceLogDao, SiteDeviceLog>{

	@Autowired
	private SiteDeviceLogDao siteDeviceLogDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(SiteDeviceLog siteDeviceLog){
		if (StringUtils.isBlank(siteDeviceLog.getLogid())) {
			siteDeviceLog.preInsert();
			siteDeviceLogDao.insert(siteDeviceLog);
		}else {
			siteDeviceLogDao.update(siteDeviceLog);
		}
	}
}
