/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.trade.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyh.sy.common.DateUtil;
import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.site.dao.SiteDeviceDao;
import com.zhilai.master.modules.site.entity.SiteDevice;
import com.zhilai.master.modules.trade.dao.TradeDefineDao;
import com.zhilai.master.modules.trade.entity.TradeDefine;

/**
 * 交易定义Service
 * 
 * @author zhilai
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class TradeDefineService extends CrudService<TradeDefineDao, TradeDefine> {
	@Autowired
	private TradeDefineDao tradeDefineDao;

	public Page<TradeDefine> findPage(Page<TradeDefine> page, TradeDefine site) {
		// 执行分页查询
		// 设置分页参数
		site.setPage(page);
		page.setList(super.findList(site));
		return page;

		// return super.findPage(page, site);
	}

	@Transactional(readOnly = false)
	public void save(TradeDefine tradeDefine) {
		TradeDefine trade = super.get(tradeDefine.getTrade_code());
		if (trade == null) {
			tradeDefine.preInsert();
			tradeDefineDao.insert(tradeDefine);
		}
		else {
			// 更新数据
			tradeDefine.preUpdate();
			tradeDefineDao.update(tradeDefine);
		}
	}

	@Transactional(readOnly = false)
	public void delete(TradeDefine tradeDefine) {
		super.delete(tradeDefine);
	}

}
