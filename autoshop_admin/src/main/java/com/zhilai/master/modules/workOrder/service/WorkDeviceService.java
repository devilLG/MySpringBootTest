package com.zhilai.master.modules.workOrder.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.workOrder.dao.WorkDeviceDao;
import com.zhilai.master.modules.workOrder.entity.WorkDevice;

/**
 * 
 *Title:WorkDeviceService
 *Description:工单设备业务层
 *@author ChengJiawei
 *@date 2018年4月24日 上午10:42:12
 *
 */
@Service
@Transactional
public class WorkDeviceService extends CrudService<WorkDeviceDao, WorkDevice>{

	@Autowired
	private WorkDeviceDao workDeviceDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(WorkDevice workDevice){
		if (StringUtils.isBlank(workDevice.getLogid())) {
			workDevice.preInsert();
			workDeviceDao.insert(workDevice);
		}else {
			workDeviceDao.update(workDevice);
		}
	}

	public List<WorkDevice> findListByWorkId(String workId) {
		// TODO Auto-generated method stub
		return workDeviceDao.findListByWorkId(workId);
	}
}
