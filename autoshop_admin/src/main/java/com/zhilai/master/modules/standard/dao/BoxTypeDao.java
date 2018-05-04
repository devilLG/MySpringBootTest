package com.zhilai.master.modules.standard.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.standard.entity.BoxType;
@MyBatisDao
public interface BoxTypeDao extends CrudDao<BoxType> {
	public BoxType findOneByTypeName(String typeName);
}
