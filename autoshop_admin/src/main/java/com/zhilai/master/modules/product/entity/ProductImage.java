package com.zhilai.master.modules.product.entity;

import com.zhilai.master.common.persistence.DataEntity;

/**
 * 商品图片Entity
 * @author zhangwei
 *
 */
public class ProductImage extends DataEntity<ProductImage> {
	private static final long serialVersionUID = -3316176715402094073L;
	private String logid;		//记录编号
	private String image_id;
	private String corp_id;		//公司编号
	private String corp_name;	//公司名称
	private String product_id;	//商品编号
	private String product_name;//商品名称
	private String chanel_type;	//图片类型 01:终端 02:微信 03:android 04:ios
	private String mime_type;	//图片格式类型
	private String image_url;	//图片http地址
	private String image_px;	//像素
	private String image_width;	//宽度
	private String image_height;//高度
	private String create_time;	//创建时间
	
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
	public String getCorp_name() {
		return corp_name;
	}
	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getChanel_type() {
		return chanel_type;
	}
	public void setChanel_type(String chanel_type) {
		this.chanel_type = chanel_type;
	}
	public String getMime_type() {
		return mime_type;
	}
	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getImage_px() {
		return image_px;
	}
	public void setImage_px(String image_px) {
		this.image_px = image_px;
	}
	public String getImage_width() {
		return image_width;
	}
	public void setImage_width(String image_width) {
		this.image_width = image_width;
	}
	public String getImage_height() {
		return image_height;
	}
	public void setImage_height(String image_height) {
		this.image_height = image_height;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getImage_id() {
		return image_id;
	}
	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
	
}
