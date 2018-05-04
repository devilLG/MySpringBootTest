package com.zhilai.master.modules.product.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhilai.master.common.persistence.Page;

import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.product.entity.ProductBrand;
import com.zhilai.master.modules.product.entity.ProductInfo;
import com.zhilai.master.modules.product.service.ProductBrandService;
import com.zhilai.master.modules.product.service.ProductInfoService;
import com.zhilai.master.modules.sys.service.SequenceIdService;

@Controller
@RequestMapping(value = "${adminPath}/product/productBrand")
public class ProductBrandController extends BaseController {

	@Autowired
	private ProductBrandService productBrandService;
	@Autowired
	private SequenceIdService sequenceIdService;
	@Autowired
	private ProductInfoService productInfoService;
	
	@ModelAttribute
	public ProductBrand get(@RequestParam(required=false) String id) {
		ProductBrand entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = productBrandService.get(id);
		}
		if (entity == null){
			entity = new ProductBrand();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ProductBrand productBrand, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProductBrand> page = productBrandService.findPage(new Page<ProductBrand>(request, response), productBrand); 
		model.addAttribute("page", page);
		return "modules/product/productBrandList";
	}

	@RequestMapping(value = "form")
	public String form(ProductBrand productBrand, Model model) {
		model.addAttribute("productBrand", productBrand);
		return "modules/product/productBrandForm";
	}

	@RequestMapping(value = "save")
	public String save(ProductBrand productBrand, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, productBrand)){
			return form(productBrand, model);
		}
		if (StringUtils.isBlank(productBrand.getLogid())){
			productBrand.setBrand_id(productBrand.getCorp_id() + "-" + sequenceIdService.findNextVal(productBrand.getCorp_id(), "productBrand_id", 7));
		}
		productBrandService.save(productBrand);
		addMessage(redirectAttributes, "保存商品品牌信息成功");
		return "redirect:" + adminPath + "/product/productBrand/";
	}
	
	@RequestMapping(value = "delete")
	public String delete(ProductBrand productBrand, RedirectAttributes redirectAttributes) {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setCorp_id(productBrand.getCorp_id());
		productInfo.setBrand_id(productBrand.getBrand_id());
		List<ProductInfo> productInfoList = productInfoService.findBrandUsed(productInfo);
		if(productInfoList != null && productInfoList.size() > 0){
			addMessage(redirectAttributes, "商品品牌使用中，不允许删除");
			return "redirect:" + adminPath + "/product/productBrand/";
		}
		productBrandService.delete(productBrand);
		addMessage(redirectAttributes, "删除商品品牌信息成功");
		return "redirect:" + adminPath + "/product/productBrand/";
	}
	
	/**
	 * 明细
	 * @param productBrand
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(ProductBrand productBrand, Model model) {
		model.addAttribute("productBrand", productBrand);
		return "modules/product/productBrandDetail";
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
    public String exportFile(ProductBrand productBrand, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商品品牌"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<ProductBrand> page = productBrandService.findPage(new Page<ProductBrand>(request, response, -1), productBrand);
            List<ProductBrand> productBrands = page.getList();
    		new ExportExcel("商品品牌", ProductBrand.class).setDataList(productBrands).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商品品牌失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/product/productBrand/list?repage";
    }
	
	/**
	 * 获取品牌JSON数据。
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
		List<ProductBrand> list = productBrandService.findList(new ProductBrand());
		for (ProductBrand e : list) {
			Map<String, String> map = Maps.newLinkedHashMap();
			map.put("品牌编号", e.getBrand_id());
			map.put("品牌名称 ", e.getBrand_name());			
//			map.put("品牌公司", e.getBrandCorp_name());
			map.put("c_归属公司编号", e.getCorp_id());
			map.put("c_归属公司名称", e.getCorp_name());
			mapList.add(map);
		}
		return mapList;
	}

	@RequestMapping(value = "checkExist")
	public void checkExist(ProductBrand productBrand, Model model, HttpServletRequest req, HttpServletResponse resp)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> param = new HashMap<String, Object>();
		List<ProductBrand> productBrandList = productBrandService.findExist(productBrand);
		if(productBrandList != null && productBrandList.size() > 0){
			if(productBrandList.size() > 1){
				param.put("result", "false");
			}else if(productBrandList.get(0) != null && (productBrand.getLogid() == null) || (productBrand.getLogid() != null && !productBrand.getLogid().equals(productBrandList.get(0).getLogid()))){
				param.put("result", "false");
			}else{
				param.put("result", "true");
			}
		}else{
			param.put("result", "true");
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
	
	@RequestMapping(value = "showProductBrandInfo")
	public String showProductBrandInfo(ProductBrand productBrand, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProductBrand> page = productBrandService.findPage(new Page<ProductBrand>(request, response), productBrand); 
		model.addAttribute("page", page);
		return "modules/product/productBrandTableSelect";
	}
}
