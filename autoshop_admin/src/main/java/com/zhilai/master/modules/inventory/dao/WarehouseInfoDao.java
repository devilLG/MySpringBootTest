package com.zhilai.master.modules.inventory.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.inventory.entity.WarehouseInfo;

@MyBatisDao
public interface WarehouseInfoDao extends CrudDao<WarehouseInfo> {
	public WarehouseInfo findByCorpId(String corp_id);
}
