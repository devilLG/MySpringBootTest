package com.zhilai.master.modules.report.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.report.entity.CorpSaleDay;


@MyBatisDao
public interface CorpSaleDayDao extends CrudDao<CorpSaleDay> {
	public CorpSaleDay findOne(CorpSaleDay corpSaleDay);
}
