package com.zhilai.master.modules.site.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.site.entity.SiteControlConfig;

@MyBatisDao
public interface SiteControlConfigDao extends CrudDao<SiteControlConfig> {
	public SiteControlConfig findOne(SiteControlConfig siteControlConfig);
}
