package com.zhilai.master.modules.inventory.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.putils.utils.DateUtil;
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
import com.zhilai.master.modules.inventory.entity.CheckOrder;
import com.zhilai.master.modules.inventory.entity.CheckOrderDetail;
import com.zhilai.master.modules.inventory.service.CheckOrderDetailService;
import com.zhilai.master.modules.inventory.service.CheckOrderService;

@Controller
@RequestMapping(value = "${adminPath}/inventory/checkOrder")
public class CheckOrderController extends BaseController {

	@Autowired
	private CheckOrderService checkOrderService;
	@Autowired
	private CheckOrderDetailService checkOrderDetailService;
	
	@ModelAttribute
	public CheckOrder get(@RequestParam(required=false) String id) {
		CheckOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = checkOrderService.get(id);
		}
		if (entity == null){
			entity = new CheckOrder();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CheckOrder checkOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==checkOrder.getBeginTime()||"".equals(checkOrder.getBeginTime())) {
			checkOrder.setBeginTime(DateUtil.getDate());
		}
		if (null == checkOrder.getEndTime() || "".equals(checkOrder.getEndTime())) {
			checkOrder.setEndTime(DateUtil.getDate());
		}
		Page<CheckOrder> page = checkOrderService.findPage(new Page<CheckOrder>(request, response), checkOrder); 
		model.addAttribute("page", page);
		return "modules/inventory/checkOrderList";
	}

	
	/**
	 * 明细
	 * @param checkOrder
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(CheckOrder checkOrder, Model model) {
		model.addAttribute("checkOrder", checkOrder);
		CheckOrderDetail checkOrderDetail= new CheckOrderDetail();
		checkOrderDetail.setOrder_id(checkOrder.getOrder_id());
		List<CheckOrderDetail> checkOrderDetailList = checkOrderDetailService.findList(checkOrderDetail);
		model.addAttribute("checkOrderDetailList", checkOrderDetailList);
		return "modules/inventory/checkOrderDetail";
	}
	
	/**
	 * 导出
	 * @param checkOrder
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CheckOrder checkOrder, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "站点盘点"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<CheckOrder> page = checkOrderService.findPage(new Page<CheckOrder>(request, response, -1), checkOrder);
            List<CheckOrder> checkOrders = page.getList();
    		new ExportExcel("站点盘点", CheckOrder.class).setDataList(checkOrders).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出站点盘点失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/inventory/checkOrder/list?repage";
    }
	
}
