package com.zhilai.master.modules.product.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.product.entity.ProductFavourable;

@MyBatisDao
public interface ProductFavourableDao extends CrudDao<ProductFavourable> {
	public List<ProductFavourable> findExist(ProductFavourable productFavourable);
	
	public List<ProductFavourable> findProductFavourableStartList(String favourable_stime);
	
	public List<ProductFavourable> findProductFavourableEndList(String favourable_etime);
}
