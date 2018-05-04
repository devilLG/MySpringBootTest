package com.zhilai.master.modules.sys.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.report.entity.CorpSaleDay;
import com.zhilai.master.modules.report.entity.CorpSaleMonth;
import com.zhilai.master.modules.report.service.CorpSaleDayService;
import com.zhilai.master.modules.report.service.CorpSaleMonthService;
import com.zhilai.master.modules.site.entity.AsSite;
import com.zhilai.master.modules.site.service.AsSiteService;
import com.zhilai.master.modules.sys.entity.AreaData;
import com.zhilai.master.modules.sys.entity.ChartTest;
import com.zhilai.master.modules.sys.entity.DonutData;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.sys.utils.UserUtils;

/**
 * 图表显示
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/index")
public class IndexController extends BaseController {
	@Autowired
	private CorpSaleDayService corpSaleDayService;
	@Autowired
	private CorpSaleMonthService corpSaleMonthService;
	@Autowired
	private AsSiteService asSiteService;
	
	@RequestMapping(value = "chart")
	public String chart(HttpServletRequest request, HttpServletResponse response, Model model) {
		String corp_id = "";
		User user = UserUtils.getUser();
		if(user != null && user.getCorpId() != null & !"1".equals(user.getCorpId())){
			corp_id = user.getCorpId();
		}
		String report_date = DateUtil.getDate();
		String startTime = report_date.substring(0,8)+"01";
		model.addAttribute("beginTime", startTime);//本月开始时间
		model.addAttribute("endTime", report_date);//本月结束时间
		//查询公司日报表
		CorpSaleDay corpSaleDay = new CorpSaleDay();
		corpSaleDay.setRpt_date(report_date);
		corpSaleDay.setCorp_id(corp_id);
		List<CorpSaleDay> corpSaleDayList = corpSaleDayService.findList(corpSaleDay);
		double sale_money = 0;//销售额
		int order_num = 0;//订单数
		int product_num = 0;//销售商品数
		for(CorpSaleDay corpSaleDayP : corpSaleDayList){
			sale_money = sale_money + corpSaleDayP.getSale_money();
			order_num = order_num + corpSaleDayP.getOrder_num();
			product_num = product_num + corpSaleDayP.getProduct_num();
		}
		//本日销售额
		model.addAttribute("saleMoneyDay", sale_money);
		//本日订单数
		model.addAttribute("orderNumDay", order_num);
		//本日销售商品数
		model.addAttribute("productNumDay", product_num);
		//查询公司月报表
		String month = report_date.substring(5,7);
		String year = report_date.substring(0,4);
		CorpSaleMonth corpSaleMonth = new CorpSaleMonth();
		corpSaleMonth.setRpt_month(Integer.parseInt(month)+"");
		corpSaleMonth.setRpt_year(year);
		corpSaleMonth.setCorp_id(corp_id);
		List<CorpSaleMonth> corpSaleMonthList = corpSaleMonthService.findList(corpSaleMonth);
		sale_money = 0;//销售额
		order_num = 0;//订单数
		int refund_num = 0;// 退款订单量
		int wechat_num = 0;// 微信销售订单量
		int app_num = 0;// app销售订单量
		int terminal_num = 0;// 终端销售订单量
		for(CorpSaleMonth corpSaleMonthP : corpSaleMonthList){
			sale_money = sale_money + corpSaleMonthP.getSale_money();
			order_num = order_num + corpSaleMonthP.getOrder_num();
			refund_num = refund_num + corpSaleMonthP.getRefund_num();
			wechat_num = wechat_num + corpSaleMonthP.getWechat_num();
			app_num = app_num + corpSaleMonthP.getApp_num();
			terminal_num = terminal_num + corpSaleMonthP.getTerminal_num();
		}
		//本月销售额
		model.addAttribute("saleMoneyMonth", sale_money);
		//本月订单数
		model.addAttribute("orderNumMonth", order_num);
		//本月退款数
		model.addAttribute("refundNumMonth", refund_num);
		//每月销售额趋势折线图
     	List<AreaData> areaDatas=new ArrayList<AreaData>();
     	//每月订单总数统计 
     	List<Object[]> perList=new ArrayList<Object[]>();
     	for(int i=1 ; i<=12 ; i++){
     		CorpSaleMonth corpSaleMonthP = new CorpSaleMonth();
     		corpSaleMonthP.setRpt_year(year);
     		corpSaleMonthP.setRpt_month(i+"");
     		corpSaleMonthP.setCorp_id(corp_id);
     		List<CorpSaleMonth> corpSaleMonthListR = corpSaleMonthService.findList(corpSaleMonthP);
     		sale_money = 0;//销售额
    		order_num = 0;//订单数
     		for(CorpSaleMonth corpSaleMonthT : corpSaleMonthListR){
    			sale_money = sale_money + corpSaleMonthT.getSale_money();
    			order_num = order_num + corpSaleMonthT.getOrder_num();
    		}
     		areaDatas.add(new AreaData(corpSaleMonthP.getRpt_year()+"-"+(i<=9?"0":"")+corpSaleMonthP.getRpt_month(),sale_money,0));
     		perList.add(new Object[]{corpSaleMonthP.getRpt_year()+"-"+(i<=9?"0":"")+corpSaleMonthP.getRpt_month(),order_num});
     	}
     	//告警信息
     	AsSite site = new AsSite();
     	site.setCorp_id(corp_id);
     	List<AsSite> siteList = asSiteService.findWarnList(site);
     	for(AsSite siteP : siteList){
     		siteP.setWarn_type(DictUtils.getDictLabel(siteP.getWarn_type(), "warn_type", "未知"));
     	}
    	model.addAttribute("siteList", siteList);
    	model.addAttribute("warnNum", siteList.size());
     	List<DonutData> donutDatas=new ArrayList<DonutData>();
     	String colorCode="";// 颜色生成
     	model.addAttribute("colorCode", "["+colorCode+"]");
     	model.addAttribute("donutDatas", donutDatas);
     	ChartTest che=new ChartTest();
        che.setAreaDatas(areaDatas);
        che.setDonutDatas(donutDatas);
        if(wechat_num != 0 || app_num != 0 || terminal_num != 0){
        	 // 本月各渠道销售情况统计
            ChartTest che2=new ChartTest();
            List<Object[]> perListR=new ArrayList<Object[]>();
            perListR.add(new Object[]{"微信",wechat_num});
            perListR.add(new Object[]{"APP",app_num});
            perListR.add(new Object[]{"终端",terminal_num});
            che2.setPerList(perListR);
            model.addAttribute("ChartTest2", che2);
            che.setPerList(perList);
        }
        model.addAttribute("ChartTest", che);
		return "modules/sys/chartIndex";
	}
	
}
