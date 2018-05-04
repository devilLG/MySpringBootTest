package com.zhilai.master.task.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.modules.inventory.dao.InventoryGoodsDao;
import com.zhilai.master.modules.inventory.entity.InventoryGoods;
import com.zhilai.master.modules.parmter.dao.MsgSendDao;
import com.zhilai.master.modules.parmter.entity.MsgSend;
import com.zhilai.master.modules.person.dao.SitePersonDao;
import com.zhilai.master.modules.person.entity.SitePerson;
import com.zhilai.master.modules.site.dao.AsSiteDao;
import com.zhilai.master.modules.site.entity.AsSite;
import com.zhilai.master.modules.utils.GParameter;
/**
 * 生成补货预警通知
 *
 */
@Service
@Transactional(readOnly = true)
public class TaskBoxNumCheckService {
	private static Logger log = Logger.getLogger(TaskBoxNumCheckService.class);
	@Autowired
	private InventoryGoodsDao inventoryGoodsDao;
	@Autowired
	private MsgSendDao msgSendDao;
	@Autowired
	private SitePersonDao sitePersonDao;
	@Autowired
	private AsSiteDao siteDao;

	@Transactional(readOnly = false)
	public String execute(Date date) {
		String retCode = "0";
		try {
			List<AsSite> siteList = siteDao.findAllList();
			SitePerson sitePerson = new SitePerson();
			for (AsSite site : siteList) {
				InventoryGoods inventoryGoods = new InventoryGoods();
				inventoryGoods.setSite_id(site.getSite_id());
				List<InventoryGoods> inventoryGoodsList = inventoryGoodsDao.findWarn(inventoryGoods);
				for(InventoryGoods inventoryGoodsP : inventoryGoodsList){
					if(inventoryGoodsP.getInventory_state().equals(GParameter.inventoryState_normal)){
						inventoryGoodsP.setInventory_state(GParameter.inventoryState_lack);
						inventoryGoodsP.setState_time(DateUtil.getNow());
						inventoryGoodsDao.update(inventoryGoodsP);
					}
				}
				if(site.getIs_replenishment() == null || site.getIs_replenishment().equals(GParameter.replenishment_no)){
					sitePerson.setSite_id(site.getSite_id());
					sitePerson.setSite_name(site.getSite_name());
					sitePerson.setEmp_type(GParameter.empType_replenishment);
					List<SitePerson> sitePersonList = sitePersonDao.findList(sitePerson);
					if(null != inventoryGoodsList && inventoryGoodsList.size() > Integer.parseInt(site.getBoxlack_num())){
						if(null != sitePersonList && sitePersonList.size() > 0){
							for (SitePerson sitePersonP : sitePersonList) {
								MsgSend msgSend = new MsgSend();
								msgSend.setCorp_id(site.getOwner_id());
								msgSend.setCorp_name(site.getOwner_name());
								msgSend.setNotice_obj(GParameter.noticeObj_site);
								msgSend.setNotice_key(site.getSite_id());
								msgSend.setCont(sitePersonP.getLogin_name()+"您好，站点"+site.getSite_name()+"于"+DateUtil.getNow()+"有"+inventoryGoodsList.size()+"个箱格处于缺货状态，请及时补货！");
								msgSend.setFail_num(0);
								msgSend.setProc_flag(GParameter.procFlag_no);
								if(sitePersonP.getMobile() != null && !sitePersonP.getMobile().equals("")){
									msgSend.setNotice_type(GParameter.noticeType_mobile);
									msgSend.setNotice_way(sitePersonP.getMobile());
									msgSend.setLogid(DateUtil.getLogid());
									msgSend.setPlan_time(DateUtil.getNow());
									msgSend.setProc_time(DateUtil.getNow());
									msgSend.setCreate_time(DateUtil.getNow());
									msgSendDao.insert(msgSend);
								}
								if(sitePersonP.getEmail() != null && !sitePersonP.getEmail().equals("")){
									msgSend.setNotice_type(GParameter.noticeType_email);
									msgSend.setNotice_way(sitePersonP.getEmail());
									msgSend.setLogid(DateUtil.getLogid());
									msgSend.setPlan_time(DateUtil.getNow());
									msgSend.setProc_time(DateUtil.getNow());
									msgSend.setCreate_time(DateUtil.getNow());
									msgSendDao.insert(msgSend);
								}
							}
						}
						site.setIs_replenishment(GParameter.replenishment_yes);
						site.setWarn_type(GParameter.warn_type_nogoods);
						site.setWarn_cont(site.getSite_name()+"于"+DateUtil.getNow()+"有"+inventoryGoodsList.size()+"个箱格处于缺货状态，请及时补货！");
						site.setWarn_state(GParameter.warn_state_warn);
						site.setWarn_time(DateUtil.getNow());
						siteDao.update(site);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retCode;
	}

}
