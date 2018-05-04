package com.zhilai.master.task.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.modules.inventory.dao.BarterOrderDao;
import com.zhilai.master.modules.inventory.dao.CheckOrderDao;
import com.zhilai.master.modules.inventory.dao.InventoryGoodsDao;
import com.zhilai.master.modules.inventory.dao.ReplenishmentOrderDao;
import com.zhilai.master.modules.inventory.entity.BarterOrder;
import com.zhilai.master.modules.inventory.entity.CheckOrder;
import com.zhilai.master.modules.inventory.entity.InventoryGoods;
import com.zhilai.master.modules.inventory.entity.ReplenishmentOrder;
import com.zhilai.master.modules.monitor.dao.SiteDeviceConfigDao;
import com.zhilai.master.modules.monitor.entity.SiteDeviceConfig;
import com.zhilai.master.modules.notice.dao.CorpNoticeTemplateDao;
import com.zhilai.master.modules.notice.dao.NoticeConfigDao;
import com.zhilai.master.modules.notice.dao.NoticeQueueDao;
import com.zhilai.master.modules.notice.dao.NoticeQueueLogDao;
import com.zhilai.master.modules.notice.entity.CorpNoticeTemplate;
import com.zhilai.master.modules.notice.entity.NoticeConfig;
import com.zhilai.master.modules.notice.entity.NoticeQueue;
import com.zhilai.master.modules.order.dao.OrderApplyDao;
import com.zhilai.master.modules.order.entity.OrderApply;
import com.zhilai.master.modules.pay.dao.PayHandleDao;
import com.zhilai.master.modules.pay.entity.PayHandle;
import com.zhilai.master.modules.person.dao.SitePersonDao;
import com.zhilai.master.modules.person.entity.SitePerson;
import com.zhilai.master.modules.sys.dao.UserDao;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.utils.TemplateUtil;

/**
 * 
 * @ClassName TaskCreateNoticeService
 * @Description 生成通知
 * 				获取所有公司通知模板，
 * 				根据不同的模板配置的通知类型获取该类型的通知配置，
 * 				获取相应的该公司的数据
 * @author GuoXuegan
 * @data 2018年4月10日上午11:44:51
 *
 */
@Service
@Transactional(readOnly=true)
public class TaskCreateNoticeService {

	private static Logger log = Logger.getLogger(TaskCreateNoticeService.class);
	
	@Autowired
	private CorpNoticeTemplateDao corpNoticeTemplateDao;
	@Autowired
	private NoticeQueueDao noticeQueueDao;
	@Autowired
	@SuppressWarnings("unused")
	private NoticeQueueLogDao noticeQueueLogDao;
	@Autowired
	private OrderApplyDao orderApplyDao;
	@Autowired
	private NoticeConfigDao noticeConfigDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private InventoryGoodsDao inventoryGoodsDao;
	@Autowired
	private ReplenishmentOrderDao replenishmentOrderDao;
	@Autowired
	private BarterOrderDao barterOrderDao;
	@Autowired
	private CheckOrderDao checkOrderDao;
	@Autowired
	private SiteDeviceConfigDao siteDeviceConfigDao;
	@Autowired
	private PayHandleDao payHandleDao;
	@Autowired
	private SitePersonDao sitePersonDao;
	
