package com.zhilai.master.modules.broadcast.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.broadcast.dao.AdvertConfigDao;
import com.zhilai.master.modules.broadcast.entity.AdvertConfig;
import com.zhilai.master.modules.broadcast.entity.AdvertMedia;

/**
 * 
 *Title:AdvertConfigService
 *Description:广告配置业务层
 *@author ChengJiawei
 *@date 2018年4月16日 下午5:42:11
 *
 */
@Service
@Transactional(readOnly = true)
public class AdvertConfigService extends CrudService<AdvertConfigDao, AdvertConfig>{
	
	@Autowired
	private AdvertConfigDao advertConfigDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(AdvertConfig advertConfig){
		if (StringUtils.isBlank(advertConfig.getLogid())) {
			advertConfig.preInsert();
			advertConfigDao.insert(advertConfig);
		}else {
			advertConfigDao.update(advertConfig);
		}
	}

}
