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
import com.zhilai.master.modules.inventory.entity.InventoryProduct;
import com.zhilai.master.modules.inventory.service.InventoryProductService;
import com.zhilai.master.modules.sys.service.SequenceIdService;

@Controller
@RequestMapping(value = "${adminPath}/inventory/inventoryProduct")
public class InventoryProductController extends BaseController {

	@Autowired
	private InventoryProductService inventoryProductService;
	@Autowired
	private SequenceIdService sequenceIdService;
	
	@ModelAttribute
	public InventoryProduct get(@RequestParam(required=false) String id) {
		InventoryProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = inventoryProductService.get(id);
		}
		if (entity == null){
			entity = new InventoryProduct();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(InventoryProduct inventoryProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<InventoryProduct> page = inventoryProductService.findPage(new Page<InventoryProduct>(request, response), inventoryProduct); 
		model.addAttribute("page", page);
		return "modules/inventory/inventoryProductList";
	}

	@RequestMapping(value = "form")
	public String form(InventoryProduct inventoryProduct, Model model) {
		model.addAttribute("inventoryProduct", inventoryProduct);
		return "modules/inventory/inventoryProductForm";
	}

	@RequestMapping(value = "save")
	public String save(InventoryProduct inventoryProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, inventoryProduct)){
			return form(inventoryProduct, model);
		}
		if (StringUtils.isBlank(inventoryProduct.getLogid())){
			inventoryProduct.setWproduct_id(inventoryProduct.getCorp_id() + "-" + sequenceIdService.findNextVal(inventoryProduct.getCorp_id(), "inventoryProduct_id", 7));
		}
		inventoryProductService.save(inventoryProduct);
		addMessage(redirectAttributes, "保存站点库存信息成功");
		return "redirect:" + adminPath + "/inventory/inventoryProduct/";
	}
	
	@RequestMapping(value = "delete")
	public String delete(InventoryProduct inventoryProduct, RedirectAttributes redirectAttributes) {
		inventoryProductService.delete(inventoryProduct);
		addMessage(redirectAttributes, "删除站点库存信息成功");
		return "redirect:" + adminPath + "/inventory/inventoryProduct/";
	}
	
	@RequestMapping(value = "destroy")
	public String destroy(InventoryProduct inventoryProduct, RedirectAttributes redirectAttributes) {
		if(inventoryProduct.getInventory_renum() == 0){
			addMessage(redirectAttributes, "当前无已回收库存需要进行销毁");
			return "redirect:" + adminPath + "/inventory/inventoryProduct/";
		}
		inventoryProduct.setInventory_renum(0);
		inventoryProductService.save(inventoryProduct);
		addMessage(redirectAttributes, "销毁已回收库存成功");
		return "redirect:" + adminPath + "/inventory/inventoryProduct/";
	}
	
	/**
	 * 明细
	 * @param inventoryProduct
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(InventoryProduct inventoryProduct, Model model) {
		model.addAttribute("inventoryProduct", inventoryProduct);
		return "modules/inventory/inventoryProductDetail";
	}
	
	/**
	 * 导出
	 * @param inventoryProduct
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(InventoryProduct inventoryProduct, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "站点库存"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<InventoryProduct> page = inventoryProductService.findPage(new Page<InventoryProduct>(request, response, -1), inventoryProduct);
            List<InventoryProduct> inventoryProducts = page.getList();
    		new ExportExcel("站点库存", InventoryProduct.class).setDataList(inventoryProducts).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出站点库存失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/inventory/inventoryProduct/list?repage";
    }
	
	@RequestMapping(value="selectInventoryProduct")
	public String selectInventoryProduct(InventoryProduct inventoryProduct, HttpServletRequest request, HttpServletResponse response, Model model){
		Page<InventoryProduct> page = inventoryProductService.findPage(new Page<InventoryProduct>(request, response), inventoryProduct);
		model.addAttribute("page", page);
	    return "modules/inventory/inventoryProductTableSelect";
	}
	
	@RequestMapping(value="showInventoryProductInfo")
	public String showInventoryProductInfo(InventoryProduct inventoryProduct, HttpServletRequest request, HttpServletResponse response, Model model){
		Page<InventoryProduct> page = inventoryProductService.findPage(new Page<InventoryProduct>(request, response), inventoryProduct);
		model.addAttribute("page", page);
	    return "modules/inventory/siteProductTableSelect";
	}
	
}
