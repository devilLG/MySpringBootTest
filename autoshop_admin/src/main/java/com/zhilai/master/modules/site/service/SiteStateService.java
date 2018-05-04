package com.zhilai.master.modules.site.service;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.site.dao.SiteStateDao;
import com.zhilai.master.modules.site.entity.SiteState;

@Service
@Transactional(readOnly = true)
public class SiteStateService extends CrudService<SiteStateDao, SiteState> {
	
	@Override
	public SiteState get(String id) {
		return super.get(id);
	}
	
	@Override
	public Page<SiteState> findPage(Page<SiteState> page, SiteState siteState) {
		return super.findPage(page, siteState);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(SiteState siteState) {
		if (StringUtils.isBlank(siteState.getLogid())){
			//siteState.setCreate_time(DateUtil.getNow());
			super.save(siteState);
		}
		super.save(siteState);//应该是修改
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(SiteState siteState) {
		super.delete(siteState);
	}
}

