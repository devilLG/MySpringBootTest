/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.site.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.CacheUtils;
import com.zhilai.master.common.utils.IdGen;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.monitor.dao.SiteDeviceConfigDao;
import com.zhilai.master.modules.site.dao.AsSiteDao;
import com.zhilai.master.modules.site.dao.SiteControlConfigDao;
import com.zhilai.master.modules.site.dao.SiteDeviceDao;
import com.zhilai.master.modules.site.dao.SiteGoodsDao;
import com.zhilai.master.modules.site.dao.SiteStateDao;
import com.zhilai.master.modules.site.entity.AsSite;
import com.zhilai.master.modules.site.entity.SiteControlConfig;
import com.zhilai.master.modules.site.entity.SiteDevice;
import com.zhilai.master.modules.site.entity.SiteGoods;
import com.zhilai.master.modules.site.entity.SiteState;
import com.zhilai.master.modules.standard.dao.BoxDao;
import com.zhilai.master.modules.standard.dao.BoxTypeDao;
import com.zhilai.master.modules.standard.dao.CabinetAttrDao;
import com.zhilai.master.modules.standard.dao.CabinetStandardDao;
import com.zhilai.master.modules.standard.entity.Box;
import com.zhilai.master.modules.standard.entity.BoxType;
import com.zhilai.master.modules.standard.entity.CabinetAttr;
import com.zhilai.master.modules.standard.entity.CabinetStandard;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.sys.utils.UserUtils;
import com.zhilai.master.modules.trade.dao.SiteTradeDao;
import com.zhilai.master.modules.trade.dao.TradeDefineDao;
import com.zhilai.master.modules.trade.entity.SiteTrade;
import com.zhilai.master.modules.trade.entity.TradeDefine;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 站点Service
 * 
 * @author zhilai
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class AsSiteService extends CrudService<AsSiteDao, AsSite> {
	@Autowired
	private AsSiteDao siteDao;
	@Autowired
	private SiteStateDao siteStateDao;// 地点状态
	@Autowired
	// 地点资源
	private CabinetStandardDao cabinetStandardDao;// 箱柜配置
	@Autowired
	private SiteDeviceDao siteDeviceDao;// 地点柜子
	@Autowired
	private SiteGoodsDao boxDao;// 货道
	@Autowired
	private CabinetAttrDao cabinetAttrDao;// 柜箱排列
	@Autowired
	private BoxTypeDao boxTypeDao;// 货道规格
	@Autowired
	private SiteDeviceConfigDao siteDeviceConfigDao;//站点设备信息

	// 地点授权Dao
	@Autowired
	private TradeDefineDao tradeDefineDao;//交易授权
	@Autowired
	private SiteTradeDao siteTradeDao;//地点交易
	@Autowired
	private SiteControlConfigDao siteControlConfigDao;// 地点交易

	public Page<AsSite> findPage(Page<AsSite> page, AsSite site) {
		// DetachedCriteria dc = siteDao.createDetachedCriteria();
		// if (StringUtils.isNotEmpty(site.getName())){
		// dc.add(Restrictions.like("name", "%"+site.getName()+"%"));
		// }
		// dc.add(Restrictions.eq(Site.FIELD_DEL_FLAG, site.getDelFlag()));
		// //dc.addOrder(Order.asc("id"));
		// return siteDao.find(page, dc);

		// site.getSqlMap().put("site", dataScopeFilter(site.getCurrentUser(),
		// "o", "u"));
		// 执行分页查询
		// 设置分页参数
		site.setPage(page);
		page.setList(super.findList(site));
		return page;

		// return super.findPage(page, site);
	}
	
	public List<AsSite> findWarnList(AsSite site) {
		return siteDao.findWarnList(site);
	}

	@Transactional(readOnly = false)
	public void save(AsSite site) {
		if (StringUtils.isBlank(site.getLogid())){
			    site.setCreate_time(DateUtil.getNow());
			    site.setAuth_id(site.getSite_id());
			    site.setAuth_name(site.getSite_id());
			    site.setLogid(DateUtil.getLogid());
			    siteDao.insert(site);
			    User user = UserUtils.getUser();
				//siteDao.insert(site);
				// 查询箱子类型信息
				List<BoxType> listBoxTypeList = boxTypeDao.findAllList();
				// 查询改配置下的柜子信息
				CabinetStandard cabinetStandardP = new CabinetStandard();
				cabinetStandardP.setCabconfig_id(site.getCabinet_type());
				List<CabinetStandard> cabinetStandardList = cabinetStandardDao.findByCabconfigId(cabinetStandardP);
				int controlBoxNum = 0;
				int box_id = 1;
				for (CabinetStandard cabinetStandard : cabinetStandardList) {
					// 查询改柜子下面的箱子配置信息
					CabinetAttr cabinetAttr = new CabinetAttr();
					cabinetAttr.setCabinettype_id(cabinetStandard.getCabinettype_id());

					List<CabinetAttr> cabinetAttrList = cabinetAttrDao.findList(cabinetAttr);
					// 生成地点柜箱信息
					SiteDevice siteDevice = new SiteDevice();
					siteDevice.setCabinet_location(cabinetStandard.getColumn_location());
					siteDevice.setSite_id(site.getSite_id());
					siteDevice.setCabinet_seq(String.valueOf(cabinetStandard.getColumn_location()));// 柜子序号
					List<SiteDevice> sitelist = siteDeviceDao.findList(siteDevice);
					siteDevice.setCabinet_type(cabinetStandard.getCabinettype_id());
					siteDevice.setCreate_time(DateUtil.getNow());
					siteDevice.setLogid(DateUtil.getLogid());
					siteDevice.setSite_name(site.getSite_name());
					siteDevice.setOwner_id(site.getOwner_id());
					// siteDevice.setCabinet_batch_num(cabinetStandard.getCabinet_batch_num());
					siteDevice.setBind_state(GParameter.bind_state_nomarl);// 绑定状态

					if (sitelist.isEmpty() && sitelist.size() < 1) {
						siteDeviceDao.insert(siteDevice);
					}
					// 插入地点货道信息
					SiteGoods box = null;
					SiteGoods box1 = null;
					for (CabinetAttr cabinetAttrTemp : cabinetAttrList) {
						box = new SiteGoods();
						box1 = new SiteGoods();
					    box.setBox_id(Integer.toString((box_id++)));
//						box.setBox_id(cabinetAttrTemp.getBox_id().toString());
				        box.setCur_state(GParameter.boxSta_nomal);
				       
						for (BoxType boxType : listBoxTypeList) {
							if (boxType.getType_id().equals(cabinetAttrTemp.getBoxtype_id()))
								box.setBox_type(boxType.getType_name());
						}
						//box.setBox_typeid(cabinetAttrTemp.getBoxtype_id());
						box.setCabinet_id(siteDevice.getCabinet_location());//柜号
						//box.setCabinet_type(cabinetAttrTemp.getCabinettype_id());
						box.setLayer_num(Integer.parseInt(cabinetAttrTemp.getLayer_num()));
						box.setLogid(DateUtil.getLogid());
						box.setOwner_id(site.getOwner_id());
						box.setSite_id(site.getSite_id());
						box.setSite_name(site.getSite_name());
						box.setCorp_id(site.getCorp_id());
						box.setDown_state(GParameter.downState_wait);
						box.setCorp_name(site.getOwner_name());
						box.setCloumn_id(cabinetAttrTemp.getCloumn_id());
						//规格所在列的存储类型，根据标识和列数找到配置规格
		
//						int col = cabinetAttrTemp.getCloumn_id().intValue();
//						CabinetStandard StandardP = new CabinetStandard();
//						StandardP.setCabconfig_id(cabinetStandard.getCabconfig_id());
//						StandardP.setColumn_location(col);
//						List<CabinetStandard> splist = cabinetStandardDao.findList(StandardP);
//						if(splist.size() > 0){
//							box.setTemper_type(splist.get(0).getCabinetTemp_type());
//						}else{
//							box.setTemper_type("1");
//						}
						box.setTemper_type(cabinetStandard.getCabinetTemp_type());
						
						box.setCreate_time(DateUtil.getNow());
						box1.setBox_id(box.getBox_id());
						box1.setSite_id(box.getSite_id());
						List<SiteGoods> boxlist = boxDao.findList(box1);

						if (boxlist.isEmpty() && boxlist.size() < 1) {
							this.boxDao.insert(box);
						} else {
							box.setLogid(box.getLogid());
							this.boxDao.update(box);
						}
					}
				}
				// 生成箱子资源信息
				SiteState site1 = new SiteState();
				site1.setSite_id(site.getSite_id());
				List<SiteState> siteStatelist = siteStateDao.findList(site1);
				SiteGoods box2 = new SiteGoods();
				box2.setSite_id(site.getSite_id());
				List<SiteGoods> boxList = boxDao.findList(box2);
				if (siteStatelist.isEmpty()) {
					site1 = new SiteState();
					site1.setBox_all(boxList.size() - controlBoxNum);
					site1.setBox_empty(0);
					//site1.setBox_using(0);
					site1.setBox_damage(0);
					site1.setBox_short(0);
					site1.setCompute_state("");
					site1.setCompute_time("");
					site1.setLogid(DateUtil.getLogid());
					site1.setOwner_id(site.getOwner_id());
					site1.setSite_id(site.getSite_id());
					site1.setSite_name(site.getSite_name());
					// siteState.setSys_time(DateUtil.getNow());
					site1.setRefresh_state(GParameter.siteUseState_offline);
					site1.setRefresh_time(DateUtil.formatDate(DateUtil.getDate(
							new Date(), -1)) + DateUtil.getNow().substring(10));
					this.siteStateDao.insert(site1);
				} else {
					site1.setBox_all(boxList.size() - controlBoxNum);
					site1.setOwner_id(site.getOwner_id());
					this.siteStateDao.update(site1);
				}
				// 授权交易码
				TradeDefine tradeDefineP = new TradeDefine();
				tradeDefineP.setPlat_code(GParameter.platCode_terminal);
				List<TradeDefine> tradeDefineList = tradeDefineDao
						.findAllList(tradeDefineP);
				SiteTrade siteTrade = null;
				for (TradeDefine tradeDefine : tradeDefineList) {
					siteTrade = new SiteTrade();
					// siteTrade.setOwner_id(site.getOwner_id());
					siteTrade.setSite_id(site.getSite_id());
					siteTrade.setTrade_code(tradeDefine.getTrade_code());
					int r = this.siteTradeDao.findList(siteTrade).size();
					siteTrade.setSite_name(site.getSite_name());
					siteTrade.setLogid(DateUtil.getLogid());
					siteTrade.setTrade_desc(tradeDefine.getTrade_desc());
					siteTrade.setTrade_name(tradeDefine.getTrade_name());
					siteTrade.setOwner_id(site.getOwner_id());
					if (r == 0) {
						this.siteTradeDao.insert(siteTrade);
					}
				}
				SiteControlConfig siteControlConfigP = null;
				for (TradeDefine tradeDefine : tradeDefineList) {
					siteControlConfigP = new SiteControlConfig();
					// siteTrade.setOwner_id(site.getOwner_id());
					siteControlConfigP.setSite_id(site.getSite_id());
					siteControlConfigP.setTrade_code(tradeDefine.getTrade_code());
					int r = this.siteControlConfigDao.findList(siteControlConfigP).size();
					siteControlConfigP.setSite_name(site.getSite_name());
					siteControlConfigP.setLogid(DateUtil.getLogid());
					siteControlConfigP.setIs_log(GParameter.is_log_on);
					siteControlConfigP.setCur_state(GParameter.cur_state_on);
					siteControlConfigP.setCreate_time(DateUtil.getNow());
					siteControlConfigP.setTrade_name(tradeDefine.getTrade_name());
					siteControlConfigP.setOper_man(user.getName());
					if (r == 0) {
						this.siteControlConfigDao.insert(siteControlConfigP);
					}
				}
		}else{
			 site.setAuth_id(site.getSite_id());
			    site.setAuth_name(site.getSite_id());
			    siteDao.update(site);
		}
		} 


	@Transactional(readOnly = false)
	public void delete(AsSite site) {
		String site_id = site.getSite_id();
		siteDeviceDao.deleteBysid(site_id);
		boxDao.deleteBysid(site_id);
		siteStateDao.deleteBysid(site_id);
		siteDeviceConfigDao.deleteBysid(site_id);
		super.delete(site);
	}

}
