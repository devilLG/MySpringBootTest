package com.zhilai.master.modules.product.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.product.entity.ProductBrand;

@MyBatisDao
public interface ProductBrandDao extends CrudDao<ProductBrand> {
	public List<ProductBrand> findExist(ProductBrand productBrand);
	public ProductBrand findOne(String brand_id);
}
