/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.version.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhilai.master.common.config.Global;
import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.site.entity.AsSite;
import com.zhilai.master.modules.site.service.AsSiteService;
import com.zhilai.master.modules.version.entity.SiteVersion;
import com.zhilai.master.modules.version.service.SiteVersionService;

/**
 * 终端版本Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/version/siteVersion")
public class SiteVersionController extends BaseController {

	@Autowired
	private SiteVersionService siteVersionService;
	@Autowired
	private AsSiteService asSiteService;
	
	
	@ModelAttribute
	public SiteVersion get(@RequestParam(required=false) String logid) {
		if (StringUtils.isNotBlank(logid)){
			return siteVersionService.get(logid);
		}else{
			return new SiteVersion();
		}
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = {"list", ""})
	public String list(SiteVersion siteVersion, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<SiteVersion> page = siteVersionService.findPage(new Page<SiteVersion>(request, response), siteVersion);
        model.addAttribute("page", page);
		return "modules/version/siteVersionList";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "form")
	public String form(SiteVersion siteVersion, Model model) {
		model.addAttribute("siteVersion", siteVersion);
		return "modules/version/siteVersionForm";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "save")
	public String save(SiteVersion siteVersion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, siteVersion)){
			return form(siteVersion, model);
		}
		AsSite site = asSiteService.get(siteVersion.getSite_id());
		if(site != null){
			siteVersion.setTree_id(site.getTree_id());
			siteVersion.setTree_name(site.getTree_name());
		}
		siteVersionService.save(siteVersion);
		addMessage(redirectAttributes, "保存'" + siteVersion.getSite_name() + "'成功");
		return "redirect:" + adminPath + "/version/siteVersion/?repage";
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "delete")
	public String delete(SiteVersion siteVersion, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/site/assite/?repage";
		}
			siteVersionService.delete(siteVersion);
			//addMessage(redirectAttributes, ("删除站点成功");
		
		return "redirect:" + adminPath + "/version/siteVersion/?repage";
	}
	/**
	 * 导出终端版本数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("site:assite:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SiteVersion siteVersion, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "终端版本信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SiteVersion> page = siteVersionService.findPage(new Page<SiteVersion>(request, response, -1), siteVersion);
    		new ExportExcel("终端版本数据", SiteVersion.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/version/siteVersion/list?repage";
    }
	
}
