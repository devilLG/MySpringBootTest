package com.zhilai.master.modules.monitor.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.monitor.entity.SiteDeviceChange;

/**
 * 
 *Title:SiteDeviceChangeDao
 *Description:站点设备状态记录持久层
 *@author ChengJiawei
 *@date 2018年4月12日 下午5:02:23
 *
 */
@MyBatisDao
public interface SiteDeviceChangeDao extends CrudDao<SiteDeviceChange>{

}
