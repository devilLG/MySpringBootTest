package com.zhilai.master.modules.monitor.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.monitor.entity.SiteDeviceLog;

/**
 * 
 *Title:SiteDeviceLogDao
 *Description:站点日志持久层
 *@author ChengJiawei
 *@date 2018年4月13日 上午8:39:48
 *
 */
@MyBatisDao
public interface SiteDeviceLogDao extends CrudDao<SiteDeviceLog>{

}
