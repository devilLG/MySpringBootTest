package com.zhilai.master.modules.broadcast.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.zhilai.master.modules.broadcast.entity.AdvertConfig;
import com.zhilai.master.modules.broadcast.service.AdvertConfigService;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:AdvertConfigController
 *Description:广告配置控制层
 *@author ChengJiawei
 *@date 2018年4月16日 下午5:43:59
 *
 */
@Controller
@RequestMapping(value="${adminPath}/broadcast/advertConfig")
public class AdvertConfigController extends BaseController{

	@Autowired
	private AdvertConfigService advertConfigService;
	
	@ModelAttribute
	public AdvertConfig get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return advertConfigService.get(logid);
		} else {
			return new AdvertConfig();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(AdvertConfig advertConfig,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<AdvertConfig> page = advertConfigService.findPage(new Page<AdvertConfig>(req, resp), advertConfig);
		Map<String, String> isDefalutMap = DictUtils.getDictMap(GParameter.isDefalut_key);
		Map<String, String> curStateMap = DictUtils.getDictMap(GParameter.advertConfigState_key);
		model.addAttribute("page", page);
		model.addAttribute("isDefalutMap", isDefalutMap);
		model.addAttribute("curStateMap", curStateMap);
 		return "/modules/broadcast/advertConfigList";
	}
	
	//跳转添加媒体页面
	@RequestMapping(value="form")
	public String form(AdvertConfig advertConfig,Model model,HttpServletRequest req,HttpServletResponse resp){
		return "/modules/broadcast/advertConfigForm";
	}
	
    //保存信息
	@RequestMapping(value = "save")
	public String save(AdvertConfig advertConfig,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		advertConfig.setLogid(request.getParameter("logid"));
		advertConfig.setConfigId(request.getParameter("configId"));
		advertConfig.setStateTime(DateUtils.getDateTime());
		advertConfigService.save(advertConfig);
		addMessage(redirectAttributes, "保存广告配置成功");
		return "redirect:" + adminPath + "/broadcast/advertConfig/list?repage";
	}
	
	//删除
	@RequestMapping(value = "delete")
	public String delete(AdvertConfig advertConfig, RedirectAttributes redirectAttributes ,HttpServletRequest req){
		DateFormat df  = new SimpleDateFormat("yyyy-MM-dd");
		Date now;
		try {
			now = df.parse(DateUtils.getDate());
			Date sTime = df.parse(advertConfig.getPlanStime());
			Date eTime = df.parse(advertConfig.getPlanEtime());
			if ((now.getTime()>=sTime.getTime())&&(now.getTime()<=eTime.getTime())) {
				addMessage(redirectAttributes, "删除失败，计划播放时间内，不允许删除!");
				return "redirect:" + adminPath + "/broadcast/advertConfig/";
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		advertConfigService.delete(advertConfig);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/broadcast/advertConfig/";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(AdvertConfig advertConfig, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "广告配置"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<AdvertConfig> page = advertConfigService.findPage(new Page<AdvertConfig>(request, response, -1), advertConfig);
            List<AdvertConfig> advertConfigs = page.getList();
    		new ExportExcel("广告配置", AdvertConfig.class).setDataList(advertConfigs).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出广告配置失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/broadcast/advertConfig/list?repage";
    }
	
	//方法镜查询配置
	@RequestMapping(value="showAdvertConfigInfo")
	public String showAdvertConfigInfo(Model model){
		AdvertConfig advertConfig = new AdvertConfig();
		List<AdvertConfig> advertConfigs = advertConfigService.findList(advertConfig);
		Map<String, String> mediaTypeMap = DictUtils.getDictMap(GParameter.mediaType_key);
		model.addAttribute("advertConfigList", advertConfigs);
		model.addAttribute("mediaTypeMap", mediaTypeMap);
		return "modules/broadcast/advertConfigSelect";
	}

}
