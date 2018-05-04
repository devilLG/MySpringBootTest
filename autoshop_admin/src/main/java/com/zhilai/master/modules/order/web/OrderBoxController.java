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
import com.zhilai.master.modules.order.entity.OrderBox;
import com.zhilai.master.modules.order.service.OrderBoxService;

@Controller
@RequestMapping(value = "${adminPath}/order/orderBox")
public class OrderBoxController extends BaseController {

	@Autowired
	private OrderBoxService orderBoxService;
	@ModelAttribute
	public OrderBox get(@RequestParam(required=false) String id) {
		OrderBox entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderBoxService.get(id);
		}
		if (entity == null){
			entity = new OrderBox();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(OrderBox orderBox, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==orderBox.getBeginTime()||"".equals(orderBox.getBeginTime())) {
			orderBox.setBeginTime(DateUtil.getDate());
		}
		if (null == orderBox.getEndTime() || "".equals(orderBox.getEndTime())) {
			orderBox.setEndTime(DateUtil.getDate());
		}
		Page<OrderBox> page = orderBoxService.findPage(new Page<OrderBox>(request, response), orderBox); 
		model.addAttribute("page", page);
		return "modules/order/orderBoxList";
	}

	
	/**
	 * 明细
	 * @param orderBox
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(OrderBox orderBox, Model model) {
		model.addAttribute("orderBox", orderBox);
		return "modules/order/orderBoxDetail";
	}
	
	/**
	 * 导出
	 * @param orderBox
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(OrderBox orderBox, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "订单货道"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<OrderBox> page = orderBoxService.findPage(new Page<OrderBox>(request, response, -1), orderBox);
            List<OrderBox> orderBoxs = page.getList();
    		new ExportExcel("订单货道", OrderBox.class).setDataList(orderBoxs).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出订单货道失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/order/orderBox/list?repage";
    }
	
}
