package com.zhilai.master.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.sys.dao.SequenceIdDao;
import com.zhilai.master.modules.sys.entity.SequenceId;
import com.zhilai.master.modules.sys.entity.User;

@Service
@Transactional(readOnly = true)
public class SequenceIdService extends CrudService<SequenceIdDao, SequenceId> {
	
	@Autowired
	private SequenceIdDao sequenceIdDao; 
	
	@Transactional(readOnly = false)	
	public String findNextVal(String corp_id,String name,int len) {
	    StringBuffer buf=new StringBuffer();
	    SequenceId sequenceId=new SequenceId();
		sequenceId.setName(name);
		sequenceId.setCorp_id(corp_id);
		int id = sequenceIdDao.nextval(sequenceId);
		String tmp=""+id;
		for(int i=0; i<len -tmp.length();  i++)
			buf.append("0");
		buf.append(tmp);
		return  buf.toString();
	}
	public List<SequenceId> findAllList() {
		
		List<SequenceId> list = sequenceIdDao.findAllList();
		return list;
	}
	
    public List<SequenceId> findList(SequenceId id) {
		List<SequenceId> list = sequenceIdDao.findList(id);
		return list;
	}
    @Transactional(readOnly = false)
    public void save(SequenceId id) {
		 sequenceIdDao.insert(id);
		
	}
}

