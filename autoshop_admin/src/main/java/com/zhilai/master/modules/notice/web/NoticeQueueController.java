package com.zhilai.master.modules.notice.web;

import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.notice.entity.NoticeQueue;
import com.zhilai.master.modules.notice.service.NoticeQueueService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.sys.utils.UserUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:NoticeQueueController
 *Description:通知发送队列控制层
 *@author ChengJiawei
 *@date 2018年4月21日 下午2:52:08
 *
 */
@Controller
@RequestMapping(value="${adminPath}/notice/noticeQueue")
public class NoticeQueueController extends BaseController {

	Logger log = Logger.getLogger(NoticeQueueController.class);
	
	@Autowired
	private NoticeQueueService noticeQueueService;
	
	@ModelAttribute
	public NoticeQueue get(@RequestParam(required=false) String queueId) {
		if (StringUtils.isNotBlank(queueId)) {
			return noticeQueueService.get(queueId);
		}else {
			return new NoticeQueue();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(NoticeQueue noticeQueue,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<NoticeQueue> page = noticeQueueService.findPage(new Page<NoticeQueue>(req, resp), noticeQueue);
		List<Dict> mainTypeList = DictUtils.getDictList(GParameter.mainType_key);
		Map<String, String> mainTypeMap = DictUtils.getDictMap(GParameter.mainType_key);
		Map<String, String> noticeTypeMap = DictUtils.getDictMap(GParameter.noticeType_key);
		Map<String, String> noticeChannelMap = DictUtils.getDictMap(GParameter.noticeChannel_key);
		Map<String, String> sendStateMap = DictUtils.getDictMap(GParameter.sendState_key);
		Map<String, String> curStateMap = DictUtils.getDictMap(GParameter.curState_key);
		model.addAttribute("page", page);
		model.addAttribute("mainTypeList", mainTypeList);
		model.addAttribute("mainTypeMap", mainTypeMap);
		model.addAttribute("noticeTypeMap", noticeTypeMap);
		model.addAttribute("noticeChannelMap", noticeChannelMap);
		model.addAttribute("sendStateMap", sendStateMap);
		model.addAttribute("curStateMap", curStateMap);
 		return "/modules/notice/noticeQueueList";
	}
	
	//跳转通知发送队列详情页面
	@RequestMapping(value="form")
	public String form(NoticeQueue noticeQueue,Model model,HttpServletRequest req,HttpServletResponse resp){
		if (null != noticeQueue && null != noticeQueue.getLogid() && !noticeQueue.getLogid().equals("")) {
			noticeQueue.setSub_type(noticeQueue.getSub_type().substring(2, noticeQueue.getSub_type().length()));
		}
		return "/modules/notice/noticeQueueForm";
	}
		
	// 获取通知个人通知数目
	@RequestMapping(value="noticeCount")
	public void noticeCount(NoticeQueue noticeQueue, HttpServletRequest request, HttpServletResponse response) {
		try {
			Writer out = response.getWriter();
			User user = UserUtils.getUser();
			noticeQueue.setLogin_id(user.getLoginName());
			noticeQueue.setNotice_type(GParameter.noticeType_manager);
			noticeQueue.setSend_state(GParameter.noticeSendState_wait);
			List<NoticeQueue> noticeQueues = noticeQueueService.findForLogin(noticeQueue);
			if (noticeQueues == null) {
				out.write("0");
				out.close();
			}else {
				out.write(noticeQueues.size()+"");
				out.close();
			}
		} catch (Exception e) {
			e.toString();
			log.info("获取通知数目出错");
		}
	}
	
	// 展示个人通知列表
	@RequestMapping(value="loginNotice")
	public String loginNotice(NoticeQueue noticeQueue, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		noticeQueue.setLogin_id(user.getLoginName());
		noticeQueue.setNotice_type(GParameter.noticeType_manager);
		//noticeQueue.setSend_state(GParameter.noticeSendState_wait);
		//List<NoticeQueue> noticeQueues = noticeQueueService.findForLogin(noticeQueue);
		Page<NoticeQueue> page = noticeQueueService.findPage(new Page<NoticeQueue>(request, response), noticeQueue);
		Map<String, String> mainTypeMap = DictUtils.getDictMap(GParameter.mainType_key);
		List<Dict> mainTypeList = DictUtils.getDictList(GParameter.mainType_key);
		model.addAttribute("page", page);
		model.addAttribute("mainTypeMap", mainTypeMap);
		model.addAttribute("mainTypeList", mainTypeList);
		return "/modules/notice/loginNoticeList";
	}
	
	@RequestMapping(value="showDetail")
	public String showDetail(NoticeQueue noticeQueue, Model model) {
		if (!noticeQueue.getSend_state().equals(GParameter.noticeSendState_success)) {
			noticeQueue.setSend_state(GParameter.noticeSendState_success);
			noticeQueue.setState_time(DateUtils.DateToString());
			noticeQueueService.save(noticeQueue);
		}
		
		Map<String, String> mainTypeMap = DictUtils.getDictMap(GParameter.mainType_key);
		model.addAttribute("mainTypeMap", mainTypeMap);
		return "/modules/notice/loginNoticeDetail";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(NoticeQueue noticeQueue, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "通知发送队列"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<NoticeQueue> page = noticeQueueService.findPage(new Page<NoticeQueue>(request, response, -1), noticeQueue);
            List<NoticeQueue> noticeQueues = page.getList();
    		new ExportExcel("通知发送队列", NoticeQueue.class).setDataList(noticeQueues).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出通知发送队列失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/notice/noticeQueue/list?repage";
    }
}
