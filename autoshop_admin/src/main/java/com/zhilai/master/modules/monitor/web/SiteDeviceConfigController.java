package com.zhilai.master.modules.monitor.web;

import java.util.ArrayList;
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

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.monitor.entity.SiteEnv;
import com.zhilai.master.modules.monitor.entity.SiteDeviceConfig;
import com.zhilai.master.modules.monitor.service.SiteEnvService;
import com.zhilai.master.modules.monitor.service.SiteDeviceConfigService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:SiteDeviceConfigController
 *Description:站点设备控制层
 *@author ChengJiawei
 *@date 2018年4月12日 上午9:07:33
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/monitor/siteDeviceConfig")
public class SiteDeviceConfigController extends BaseController{

	@Autowired
	private SiteDeviceConfigService siteDeviceConfigService;
	@Autowired
	private SiteEnvService siteEnvService;
	
	@ModelAttribute
	public SiteDeviceConfig get(@RequestParam(required = false)String sdeviceId){
		if (org.apache.commons.lang3.StringUtils.isNotBlank(sdeviceId)) {
			SiteDeviceConfig siteDeviceConfig =  siteDeviceConfigService.get(sdeviceId);
			if (siteDeviceConfig == null) {
				return new SiteDeviceConfig();
			}
			List<SiteEnv> deviceEnvList = siteEnvService.findBySdeviceId(siteDeviceConfig.getSdeviceId());
			if (deviceEnvList.size()>0) {
				siteDeviceConfig.setSiteEnv(deviceEnvList);
			} else {
				deviceEnvList = new ArrayList<SiteEnv>();
				siteDeviceConfig.setSiteEnv(deviceEnvList);
			}
			return siteDeviceConfig;
		} else {
			SiteDeviceConfig siteDeviceConfig = new SiteDeviceConfig();
			List<SiteEnv> deviceEnvList = new ArrayList<SiteEnv>();
			siteDeviceConfig.setSiteEnv(deviceEnvList);
			return siteDeviceConfig;
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(SiteDeviceConfig siteDeviceConfig,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<SiteDeviceConfig> page = siteDeviceConfigService.findPage(new Page<SiteDeviceConfig>(req, resp), siteDeviceConfig);
		List<Dict> deviceInfoList = DictUtils.getDictList(GParameter.deviceInfo_key);
		Map<String, String> deviceInfoMap = DictUtils.getDictMap(GParameter.deviceInfo_key);
		Map<String, String> deviceModelMap = DictUtils.getDictMap(GParameter.deviceModel_key);
		Map<String, String> deviceBardMap = DictUtils.getDictMap(GParameter.deviceBard_key);
		Map<String, String> deviceMfrsMap = DictUtils.getDictMap(GParameter.deviceMfrs_key);
		Map<String, String> errorLevelInfoMap = DictUtils.getDictMap(GParameter.errorLevelInfo_key);
		Map<String, String> siteDeviceStateMap = DictUtils.getDictMap(GParameter.siteDeviceState_key);
		Map<String, String> siteDeviceCheckStateMap = DictUtils.getDictMap(GParameter.siteDeviceCheckState_key);
		model.addAttribute("page", page);
		model.addAttribute("deviceInfoList", deviceInfoList);
		model.addAttribute("deviceInfoMap", deviceInfoMap);
		model.addAttribute("deviceModelMap", deviceModelMap);
		model.addAttribute("deviceBardMap", deviceBardMap);
		model.addAttribute("deviceMfrsMap", deviceMfrsMap);
		model.addAttribute("errorLevelInfoMap", errorLevelInfoMap);
		model.addAttribute("siteDeviceStateMap", siteDeviceStateMap);
		model.addAttribute("siteDeviceCheckStateMap", siteDeviceCheckStateMap);
 		return "/modules/monitor/siteDeviceConfigList";
	}
	
	//跳转详情页面
	@RequestMapping(value="form")
	public String form(SiteDeviceConfig siteDeviceConfig,Model model,HttpServletRequest req,HttpServletResponse resp){
		List<Dict> deviceInfoList = DictUtils.getDictList(GParameter.deviceInfo_key);
		List<Dict> siteDeviceCheckStateList = DictUtils.getDictList(GParameter.siteDeviceCheckState_key);
		List<Dict> deviceUnitList = DictUtils.getDictList(GParameter.deviceUnit_key);
		List<SiteEnv> siteEnvList = siteEnvService.findBySdeviceId(siteDeviceConfig.getSdeviceId());
		siteDeviceConfig.setSiteEnv(siteEnvList);
		model.addAttribute("siteDeviceConfig", siteDeviceConfig);
		model.addAttribute("siteDeviceCheckStateList", siteDeviceCheckStateList);
		model.addAttribute("deviceInfoList", deviceInfoList);
		model.addAttribute("deviceUnitList", deviceUnitList);
		return "/modules/monitor/siteDeviceConfigForm";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SiteDeviceConfig siteDeviceConfig, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "站点设备"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SiteDeviceConfig> page = siteDeviceConfigService.findPage(new Page<SiteDeviceConfig>(request, response, -1), siteDeviceConfig);
            List<SiteDeviceConfig> siteDeviceConfigs = page.getList();
    		new ExportExcel("站点设备", SiteDeviceConfig.class).setDataList(siteDeviceConfigs).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出站点设备失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/monitor/siteDeviceConfig/list?repage";
    }
}
