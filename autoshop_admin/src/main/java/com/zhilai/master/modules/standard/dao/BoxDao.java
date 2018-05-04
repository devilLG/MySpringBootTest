package com.zhilai.master.modules.standard.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.site.entity.SiteState;
import com.zhilai.master.modules.standard.entity.Box;

@MyBatisDao
public interface BoxDao extends CrudDao<Box> {

	public List<Box> findBySiteId(String site_id);
	public int deleteByCabinetId(Box box); 
}
