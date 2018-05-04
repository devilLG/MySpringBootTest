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
import com.zhilai.master.modules.inventory.entity.CheckChange;
import com.zhilai.master.modules.inventory.service.CheckChangeService;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 * @ClassName CheckChangeController
 * @Description 盘点日志
 * @author GuoXuegan
 * @data 2018年4月10日上午9:25:46
 *
 */
@Controller
@RequestMapping(value="${adminPath}/inventory/checkChange")
public class CheckChangeController extends BaseController {

	@Autowired
	private CheckChangeService checkChangeService;
	
	@ModelAttribute
	public CheckChange get(@RequestParam(required=false)String changeId) {
		if (StringUtils.isNotBlank(changeId)) {
			return checkChangeService.get(changeId);
		}else {
			return new CheckChange();
		}
	}
	
	@RequestMapping(value={"","list"})
	public String list(CheckChange checkChange, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CheckChange> page = checkChangeService.findPage(new Page<CheckChange>(request, response), checkChange);
		Map<String, String> operActionMap = DictUtils.getDictMap(GParameter.checkChangeAction_key);
		
		model.addAttribute("page", page);
		model.addAttribute("operActionMap", operActionMap);
		return "modules/inventory/check/checkChangeList";
	}
	
	/**
	 * 
	 * @Title exportFile
	 * @Description 导出
	 * @Author GuoXuegan
	 * @data 2018年4月8日下午2:40:16
	 * @param checkChange
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 *
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CheckChange checkChange, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "盘点日志"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<CheckChange> page = checkChangeService.findPage(new Page<CheckChange>(request, response, -1), checkChange);
            List<CheckChange> replenishmentOrders = page.getList();
    		new ExportExcel("盘点日志", CheckChange.class).setDataList(replenishmentOrders).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出盘点日志失败！失败信息："+e.getMessage());
		}
		return "redirect:"+adminPath+"/inventory/checkChange?repage";
    }
	
}
