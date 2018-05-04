package com.zhilai.master.task.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.modules.product.dao.ProductFavourableDao;
import com.zhilai.master.modules.product.dao.ProductInfoDao;
import com.zhilai.master.modules.product.entity.ProductFavourable;
import com.zhilai.master.modules.product.entity.ProductInfo;
import com.zhilai.master.modules.utils.GParameter;

@Service
@Transactional(readOnly = true)
public class TaskProductFavourableEndService {
	private static Logger log = Logger.getLogger(TaskProductFavourableEndService.class);
	@Autowired
	private ProductFavourableDao productFavourableDao;
	@Autowired
	private ProductInfoDao productInfoDao;

	@Transactional(readOnly = false)
	public String execute(Date date) {
		String retCode = "0";
		try {
			String favourable_etime = DateUtils.Date();
			List<ProductFavourable> productFavourableList = productFavourableDao.findProductFavourableEndList(favourable_etime);
			if(productFavourableList != null && productFavourableList.size() > 0){
				for(ProductFavourable productFavourable : productFavourableList){
					if(productFavourable.getFavourable_type().equals(GParameter.favourableType_all)){
						ProductInfo productInfo = productInfoDao.findOneByProductId(productFavourable.getProduct_id());
						if(productInfo != null){
							productInfo.setNormal_price(productFavourable.getNomarl_price());
							productInfo.setProduct_title(productFavourable.getNomarl_title());
							productInfo.setDetail_url(productFavourable.getNomarl_url());
							productInfo.setPromotion_desc("");
							productInfoDao.update(productInfo);
							productFavourable.setCur_state(GParameter.productFavourableState_finish);
							productFavourableDao.update(productFavourable);
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("TaskProductFavourableEnd is fail");
			e.printStackTrace();
		}
		return retCode;
	}

}
