package com.zhilai.master.modules.workOrder.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.workOrder.entity.Work;

/**
 * 
 *Title:WorkDao
 *Description:工单信息持久层
 *@author ChengJiawei
 *@date 2018年4月24日 上午10:36:50
 *
 */
@MyBatisDao
public interface WorkDao extends CrudDao<Work>{

	List<Work> findDataPage(Work work);

}
