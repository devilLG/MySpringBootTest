package com.zhilai.master.modules.monitor.dao;


import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.monitor.entity.SiteEnv;
/**
 * 
 *Title:SiteEnvDao
 *Description:站点设备运行状态持久层
 *@author ChengJiawei
 *@date 2018年4月12日 下午3:11:28
 *
 */
@MyBatisDao
public interface SiteEnvDao extends CrudDao<SiteEnv>{

	List<SiteEnv> findBySdeviceId(String sdeviceId);
}
