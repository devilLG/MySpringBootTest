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
import com.zhilai.master.modules.site.service.AsSiteService;
import com.zhilai.master.modules.standard.entity.CabinetAttr;
import com.zhilai.master.modules.standard.entity.CabinetStandard;
import com.zhilai.master.modules.standard.entity.CabinetType;
import com.zhilai.master.modules.standard.service.CabinetAttrService;
import com.zhilai.master.modules.standard.service.CabinetStandardService;

/**
 * 规格Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/standard/cabinetStandard")
public class CabinetStandardController extends BaseController {

	@Autowired
	private CabinetStandardService cabinetStandardService;
	@Autowired
	private AsSiteService asSiteService;
	@Autowired
	private CabinetAttrService cabinetAttrService;
	
	
	@ModelAttribute
	public CabinetStandard get(@RequestParam(required=false) String logid) {
		if (StringUtils.isNotBlank(logid)){
			return cabinetStandardService.get(logid);
		}else{
			return new CabinetStandard();
		}
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = {"list", ""})
	public String list(CabinetStandard cabinetStandard, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<CabinetStandard> page = cabinetStandardService.findPage(new Page<CabinetStandard>(request, response), cabinetStandard);
        model.addAttribute("page", page);
		return "modules/standard/cabinetStandardList";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "form")
	public String form(CabinetStandard cabinetStandard, Model model) {
		model.addAttribute("cabinetStandard", cabinetStandard);
		return "modules/standard/cabinetStandardForm";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "save")
	public String save(CabinetStandard cabinetStandard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cabinetStandard)){
			return form(cabinetStandard, model);
		}//相同的配置和柜子类型所在列不能重复添加
		CabinetStandard standard = new CabinetStandard();
		standard.setCabconfig_id(cabinetStandard.getCabconfig_id());
		standard.setCabinettype_id(cabinetStandard.getCabinettype_id());
		standard.setColumn_location(cabinetStandard.getColumn_location());
		List<CabinetStandard> slist = cabinetStandardService.findList(standard);
		if(slist.size() > 0){
			addMessage(redirectAttributes, "不能重复添加");
			return "redirect:" + adminPath + "/standard/cabinetStandard/?repage";
		}
		//没有柜子排列的柜子类型不予添加
		String type_id = cabinetStandard.getCabinettype_id();
		CabinetAttr cabinetAttr = new CabinetAttr();
		cabinetAttr.setCabinettype_id(type_id);
		cabinetAttr.setCloumn_id(standard.getColumn_location());
		List<CabinetAttr> clist = cabinetAttrService.findList(cabinetAttr);
		if(clist.size() > 0){
			cabinetStandardService.save(cabinetStandard);
			addMessage(redirectAttributes, "保存'" + cabinetStandard.getCabconfig_name() + "'成功");
			return "redirect:" + adminPath + "/standard/cabinetStandard/?repage";
		}else{
			addMessage(redirectAttributes, "保存失败，请先将此类型添加至柜子排列中。");
			return "redirect:" + adminPath + "/standard/cabinetStandard/?repage";
		}
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "delete")
	public String delete(CabinetStandard cabinetStandard, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/site/assite/?repage";
		}   
		AsSite site = new AsSite();
		site.setCabinet_type(cabinetStandard.getCabconfig_id());
		List<AsSite> slist = asSiteService.findList(site);
		if(slist.size() > 0){
			addMessage(redirectAttributes, "此状态不允许删除！");
			return "redirect:" + adminPath + "/standard/cabinetStandard/?repage";
		}
			cabinetStandardService.delete(cabinetStandard);
			addMessage(redirectAttributes, "删除规格成功");
		
		return "redirect:" + adminPath + "/standard/cabinetStandard/?repage";
	}
	/**
	 * 导出站点数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("site:assite:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CabinetStandard cabinetStandard, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "规格信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<CabinetStandard> page = cabinetStandardService.findPage(new Page<CabinetStandard>(request, response, -1), cabinetStandard);
    		new ExportExcel("规格数据", CabinetStandard.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/standard/cabinetStandard/list?repage";
    }
	/**
	 * 获取柜子配置JSON数据。
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
		CabinetStandard cabinetStandard = new CabinetStandard();
		List<CabinetStandard> list = cabinetStandardService.findAllList(cabinetStandard);
		for (CabinetStandard e : list) {
			Map<String, String> map = Maps.newLinkedHashMap();
			map.put("柜子配置标识", e.getCabconfig_id());
			map.put("柜子配置名称", e.getCabconfig_name());			
			map.put("柜子类型名称", e.getCabinettype_name());			
			mapList.add(map);
		}
		return mapList;
	}
	
}
