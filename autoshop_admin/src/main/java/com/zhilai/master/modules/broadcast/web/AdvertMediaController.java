package com.zhilai.master.modules.broadcast.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.broadcast.entity.AdvertArea;
import com.zhilai.master.modules.broadcast.entity.AdvertAreaMedia;
import com.zhilai.master.modules.broadcast.entity.AdvertMedia;
import com.zhilai.master.modules.broadcast.service.AdvertAreaMediaService;
import com.zhilai.master.modules.broadcast.service.AdvertAreaService;
import com.zhilai.master.modules.broadcast.service.AdvertMediaService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.service.SequenceIdService;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:AdvertMediaController
 *Description:媒体信息控制层
 *@author ChengJiawei
 *@date 2018年4月13日 下午2:31:07
 *
 */
@Controller
@RequestMapping(value="${adminPath}/broadcast/advertMedia")
public class AdvertMediaController extends BaseController{

	@Autowired
	private AdvertMediaService advertMediaService;
	@Autowired
	private SequenceIdService sequenceIdService;
	@Autowired
	private AdvertAreaService advertAreaService;
	@Autowired
	private AdvertAreaMediaService advertAreaMediaService;
	
	@ModelAttribute
	public AdvertMedia get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return advertMediaService.get(logid);
		} else {
			return new AdvertMedia();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(AdvertMedia advertMedia,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<AdvertMedia> page = advertMediaService.findPage(new Page<AdvertMedia>(req, resp), advertMedia);
		Map<String, String> mediaState_key = DictUtils.getDictMap(GParameter.mediaState_key);
		Map<String, String> mediaTypeMap = DictUtils.getDictMap(GParameter.mediaType_key);
		Map<String, String> mimeTypeMap = DictUtils.getDictMap(GParameter.mimeType_key);
		List<Dict> mediaTypeList = DictUtils.getDictList(GParameter.mediaType_key);
		List<Dict> mimeTypeList = DictUtils.getDictList(GParameter.mimeType_key);
		model.addAttribute("page", page);
		model.addAttribute("mediaState_key", mediaState_key);
		model.addAttribute("mediaTypeMap", mediaTypeMap);
		model.addAttribute("mimeTypeMap", mimeTypeMap);
		model.addAttribute("mediaTypeList", mediaTypeList);
		model.addAttribute("mimeTypeList", mimeTypeList);
 		return "/modules/broadcast/advertMediaList";
	}
	
	//跳转添加媒体页面
	@RequestMapping(value="form")
	public String form(AdvertMedia advertMedia,Model model,HttpServletRequest req,HttpServletResponse resp){
		if (advertMedia.getLogid()==null) {
			String mediaId = sequenceIdService.findNextVal("8888","media_id", 8); 
			advertMedia.setMediaId(mediaId);
		}
		return "/modules/broadcast/advertMediaForm";
	}
	
	//跳轉圖片上傳頁面
    @RequestMapping(value="toImgUpload/{mediaId}")
	public String toImgUpload(@PathVariable("mediaId") String mediaId,Model model,HttpServletRequest req) throws JsonProcessingException{
    	String path =  req.getSession().getServletContext().getRealPath("/")+"upload\\materielImg\\"+mediaId;
        File file = new File(path);
		String[] images = file.list();
		List<String> imageList = new ArrayList<String>();
		if (null!=images&&images.length>0) {
			imageList.add("\\upload\\materielImg\\"+mediaId+"\\"+images[0]);
		}
    	ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("materielImgList", mapper.writeValueAsString(imageList));
		return "modules/broadcast/imageUpload";
	}

    //上传图片
    @RequestMapping(value="imgUpload")
    @ResponseBody
    public Map<String, Object> imgUpload(@RequestParam(value="images",required=false) MultipartFile images, HttpServletRequest req,HttpServletResponse resp ,Model model) throws IOException{
    	String mediaId = req.getParameter("mediaId");
    	String path = req.getSession().getServletContext().getRealPath("/upload")+"/materielImg/"+mediaId+"/";
    	String originalFileName = images.getOriginalFilename();
    	String fileName = mediaId+"-"+System.nanoTime()+originalFileName.substring(originalFileName.lastIndexOf("."));
    	InputStream imgageInStream = images.getInputStream();
    	File file = new File(path);
    	if(!file.exists()){
    		file.mkdirs();
    	}
    	File outFile = new File(path+fileName);
    	outFile.createNewFile();
    	FileOutputStream outFileOs = new FileOutputStream(outFile);
    	byte[] bbuf = new byte[1024];
    	int hasRead = 0;
    	PrintWriter pw = resp.getWriter();
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
			while((hasRead = imgageInStream.read(bbuf))>0){
				outFileOs.write(bbuf);
			}
            map.put("success", "上传成功");
            map.put("originalFilename", images.getOriginalFilename());
		} catch (IOException e) {
			pw.write("default");
			map.put("error", "上传失败");
			map.put("originalFilename", images.getOriginalFilename());
		}
    	outFileOs.close();
    	return map;
    }
    
    //页面显示上传图片
    @RequestMapping(value="findImgUrl")
    @ResponseBody
    public List<String> findImgUrl(HttpServletRequest req){
    	String mediaId = req.getParameter("mediaId");
    	String path =  req.getSession().getServletContext().getRealPath("/")+"upload\\materielImg\\"+mediaId;
        File file = new File(path);
		String[] images = file.list();
		List<String> imageList = new ArrayList<String>();
		if (null!=images&&images.length>0) {
			imageList.add("\\upload\\materielImg\\"+mediaId+"\\"+images[0]);
			BufferedImage bi = null;
			String pa = req.getSession().getServletContext().getRealPath("/")+"upload\\materielImg\\"+mediaId+"\\"+images[0];
			File file2 = new File(pa);
			try {
				bi = ImageIO.read(file2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			imageList.add(bi.getWidth()+"");
			imageList.add(bi.getHeight()+"");
			String size = file2.length()/1024+"";
			imageList.add(size);
		} else {
			imageList.add("");
			imageList.add("");
			imageList.add("");
			imageList.add("");
		}
    	return imageList;
    }  
    
    //删除图片
    @RequestMapping(value="imgDelete")
    @ResponseBody
    public Map<String, Object> imgDelete(HttpServletRequest req,Model model){
    	String mediaId = req.getParameter("mediaId");
    	String originalName = req.getParameter("originalName");
    	String path =  req.getSession().getServletContext().getRealPath("/")+"upload\\materielImg\\"+mediaId+"\\"+originalName;
    	File file = new File(path);
    	file.delete();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("target", mediaId);
    	map.put("originalName", originalName);
    	return map;
    }
    
    //保存信息
	@RequestMapping(value = "save")
	public String save(AdvertMedia advertMedia,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		advertMedia.setLogid(request.getParameter("logid"));
		advertMedia.setMediaId(request.getParameter("mediaId"));
    	String mediaAddressS = request.getParameter("mediaAddressS");
    	if (mediaAddressS!=null&&mediaAddressS!="") {
    		String name = mediaAddressS.substring(mediaAddressS.lastIndexOf("\\")+1,mediaAddressS.length());
        	String dId = mediaAddressS.substring(mediaAddressS.lastIndexOf("\\")-8,mediaAddressS.lastIndexOf("\\"));
    		if (advertMedia.getMediaType().equals("01")) {
    			String path =  request.getSession().getServletContext().getRealPath("/")+"upload\\materielImg\\"+dId+"\\"+name;
    	    	File file = new File(path);
    	    	file.delete();
    		}
		}
    	advertMedia.setStateTime(DateUtils.getDateTime());
		advertMediaService.save(advertMedia);
		addMessage(redirectAttributes, "保存设备信息成功");
		return "redirect:" + adminPath + "/broadcast/advertMedia/list?repage";
	}
	
	//删除
	@RequestMapping(value = "delete")
	public String delete(AdvertMedia advertMedia, RedirectAttributes redirectAttributes ,HttpServletRequest req){
		AdvertAreaMedia obj = new AdvertAreaMedia();
		obj.setMediaId(advertMedia.getMediaId());
		List<AdvertAreaMedia> advertAreaMediaList = advertAreaMediaService.findList(obj);
		if (advertAreaMediaList.size()>0) {
			addMessage(redirectAttributes, "删除失败，已被区域媒体引用");
			return "redirect:" + adminPath + "/broadcast/advertMedia/";
		}
		advertMediaService.delete(advertMedia);
		//删除附件
		String path =  req.getSession().getServletContext().getRealPath("/")+"upload\\materielImg\\"+advertMedia.getMediaId();
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/broadcast/advertMedia/";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(AdvertMedia advertMedia, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "媒体信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<AdvertMedia> page = advertMediaService.findPage(new Page<AdvertMedia>(request, response, -1), advertMedia);
            List<AdvertMedia> advertMedias = page.getList();
    		new ExportExcel("媒体信息", AdvertMedia.class).setDataList(advertMedias).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出媒体信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/broadcast/advertMedia/list?repage";
    }

	//放大镜查询媒体信息
	@RequestMapping(value="advertMediaSelect")
	public String advertMediaSelect(AdvertMedia advertMedia,HttpServletRequest req, HttpServletResponse resp, Model model){
		String configId = req.getParameter("config_id");
		AdvertArea advertArea = advertAreaService.findOneByConfigId(configId);
		advertMedia.setCurState("0");
		advertMedia.setMediaType(advertArea.getMediaType());
		Page<AdvertMedia> page = advertMediaService.findPage(new Page<AdvertMedia>(req, resp), advertMedia);
		Map<String, String> mediaTypeMap = DictUtils.getDictMap(GParameter.mediaType_key);
		Map<String, String> mimeTypeMap = DictUtils.getDictMap(GParameter.mimeType_key);
		model.addAttribute("page", page);
		model.addAttribute("mimeTypeMap", mimeTypeMap);
		model.addAttribute("mediaTypeMap", mediaTypeMap);
		return "/modules/broadcast/advertMediaSelect";
	}
	
	
	//方法镜查询单个媒体信息
	@RequestMapping(value="advertMediaOneSelect")
	public String advertMediaOneSelect(Model model){
		AdvertMedia advertMedia = new AdvertMedia();
		List<AdvertMedia> advertMediaList = advertMediaService.findList(advertMedia);
		Map<String, String> mediaTypeMap = DictUtils.getDictMap(GParameter.mediaType_key);
		Map<String, String> mimeTypeMap = DictUtils.getDictMap(GParameter.mimeType_key);
		model.addAttribute("advertMediaList", advertMediaList);
		model.addAttribute("mimeTypeMap", mimeTypeMap);
		model.addAttribute("mediaTypeMap", mediaTypeMap);
		return "modules/broadcast/advertMediaOneSelect";
	}
	
}
