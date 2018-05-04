package com.zhilai.master.modules.inventory.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSON;
import com.zhilai.master.common.persistence.Page;

import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.inventory.entity.BarterOrder;
import com.zhilai.master.modules.inventory.entity.BarterOrderDetail;
import com.zhilai.master.modules.inventory.service.BarterOrderDetailService;
import com.zhilai.master.modules.inventory.service.BarterOrderService;
import com.zhilai.master.modules.site.entity.AsSite;
import com.zhilai.master.modules.site.service.AsSiteService;
import com.zhilai.master.modules.sys.service.SequenceIdService;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.utils.heartbeat.HeartBeat;
import com.zhilai.master.modules.utils.heartbeat.SiteNoticeHeartBeat;

@Controller
@RequestMapping(value = "${adminPath}/inventory/barterOrder")
public class BarterOrderController extends BaseController {

	@Autowired
	private BarterOrderService barterOrderService;
	@Autowired
	private BarterOrderDetailService barterOrderDetailService;
	@Autowired
	private SequenceIdService sequenceIdService;
	@Autowired
	private AsSiteService siteService;
	@Autowired
	private SiteNoticeHeartBeat siteNoticeHeartBeat;
	
	@ModelAttribute
	public BarterOrder get(@RequestParam(required=false) String id) {
		BarterOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = barterOrderService.get(id);
		}
		if (entity == null){
			entity = new BarterOrder();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(BarterOrder barterOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==barterOrder.getBeginTime()||"".equals(barterOrder.getBeginTime())) {
			barterOrder.setBeginTime(DateUtil.getDate());
		}
		if (null == barterOrder.getEndTime() || "".equals(barterOrder.getEndTime())) {
			barterOrder.setEndTime(DateUtil.getDate());
		}
		Page<BarterOrder> page = barterOrderService.findPage(new Page<BarterOrder>(request, response), barterOrder); 
		model.addAttribute("page", page);
		return "modules/inventory/barterOrderList";
	}
	
	@RequestMapping(value = "form")
	public String form(BarterOrder barterOrder, Model model, RedirectAttributes redirectAttributes) {
		if(barterOrder.getLogid() != null){
			if(barterOrder.getBarter_state().equals(GParameter.barterOrderState_finish)){
				addMessage(redirectAttributes, "该换货单已换货完成，不允许修改");
				return "redirect:" + adminPath + "/inventory/barterOrder/";
			}
			BarterOrderDetail barterOrderDetailP = new BarterOrderDetail();
			barterOrderDetailP.setOrder_id(barterOrder.getOrder_id());
			List<BarterOrderDetail> barterOrderDetailList = barterOrderDetailService.findList(barterOrderDetailP);
			if(barterOrder.getBarter_type() == null || "".equals(barterOrder.getBarter_type())){
				if(barterOrderDetailList != null && barterOrderDetailList.size() > 0){
					if(barterOrderDetailList.get(0).getBox_id() != null && !barterOrderDetailList.get(0).getBox_id().equals("")){
						barterOrder.setBarter_type(GParameter.barterOrderType_goods);
					}else{
						barterOrder.setBarter_type(GParameter.barterOrderType_product);
					}
				}
			}
			model.addAttribute("barterOrderDetailList", barterOrderDetailList);
		}
		model.addAttribute("barterOrder", barterOrder);
		return "modules/inventory/barterOrderForm";
	}