	@Transactional
	public String execute() {
		String retCode = "0";
		log.info("Create Notice BEGIN! "+DateUtil.getNow());
		
		List<CorpNoticeTemplate> cnts = corpNoticeTemplateDao.findAllNormal();
		for (CorpNoticeTemplate cnt : cnts) {
			//获取当前模板所需的该公司的通知配置
			char[] noticeTypes = cnt.getNotice_type().toCharArray();
			
			NoticeConfig noticeConfig = new NoticeConfig();
			noticeConfig.setCorp_id(cnt.getCorp_id());
			noticeConfig.setCur_state("1");
			List<NoticeConfig> noticeConfigs = noticeConfigDao.findForCreateNotice(noticeConfig, noticeTypes);
			
			if (cnt.getMain_type().equals(GParameter.mainType_saleOrder)) {
				// 销售订单
				excuteSaleOrder(cnt, noticeConfigs);
			}else if (cnt.getMain_type().equals(GParameter.mainType_siteMonitor)) {
				// 站点监控
				excuteSiteMonitor(cnt, noticeConfigs);
			}else if (cnt.getMain_type().equals(GParameter.mainType_work)) {
				// 工单服务
				excuteWork(cnt, noticeConfigs);
			}else if (cnt.getMain_type().equals(GParameter.mainType_siteInventory)) {
				// 站点库存
				excuteSiteInventory(cnt, noticeConfigs);
			}else if (cnt.getMain_type().equals(GParameter.mainType_siteReplenishment)) {
				// 站点补货
				excuteSiteReplenishment(cnt, noticeConfigs);
			}else if (cnt.getMain_type().equals(GParameter.mainType_siteBarter)) {
				// 站点换货
				excuteSiteBarter(cnt, noticeConfigs);
			}else if (cnt.getMain_type().equals(GParameter.mainType_siteCheck)) {
				// 站点盘点
				excuteSiteCheck(cnt, noticeConfigs);
			}else if (cnt.getMain_type().equals(GParameter.mainType_userRegister)) {
				// 人员注册及更新
				excuteUserRegister(cnt, noticeConfigs);
			}else {
				// 对账单
				excutePayChecking(cnt, noticeConfigs);
			}
		}
		return retCode;
	}
	
	// 销售订单
	@Transactional
	private void excuteSaleOrder(CorpNoticeTemplate vo, List<NoticeConfig> noticeConfigs) {
		// 发送给公司管理员
		User user = userDao.findByRole("FCcompanysystem", vo.getCorp_id());
		if (user == null) {
			return;
		}
		//获取该公司所有的销售订单
		String curState = vo.getSub_type().substring(2, vo.getSub_type().length());//获取要通知的订单状态
		OrderApply orderApply = new OrderApply();
		orderApply.setOrder_type("1");
		orderApply.setCur_state(curState);
		orderApply.setCorp_id(vo.getCorp_id());
		List<OrderApply> orderApplies = orderApplyDao.findList(orderApply);
		
		for (OrderApply oa : orderApplies) {
			for (NoticeConfig nc : noticeConfigs) {
				NoticeQueue nq = new NoticeQueue();
				takeNoticeQueue(nq, vo, nc, user);
				
				nq.setOrder_id(oa.getOrder_id());
				nq.setCont(TemplateUtil.velocityTranslate(oa, vo.getContent())); //根据模板生成内容  模板字段采用的是数据库字段，然实体类采用的是驼峰
				noticeQueueDao.insert(nq);
			}
		}
		
	}
	
	// 站点监控
	@Transactional
	private void excuteSiteMonitor(CorpNoticeTemplate vo, List<NoticeConfig> noticeConfigs) {
		// 发送给公司管理员
		User user = userDao.findByRole("FCcompanysystem", vo.getCorp_id());
		if (user == null) {
			return;
		}
		String curState = vo.getSub_type().substring(2, vo.getSub_type().length());//获取要通知的订单状态
		SiteDeviceConfig siteDeviceConfig = new SiteDeviceConfig();
		siteDeviceConfig.setCurState(curState);
		siteDeviceConfig.setCheckState("1");
		siteDeviceConfig.setCorp_id(vo.getCorp_id());
		List<SiteDeviceConfig> siteDeviceConfigs = siteDeviceConfigDao.findList(siteDeviceConfig);
		for (SiteDeviceConfig sdc : siteDeviceConfigs) {
			for (NoticeConfig nc : noticeConfigs) {
				NoticeQueue nq = new NoticeQueue();
				takeNoticeQueue(nq, vo, nc, user);
				
				nq.setOrder_id(sdc.getSdeviceId());
				nq.setCont(TemplateUtil.velocityTranslate(sdc, vo.getContent())); //根据模板生成内容  模板字段采用的是数据库字段，然实体类采用的是驼峰
				noticeQueueDao.insert(nq);
			}
		}
	}
	
	// 工单服务
	@Transactional
	private void excuteWork(CorpNoticeTemplate vo, List<NoticeConfig> noticeConfigs) {
		String curState = vo.getSub_type().substring(2, vo.getSub_type().length());//获取要通知的订单状态
		
	}
	
