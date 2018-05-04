package com.zhilai.master.modules.order.dao;

import java.util.List;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.order.entity.OrderPay;

@MyBatisDao
public interface OrderPayDao extends CrudDao<OrderPay> {
	public List<OrderPay> findPayOverdueList(String pay_overtime);
}
