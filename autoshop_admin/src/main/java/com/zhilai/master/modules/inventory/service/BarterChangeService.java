package com.zhilai.master.modules.inventory.service;	import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import com.zhilai.master.common.service.CrudService;import com.zhilai.master.modules.inventory.dao.BarterChangeDao;import com.zhilai.master.modules.inventory.entity.BarterChange;	@Service@Transactional(readOnly=true)public class BarterChangeService extends CrudService<BarterChangeDao, BarterChange> {	}