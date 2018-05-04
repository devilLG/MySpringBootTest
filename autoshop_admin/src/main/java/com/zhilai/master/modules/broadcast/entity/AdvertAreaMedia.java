package com.zhilai.master.modules.broadcast.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.sys.utils.UserUtils;

/**
 * 
 *Title:AdvertAreaMedia
 *Description:区域媒体实体类
 *@author ChengJiawei
 *@date 2018年4月17日 下午2:34:56
 *
 */
public class AdvertAreaMedia extends DataEntity<AdvertAreaMedia>{
	private static final long serialVersionUID = -8537655660315150956L;

	private String logid; //记录编号
	private String playId; //区域播放媒体
	private String areaId; //区域编号
	private String areaName; //区域名称
	private String configId; //播控配置编号
	private String configName; //配置名称
	private String mediaType; //媒体类型 01:视频 02:图片
	private String mimeType; //媒体格式
	private String mediaId; //媒体编号
	private String mediaAddress; //媒体地址
	private String mediaSize; //媒体大小KB
	private String mediaTime; //播放时间
	private String mediaSeq; //播放顺序
	private String curState; //状态 0:正常 1:失效
	private String stateTime; //状态时间
	private String createTime; //创建时间
	
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getPlayId() {
		return playId;
	}
	public void setPlayId(String playId) {
		this.playId = playId;
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
	@ExcelField(title="媒体类型", sort=3, dictType="media_type")
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	@ExcelField(title="媒体格式", sort=4, dictType="mime_type")
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	@ExcelField(title="媒体编号", sort=5)
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	@ExcelField(title="媒体地址", sort=6)
	public String getMediaAddress() {
		return mediaAddress;
	}
	public void setMediaAddress(String mediaAddress) {
		this.mediaAddress = mediaAddress;
	}
	@ExcelField(title="媒体大小(KB)", sort=7)
	public String getMediaSize() {
		return mediaSize;
	}
	public void setMediaSize(String mediaSize) {
		this.mediaSize = mediaSize;
	}
	public String getMediaTime() {
		return mediaTime;
	}
	public void setMediaTime(String mediaTime) {
		this.mediaTime = mediaTime;
	}
	@ExcelField(title="播放顺序", sort=8)
	public String getMediaSeq() {
		return mediaSeq;
	}
	public void setMediaSeq(String mediaSeq) {
		this.mediaSeq = mediaSeq;
	}
	@ExcelField(title="状态", sort=9 , dictType="Cur_state")
	public String getCurState() {
		return curState;
	}
	public void setCurState(String curState) {
		this.curState = curState;
	}
	@ExcelField(title="状态时间", sort=10)
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}
	@ExcelField(title="创建时间", sort=11)
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
			setPlayId(System.nanoTime()+"");
			setCurState("0");
		}
		this.createTime = DateUtils.getDateTime();
	}
	
}
