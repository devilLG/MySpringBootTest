package com.zhilai.master.modules.broadcast.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.broadcast.entity.AdvertArea;

/**
 * 
 *Title:AdvertAreaDao
 *Description:区域配置持久层
 *@author ChengJiawei
 *@date 2018年4月17日 上午10:04:59
 *
 */
@MyBatisDao
public interface AdvertAreaDao extends CrudDao<AdvertArea>{

	AdvertArea findOneByConfigId(String configId);

}
