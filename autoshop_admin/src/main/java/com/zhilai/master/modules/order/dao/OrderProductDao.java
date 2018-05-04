package com.zhilai.master.modules.order.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.order.entity.OrderProduct;

@MyBatisDao
public interface OrderProductDao extends CrudDao<OrderProduct> {
	
}
