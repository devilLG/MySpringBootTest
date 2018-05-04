package com.zhilai.master.modules.broadcast.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.broadcast.dao.AdvertMediaDao;
import com.zhilai.master.modules.broadcast.entity.AdvertMedia;

/**
 * 
 *Title:AdvertMediaService
 *Description:媒体信息业务层
 *@author ChengJiawei
 *@date 2018年4月13日 下午2:29:46
 *
 */
@Service
@Transactional(readOnly = true)
public class AdvertMediaService extends CrudService<AdvertMediaDao, AdvertMedia>{
	@Autowired
	private AdvertMediaDao advertMediaDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(AdvertMedia advertMedia){
		if (StringUtils.isBlank(advertMedia.getLogid())) {
			advertMedia.preInsert();
			advertMediaDao.insert(advertMedia);
		}else {
			advertMediaDao.update(advertMedia);
		}
	}
}
