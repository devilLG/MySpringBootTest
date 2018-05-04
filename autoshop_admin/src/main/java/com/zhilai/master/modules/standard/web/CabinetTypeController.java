/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.standard.web;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhilai.master.common.config.Global;
import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.site.entity.AsSite;
import com.zhilai.master.modules.standard.entity.CabinetAttr;
import com.zhilai.master.modules.standard.entity.CabinetStandard;
import com.zhilai.master.modules.standard.entity.CabinetType;
import com.zhilai.master.modules.standard.service.CabinetAttrService;
import com.zhilai.master.modules.standard.service.CabinetStandardService;
import com.zhilai.master.modules.standard.service.CabinetTypeService;

/**
 * 站点Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/standard/cabinetType")
public class CabinetTypeController extends BaseController {

	@Autowired
	private CabinetTypeService cabinetTypeService;
	@Autowired
	private CabinetStandardService cabinetStandardService;
	@Autowired
	private CabinetAttrService cabinetAttrService;
	
	
	@ModelAttribute
	public CabinetType get(@RequestParam(required=false) String logid) {
		if (StringUtils.isNotBlank(logid)){
			return cabinetTypeService.get(logid);
		}else{
			return new CabinetType();
		}
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = {"list", ""})
	public String list(CabinetType cabinetType, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<CabinetType> page = cabinetTypeService.findPage(new Page<CabinetType>(request, response), cabinetType);
        model.addAttribute("page", page);
		return "modules/standard/cabinetTypeList";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "form")
	public String form(CabinetType cabinetType, Model model) {
		model.addAttribute("cabinetType", cabinetType);
		return "modules/standard/cabinetTypeForm";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "save")
	public String save(CabinetType cabinetType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cabinetType)){
			return form(cabinetType, model);
		}
		cabinetTypeService.save(cabinetType);
		addMessage(redirectAttributes, "保存'" + cabinetType.getType_name() + "'成功");
		return "redirect:" + adminPath + "/standard/cabinetType/?repage";
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "delete")
	public String delete(CabinetType cabinetType, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/site/assite/?repage";
		}
		CabinetStandard Standard = new CabinetStandard();
		Standard.setCabinettype_id(cabinetType.getType_id());
		List<CabinetStandard> stalist = cabinetStandardService.findList(Standard);
		CabinetAttr cabinetAttr = new CabinetAttr();
		cabinetAttr.setCabinettype_id(cabinetType.getType_id());
		List<CabinetAttr> attrlist = cabinetAttrService.findList(cabinetAttr);
		if(stalist.size() > 0||attrlist.size() > 0){
			addMessage(redirectAttributes, "此类型被引用，不允许删除！");
			return "redirect:" + adminPath + "/standard/cabinetType/?repage";
		}else{
			cabinetTypeService.delete(cabinetType);
			addMessage(redirectAttributes, "删除成功");
		}
		return "redirect:" + adminPath + "/standard/cabinetType/?repage";
	}
	/**
	 * 导出数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("site:assite:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CabinetType cabinetType, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "规格信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<CabinetType> page = cabinetTypeService.findPage(new Page<CabinetType>(request, response, -1), cabinetType);
    		new ExportExcel("柜子类型数据", CabinetType.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/standard/cabinetType/list?repage";
    }
	/**
	 * 获取柜子类型JSON数据。
	 * @param extId 排除的ID  注意id后台与前端的命名
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "tableData")
	public List<Map<String, String>> tableData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, String>> mapList = Lists.newArrayList();
		List<CabinetType> list = cabinetTypeService.findList(new CabinetType());
		for (CabinetType e : list) {
			Map<String, String> map = Maps.newLinkedHashMap();
			map.put("类型编号", e.getType_id());
			map.put("类型简称 ", e.getType_name());			
			map.put("类型全称", e.getFullname());			
			mapList.add(map);
		}
		return mapList;
	}
	
}
