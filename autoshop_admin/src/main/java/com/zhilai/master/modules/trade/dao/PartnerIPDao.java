package com.zhilai.master.modules.trade.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.trade.entity.PartnerIP;
@MyBatisDao
public interface PartnerIPDao extends CrudDao<PartnerIP> {
	public PartnerIP findOneByCorpId(String corp_id);
	public int deleteByCorpId(String id);
	
}
