package com.zhilai.master.modules.inventory.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.putils.utils.DateUtil;
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
import com.zhilai.master.modules.inventory.entity.ClearProduct;
import com.zhilai.master.modules.inventory.service.ClearProductService;

@Controller
@RequestMapping(value = "${adminPath}/inventory/clearProduct")
public class ClearProductController extends BaseController {

	@Autowired
	private ClearProductService clearProductService;
	@ModelAttribute
	public ClearProduct get(@RequestParam(required=false) String id) {
		ClearProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = clearProductService.get(id);
		}
		if (entity == null){
			entity = new ClearProduct();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ClearProduct clearProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==clearProduct.getBeginTime()||"".equals(clearProduct.getBeginTime())) {
			clearProduct.setBeginTime(DateUtil.getDate());
		}
		if (null == clearProduct.getEndTime() || "".equals(clearProduct.getEndTime())) {
			clearProduct.setEndTime(DateUtil.getDate());
		}
		Page<ClearProduct> page = clearProductService.findPage(new Page<ClearProduct>(request, response), clearProduct); 
		model.addAttribute("page", page);
		return "modules/inventory/clearProductList";
	}

	
	/**
	 * 明细
	 * @param clearProduct
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(ClearProduct clearProduct, Model model) {
		model.addAttribute("clearProduct", clearProduct);
		return "modules/inventory/clearProductDetail";
	}
	
	/**
	 * 导出
	 * @param clearProduct
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ClearProduct clearProduct, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "货道清除"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<ClearProduct> page = clearProductService.findPage(new Page<ClearProduct>(request, response, -1), clearProduct);
            List<ClearProduct> clearProducts = page.getList();
    		new ExportExcel("货道清除", ClearProduct.class).setDataList(clearProducts).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出货道清除失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/inventory/clearProduct/list?repage";
    }
	
}
