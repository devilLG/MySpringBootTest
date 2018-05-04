package com.zhilai.master.modules.broadcast.entity;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.sys.utils.UserUtils;

/**
 * 
 *Title:AdvertMedia
 *Description:媒体信息实体类
 *@author ChengJiawei
 *@date 2018年4月13日 下午2:28:04
 *
 */
public class AdvertMedia extends DataEntity<AdvertMedia>{
	private static final long serialVersionUID = -6107306998004791055L;

	private String logid; //记录编号
	private String mediaId; //媒体编号
	private String corpId; //公司编号
	private String corpName; //公司名称
	private String mediaName; //媒体名称
	private String mediaTitile; //媒体标题
	private String mediaType; //媒体类型 01:视频 02:图片
	private String mimeType; //媒体格式
	private String resolutionX; //媒体显示像素x(系统自动读取)
	private String resolutionY; //媒体显示像素y(系统自动读取)
	private String mediaAddress; //媒体地址
	private String mediaSize; //媒体大小KB
	private String curState; //状态 0:正常 1:失效
	private String stateTime; //状态时间
	private String description; //媒体描述
	private String createTime; //创建时间
	
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	@ExcelField(title="媒体名称", sort=1)
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	@ExcelField(title="媒体标题", sort=2)
	public String getMediaTitile() {
		return mediaTitile;
	}
	public void setMediaTitile(String mediaTitile) {
		this.mediaTitile = mediaTitile;
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
	@ExcelField(title="媒体像素X", sort=5)
	public String getResolutionX() {
		return resolutionX;
	}
	public void setResolutionX(String resolutionX) {
		this.resolutionX = resolutionX;
	}
	@ExcelField(title="媒体像素Y", sort=6)
	public String getResolutionY() {
		return resolutionY;
	}
	public void setResolutionY(String resolutionY) {
		this.resolutionY = resolutionY;
	}
	@ExcelField(title="媒体地址", sort=7)
	public String getMediaAddress() {
		return mediaAddress;
	}
	public void setMediaAddress(String mediaAddress) {
		this.mediaAddress = mediaAddress;
	}
	@ExcelField(title="媒体大小(KB)", sort=8)
	public String getMediaSize() {
		return mediaSize;
	}
	public void setMediaSize(String mediaSize) {
		this.mediaSize = mediaSize;
	}
	@ExcelField(title="状态", sort=9, dictType="media_state")
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
	@ExcelField(title="媒体描述", sort=11)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ExcelField(title="创建时间", sort=12)
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
			setCurState("0");
			User user  = UserUtils.getUser();
			if (user.getCorpId()!=null) {
				setCorpId(user.getCorpId());
				setCorpName(user.getCompany_name());
			}
		}
		this.createTime = DateUtils.getDateTime();
	}
	
}
