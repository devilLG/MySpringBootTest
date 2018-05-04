package com.zhilai.master.modules.workOrder.web;

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
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.workOrder.entity.WorkChange;
import com.zhilai.master.modules.workOrder.service.WorkChangeService;

/**
 * 
 *Title:WorkChangeController
 *Description:工单日志控制层
 *@author ChengJiawei
 *@date 2018年4月19日 上午11:28:28
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/workOrder/workChange")
public class WorkChangeController extends BaseController{
	@Autowired
	private WorkChangeService workChangeService;
	
	@ModelAttribute
	public WorkChange get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return workChangeService.get(logid);
		} else {
			return new WorkChange();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(WorkChange workChange,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<WorkChange> page = workChangeService.findPage(new Page<WorkChange>(req, resp), workChange);
		Map<String, String> operActionMap = DictUtils.getDictMap(GParameter.workOperAction_key);
		model.addAttribute("page", page);
		model.addAttribute("operActionMap", operActionMap);
 		return "/modules/workOrder/workChangeList";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(WorkChange workChange, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "工单日志"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<WorkChange> page = workChangeService.findPage(new Page<WorkChange>(request, response, -1), workChange);
            List<WorkChange> workChanges = page.getList();
    		new ExportExcel("工单日志", WorkChange.class).setDataList(workChanges).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出工单日志失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/workOrder/workChange/list?repage";
    }
}
