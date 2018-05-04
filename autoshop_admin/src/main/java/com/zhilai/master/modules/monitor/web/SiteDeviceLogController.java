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
import com.zhilai.master.modules.monitor.entity.SiteDeviceLog;
import com.zhilai.master.modules.monitor.service.SiteDeviceLogService;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:SiteDeviceLogController
 *Description:站点日志控制层
 *@author ChengJiawei
 *@date 2018年4月12日 下午4:34:30
 *
 */
@Controller
@RequestMapping(value ="${adminPath}/monitor/siteDeviceLog")
public class SiteDeviceLogController extends BaseController{
	
	@Autowired
	private SiteDeviceLogService siteDeviceLogService;
	
	@ModelAttribute
	public SiteDeviceLog get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return siteDeviceLogService.get(logid);
		} else {
			return new SiteDeviceLog();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(SiteDeviceLog siteDeviceLog,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<SiteDeviceLog> page = siteDeviceLogService.findPage(new Page<SiteDeviceLog>(req, resp), siteDeviceLog);
		Map<String, String> deviceInfoMap = DictUtils.getDictMap(GParameter.deviceInfo_key);
		Map<String, String> operStateMap = DictUtils.getDictMap(GParameter.operState_key);
		Map<String, String> logLevelMap = DictUtils.getDictMap(GParameter.logLevel_key);
		model.addAttribute("page", page);
		model.addAttribute("deviceInfoMap", deviceInfoMap);
		model.addAttribute("operStateMap", operStateMap);
		model.addAttribute("logLevelMap", logLevelMap);
 		return "/modules/monitor/siteDeviceLogList";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SiteDeviceLog siteDeviceLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "站点日志"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SiteDeviceLog> page = siteDeviceLogService.findPage(new Page<SiteDeviceLog>(request, response, -1), siteDeviceLog);
            List<SiteDeviceLog> siteDeviceLogs = page.getList();
    		new ExportExcel("站点日志", SiteDeviceLog.class).setDataList(siteDeviceLogs).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出站点日志失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/monitor/siteDeviceLog/list?repage";
    }
}
