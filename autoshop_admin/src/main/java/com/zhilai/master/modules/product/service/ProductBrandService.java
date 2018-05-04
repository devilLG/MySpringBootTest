package com.zhilai.master.modules.product.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.product.dao.ProductBrandDao;
import com.zhilai.master.modules.product.entity.ProductBrand;

@Service
@Transactional(readOnly = true)
public class ProductBrandService extends CrudService<ProductBrandDao, ProductBrand> {
	
	@Autowired
	private ProductBrandDao productBrandDao;
	
	@Override
	public ProductBrand get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ProductBrand> findPage(Page<ProductBrand> page, ProductBrand productBrand) {
		return super.findPage(page, productBrand);
	}
	
	@Override
	public List<ProductBrand> findList(ProductBrand productBrand) {
		return super.findList(productBrand);
	}
	
	public List<ProductBrand> findExist(ProductBrand productBrand) {
		return productBrandDao.findExist(productBrand);
	}
	
	public ProductBrand findOne(String brand_id) {
		return productBrandDao.findOne(brand_id);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(ProductBrand productBrand) {
		if (StringUtils.isBlank(productBrand.getLogid())){
			productBrand.setCreate_time(DateUtil.getNow());
		}
		super.save(productBrand);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(ProductBrand productBrand) {
		super.delete(productBrand);
	}
}

