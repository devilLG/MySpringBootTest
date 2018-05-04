package com.zhilai.master.modules.workOrder.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.monitor.service.SiteEnvService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.sys.utils.UserUtils;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.workOrder.entity.Work;
import com.zhilai.master.modules.workOrder.entity.WorkDevice;
import com.zhilai.master.modules.workOrder.service.WorkChangeService;
import com.zhilai.master.modules.workOrder.service.WorkDeviceService;
import com.zhilai.master.modules.workOrder.service.WorkService;

/**
 * 
 *Title:WorkSolvingController
 *Description:工单处理控制层
 *@author ChengJiawei
 *@date 2018年4月26日 上午11:06:19
 *
 */
@Controller
@RequestMapping(value="${adminPath}/workOrder/workSolving")
public class WorkSolvingController extends BaseController{

	@Autowired
	private WorkService workService;
	
	@Autowired
	private WorkDeviceService workDeviceService;
	
	@Autowired
	private WorkChangeService workChangeService;
	
	@Autowired
	private SiteEnvService siteEnvService;

	@ModelAttribute
	public Work get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return workService.get(logid);
		} else {
			return new Work();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(Work work,HttpServletRequest req,HttpServletResponse resp,Model model){
		work.setHandlerId(UserUtils.getUser().getLoginName());
		Page<Work> page = workService.findDataPage(new Page<Work>(req, resp), work);
		List<Dict> workCurStateList = DictUtils.getDictList(GParameter.workCurState_key);
		Map<String, String> workCurStateMap = DictUtils.getDictMap(GParameter.workCurState_key);
		Map<String, String> workTypeMap = DictUtils.getDictMap(GParameter.workType_key);
		Map<String, String> workLevelMap = DictUtils.getDictMap(GParameter.workLevel_key);
		model.addAttribute("types", "02");
		model.addAttribute("page", page);
		model.addAttribute("workCurStateList", workCurStateList);
		model.addAttribute("workTypeMap", workTypeMap);
		model.addAttribute("workCurStateMap", workCurStateMap);
		model.addAttribute("workLevelMap", workLevelMap);
 		return "/modules/workOrder/workManageList";
	}
	
	//跳转结单页面
	@RequestMapping(value="deal")
	public String deal(Work work,Model model,HttpServletRequest req,HttpServletResponse resp){
		List<WorkDevice> workDeviceList = workDeviceService.findListByWorkId(work.getWorkId());
		Map<String, String> deviceInfoMap = DictUtils.getDictMap(GParameter.deviceInfo_key);
		Map<String, String> deviceModelMap = DictUtils.getDictMap(GParameter.deviceModel_key);
		Map<String, String> deviceBardMap = DictUtils.getDictMap(GParameter.deviceBard_key);
		Map<String, String> workDeviceStateMap = DictUtils.getDictMap(GParameter.workDeviceState_key);
		model.addAttribute("deviceInfoMap", deviceInfoMap);
		model.addAttribute("deviceModelMap", deviceModelMap);
		model.addAttribute("deviceBardMap", deviceBardMap);
		model.addAttribute("workDeviceStateMap", workDeviceStateMap);
		model.addAttribute("workDeviceList", workDeviceList);
		model.addAttribute("types", "02");
		model.addAttribute("type", "06");
		return "/modules/workOrder/workManageEdit";
	}
	
	//跳转退单页面
	@RequestMapping(value="back")
	public String back(Work work,Model model,HttpServletRequest req,HttpServletResponse resp){
		work.setHandleResult("");
		List<WorkDevice> workDeviceList = workDeviceService.findListByWorkId(work.getWorkId());
		Map<String, String> deviceInfoMap = DictUtils.getDictMap(GParameter.deviceInfo_key);
		Map<String, String> deviceModelMap = DictUtils.getDictMap(GParameter.deviceModel_key);
		Map<String, String> deviceBardMap = DictUtils.getDictMap(GParameter.deviceBard_key);
		Map<String, String> workDeviceStateMap = DictUtils.getDictMap(GParameter.workDeviceState_key);
		model.addAttribute("deviceInfoMap", deviceInfoMap);
		model.addAttribute("deviceModelMap", deviceModelMap);
		model.addAttribute("deviceBardMap", deviceBardMap);
		model.addAttribute("workDeviceStateMap", workDeviceStateMap);
		model.addAttribute("workDeviceList", workDeviceList);
		model.addAttribute("types", "02");
		model.addAttribute("type", "07");
		return "/modules/workOrder/workManageEdit";
	}
	
	//跳转处理页面
	@RequestMapping(value="solve")
	public String solve(Work work,Model model,HttpServletRequest req,HttpServletResponse resp){
		List<WorkDevice> workDeviceList = workDeviceService.findListByWorkId(work.getWorkId());
		Map<String, String> deviceInfoMap = DictUtils.getDictMap(GParameter.deviceInfo_key);
		Map<String, String> deviceModelMap = DictUtils.getDictMap(GParameter.deviceModel_key);
		Map<String, String> deviceBardMap = DictUtils.getDictMap(GParameter.deviceBard_key);
		Map<String, String> workDeviceStateMap = DictUtils.getDictMap(GParameter.workDeviceState_key);
		model.addAttribute("deviceInfoMap", deviceInfoMap);
		model.addAttribute("deviceModelMap", deviceModelMap);
		model.addAttribute("deviceBardMap", deviceBardMap);
		model.addAttribute("workDeviceStateMap", workDeviceStateMap);
		model.addAttribute("workDeviceList", workDeviceList);
		model.addAttribute("types", "02");
		model.addAttribute("type", "08");
		return "/modules/workOrder/workManageEdit";
	}
}
