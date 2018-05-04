package com.zhilai.master.modules.report.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;

import com.zhilai.master.modules.report.dao.CorpSaleYearDao;
import com.zhilai.master.modules.report.entity.CorpSaleYear;

@Service
@Transactional(readOnly = true)
public class CorpSaleYearService extends CrudService<CorpSaleYearDao, CorpSaleYear> {
	
	@Override
	public CorpSaleYear get(String id) {
		return super.get(id);
	}
	
	@Override
	public List<CorpSaleYear> findList(CorpSaleYear corpSaleYear) {
		return super.findList(corpSaleYear);
	}
}

