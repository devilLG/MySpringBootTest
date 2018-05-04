package com.zhilai.master.modules.workOrder.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.workOrder.entity.WorkDevice;

/**
 * 
 *Title:WorkDeviceDao
 *Description:工单设备持久层
 *@author ChengJiawei
 *@date 2018年4月24日 上午10:37:33
 *
 */
@MyBatisDao
public interface WorkDeviceDao extends CrudDao<WorkDevice>{

	List<WorkDevice> findListByWorkId(String workId);

}
