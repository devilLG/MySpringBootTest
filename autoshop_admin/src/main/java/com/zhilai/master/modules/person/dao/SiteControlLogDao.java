package com.zhilai.master.modules.person.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.person.entity.SiteControlLog;

@MyBatisDao
public interface SiteControlLogDao extends CrudDao<SiteControlLog> {
	
}
