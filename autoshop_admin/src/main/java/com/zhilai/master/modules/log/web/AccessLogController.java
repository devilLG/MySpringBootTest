package com.zhilai.master.modules.log.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.putils.utils.DateUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.log.entity.AccessLog;
import com.zhilai.master.modules.log.entity.PkgLog;
import com.zhilai.master.modules.log.service.AccessLogService;
import com.zhilai.master.modules.log.service.PkgLogService;
import com.zhilai.master.modules.sys.service.SequenceIdService;


/**
 * @author guowei
 * @date 2017-12-12 下午4:36:40
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/log/accessLog")
public class AccessLogController extends BaseController {

	@Autowired
	private AccessLogService accessLogService;
	
	@ModelAttribute
	public AccessLog get(@RequestParam(required = false) String log_id) {
		AccessLog entity = null;
		if (StringUtils.isNotBlank(log_id)) {
			entity = accessLogService.get(log_id);
		}
		if (entity == null) {
			entity = new AccessLog();
		}
		return entity;
	}

	// @RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = { "list", "" })
	public String list(AccessLog Log, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==Log.getAccess_time()||"".equals(Log.getAccess_time())) {
			Log.setAccess_time(DateUtil.getDate());
		}
		if (null == Log.getAccess_time_end() || "".equals(Log.getAccess_time_end())) {
			Log.setAccess_time_end(DateUtil.getDate());
		}
		Page<AccessLog> page = accessLogService.findPage(new Page<AccessLog>(request, response), Log);
		model.addAttribute("page", page);
		return "modules/log/accessLogList";
	}
}
