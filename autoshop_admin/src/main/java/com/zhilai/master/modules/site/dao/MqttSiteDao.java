package com.zhilai.master.modules.site.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.site.entity.MqttSite;
@MyBatisDao
public interface MqttSiteDao extends  CrudDao<MqttSite>{
	public List<MqttSite> findList();
	
	public MqttSite findBySite(MqttSite mqttSite);
	
	public MqttSite findByDev(String devId);
	
	public int findTotal();
	
	public int findTotalByDate(MqttSite ro);
	
	public List<MqttSite>findByState(String state);
	
	public void byStateUpdate(MqttSite mqttSite);

	public void insertList(List<MqttSite> list);
	
	public List<MqttSite> findByGroupId(String groupId);
}
