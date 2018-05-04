package com.zhilai.master.modules.notice.web;

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
import com.zhilai.master.modules.notice.entity.NoticeQueueLog;
import com.zhilai.master.modules.notice.service.NoticeQueueLogService;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:NoticeQueueLogController
 *Description:通知发送日志控制层
 *@author ChengJiawei
 *@date 2018年4月23日 上午8:48:24
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/notice/noticeQueueLog")
public class NoticeQueueLogController extends BaseController{

	@Autowired
	private NoticeQueueLogService noticeQueueLogService;
	
	@ModelAttribute
	public NoticeQueueLog get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return noticeQueueLogService.get(logid);
		} else {
			return new NoticeQueueLog();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(NoticeQueueLog noticeQueueLog,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<NoticeQueueLog> page = noticeQueueLogService.findPage(new Page<NoticeQueueLog>(req, resp), noticeQueueLog);
		Map<String, String> noticeOperActionMap = DictUtils.getDictMap(GParameter.noticeOperAction_key);
		model.addAttribute("page", page);
		model.addAttribute("noticeOperActionMap", noticeOperActionMap);
 		return "/modules/notice/noticeQueueLogList";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(NoticeQueueLog noticeQueueLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "通知发送日志"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<NoticeQueueLog> page = noticeQueueLogService.findPage(new Page<NoticeQueueLog>(request, response, -1), noticeQueueLog);
            List<NoticeQueueLog> noticeQueueLogs = page.getList();
    		new ExportExcel("通知发送日志", NoticeQueueLog.class).setDataList(noticeQueueLogs).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出通知发送日志失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/notice/noticeQueueLog/list?repage";
    }
	
}
