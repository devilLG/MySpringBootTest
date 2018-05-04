package com.zhilai.master.modules.report.service;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.report.dao.ClassifySaleDayDao;
import com.zhilai.master.modules.report.dao.ClassifySaleMonthDao;
import com.zhilai.master.modules.report.entity.ClassifySaleDay;
import com.zhilai.master.modules.report.entity.ClassifySaleMonth;


@Service
@Transactional(readOnly = true)
public class ClassifySaleMonthService extends CrudService<ClassifySaleMonthDao, ClassifySaleMonth> {
	
	@Override
	public ClassifySaleMonth get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ClassifySaleMonth> findPage(Page<ClassifySaleMonth> page, ClassifySaleMonth classifySaleMonth) {
		return super.findPage(page, classifySaleMonth);
	}
}

