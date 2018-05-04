package com.zhilai.master.modules.broadcast.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.broadcast.dao.AdvertAreaMediaDao;
import com.zhilai.master.modules.broadcast.entity.AdvertAreaMedia;

/**
 * 
 *Title:AdvertAreaMediaService
 *Description:区域媒体业务层
 *@author ChengJiawei
 *@date 2018年4月17日 下午2:40:15
 *
 */
@Service
@Transactional(readOnly = true)
public class AdvertAreaMediaService extends CrudService<AdvertAreaMediaDao, AdvertAreaMedia>{

	@Autowired
	private AdvertAreaMediaDao advertAreaMediaDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(AdvertAreaMedia advertAreaMedia){
		if (StringUtils.isBlank(advertAreaMedia.getLogid())) {
			advertAreaMedia.preInsert();
			advertAreaMediaDao.insert(advertAreaMedia);
		}else {
			advertAreaMediaDao.update(advertAreaMedia);
		}
	}
}
