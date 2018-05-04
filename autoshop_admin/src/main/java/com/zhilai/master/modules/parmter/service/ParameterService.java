/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.parmter.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.parmter.dao.ParameterDao;
import com.zhilai.master.modules.parmter.entity.Parameter;

/**
 *参数
 */
@Service
@Transactional(readOnly = true)
public class ParameterService extends CrudService<ParameterDao, Parameter> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	
	@Transactional(readOnly = false)
	public void save(Parameter parameter) {
		super.save(parameter);
	}

	@Transactional(readOnly = false)
	public void delete(Parameter parameter) {
		super.delete(parameter);
	}
 
}
