package com.zhilai.master.modules.product.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 商品信息Entity
 * @author zhangwei
 *
 */
public class ProductInfo extends DataEntity<ProductInfo> {
	private static final long serialVersionUID = 9133336507721128770L;
	private String logid;			//记录编号
	private String corp_id;			//公司编号
	private String corp_name;		//公司名称
	private String product_id;		//商品编号
	private String product_name;	//商品名称
	private String product_title;	//商品标题
	private String product_model;	//商品型号
	private String unit_id;			//单位(件/瓶/套)从数据字典获取
	private String productType_id;	//商品分类编号
	private String productType_name;//商品分类名称
	private String temper_type;		//温度环境类型 1:常温 2:冷藏 3:保鲜
	private String brand_id;		//商品品牌编号
	private String brand_name;		//商品品牌名称
	private String bar_code;		//商品条码
	private String normal_price;	//商品标准价 (¥00.00)
	private String pro_length;		//商品包装的长(cm)
	private String pro_width;		//商品包装的宽(cm)
	private String pro_height;		//商品包装的高(cm)
	private String pro_desc;		//商品的描述
	private String promotion_desc;	//商品促销描述
	private String pay_image;		//支付图片地址
	private String detail_url;		//商品介绍信息url地址
	private String create_time;		//创建时间
	private String overdue_date;	//保质期
	private String metering_num;	//容量
	private String metering_name;	//容量单位名称
	private String metering_id;	    //容量单位编号
	
	//虚拟字段
	private String product_beginPrice;
	private String product_endPrice;
	private String chanel_type;
	private String image_url;
	private String[] productType_ids;	

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
	@ExcelField(title="商品编码", sort=10)
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	@ExcelField(title="商品名称", sort=20)
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	@ExcelField(title="商品标题", sort=25)
	public String getProduct_title() {
		return product_title;
	}
	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}
	@ExcelField(title="商品型号", sort=30)
	public String getProduct_model() {
		return product_model;
	}
	public void setProduct_model(String product_model) {
		this.product_model = product_model;
	}
	@ExcelField(title="计量单位", sort=35, dictType="unit_id")
	public String getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	public String getProductType_id() {
		return productType_id;
	}
	public void setProductType_id(String productType_id) {
		this.productType_id = productType_id;
	}
	@ExcelField(title="商品分类", sort=45)
	public String getProductType_name() {
		return productType_name;
	}
	public void setProductType_name(String productType_name) {
		this.productType_name = productType_name;
	}
	@ExcelField(title="温度环境", sort=55, dictType="temper_type")
	public String getTemper_type() {
		return temper_type;
	}
	public void setTemper_type(String temper_type) {
		this.temper_type = temper_type;
	}
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	@ExcelField(title="品牌名称", sort=50)
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	@ExcelField(title="商品条码", sort=15)
	public String getBar_code() {
		return bar_code;
	}
	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}
	@ExcelField(title="标准价（元）", sort=65)
	public String getNormal_price() {
		return normal_price;
	}
	public void setNormal_price(String normal_price) {
		this.normal_price = normal_price;
	}
	@ExcelField(title="长(cm)", sort=70)
	public String getPro_length() {
		return pro_length;
	}
	public void setPro_length(String pro_length) {
		this.pro_length = pro_length;
	}
	@ExcelField(title="宽(cm)", sort=75)
	public String getPro_width() {
		return pro_width;
	}
	public void setPro_width(String pro_width) {
		this.pro_width = pro_width;
	}
	@ExcelField(title="高(cm)", sort=80)
	public String getPro_height() {
		return pro_height;
	}
	public void setPro_height(String pro_height) {
		this.pro_height = pro_height;
	}
	@ExcelField(title="商品描述", sort=85)
	public String getPro_desc() {
		return pro_desc;
	}
	public void setPro_desc(String pro_desc) {
		this.pro_desc = pro_desc;
	}
	public String getPromotion_desc() {
		return promotion_desc;
	}
	public void setPromotion_desc(String promotion_desc) {
		this.promotion_desc = promotion_desc;
	}
	public String getPay_image() {
		return pay_image;
	}
	public void setPay_image(String pay_image) {
		this.pay_image = pay_image;
	}
	@ExcelField(title="商品介绍地址", sort=90)
	public String getDetail_url() {
		return detail_url;
	}
	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}
	@ExcelField(title="创建时间", sort=95)
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	@ExcelField(title="保质期(H)", sort=60)
	public String getOverdue_date() {
		return overdue_date;
	}
	public void setOverdue_date(String overdue_date) {
		this.overdue_date = overdue_date;
	}
	public String getProduct_beginPrice() {
		return product_beginPrice;
	}
	public void setProduct_beginPrice(String product_beginPrice) {
		this.product_beginPrice = product_beginPrice;
	}
	public String getProduct_endPrice() {
		return product_endPrice;
	}
	public void setProduct_endPrice(String product_endPrice) {
		this.product_endPrice = product_endPrice;
	}
	public String getChanel_type() {
		return chanel_type;
	}
	public void setChanel_type(String chanel_type) {
		this.chanel_type = chanel_type;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String[] getProductType_ids() {
		return productType_ids;
	}
	public void setProductType_ids(String[] productType_ids) {
		this.productType_ids = productType_ids;
	}
	@ExcelField(title="容量", sort=36)
	public String getMetering_num() {
		return metering_num;
	}
	public void setMetering_num(String metering_num) {
		this.metering_num = metering_num;
	}
	@ExcelField(title="容量单位", sort=37)
	public String getMetering_name() {
		return metering_name;
	}
	public void setMetering_name(String metering_name) {
		this.metering_name = metering_name;
	}
	public String getMetering_id() {
		return metering_id;
	}
	public void setMetering_id(String metering_id) {
		this.metering_id = metering_id;
	}
	

}
