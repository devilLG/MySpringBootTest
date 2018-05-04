/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.person.service;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.modules.person.dao.SitePersonDao;
import com.zhilai.master.modules.person.entity.SitePerson;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 站点Service
 * @author zhilai
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class SitePersonService extends CrudService<SitePersonDao, SitePerson> {
	@Autowired
	private SitePersonDao sitePersonDao;
	public Page<SitePerson> findPage(Page<SitePerson> page, SitePerson sitePerson) {
//		DetachedCriteria dc = sitePersonDao.createDetachedCriteria();
//		if (StringUtils.isNotEmpty(site.getName())){
//			dc.add(Restrictions.like("name", "%"+site.getName()+"%"));
//		}
//		dc.add(Restrictions.eq(Site.FIELD_DEL_FLAG, site.getDelFlag()));
//		//dc.addOrder(Order.asc("id"));
//		return sitePersonDao.find(page, dc);
		
		//site.getSqlMap().put("site", dataScopeFilter(site.getCurrentUser(), "o", "u"));
		// 执行分页查询
		// 设置分页参数
		sitePerson.setPage(page);
		page.setList(super.findList(sitePerson));
		return page;
		
		//return super.findPage(page, site);
	}

	@Transactional(readOnly = false)
	public void save(SitePerson sitePerson) {
		sitePerson.setDown_state(GParameter.downState_wait);
		sitePerson.setDown_time(DateUtil.getNow());
		if (sitePerson.getEmp_type().equals(GParameter.empType_replenishment)) {
			sitePerson.setNotice_state("1");
		}else {
			sitePerson.setNotice_state("3");
		}
		if (StringUtils.isBlank(sitePerson.getLogid())) {
			sitePerson.preInsert();
			sitePerson.setCreate_time(DateUtil.getNow());
			//site.setSite_id(IdGen.uuid());//应该递增，后续更改
			sitePersonDao.insert(sitePerson);
		} else {
			// 更新数据
			sitePerson.preUpdate();
			sitePersonDao.update(sitePerson);
		}
		//super.save(site);
	}
	
	@Transactional(readOnly = false)
	public void delete(SitePerson sitePerson) {
		super.delete(sitePerson);
	}
	
}
