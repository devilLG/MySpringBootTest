package com.zhilai.master.modules.inventory.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.inventory.entity.BarterOrder;

@MyBatisDao
public interface BarterOrderDao extends CrudDao<BarterOrder> {
	public List<BarterOrder> findExist(BarterOrder barterOrder);
}
