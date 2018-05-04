package com.zhilai.master.modules.inventory.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSON;
import com.zhilai.master.common.persistence.Page;

import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.inventory.entity.InventoryGoods;
import com.zhilai.master.modules.inventory.entity.InventoryProduct;
import com.zhilai.master.modules.inventory.entity.WarehouseInfo;
import com.zhilai.master.modules.inventory.service.InventoryGoodsService;
import com.zhilai.master.modules.inventory.service.InventoryProductService;
import com.zhilai.master.modules.inventory.service.WarehouseInfoService;
import com.zhilai.master.modules.product.entity.ProductInfo;
import com.zhilai.master.modules.product.service.ProductInfoService;
import com.zhilai.master.modules.site.entity.SiteGoods;
import com.zhilai.master.modules.site.service.SiteGoodsService;
import com.zhilai.master.modules.standard.entity.BoxType;
import com.zhilai.master.modules.standard.service.BoxTypeService;
import com.zhilai.master.modules.sys.service.SequenceIdService;
import com.zhilai.master.modules.utils.GParameter;

@Controller
@RequestMapping(value = "${adminPath}/inventory/inventoryGoods")
public class InventoryGoodsController extends BaseController {

	@Autowired
	private InventoryGoodsService inventoryGoodsService;
	@Autowired
	private InventoryProductService inventoryProductService;
	@Autowired
	private WarehouseInfoService warehouseInfoService;
	@Autowired
	private SequenceIdService sequenceIdService;
	@Autowired
	private SiteGoodsService siteGoodsService;
	@Autowired
	private BoxTypeService boxTypeService;
	@Autowired
	private ProductInfoService productInfoService;
	
