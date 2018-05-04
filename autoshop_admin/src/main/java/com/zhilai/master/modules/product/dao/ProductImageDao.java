package com.zhilai.master.modules.product.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.product.entity.ProductImage;

@MyBatisDao
public interface ProductImageDao extends CrudDao<ProductImage> {
	
	public ProductImage findExist(ProductImage productImage);
}
