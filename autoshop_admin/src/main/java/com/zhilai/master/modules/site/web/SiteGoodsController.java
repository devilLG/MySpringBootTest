package com.zhilai.master.modules.site.web;

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
import com.zhilai.master.modules.inventory.entity.InventoryGoods;
import com.zhilai.master.modules.inventory.service.InventoryGoodsService;
import com.zhilai.master.modules.site.entity.SiteGoods;
import com.zhilai.master.modules.site.entity.SiteState;
import com.zhilai.master.modules.site.service.SiteGoodsService;
 

/**
 * @author Administrator
 * @date 2017-12-12 下午4:36:40
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/site/siteGoods")
public class SiteGoodsController extends BaseController {

	@Autowired
	private SiteGoodsService siteGoodsService;
//	@Autowired
//	private SequenceIdService sequenceIdService;
	@Autowired
	private InventoryGoodsService inventoryGoodsService;
	
	@ModelAttribute
	public SiteGoods get(@RequestParam(required=false) String id) {
		SiteGoods entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = siteGoodsService.get(id);
		}
		if (entity == null){
			entity = new SiteGoods();
		}
		return entity;
	}
	
//	@RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = {"list", ""})
	public String list(SiteGoods siteGoods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SiteGoods> page = siteGoodsService.findPage(new Page<SiteGoods>(request, response), siteGoods); 
		model.addAttribute("page", page);
		return "modules/site/siteGoodsList";
	}


//	@RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = "form")
	public String form(SiteGoods siteGoods, Model model) {
		model.addAttribute("siteState", siteGoods);
		return "modules/site/siteGoodsForm";
	}


//	@RequiresPermissions("product:productBrand:edit")
	@RequestMapping(value = "delete")
	public String delete(SiteGoods siteGoods, RedirectAttributes redirectAttributes) {
		siteGoodsService.delete(siteGoods);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:" + adminPath + "/site/siteGoods/";
	}
	

	/**
	 * 导出
	 * @param productBrand
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SiteGoods siteGoods, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "货道状态"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
//            Page<SiteGoods> page = siteGoodsService.findPage(new Page<SiteGoods>(request, response, -1), siteGoods);
//            List<SiteGoods> siteGoodss = page.getList();
            List<SiteGoods> siteGoodss = siteGoodsService.findList(siteGoods);
    		new ExportExcel("货道状态", SiteGoods.class).setDataList(siteGoodss).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出货道状态！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/site/siteGoods/list?repage";
    }
	/**
	 * 明细
	 * @param productBrand
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("stock:productBrand:view")
	@RequestMapping(value = "detail")
	public String detail(SiteGoods siteGoods, Model model) {
		model.addAttribute("siteGoods", siteGoods);
		return "modules/site/siteGoodsDetail";
	}
	
	@RequestMapping(value="selectSiteGoods")
	public String selectSiteGoods(SiteGoods siteGoods, HttpServletRequest request, HttpServletResponse response, Model model){
		InventoryGoods inventoryGoods = new InventoryGoods();
		inventoryGoods.setSite_id(siteGoods.getSite_id());
		List<InventoryGoods> inventoryGoodsList = inventoryGoodsService.findList(inventoryGoods);
		String box_id = "";
		for(InventoryGoods inventoryGoodsP : inventoryGoodsList){
			box_id = box_id + ":" + inventoryGoodsP.getBox_id();
		}
		String[] box_ids = box_id.split(":");
		siteGoods.setBox_ids(box_ids);
		Page<SiteGoods> page = siteGoodsService.findPage(new Page<SiteGoods>(request, response), siteGoods);
		model.addAttribute("page", page);
	    return "modules/site/siteGoodsTableSelect";
	}
	
	@RequestMapping(value="showSiteGoodsInfo")
	public String showSiteGoodsInfo(SiteGoods siteGoods, HttpServletRequest request, HttpServletResponse response, Model model){
		Page<SiteGoods> page = siteGoodsService.findPage(new Page<SiteGoods>(request, response), siteGoods);
		model.addAttribute("page", page);
	    return "modules/site/siteGoodsInfoSelect";
	}

}
