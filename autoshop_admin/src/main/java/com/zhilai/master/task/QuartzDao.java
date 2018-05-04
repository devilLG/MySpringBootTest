package com.zhilai.master.task;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
@MyBatisDao
public interface QuartzDao extends CrudDao<QuartzTimerJob> {

	public List<QuartzTimerJob> findAllTask();

}
