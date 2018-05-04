package com.zhilai.master.modules.product.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.product.dao.ProductFavourableDao;
import com.zhilai.master.modules.product.entity.ProductFavourable;

@Service
@Transactional(readOnly = true)
public class ProductFavourableService extends CrudService<ProductFavourableDao, ProductFavourable> {
	
	@Autowired
	private ProductFavourableDao productFavourableDao;
	
	@Override
	public ProductFavourable get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ProductFavourable> findPage(Page<ProductFavourable> page, ProductFavourable productFavourable) {
		return super.findPage(page, productFavourable);
	}
	
	@Override
	public List<ProductFavourable> findList(ProductFavourable productFavourable) {
		return super.findList(productFavourable);
	}
	
	public List<ProductFavourable> findExist(ProductFavourable productFavourable) {
		return productFavourableDao.findExist(productFavourable);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(ProductFavourable productFavourable) {
		if (StringUtils.isBlank(productFavourable.getLogid())){
			productFavourable.setCreate_time(DateUtil.getNow());
		}
		super.save(productFavourable);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(ProductFavourable productFavourable) {
		super.delete(productFavourable);
	}
}

