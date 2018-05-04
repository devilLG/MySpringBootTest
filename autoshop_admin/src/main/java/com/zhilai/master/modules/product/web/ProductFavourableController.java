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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.product.entity.ProductFavourable;
import com.zhilai.master.modules.product.service.ProductFavourableService;
import com.zhilai.master.modules.sys.service.SequenceIdService;
import com.zhilai.master.modules.utils.GParameter;

@Controller
@RequestMapping(value = "${adminPath}/product/productFavourable")
public class ProductFavourableController extends BaseController {

	@Autowired
	private ProductFavourableService productFavourableService;
	@Autowired
	private SequenceIdService sequenceIdService;
	
	@ModelAttribute
	public ProductFavourable get(@RequestParam(required=false) String id) {
		ProductFavourable entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = productFavourableService.get(id);
		}
		if (entity == null){
			entity = new ProductFavourable();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ProductFavourable productFavourable, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProductFavourable> page = productFavourableService.findPage(new Page<ProductFavourable>(request, response), productFavourable); 
		model.addAttribute("page", page);
		return "modules/product/productFavourableList";
	}

	@RequestMapping(value = "form")
	public String form(ProductFavourable productFavourable, Model model) {
		model.addAttribute("productFavourable", productFavourable);
		return "modules/product/productFavourableForm";
	}

	@RequestMapping(value = "save")
	public String save(ProductFavourable productFavourable, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, productFavourable)){
			return form(productFavourable, model);
		}
		if (StringUtils.isBlank(productFavourable.getLogid())){
			productFavourable.setFavourable_id(productFavourable.getCorp_id() + "-" + sequenceIdService.findNextVal(productFavourable.getCorp_id(), "productFavourable_id", 7));
			if(productFavourable.getFavourable_type().equals(GParameter.favourableType_all)){
				productFavourable.setSite_id("全网");
			}
		}
		productFavourable.setCur_state(GParameter.favourableState_wait);
		productFavourableService.save(productFavourable);
		addMessage(redirectAttributes, "保存商品优惠信息成功");
		return "redirect:" + adminPath + "/product/productFavourable/";
	}
	
	@RequestMapping(value = "delete")
	public String delete(ProductFavourable productFavourable, RedirectAttributes redirectAttributes) {
		if(productFavourable.getCur_state().equals(GParameter.favourableState_executing)){
			addMessage(redirectAttributes, "商品优惠正在执行，不能删除");
			return "redirect:" + adminPath + "/product/productFavourable/";
		}
		productFavourableService.delete(productFavourable);
		addMessage(redirectAttributes, "删除商品优惠信息成功");
		return "redirect:" + adminPath + "/product/productFavourable/";
	}
	
	/**
	 * 明细
	 * @param productFavourable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(ProductFavourable productFavourable, Model model) {
		model.addAttribute("productFavourable", productFavourable);
		return "modules/product/productFavourableDetail";
	}
	
	/**
	 * 导出
	 * @param productFavourable
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ProductFavourable productFavourable, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商品优惠"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<ProductFavourable> page = productFavourableService.findPage(new Page<ProductFavourable>(request, response, -1), productFavourable);
            List<ProductFavourable> productFavourables = page.getList();
    		new ExportExcel("商品优惠", ProductFavourable.class).setDataList(productFavourables).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商品优惠失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/product/productFavourable/list?repage";
    }
	
	@RequestMapping(value = "checkExist")
	public void checkExist(ProductFavourable productFavourable, Model model, HttpServletRequest req, HttpServletResponse resp)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> param = new HashMap<String, Object>();
		List<ProductFavourable> productFavourableList = productFavourableService.findExist(productFavourable);
		if(productFavourableList != null && productFavourableList.size() > 0){
			if(productFavourableList.size() > 1){
				param.put("result", "false");
			}else if(productFavourableList.get(0) != null && (productFavourable.getLogid() == null) || (productFavourable.getLogid() != null && !productFavourable.getLogid().equals(productFavourableList.get(0).getLogid()))){
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
	
}
