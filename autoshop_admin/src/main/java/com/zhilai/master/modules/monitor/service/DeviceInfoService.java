package com.zhilai.master.modules.monitor.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.monitor.dao.DeviceInfoDao;
import com.zhilai.master.modules.monitor.entity.DeviceInfo;

/**
 * 
 *Title:DeviceInfoService
 *Description:设备信息表业务层
 *@author ChengJiawei
 *@date 2018年4月2日 上午9:29:05
 *
 */
@Service
@Transactional(readOnly = true)
public class DeviceInfoService extends CrudService<DeviceInfoDao, DeviceInfo>{
	
	@Autowired
	private DeviceInfoDao deviceInfoDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(DeviceInfo deviceInfo){
		if (StringUtils.isBlank(deviceInfo.getLogid())) {
			deviceInfo.preInsert();
			deviceInfoDao.insert(deviceInfo);
		}else {
			deviceInfoDao.update(deviceInfo);
		}
	}

	public DeviceInfo findOneByDeviceId(String deviceId) {
		// TODO Auto-generated method stub
		return deviceInfoDao.findOneByDeviceId(deviceId);
	}
}
