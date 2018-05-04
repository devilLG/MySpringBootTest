package com.zhilai.master.task.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.modules.product.dao.ProductFavourableDao;
import com.zhilai.master.modules.product.dao.ProductInfoDao;
import com.zhilai.master.modules.product.entity.ProductFavourable;
import com.zhilai.master.modules.product.entity.ProductInfo;
import com.zhilai.master.modules.utils.GParameter;

@Service
@Transactional(readOnly = true)
public class TaskProductFavourableStartService {
	private static Logger log = Logger.getLogger(TaskProductFavourableStartService.class);
	@Autowired
	private ProductFavourableDao productFavourableDao;
	@Autowired
	private ProductInfoDao productInfoDao;

	@Transactional(readOnly = false)
	public String execute(Date date) {
		String retCode = "0";
		try {
			String favourable_stime = DateUtil.formatDate(DateUtil.getDate(new Date(), 1));
			List<ProductFavourable> productFavourableList = productFavourableDao.findProductFavourableStartList(favourable_stime);
			if(productFavourableList != null && productFavourableList.size() > 0){
				for(ProductFavourable productFavourable : productFavourableList){
					if(productFavourable.getFavourable_type().equals(GParameter.favourableType_all)){
						ProductInfo productInfo = productInfoDao.findOneByProductId(productFavourable.getProduct_id());
						if(productInfo != null){
							productInfo.setNormal_price(productFavourable.getFavourable_price());
							productInfo.setProduct_title(productFavourable.getFavourable_title());
							productInfo.setDetail_url(productFavourable.getFavourable_url());
							productInfo.setPromotion_desc(productFavourable.getFavourable_desc());
							productInfoDao.update(productInfo);
							productFavourable.setCur_state(GParameter.productFavourableState_doing);
							productFavourableDao.update(productFavourable);
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("TaskProductFavourableStart is fail");
			e.printStackTrace();
		}
		return retCode;
	}

}
