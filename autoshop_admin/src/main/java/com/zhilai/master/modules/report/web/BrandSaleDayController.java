package com.zhilai.master.modules.report.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.putils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.report.entity.BrandSaleDay;
import com.zhilai.master.modules.report.entity.ClassifySaleDay;
import com.zhilai.master.modules.report.entity.ProductSaleDay;
import com.zhilai.master.modules.report.entity.SiteSaleDay;
import com.zhilai.master.modules.report.entity.SiteSaleMonth;
import com.zhilai.master.modules.report.entity.SiteSaleYear;
import com.zhilai.master.modules.report.service.BrandSaleDayService;
import com.zhilai.master.modules.report.service.ClassifySaleDayService;
import com.zhilai.master.modules.report.service.ProductSaleDayService;
import com.zhilai.master.modules.report.service.SiteSaleDayService;
import com.zhilai.master.modules.report.service.SiteSaleMonthService;
import com.zhilai.master.modules.report.service.SiteSaleYearService;
import com.zhilai.master.modules.site.entity.SiteState;
import com.zhilai.master.modules.site.service.SiteStateService;


/**
 * @author Administrator
 *分类日销售额
 */
@Controller
@RequestMapping(value = "${adminPath}/report/brandSaleDay")
public class BrandSaleDayController extends BaseController {

	@Autowired
	private BrandSaleDayService brandSaleDayService;

	
	@ModelAttribute
	public BrandSaleDay get(@RequestParam(required=false) String id) {
		BrandSaleDay entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = brandSaleDayService.get(id);
		}
		if (entity == null){
			entity = new BrandSaleDay();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(BrandSaleDay brandSaleDay, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==brandSaleDay.getRpt_date()||"".equals(brandSaleDay.getRpt_date())) {
			brandSaleDay.setRpt_date(DateUtil.getDate());
		} 
		Page<BrandSaleDay> page = brandSaleDayService.findPage(new Page<BrandSaleDay>(request, response), brandSaleDay); 
		model.addAttribute("page", page);
		return "modules/report/brandSaleDayList";
	}
	/**
	 * 导出报表数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(BrandSaleDay brandSaleDay, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "品牌销售日报表信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<BrandSaleDay> page = brandSaleDayService.findPage(new Page<BrandSaleDay>(request, response, -1), brandSaleDay);
    		new ExportExcel("品牌销售日报表数据", BrandSaleDay.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/report/brandSaleDay/list?repage";
    }
	/*@RequestMapping(value = "delete")
	public String delete(SiteState siteState, RedirectAttributes redirectAttributes) {
		siteStateService.delete(siteState);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:" + adminPath + "/site/siteState/";
	}*/
	
}
