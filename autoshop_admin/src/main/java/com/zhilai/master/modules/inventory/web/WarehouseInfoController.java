package com.zhilai.master.modules.inventory.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.zhilai.master.modules.inventory.entity.WarehouseInfo;
import com.zhilai.master.modules.inventory.service.WarehouseInfoService;

@Controller
@RequestMapping(value = "${adminPath}/inventory/warehouseInfo")
public class WarehouseInfoController extends BaseController {

	@Autowired
	private WarehouseInfoService warehouseInfoService;
	@ModelAttribute
	public WarehouseInfo get(@RequestParam(required=false) String id) {
		WarehouseInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = warehouseInfoService.get(id);
		}
		if (entity == null){
			entity = new WarehouseInfo();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(WarehouseInfo warehouseInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WarehouseInfo> page = warehouseInfoService.findPage(new Page<WarehouseInfo>(request, response), warehouseInfo); 
		model.addAttribute("page", page);
		return "modules/inventory/warehouseInfoList";
	}

	
	/**
	 * 明细
	 * @param warehouseInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(WarehouseInfo warehouseInfo, Model model) {
		model.addAttribute("warehouseInfo", warehouseInfo);
		return "modules/inventory/warehouseInfoDetail";
	}
	
	/**
	 * 导出
	 * @param warehouseInfo
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(WarehouseInfo warehouseInfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "虚拟仓库"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WarehouseInfo> page = warehouseInfoService.findPage(new Page<WarehouseInfo>(request, response, -1), warehouseInfo);
            List<WarehouseInfo> warehouseInfos = page.getList();
    		new ExportExcel("虚拟仓库", WarehouseInfo.class).setDataList(warehouseInfos).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出虚拟仓库失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/inventory/warehouseInfo/list?repage";
    }
	
}
