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
import com.zhilai.master.modules.report.entity.BrandSaleMonth;
import com.zhilai.master.modules.report.service.BrandSaleMonthService;


/**
 * @author Administrator
 *品牌月销售额
 */
@Controller
@RequestMapping(value = "${adminPath}/report/brandSaleMonth")
public class BrandSaleMonthController extends BaseController {

	@Autowired
	private BrandSaleMonthService brandSaleMonthService;

	
	@ModelAttribute
	public BrandSaleMonth get(@RequestParam(required=false) String id) {
		BrandSaleMonth entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = brandSaleMonthService.get(id);
		}
		if (entity == null){
			entity = new BrandSaleMonth();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(BrandSaleMonth brandSaleMonth, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==brandSaleMonth.getRpt_year()||"".equals(brandSaleMonth.getRpt_year())) {
			brandSaleMonth.setRpt_year(DateUtils.getYear());
		}
		if (null==brandSaleMonth.getRpt_month()||"".equals(brandSaleMonth.getRpt_month())) {
			brandSaleMonth.setRpt_month(DateUtils.getMonth());
		} 
		Page<BrandSaleMonth> page = brandSaleMonthService.findPage(new Page<BrandSaleMonth>(request, response), brandSaleMonth); 
		model.addAttribute("page", page);
		return "modules/report/brandSaleMonthList";
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
    public String exportFile(BrandSaleMonth brandSaleMonth, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "品牌销售月报表信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<BrandSaleMonth> page = brandSaleMonthService.findPage(new Page<BrandSaleMonth>(request, response, -1), brandSaleMonth);
    		new ExportExcel("品牌销售月报表数据", BrandSaleMonth.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/report/brandSaleMonth/list?repage";
    }
	/*@RequestMapping(value = "delete")
	public String delete(SiteState siteState, RedirectAttributes redirectAttributes) {
		siteStateService.delete(siteState);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:" + adminPath + "/site/siteState/";
	}*/
	
}
