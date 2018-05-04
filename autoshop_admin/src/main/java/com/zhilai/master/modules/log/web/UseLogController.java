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
import com.zhilai.master.modules.log.entity.UseLog;
import com.zhilai.master.modules.log.service.AccessLogService;
import com.zhilai.master.modules.log.service.PkgLogService;
import com.zhilai.master.modules.log.service.UseLogService;
import com.zhilai.master.modules.sys.service.SequenceIdService;


/**
 * @author guowei
 * 终端日志
 * @date 2017-12-12 下午4:36:40
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/log/useLog")
public class UseLogController extends BaseController {

	@Autowired
	private UseLogService useLogService;
	
	@ModelAttribute
	public UseLog get(@RequestParam(required = false) String logid) {
		UseLog entity = null;
		if (StringUtils.isNotBlank(logid)) {
			entity = useLogService.get(logid);
		}
		if (entity == null) {
			entity = new UseLog();
		}
		return entity;
	}

	// @RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = { "list", "" })
	public String list(UseLog Log, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==Log.getOper_time()||"".equals(Log.getOper_time())) {
			Log.setOper_time(DateUtil.getDate());
		}
		if (null == Log.getOper_time_end() || "".equals(Log.getOper_time_end())) {
			Log.setOper_time_end(DateUtil.getDate());
		}
		Page<UseLog> page = useLogService.findPage(new Page<UseLog>(request, response), Log);
		model.addAttribute("page", page);
		return "modules/log/useLogList";
	}
}
