package com.zhilai.master.modules.monitor.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.monitor.entity.DeviceInfo;

/**
 * 
 *Title:DeviceInfoDao
 *Description:设备信息表持久层
 *@author ChengJiawei
 *@date 2018年4月2日 上午9:27:39
 *
 */
@MyBatisDao
public interface DeviceInfoDao extends CrudDao<DeviceInfo>{

	DeviceInfo findOneByDeviceId(String deviceId);

}
