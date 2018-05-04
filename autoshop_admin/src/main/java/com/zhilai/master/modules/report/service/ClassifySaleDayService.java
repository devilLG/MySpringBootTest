package com.zhilai.master.modules.report.service;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.report.dao.ClassifySaleDayDao;
import com.zhilai.master.modules.report.entity.ClassifySaleDay;


@Service
@Transactional(readOnly = true)
public class ClassifySaleDayService extends CrudService<ClassifySaleDayDao, ClassifySaleDay> {
	
	@Override
	public ClassifySaleDay get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ClassifySaleDay> findPage(Page<ClassifySaleDay> page, ClassifySaleDay classifySaleDay) {
		return super.findPage(page, classifySaleDay);
	}
}

