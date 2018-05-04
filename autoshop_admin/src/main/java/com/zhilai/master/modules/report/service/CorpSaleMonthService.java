package com.zhilai.master.modules.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;

import com.zhilai.master.modules.report.dao.CorpSaleMonthDao;
import com.zhilai.master.modules.report.entity.CorpSaleMonth;

@Service
@Transactional(readOnly = true)
public class CorpSaleMonthService extends CrudService<CorpSaleMonthDao, CorpSaleMonth> {
	
	@Autowired
	private CorpSaleMonthDao corpSaleMonthDao;
	
	@Override
	public CorpSaleMonth get(String id) {
		return super.get(id);
	}
	
	@Override
	public List<CorpSaleMonth> findList(CorpSaleMonth corpSaleMonth) {
		return super.findList(corpSaleMonth);
	}
	
	public CorpSaleMonth findOne(CorpSaleMonth corpSaleMonth) {
		return corpSaleMonthDao.findOne(corpSaleMonth);
	}
}

