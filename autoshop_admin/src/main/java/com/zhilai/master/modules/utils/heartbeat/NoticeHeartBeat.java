package com.zhilai.master.modules.utils.heartbeat;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhilai.master.modules.person.dao.SiteControlLogDao;
import com.zhilai.master.modules.person.entity.SiteControlLog;
import com.zhilai.master.modules.site.dao.SiteControlConfigDao;
import com.zhilai.master.modules.site.dao.SiteControlDao;
import com.zhilai.master.modules.site.entity.SiteControl;
import com.zhilai.master.modules.site.entity.SiteControlConfig;
import com.zhilai.master.modules.utils.GParameter;

public abstract class NoticeHeartBeat {
	@Autowired
	private SiteControlConfigDao siteControlConfigDao;
	@Autowired
	private SiteControlDao siteControlDao;
	@Autowired
	private SiteControlLogDao siteControlLogDao;

	/**
	 * 
	 * 此方法描述的是：通知心跳
	 * 
	 * @author: wusijie
	 * @version: 2014-12-26 上午11:07:32
	 */
	public void pushHeartBeat(HeartBeat heartBeat) {
		SiteControlConfig siteControlConfigTemp, siteControlConfig = new SiteControlConfig();
		siteControlConfig.setSite_id(heartBeat.getSite_id());
		siteControlConfig.setTrade_code(heartBeat.getTrade_code());
		siteControlConfigTemp = siteControlConfigDao.findOne(siteControlConfig);
		if (siteControlConfigTemp != null && GParameter.siteControlConfigState_nomarl.equals(siteControlConfigTemp.getCur_state())) {
			// 心跳状态
			SiteControl siteControl = new SiteControl();
			siteControl.setSite_id(heartBeat.getSite_id());
			siteControl.setTrade_code(heartBeat.getTrade_code());
			siteControl = this.siteControlDao.findOne(siteControl);
			if (siteControl == null || siteControl.getLogid() == null) {
				siteControl = new SiteControl();
				siteControl.setCreate_time(DateUtil.getNow());
				siteControl.setLogid(DateUtil.getLogid());
				siteControl.setSite_id(heartBeat.getSite_id());
				siteControl.setSite_name(heartBeat.getSite_name());
				siteControl.setTrade_code(heartBeat.getTrade_code());
				siteControl.setTrade_name(heartBeat.getTrade_name());
				siteControl.setUpdate_time(DateUtil.getNow());
				siteControl.setCreate_time(DateUtil.getNow());
				siteControl.setPush_id(DateUtil.getLogid());
				siteControlDao.insert(siteControl);
				heartBeat.setCur_state(GParameter.siteControlLogState_sheart);
				logHeartBeat(heartBeat, siteControlConfigTemp);
			} else if (siteControl != null && siteControl.getLogid() != null && !"".equals(siteControl.getLogid())) {
				siteControl.setPush_id(DateUtil.getLogid());
				siteControl.setUpdate_time(DateUtil.getNow());
				siteControlDao.update(siteControl);
			}
			if (GParameter.siteControlConfigLog_on.equals(siteControlConfigTemp.getIs_log())) {
				heartBeat.setCur_state(GParameter.siteControlLogState_dataNoIuessed);
				logHeartBeat(heartBeat, siteControlConfigTemp);
			}
		}
	}

	/**
	 * 
	 * 此方法描述的是：删除心跳
	 * 
	 * @author: wusijie
	 * @version: 2014-12-26 上午11:07:38
	 */
	public void deleteHeartBeat(HeartBeat heartBeat) {
		SiteControl siteControl = new SiteControl();
		siteControl.setTrade_code(heartBeat.getTrade_code());
		siteControl.setSite_id(heartBeat.getSite_id());
		siteControlDao.delete(siteControl);
		SiteControlConfig siteControlConfig = new SiteControlConfig();
		siteControlConfig.setSite_id(heartBeat.getSite_id());
		siteControlConfig.setTrade_code(heartBeat.getTrade_code());
		SiteControlConfig siteControlConfigTemp = siteControlConfigDao.findOne(siteControlConfig);
		if (siteControlConfigTemp != null && GParameter.siteControlConfigState_nomarl.equals(siteControlConfigTemp.getCur_state())
				&& GParameter.siteControlConfigLog_on.equals(siteControlConfigTemp.getIs_log())) {
			heartBeat.setCur_state(GParameter.siteControlLogState_eheart);
			logHeartBeat(heartBeat);
		}
	}

	/**
	 * 
	 * 此方法描述的是：记录日志
	 * 
	 * @author: wusijie
	 * @version: 2014-12-26 上午11:07:43
	 */
	public void logHeartBeat(HeartBeat heartBeat, SiteControlConfig siteControlConfig) {
		if (GParameter.siteControlConfigLog_on.equals(siteControlConfig.getIs_log())) {
			SiteControlLog siteControlLog = new SiteControlLog();
			siteControlLog.setCreate_time(DateUtil.getNow());
			siteControlLog.setCur_state(heartBeat.getCur_state());
			if (!GParameter.siteControlLogState_sheart.equals(heartBeat.getCur_state()) && !GParameter.siteControlLogState_eheart.equals(heartBeat.getCur_state()))
				siteControlLog.setIssued_key(heartBeat.getIssued_key());
			else
				siteControlLog.setIssued_key("");
			siteControlLog.setLogid(DateUtil.getLogid());
			siteControlLog.setSite_id(heartBeat.getSite_id());
			siteControlLog.setSite_name(heartBeat.getSite_name());
			siteControlLog.setTrade_code(heartBeat.getTrade_code());
			siteControlLog.setTrade_name(heartBeat.getTrade_name());
			siteControlLogDao.insert(siteControlLog);
		}
	}

	/**
	 * 
	 * 此方法描述的是：记录日志
	 * 
	 * @author: wusijie
	 * @version: 2014-12-26 上午11:07:43
	 */
	public void logHeartBeat(HeartBeat heartBeat) {
		SiteControlConfig siteControlConfigTemp, siteControlConfig = new SiteControlConfig();
		siteControlConfig.setSite_id(heartBeat.getSite_id());
		siteControlConfig.setTrade_code(heartBeat.getTrade_code());
		siteControlConfigTemp = siteControlConfigDao.findOne(siteControlConfig);
		if (siteControlConfigTemp != null && GParameter.siteControlConfigState_nomarl.equals(siteControlConfigTemp.getCur_state())
				&& GParameter.siteControlConfigLog_on.equals(siteControlConfigTemp.getIs_log())) {
			SiteControlLog siteControlLog = new SiteControlLog();
			siteControlLog.setCreate_time(DateUtil.getNow());
			siteControlLog.setCur_state(heartBeat.getCur_state());
			if (!GParameter.siteControlLogState_sheart.equals(heartBeat.getCur_state()) && !GParameter.siteControlLogState_eheart.equals(heartBeat.getCur_state()))
				siteControlLog.setIssued_key(heartBeat.getIssued_key());
			else
				siteControlLog.setIssued_key("");
			siteControlLog.setLogid(DateUtil.getLogid());
			siteControlLog.setSite_id(heartBeat.getSite_id());
			siteControlLog.setSite_name(heartBeat.getSite_name());
			siteControlLog.setTrade_code(heartBeat.getTrade_code());
			siteControlLog.setTrade_name(heartBeat.getTrade_name());
			siteControlLogDao.insert(siteControlLog);
		}
	}

	public abstract void pushHeart(HeartBeat heartBeat);

	public abstract void deleteHeart(HeartBeat heartBeat);

}
