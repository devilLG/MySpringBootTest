package com.zhilai.master.modules.product.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.product.entity.ProductClassify;
import com.zhilai.master.modules.product.entity.ProductInfo;
import com.zhilai.master.modules.product.service.ProductClassifyService;
import com.zhilai.master.modules.product.service.ProductInfoService;
import com.zhilai.master.modules.sys.service.SequenceIdService;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

@Controller
@RequestMapping(value = "${adminPath}/product/productClassify")
public class ProductClassifyController extends BaseController {

	@Autowired
	private ProductClassifyService productClassifyService;
	@Autowired
	private SequenceIdService sequenceIdService;
	@Autowired
	private ProductInfoService productInfoService;
	
	@ModelAttribute
	public ProductClassify get(@RequestParam(required=false) String classify_id) {
		if (StringUtils.isNotBlank(classify_id)){
			return productClassifyService.get(classify_id);
		}else{
			return new ProductClassify();
		}
	}
	
	@RequestMapping(value = {"list",""})
	public String list(ProductClassify productClassify, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询所有并排序展现成树状
		List<ProductClassify> list = Lists.newArrayList();
		List<ProductClassify> sourcelist = productClassifyService.findAll();
		productClassify.sortList(list, sourcelist, ProductClassify.getRootId(), true);
		model.addAttribute("ProductClassifyList", list);
		return "modules/product/productClassifyList";
	}
	
	//添加，修改分类页面
	@RequestMapping(value = "form")
	public String form(ProductClassify productClassify, Model model) {
		if (productClassify.getParent_id() == null || productClassify.getParent_id().equals("")) {
			productClassify.setParent_id(ProductClassify.getRootId());
		}
		// 获取排序号，最末节点排序号+30
		if (StringUtils.isBlank(productClassify.getLogid())){
			List<ProductClassify> list = Lists.newArrayList();
			List<ProductClassify> sourcelist = productClassifyService.findAll();
			productClassify.sortList(list, sourcelist, productClassify.getParent_id(), false);
			if (list.size() > 0){
				productClassify.setSort_by(list.get(list.size()-1).getSort_by() + 30);
			}
		}
		productClassify.setParentName(productClassifyService.get(productClassify.getParent_id()).getClassify_name());
		productClassify.setLevel(productClassifyService.get(productClassify.getParent_id()).getLevel() + 1);
		model.addAttribute("productClassify", productClassify);
		return "modules/product/productClassifyForm";
	}
	
