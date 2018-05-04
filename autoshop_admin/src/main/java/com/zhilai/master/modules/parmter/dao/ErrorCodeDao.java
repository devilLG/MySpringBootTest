package com.zhilai.master.modules.parmter.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.parmter.entity.ErrorCode;
@MyBatisDao
public interface ErrorCodeDao extends CrudDao<ErrorCode> {
	
}
