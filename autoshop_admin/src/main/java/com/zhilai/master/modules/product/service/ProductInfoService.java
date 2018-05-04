package com.zhilai.master.modules.product.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.product.dao.ProductInfoDao;
import com.zhilai.master.modules.product.entity.ProductInfo;

@Service
@Transactional(readOnly = true)
public class ProductInfoService extends CrudService<ProductInfoDao, ProductInfo> {
	
	@Autowired
	private ProductInfoDao productInfoDao;
	
	@Override
	public ProductInfo get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<ProductInfo> findPage(Page<ProductInfo> page, ProductInfo productInfo) {
		return super.findPage(page, productInfo);
	}
	
	public List<ProductInfo> findExist(ProductInfo productInfo) {
		return productInfoDao.findExist(productInfo);
	}
	
	public ProductInfo findOneByProductId(String product_id) {
		return productInfoDao.findOneByProductId(product_id);
	}
	
	public List<ProductInfo> findClassifyUsed(ProductInfo productInfo) {
		return productInfoDao.findClassifyUsed(productInfo);
	}
	
	public List<ProductInfo> findBrandUsed(ProductInfo productInfo) {
		return productInfoDao.findBrandUsed(productInfo);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(ProductInfo productInfo) {
		if (StringUtils.isBlank(productInfo.getLogid())){
			productInfo.setCreate_time(DateUtil.getNow());
		}
		super.save(productInfo);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(ProductInfo productInfo) {
		super.delete(productInfo);
	}
}

