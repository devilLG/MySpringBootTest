/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.standard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.standard.dao.BoxTypeDao;
import com.zhilai.master.modules.standard.entity.BoxType;

/**
 * 站点Service
 * @author zhilai
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class BoxTypeService extends CrudService<BoxTypeDao, BoxType> {
	@Autowired
	private BoxTypeDao boxTypeDao;

	public Page<BoxType> findPage(Page<BoxType> page, BoxType box) {
		box.setPage(page);
		page.setList(super.findList(box));
		return page;
		
		//return super.findPage(page, site);
	}
	
	public BoxType findOneByTypeName(String typeName) {
		return boxTypeDao.findOneByTypeName(typeName);
	}

	@Transactional(readOnly = false)
	public void save(BoxType box) {
		String id = box.getType_id();
		BoxType site = super.get(id);
		if (site == null) {
			box.preInsert();
			boxTypeDao.insert(box);
		} else {
			// 更新数据
			box.preUpdate();
			boxTypeDao.update(box);
		}
		//super.save(site);
	}
	
	@Transactional(readOnly = false)
	public void delete(BoxType box) {
		super.delete(box);
	}
	
}
