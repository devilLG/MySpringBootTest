package com.zhilai.master.modules.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.site.dao.SiteGoodsDao;
import com.zhilai.master.modules.site.entity.SiteGoods;

@Service
@Transactional(readOnly = true)
public class SiteGoodsService extends CrudService<SiteGoodsDao, SiteGoods> {
	@Autowired
	private SiteGoodsDao siteGoodsDao;
	
	@Override
	public SiteGoods get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<SiteGoods> findPage(Page<SiteGoods> page, SiteGoods siteGoods) {
		return super.findPage(page, siteGoods);
	}
	
	public SiteGoods findOne(SiteGoods siteGoods) {
		return siteGoodsDao.findOne(siteGoods);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(SiteGoods siteGoods) {
		super.delete(siteGoods);
	}
}

