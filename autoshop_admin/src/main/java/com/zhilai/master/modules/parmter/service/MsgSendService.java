package com.zhilai.master.modules.parmter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.service.CrudService;
import com.zhilai.master.modules.parmter.dao.MsgSendDao;
import com.zhilai.master.modules.parmter.entity.MsgSend;

@Service
@Transactional(readOnly = true)
public class MsgSendService extends CrudService<MsgSendDao, MsgSend> {
	
	@Autowired
	private MsgSendDao msgSendDao;
	
	public List<MsgSend> findWarnList(MsgSend msgSend) {
		return msgSendDao.findWarnList(msgSend);
	}
	
}

