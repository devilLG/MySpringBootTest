package com.zhilai.master.modules.site.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.site.entity.SiteGoods;

@MyBatisDao
public interface SiteGoodsDao extends CrudDao<SiteGoods> {
	public List<SiteGoods> findBySiteId(String site_id);
	public int deleteByCabinetId(SiteGoods box);
	public int deleteBysid(String site_id);
	public SiteGoods findOne(SiteGoods siteGoods);
}
