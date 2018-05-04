/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.parmter.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.zhilai.master.modules.parmter.entity.ErrorCode;
import com.zhilai.master.modules.parmter.service.ErrorCodeService;
import com.zhilai.master.modules.sys.service.SequenceIdService;

/**
 * 错误码Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/parmter/errorCode")
public class ErrorCodeController extends BaseController {

	@Autowired
	private ErrorCodeService errorCodeService;
	@Autowired
	private SequenceIdService sequenceIdService;
	
	@ModelAttribute
	public ErrorCode get(@RequestParam(required=false) String logid) {
		if (StringUtils.isNotBlank(logid)){
			return errorCodeService.get(logid);
		}else{
			return new ErrorCode();
		}
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = {"list", ""})
	public String list(ErrorCode errorCode, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<ErrorCode> page = errorCodeService.findPage(new Page<ErrorCode>(request, response), errorCode);
        model.addAttribute("page", page);
		return "modules/parmter/errorCodeList";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "form")
	public String form(ErrorCode errorCode, Model model) {
		model.addAttribute("errorCode", errorCode);
		return "modules/parmter/errorCodeForm";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "save")
	public String save(ErrorCode errorCode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, errorCode)){
			return form(errorCode, model);
		}
		if (StringUtils.isBlank(errorCode.getLogid())){
			errorCode.setErr_id(sequenceIdService.findNextVal("8888", "err_id", 7));
		}
		errorCodeService.save(errorCode);
		addMessage(redirectAttributes, "保存'" + errorCode.getErr_code() + "'成功");
		return "redirect:" + adminPath + "/parmter/errorCode/?repage";
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "delete")
	public String delete(ErrorCode errorCode, RedirectAttributes redirectAttributes) {
		errorCodeService.delete(errorCode);
		//addMessage(redirectAttributes, ("删除站点成功");
		
		return "redirect:" + adminPath + "/parmter/errorCode/?repage";
	}
		
}
