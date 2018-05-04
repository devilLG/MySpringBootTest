package com.zhilai.master.modules.inventory.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.inventory.dao.BarterOrderDetailDao;
import com.zhilai.master.modules.inventory.entity.BarterOrderDetail;

@Service
@Transactional(readOnly = true)
public class BarterOrderDetailService extends CrudService<BarterOrderDetailDao, BarterOrderDetail> {
	
	@Override
	public BarterOrderDetail get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<BarterOrderDetail> findPage(Page<BarterOrderDetail> page, BarterOrderDetail barterOrderDetail) {
		return super.findPage(page, barterOrderDetail);
	}
	
	@Override
	public List<BarterOrderDetail> findList(BarterOrderDetail barterOrderDetail) {
		return super.findList(barterOrderDetail);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(BarterOrderDetail barterOrderDetail) {
		if (StringUtils.isBlank(barterOrderDetail.getLogid())){
			barterOrderDetail.setCreate_time(DateUtil.getNow());
		}
		super.save(barterOrderDetail);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(BarterOrderDetail barterOrderDetail) {
		super.delete(barterOrderDetail);
	}
}

