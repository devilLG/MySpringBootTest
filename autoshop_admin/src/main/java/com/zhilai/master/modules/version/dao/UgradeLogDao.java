package com.zhilai.master.modules.version.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.version.entity.UgradeLog;
@MyBatisDao
public interface UgradeLogDao extends CrudDao<UgradeLog> {
	
}
