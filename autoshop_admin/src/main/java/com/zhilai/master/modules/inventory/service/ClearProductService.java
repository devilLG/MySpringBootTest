package com.zhilai.master.modules.inventory.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.inventory.dao.ClearProductDao;
import com.zhilai.master.modules.inventory.entity.ClearProduct;

@Service
@Transactional(readOnly = true)
public class ClearProductService extends CrudService<ClearProductDao, ClearProduct> {
	
	@Override
	public ClearProduct get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ClearProduct> findPage(Page<ClearProduct> page, ClearProduct clearProduct) {
		return super.findPage(page, clearProduct);
	}
	
	@Override
	public List<ClearProduct> findList(ClearProduct clearProduct) {
		return super.findList(clearProduct);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(ClearProduct clearProduct) {
		if (StringUtils.isBlank(clearProduct.getLogid())){
			clearProduct.setCreate_time(DateUtil.getNow());
		}
		super.save(clearProduct);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(ClearProduct clearProduct) {
		super.delete(clearProduct);
	}
}

