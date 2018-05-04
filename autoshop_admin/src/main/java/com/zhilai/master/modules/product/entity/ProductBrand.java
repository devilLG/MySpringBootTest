package com.zhilai.master.modules.product.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 品牌公司信息Entity
 * @author zhangwei
 *
 */
public class ProductBrand extends DataEntity<ProductBrand> {
	private static final long serialVersionUID = 115723358076704876L;
	private String logid;			//记录编号
	private String corp_id;			//公司编号
	private String corp_name;		//公司名称
	private String brand_id;		//品牌编号
	private String brand_name;		//品牌名称
	private String brandCorp_name;	//品牌公司名
	private String establish_time;	//成立时间
	private String industry;		//所属行业
	private String logo;			//logo的http地址
	private String create_time;		//创建时间
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getCorp_id() {
		return corp_id;
	}
	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	@ExcelField(title="归属公司", sort=40)
	public String getCorp_name() {
		return corp_name;
	}
	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}
	@ExcelField(title="品牌编号", sort=10)
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	@ExcelField(title="品牌名称", sort=15)
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	@ExcelField(title="品牌公司", sort=20)
	public String getBrandCorp_name() {
		return brandCorp_name;
	}
	public void setBrandCorp_name(String brandCorp_name) {
		this.brandCorp_name = brandCorp_name;
	}
	@ExcelField(title="成立时间", sort=25)
	public String getEstablish_time() {
		return establish_time;
	}
	public void setEstablish_time(String establish_time) {
		this.establish_time = establish_time;
	}
	@ExcelField(title="所属行业", sort=30)
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	@ExcelField(title="Logo地址", sort=35)
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@ExcelField(title="创建时间", sort=45)
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
}
