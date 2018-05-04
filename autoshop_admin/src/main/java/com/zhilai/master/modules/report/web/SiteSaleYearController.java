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
import com.zhilai.master.modules.report.entity.SiteSaleYear;
import com.zhilai.master.modules.report.service.SiteSaleDayService;
import com.zhilai.master.modules.report.service.SiteSaleMonthService;
import com.zhilai.master.modules.report.service.SiteSaleYearService;
import com.zhilai.master.modules.site.entity.SiteState;
import com.zhilai.master.modules.site.service.SiteStateService;


/**
 * @author Administrator
 *站点年销售额
 */
@Controller
@RequestMapping(value = "${adminPath}/report/siteSaleYear")
public class SiteSaleYearController extends BaseController {

	@Autowired
	private SiteSaleYearService siteSaleYearService;

	
	@ModelAttribute
	public SiteSaleYear get(@RequestParam(required=false) String id) {
		SiteSaleYear entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = siteSaleYearService.get(id);
		}
		if (entity == null){
			entity = new SiteSaleYear();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(SiteSaleYear siteSaleYear, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==siteSaleYear.getRpt_year()||"".equals(siteSaleYear.getRpt_year())) {
			siteSaleYear.setRpt_year(DateUtils.getYear());
		} 
		Page<SiteSaleYear> page = siteSaleYearService.findPage(new Page<SiteSaleYear>(request, response), siteSaleYear); 
		model.addAttribute("page", page);
		return "modules/report/siteSaleYearList";
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
    public String exportFile(SiteSaleYear siteSaleYear, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "站点销售年报表信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SiteSaleYear> page = siteSaleYearService.findPage(new Page<SiteSaleYear>(request, response, -1), siteSaleYear);
    		new ExportExcel("站点销售年报表数据", SiteSaleYear.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/report/siteSaleYear/list?repage";
    }
	/*@RequestMapping(value = "delete")
	public String delete(SiteState siteState, RedirectAttributes redirectAttributes) {
		siteStateService.delete(siteState);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:" + adminPath + "/site/siteState/";
	}*/
	
}
