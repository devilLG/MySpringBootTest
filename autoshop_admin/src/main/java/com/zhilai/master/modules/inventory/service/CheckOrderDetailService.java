package com.zhilai.master.modules.inventory.service;

import java.util.List;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.inventory.dao.CheckOrderDetailDao;
import com.zhilai.master.modules.inventory.entity.CheckOrderDetail;

@Service
@Transactional(readOnly = true)
public class CheckOrderDetailService extends CrudService<CheckOrderDetailDao, CheckOrderDetail> {
	
	@Override
	public CheckOrderDetail get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<CheckOrderDetail> findPage(Page<CheckOrderDetail> page, CheckOrderDetail checkOrderDetail) {
		return super.findPage(page, checkOrderDetail);
	}
	
	@Override
	public List<CheckOrderDetail> findList(CheckOrderDetail checkOrderDetail) {
		return super.findList(checkOrderDetail);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(CheckOrderDetail checkOrderDetail) {
		if (StringUtils.isBlank(checkOrderDetail.getLogid())){
			checkOrderDetail.setCreate_time(DateUtil.getNow());
		}
		super.save(checkOrderDetail);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(CheckOrderDetail checkOrderDetail) {
		super.delete(checkOrderDetail);
	}
}

