/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.site.service;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.site.dao.SiteCmdDao;
import com.zhilai.master.modules.site.entity.SiteCmd;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.utils.heartbeat.HeartBeat;
import com.zhilai.master.modules.utils.heartbeat.SiteNoticeHeartBeat;

/**
 * 站点Service
 * @author zhilai
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class SiteCmdService extends CrudService<SiteCmdDao, SiteCmd> {
	@Autowired
	private SiteCmdDao siteCmdDao;
	
	@Autowired
	private SiteNoticeHeartBeat siteNoticeHeartBeat;

	public Page<SiteCmd> findPage(Page<SiteCmd> page, SiteCmd siteCmd) {
		siteCmd.setPage(page);
		page.setList(super.findList(siteCmd));
		return page;
		
		//return super.findPage(page, site);
	}

	@Transactional(readOnly = false)
	public void save(SiteCmd siteCmd) {
		String id = siteCmd.getCmd_id();
		SiteCmd site = super.get(id);
		if (site == null) {
			siteCmd.preInsert();
			siteCmd.setCreate_time(DateUtil.getNow());
			siteCmd.setDown_state(GParameter.downState_wait);
			siteCmd.setDown_time(DateUtil.getNow());
			siteCmdDao.insert(siteCmd);
			HeartBeat heartBeat = new HeartBeat();
			heartBeat.setSite_id(siteCmd.getSite_id());
			heartBeat.setSite_name(siteCmd.getSite_name());
			heartBeat.setTrade_code(GParameter.issued_siteCmd_trade_code);
			heartBeat.setTrade_name(GParameter.issued_siteCmd_trade_desc);
			heartBeat.setCreate_time(DateUtil.getNow());
			heartBeat.setUpdate_time(DateUtil.getNow());
			heartBeat.setPush_id(DateUtil.getLogid());
			heartBeat.setIssued_key(siteCmd.getCmd_id());
			siteNoticeHeartBeat.pushHeart(heartBeat);
		} else {
			// 更新数据
			siteCmd.preUpdate();
			siteCmdDao.update(siteCmd);
		}
		//super.save(site);
	}
	
	@Transactional(readOnly = false)
	public void delete(SiteCmd siteCmd) {
		super.delete(siteCmd);
	}
	
}
