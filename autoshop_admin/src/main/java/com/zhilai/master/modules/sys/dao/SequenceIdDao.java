package com.zhilai.master.modules.sys.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;

import com.zhilai.master.modules.sys.entity.SequenceId;

@MyBatisDao
public interface SequenceIdDao extends CrudDao<SequenceId> {
	public int nextval(SequenceId sequenceId);
	public List<SequenceId> findAllList();
	public List<SequenceId> findList(SequenceId id);
	public int deleteByCorpId(String id);
}
