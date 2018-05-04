package com.zhilai.master.modules.report.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.report.entity.CorpSaleMonth;


@MyBatisDao
public interface CorpSaleMonthDao extends CrudDao<CorpSaleMonth> {
	public CorpSaleMonth findOne(CorpSaleMonth corpSaleMonth);
}
