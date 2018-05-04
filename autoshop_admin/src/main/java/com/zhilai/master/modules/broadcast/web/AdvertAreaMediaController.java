package com.zhilai.master.modules.broadcast.web;

import java.io.Writer;
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
import com.zhilai.master.modules.broadcast.entity.AdvertAreaMedia;
import com.zhilai.master.modules.broadcast.service.AdvertAreaMediaService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:AdvertAreaMediaController
 *Description:区域媒体控制层
 *@author ChengJiawei
 *@date 2018年4月17日 下午2:42:33
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/broadcast/advertAreaMedia")
public class AdvertAreaMediaController extends BaseController{

	@Autowired
	private AdvertAreaMediaService advertAreaMediaService;
	
	@ModelAttribute
	public AdvertAreaMedia get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return advertAreaMediaService.get(logid);
		} else {
			return new AdvertAreaMedia();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(AdvertAreaMedia advertAreaMedia,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<AdvertAreaMedia> page = advertAreaMediaService.findPage(new Page<AdvertAreaMedia>(req, resp), advertAreaMedia);
		Map<String, String> mediaTypeMap = DictUtils.getDictMap(GParameter.mediaType_key);
		Map<String, String> mimeTypeMap = DictUtils.getDictMap(GParameter.mimeType_key);
		Map<String, String> curStateMap = DictUtils.getDictMap(GParameter.curState_key);
		List<Dict> mimeTypeList = DictUtils.getDictList(GParameter.mimeType_key);
		model.addAttribute("page", page);
		model.addAttribute("mediaTypeMap", mediaTypeMap);
		model.addAttribute("mimeTypeMap", mimeTypeMap);
		model.addAttribute("curStateMap", curStateMap);
		model.addAttribute("mimeTypeList", mimeTypeList);
 		return "/modules/broadcast/advertAreaMediaList";
	}
	
	//跳转添加媒体页面
	@RequestMapping(value="form")
	public String form(AdvertAreaMedia advertAreaMedia,Model model,HttpServletRequest req,HttpServletResponse resp){
		Map<String, String> mediaTypeMap = DictUtils.getDictMap(GParameter.mediaType_key);
		Map<String, String> mimeTypeMap = DictUtils.getDictMap(GParameter.mimeType_key);
		model.addAttribute("mediaTypeMap", mediaTypeMap);
		model.addAttribute("mimeTypeMap", mimeTypeMap);
		return "/modules/broadcast/advertAreaMediaForm";
	}
	
    //保存信息
	@RequestMapping(value = "save")
	public String save(AdvertAreaMedia advertAreaMedia,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		String[] mediaIdList = request.getParameterValues("mediaId");
		String[] mediaTypeList = request.getParameterValues("mediaType");
		String[] mimeTypeList = request.getParameterValues("mimeType");
		String[] mediaAddressList = request.getParameterValues("mediaAddress");
		String[] mediaSizeList = request.getParameterValues("mediaSize");
		String areaId = advertAreaMedia.getAreaId();
		String areaName = advertAreaMedia.getAreaName();
		String configId = advertAreaMedia.getConfigId();
		String configName = advertAreaMedia.getConfigName();
		String sine = "";
		for (int i = 0; i < mediaIdList.length; i++) {
			advertAreaMedia = new AdvertAreaMedia(); 
			advertAreaMedia.setMediaId(mediaIdList[i]);
			List<AdvertAreaMedia> objList = advertAreaMediaService.findList(advertAreaMedia);
			if (objList!=null&&objList.size()>0) {
				sine+=mediaIdList[i]+";";
				continue;
			}
			advertAreaMedia.setAreaId(areaId);
			advertAreaMedia.setAreaName(areaName);
			advertAreaMedia.setConfigId(configId);
			advertAreaMedia.setConfigName(configName);
			advertAreaMedia.setMediaType(mediaTypeList[i]);
			advertAreaMedia.setMimeType(mimeTypeList[i]);
			advertAreaMedia.setMediaAddress(mediaAddressList[i]);
			advertAreaMedia.setMediaSize(mediaSizeList[i]);
			advertAreaMedia.setMediaSeq(i+1+"");
			advertAreaMedia.setStateTime(DateUtils.getDateTime());
			advertAreaMediaService.save(advertAreaMedia);
		}
		if (sine.equals("")) {
			addMessage(redirectAttributes, "保存区域媒体成功");
		} else {
			addMessage(redirectAttributes, "保存区域媒体成功,以下媒体已存在当前配置："+sine);
		}
		return "redirect:" + adminPath + "/broadcast/advertAreaMedia/list?repage";
	}
	
	//更新启用
	@RequestMapping(value = "updateState")
	public void updateState(AdvertAreaMedia advertAreaMedia, HttpServletRequest request, HttpServletResponse response) {
		try {
			Writer out = response.getWriter();
			advertAreaMedia = get(request.getParameter("logid"));
			advertAreaMedia.setCurState(request.getParameter("curStatus"));
			advertAreaMedia.setStateTime(DateUtils.getDateTime());
			advertAreaMediaService.save(advertAreaMedia);
			out.write("success");
			out.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//删除
	@RequestMapping(value = "delete")
	public String delete(AdvertAreaMedia advertAreaMedia, RedirectAttributes redirectAttributes ,HttpServletRequest req){
		advertAreaMediaService.delete(advertAreaMedia);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/broadcast/advertAreaMedia/";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(AdvertAreaMedia advertAreaMedia, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "区域媒体"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<AdvertAreaMedia> page = advertAreaMediaService.findPage(new Page<AdvertAreaMedia>(request, response, -1), advertAreaMedia);
            List<AdvertAreaMedia> advertAreaMedias = page.getList();
    		new ExportExcel("区域媒体", AdvertAreaMedia.class).setDataList(advertAreaMedias).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出区域媒体失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/broadcast/advertAreaMedia/list?repage";
    }
}
