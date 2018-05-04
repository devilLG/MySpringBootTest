package com.zhilai.master.task.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.task.dao.CallProcedureDao;

@Service
@Transactional(readOnly = true)
public class TaskCallProcedureService {
	private static Logger log = Logger.getLogger(TaskCallProcedureService.class);
	@Autowired
	private CallProcedureDao callProcedureDao;

	@Transactional(readOnly = false)
	public int executeCall(String task_code, String report_date) {
		String ret_code = "";
		if (null == report_date || !DateUtils.isValidDate(report_date)) {
			ret_code = "-1";				
		}
		Map<String, Object> param = new HashMap<String, Object>();
		report_date = DateUtils.EnToCn(report_date);
		String beginTime = " 00：00：00";
		String endTime = " 23：59：59";
		String month = report_date.substring(5,7);
		String year = report_date.substring(0,4);
		param.put("p_beginTime", report_date+beginTime);
		param.put("p_endTime", report_date+endTime);
		param.put("p_month", month);
		param.put("p_year", year);
		param.put("p_year", year);
		try{
			if (task_code.equals("1001")) {
				callProcedureDao.SiteSaleDay(param);
			}else if(task_code.equals("1002")) {
				callProcedureDao.SiteSaleMonth(param);
			}else if(task_code.equals("1003")) {
				callProcedureDao.SiteSaleYear(param);
			}else if(task_code.equals("1004")) {
				callProcedureDao.ProductSaleDay(param);
			}else if(task_code.equals("1005")) {
				callProcedureDao.ProductSaleMonth(param);
			}else if(task_code.equals("1006")) {
				callProcedureDao.ProductSaleYear(param);
			}else if(task_code.equals("1007")) {
				callProcedureDao.ClassifySaleDay(param);
			}else if(task_code.equals("1008")) {
				callProcedureDao.ClassifySaleMonth(param);
			}else if(task_code.equals("1009")) {
				callProcedureDao.ClassifySaleYear(param);
			}else if(task_code.equals("1010")) {
				callProcedureDao.BrandSaleDay(param);
			}else if(task_code.equals("1011")) {
				callProcedureDao.BrandSaleMonth(param);
			}else if(task_code.equals("1012")) {
				callProcedureDao.BrandSaleYear(param);
			}else if(task_code.equals("1013")) {
				callProcedureDao.CorpSaleDay(param);
			}else if(task_code.equals("1014")) {
				callProcedureDao.CorpSaleMonth(param);
			}else if(task_code.equals("1015")) {
				callProcedureDao.CorpSaleYear(param);
			}
			
			log.info(task_code+" report_date=" + report_date+ "生成成功!");
		} catch (Exception e) {
			   log.error(task_code + "is fail" + e.getMessage());
			   e.printStackTrace();
		}
		return 0;
	}
	
}
