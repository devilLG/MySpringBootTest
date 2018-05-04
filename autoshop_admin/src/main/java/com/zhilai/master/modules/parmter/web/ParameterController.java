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
import com.zhilai.master.modules.parmter.entity.Parameter;
import com.zhilai.master.modules.parmter.service.ParameterService;
import com.zhilai.master.modules.standard.entity.BoxType;
import com.zhilai.master.modules.standard.service.BoxTypeService;
import com.zhilai.master.modules.sys.service.SequenceIdService;

/**
 * 参数Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/parmter/parameter")
public class ParameterController extends BaseController {

	@Autowired
	private ParameterService parameterService;
	@Autowired
	private SequenceIdService sequenceIdService;
	
	@ModelAttribute
	public Parameter get(@RequestParam(required=false) String logid) {
		if (StringUtils.isNotBlank(logid)){
			return parameterService.get(logid);
		}else{
			return new Parameter();
		}
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = {"list", ""})
	public String list(Parameter parameter, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Parameter> page = parameterService.findPage(new Page<Parameter>(request, response), parameter);
        model.addAttribute("page", page);
		return "modules/parmter/parameterList";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "form")
	public String form(Parameter parameter, Model model) {
		model.addAttribute("parameter", parameter);
		return "modules/parmter/parameterForm";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "save")
	public String save(Parameter parameter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, parameter)){
			return form(parameter, model);
		}
		parameterService.save(parameter);
		addMessage(redirectAttributes, "保存参数'" + parameter.getName() + "'成功");
		return "redirect:" + adminPath + "/parmter/parameter/?repage";
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "delete")
	public String delete(Parameter parameter, RedirectAttributes redirectAttributes) {
		
		parameterService.delete(parameter);
		//addMessage(redirectAttributes, ("删除站点成功");
		
		return "redirect:" + adminPath + "/parmter/parameter/?repage";
	}
		
}
