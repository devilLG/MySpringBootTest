package com.zhilai.master.modules.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.monitor.dao.SiteEnvDao;
import com.zhilai.master.modules.monitor.entity.SiteEnv;

/**
 * 
 *Title:SiteEnvService
 *Description:站点设备运行状态业务层
 *@author ChengJiawei
 *@date 2018年4月12日 下午3:11:01
 *
 */
@Service
@Transactional(readOnly = true)
public class SiteEnvService extends CrudService<SiteEnvDao,SiteEnv>{

	@Autowired
	private SiteEnvDao siteEnvDao;
	
	public List<SiteEnv> findBySdeviceId(String sdeviceId) {
		// TODO Auto-generated method stub
		return siteEnvDao.findBySdeviceId(sdeviceId);
	}


}