	// 站点库存
	@Transactional
	private void excuteSiteInventory(CorpNoticeTemplate vo, List<NoticeConfig> noticeConfigs) {
		String curState = vo.getSub_type().substring(2, vo.getSub_type().length());//获取要通知的订单状态
		InventoryGoods inventoryGoods = new InventoryGoods();
		inventoryGoods.setInventory_state(curState);
		inventoryGoods.setCorp_id(vo.getCorp_id());
		//获取所有当前缺货的货道信息
		List<InventoryGoods> inventoryGoodss = inventoryGoodsDao.findWarnByInventoryState(inventoryGoods);
		for (InventoryGoods ig : inventoryGoodss) {
			// 发送给站点维护人员
			User user = userDao.findByInventory(ig.getSite_id());
			if (user == null) {
				continue;
			}
			for (NoticeConfig nc : noticeConfigs) {
				NoticeQueue nq = new NoticeQueue();
				takeNoticeQueue(nq, vo, nc, user);
				
				nq.setOrder_id(ig.getInvengoods_id());
				nq.setCont(TemplateUtil.velocityTranslate(ig, vo.getContent())); //根据模板生成内容  模板字段采用的是数据库字段，然实体类采用的是驼峰
				noticeQueueDao.insert(nq);
			}
		}
	}
	
	// 站点补货
	@Transactional
	private void excuteSiteReplenishment(CorpNoticeTemplate vo, List<NoticeConfig> noticeConfigs) {
		String curState = vo.getSub_type().substring(2, vo.getSub_type().length());//获取要通知的订单状态
		// 获取待发送的本公司的补货订单
		BarterOrder barterOrder = new BarterOrder();
		barterOrder.setCorp_id(vo.getCorp_id());
		barterOrder.setBarter_state(curState);
		List<BarterOrder> barterOrders = barterOrderDao.findList(barterOrder);
		for (BarterOrder bo : barterOrders) {
			// 发送给站点维护人员
			User user = userDao.findByInventory(bo.getSite_id());
			if (user == null) {
				continue;
			}
			for (NoticeConfig nc : noticeConfigs) {
				NoticeQueue nq = new NoticeQueue();
				takeNoticeQueue(nq, vo, nc, user);
				
				nq.setOrder_id(bo.getOrder_id());
				nq.setCont(TemplateUtil.velocityTranslate(bo, vo.getContent())); //根据模板生成内容  模板字段采用的是数据库字段，然实体类采用的是驼峰
				noticeQueueDao.insert(nq);
			}
		}
	}
	
	// 站点换货
	@Transactional
	private void excuteSiteBarter(CorpNoticeTemplate vo, List<NoticeConfig> noticeConfigs) {
		String curState = vo.getSub_type().substring(2, vo.getSub_type().length());//获取要通知的订单状态
		// 获取待发送的本公司的补货订单
		ReplenishmentOrder replenishmentOrder = new ReplenishmentOrder();
		replenishmentOrder.setCorp_id(vo.getCorp_id());
		replenishmentOrder.setCur_state(curState);
		List<ReplenishmentOrder> replenishmentOrders = replenishmentOrderDao.findList(replenishmentOrder);
		for (ReplenishmentOrder ro : replenishmentOrders) {
			// 发送给站点维护人员
			User user = userDao.findByInventory(ro.getSite_id());
			if (user == null) {
				continue;
			}
			for (NoticeConfig nc : noticeConfigs) {
				NoticeQueue nq = new NoticeQueue();
				takeNoticeQueue(nq, vo, nc, user);
				
				nq.setOrder_id(ro.getOrder_id());
				nq.setCont(TemplateUtil.velocityTranslate(ro, vo.getContent())); //根据模板生成内容  模板字段采用的是数据库字段，然实体类采用的是驼峰
				noticeQueueDao.insert(nq);
			}
		}
	}
	
