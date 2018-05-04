package com.zhilai.master.modules.standard.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.standard.entity.CabinetStandard;
@MyBatisDao
public interface CabinetStandardDao extends CrudDao<CabinetStandard> {
	//public List<CabinetStandard> findAllList();
	public List<CabinetStandard> findAllList(CabinetStandard CabinetStandard);
	public List<CabinetStandard> findByCabconfigId(CabinetStandard CabinetStandard);
	public CabinetStandard findObj(CabinetStandard CabinetStandard);
}
