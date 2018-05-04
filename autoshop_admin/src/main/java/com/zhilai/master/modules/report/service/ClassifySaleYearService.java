package com.zhilai.master.modules.report.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.report.dao.ClassifySaleYearDao;
import com.zhilai.master.modules.report.entity.ClassifySaleYear;


@Service
@Transactional(readOnly = true)
public class ClassifySaleYearService extends CrudService<ClassifySaleYearDao, ClassifySaleYear> {
	
	@Override
	public ClassifySaleYear get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ClassifySaleYear> findPage(Page<ClassifySaleYear> page, ClassifySaleYear classifySaleYear) {
		return super.findPage(page, classifySaleYear);
	}
}