	//保存分类
	@RequestMapping(value = "save")
	public String save(ProductClassify productClassify, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, productClassify)){
			return form(productClassify, model);
		}
		if (StringUtils.isBlank(productClassify.getLogid())){
			productClassify.setClassify_id(productClassify.getCorp_id() + "-" + sequenceIdService.findNextVal(productClassify.getCorp_id(), "productClassify_id", 7));
		}
		ProductClassify vo = productClassifyService.get(productClassify.getParent_id());//父级节点
		productClassify.setParent_ids((vo.getParent_ids() == null ? "" : vo.getParent_ids()) + vo.getClassify_id() +",");
		productClassifyService.save(productClassify);
		addMessage(redirectAttributes, "保存商品分类" + productClassify.getClassify_name() + "成功");
		return "redirect:" + adminPath + "/product/productClassify/";
	}
	
	//删除分类
	@RequestMapping(value = "delete")
	public String delete(ProductClassify productClassify, RedirectAttributes redirectAttributes) {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setCorp_id(productClassify.getCorp_id());
		productInfo.setProductType_id(productClassify.getClassify_id());
		List<ProductInfo> productInfoList = productInfoService.findClassifyUsed(productInfo);
		if(productInfoList != null && productInfoList.size() > 0){
			addMessage(redirectAttributes, "商品分类使用中，不允许删除");
			return "redirect:" + adminPath + "/product/productClassify/";
		}
		productClassifyService.delete(productClassify);
		addMessage(redirectAttributes, "删除商品分类成功");
		return "redirect:" + adminPath + "/product/productClassify/";
	}
	
	/**
	 * 图片管理
	 * @param productClassify
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "image")
	public String image(ProductClassify productClassify, Model model) {
		model.addAttribute("productClassify", productClassify);
		return "modules/product/productClassifyImageDetail";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "uploadImage", method=RequestMethod.POST)
	public String uploadImage(HttpServletRequest req, HttpServletResponse resp, ProductClassify productClassify, RedirectAttributes redirectAttributes) throws IllegalAccessException, InvocationTargetException, IOException, FileUploadException {
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
						return "redirect:" + adminPath + "/product/productClassify/image?classify_id="+productClassify.getClassify_id();
					}
				}
			}
		}
		String[] chanelType = productClassify.getChanel_type().split(",");
		for(int i=0 ; i<chanelType.length ; i++){
			String chanel_type = chanelType[i];
			//保存文件  托管公司/商品编号/图片类型/xxx.jpg
			String dirPath="picture"+"/"+"productClassify"+"/"+productClassify.getCorp_id()+"/"+productClassify.getClassify_id()+"/"+chanel_type;
			String filePath="picture"+"/"+"productClassify"+"/"+productClassify.getCorp_id()+"/"+productClassify.getClassify_id()+"/"+chanel_type+"/"+productClassify.getClassify_id()+"_"+chanel_type+msg.substring(msg.indexOf("."));
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
			//更新商品分类信息
			String localUrl = DictUtils.getDictValue("productImageUrl", "productImage", "http://127.0.0.1:8880/autoshop_admin/");
			if(chanel_type.equals(GParameter.chanelType_terminal)){
				productClassify.setTerminal_image(localUrl+filePath);
			}else if(chanel_type.equals(GParameter.chanelType_wechart)){
				productClassify.setWechart_image(localUrl+filePath);
			}else if(chanel_type.equals(GParameter.chanelType_android)){
				productClassify.setAndroid_image(localUrl+filePath);
			}else if(chanel_type.equals(GParameter.chanelType_ios)){
				productClassify.setIos_image(localUrl+filePath);
			}
			productClassifyService.save(productClassify);
		}
		addMessage(redirectAttributes, "上传商品分类图片信息成功");
		return "redirect:" + adminPath + "/product/productClassify/image?classify_id="+productClassify.getClassify_id();
	}
	
	/**
	 * 批量修改分类排序
	 */
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts, RedirectAttributes redirectAttributes) {
    	for (int i = 0; i < ids.length; i++) {
    		ProductClassify productClassify = new ProductClassify();
    		productClassify.setLogid(ids[i]);
    		productClassify.setSort_by(sorts[i]);
    		productClassifyService.updateSort(productClassify);
    	}
    	addMessage(redirectAttributes, "保存分类排序成功!");
		return "redirect:" + adminPath + "/product/productClassify/";
	}

	//商品分类树模型
	@RequestMapping(value = {"treeModel"})
	public String treeModel(ProductClassify productClassify, Model model) {
		return "modules/product/productClassifyTree";
	}
	
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ProductClassify> list = productClassifyService.findAll();
		for (int i=0; i<list.size(); i++){
			ProductClassify e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getClassify_id());
			map.put("pId", e.getParent_id());
			map.put("name", e.getClassify_name());
			map.put("code", e.getCorp_id());
			map.put("description", e.getClassify_name()+"("+e.getCorp_name()+")");
			mapList.add(map);
		}
		return mapList;
	}
	@ResponseBody
	@RequestMapping(value = "treeData1")
	public List<Map<String, Object>> treeData1(HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ProductClassify> list = productClassifyService.findAll();
		for (int i=0; i<list.size(); i++){
			ProductClassify e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getClassify_id());
			map.put("name", e.getClassify_name());
			mapList.add(map);
		}
		return mapList;
	}
	
	@RequestMapping(value = "checkExist")
	public void checkExist(ProductClassify productClassify, Model model, HttpServletRequest req, HttpServletResponse resp)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> param = new HashMap<String, Object>();
		List<ProductClassify> productClassifyList = productClassifyService.findExist(productClassify);
		if(productClassifyList != null && productClassifyList.size() > 0){
			if(productClassifyList.size() > 1){
				param.put("result", "false");
			}else if(productClassifyList.get(0) != null && (productClassify.getClassify_id() == null) || (productClassify.getClassify_id() != null && !productClassify.getClassify_id().equals(productClassifyList.get(0).getClassify_id()))){
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
