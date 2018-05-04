package com.zhilai.master.modules.broadcast.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.broadcast.dao.AdvertAreaDao;
import com.zhilai.master.modules.broadcast.entity.AdvertArea;

/**
 * 
 *Title:AdvertAreaService
 *Description:区域配置业务层
 *@author ChengJiawei
 *@date 2018年4月17日 上午10:05:37
 *
 */
@Service
@Transactional(readOnly = true)
public class AdvertAreaService extends CrudService<AdvertAreaDao, AdvertArea>{

	@Autowired
	private AdvertAreaDao advertAreaDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(AdvertArea advertArea){
		if (StringUtils.isBlank(advertArea.getLogid())) {
			advertArea.preInsert();
			advertAreaDao.insert(advertArea);
		}else {
			advertAreaDao.update(advertArea);
		}
	}

	public AdvertArea findOneByConfigId(String configId) {
		// TODO Auto-generated method stub
		return advertAreaDao.findOneByConfigId(configId);
	}
}
