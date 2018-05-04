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

import com.zhilai.master.modules.version.entity.PlatVersion;

import com.zhilai.master.modules.version.service.PlatVersionService;

/**
 * 平台版本Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/version/platVersion")
public class PlatVersionController extends BaseController {

	@Autowired
	private PlatVersionService platVersionService;
	
	
	@ModelAttribute
	public PlatVersion get(@RequestParam(required=false) String logid) {
		if (StringUtils.isNotBlank(logid)){
			return platVersionService.get(logid);
		}else{
			return new PlatVersion();
		}
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlatVersion platVersion, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PlatVersion> page = platVersionService.findPage(new Page<PlatVersion>(request, response), platVersion);
        model.addAttribute("page", page);
		return "modules/version/platVersionList";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "form")
	public String form(PlatVersion platVersion, Model model) {
		model.addAttribute("platVersion", platVersion);
		return "modules/version/platVersionForm";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "save")
	public String save(PlatVersion platVersion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, platVersion)){
			return form(platVersion, model);
		}
		String plat_code = platVersion.getPlat_code(); 
		if(plat_code.equals("04")){
			platVersion.setPlat_name("网站");
		}
		if(plat_code.equals("03")){
			platVersion.setPlat_name("终端");
		}
		platVersionService.save(platVersion);
		addMessage(redirectAttributes, "保存'" + platVersion.getPlat_name() + "'成功");
		return "redirect:" + adminPath + "/version/platVersion/?repage";
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "delete")
	public String delete(PlatVersion platVersion, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/site/assite/?repage";
		}
			platVersionService.delete(platVersion);
			//addMessage(redirectAttributes, ("删除站点成功");
		
		return "redirect:" + adminPath + "/version/platVersion/?repage";
	}
	/**
	 * 导出平台版本数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("site:assite:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PlatVersion platVersion, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "平台版本信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PlatVersion> page = platVersionService.findPage(new Page<PlatVersion>(request, response, -1), platVersion);
    		new ExportExcel("平台版本数据", PlatVersion.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/version/platVersion/list?repage";
    }
	
}
