package com.zhilai.master.modules.product.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.product.entity.ProductClassify;

@MyBatisDao
public interface ProductClassifyDao extends CrudDao<ProductClassify> {
	//获取所有的分类
	public List<ProductClassify> findAll(ProductClassify productClassify);
	
	//根据ID查询所有子目录
	public List<ProductClassify> findChildByParentId(ProductClassify productClassify);
	
	public int updateSort(ProductClassify productClassify);
	
	public List<ProductClassify> findExist(ProductClassify productClassify);
	
}
