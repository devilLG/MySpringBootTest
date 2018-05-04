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
import com.zhilai.master.modules.inventory.entity.ReplenishmentOrder;
import com.zhilai.master.modules.inventory.entity.ReplenishmentOrderDetail;
import com.zhilai.master.modules.inventory.service.ReplenishmentOrderDetailService;
import com.zhilai.master.modules.inventory.service.ReplenishmentOrderService;

@Controller
@RequestMapping(value = "${adminPath}/inventory/replenishmentOrder")
public class ReplenishmentOrderController extends BaseController {

	@Autowired
	private ReplenishmentOrderService replenishmentOrderService;
	@Autowired
	private ReplenishmentOrderDetailService replenishmentOrderDetailService;
	
	@ModelAttribute
	public ReplenishmentOrder get(@RequestParam(required=false) String id) {
		ReplenishmentOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = replenishmentOrderService.get(id);
		}
		if (entity == null){
			entity = new ReplenishmentOrder();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ReplenishmentOrder replenishmentOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==replenishmentOrder.getBeginTime()||"".equals(replenishmentOrder.getBeginTime())) {
			replenishmentOrder.setBeginTime(DateUtil.getDate());
		}
		if (null == replenishmentOrder.getEndTime() || "".equals(replenishmentOrder.getEndTime())) {
			replenishmentOrder.setEndTime(DateUtil.getDate());
		}
		Page<ReplenishmentOrder> page = replenishmentOrderService.findPage(new Page<ReplenishmentOrder>(request, response), replenishmentOrder); 
		model.addAttribute("page", page);
		return "modules/inventory/replenishmentOrderList";
	}

	
	/**
	 * 明细
	 * @param replenishmentOrder
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(ReplenishmentOrder replenishmentOrder, Model model) {
		model.addAttribute("replenishmentOrder", replenishmentOrder);
		ReplenishmentOrderDetail replenishmentOrderDetail= new ReplenishmentOrderDetail();
		replenishmentOrderDetail.setOrder_id(replenishmentOrder.getOrder_id());
		List<ReplenishmentOrderDetail> replenishmentOrderDetailList = replenishmentOrderDetailService.findList(replenishmentOrderDetail);
		model.addAttribute("replenishmentOrderDetailList", replenishmentOrderDetailList);
		return "modules/inventory/replenishmentOrderDetail";
	}
	
	/**
	 * 导出
	 * @param replenishmentOrder
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ReplenishmentOrder replenishmentOrder, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "站点补货"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<ReplenishmentOrder> page = replenishmentOrderService.findPage(new Page<ReplenishmentOrder>(request, response, -1), replenishmentOrder);
            List<ReplenishmentOrder> replenishmentOrders = page.getList();
    		new ExportExcel("站点补货", ReplenishmentOrder.class).setDataList(replenishmentOrders).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出站点补货失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/inventory/replenishmentOrder/list?repage";
    }
	
}
