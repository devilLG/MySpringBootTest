package com.zhilai.master.modules.parmter.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.parmter.entity.MsgSend;

@MyBatisDao
public interface MsgSendDao extends CrudDao<MsgSend> {
	public List<MsgSend> findWarnList(MsgSend msgSend);
}
