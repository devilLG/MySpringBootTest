package com.zhilai.master.modules.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;

import com.zhilai.master.modules.report.dao.CorpSaleDayDao;
import com.zhilai.master.modules.report.entity.CorpSaleDay;

@Service
@Transactional(readOnly = true)
public class CorpSaleDayService extends CrudService<CorpSaleDayDao, CorpSaleDay> {

	@Autowired
	private CorpSaleDayDao corpSaleDayDao;
	
	@Override
	public CorpSaleDay get(String id) {
		return super.get(id);
	}
	
	@Override
	public List<CorpSaleDay> findList(CorpSaleDay corpSaleDay) {
		return super.findList(corpSaleDay);
	}
	
	public CorpSaleDay findOne(CorpSaleDay corpSaleDay) {
		return corpSaleDayDao.findOne(corpSaleDay);
	}
}

