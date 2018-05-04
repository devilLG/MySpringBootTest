package com.zhilai.master.modules.standard.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.standard.entity.CabinetType;

@MyBatisDao
public interface CabinetTypeDao extends CrudDao<CabinetType> {
	public CabinetType findByid(String id);
}
