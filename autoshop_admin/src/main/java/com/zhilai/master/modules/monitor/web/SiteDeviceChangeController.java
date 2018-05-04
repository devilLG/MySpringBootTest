package com.zhilai.master.modules.monitor.web;

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
import com.zhilai.master.modules.monitor.entity.SiteDeviceChange;
import com.zhilai.master.modules.monitor.service.SiteDeviceChangeService;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:SiteDeviceChangeController
 *Description:设备日志控制层
 *@author ChengJiawei
 *@date 2018年4月13日 上午11:20:50
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/monitor/siteDeviceChange")
public class SiteDeviceChangeController extends BaseController{
	
	@Autowired
	private SiteDeviceChangeService SiteDeviceChangeService;
	
	@ModelAttribute
	public SiteDeviceChange get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return SiteDeviceChangeService.get(logid);
		} else {
			return new SiteDeviceChange();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(SiteDeviceChange siteDeviceChange,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<SiteDeviceChange> page = SiteDeviceChangeService.findPage(new Page<SiteDeviceChange>(req, resp), siteDeviceChange);
		Map<String, String> deviceInfoMap = DictUtils.getDictMap(GParameter.deviceInfo_key);
		Map<String, String> operActionMap = DictUtils.getDictMap(GParameter.operAction_key);
		model.addAttribute("page", page);
		model.addAttribute("deviceInfoMap", deviceInfoMap);
		model.addAttribute("operActionMap", operActionMap);
 		return "/modules/monitor/siteDeviceChangeList";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SiteDeviceChange siteDeviceChange, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "设备日志"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SiteDeviceChange> page = SiteDeviceChangeService.findPage(new Page<SiteDeviceChange>(request, response, -1), siteDeviceChange);
            List<SiteDeviceChange> siteDeviceChanges = page.getList();
    		new ExportExcel("设备日志", SiteDeviceChange.class).setDataList(siteDeviceChanges).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出设备日志失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/monitor/siteDeviceChange/list?repage";
    }
}
