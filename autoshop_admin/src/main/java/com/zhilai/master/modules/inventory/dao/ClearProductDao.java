package com.zhilai.master.modules.inventory.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.inventory.entity.ClearProduct;

@MyBatisDao
public interface ClearProductDao extends CrudDao<ClearProduct> {
	
}
