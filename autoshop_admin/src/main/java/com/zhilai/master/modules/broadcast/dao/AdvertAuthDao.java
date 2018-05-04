package com.zhilai.master.modules.broadcast.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.broadcast.entity.AdvertAuth;

/**
 * 
 *Title:AdvertAuthDao
 *Description:播控权限持久层
 *@author ChengJiawei
 *@date 2018年4月18日 下午2:15:18
 *
 */
@MyBatisDao
public interface AdvertAuthDao extends CrudDao<AdvertAuth>{

}