	@ModelAttribute
	public InventoryGoods get(@RequestParam(required=false) String id) {
		InventoryGoods entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = inventoryGoodsService.get(id);
		}
		if (entity == null){
			entity = new InventoryGoods();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(InventoryGoods inventoryGoods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<InventoryGoods> page = inventoryGoodsService.findPage(new Page<InventoryGoods>(request, response), inventoryGoods); 
		model.addAttribute("page", page);
		return "modules/inventory/inventoryGoodsList";
	}

	@RequestMapping(value = "form")
	public String form(InventoryGoods inventoryGoods, Model model) {
		model.addAttribute("inventoryGoods", inventoryGoods);
		return "modules/inventory/inventoryGoodsForm";
	}

	@RequestMapping(value = "save")
	public String save(InventoryGoods inventoryGoods, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, inventoryGoods)){
			return form(inventoryGoods, model);
		}
		//增加
		if (StringUtils.isBlank(inventoryGoods.getLogid())){
			inventoryGoods.setInvengoods_id(inventoryGoods.getCorp_id() + "-" + sequenceIdService.findNextVal(inventoryGoods.getCorp_id(), "inventoryGoods_id", 7));
			inventoryGoods.setInventory_state(GParameter.inventoryState_none);
			inventoryGoods.setState_time(DateUtil.getNow());
			inventoryGoods.setIs_overdue(GParameter.isOverdue_no);
			inventoryGoods.setCreate_time(DateUtil.getNow());
			inventoryGoodsService.save(inventoryGoods);
			//判断商品库存是否存在，若不存在则增加
			InventoryProduct inventoryProduct = new InventoryProduct();
			inventoryProduct.setSite_id(inventoryGoods.getSite_id());
			inventoryProduct.setProduct_id(inventoryGoods.getProduct_id());
			InventoryProduct inventoryProductR = inventoryProductService.findExist(inventoryProduct);
			if(inventoryProductR == null){
				inventoryProduct.setWproduct_id(inventoryGoods.getCorp_id() + "-" + sequenceIdService.findNextVal(inventoryGoods.getCorp_id(), "inventoryProduct_id", 7));
				inventoryProduct.setCorp_id(inventoryGoods.getCorp_id());
				inventoryProduct.setCorp_name(inventoryGoods.getCorp_name());
				inventoryProduct.setSite_name(inventoryGoods.getSite_name());
				WarehouseInfo warehouseInfoP = new WarehouseInfo();
				warehouseInfoP.setCorp_id(inventoryGoods.getCorp_id());
				List<WarehouseInfo> warehouseInfoList = warehouseInfoService.findList(warehouseInfoP);
				if(warehouseInfoList != null && warehouseInfoList.size() > 0){
					inventoryProduct.setWarehouse_id(warehouseInfoList.get(0).getWarehouse_id());
					inventoryProduct.setWarehouse_name(warehouseInfoList.get(0).getWarehouse_name());
					inventoryProduct.setWarehouse_code(warehouseInfoList.get(0).getWarehouse_code());
				}
				inventoryProduct.setProduct_name(inventoryGoods.getProduct_name());
				inventoryProduct.setProductType_id(inventoryGoods.getProductType_id());
				inventoryProduct.setProductType_name(inventoryGoods.getProductType_name());
				inventoryProduct.setBar_code(inventoryGoods.getBar_code());
				inventoryProductService.save(inventoryProduct);
			}
		}else{
			//修改
			InventoryGoods inventoryGoodsR = inventoryGoodsService.get(inventoryGoods);
			//若未修改货道商品
			if(inventoryGoodsR != null && inventoryGoods.getProduct_id().equals(inventoryGoodsR.getProduct_id())){
				inventoryGoods.setInventory_total(inventoryGoodsR.getInventory_total());
				inventoryGoods.setInventory_num(inventoryGoodsR.getInventory_num());
				inventoryGoods.setInventory_transit(inventoryGoodsR.getInventory_transit());
				inventoryGoods.setOverdue_num(inventoryGoodsR.getOverdue_num());
				inventoryGoods.setInventory_recovery(inventoryGoodsR.getInventory_recovery());
				inventoryGoods.setIs_overdue(inventoryGoodsR.getIs_overdue());
				inventoryGoods.setInventory_state(inventoryGoodsR.getInventory_state());
				inventoryGoods.setState_time(inventoryGoodsR.getState_time());
				inventoryGoods.setCreate_time(inventoryGoodsR.getCreate_time());
				inventoryGoods.setOverdue_time(inventoryGoodsR.getOverdue_time());
				inventoryGoodsService.save(inventoryGoods);
			}else{
				//若修改货道商品
				inventoryGoods.setInventory_total(0);
				inventoryGoods.setInventory_num(0);
				inventoryGoods.setInventory_transit(0);
				inventoryGoods.setOverdue_num(0);
				inventoryGoods.setInventory_recovery(0);
				inventoryGoods.setCreate_time(DateUtil.getNow());
				inventoryGoods.setOverdue_time("");
				inventoryGoodsService.save(inventoryGoods);
				//判断修改之前的商品在该站点是否还存在，若不存在则删除商品库存
				InventoryProduct inventoryProduct = new InventoryProduct();
				inventoryProduct.setSite_id(inventoryGoods.getSite_id());
				InventoryGoods inventoryGoodsP = new InventoryGoods();
				inventoryGoodsP.setSite_id(inventoryGoods.getSite_id());
				inventoryGoodsP.setProduct_id(inventoryGoodsR.getProduct_id());
				List<InventoryGoods> inventoryGoodsList = inventoryGoodsService.findList(inventoryGoodsP);
				if(inventoryGoodsList == null || inventoryGoodsList.size() <= 0){
					inventoryProduct.setProduct_id(inventoryGoodsR.getProduct_id());
					InventoryProduct inventoryProductR = inventoryProductService.findExist(inventoryProduct);
					inventoryProductService.delete(inventoryProductR);
				}
				//判断修改后的商品库存是否存在，若不存在则增加
				inventoryProduct.setProduct_id(inventoryGoods.getProduct_id());
				InventoryProduct inventoryProductR = inventoryProductService.findExist(inventoryProduct);
				if(inventoryProductR == null){
					inventoryProduct.setWproduct_id(inventoryGoods.getCorp_id() + "-" + sequenceIdService.findNextVal(inventoryGoods.getCorp_id(), "inventoryProduct_id", 7));
					inventoryProduct.setCorp_id(inventoryGoods.getCorp_id());
					inventoryProduct.setCorp_name(inventoryGoods.getCorp_name());
					inventoryProduct.setSite_name(inventoryGoods.getSite_name());
					WarehouseInfo warehouseInfoP = new WarehouseInfo();
					warehouseInfoP.setCorp_id(inventoryGoods.getCorp_id());
					List<WarehouseInfo> warehouseInfoList = warehouseInfoService.findList(warehouseInfoP);
					if(warehouseInfoList != null && warehouseInfoList.size() > 0){
						inventoryProduct.setWarehouse_id(warehouseInfoList.get(0).getWarehouse_id());
						inventoryProduct.setWarehouse_name(warehouseInfoList.get(0).getWarehouse_name());
						inventoryProduct.setWarehouse_code(warehouseInfoList.get(0).getWarehouse_code());
					}
					inventoryProduct.setProduct_name(inventoryGoods.getProduct_name());
					inventoryProduct.setProductType_id(inventoryGoods.getProductType_id());
					inventoryProduct.setProductType_name(inventoryGoods.getProductType_name());
					inventoryProduct.setBar_code(inventoryGoods.getBar_code());
					inventoryProductService.save(inventoryProduct);
				}
			}
		}
		addMessage(redirectAttributes, "保存货道库存信息成功");
		return "redirect:" + adminPath + "/inventory/inventoryGoods/";
	}
	
	@RequestMapping(value = "delete")
	public String delete(InventoryGoods inventoryGoods, RedirectAttributes redirectAttributes) {
		inventoryGoodsService.delete(inventoryGoods);
		//判断删除的商品在该站点是否还存在，若不存在则删除商品库存
		InventoryGoods inventoryGoodsP = new InventoryGoods();
		inventoryGoodsP.setSite_id(inventoryGoods.getSite_id());
		inventoryGoodsP.setProduct_id(inventoryGoods.getProduct_id());
		List<InventoryGoods> inventoryGoodsList = inventoryGoodsService.findList(inventoryGoodsP);
		if(inventoryGoodsList == null || inventoryGoodsList.size() <= 0){
			InventoryProduct inventoryProduct = new InventoryProduct();
			inventoryProduct.setSite_id(inventoryGoods.getSite_id());
			inventoryProduct.setProduct_id(inventoryGoods.getProduct_id());
			InventoryProduct inventoryProductR = inventoryProductService.findExist(inventoryProduct);
			inventoryProductService.delete(inventoryProductR);
		}
		addMessage(redirectAttributes, "删除货道库存信息成功");
		return "redirect:" + adminPath + "/inventory/inventoryGoods/";
	}
	
	/**
	 * 明细
	 * @param inventoryGoods
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(InventoryGoods inventoryGoods, Model model) {
		model.addAttribute("inventoryGoods", inventoryGoods);
		return "modules/inventory/inventoryGoodsDetail";
	}
	
	/**
	 * 导出
	 * @param inventoryGoods
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(InventoryGoods inventoryGoods, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "货道库存"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<InventoryGoods> page = inventoryGoodsService.findPage(new Page<InventoryGoods>(request, response, -1), inventoryGoods);
            List<InventoryGoods> inventoryGoodss = page.getList();
    		new ExportExcel("货道库存", InventoryGoods.class).setDataList(inventoryGoodss).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出货道库存失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/inventory/inventoryGoods/list?repage";
    }
	
	@RequestMapping(value = "checkExist")
	public void checkExist(InventoryGoods inventoryGoods, Model model, HttpServletRequest req, HttpServletResponse resp)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("result", "true");
		//检查商品是否能够放入选择货道
		double length = 0;
		double width = 0;
		double height = 0;
		String boxIds[] = inventoryGoods.getBox_id().split(":");
		for(int i=0 ; i<boxIds.length ; i++){
			SiteGoods siteGoodsP = new SiteGoods();
			siteGoodsP.setSite_id(inventoryGoods.getSite_id());
			siteGoodsP.setBox_id(boxIds[i]);
			SiteGoods siteGoods = siteGoodsService.findOne(siteGoodsP);
			if(siteGoods != null){
				BoxType boxType = boxTypeService.findOneByTypeName(siteGoods.getBox_type());
				if(boxType != null){
					length = boxType.getSize_length();
					width = width + boxType.getSize_width();
					height = boxType.getSize_height();
				}
			}
		}
		ProductInfo productInfo = productInfoService.findOneByProductId(inventoryGoods.getProduct_id());
		if(Double.parseDouble(productInfo.getPro_length()) > length){
			param.put("result", "false");
		}else if(Double.parseDouble(productInfo.getPro_width()) > width){
			param.put("result", "false");
		}else if(Double.parseDouble(productInfo.getPro_height()) > height){
			param.put("result", "false");
		}else {
			int inventory_max = (int) (length/Double.parseDouble(productInfo.getPro_length()));
			param.put("inventory_max", inventory_max);
		}
//		String boxIds[] = inventoryGoods.getBox_id().split(":");
//		for(int i=0 ; i<boxIds.length ; i++){
//			InventoryGoods inventoryGoodsP = new InventoryGoods();
//			inventoryGoodsP.setSite_id(inventoryGoods.getSite_id());
//			inventoryGoodsP.setBox_id(boxIds[i]);
//			List<InventoryGoods> inventoryGoodsList = inventoryGoodsService.findExist(inventoryGoodsP);
//			if(inventoryGoodsList != null && inventoryGoodsList.size() > 0){
//				param.put("result", "false");
//				param.put("boxId", boxIds[i]);
//				break;
//			}
//		}
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setHeader("Cache-Control", "no-cache");
		try {
			resp.getWriter().write(JSON.toJSONString(param));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="showInventoryGoodsInfo")
	public String showInventoryGoodsInfo(InventoryGoods inventoryGoods, HttpServletRequest request, HttpServletResponse response, Model model){
		Page<InventoryGoods> page = inventoryGoodsService.findPage(new Page<InventoryGoods>(request, response), inventoryGoods);
		model.addAttribute("page", page);
	    return "modules/inventory/inventoryGoodsTableSelect";
	}
	
	@RequestMapping(value = "findGoodsByProduct")
	public void findGoodsByProduct(InventoryGoods inventoryGoods, Model model, HttpServletRequest req, HttpServletResponse resp)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("result", "true");
		String boxId = "";
		List<InventoryGoods> inventoryGoodsList = inventoryGoodsService.findList(inventoryGoods);
		if(inventoryGoodsList != null && inventoryGoodsList.size() > 0){
			for(InventoryGoods inventoryGoodsP : inventoryGoodsList){
				boxId = boxId + ";" + inventoryGoodsP.getBox_id();
			}
			
			param.put("boxId", boxId);
		}else{
			param.put("result", "false");
		}
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setHeader("Cache-Control", "no-cache");
		try {
			resp.getWriter().write(JSON.toJSONString(param));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
