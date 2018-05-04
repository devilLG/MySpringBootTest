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
import com.zhilai.master.modules.log.entity.TradeLog;
import com.zhilai.master.modules.log.service.AccessLogService;
import com.zhilai.master.modules.log.service.PkgLogService;
import com.zhilai.master.modules.log.service.TradeLogService;
import com.zhilai.master.modules.sys.service.SequenceIdService;


/**
 * @author guowei
 * @date 2017-12-12 下午4:36:40
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/log/tradeLog")
public class TradeLogController extends BaseController {

	@Autowired
	private TradeLogService tradeLogService;
	
	@ModelAttribute
	public TradeLog get(@RequestParam(required = false) String log_id) {
		TradeLog entity = null;
		if (StringUtils.isNotBlank(log_id)) {
			entity = tradeLogService.get(log_id);
		}
		if (entity == null) {
			entity = new TradeLog();
		}
		return entity;
	}

	// @RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = { "list", "" })
	public String list(TradeLog Log, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==Log.getBegin_time()||"".equals(Log.getBegin_time())) {
			Log.setBegin_time(DateUtil.getDate());
		}
		if (null == Log.getEnd_time() || "".equals(Log.getEnd_time())) {
			Log.setEnd_time(DateUtil.getDate());
		}
		Page<TradeLog> page = tradeLogService.findPage(new Page<TradeLog>(request, response), Log);
		model.addAttribute("page", page);
		return "modules/log/tradeLogList";
	}
}
