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
import com.zhilai.master.modules.inventory.entity.ReplenishmentChange;
import com.zhilai.master.modules.inventory.service.ReplenishmentChangeService;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 * @ClassName ReplenishmentChangeController
 * @Description 补货日志
 * @author GuoXuegan
 * @data 2018年4月8日下午3:28:53
 *
 */
@Controller
@RequestMapping(value="${adminPath}/inventory/replenishmentChange")
public class ReplenishmentChangeController extends BaseController {

	@Autowired
	private ReplenishmentChangeService replenishmentChangeService;
	
	@ModelAttribute
	public ReplenishmentChange get(@RequestParam(required=false)String changeId) {
		if (StringUtils.isNotBlank(changeId)) {
			return replenishmentChangeService.get(changeId);
		}else {
			return new ReplenishmentChange();
		}
	}
	
	@RequestMapping(value={"","list"})
	public String list(ReplenishmentChange replenishmentChange, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReplenishmentChange> page = replenishmentChangeService.findPage(new Page<ReplenishmentChange>(request, response), replenishmentChange);
		Map<String, String> operActionMap = DictUtils.getDictMap(GParameter.replenishmentAction_key);
		
		model.addAttribute("page", page);
		model.addAttribute("operActionMap", operActionMap);
		return "modules/inventory/replenishmentPlan/replenishmentChangeList";
	}
	
	/**
	 * 
	 * @Title exportFile
	 * @Description 导出
	 * @Author GuoXuegan
	 * @data 2018年4月8日下午2:40:16
	 * @param ReplenishmentChange
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 *
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ReplenishmentChange replenishmentChange, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "补货日志"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<ReplenishmentChange> page = replenishmentChangeService.findPage(new Page<ReplenishmentChange>(request, response, -1), replenishmentChange);
            List<ReplenishmentChange> replenishmentOrders = page.getList();
    		new ExportExcel("补货日志", ReplenishmentChange.class).setDataList(replenishmentOrders).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出补货日志失败！失败信息："+e.getMessage());
		}
		return "redirect:"+adminPath+"/inventory/replenishmentChange?repage";
    }
	
}
