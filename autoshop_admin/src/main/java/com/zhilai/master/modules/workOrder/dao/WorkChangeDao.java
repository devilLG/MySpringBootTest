package com.zhilai.master.modules.workOrder.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.workOrder.entity.WorkChange;

/**
 * 
 *Title:WorkChangeDao
 *Description:工单日志持久层
 *@author ChengJiawei
 *@date 2018年4月19日 上午11:42:04
 *
 */
@MyBatisDao
public interface WorkChangeDao extends CrudDao<WorkChange>{

}
