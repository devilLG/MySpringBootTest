/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.gen.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.gen.entity.GenTable;

/**
 * 业务表DAO接口
 * @author zhilai
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenTableDao extends CrudDao<GenTable> {
	
}