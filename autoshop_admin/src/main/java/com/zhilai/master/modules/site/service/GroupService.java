package com.zhilai.master.modules.site.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.site.dao.GroupDao;
import com.zhilai.master.modules.site.entity.Group;

@Service
@Transactional(readOnly = true)
public class GroupService extends CrudService<GroupDao, Group> {
	
	@Transactional(readOnly = false)
	public void insert(Group ro) {
		dao.insert(ro);
	}
	
	@Transactional(readOnly = false)
	public void update(Group ro) {
		dao.update(ro);
	}
	/**
	 * 查询站点表中的分组信息
	 * @return
	 */
	public List<Group> getSiteGroup(){
		return dao.getSiteGroup();
		
	}
	/**
	 * 查询站点表中的分组信息
	 * @return
	 */
	public Page<Group> getSiteGroup(Page<Group> page){
		page.setList(dao.getSiteGroup());
		return page;
	}

}
