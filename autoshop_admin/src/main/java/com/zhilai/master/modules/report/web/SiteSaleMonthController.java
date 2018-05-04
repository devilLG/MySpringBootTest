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
import com.zhilai.master.modules.report.entity.SiteSaleDay;
import com.zhilai.master.modules.report.entity.SiteSaleMonth;
import com.zhilai.master.modules.report.service.SiteSaleDayService;
import com.zhilai.master.modules.report.service.SiteSaleMonthService;
import com.zhilai.master.modules.site.entity.SiteState;
import com.zhilai.master.modules.site.service.SiteStateService;


/**
 * @author Administrator
 *月销售额
 */
@Controller
@RequestMapping(value = "${adminPath}/report/siteSaleMonth")
public class SiteSaleMonthController extends BaseController {

	@Autowired
	private SiteSaleMonthService siteSaleMonthService;

	
	@ModelAttribute
	public SiteSaleMonth get(@RequestParam(required=false) String id) {
		SiteSaleMonth entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = siteSaleMonthService.get(id);
		}
		if (entity == null){
			entity = new SiteSaleMonth();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(SiteSaleMonth siteSaleMonth, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==siteSaleMonth.getRpt_year()||"".equals(siteSaleMonth.getRpt_year())) {
			siteSaleMonth.setRpt_year(DateUtils.getYear());
		}
		if (null==siteSaleMonth.getRpt_month()||"".equals(siteSaleMonth.getRpt_month())) {
			siteSaleMonth.setRpt_month(DateUtils.getMonth());
		}
		Page<SiteSaleMonth> page = siteSaleMonthService.findPage(new Page<SiteSaleMonth>(request, response), siteSaleMonth); 
		model.addAttribute("page", page);
		return "modules/report/siteSaleMonthList";
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
    public String exportFile(SiteSaleMonth siteSaleMonth, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "站点销售月报表信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SiteSaleMonth> page = siteSaleMonthService.findPage(new Page<SiteSaleMonth>(request, response, -1), siteSaleMonth);
    		new ExportExcel("站点销售月报表数据", SiteSaleMonth.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/report/siteSaleMonth/list?repage";
    }
	/*@RequestMapping(value = "delete")
	public String delete(SiteState siteState, RedirectAttributes redirectAttributes) {
		siteStateService.delete(siteState);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:" + adminPath + "/site/siteState/";
	}*/
	
}
