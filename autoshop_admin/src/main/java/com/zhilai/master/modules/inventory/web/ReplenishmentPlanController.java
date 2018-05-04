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
import com.zhilai.master.modules.inventory.entity.ReplenishmentPlan;
import com.zhilai.master.modules.inventory.service.ReplenishmentPlanService;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.sys.utils.UserUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 * @ClassName ReplenishmentPlanController
 * @Description 补货计划控制台
 * @author GuoXuegan
 * @data 2018年4月3日下午2:53:40
 *
 */
@Controller
@RequestMapping(value="${adminPath}/inventory/replenishmentPlan")
public class ReplenishmentPlanController extends BaseController {

	@Autowired
	private ReplenishmentPlanService replenishmentPlanService;
	
	@ModelAttribute
	public ReplenishmentPlan get(@RequestParam(required=false) String planId) {
		if (StringUtils.isNotBlank(planId)) {
			return replenishmentPlanService.get(planId);
		}else {
			return new ReplenishmentPlan();
		}
	}
	
	@RequestMapping(value={"","list"})
	public String list(ReplenishmentPlan replenishmentPlan, Model model, HttpServletRequest request, HttpServletResponse response) {
		if (replenishmentPlan.getPlanType() != null && !replenishmentPlan.getPlanType().equals("")) {
			if (replenishmentPlan.getPlanType().equals(GParameter.planType_site)) {
				replenishmentPlan.setTargetId(replenishmentPlan.getSite_id());
				replenishmentPlan.setTargetName(replenishmentPlan.getSite_name());
			}else {
				replenishmentPlan.setTargetId(replenishmentPlan.getArea_code());
				replenishmentPlan.setTargetName(replenishmentPlan.getArea_name());
			}
		}
		Page<ReplenishmentPlan> page = replenishmentPlanService.findPage(new Page<ReplenishmentPlan>(request, response), replenishmentPlan);
		Map<String, String> planTypeMap = DictUtils.getDictMap(GParameter.planType_key);
		Map<String, String> cycleTypeMap = DictUtils.getDictMap(GParameter.cycleType_key);
		Map<String, String> curStateMap = DictUtils.getDictMap(GParameter.curState_key);
		
		model.addAttribute("page", page);
		model.addAttribute("planTypeMap", planTypeMap);
		model.addAttribute("cycleTypeMap", cycleTypeMap);
		model.addAttribute("curStateMap", curStateMap);
		return "modules/inventory/replenishmentPlan/replenishmentPlanList";
	}
	
	@RequestMapping(value="form")
	public String form(ReplenishmentPlan replenishmentPlan, Model model) {
		if (StringUtils.isBlank(replenishmentPlan.getPlanId())) {
			replenishmentPlan.setPlanType(GParameter.planType_site);
			replenishmentPlan.setCycleType(GParameter.cycleType_week);
			replenishmentPlan.setCycleValue(1);
		}
		return "modules/inventory/replenishmentPlan/replenishmentPlanForm";
	}
	
	@RequestMapping(value="save")
	public String save(ReplenishmentPlan replenishmentPlan, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		replenishmentPlanService.save(replenishmentPlan, user);
		addMessage(redirectAttributes, "添加成功");
		return "redirect:"+adminPath+"/inventory/replenishmentPlan?repage";
	}
	
	@RequestMapping(value="delete")
	public String delete(ReplenishmentPlan replenishmentPlan, RedirectAttributes redirectAttributes) {
		if (replenishmentPlan == null) {
			addMessage(redirectAttributes, "删除失败，请刷新页面重试！");
			return "redirect:"+adminPath+"/inventory/replenishmentPlan?repage";
		}
		if (replenishmentPlan.getPlanId() == null) {
			addMessage(redirectAttributes, "删除失败，请刷新页面重试！");
			return "redirect:"+adminPath+"/inventory/replenishmentPlan?repage";
		}
		if (replenishmentPlan.getCurState().equals(GParameter.Cur_state_normal)) {
			addMessage(redirectAttributes, "删除失败，未失效，不允许删除！");
			return "redirect:"+adminPath+"/inventory/replenishmentPlan?repage";
		}
		replenishmentPlanService.delete(replenishmentPlan);
		addMessage(redirectAttributes, "删除成功！");
		return "redirect:"+adminPath+"/inventory/replenishmentPlan?repage";
	}
	
	/**
	 * 
	 * @Title exportFile
	 * @Description 导出
	 * @Author GuoXuegan
	 * @data 2018年4月8日下午2:40:16
	 * @param replenishmentPlan
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 *
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ReplenishmentPlan replenishmentPlan, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "补货计划"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<ReplenishmentPlan> page = replenishmentPlanService.findPage(new Page<ReplenishmentPlan>(request, response, -1), replenishmentPlan);
            List<ReplenishmentPlan> replenishmentOrders = page.getList();
    		new ExportExcel("补货计划", ReplenishmentPlan.class).setDataList(replenishmentOrders).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出站点补货失败！失败信息："+e.getMessage());
		}
		return "redirect:"+adminPath+"/inventory/replenishmentPlan?repage";
    }
}
