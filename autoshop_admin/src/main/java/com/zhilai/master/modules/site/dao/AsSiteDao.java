package com.zhilai.master.modules.site.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.site.entity.AsSite;

@MyBatisDao
public interface AsSiteDao extends CrudDao<AsSite> {
	
	public int updateSiteState(AsSite site);
	
	public List<AsSite> findListOnline();
	
	public List<AsSite> findWarnList(AsSite site);
	
	public AsSite findBySiteId(String site_id);
}
