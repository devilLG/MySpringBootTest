package com.zhilai.master.modules.site.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.site.entity.SiteDevice;
@MyBatisDao
public interface SiteDeviceDao extends CrudDao<SiteDevice> {
	public int deleteBysid(String site_id);
}
