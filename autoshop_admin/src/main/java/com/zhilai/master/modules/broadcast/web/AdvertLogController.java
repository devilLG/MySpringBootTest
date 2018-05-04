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
import com.zhilai.master.modules.broadcast.entity.AdvertLog;
import com.zhilai.master.modules.broadcast.service.AdvertLogService;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:AdvertLogController
 *Description:广告日志控制层
 *@author ChengJiawei
 *@date 2018年4月19日 上午9:24:46
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/broadcast/advertLog")
public class AdvertLogController extends BaseController{

	@Autowired
	private AdvertLogService advertLogService;
	
	@ModelAttribute
	public AdvertLog get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return advertLogService.get(logid);
		} else {
			return new AdvertLog();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(AdvertLog advertLog,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<AdvertLog> page = advertLogService.findPage(new Page<AdvertLog>(req, resp), advertLog);
		Map<String, String> operActionMap = DictUtils.getDictMap(GParameter.advertOperAction_key);
		model.addAttribute("page", page);
		model.addAttribute("operActionMap", operActionMap);
 		return "/modules/broadcast/advertLogList";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(AdvertLog advertLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "广告日志"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<AdvertLog> page = advertLogService.findPage(new Page<AdvertLog>(request, response, -1), advertLog);
            List<AdvertLog> advertLogs = page.getList();
    		new ExportExcel("广告日志", AdvertLog.class).setDataList(advertLogs).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出广告日志失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/broadcast/advertLog/list?repage";
    }
}
