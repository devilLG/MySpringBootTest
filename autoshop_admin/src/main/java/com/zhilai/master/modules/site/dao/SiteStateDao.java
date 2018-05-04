package com.zhilai.master.modules.site.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.site.entity.SiteState;

@MyBatisDao
public interface SiteStateDao extends CrudDao<SiteState> {
	public List<SiteState> findByRefreshTime(String refresh_time);
	public SiteState findBySiteId(String site_id);
	public int deleteBysid(String site_id);
	public int updateRefresh(SiteState siteState);
}