	@RequestMapping(value = "save")
	public String save(BarterOrder barterOrder, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, barterOrder)){
			return form(barterOrder, model, redirectAttributes);
		}
		AsSite site = siteService.get(barterOrder.getSite_id());
		barterOrder.setCorp_id(site.getOwner_id());
		barterOrder.setCorp_name(site.getOwner_name());
		if (StringUtils.isBlank(barterOrder.getLogid())){
			barterOrder.setOrder_id(barterOrder.getCorp_id() + "-" + sequenceIdService.findNextVal(barterOrder.getCorp_id(), "barterOrder_id", 7));
		}
		BarterOrderDetail barterOrderDetailP = new BarterOrderDetail();
		barterOrderDetailP.setOrder_id(barterOrder.getOrder_id());
		barterOrderDetailService.delete(barterOrderDetailP);
		String[] box_id = request.getParameterValues("box_id");
		String[] bproduct_id = request.getParameterValues("bproduct_id");
		String[] bproduct_name = request.getParameterValues("bproduct_name");
		String[] product_id = request.getParameterValues("product_id");
		String[] product_name = request.getParameterValues("product_name");
		String[] invalid_date = request.getParameterValues("invalid_date");
		String[] inventory_num = request.getParameterValues("inventory_num");
		String[] warn_num = request.getParameterValues("warn_num");
		for(int i = 0; i < bproduct_id.length;i++){
			BarterOrderDetail barterOrderDetail = new BarterOrderDetail();
			barterOrderDetail.setDetail_id(barterOrder.getCorp_id() + "-" + sequenceIdService.findNextVal(barterOrder.getCorp_id(), "barterOrderDetail_id", 7));
			barterOrderDetail.setOrder_id(barterOrder.getOrder_id());
			barterOrderDetail.setCorp_id(barterOrder.getCorp_id());
			barterOrderDetail.setCorp_name(barterOrder.getCorp_name());
			barterOrderDetail.setSite_id(barterOrder.getSite_id());
			barterOrderDetail.setSite_name(barterOrder.getSite_name());
			if(!box_id[i].equals("")){
				barterOrderDetail.setBox_id(box_id[i]);
			}
			barterOrderDetail.setProduct_id(product_id[i]);
			barterOrderDetail.setProduct_name(product_name[i]);
			barterOrderDetail.setInventory_num(Integer.parseInt(inventory_num[i]));
			barterOrderDetail.setBproduct_id(bproduct_id[i]);
			barterOrderDetail.setBproduct_name(bproduct_name[i]);
			barterOrderDetail.setBarter_date(barterOrder.getBarter_date());
			barterOrderDetail.setInvalid_date(invalid_date[i]);
			barterOrderDetail.setCur_state(GParameter.barterOrderState_wait);
			barterOrderDetail.setState_time(DateUtil.getNow());
			barterOrderDetail.setWarn_num(Integer.parseInt(warn_num[i]));
			barterOrderDetailService.save(barterOrderDetail);
		}
		if(!box_id[0].equals("")){
			barterOrder.setBarter_box(box_id.length);
		}
		barterOrder.setBarter_product(bproduct_id.length);
		barterOrder.setBarter_state(GParameter.barterOrderState_wait);
		barterOrder.setState_time(DateUtil.getNow());
		barterOrder.setDown_state(GParameter.downState_wait);
		barterOrder.setDown_time(DateUtil.getNow());
		barterOrderService.save(barterOrder);
		HeartBeat heartBeat = new HeartBeat();
		heartBeat.setSite_id(barterOrder.getSite_id());
		heartBeat.setSite_name(barterOrder.getSite_name());
		heartBeat.setTrade_code(GParameter.issued_barterOrder_trade_code);
		heartBeat.setTrade_name(GParameter.issued_barterOrder_trade_desc);
		heartBeat.setCreate_time(DateUtil.getNow());
		heartBeat.setUpdate_time(DateUtil.getNow());
		heartBeat.setPush_id(DateUtil.getLogid());
		heartBeat.setIssued_key(barterOrder.getOrder_id());
		siteNoticeHeartBeat.pushHeart(heartBeat);
		addMessage(redirectAttributes, "保存站点换货信息成功");
		return "redirect:" + adminPath + "/inventory/barterOrder/";
	}
	
	@RequestMapping(value = "delete")
	public String delete(BarterOrder barterOrder, RedirectAttributes redirectAttributes) {
		if(!barterOrder.getBarter_state().equals(GParameter.barterOrderState_finish)){
			addMessage(redirectAttributes, "当前未完成的换货单不能删除");
			return "redirect:" + adminPath + "/inventory/barterOrder/";
		}
		barterOrderService.delete(barterOrder);
		BarterOrderDetail barterOrderDetailP = new BarterOrderDetail();
		barterOrderDetailP.setOrder_id(barterOrder.getOrder_id());
		barterOrderDetailService.delete(barterOrderDetailP);
		addMessage(redirectAttributes, "删除站点换货信息成功");
		return "redirect:" + adminPath + "/inventory/barterOrder/";
	}
	
	@RequestMapping(value = "barterOrderDetailProductForm")
	public String barterOrderDetailProductForm(BarterOrderDetail barterOrderDetail, Model model) {
		model.addAttribute("barterOrderDetail", barterOrderDetail);
		return "modules/inventory/barterOrderDetailProductForm";
	}
	
	@RequestMapping(value = "barterOrderDetailGoodsForm")
	public String barterOrderDetailGoodsForm(BarterOrderDetail barterOrderDetail, Model model) {
		model.addAttribute("barterOrderDetail", barterOrderDetail);
		return "modules/inventory/barterOrderDetailGoodsForm";
	}
	
	/**
	 * 明细
	 * @param barterOrder
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(BarterOrder barterOrder, Model model) {
		BarterOrderDetail barterOrderDetail= new BarterOrderDetail();
		barterOrderDetail.setOrder_id(barterOrder.getOrder_id());
		List<BarterOrderDetail> barterOrderDetailList = barterOrderDetailService.findList(barterOrderDetail);
		if(barterOrderDetailList != null && barterOrderDetailList.size() > 0){
			if(barterOrderDetailList.get(0).getBox_id() != null && !barterOrderDetailList.get(0).getBox_id().equals("")){
				barterOrder.setBarter_type(GParameter.barterOrderType_goods);
			}else{
				barterOrder.setBarter_type(GParameter.barterOrderType_product);
			}
		}
		model.addAttribute("barterOrder", barterOrder);
		model.addAttribute("barterOrderDetailList", barterOrderDetailList);
		return "modules/inventory/barterOrderDetail";
	}
	
	/**
	 * 导出
	 * @param barterOrder
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(BarterOrder barterOrder, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "站点换货"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<BarterOrder> page = barterOrderService.findPage(new Page<BarterOrder>(request, response, -1), barterOrder);
            List<BarterOrder> barterOrders = page.getList();
    		new ExportExcel("站点换货", BarterOrder.class).setDataList(barterOrders).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出站点换货失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/inventory/barterOrder/list?repage";
    }
	
	@RequestMapping(value = "checkExist")
	public void checkExist(BarterOrder barterOrder, Model model, HttpServletRequest req, HttpServletResponse resp)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> param = new HashMap<String, Object>();
		List<BarterOrder> barterOrderList = barterOrderService.findExist(barterOrder);
		if(barterOrderList != null && barterOrderList.size() > 0){
			if(barterOrderList.size() > 1){
				param.put("result", "false");
			}else if(barterOrderList.get(0) != null && (barterOrder.getLogid() == null) || (barterOrder.getLogid() != null && !barterOrder.getLogid().equals(barterOrderList.get(0).getLogid()))){
				param.put("result", "false");
			}else{
				param.put("result", "true");
			}
		}else{
			param.put("result", "true");
		}
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setHeader("Cache-Control", "no-cache");
		try {
			resp.getWriter().write(JSON.toJSONString(param));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
