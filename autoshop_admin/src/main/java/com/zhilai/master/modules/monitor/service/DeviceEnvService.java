package com.zhilai.master.modules.monitor.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.monitor.dao.DeviceEnvDao;
import com.zhilai.master.modules.monitor.entity.DeviceEnv;

/**
 * 
 *Title:DeviceEnvService
 *Description:备运行参数业务层
 *@author ChengJiawei
 *@date 2018年4月2日 上午9:41:09
 *
 */
@Service
@Transactional(readOnly = true)
public class DeviceEnvService extends CrudService<DeviceEnvDao, DeviceEnv>{

	@Autowired
	private DeviceEnvDao deviceEnvDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(DeviceEnv deviceEnv){
		if (StringUtils.isBlank(deviceEnv.getLogid())) {
			deviceEnv.preInsert();
			deviceEnvDao.insert(deviceEnv);
		}else {
			deviceEnvDao.update(deviceEnv);
		}
	}
	
	public List<DeviceEnv> findByDeviceId(String deviceId) {
		// TODO Auto-generated method stub
		return deviceEnvDao.findByDeviceId(deviceId);
	}

	@Transactional(readOnly = false)
	public void deleteList(String deviceId) {
		// TODO Auto-generated method stub
		deviceEnvDao.deleteList(deviceId);
	}

	public List<DeviceEnv> findByDeviceName(String deviceName) {
		// TODO Auto-generated method stub
		return deviceEnvDao.findByDeviceName(deviceName);
	}

}
