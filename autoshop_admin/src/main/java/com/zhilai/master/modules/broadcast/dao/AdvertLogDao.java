package com.zhilai.master.modules.broadcast.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.broadcast.entity.AdvertLog;

/**
 * 
 *Title:AdvertLogDao
 *Description:广告日志持久层
 *@author ChengJiawei
 *@date 2018年4月19日 上午9:10:22
 *
 */
@MyBatisDao
public interface AdvertLogDao extends CrudDao<AdvertLog>{

}
