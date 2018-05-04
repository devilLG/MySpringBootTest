package com.zhilai.master.modules.monitor.entity;

import java.util.List;

import com.zhilai.master.common.persistence.DataEntity;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.annotation.ExcelField;

/**
 * 
 *Title:DeviceInfo
 *Description:设备信息表实体类
 *@author ChengJiawei
 *@date 2018年3月31日 下午4:54:09
 *
 */
public class DeviceInfo extends DataEntity<DeviceInfo>{
	
	private static final long serialVersionUID = 1L;

	private String logid; //记录编号
	private String deviceId; //设备编号
	private String deviceCode; //设备编码(引用数据字典)
	private String deviceName; //设备名称(引用数据字典)
	private String deviceModel; //设备型号
	private String deviceBard; // 设备品牌
	private String deviceSize; //设备尺寸 长*宽*高
	private String deviceMfrs; //设备生产商
	private String devicePic; //设备图片地址信息
	private String specialNature; //设备属性特别描述
	private String devicePostion; //设备所在柜子位置描述
	private String deviceEffect; //设备提供的功能描述
	private Integer maintenance; //维护周期(天)
	private String errorLevel; //故障严重等级 1:严重 2:中等 3:一般 4:警报
	private String createTime; //创建时间
	
	//虚拟字段
	private List<DeviceEnv> deviceEnvList; //设备运行参数配置
	private String createTimeStart; //创建开始时间
	private String createTimeEnd; //创建结束时间
	private String deviceLength; //长
	private String deviceWidth; //宽
	private String deviceHeight; //高
	
	
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	@ExcelField(title="设备名称", sort=1, dictType="device_info")
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	@ExcelField(title="设备型号", sort=2, dictType="device_model")
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	@ExcelField(title="设备品牌", sort=3, dictType="device_bard")
	public String getDeviceBard() {
		return deviceBard;
	}
	public void setDeviceBard(String deviceBard) {
		this.deviceBard = deviceBard;
	}
	@ExcelField(title="设备尺寸(长x宽x高 cm³)", sort=4)
	public String getDeviceSize() {
		return deviceSize;
	}
	public void setDeviceSize(String deviceSize) {
		this.deviceSize = deviceSize;
	}
	@ExcelField(title="设备生产商", sort=5, dictType="device_mfrs")
	public String getDeviceMfrs() {
		return deviceMfrs;
	}
	public void setDeviceMfrs(String deviceMfrs) {
		this.deviceMfrs = deviceMfrs;
	}
	@ExcelField(title="设备图片地址信息", sort=6)
	public String getDevicePic() {
		return devicePic;
	}
	public void setDevicePic(String devicePic) {
		this.devicePic = devicePic;
	}
	@ExcelField(title="设备属性特别描述", sort=7)
	public String getSpecialNature() {
		return specialNature;
	}
	public void setSpecialNature(String specialNature) {
		this.specialNature = specialNature;
	}
	@ExcelField(title="设备所在柜子位置描述", sort=8)
	public String getDevicePostion() {
		return devicePostion;
	}
	public void setDevicePostion(String devicePostion) {
		this.devicePostion = devicePostion;
	}
	@ExcelField(title="设备提供的功能描述", sort=9)
	public String getDeviceEffect() {
		return deviceEffect;
	}
	public void setDeviceEffect(String deviceEffect) {
		this.deviceEffect = deviceEffect;
	}
	@ExcelField(title="维护周期(天)", sort=10)
	public Integer getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(Integer maintenance) {
		this.maintenance = maintenance;
	}
	@ExcelField(title="故障等级", sort=11, dictType="error_level_info")
	public String getErrorLevel() {
		return errorLevel;
	}
	public void setErrorLevel(String errorLevel) {
		this.errorLevel = errorLevel;
	}
	@ExcelField(title="创建时间", sort=12)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	

	public List<DeviceEnv> getDeviceEnvList() {
		return deviceEnvList;
	}
	public void setDeviceEnvList(List<DeviceEnv> deviceEnvList) {
		this.deviceEnvList = deviceEnvList;
	}
	public String getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public String getDeviceLength() {
		return deviceLength;
	}
	public void setDeviceLength(String deviceLength) {
		this.deviceLength = deviceLength;
	}
	public String getDeviceWidth() {
		return deviceWidth;
	}
	public void setDeviceWidth(String deviceWidth) {
		this.deviceWidth = deviceWidth;
	}
	public String getDeviceHeight() {
		return deviceHeight;
	}
	public void setDeviceHeight(String deviceHeight) {
		this.deviceHeight = deviceHeight;
	}
	
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			setLogid(System.nanoTime()+"");
		}
		this.createTime = DateUtils.getDateTime();
	}
	
	
}
