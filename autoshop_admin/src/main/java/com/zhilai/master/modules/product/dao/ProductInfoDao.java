package com.zhilai.master.modules.product.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.product.entity.ProductInfo;

@MyBatisDao
public interface ProductInfoDao extends CrudDao<ProductInfo> {

	public List<ProductInfo> findExist(ProductInfo productInfo);
	
	public List<ProductInfo> findClassifyUsed(ProductInfo productInfo);
	
	public List<ProductInfo> findBrandUsed(ProductInfo productInfo);
	
	public ProductInfo findOneByProductId(String product_id);
	
}
