package com.zhilai.master.modules.broadcast.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.broadcast.dao.AdvertAuthDao;
import com.zhilai.master.modules.broadcast.entity.AdvertAuth;

/**
 * 
 *Title:AdvertAuthService
 *Description:权限播控业务层
 *@author ChengJiawei
 *@date 2018年4月18日 下午2:18:31
 *
 */
@Service
@Transactional(readOnly = true)
public class AdvertAuthService extends CrudService<AdvertAuthDao, AdvertAuth>{

	@Autowired
	private AdvertAuthDao advertAuthDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(AdvertAuth advertAuth){
		if (StringUtils.isBlank(advertAuth.getLogid())) {
			advertAuth.preInsert();
			advertAuthDao.insert(advertAuth);
		}else {
			advertAuthDao.update(advertAuth);
		}
	}
}
