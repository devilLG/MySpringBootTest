package com.zhilai.master.modules.broadcast.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 
 *Title:AdvertArea
 *Description:区域配置实体类
 *@author ChengJiawei
 *@date 2018年4月17日 上午10:04:21
 *
 */

public class AdvertArea extends DataEntity<AdvertArea>{
	private static final long serialVersionUID = -8893811073463738235L;

	private String logid; //记录编号
	private String areaId; //区域编号
	private String areaName; //区域名称
	private String configId; //播控配置编号
	private String configName; //配置名称
	private String seqId; //排列顺序 从1开始递增
	private String resolutionX; //屏幕显示像素x轴
	private String resolutionY; //屏幕显示像素y轴
	private String mediaType; //媒体类型 01:视频 02:图片
	private String mimeType; //媒体格式
	private String description; //配置描述
	private String createTime; //创建时间
	
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@ExcelField(title="区域名称", sort=2)
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	@ExcelField(title="配置名称", sort=1)
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	@ExcelField(title="排列顺序", sort=3)
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	@ExcelField(title="媒体像素X", sort=4)
	public String getResolutionX() {
		return resolutionX;
	}
	public void setResolutionX(String resolutionX) {
		this.resolutionX = resolutionX;
	}
	@ExcelField(title="媒体像素Y", sort=5)
	public String getResolutionY() {
		return resolutionY;
	}
	public void setResolutionY(String resolutionY) {
		this.resolutionY = resolutionY;
	}
	@ExcelField(title="媒体类型", sort=6, dictType="media_type")
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	@ExcelField(title="媒体格式", sort=7, dictType="mime_type")
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	@ExcelField(title="配置描述", sort=8)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ExcelField(title="创建时间", sort=9)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			setLogid(System.nanoTime()+"");
			setConfigId(System.nanoTime()+"");
		}
		this.createTime = DateUtils.getDateTime();
	}
	
}
