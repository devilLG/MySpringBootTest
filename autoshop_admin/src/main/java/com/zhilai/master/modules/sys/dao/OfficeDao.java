/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.sys.dao;

import java.util.List;

import com.zhilai.master.common.persistence.TreeDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author zhilai
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	public Office findByCorpId(String corpId);

	public List<Office> findAllCorp();
}
