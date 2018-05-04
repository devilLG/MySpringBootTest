package com.zhilai.master.modules.site.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.site.entity.SiteControl;

@MyBatisDao
public interface SiteControlDao extends CrudDao<SiteControl> {
	public SiteControl findOne(SiteControl siteControl);
	
	public int insert(SiteControl siteControl);
	
	public int update(SiteControl siteControl);
}
