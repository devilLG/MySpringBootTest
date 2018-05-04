package com.zhilai.master.modules.product.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 商品优惠Entity
 * @author zhangwei
 *
 */
public class ProductFavourable extends DataEntity<ProductFavourable> {
	private static final long serialVersionUID = -2724464546702402179L;
	private String logid;			//记录编号
	private String favourable_id;	//优惠编号
	private String corp_id;			//公司编号
	private String corp_name;		//公司名称
	private String favourable_type;	//优惠对象 01:站点 02:全网
	private String site_id;			//优惠站点编号
	private String product_id;		//商品编号
	private String product_name;	//商品名称
	private String bar_code;		//条码
	private String favourable_stime;//优惠开始日期(yyyy-MM-dd)
	private String favourable_etime;//优惠结束日期(yyyy-MM-dd)
	private String favourable_price;//优惠价格(¥00.00)
	private String nomarl_price;	//恢复价格(¥00.00)
	private String favourable_title;//优惠商品标题
	private String nomarl_title;	//恢复商品标题
	private String favourable_url;	//优惠商品详情地址
	private String nomarl_url;		//恢复商品详情地址
	private String favourable_desc;	//优惠描述
	private String cur_state;		//状态 0:待执行 1:正在执行 2:执行完成
	private String create_time;		//创建时间
	
	//虚拟字段
	private String site_name;		
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getFavourable_id() {
		return favourable_id;
	}
	public void setFavourable_id(String favourable_id) {
		this.favourable_id = favourable_id;
	}
	public String getCorp_id() {
		return corp_id;
	}
	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	public String getCorp_name() {
		return corp_name;
	}
	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}
	@ExcelField(title="优惠类型", sort=15, dictType="favourable_type")
	public String getFavourable_type() {
		return favourable_type;
	}
	public void setFavourable_type(String favourable_type) {
		this.favourable_type = favourable_type;
	}
	@ExcelField(title="优惠对象", sort=10)
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	@ExcelField(title="商品编码", sort=20)
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	@ExcelField(title="商品名称", sort=25)
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	@ExcelField(title="商品条码", sort=30)
	public String getBar_code() {
		return bar_code;
	}
	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}
	@ExcelField(title="优惠开始日期", sort=70)
	public String getFavourable_stime() {
		return favourable_stime;
	}
	public void setFavourable_stime(String favourable_stime) {
		this.favourable_stime = favourable_stime;
	}
	@ExcelField(title="优惠结束日期", sort=75)
	public String getFavourable_etime() {
		return favourable_etime;
	}
	public void setFavourable_etime(String favourable_etime) {
		this.favourable_etime = favourable_etime;
	}
	@ExcelField(title="优惠商品标价（元）", sort=55)
	public String getFavourable_price() {
		return favourable_price;
	}
	public void setFavourable_price(String favourable_price) {
		this.favourable_price = favourable_price;
	}
	@ExcelField(title="恢复商品标价（元）", sort=40)
	public String getNomarl_price() {
		return nomarl_price;
	}
	public void setNomarl_price(String nomarl_price) {
		this.nomarl_price = nomarl_price;
	}
	@ExcelField(title="优惠商品标题", sort=50)
	public String getFavourable_title() {
		return favourable_title;
	}
	public void setFavourable_title(String favourable_title) {
		this.favourable_title = favourable_title;
	}
	@ExcelField(title="恢复商品标题", sort=35)
	public String getNomarl_title() {
		return nomarl_title;
	}
	public void setNomarl_title(String nomarl_title) {
		this.nomarl_title = nomarl_title;
	}
	@ExcelField(title="优惠商品介绍地址", sort=60)
	public String getFavourable_url() {
		return favourable_url;
	}
	public void setFavourable_url(String favourable_url) {
		this.favourable_url = favourable_url;
	}
	@ExcelField(title="恢复商品介绍地址", sort=45)
	public String getNomarl_url() {
		return nomarl_url;
	}
	public void setNomarl_url(String nomarl_url) {
		this.nomarl_url = nomarl_url;
	}
	@ExcelField(title="优惠描述", sort=65)
	public String getFavourable_desc() {
		return favourable_desc;
	}
	public void setFavourable_desc(String favourable_desc) {
		this.favourable_desc = favourable_desc;
	}
	@ExcelField(title="状态", sort=80, dictType="product_favourable_state")
	public String getCur_state() {
		return cur_state;
	}
	public void setCur_state(String cur_state) {
		this.cur_state = cur_state;
	}
	@ExcelField(title="创建时间", sort=85)
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getSite_name() {
		return site_name;
	}
	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

}
