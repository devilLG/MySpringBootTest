package com.zhilai.master.modules.workOrder.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.workOrder.dao.WorkChangeDao;
import com.zhilai.master.modules.workOrder.entity.WorkChange;

/**
 * 
 *Title:WorkChangeService
 *Description:工单日志业务层
 *@author ChengJiawei
 *@date 2018年4月19日 上午11:40:53
 *
 */
@Service
@Transactional(readOnly = true)
public class WorkChangeService extends CrudService<WorkChangeDao, WorkChange>{

	@Autowired
	private WorkChangeDao workChangeDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(WorkChange workChange){
		if (StringUtils.isBlank(workChange.getLogid())) {
			workChange.preInsert();
			workChangeDao.insert(workChange);
		}else {
			workChangeDao.update(workChange);
		}
	}
}
