package com.zhilai.master.modules.version.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.version.entity.SiteVersion;
@MyBatisDao
public interface SiteVersionDao extends CrudDao<SiteVersion> {
	
}
