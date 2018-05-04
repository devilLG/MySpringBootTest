package com.zhilai.master.modules.log.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.log.entity.PkgLog;
@MyBatisDao
public interface PkgLogDao extends CrudDao<PkgLog> {
	
}
