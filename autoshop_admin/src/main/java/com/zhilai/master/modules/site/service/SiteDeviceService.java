/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.site.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.putils.utils.DateUtil;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.site.dao.AsSiteDao;
import com.zhilai.master.modules.site.dao.SiteDeviceDao;
import com.zhilai.master.modules.site.dao.SiteGoodsDao;
import com.zhilai.master.modules.site.dao.SiteStateDao;
import com.zhilai.master.modules.site.entity.AsSite;
import com.zhilai.master.modules.site.entity.SiteDevice;
import com.zhilai.master.modules.site.entity.SiteGoods;
import com.zhilai.master.modules.site.entity.SiteState;
import com.zhilai.master.modules.standard.dao.BoxDao;
import com.zhilai.master.modules.standard.dao.BoxTypeDao;
import com.zhilai.master.modules.standard.dao.CabinetAttrDao;
import com.zhilai.master.modules.standard.entity.Box;
import com.zhilai.master.modules.standard.entity.BoxType;
import com.zhilai.master.modules.standard.entity.CabinetAttr;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 柜子配置Service
 * @author zhilai
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class SiteDeviceService extends CrudService<SiteDeviceDao, SiteDevice> {
	@Autowired
	private SiteDeviceDao siteDeviceDao;
	@Autowired
	private SiteStateDao siteStateDao;
	@Autowired
	private CabinetAttrDao cabinetAttrDao;
	@Autowired
	private AsSiteDao siteDao;
	@Autowired
	private BoxTypeDao boxTypeDao;
	@Autowired
	private SiteGoodsDao boxDao;
	public Page<SiteDevice> findPage(Page<SiteDevice> page, SiteDevice site) {
		return super.findPage(page, site);
	}

	@Transactional(readOnly = false)
	public void save(SiteDevice siteDevice) {
		if (StringUtils.isBlank(siteDevice.getLogid())) {
			siteDevice.setCreate_time(DateUtil.getNow());
			}
			CabinetAttr cabinetAttr = new CabinetAttr();
			cabinetAttr.setCabinettype_id(siteDevice.getCabinettype_id());
			List<CabinetAttr> cabinetAttrList = cabinetAttrDao.findList(cabinetAttr);
			// 查找站点归属业主
			AsSite site = siteDao.findBySiteId(siteDevice.getSite_id());//
			siteDevice.setOwner_id(site.getOwner_id());
			
			siteDevice.setBind_state(GParameter.bindState_yes);
			//siteDeviceDao.insert(siteDevice);
			super.save(siteDevice);
			// 插入地点箱子信息
			List<BoxType> listBoxTypeList = boxTypeDao.findAllList();
			SiteGoods box = null;
			SiteGoods box1 = null;
			for (CabinetAttr cabinetAttrTemp : cabinetAttrList) {
				box = new SiteGoods();
				box1 = new SiteGoods();
				box.setBox_id(Integer.toString(cabinetAttrTemp.getBox_id()));
				box.setCur_state(GParameter.boxSta_nomal);
				for (BoxType boxType : listBoxTypeList) {
					if (boxType.getType_id().equals(cabinetAttrTemp.getBoxtype_id()))
						box.setBox_type(boxType.getType_name());
				}
				box.setBox_type(cabinetAttrTemp.getBoxtype_id());
				box.setCabinet_id(siteDevice.getCabinet_location());
//				box.setCabinet_id(Integer.parseInt(cabinetAttrTemp.getCabinettype_id()));
				box.setLayer_num(Integer.parseInt(cabinetAttrTemp.getLayer_num()));
				box.setLogid(DateUtil.getLogid());
				box.setOwner_id(site.getOwner_id());
				box.setSite_id(site.getSite_id());
				box.setCorp_id(site.getCorp_id());
				box.setCorp_name(site.getOwner_name());
				box.setSite_name(site.getSite_name());
				box.setCur_state(GParameter.boxSta_nomal);
				box.setCreate_time(DateUtil.getNow());
				box1.setBox_id(box.getBox_id());
				box1.setSite_id(box.getSite_id());
				List<SiteGoods> boxTemplist = boxDao.findList(box1);
				if (boxTemplist.size()==0) {
					this.boxDao.insert(box);
				} else {
					box.setLogid(boxTemplist.get(0).getLogid());
					this.boxDao.update(boxTemplist.get(0));
				}
			}
			// 调整箱子申请最大数
			SiteState siteState = siteStateDao.findBySiteId(site.getSite_id());
			List<SiteGoods> boxList = this.boxDao.findBySiteId(site.getSite_id());
			if (boxList != null && boxList.size() > 0) {
				if (siteState != null) {
					siteState.setBox_all(boxList.size());
					this.siteStateDao.update(siteState);
				}
			}
			
		}
	
	
	@Transactional(readOnly = false)
	public void delete(SiteDevice site) {
		// 删除箱子信息
		SiteGoods boxT = new SiteGoods();
		boxT.setSite_id(site.getSite_id());
		boxT.setCabinet_id(site.getCabinet_location());
		this.boxDao.deleteByCabinetId(boxT);
		// 调整箱子申请最大数
		SiteState siteState = siteStateDao.findBySiteId(site.getSite_id());
		List<SiteGoods> boxList = this.boxDao.findBySiteId(site.getSite_id());
		if (boxList != null && boxList.size() > 0) {
			siteState.setBox_all(boxList.size());
		} else {
			siteState.setBox_all(0);
		}
		this.siteStateDao.update(siteState);
		super.delete(site);
	}
	
}
