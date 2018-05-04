package com.zhilai.master.modules.broadcast.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.broadcast.entity.AdvertArea;
import com.zhilai.master.modules.broadcast.entity.AdvertAreaMedia;
import com.zhilai.master.modules.broadcast.service.AdvertAreaMediaService;
import com.zhilai.master.modules.broadcast.service.AdvertAreaService;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:AdvertAreaController
 *Description:区域配置控制层
 *@author ChengJiawei
 *@date 2018年4月17日 上午10:19:19
 *
 */
@Controller
@RequestMapping(value="${adminPath}/broadcast/advertArea")
public class AdvertAreaController extends BaseController{

	@Autowired
	private AdvertAreaService advertAreaService;
	
	@Autowired
	private AdvertAreaMediaService advertAreaMediaService;
	
	@ModelAttribute
	public AdvertArea get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return advertAreaService.get(logid);
		} else {
			return new AdvertArea();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(AdvertArea advertArea,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<AdvertArea> page = advertAreaService.findPage(new Page<AdvertArea>(req, resp), advertArea);
		Map<String, String> mediaTypeMap = DictUtils.getDictMap(GParameter.mediaType_key);
		Map<String, String> mimeTypeMap = DictUtils.getDictMap(GParameter.mimeType_key);
		model.addAttribute("page", page);
		model.addAttribute("mediaTypeMap", mediaTypeMap);
		model.addAttribute("mimeTypeMap", mimeTypeMap);
 		return "/modules/broadcast/advertAreaList";
	}
	
	//跳转添加媒体页面
	@RequestMapping(value="form")
	public String form(AdvertArea advertArea,Model model,HttpServletRequest req,HttpServletResponse resp){
		return "/modules/broadcast/advertAreaForm";
	}
	
    //保存信息
	@RequestMapping(value = "save")
	public String save(AdvertArea advertArea,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		advertAreaService.save(advertArea);
		addMessage(redirectAttributes, "保存区域配置成功");
		return "redirect:" + adminPath + "/broadcast/advertArea/list?repage";
	}
	
	//删除
	@RequestMapping(value = "delete")
	public String delete(AdvertArea advertArea, RedirectAttributes redirectAttributes ,HttpServletRequest req){
		AdvertAreaMedia obj = new AdvertAreaMedia();
		obj.setConfigId(advertArea.getConfigId());
		List<AdvertAreaMedia> advertAreaMediaList = advertAreaMediaService.findList(obj);
		if (advertAreaMediaList.size()>0) {
			addMessage(redirectAttributes, "删除失败，已被区域媒体引用！");
			return "redirect:" + adminPath + "/broadcast/advertArea/";
		}
		advertAreaService.delete(advertArea);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/broadcast/advertArea/";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(AdvertArea advertArea, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "区域配置"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<AdvertArea> page = advertAreaService.findPage(new Page<AdvertArea>(request, response, -1), advertArea);
            List<AdvertArea> advertAreas = page.getList();
    		new ExportExcel("区域配置", AdvertArea.class).setDataList(advertAreas).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出区域配置失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/broadcast/advertArea/list?repage";
    }
	
	//方法镜查询区域配置
	@RequestMapping(value="showAdvertAreaInfo")
	public String showAdvertAreaInfo(Model model){
		AdvertArea advertArea = new AdvertArea();
		List<AdvertArea> advertAreas = advertAreaService.findList(advertArea);
		Map<String, String> mediaTypeMap = DictUtils.getDictMap(GParameter.mediaType_key);
		model.addAttribute("advertAreaList", advertAreas);
		model.addAttribute("mediaTypeMap", mediaTypeMap);
		return "modules/broadcast/advertAreaSelect";
	}
}
