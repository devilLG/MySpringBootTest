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
import com.zhilai.master.modules.log.entity.PkgLog;
import com.zhilai.master.modules.log.service.PkgLogService;
import com.zhilai.master.modules.site.entity.SiteGoods;
import com.zhilai.master.modules.sys.service.SequenceIdService;


/**
 * @author guowei
 * @date 2017-12-12 下午4:36:40
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/log/pkgLog")
public class PkgLogController extends BaseController {

	@Autowired
	private PkgLogService pkgLogService;
	@Autowired
	private SequenceIdService sequenceIdService;

	@ModelAttribute
	public PkgLog get(@RequestParam(required = false) String log_id) {
		PkgLog entity = null;
		if (StringUtils.isNotBlank(log_id)) {
			entity = pkgLogService.get(log_id);
		}
		if (entity == null) {
			entity = new PkgLog();
		}
		return entity;
	}

	// @RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = { "list", "" })
	public String list(PkgLog pkgLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==pkgLog.getTrade_time()||"".equals(pkgLog.getTrade_time())) {
			pkgLog.setTrade_time(DateUtil.getDate());
		}
		if (null == pkgLog.getTrade_time_end() || "".equals(pkgLog.getTrade_time_end())) {
			pkgLog.setTrade_time_end(DateUtil.getDate());
		}
		Page<PkgLog> page = pkgLogService.findPage(new Page<PkgLog>(request, response), pkgLog);
		model.addAttribute("page", page);
		return "modules/log/pkgLogList";
	}

	// @RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = "form")
	public String form(PkgLog pkgLog, Model model) {
		model.addAttribute("pkgLog", pkgLog);
		return "modules/log/pkgLogForm";
	}
	/**
	 * 明细
	 * @param pkgLog
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("stock:productBrand:view")
	@RequestMapping(value = "detail")
	public String detail(PkgLog pkgLog, Model model) {
		model.addAttribute("pkgLog", pkgLog);
		return "modules/log/pkgLogDetail";
	}

	// @RequiresPermissions("product:productBrand:edit")
	@RequestMapping(value = "save")
	public String save(PkgLog pkgLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pkgLog)) {
			return form(pkgLog, model);
		}
		if (pkgLog.getLog_id() == null || "".equals(pkgLog.getLog_id().trim())) {
			pkgLog.setLog_id(sequenceIdService.findNextVal("2", "Log_id", 4));
			pkgLogService.save(pkgLog);
		} else {
			pkgLogService.save(pkgLog);
		}
		addMessage(redirectAttributes, "保存信息成功");
		return "redirect:" + adminPath + "/log/pkgLog/";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "delete")
	public String delete(PkgLog pkgLog, RedirectAttributes redirectAttributes) {
		pkgLogService.delete(pkgLog);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:" + adminPath + "/log/pkgLog/";
	}

}
