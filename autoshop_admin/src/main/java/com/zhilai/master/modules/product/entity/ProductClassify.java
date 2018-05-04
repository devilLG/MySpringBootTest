package com.zhilai.master.modules.product.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhilai.master.common.persistence.DataEntity;

/**
 * 商品分类Entity
 * @author zhangwei
 *
 */
public class ProductClassify extends DataEntity<ProductClassify> {
	private static final long serialVersionUID = -5291280172370932193L;
	private String logid;			//记录编号
	private String corp_id;			//公司编号
	private String corp_name;		//公司名称
	private String classify_id;		//分类编号
	private String classify_name;	//分类关键字
	private String classify_desc;	//分类描述
	private String parent_id;		//父级分类标识
	private String parent_ids;		//父级分类标识集合
	private String create_time;		//创建时间
	private int level;		        //级别
	private int sort_by;		    //排序
	private String wechart_image;	//微信图片地址
	private String android_image;	//安卓图片地址
	private String ios_image;		//IOS图片地址
	private String terminal_image;	//终端图片地址
	
	//虚拟字段
	private String chanel_type;	//图片类型
	
	private String parentName;//父级分类名称
	
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
	public String getClassify_id() {
		return classify_id;
	}
	public void setClassify_id(String classify_id) {
		this.classify_id = classify_id;
	}
	public String getClassify_name() {
		return classify_name;
	}
	public void setClassify_name(String classify_name) {
		this.classify_name = classify_name;
	}
	public String getClassify_desc() {
		return classify_desc;
	}
	public void setClassify_desc(String classify_desc) {
		this.classify_desc = classify_desc;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public String getParent_ids() {
		return parent_ids;
	}
	public void setParent_ids(String parent_ids) {
		this.parent_ids = parent_ids;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	@JsonIgnore
	public void sortList(List<ProductClassify> list, List<ProductClassify> sourcelist, String parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			ProductClassify e = sourcelist.get(i);
			if (e.getParent_id() != null && e.getParent_id().equals(parentId)){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						ProductClassify child = sourcelist.get(j);
						if (child.getParent_id() !=null && child.getParent_id().equals(e.getClassify_id())){
							sortList(list, sourcelist, e.getClassify_id(), true);
							break;
						}
					}
				}
			}
		}
	}
	
	@JsonIgnore
	public static String getRootId(){
		return "1";
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getSort_by() {
		return sort_by;
	}
	public void setSort_by(int sort_by) {
		this.sort_by = sort_by;
	}
	public String getWechart_image() {
		return wechart_image;
	}
	public void setWechart_image(String wechart_image) {
		this.wechart_image = wechart_image;
	}
	public String getAndroid_image() {
		return android_image;
	}
	public void setAndroid_image(String android_image) {
		this.android_image = android_image;
	}
	public String getIos_image() {
		return ios_image;
	}
	public void setIos_image(String ios_image) {
		this.ios_image = ios_image;
	}
	public String getTerminal_image() {
		return terminal_image;
	}
	public void setTerminal_image(String terminal_image) {
		this.terminal_image = terminal_image;
	}
	public String getChanel_type() {
		return chanel_type;
	}
	public void setChanel_type(String chanel_type) {
		this.chanel_type = chanel_type;
	}
	
}
