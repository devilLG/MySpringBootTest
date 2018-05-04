package com.zhilai.master.task.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.modules.inventory.dao.InventoryGoodsDao;
import com.zhilai.master.modules.inventory.dao.InventoryProductDao;
import com.zhilai.master.modules.inventory.dao.ReplenishmentOrderDetailDao;
import com.zhilai.master.modules.inventory.dao.WarehouseInfoDao;
import com.zhilai.master.modules.inventory.entity.InventoryGoods;
import com.zhilai.master.modules.inventory.entity.InventoryProduct;
import com.zhilai.master.modules.inventory.entity.ReplenishmentOrderDetail;
import com.zhilai.master.modules.inventory.entity.WarehouseInfo;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.utils.heartbeat.HeartBeat;
import com.zhilai.master.modules.utils.heartbeat.SiteNoticeHeartBeat;

@Service
@Transactional(readOnly = true)
public class TaskProductOverdueService {
	private static Logger log = Logger.getLogger(TaskProductOverdueService.class);
	@Autowired
	private ReplenishmentOrderDetailDao replenishmentOrderDetailDao;
	@Autowired
	private SiteNoticeHeartBeat siteNoticeHeartBeat;
	@Autowired
	private WarehouseInfoDao warehouseInfoDao;
	@Autowired
	private InventoryProductDao inventoryProductDao;
	@Autowired
	private InventoryGoodsDao inventoryGoodsDao;

	@Transactional(readOnly = false)
	public String execute(Date date) {
		String retCode = "0";
		try {
			String invalid_date = DateUtil.getNow();
			List<ReplenishmentOrderDetail> replenishmentOrderDetailList = replenishmentOrderDetailDao.findProductOverdueList(invalid_date);
			if(replenishmentOrderDetailList != null && replenishmentOrderDetailList.size() > 0){
				for(ReplenishmentOrderDetail replenishmentOrderDetail : replenishmentOrderDetailList){
					//更新补货清单信息闭并下发
					replenishmentOrderDetail.setOverdue_num(((replenishmentOrderDetail.getReplen_num() - replenishmentOrderDetail.getSold_num()) > 0) ? (replenishmentOrderDetail.getReplen_num() - replenishmentOrderDetail.getSold_num()) : 0);
					replenishmentOrderDetail.setCur_state(GParameter.replenishmentOrderDetailState_overdue);
					replenishmentOrderDetail.setState_time(DateUtil.getNow());
					replenishmentOrderDetail.setDown_state(GParameter.downState_wait);
					replenishmentOrderDetail.setDown_time(DateUtil.getNow());
					replenishmentOrderDetailDao.update(replenishmentOrderDetail);
					HeartBeat heartBeat = new HeartBeat();
					heartBeat.setSite_id(replenishmentOrderDetail.getSite_id());
					heartBeat.setSite_name(replenishmentOrderDetail.getSite_name());
					heartBeat.setTrade_code(GParameter.issued_productRecover_trade_code);
					heartBeat.setTrade_name(GParameter.issued_productRecover_trade_desc);
					heartBeat.setCreate_time(DateUtil.getNow());
					heartBeat.setUpdate_time(DateUtil.getNow());
					heartBeat.setPush_id(DateUtil.getLogid());
					heartBeat.setIssued_key(replenishmentOrderDetail.getDetail_id());
					siteNoticeHeartBeat.pushHeart(heartBeat);
					//更新虚拟仓库过期数量
					WarehouseInfo warehouseInfo = warehouseInfoDao.findByCorpId(replenishmentOrderDetail.getCorp_id());
					if(warehouseInfo != null){
						warehouseInfo.setInventory_num(((warehouseInfo.getInventory_num() - replenishmentOrderDetail.getOverdue_num()) > 0) ? (warehouseInfo.getInventory_num() - replenishmentOrderDetail.getOverdue_num()) : 0);
						warehouseInfo.setOverdue_num(warehouseInfo.getOverdue_num() + replenishmentOrderDetail.getOverdue_num());
						warehouseInfoDao.update(warehouseInfo);
					}
					//更新站点库存过期数量
					InventoryProduct inventoryProduct = new InventoryProduct();
					inventoryProduct.setSite_id(replenishmentOrderDetail.getSite_id());
					inventoryProduct.setProduct_id(replenishmentOrderDetail.getProduct_id());
					InventoryProduct inventoryProductR = inventoryProductDao.findExist(inventoryProduct);
					if(inventoryProductR != null){
						inventoryProductR.setInventory_num(((inventoryProductR.getInventory_num() - replenishmentOrderDetail.getOverdue_num()) > 0) ? (inventoryProductR.getInventory_num() - replenishmentOrderDetail.getOverdue_num()) : 0);
						inventoryProductR.setOverdue_num(inventoryProductR.getOverdue_num() + replenishmentOrderDetail.getOverdue_num());
						inventoryProductDao.update(inventoryProductR);
					}
					//更新货道库存过期数量
					InventoryGoods inventoryGoods = new InventoryGoods();
					inventoryGoods.setSite_id(replenishmentOrderDetail.getSite_id());
					inventoryGoods.setBox_id(replenishmentOrderDetail.getBox_id());
					InventoryGoods inventoryGoodsR = inventoryGoodsDao.findExist(inventoryGoods);
					if(inventoryGoodsR != null){
						inventoryGoodsR.setInventory_num(((inventoryGoodsR.getInventory_num() - replenishmentOrderDetail.getOverdue_num()) > 0) ? (inventoryGoodsR.getInventory_num() - replenishmentOrderDetail.getOverdue_num()) : 0);
						inventoryGoodsR.setOverdue_num(inventoryGoodsR.getOverdue_num() + replenishmentOrderDetail.getOverdue_num());
						inventoryGoodsR.setIs_overdue(GParameter.isOverdue_yes);
						inventoryGoodsR.setOverdue_time(DateUtil.getNow());
						inventoryGoodsDao.update(inventoryGoodsR);
					}
				}
			}
		} catch (Exception e) {
			log.error("TaskProductOverdueService is fail");
			e.printStackTrace();
		}
		return retCode;
	}

}
