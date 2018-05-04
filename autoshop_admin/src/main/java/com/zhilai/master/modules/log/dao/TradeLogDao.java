package com.zhilai.master.modules.log.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.log.entity.TradeLog;
@MyBatisDao
public interface TradeLogDao extends CrudDao<TradeLog> {
	
}
