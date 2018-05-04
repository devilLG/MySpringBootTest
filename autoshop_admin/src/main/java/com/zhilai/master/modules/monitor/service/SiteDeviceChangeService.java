package com.zhilai.master.modules.monitor.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.monitor.dao.SiteDeviceChangeDao;
import com.zhilai.master.modules.monitor.entity.SiteDeviceChange;

/**
 * 
 *Title:SiteDeviceChangeService
 *Description:站点设备状态记录业务层
 *@author ChengJiawei
 *@date 2018年4月12日 下午5:03:30
 *
 */
@Service
@Transactional(readOnly = true)
public class SiteDeviceChangeService extends CrudService<SiteDeviceChangeDao, SiteDeviceChange>{
	
	@Autowired
	private SiteDeviceChangeDao siteDeviceChangeDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(SiteDeviceChange siteDeviceChange){
		if (StringUtils.isBlank(siteDeviceChange.getLogid())) {
			siteDeviceChange.preInsert();
			siteDeviceChangeDao.insert(siteDeviceChange);
		}else {
			siteDeviceChangeDao.update(siteDeviceChange);
		}
	}
}
