package com.zhilai.master.modules.monitor.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.monitor.dao.CabinetDeviceDao;
import com.zhilai.master.modules.monitor.entity.CabinetDevice;

/**
 * 
 *Title:CabinetDeviceService
 *Description:柜子设备业务层
 *@author ChengJiawei
 *@date 2018年4月11日 上午10:12:55
 *
 */
@Service
@Transactional(readOnly = true)
public class CabinetDeviceService extends CrudService<CabinetDeviceDao, CabinetDevice>{
	
	@Autowired
	private CabinetDeviceDao cabinetDeviceDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(CabinetDevice cabinetDevice){
		if (StringUtils.isBlank(cabinetDevice.getLogid())) {
			cabinetDevice.preInsert();
			cabinetDeviceDao.insert(cabinetDevice);
		}else {
			cabinetDeviceDao.update(cabinetDevice);
		}
	}

	public CabinetDevice findOneByLogid(String logid) {
		// TODO Auto-generated method stub
		return cabinetDeviceDao.findOneByLogid(logid);
	}
}
