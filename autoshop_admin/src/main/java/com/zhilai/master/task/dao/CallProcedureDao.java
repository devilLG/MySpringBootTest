package com.zhilai.master.task.dao;

import java.util.Map;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface CallProcedureDao extends CrudDao<String> {
	/**
	 * 站点销售日报
	 * @param p_beginTime
	 * @param p_endTime
	 * @return
	 */
	public String SiteSaleDay(Map<String, Object> param);
	/**
	 * 站点销售月报
	 * @param month
	 * @param year
	 * @return
	 */
	public String SiteSaleMonth(Map<String, Object> param);
	/**
	 * 站点销售年报
	 * @param year
	 * @return
	 */
	public String SiteSaleYear(Map<String, Object> param);
	/**
	 * 商品销售日报
	 * @param p_beginTime
	 * @param p_endTime
	 * @return
	 */
	public String ProductSaleDay(Map<String, Object> param);
	/**
	 * 商品销售月报
	 * @param month
	 * @param year
	 * @return
	 */
	public String ProductSaleMonth(Map<String, Object> param);
	/**
	 * 商品销售年报
	 * @param year
	 * @return
	 */
	public String ProductSaleYear(Map<String, Object> param);
	
	/**
	 * 分类销售日报
	 * @param p_beginTime
	 * @param p_endTime
	 * @return
	 */
	public String ClassifySaleDay(Map<String, Object> param);
	/**
	 * 分类销售月报
	 * @param month
	 * @param year
	 * @return
	 */
	public String ClassifySaleMonth(Map<String, Object> param);
	/**
	 * 分类销售年报
	 * @param year
	 * @return
	 */
	public String ClassifySaleYear(Map<String, Object> param);
	/**
	 * 品牌销售日报
	 * @param p_beginTime
	 * @param p_endTime
	 * @return
	 */
	public String BrandSaleDay(Map<String, Object> param);
	/**
	 * 品牌销售月报
	 * @param month
	 * @param year
	 * @return
	 */
	public String BrandSaleMonth(Map<String, Object> param);
	/**
	 * 品牌销售年报
	 * @param year
	 * @return
	 */
	public String BrandSaleYear(Map<String, Object> param);
	/**
	 * 公司销售日报
	 * @param p_beginTime
	 * @param p_endTime
	 * @return
	 */
	public String CorpSaleDay(Map<String, Object> param);
	/**
	 * 公司销售月报
	 * @param month
	 * @param year
	 * @return
	 */
	public String CorpSaleMonth(Map<String, Object> param);
	/**
	 * 公司销售年报
	 * @param year
	 * @return
	 */
	public String CorpSaleYear(Map<String, Object> param);
	
}
