/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.log.entity.PkgLog;
import com.zhilai.master.modules.sys.entity.Log;
import com.zhilai.master.modules.sys.service.LogService;

/**
 * 日志Controller
 * @author zhilai
 * @version 2013-6-2
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;
	
	
	@ModelAttribute
	public Log get(@RequestParam(required = false) String logid) {
		Log entity = null;
		if (StringUtils.isNotBlank(logid)) {
			entity = logService.get(logid);
		}
		if (entity == null) {
			entity = new Log();
		}
		return entity;
	}
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = {"list", ""})
	public String list(Log log, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Log> page = logService.findPage(new Page<Log>(request, response), log); 
        model.addAttribute("page", page);
		return "modules/sys/logList";
	}
	/**
	 * 明细
	 * @param pkgLog
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "detail")
	public String detail(Log log, Model model) {
		model.addAttribute("log", log);
		return "modules/sys/logDetail";
	}

}
