package com.zhilai.master.modules.inventory.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.inventory.entity.ReplenishmentOrderDetail;

@MyBatisDao
public interface ReplenishmentOrderDetailDao extends CrudDao<ReplenishmentOrderDetail> {
	public List<ReplenishmentOrderDetail> findProductOverdueList(String time);
}
