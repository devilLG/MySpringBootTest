package com.zhilai.master.modules.report.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




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
import com.zhilai.master.modules.report.entity.ClassifySaleYear;
import com.zhilai.master.modules.report.service.ClassifySaleYearService;


/**
 * @author Administrator
 *分类年销售额
 */
@Controller
@RequestMapping(value = "${adminPath}/report/classifySaleYear")
public class ClassifySaleYearController extends BaseController {

	@Autowired
	private ClassifySaleYearService classifySaleYearService;

	
	@ModelAttribute
	public ClassifySaleYear get(@RequestParam(required=false) String id) {
		ClassifySaleYear entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = classifySaleYearService.get(id);
		}
		if (entity == null){
			entity = new ClassifySaleYear();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ClassifySaleYear classifySaleYear, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==classifySaleYear.getRpt_year()||"".equals(classifySaleYear.getRpt_year())) {
			classifySaleYear.setRpt_year(DateUtils.getYear());
		} 
		Page<ClassifySaleYear> page = classifySaleYearService.findPage(new Page<ClassifySaleYear>(request, response), classifySaleYear); 
		model.addAttribute("page", page);
		return "modules/report/classifySaleYearList";
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
    public String exportFile(ClassifySaleYear classifySaleYear, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "分类销售年报表信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<ClassifySaleYear> page = classifySaleYearService.findPage(new Page<ClassifySaleYear>(request, response, -1), classifySaleYear);
    		new ExportExcel("分类销售年报表数据", ClassifySaleYear.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/report/classifySaleYear/list?repage";
    }
	/*@RequestMapping(value = "delete")
	public String delete(SiteState siteState, RedirectAttributes redirectAttributes) {
		siteStateService.delete(siteState);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:" + adminPath + "/site/siteState/";
	}*/
	
}
