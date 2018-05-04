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
import com.zhilai.master.modules.report.entity.BrandSaleYear;
import com.zhilai.master.modules.report.service.BrandSaleMonthService;
import com.zhilai.master.modules.report.service.BrandSaleYearService;


/**
 * @author Administrator
 *品牌年销售额
 */
@Controller
@RequestMapping(value = "${adminPath}/report/brandSaleYear")
public class BrandSaleYearController extends BaseController {

	@Autowired
	private BrandSaleYearService brandSaleYearService;

	
	@ModelAttribute
	public BrandSaleYear get(@RequestParam(required=false) String id) {
		BrandSaleYear entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = brandSaleYearService.get(id);
		}
		if (entity == null){
			entity = new BrandSaleYear();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(BrandSaleYear brandSaleYear, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==brandSaleYear.getRpt_year()||"".equals(brandSaleYear.getRpt_year())) {
			brandSaleYear.setRpt_year(DateUtils.getYear());
		}
		Page<BrandSaleYear> page = brandSaleYearService.findPage(new Page<BrandSaleYear>(request, response), brandSaleYear); 
		model.addAttribute("page", page);
		return "modules/report/brandSaleYearList";
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
    public String exportFile(BrandSaleYear brandSaleYear, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "品牌销售年报表信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<BrandSaleYear> page = brandSaleYearService.findPage(new Page<BrandSaleYear>(request, response, -1), brandSaleYear);
    		new ExportExcel("品牌销售年报表数据", BrandSaleYear.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/report/brandSaleYear/list?repage";
    }
	/*@RequestMapping(value = "delete")
	public String delete(SiteState siteState, RedirectAttributes redirectAttributes) {
		siteStateService.delete(siteState);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:" + adminPath + "/site/siteState/";
	}*/
	
}
