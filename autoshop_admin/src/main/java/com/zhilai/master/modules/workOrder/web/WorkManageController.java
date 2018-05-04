package com.zhilai.master.modules.workOrder.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.monitor.entity.SiteEnv;
import com.zhilai.master.modules.monitor.service.SiteEnvService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.sys.utils.UserUtils;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.workOrder.entity.Work;
import com.zhilai.master.modules.workOrder.entity.WorkChange;
import com.zhilai.master.modules.workOrder.entity.WorkDevice;
import com.zhilai.master.modules.workOrder.service.WorkChangeService;
import com.zhilai.master.modules.workOrder.service.WorkDeviceService;
import com.zhilai.master.modules.workOrder.service.WorkService;

/**
 * 
 *Title:WorkManageController
 *Description:工单管理控制层
 *@author ChengJiawei
 *@date 2018年4月24日 上午10:11:25
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/workOrder/workManage")
public class WorkManageController extends BaseController{

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
		Page<Work> page = workService.findPage(new Page<Work>(req, resp), work);
		List<Dict> workCurStateList = DictUtils.getDictList(GParameter.workCurState_key);
		Map<String, String> workCurStateMap = DictUtils.getDictMap(GParameter.workCurState_key);
		Map<String, String> workTypeMap = DictUtils.getDictMap(GParameter.workType_key);
		Map<String, String> workLevelMap = DictUtils.getDictMap(GParameter.workLevel_key);
		model.addAttribute("types", "01");
		model.addAttribute("page", page);
		model.addAttribute("workCurStateList", workCurStateList);
		model.addAttribute("workTypeMap", workTypeMap);
		model.addAttribute("workCurStateMap", workCurStateMap);
		model.addAttribute("workLevelMap", workLevelMap);
 		return "/modules/workOrder/workManageList";
	}
	
	//跳转添加、修改工单页面
	@RequestMapping(value="form")
	public String form(Work work,Model model,HttpServletRequest req,HttpServletResponse resp){
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
		model.addAttribute("types", "01");
		if (null!=work.getLogid()&&!work.getLogid().equals("")) {
			model.addAttribute("type", "01");
			return "/modules/workOrder/workManageEdit";
		}
		return "/modules/workOrder/workManageForm";
	}
	
	//跳转受理工单页面
	@RequestMapping(value="accept")
	public String accept(Work work,Model model,HttpServletRequest req,HttpServletResponse resp){
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
		model.addAttribute("types", "01");
		model.addAttribute("type", "02");
		return "/modules/workOrder/workManageEdit";
	}
	
	//跳转委派工单页面
	@RequestMapping(value="delegation")
	public String delegation(Work work,Model model,HttpServletRequest req,HttpServletResponse resp){
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
		model.addAttribute("types", "01");
		model.addAttribute("type", "03");
		return "/modules/workOrder/workManageEdit";
	}
	
    //撤单
	@RequestMapping(value = "remove")
	public String remove(Work work,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
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
		model.addAttribute("types", "01");
		model.addAttribute("type", "04");
		return "/modules/workOrder/workManageEdit";
	}
	
    //结单
	@RequestMapping(value = "end")
	public String end(Work work,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
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
		model.addAttribute("types", "01");
		model.addAttribute("type", "05");
		return "/modules/workOrder/workManageEdit";
	}
	
    //保存信息
	@RequestMapping(value = "save")
	public String save(Work work,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		String type = request.getParameter("type");
		WorkChange workChange = new WorkChange();
		if (null!=type&&!type.equals("")) {
			if (type.equals("02")) {
				work.setCurState("02");
				work.setStateTime(DateUtils.getDateTime());
			} else if (type.equals("03")) {
				work.setCurState("03");
				work.setStateTime(DateUtils.getDateTime());
			} else if (type.equals("04")) {
				work.setCurState("08");
				work.setStateTime(DateUtils.getDateTime());
			} else if (type.equals("05")) {
				work.setCurState("07");
				work.setStateTime(DateUtils.getDateTime());
			} else if (type.equals("06")) {
				work.setCurState("04");
				work.setStateTime(DateUtils.getDateTime());
			} else if (type.equals("07")) {
				work.setHandleResult("退回,原因:"+work.getHandleResult());
				work.setCurState("05");
				work.setStateTime(DateUtils.getDateTime());
			} else if (type.equals("08")) {
				work.setCurState("06");
				work.setStateTime(DateUtils.getDateTime());
			}
		}
		if (null==work.getLogid()||work.getLogid().equals("")) {
			work.setWorkId(System.nanoTime()+"");
			workChange.setCorpId(UserUtils.getUser().getCorpId());
			workChange.setCorpName(UserUtils.getUser().getCompanyName());
			workChange.setWorkId(work.getWorkId());
			workChange.setWorkTitle(work.getWorkTitle());
			workChange.setOperAction("01");
			workChange.setOperTime(DateUtils.getDateTime());
			work.setCurState(workChange.getOperAction());
			work.setStateTime(workChange.getOperTime());
			workChange.setOperId(UserUtils.getUser().getLoginName());
			workChange.setOperName(UserUtils.getUser().getName());
			workChange.setPerCont(work.getHandleResult());
			workChangeService.save(workChange);
		} else if (!type.equals("01")) {
			workChange.setCorpId(work.getCorpId());
			workChange.setCorpName(work.getCorpName());
			workChange.setWorkId(work.getWorkId());
			workChange.setWorkTitle(work.getWorkTitle());
			workChange.setOperAction(work.getCurState());
			workChange.setOperTime(work.getStateTime());
			workChange.setOperId(UserUtils.getUser().getLoginName());
			workChange.setOperName(UserUtils.getUser().getName());
			if (type.equals("07")||type.equals("08")) {
				workChange.setPerCont(work.getHandleResult());
			} else {
				Map<String, String> workCurStateMap = DictUtils.getDictMap(GParameter.workCurState_key);
				String cont = workCurStateMap.get(work.getCurState());
				workChange.setPerCont(cont);
			}
			workChangeService.save(workChange);
		}
		workService.save(work);
		addMessage(redirectAttributes, "操作成功");
		if (null==type||type.equals("")) {
			return "redirect:" + adminPath + "/workOrder/workManage/list?repage";
		} else if(type.equals("06")||type.equals("07")||type.equals("08")){
			return "redirect:" + adminPath + "/workOrder/workSolving/list?repage";
		} else {
			return "redirect:" + adminPath + "/workOrder/workManage/list?repage";
		}
	}
	
	//查看详情	
	@RequestMapping(value = "detailDevice/{primaryKey}")
	public String detailDevice(@PathVariable("primaryKey") String primaryKey, Model model){
		WorkDevice workDevice = workDeviceService.get(primaryKey);
		List<SiteEnv> siteEnvList = siteEnvService.findBySdeviceId(workDevice.getDeviceId());
		Map<String, String> workDeviceStateMap = DictUtils.getDictMap(GParameter.workDeviceState_key);
		model.addAttribute("workDeviceStateMap", workDeviceStateMap);
		model.addAttribute("workDevice", workDevice);
		model.addAttribute("siteEnvList", siteEnvList);
		return "/modules/workOrder/detailWorkDevice";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(Work work, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "工单"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Work> page = workService.findPage(new Page<Work>(request, response, -1), work);
            List<Work> works = page.getList();
    		new ExportExcel("工单", Work.class).setDataList(works).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出工单失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/workOrder/workOrder/list?repage";
    }
	
	
}

