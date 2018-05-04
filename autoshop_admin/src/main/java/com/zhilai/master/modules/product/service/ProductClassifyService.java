package com.zhilai.master.modules.product.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.product.dao.ProductClassifyDao;
import com.zhilai.master.modules.product.entity.ProductClassify;
import com.zhilai.master.modules.utils.PermissionUtils;

@Service
@Transactional(readOnly = true)
public class ProductClassifyService extends CrudService<ProductClassifyDao, ProductClassify> {
	
	@Autowired
	private ProductClassifyDao productClassifyDao;

	@Override
	public ProductClassify get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ProductClassify> findPage(Page<ProductClassify> page, ProductClassify productClassify) {
		return super.findPage(page, productClassify);
	}
	
	public List<ProductClassify> findAll() {
		ProductClassify productClassify = new ProductClassify();
		PermissionUtils.limitCheck(productClassify);
		return productClassifyDao.findAll(productClassify);
	}
	
	public List<ProductClassify> findChildByParentId(ProductClassify productClassify) {
		return productClassifyDao.findChildByParentId(productClassify);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(ProductClassify productClassify) {
		if (StringUtils.isBlank(productClassify.getLogid())){
			productClassify.setCreate_time(DateUtil.getNow());
		}
		super.save(productClassify);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(ProductClassify productClassify) {
		super.delete(productClassify);
	}
	
	@Transactional(readOnly = false)
	public void updateSort(ProductClassify productClassify) {
		productClassifyDao.updateSort(productClassify);
	}
	
	public List<ProductClassify> findExist(ProductClassify productClassify) {
		return productClassifyDao.findExist(productClassify);
	}
}

