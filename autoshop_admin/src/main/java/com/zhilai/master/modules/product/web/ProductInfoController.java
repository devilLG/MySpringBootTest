package com.zhilai.master.modules.product.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhilai.master.common.persistence.Page;

import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.inventory.entity.InventoryProduct;
import com.zhilai.master.modules.inventory.service.InventoryProductService;
import com.zhilai.master.modules.product.entity.ProductBrand;
import com.zhilai.master.modules.product.entity.ProductClassify;
import com.zhilai.master.modules.product.entity.ProductInfo;
import com.zhilai.master.modules.product.entity.ProductImage;
import com.zhilai.master.modules.product.service.ProductBrandService;
import com.zhilai.master.modules.product.service.ProductClassifyService;
import com.zhilai.master.modules.product.service.ProductImageService;
import com.zhilai.master.modules.product.service.ProductInfoService;
import com.zhilai.master.modules.sys.service.SequenceIdService;
import com.zhilai.master.modules.sys.utils.DictUtils;

@Controller
@RequestMapping(value = "${adminPath}/product/productInfo")
public class ProductInfoController extends BaseController {

	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private SequenceIdService sequenceIdService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ProductClassifyService productClassifyService;
	@Autowired
	private InventoryProductService inventoryProductService;
	@Autowired
	private ProductBrandService productBrandService;
	
