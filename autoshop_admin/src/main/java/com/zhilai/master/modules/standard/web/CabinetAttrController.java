/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.standard.web;

import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.standard.entity.BoxType;
import com.zhilai.master.modules.standard.entity.CabinetAttr;
import com.zhilai.master.modules.standard.entity.CabinetType;
import com.zhilai.master.modules.standard.service.CabinetAttrService;
import com.zhilai.master.modules.standard.service.CabinetTypeService;
import com.zhilai.master.modules.sys.service.SequenceIdService;

/**
 * 站点Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/standard/cabinetAttr")
public class CabinetAttrController extends BaseController {

	@Autowired
	private CabinetAttrService cabinetAttrService;
	@Autowired
	private SequenceIdService sequenceIdService;
	@Autowired
	private CabinetTypeService cabinetTypeService;
	
	@ModelAttribute
	public CabinetAttr get(@RequestParam(required=false) String attr_id) {
		if (StringUtils.isNotBlank(attr_id)){
			return cabinetAttrService.get(attr_id);
		}else{
			return new CabinetAttr();
		}
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = {"list", ""})
	public String list(CabinetAttr cab, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<CabinetAttr> page = cabinetAttrService.findPage(new Page<CabinetAttr>(request, response), cab);
        model.addAttribute("page", page);
		return "modules/standard/cabinetAttrList";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "form")
	public String form(CabinetAttr cabinetAttr, Model model) {
		model.addAttribute("cabinetAttr", cabinetAttr);
		return "modules/standard/cabinetAttrForm";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "save")
	public String save(CabinetAttr cabinetAttr, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cabinetAttr)){
			return form(cabinetAttr, model);
		}
		if(cabinetAttr.getAttr_id() == null || "".equals(cabinetAttr.getAttr_id().trim())){
			cabinetAttr.setAttr_id(sequenceIdService.findNextVal("8888", "attr_id", 7));	
		}
		CabinetAttr Cabinet = new CabinetAttr();
		CabinetType type = new CabinetType();
		String type_id = cabinetAttr.getCabinettype_id(); 
		type = cabinetTypeService.findByid(type_id);
		//添加和修改时判断总列数不能大于柜子类型的总列数
		if(cabinetAttr.getCloumn_id()>type.getColumn_num()){
			addMessage(redirectAttributes, "列数不能大于柜子类型的总列数");
			return "redirect:" + adminPath + "/standard/cabinetAttr/?repage";
		}
		List<CabinetAttr> clist = null;
		
		//相同类型 层数和列数不能重复
		Cabinet.setBox_id(cabinetAttr.getBox_id());
		Cabinet.setBoxtype_id(cabinetAttr.getBoxtype_id());
		Cabinet.setCabinettype_id(cabinetAttr.getCabinettype_id());
		Cabinet.setCloumn_id(cabinetAttr.getCloumn_id());
		Cabinet.setLayer_num(cabinetAttr.getLayer_num());
		Cabinet.setState(cabinetAttr.getState());
		clist = cabinetAttrService.findList(Cabinet);
		if(clist.size()>0){
			addMessage(redirectAttributes, "柜子列数或层数重复");
			return "redirect:" + adminPath + "/standard/cabinetAttr/?repage";
		}
		
		cabinetAttrService.save(cabinetAttr);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/standard/cabinetAttr/?repage";
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "delete")
	public String delete(CabinetAttr cabinetAttr,String cabinettype_id,String box_id,String cloumn_id,String layer_num,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, Model model) {
		CabinetAttr cabinet = new CabinetAttr();
		
		if(cloumn_id!=null&&!cloumn_id.equals("")){
			cabinet.setCloumn_id(Integer.parseInt(cloumn_id));
		}
		if(box_id!=null&&!box_id.equals("")){
			cabinet.setBox_id(Integer.parseInt(box_id));
		}
		if(cabinettype_id!=null&&!cabinettype_id.equals("")){
			cabinet.setCabinettype_id(cabinettype_id);
		}
		if(layer_num!=null&&!layer_num.equals("")){
			cabinet.setLayer_num(layer_num);
		}
		
		if(cabinetAttr.getState().equals("0")){
			addMessage(redirectAttributes, "此状态不允许删除！");
			return list(cabinet,request, response, model);
			//return "redirect:" + adminPath + "/standard/cabinetAttr/?repage";
		}else {
			cabinetAttrService.delete(cabinetAttr);
			addMessage(redirectAttributes, "删除成功");
		}
		return list(cabinet,request, response, model); 
		//return "redirect:" + adminPath + "/standard/cabinetAttr/list";
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
    public String exportFile(CabinetAttr cabinetAttr, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "柜子排列信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<CabinetAttr> page = cabinetAttrService.findPage(new Page<CabinetAttr>(request, response, -1), cabinetAttr);
    		new ExportExcel("柜子排列数据", CabinetAttr.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/standard/cabinetAttr/list?repage";
    }
	
}
