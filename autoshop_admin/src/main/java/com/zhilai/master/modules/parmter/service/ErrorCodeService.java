package com.zhilai.master.modules.parmter.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.parmter.dao.ErrorCodeDao;
import com.zhilai.master.modules.parmter.entity.ErrorCode;
import com.zhilai.master.modules.product.dao.ProductBrandDao;
import com.zhilai.master.modules.product.entity.ProductBrand;

@Service
@Transactional(readOnly = true)
public class ErrorCodeService extends CrudService<ErrorCodeDao, ErrorCode> {
	
	@Autowired
	private ErrorCodeDao errorCodeBrandDao;
	
	@Override
	public ErrorCode get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ErrorCode> findPage(Page<ErrorCode> page, ErrorCode errorCode) {
		return super.findPage(page, errorCode);
	}
	
	@Override
	public List<ErrorCode> findList(ErrorCode errorCode) {
		return super.findList(errorCode);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(ErrorCode productBrand) {
		
		super.save(productBrand);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(ErrorCode errorCode) {
		super.delete(errorCode);
	}
}

