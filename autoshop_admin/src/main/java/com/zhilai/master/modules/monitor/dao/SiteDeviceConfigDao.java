package com.zhilai.master.modules.monitor.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.monitor.entity.SiteDeviceConfig;

/**
 * 
 *Title:SiteDeviceConfigDao
 *Description:站点设备持久层
 *@author ChengJiawei
 *@date 2018年4月12日 上午8:41:54
 *
 */
@MyBatisDao
public interface SiteDeviceConfigDao extends CrudDao<SiteDeviceConfig>{

	void deleteBysid(String site_id);

}
