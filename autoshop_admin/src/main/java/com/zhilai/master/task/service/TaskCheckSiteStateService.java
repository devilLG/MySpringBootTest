package com.zhilai.master.task.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.putils.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.modules.parmter.dao.MsgSendDao;
import com.zhilai.master.modules.parmter.entity.MsgSend;
import com.zhilai.master.modules.person.dao.SitePersonDao;
import com.zhilai.master.modules.person.entity.SitePerson;
import com.zhilai.master.modules.site.dao.AsSiteDao;
import com.zhilai.master.modules.site.dao.SiteStateDao;
import com.zhilai.master.modules.site.entity.AsSite;
import com.zhilai.master.modules.site.entity.SiteState;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

@Service
@Transactional(readOnly = true)
public class TaskCheckSiteStateService {
	private static Logger log = Logger.getLogger(TaskCheckSiteStateService.class);
	@Autowired
	private AsSiteDao siteDao;
	@Autowired
	private SiteStateDao siteStateDao;
	@Autowired
	private MsgSendDao msgSendDao;
	@Autowired
	private SitePersonDao sitePersonDao;

	@Transactional(readOnly = false)
	public String execute(Date date) {
		String retCode = "0";
		try {
			String refresh_interval=DictUtils.getDictValue("siteComm_keepAlive", "siteComm", "300");
			if(null==refresh_interval) {
				log.error("parameter siteComm_keepAlive not define!"); 
				return "1";
			}
			String refresh_time=DateUtil.formatDateTime(DateUtils.addMilliseconds(date, -(Integer.parseInt(refresh_interval))*1000*3));
			//查询刷新时间在当前时间15分钟前且刷新状态为在线的数据
			List<SiteState> siteStateList = siteStateDao.findByRefreshTime(refresh_time);
			if(null != siteStateList && siteStateList.size() > 0){
				for(SiteState siteState : siteStateList){
					siteState.setRefresh_state(GParameter.siteUseState_offline);
					siteState.setRefresh_time(DateUtil.getNow());
					siteStateDao.updateRefresh(siteState);
					AsSite site = siteDao.get(siteState.getSite_id());
					site.setSite_state(GParameter.siteUseState_offline);
					siteDao.updateSiteState(site);
					SitePerson sitePerson = new SitePerson();
					sitePerson.setSite_id(site.getSite_id());
					sitePerson.setSite_name(site.getSite_name());
					sitePerson.setEmp_type(GParameter.empType_maintenance);
					List<SitePerson> sitePersonList = sitePersonDao.findList(sitePerson);
					if(null != sitePersonList && sitePersonList.size() > 0){
						for (SitePerson sitePersonP : sitePersonList) {
							MsgSend msgSend = new MsgSend();
							msgSend.setCorp_id(site.getOwner_id());
							msgSend.setCorp_name(site.getOwner_name());
							msgSend.setNotice_obj(GParameter.noticeObj_site);
							msgSend.setNotice_key(site.getSite_id());
							msgSend.setCont(sitePersonP.getLogin_name()+"您好，站点"+site.getSite_name()+"于"+DateUtil.getNow()+"处于离线状态，请及时检查网络状况！");
							msgSend.setFail_num(0);
							msgSend.setProc_flag(GParameter.procFlag_no);
							if(sitePersonP.getMobile() != null && !sitePersonP.getMobile().equals("")){
								msgSend.setNotice_type(GParameter.noticeType_mobile);
								msgSend.setNotice_way(sitePersonP.getMobile());
								msgSend.setPlan_time(DateUtil.getNow());
								msgSend.setProc_time(DateUtil.getNow());
								msgSend.setCreate_time(DateUtil.getNow());
								msgSend.setLogid(DateUtil.getLogid());
								msgSendDao.insert(msgSend);
							}
							if(sitePersonP.getEmail() != null && !sitePersonP.getEmail().equals("")){
								msgSend.setNotice_type(GParameter.noticeType_email);
								msgSend.setNotice_way(sitePersonP.getEmail());
								msgSend.setPlan_time(DateUtil.getNow());
								msgSend.setProc_time(DateUtil.getNow());
								msgSend.setCreate_time(DateUtil.getNow());
								msgSend.setLogid(DateUtil.getLogid());
								msgSendDao.insert(msgSend);
							}
						}
					}
				}
			}
			List<AsSite> siteList = siteDao.findListOnline();
			for(AsSite site : siteList){
				site.setSite_state(GParameter.siteUseState_offline);
				siteDao.updateSiteState(site);
			}
			
		} catch (Exception e) {
			log.error("TaskCheckSiteStateService is fail");
			e.printStackTrace();
		}
		return retCode;
	}

}
