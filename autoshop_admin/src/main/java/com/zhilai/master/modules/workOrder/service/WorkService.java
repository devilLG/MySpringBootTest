package com.zhilai.master.modules.workOrder.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.utils.PermissionUtils;
import com.zhilai.master.modules.workOrder.dao.WorkDao;
import com.zhilai.master.modules.workOrder.entity.Work;

/**
 * 
 *Title:WorkService
 *Description:工单管理业务层
 *@author ChengJiawei
 *@date 2018年4月24日 上午10:27:41
 *
 */
@Service
@Transactional(readOnly = true)
public class WorkService extends CrudService<WorkDao, Work>{

	@Autowired
	private WorkDao workDao;
	
	@Override
	@Transactional(readOnly = false)
	public void save(Work work){
		if (StringUtils.isBlank(work.getLogid())) {
			work.preInsert();
			workDao.insert(work);
		}else {
			workDao.update(work);
		}
	}
	
	//分页查询自己的工单
	public Page<Work> findDataPage(Page<Work> page, Work work) {
		PermissionUtils.limitCheck(work);
		work.setPage(page);
		page.setList(dao.findDataPage(work));
		return page;
	}

}
