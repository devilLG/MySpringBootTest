package com.zhilai.master.modules.person.web;

import java.util.List;

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
import com.zhilai.master.modules.person.entity.SiteControlLog;
import com.zhilai.master.modules.person.service.SiteControlLogService;
import com.zhilai.master.modules.utils.GParameter;


 

/**
 * @author Administrator
 * @date 2017-12-12 下午4:36:40
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/person/siteControlLog")
public class SiteControlLogController extends BaseController {

	@Autowired
	private SiteControlLogService siteControlLogService;
//	@Autowired
//	private SequenceIdService sequenceIdService;
	
	@ModelAttribute
	public SiteControlLog get(@RequestParam(required=false) String id) {
		SiteControlLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = siteControlLogService.get(id);
		}
		if (entity == null){
			entity = new SiteControlLog();
		}
		return entity;
	}
	
//	@RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = {"list", ""})
	public String list(SiteControlLog siteControlLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		siteControlLog.setTrade_code(GParameter.issued_sitePerson_trade_code);
		Page<SiteControlLog> page = siteControlLogService.findPage(new Page<SiteControlLog>(request, response), siteControlLog); 
		model.addAttribute("page", page);
		return "modules/person/siteControlLogList";
	}
	
	@RequestMapping(value = {"orderPushList", ""})
	public String orderPushList(SiteControlLog siteControlLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		siteControlLog.setTrade_code(GParameter.issued_partnerOrder_trade_code);
		Page<SiteControlLog> page = siteControlLogService.findPage(new Page<SiteControlLog>(request, response), siteControlLog); 
		model.addAttribute("page", page);
		return "modules/person/siteControlOrderLogList";
	}


//	@RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = "form")
	public String form(SiteControlLog siteControlLog, Model model) {
		model.addAttribute("siteState", siteControlLog);
		return "modules/person/siteControlLogForm";
	}


//	@RequiresPermissions("product:productBrand:edit")
	@RequestMapping(value = "delete")
	public String delete(SiteControlLog siteControlLog, RedirectAttributes redirectAttributes) {
		siteControlLogService.delete(siteControlLog);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:" + adminPath + "/person/siteControlLog/";
	}
	

	/**
	 * 导出
	 * @param productBrand
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SiteControlLog siteControlLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "人员推送"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            siteControlLog.setTrade_code(GParameter.issued_sitePerson_trade_code);
            Page<SiteControlLog> page = siteControlLogService.findPage(new Page<SiteControlLog>(request, response, -1), siteControlLog);
            List<SiteControlLog> siteGoodss = page.getList();
    		new ExportExcel("人员推送", SiteControlLog.class).setDataList(siteGoodss).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出人员推送！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/person/siteControlLog/list?repage";
    }
	/**
	 * 明细
	 * @param productBrand
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("stock:productBrand:view")
	@RequestMapping(value = "detail")
	public String detail(SiteControlLog siteControlLog, Model model) {
		model.addAttribute("siteGoods", siteControlLog);
		return "modules/person/siteControlLogDetail";
	}

}
