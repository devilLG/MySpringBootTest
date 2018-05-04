package com.zhilai.master.modules.notice.web;

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
import com.zhilai.master.modules.notice.entity.NoticeConfig;
import com.zhilai.master.modules.notice.entity.NoticeConfig;
import com.zhilai.master.modules.notice.service.NoticeConfigService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.sys.utils.UserUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:CorpConfigController
 *Description:通知配置信息控制层
 *@author ChengJiawei
 *@date 2018年4月21日 上午9:11:00
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/notice/noticeConfig")
public class NoticeConfigController extends BaseController{

	@Autowired
	private NoticeConfigService noticeConfigService;
	
	@ModelAttribute
	public NoticeConfig get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return noticeConfigService.get(logid);
		} else {
			return new NoticeConfig();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(NoticeConfig noticeConfig,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<NoticeConfig> page = noticeConfigService.findPage(new Page<NoticeConfig>(req, resp), noticeConfig);
		List<Dict> noticeTypeList = DictUtils.getDictList(GParameter.noticeType_key);
		Map<String, String> configTypeMap = DictUtils.getDictMap(GParameter.configType_key);
		Map<String, String> noticeTypeMap = DictUtils.getDictMap(GParameter.noticeType_key);
		Map<String, String> noticeChannelMap = DictUtils.getDictMap(GParameter.noticeChannel_key);
		Map<String, String> curStateMap = DictUtils.getDictMap(GParameter.curState_key);
		model.addAttribute("page", page);
		model.addAttribute("noticeTypeList", noticeTypeList);
		model.addAttribute("configTypeMap", configTypeMap);
		model.addAttribute("noticeChannelMap", noticeChannelMap);
		model.addAttribute("noticeTypeMap", noticeTypeMap);
		model.addAttribute("curStateMap", curStateMap);
 		return "/modules/notice/noticeConfigList";
	}
	
	//跳转添加公共通知模板页面
	@RequestMapping(value="form")
	public String form(NoticeConfig noticeConfig,Model model,HttpServletRequest req,HttpServletResponse resp){
		return "/modules/notice/noticeConfigForm";
	}
	
    //保存信息
	@RequestMapping(value = "save")
	public String save(NoticeConfig noticeConfig,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		String copId = UserUtils.getUser().getCompany().getCompanyId();
		NoticeConfig obj = noticeConfigService.findOneByComId(copId);
		if (null!=obj&&(null!=obj.getLogid()||!obj.getLogid().equals(""))) {
			addMessage(redirectAttributes, "保存公共通知模板失败，该公司模板已经存在！");
			return "redirect:" + adminPath + "/notice/noticeConfig/list?repage";
		}
		noticeConfig.setCur_state("0");
		noticeConfigService.save(noticeConfig);
		addMessage(redirectAttributes, "保存公共通知模板成功");
		return "redirect:" + adminPath + "/notice/noticeConfig/list?repage";
	}
	
	//修改状态
	@RequestMapping(value = "updateState")
	public void updateState(NoticeConfig noticeConfig, HttpServletRequest request, HttpServletResponse response) {
		try {
			Writer out = response.getWriter();
			noticeConfig = get(request.getParameter("logid"));
			noticeConfig.setCur_state(request.getParameter("cur_state"));
			noticeConfigService.save(noticeConfig);
			out.write("success");
			out.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	//删除
	@RequestMapping(value = "delete")
	public String delete(NoticeConfig noticeConfig, RedirectAttributes redirectAttributes ,HttpServletRequest req){
		if (noticeConfig.getCur_state().equals("0")) {
			addMessage(redirectAttributes, "删除失败，只有失效模板可以删除");
			return "redirect:" + adminPath + "/notice/noticeConfig/";
		}
		noticeConfigService.delete(noticeConfig);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/notice/noticeConfig/";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(NoticeConfig noticeConfig, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "公共通知模板"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<NoticeConfig> page = noticeConfigService.findPage(new Page<NoticeConfig>(request, response, -1), noticeConfig);
            List<NoticeConfig> noticeConfigs = page.getList();
    		new ExportExcel("公共通知模板", NoticeConfig.class).setDataList(noticeConfigs).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出公共通知模板失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/notice/noticeConfig/list?repage";
    }
	
}
