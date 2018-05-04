package com.zhilai.master.modules.monitor.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.monitor.entity.CabinetDevice;

/**
 * 
 *Title:CabinetDeviceDao
 *Description:柜子设备持久层
 *@author ChengJiawei
 *@date 2018年4月11日 上午10:12:06
 *
 */
@MyBatisDao
public interface CabinetDeviceDao extends CrudDao<CabinetDevice>{

	CabinetDevice findOneByLogid(String logid);

}
