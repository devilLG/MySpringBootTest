package com.zhilai.master.modules.monitor.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.monitor.entity.DeviceEnv;

/**
 * 
 *Title:DeviceEnv
 *Description:设备运行参数配置持久层
 *@author ChengJiawei
 *@date 2018年4月2日 上午9:40:52
 *
 */

@MyBatisDao
public interface DeviceEnvDao extends CrudDao<DeviceEnv>{
	
	List<DeviceEnv> findByDeviceId(String deviceId);

	void deleteList(String deviceId);

	List<DeviceEnv> findByDeviceName(String deviceName);

}
