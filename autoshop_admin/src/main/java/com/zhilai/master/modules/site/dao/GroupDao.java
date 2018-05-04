package com.zhilai.master.modules.site.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.site.entity.Group;
@MyBatisDao
public interface GroupDao extends CrudDao<Group> {
	public List<Group> getSiteGroup();
}