	@ModelAttribute
	public ProductInfo get(@RequestParam(required=false) String id) {
		ProductInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = productInfoService.get(id);
		}
		if (entity == null){
			entity = new ProductInfo();
		}
		return entity;
	}
	
	@RequestMapping(value = {"index"})
	public String index(ProductInfo productInfo, Model model) {
		return "modules/product/productInfoIndex";
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ProductInfo productInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(productInfo.getProductType_id() != null){
			ProductClassify productClassify = new ProductClassify();
			productClassify.setClassify_id(productInfo.getProductType_id());
			List<ProductClassify> productClassifyList = productClassifyService.findChildByParentId(productClassify);
			String classify_id = productInfo.getProductType_id();
			for(ProductClassify productClassifyP : productClassifyList){
				classify_id = classify_id + "," + productClassifyP.getClassify_id();
			}
			String[] classify_ids = classify_id.split(",");
			productInfo.setProductType_ids(classify_ids);
		}
		Page<ProductInfo> page = productInfoService.findPage(new Page<ProductInfo>(request, response), productInfo); 
		model.addAttribute("page", page);
		return "modules/product/productInfoList";
	}

	@RequestMapping(value = "form")
	public String form(ProductInfo productInfo, Model model) {
		model.addAttribute("productInfo", productInfo);
		return "modules/product/productInfoForm";
	}

	@RequestMapping(value = "save")
	public String save(ProductInfo productInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, productInfo)){
			return form(productInfo, model);
		}
		ProductBrand productBrand = productBrandService.findOne(productInfo.getBrand_id());
		if(null != productBrand){
			productInfo.setCorp_id(productBrand.getCorp_id());
			productInfo.setCorp_name(productBrand.getCorp_name());
		}
		if (StringUtils.isBlank(productInfo.getLogid())){
			productInfo.setProduct_id(productInfo.getCorp_id() + "-" + sequenceIdService.findNextVal(productInfo.getCorp_id(), "productInfo_id", 7));
		}
		String metering_name = DictUtils.getDictLabel(productInfo.getMetering_id(), "metering_id", "");
		productInfo.setMetering_name(metering_name);
		productInfoService.save(productInfo);
		addMessage(redirectAttributes, "保存商品信息信息成功");
		return "redirect:" + adminPath + "/product/productInfo/list";
	}
	
	@RequestMapping(value = "delete")
	public String delete(ProductInfo productInfo, RedirectAttributes redirectAttributes) {
		InventoryProduct inventoryProduct = new InventoryProduct();
		inventoryProduct.setCorp_id(productInfo.getCorp_id());
		inventoryProduct.setProduct_id(productInfo.getProduct_id());
		List<InventoryProduct> inventoryProductList = inventoryProductService.findProductByCorpId(inventoryProduct);
		if(inventoryProductList != null && inventoryProductList.size() > 0){
			addMessage(redirectAttributes, "该商品信息使用中，不允许删除");
			return "redirect:" + adminPath + "/product/productInfo/list";
		}
		productInfoService.delete(productInfo);
		ProductImage productImage = new ProductImage();
		productImage.setProduct_id(productInfo.getProduct_id());
		productImageService.delete(productImage);
		addMessage(redirectAttributes, "删除商品信息信息成功");
		return "redirect:" + adminPath + "/product/productInfo/list";
	}
	
	/**
	 * 明细
	 * @param productInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(ProductInfo productInfo, Model model) {
		model.addAttribute("productInfo", productInfo);
		return "modules/product/productInfoDetail";
	}
	
	/**
	 * 导出
	 * @param productInfo
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ProductInfo productInfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商品信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<ProductInfo> page = productInfoService.findPage(new Page<ProductInfo>(request, response, -1), productInfo);
            List<ProductInfo> productInfos = page.getList();
    		new ExportExcel("商品信息", ProductInfo.class).setDataList(productInfos).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商品信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/product/productInfo/list?repage";
    }
	
	@RequestMapping(value = "checkExist")
	public void checkExist(ProductInfo productInfo, Model model, HttpServletRequest req, HttpServletResponse resp)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> param = new HashMap<String, Object>();
		List<ProductInfo> productInfoList = productInfoService.findExist(productInfo);
		if(productInfoList != null && productInfoList.size() > 0){
			if(productInfoList.size() > 1){
				param.put("result", "false");
			}else if(productInfoList.get(0) != null && (productInfo.getLogid() == null) || (productInfo.getLogid() != null && !productInfo.getLogid().equals(productInfoList.get(0).getLogid()))){
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
	
	/**
	 * 图片管理
	 * @param productInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "image")
	public String image(ProductInfo productInfo, Model model) {
		ProductImage productImage = new ProductImage();
		productImage.setProduct_id(productInfo.getProduct_id());
		productImage.setCorp_id(productInfo.getCorp_id());
		List<ProductImage> productImageList = productImageService.findList(productImage);
		model.addAttribute("productInfo", productInfo);
		model.addAttribute("productImageList", productImageList);
		return "modules/product/productImageDetail";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "uploadImage", method=RequestMethod.POST)
	public String uploadImage(HttpServletRequest req, HttpServletResponse resp, ProductInfo productInfo, RedirectAttributes redirectAttributes) throws IllegalAccessException, InvocationTargetException, IOException, FileUploadException {
		req.setCharacterEncoding("UTF-8");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		List<MultipartFile> fileList = multipartRequest.getFiles("file");
		String msg = null;
		MultipartFile fileByte=null;
		//获取上传信息
		for (MultipartFile file : fileList) {
			if (!((MultipartFile) file).isEmpty()) { // 判断是文件还是文本信息
				fileByte=(MultipartFile) file;
				msg = file.getOriginalFilename();
				if (null != msg && !"".equals(msg)) { // 判断是否选择了文件
					if (file.getSize() > 15 * 1024 * 1024) {
						resp.setContentType("text/html");
						resp.getWriter().println("{\"msg\":\"容量应小于15M\",\"code\":\"2\"}");
						addMessage(redirectAttributes, "图片大小不能超过15M");
						return "redirect:" + adminPath + "/product/productInfo/image?id="+productInfo.getLogid();
					}
				}
			}
		}
		String[] chanelType = productInfo.getChanel_type().split(",");
		for(int i=0 ; i<chanelType.length ; i++){
			String chanel_type = chanelType[i];
			//保存文件  托管公司/商品编号/图片类型/xxx.jpg
			String dirPath="picture"+"/"+"product"+"/"+productInfo.getCorp_id()+"/"+productInfo.getProduct_id()+"/"+chanel_type;
			String filePath="picture"+"/"+"product"+"/"+productInfo.getCorp_id()+"/"+productInfo.getProduct_id()+"/"+chanel_type+"/"+productInfo.getProduct_id()+"_"+chanel_type+msg.substring(msg.indexOf("."));
			String dirRealPath=req.getRealPath("/");
			File dir=new File(dirRealPath+dirPath);
			if(!dir.exists()){
				dir.mkdirs();
			}
			File file=new File(dirRealPath+filePath);
			byte[] fileBytes=((MultipartFile) fileByte).getBytes();
			FileOutputStream fop = new FileOutputStream(file);
			if(!file.exists()){
				file.createNewFile();
			}
			fop.write(fileBytes);
			fop.flush();
			fop.close();
			//更新商品信息
			String localUrl = DictUtils.getDictValue("productImageUrl", "productImage", "http://127.0.0.1:8880/autoshop_admin/");
			ProductImage productImage = new ProductImage();
			productImage.setCorp_id(productInfo.getCorp_id());
			productImage.setCorp_name(productInfo.getCorp_name());
			productImage.setProduct_id(productInfo.getProduct_id());
			productImage.setProduct_name(productInfo.getProduct_name());
			productImage.setChanel_type(chanel_type);
			productImage.setMime_type(msg.substring(msg.indexOf(".")));
			productImage.setImage_url(localUrl+filePath);
			BufferedImage bi = ImageIO.read(file);
			int width = bi.getWidth();
			int height = bi.getHeight();
			productImage.setImage_width(width + "");
			productImage.setImage_height(height + "");
			productImage.setImage_px(width + "×" + height);
			ProductImage productImageR = productImageService.findExist(productImage);
			if(productImageR == null){
				productImage.setImage_id(productInfo.getCorp_id() + "-" + sequenceIdService.findNextVal(productInfo.getCorp_id(), "productImage_id", 7));
			}else{
				productImage.setLogid(productImageR.getLogid());
			}
			productImageService.save(productImage);
		}
		addMessage(redirectAttributes, "上传商品图片信息成功");
		return "redirect:" + adminPath + "/product/productInfo/image?id="+productInfo.getLogid();
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
		List<ProductInfo> list = productInfoService.findList(new ProductInfo());
		for (ProductInfo e : list) {
			Map<String, String> map = Maps.newLinkedHashMap();
			map.put("商品编码", e.getProduct_id());
			map.put("商品名称 ", e.getProduct_name());			
			map.put("商品条码", e.getBar_code());	
			map.put("商品标题", e.getProduct_title());	
			map.put("商品标价（元）", e.getNormal_price());	
			map.put("商品介绍地址", e.getDetail_url());	
			mapList.add(map);
		}
		return mapList;
	}
	
	@RequestMapping(value = "showProductInfo")
	public String showProductInfo(ProductInfo productInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProductInfo> page = productInfoService.findPage(new Page<ProductInfo>(request, response), productInfo); 
		model.addAttribute("page", page);
		return "modules/product/productInfoTableSelect";
	}


}
