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
import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.standard.entity.BoxType;
import com.zhilai.master.modules.standard.entity.CabinetAttr;
import com.zhilai.master.modules.standard.entity.CabinetType;
import com.zhilai.master.modules.standard.service.BoxTypeService;
import com.zhilai.master.modules.standard.service.CabinetAttrService;
import com.zhilai.master.modules.sys.service.SequenceIdService;

/**
 * 货道Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/standard/boxType")
public class BoxTypeController extends BaseController {

	@Autowired
	private BoxTypeService boxTypeService;
	@Autowired
	private SequenceIdService sequenceIdService;
	@Autowired
	private CabinetAttrService cabinetAttrService;
	
	@ModelAttribute
	public BoxType get(@RequestParam(required=false) String type_id) {
		if (StringUtils.isNotBlank(type_id)){
			return boxTypeService.get(type_id);
		}else{
			return new BoxType();
		}
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = {"list", ""})
	public String list(BoxType boxType, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<BoxType> page = boxTypeService.findPage(new Page<BoxType>(request, response), boxType);
        model.addAttribute("page", page);
		return "modules/standard/boxTypeList";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "form")
	public String form(BoxType boxType, Model model) {
		model.addAttribute("boxType", boxType);
		return "modules/standard/boxTypeForm";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "save")
	public String save(BoxType boxType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, boxType)){
			return form(boxType, model);
		}
		boxType.setSize_level(boxType.getSize_length()*boxType.getSize_width()*boxType.getSize_height());
		if(boxType.getType_id() == null || "".equals(boxType.getType_id().trim())){
			boxType.setType_id(sequenceIdService.findNextVal("8888", "type_id", 4));
			//assite.setSite_id(assite.getCorp_id() + "-" + sequenceIdService.findNextVal(assite.getCorp_id(), "site_id", 4));
	     	boxTypeService.save(boxType);
		}else {
			boxTypeService.save(boxType);
		}
		addMessage(redirectAttributes, "保存货道类型'" + boxType.getType_name() + "'成功");
		return "redirect:" + adminPath + "/standard/boxType/?repage";
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "delete")
	public String delete(BoxType boxType, RedirectAttributes redirectAttributes) {
		String type_id = boxType.getType_name();
		CabinetAttr cabinetAttr = new CabinetAttr();
		cabinetAttr.setBoxtype_id(type_id);
		List<CabinetAttr> calist = cabinetAttrService.findList(cabinetAttr);
		if(calist.size()>0){
			addMessage(redirectAttributes, "此类型被引用，不允许删除！");
			return "redirect:" + adminPath + "/standard/boxType/?repage";
		}else{
			boxTypeService.delete(boxType);
			addMessage(redirectAttributes, "删除成功");
		}
		return "redirect:" + adminPath + "/standard/boxType/?repage";
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
    public String exportFile(BoxType boxType, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "boxType"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<BoxType> page = boxTypeService.findPage(new Page<BoxType>(request, response, -1), boxType);
    		new ExportExcel("货道规格数据", BoxType.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/standard/boxType/list?repage";
    }
	/**
	 * 获取货道规格JSON数据。
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
		List<BoxType> list = boxTypeService.findList(new BoxType());
		for (BoxType e : list) {
			Map<String, String> map = Maps.newLinkedHashMap();
			map.put("类型编号", e.getType_id());
			map.put("类型简称 ", e.getType_name());			
			map.put("类型全称", e.getFullname());			
			mapList.add(map);
		}
		return mapList;
	}
}
