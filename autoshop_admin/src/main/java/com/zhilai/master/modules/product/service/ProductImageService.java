package com.zhilai.master.modules.product.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.product.dao.ProductImageDao;
import com.zhilai.master.modules.product.entity.ProductImage;

@Service
@Transactional(readOnly = true)
public class ProductImageService extends CrudService<ProductImageDao, ProductImage> {
	
	@Autowired
	private ProductImageDao productImageDao;
	
	@Override
	public ProductImage get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ProductImage> findPage(Page<ProductImage> page, ProductImage productImage) {
		return super.findPage(page, productImage);
	}
	
	@Override
	public List<ProductImage> findList(ProductImage productImage) {
		return super.findList(productImage);
	}
	
	public ProductImage findExist(ProductImage productImage) {
		return productImageDao.findExist(productImage);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(ProductImage productImage) {
		if (StringUtils.isBlank(productImage.getLogid())){
			productImage.setCreate_time(DateUtil.getNow());
		}
		super.save(productImage);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(ProductImage productImage) {
		super.delete(productImage);
	}
}

