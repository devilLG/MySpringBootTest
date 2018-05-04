package com.zhilai.master.modules.monitor.web;

import java.io.File;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.monitor.entity.CabinetDevice;
import com.zhilai.master.modules.monitor.entity.DeviceInfo;
import com.zhilai.master.modules.monitor.entity.SiteDeviceConfig;
import com.zhilai.master.modules.monitor.service.CabinetDeviceService;
import com.zhilai.master.modules.monitor.service.DeviceInfoService;
import com.zhilai.master.modules.monitor.service.SiteDeviceConfigService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:CabinetDeviceController
 *Description:柜子设备控制层
 *@author ChengJiawei
 *@date 2018年4月11日 上午10:15:16
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/monitor/cabinetDevice")
public class CabinetDeviceController extends BaseController{

	@Autowired
	private CabinetDeviceService cabinetDeviceService;
	
	@Autowired
	private DeviceInfoService deviceInfoService;
	
	@Autowired
	private SiteDeviceConfigService siteDeviceConfigService;
	
	@ModelAttribute
	public CabinetDevice get(@RequestParam(required = false)String cadeviceId){
		if (StringUtils.isNotBlank(cadeviceId)) {
			return cabinetDeviceService.get(cadeviceId);
		} else {
			return new CabinetDevice();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(CabinetDevice cabinetDevice,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<CabinetDevice> page = cabinetDeviceService.findPage(new Page<CabinetDevice>(req, resp), cabinetDevice);
		List<Dict> deviceInfoList = DictUtils.getDictList(GParameter.deviceInfo_key);
		Map<String, String> deviceInfoMap = DictUtils.getDictMap(GParameter.deviceInfo_key);
		Map<String, String> deviceModelMap = DictUtils.getDictMap(GParameter.deviceModel_key);
		Map<String, String> deviceBardMap = DictUtils.getDictMap(GParameter.deviceBard_key);
		Map<String, String> deviceMfrsMap = DictUtils.getDictMap(GParameter.deviceMfrs_key);
		model.addAttribute("page", page);
		model.addAttribute("deviceInfoList", deviceInfoList);
		model.addAttribute("deviceInfoMap", deviceInfoMap);
		model.addAttribute("deviceModelMap", deviceModelMap);
		model.addAttribute("deviceBardMap", deviceBardMap);
		model.addAttribute("deviceMfrsMap", deviceMfrsMap);
 		return "/modules/monitor/cabinetDeviceList";
	}
	
	//跳转添加柜子设备页面
	@RequestMapping(value="form")
	public String form(CabinetDevice cabinetDevice,Model model,HttpServletRequest req,HttpServletResponse resp){
		List<Dict> deviceInfoList = DictUtils.getDictList(GParameter.deviceInfo_key);
		Map<String, String> deviceInfoMap = DictUtils.getDictMap(GParameter.deviceInfo_key);
		Map<String, String> deviceModelMap = DictUtils.getDictMap(GParameter.deviceModel_key);
		Map<String, String> deviceBardMap = DictUtils.getDictMap(GParameter.deviceBard_key);
		Map<String, String> deviceMfrsMap = DictUtils.getDictMap(GParameter.deviceMfrs_key);
		model.addAttribute("deviceInfoList", deviceInfoList);
		model.addAttribute("deviceInfoMap", deviceInfoMap);
		model.addAttribute("deviceModelMap", deviceModelMap);
		model.addAttribute("deviceBardMap", deviceBardMap);
		model.addAttribute("deviceMfrsMap", deviceMfrsMap);
		return "/modules/monitor/cabinetDeviceForm";
	}
	
	//检查重复添加柜子设备
	@ResponseBody
	@RequestMapping(value = "checkDeviceName/{deviceName}")
	public String checkDeviceName(@PathVariable("deviceName") String deviceName) {
		String code[] = deviceName.split(",");
		CabinetDevice cab = new CabinetDevice();
		cab.setCabconfigId(code[1]);
		cab.setDeviceName(code[2]);
		List<CabinetDevice> cabinetDeviceList = cabinetDeviceService.findList(cab);
		if (cabinetDeviceList.size()>0) {
			if (cabinetDeviceList.get(0).getLogid().equals(code[0])) {
				return "1";
			}else {
				return "0";
			}
		} else {
			return "1";
		}
	}
	
	 //保存信息
	@RequestMapping(value = "save")
	public String save(CabinetDevice cabinetDevice,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		DeviceInfo deviceInfo = deviceInfoService.findOneByDeviceId(cabinetDevice.getDeviceId());
		cabinetDevice.setDeviceModel(deviceInfo.getDeviceModel());
		cabinetDevice.setDeviceBard(deviceInfo.getDeviceBard());
		cabinetDevice.setDeviceMfrs(deviceInfo.getDeviceMfrs());
		cabinetDeviceService.save(cabinetDevice);
		addMessage(redirectAttributes, "保存柜子设备成功");
		return "redirect:" + adminPath + "/monitor/cabinetDevice/list?repage";
	}
	
	//删除
	@RequestMapping(value = "delete")
	public String delete(CabinetDevice cabinetDevice, RedirectAttributes redirectAttributes ,HttpServletRequest req){
		SiteDeviceConfig obj = new SiteDeviceConfig();
		obj.setDeviceId(cabinetDevice.getDeviceId());
		List<SiteDeviceConfig> siteDeviceConfigList = siteDeviceConfigService.findList(obj);
		if (siteDeviceConfigList.size()>0) {
			addMessage(redirectAttributes, "删除失败，该柜子设备已被站点引用！");
			return "redirect:" + adminPath + "/monitor/monitorDevice/";
		}
		cabinetDeviceService.delete(cabinetDevice);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/monitor/monitorDevice/";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CabinetDevice cabinetDevice, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "柜子设备"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<CabinetDevice> page = cabinetDeviceService.findPage(new Page<CabinetDevice>(request, response, -1), cabinetDevice);
            List<CabinetDevice> cabinetDevices = page.getList();
    		new ExportExcel("柜子设备", CabinetDevice.class).setDataList(cabinetDevices).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出柜子设备失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/monitor/monitorDevice/list?repage";
    }
	
}
