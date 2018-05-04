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

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.version.entity.UgradeLog;
import com.zhilai.master.modules.version.service.UgradeLogService;

/**
 * 版本升级Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/version/ugradeLog")
public class UgradeLogController extends BaseController {

	@Autowired
	private UgradeLogService ugradeLogService;
	
	
	@ModelAttribute
	public UgradeLog get(@RequestParam(required=false) String logid) {
		if (StringUtils.isNotBlank(logid)){
			return ugradeLogService.get(logid);
		}else{
			return new UgradeLog();
		}
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = {"list", ""})
	public String list(UgradeLog ugradeLog, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<UgradeLog> page = ugradeLogService.findPage(new Page<UgradeLog>(request, response), ugradeLog);
        model.addAttribute("page", page);
		return "modules/version/ugradeLogList";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "form")
	public String form(UgradeLog ugradeLog, Model model) {
		model.addAttribute("ugradeLog", ugradeLog);
		return "modules/version/ugradeLogForm";
	}

	/**
	 * 导出版本数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("site:assite:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(UgradeLog ugradeLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "平台版本信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<UgradeLog> page = ugradeLogService.findPage(new Page<UgradeLog>(request, response, -1), ugradeLog);
    		new ExportExcel("平台数据", UgradeLog.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/version/ugradeLog/list?repage";
    }
	
}
