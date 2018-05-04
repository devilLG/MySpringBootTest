package com.zhilai.master.modules.monitor.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.monitor.dao.SiteDeviceConfigDao;
import com.zhilai.master.modules.monitor.entity.SiteDeviceConfig;

/**
 * 
 *Title:SiteDeviceConfigService
 *Description:站点设备业务层
 *@author ChengJiawei
 *@date 2018年4月12日 上午8:44:22
 *
 */
@Service
@Transactional(readOnly = true)
public class SiteDeviceConfigService extends CrudService<SiteDeviceConfigDao, SiteDeviceConfig>{

	@Autowired
	private SiteDeviceConfigDao siteDeviceConfigDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(SiteDeviceConfig siteDeviceConfig){
		if (StringUtils.isBlank(siteDeviceConfig.getLogid())) {
			siteDeviceConfig.preInsert();
			siteDeviceConfigDao.insert(siteDeviceConfig);
		}else {
			siteDeviceConfigDao.update(siteDeviceConfig);
		}
	}
}
