package com.zhilai.master.modules.trade.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.trade.entity.PartnerTrade;
@MyBatisDao
public interface PartnerTradeDao extends CrudDao<PartnerTrade> {
	public int  findTradeExist(PartnerTrade obj);
	public int deleteByCorpId(String id);
}
