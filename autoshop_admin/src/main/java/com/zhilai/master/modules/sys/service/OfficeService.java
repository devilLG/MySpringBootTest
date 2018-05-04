/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.sys.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.TreeService;
import com.zhilai.master.modules.inventory.dao.WarehouseInfoDao;
import com.zhilai.master.modules.inventory.entity.WarehouseInfo;
import com.zhilai.master.modules.sys.dao.OfficeDao;
import com.zhilai.master.modules.sys.dao.SequenceIdDao;
import com.zhilai.master.modules.sys.entity.Office;
import com.zhilai.master.modules.sys.entity.SequenceId;
import com.zhilai.master.modules.sys.utils.UserUtils;
import com.zhilai.master.modules.trade.dao.PartnerIPDao;
import com.zhilai.master.modules.trade.dao.PartnerTradeDao;
import com.zhilai.master.modules.trade.dao.TradeDefineDao;
import com.zhilai.master.modules.trade.entity.PartnerIP;
import com.zhilai.master.modules.trade.entity.PartnerTrade;
import com.zhilai.master.modules.trade.entity.TradeDefine;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 机构Service
 * @author zhilai
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {
	
	@Autowired
	private SequenceIdDao sequenceIdDao;
	@Autowired
	private TradeDefineDao tradeDefineDao;
	@Autowired
	private PartnerTradeDao partnerTradeDao;
	@Autowired
	private PartnerIPDao partnerIPDao;
	@Autowired
	private WarehouseInfoDao warehouseInfoDao;

	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}

	public List<Office> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}
	
	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		/*if(office != null){
			office.setParentIds(office.getParentIds()+"%");
			return dao.findByParentIdsLike(office);
		}
		return  new ArrayList<Office>();*/
		return dao.findByParentIdsLike(office);
	}
	
	@Transactional(readOnly = true)
	public Office findByCorpId(String corpId){
		return dao.findByCorpId(corpId);
	}
	
	@Transactional(readOnly = false)
	public void save(Office office) {
		if(office.getType().equals("1")){
			if(office.getAuthId()==null||office.getAuthId().equals("")){
				office.setAuthId(office.getCorpId());
				office.setAuthName(office.getCorpId());
			}
		}
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
		if(office.getType().equals("1")){
			//虚拟仓库配置
			WarehouseInfo warehouseInfo = new WarehouseInfo();
			warehouseInfo.setLogid(DateUtil.getLogid());
			warehouseInfo.setWarehouse_id(office.getCorpId());
			warehouseInfo.setCorp_id(office.getCorpId());
			warehouseInfo.setCorp_name(office.getName());
			warehouseInfo.setWarehouse_name(office.getName());
			warehouseInfo.setWarehouse_code(office.getCorpId());
			warehouseInfo.setLatitude("");
			warehouseInfo.setLongitude("");
			warehouseInfo.setAddress(office.getAddress());
			warehouseInfo.setCreate_time(DateUtil.getNow());
			List<WarehouseInfo> warehouseInfoList = warehouseInfoDao.findList(warehouseInfo);
			if(warehouseInfoList == null || warehouseInfoList.size() <= 0){
				warehouseInfoDao.insert(warehouseInfo);
			}else{
				warehouseInfoList.get(0).setCorp_name(office.getName());
				warehouseInfoList.get(0).setWarehouse_name(office.getName());
				warehouseInfoList.get(0).setAddress(office.getAddress());
				warehouseInfoDao.update(warehouseInfoList.get(0));
			}
			//SequenceId配置
			List<SequenceId> slist = sequenceIdDao.findAllList();
			for (SequenceId sequenceId : slist) {
				SequenceId sequence = new SequenceId();
				sequence.setCorp_id(office.getCorpId());
				sequence.setName(sequenceId.getName());
				List<SequenceId> list = sequenceIdDao.findList(sequence);
				if(list.size() < 1){
					sequence.setId(1);
					sequenceIdDao.insert(sequence);
				}
			}
			// 授权交易码
			TradeDefine tradeDefineP = new TradeDefine();
			tradeDefineP.setPlat_code(GParameter.platCode_web);
			List<TradeDefine> tradeDefineList = this.tradeDefineDao.findAllList(tradeDefineP);
			PartnerTrade partnerTrade = null;
			for (TradeDefine tradeDefine : tradeDefineList) {
				partnerTrade = new PartnerTrade();
				partnerTrade.setLogid(DateUtil.getLogid());
				partnerTrade.setCorp_id(office.getCorpId());
				partnerTrade.setCorp_name(office.getName());
				partnerTrade.setTrade_code(tradeDefine.getTrade_code());
				partnerTrade.setTrade_desc(tradeDefine.getTrade_desc());
				partnerTrade.setTrade_name(tradeDefine.getTrade_name());
				partnerTrade.setCreate_time(DateUtil.getNow());
				int r = partnerTradeDao.findTradeExist(partnerTrade);
				if (r == 0) {
					this.partnerTradeDao.insert(partnerTrade);
				}
			}
			PartnerIP partnerIP = new PartnerIP();
			partnerIP.setLogid(DateUtil.getLogid());
			partnerIP.setCorp_id(office.getCorpId());
			partnerIP.setCorp_name(office.getName());
			partnerIP.setAuth_name(office.getAuthName());
			partnerIP.setAddr_ip("127.0.0.1");
			partnerIP.setAddr_mac("localhost");
			partnerIP.setAuth_state(office.getUseable());
			PartnerIP partnerIPR = partnerIPDao.findOneByCorpId(partnerIP.getCorp_id());
			if(partnerIPR != null){
				partnerIP.setLogid(partnerIPR.getLogid());
				partnerIPDao.update(partnerIP);
			}else{
				partnerIPDao.insert(partnerIP);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Office office) {
		WarehouseInfo warehouseInfo = new WarehouseInfo();
		warehouseInfo.setCorp_id(office.getCorpId());
		warehouseInfoDao.delete(warehouseInfo);
		sequenceIdDao.deleteByCorpId(office.getCorpId());
		partnerTradeDao.deleteByCorpId(office.getCorpId());
		partnerIPDao.deleteByCorpId(office.getCorpId());
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
}
