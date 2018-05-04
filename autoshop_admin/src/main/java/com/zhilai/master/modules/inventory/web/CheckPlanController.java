package com.zhilai.master.modules.inventory.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.putils.utils.StringUtils;
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
import com.zhilai.master.modules.inventory.entity.CheckPlan;
import com.zhilai.master.modules.inventory.service.CheckPlanService;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.sys.utils.UserUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 * @ClassName CheckPlanController
 * @Description 盘点计划控制台
 * @author GuoXuegan
 * @data 2018年4月9日下午3:20:35
 *
 */
@Controller
@RequestMapping(value="${adminPath}/inventory/checkPlan")
public class CheckPlanController extends BaseController {

	@Autowired
	private CheckPlanService checkPlanService;
	
	@ModelAttribute
	public CheckPlan get(@RequestParam(required=false) String planId) {
		if (StringUtils.isNotBlank(planId)) {
			return checkPlanService.get(planId);
		}else {
			return new CheckPlan();
		}
	}
	
	@RequestMapping(value={"","list"})
	public String list(CheckPlan checkPlan, Model model, HttpServletRequest request, HttpServletResponse response) {
		if (checkPlan.getPlanType() != null && !checkPlan.getPlanType().equals("")) {
			if (checkPlan.getPlanType().equals(GParameter.planType_site)) {
				checkPlan.setTargetId(checkPlan.getSite_id());
				checkPlan.setTargetName(checkPlan.getSite_name());
			}else {
				checkPlan.setTargetId(checkPlan.getArea_code());
				checkPlan.setTargetName(checkPlan.getArea_name());
			}
		}
		Page<CheckPlan> page = checkPlanService.findPage(new Page<CheckPlan>(request, response), checkPlan);
		Map<String, String> planTypeMap = DictUtils.getDictMap(GParameter.planType_key);
		Map<String, String> cycleTypeMap = DictUtils.getDictMap(GParameter.cycleType_key);
		Map<String, String> curStateMap = DictUtils.getDictMap(GParameter.curState_key);
		Map<String, String> checkObjMap = DictUtils.getDictMap(GParameter.checkObj_key);
		Map<String, String> checkTypeMap = DictUtils.getDictMap(GParameter.checkType_key);
		
		model.addAttribute("page", page);
		model.addAttribute("planTypeMap", planTypeMap);
		model.addAttribute("cycleTypeMap", cycleTypeMap);
		model.addAttribute("curStateMap", curStateMap);
		model.addAttribute("checkObjMap", checkObjMap);
		model.addAttribute("checkTypeMap", checkTypeMap);
		return "modules/inventory/check/checkPlanList";
	}
	
	@RequestMapping(value="form")
	public String form(CheckPlan checkPlan, Model model) {
		if (StringUtils.isBlank(checkPlan.getPlanId())) {
			checkPlan.setPlanType(GParameter.planType_site);
			checkPlan.setCycleType(GParameter.cycleType_week);
			checkPlan.setCycleValue("1");
		}
		return "modules/inventory/check/checkPlanForm";
	}
	
	@RequestMapping(value="save")
	public String save(CheckPlan checkPlan, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		checkPlanService.save(checkPlan, user);
		addMessage(redirectAttributes, "添加成功");
		return "redirect:"+adminPath+"/inventory/checkPlan?repage";
	}
	
	@RequestMapping(value="delete")
	public String delete(CheckPlan checkPlan, RedirectAttributes redirectAttributes) {
		if (checkPlan == null) {
			addMessage(redirectAttributes, "删除失败，请刷新页面重试！");
			return "redirect:"+adminPath+"/inventory/checkPlan?repage";
		}
		if (checkPlan.getPlanId() == null) {
			addMessage(redirectAttributes, "删除失败，请刷新页面重试！");
			return "redirect:"+adminPath+"/inventory/checkPlan?repage";
		}
		if (checkPlan.getCurState().equals(GParameter.Cur_state_normal)) {
			addMessage(redirectAttributes, "删除失败，未失效，不允许删除！");
			return "redirect:"+adminPath+"/inventory/checkPlan?repage";
		}
		checkPlanService.delete(checkPlan);
		addMessage(redirectAttributes, "删除成功！");
		return "redirect:"+adminPath+"/inventory/checkPlan?repage";
	}
	
	/**
	 * 
	 * @Title exportFile
	 * @Description 导出
	 * @Author GuoXuegan
	 * @data 2018年4月8日下午2:40:16
	 * @param checkPlan
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 *
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CheckPlan checkPlan, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "盘点计划"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<CheckPlan> page = checkPlanService.findPage(new Page<CheckPlan>(request, response, -1), checkPlan);
            List<CheckPlan> replenishmentOrders = page.getList();
    		new ExportExcel("补货计划", CheckPlan.class).setDataList(replenishmentOrders).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出盘点计划失败！失败信息："+e.getMessage());
		}
		return "redirect:"+adminPath+"/inventory/checkPlan?repage";
    }
}