	// 站点盘点
	@Transactional
	private void excuteSiteCheck(CorpNoticeTemplate vo, List<NoticeConfig> noticeConfigs) {
		String curState = vo.getSub_type().substring(2, vo.getSub_type().length());//获取要通知的订单状态
		// 获取待发送的本公司的补货订单
		CheckOrder checkOrder = new CheckOrder();
		checkOrder.setCorp_id(vo.getCorp_id());
		checkOrder.setCheck_state(curState);
		List<CheckOrder> checkOrders = checkOrderDao.findList(checkOrder);
		for (CheckOrder co : checkOrders) {
			// 发送给站点维护人员
			User user = userDao.findByInventory(co.getSite_id());
			if (user == null) {
				continue;
			}
			for (NoticeConfig nc : noticeConfigs) {
				NoticeQueue nq = new NoticeQueue();
				takeNoticeQueue(nq, vo, nc, user);
				
				nq.setOrder_id(co.getOrder_id());
				nq.setCont(TemplateUtil.velocityTranslate(co, vo.getContent())); //根据模板生成内容  模板字段采用的是数据库字段，然实体类采用的是驼峰
				noticeQueueDao.insert(nq);
			}
		}
	}
	
	// 人员注册
	@Transactional
	private void excuteUserRegister(CorpNoticeTemplate vo, List<NoticeConfig> noticeConfigs) {
		String curState = vo.getSub_type().substring(2, vo.getSub_type().length());//获取要通知的订单状态
		SitePerson sitePerson = new SitePerson();
		sitePerson.setCur_state(curState);
		sitePerson.setNotice_state("1");
		sitePerson.setEmp_type("2");
		sitePerson.setCorp_id(vo.getCorp_id());
		List<SitePerson> sitePersons = sitePersonDao.findList(sitePerson);
		int size = sitePersons.size();
		for (int i = 0;i < size ;i++) {
			SitePerson sp = sitePersons.get(i);
			User user = userDao.findOneById(sp.getLogin_id());
			for (NoticeConfig nc : noticeConfigs) {
				NoticeQueue nq = new NoticeQueue();
				takeNoticeQueue(nq, vo, nc, user);
				
				nq.setOrder_id(sp.getConfig_id());
				nq.setCont(TemplateUtil.velocityTranslate(sp, vo.getContent())); //根据模板生成内容  模板字段采用的是数据库字段，然实体类采用的是驼峰
				noticeQueueDao.insert(nq);
			}
		}
	}
	
	// 对账单
	@Transactional
	private void excutePayChecking(CorpNoticeTemplate vo, List<NoticeConfig> noticeConfigs) {
		// 发送给公司管理员
		User user = userDao.findByRole("FCcompanysystem", vo.getCorp_id());
		if (user == null) {
			return;
		}
		String curState = vo.getSub_type().substring(2, vo.getSub_type().length());//获取要通知的订单状态
		PayHandle payHandle = new PayHandle();
		payHandle.setCorp_id(vo.getCorp_id());
		payHandle.setProc_status(curState);
		List<PayHandle> payHandles = payHandleDao.findList(payHandle);
		for (PayHandle ph : payHandles) {
			for (NoticeConfig nc : noticeConfigs) {
				NoticeQueue nq = new NoticeQueue();
				takeNoticeQueue(nq, vo, nc, user);
				
				nq.setOrder_id(ph.getTrade_number());
				nq.setCont(TemplateUtil.velocityTranslate(ph, vo.getContent())); //根据模板生成内容  模板字段采用的是数据库字段，然实体类采用的是驼峰
				noticeQueueDao.insert(nq);
			}
		}
	}
	
	private void takeNoticeQueue(NoticeQueue nq, CorpNoticeTemplate vo, NoticeConfig nc, User user) {
		nq.beforeInsert();
		nq.setCorp_id(vo.getCorp_id());
		nq.setCorp_name(vo.getCorp_name());
		nq.setTemp_id(vo.getCorptemp_id());
		nq.setTemp_name(vo.getTemp_name());
		nq.setMain_type(vo.getMain_type());
		nq.setSub_type(vo.getSub_type());
		nq.setNotice_channel(nc.getNotice_channel());
		nq.setNotice_address(nc.getNotice_address());
		nq.setNotice_type(vo.getNotice_type());
		nq.setSend_num(vo.getSend_num()+"");
		nq.setFail_num("0");
		nq.setLevel(vo.getLevel());
		nq.setLogin_id(user.getLoginName());
		nq.setLogin_name(user.getName());
	}
}
