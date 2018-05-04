package com.zhilai.master.modules.order.web;

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
import com.zhilai.master.modules.order.entity.OrderChange;
import com.zhilai.master.modules.order.service.OrderChangeService;

@Controller
@RequestMapping(value = "${adminPath}/order/orderChange")
public class OrderChangeController extends BaseController {

	@Autowired
	private OrderChangeService orderChangeService;
	@ModelAttribute
	public OrderChange get(@RequestParam(required=false) String id) {
		OrderChange entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderChangeService.get(id);
		}
		if (entity == null){
			entity = new OrderChange();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(OrderChange orderChange, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==orderChange.getBeginTime()||"".equals(orderChange.getBeginTime())) {
			orderChange.setBeginTime(DateUtil.getDate());
		}
		if (null == orderChange.getEndTime() || "".equals(orderChange.getEndTime())) {
			orderChange.setEndTime(DateUtil.getDate());
		}
		Page<OrderChange> page = orderChangeService.findPage(new Page<OrderChange>(request, response), orderChange); 
		model.addAttribute("page", page);
		return "modules/order/orderChangeList";
	}

	
	/**
	 * 明细
	 * @param orderChange
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(OrderChange orderChange, Model model) {
		model.addAttribute("orderChange", orderChange);
		return "modules/order/orderChangeDetail";
	}
	
	/**
	 * 导出
	 * @param orderChange
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(OrderChange orderChange, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "订单状态"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<OrderChange> page = orderChangeService.findPage(new Page<OrderChange>(request, response, -1), orderChange);
            List<OrderChange> orderChanges = page.getList();
    		new ExportExcel("订单状态", OrderChange.class).setDataList(orderChanges).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出订单状态失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/order/orderChange/list?repage";
    }
	
}
