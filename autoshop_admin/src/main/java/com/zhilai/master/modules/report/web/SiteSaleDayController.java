package com.zhilai.master.modules.report.web;

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
import com.zhilai.master.modules.report.service.SiteSaleDayService;


/**
 * @author Administrator
 *日销售额
 */
@Controller
@RequestMapping(value = "${adminPath}/report/siteSaleDay")
public class SiteSaleDayController extends BaseController {

	@Autowired
	private SiteSaleDayService siteSaleDayService;

	
	@ModelAttribute
	public SiteSaleDay get(@RequestParam(required=false) String id) {
		SiteSaleDay entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = siteSaleDayService.get(id);
		}
		if (entity == null){
			entity = new SiteSaleDay();
		}
		return entity;
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(SiteSaleDay siteSaleDay, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==siteSaleDay.getRpt_date()||"".equals(siteSaleDay.getRpt_date())) {
			siteSaleDay.setRpt_date(DateUtil.getDate());
		}
		Page<SiteSaleDay> page = siteSaleDayService.findPage(new Page<SiteSaleDay>(request, response), siteSaleDay); 
		model.addAttribute("page", page);
		return "modules/report/siteSaleDayList";
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
    public String exportFile(SiteSaleDay siteSaleDay, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "站点销售日报表信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SiteSaleDay> page = siteSaleDayService.findPage(new Page<SiteSaleDay>(request, response, -1), siteSaleDay);
    		new ExportExcel("站点销售日报表数据", SiteSaleDay.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/report/siteSaleDay/list?repage";
    }
	/*@RequestMapping(value = "delete")
	public String delete(SiteState siteState, RedirectAttributes redirectAttributes) {
		siteStateService.delete(siteState);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:" + adminPath + "/site/siteState/";
	}*/
	
}
