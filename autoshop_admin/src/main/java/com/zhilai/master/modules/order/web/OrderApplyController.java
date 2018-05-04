package com.zhilai.master.modules.order.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
import com.zhilai.master.modules.inventory.entity.InventoryGoods;
import com.zhilai.master.modules.inventory.service.InventoryGoodsService;
import com.zhilai.master.modules.order.entity.OrderApply;
import com.zhilai.master.modules.order.entity.OrderBox;
import com.zhilai.master.modules.order.entity.OrderProduct;
import com.zhilai.master.modules.order.service.OrderApplyService;
import com.zhilai.master.modules.order.service.OrderBoxService;
import com.zhilai.master.modules.order.service.OrderProductService;
import com.zhilai.master.modules.site.entity.AsSite;
import com.zhilai.master.modules.site.service.AsSiteService;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.zxml.ClientCallSoap;
import com.zhilai.master.modules.zxml.GetPayCodeReq;
import com.zhilai.master.modules.zxml.GetPayCodeResp;
import com.zhilai.master.modules.zxml.OrderAbnormalReq;
import com.zhilai.master.modules.zxml.OrderApplyReq;
import com.zhilai.master.modules.zxml.OrderCancelReq;
import com.zhilai.master.modules.zxml.OrderCommonResp;
import com.zhilai.master.modules.zxml.OrderConfirmReq;
import com.zhilai.master.modules.zxml.OrderConfirmResp;
import com.zhilai.master.modules.zxml.OrderFetchReq;
import com.zhilai.master.modules.zxml.OrderPickupReq;
import com.zhilai.master.modules.zxml.OrderRecoverReq;
import com.zhilai.master.modules.zxml.OrderRefundReq;
import com.zhilai.master.modules.zxml.QueryPayStatusReq;
import com.zhilai.master.modules.zxml.QueryPayStatusResp;

@Controller
@RequestMapping(value = "${adminPath}/order/orderApply")
public class OrderApplyController extends BaseController {

	@Autowired
	private OrderApplyService orderApplyService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private OrderBoxService orderBoxService;
	@Autowired
	private AsSiteService siteService;
	@Autowired
	private InventoryGoodsService inventoryGoodsService;
	
	@ModelAttribute
	public OrderApply get(@RequestParam(required=false) String id) {
		OrderApply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderApplyService.get(id);
		}
		if (entity == null){
			entity = new OrderApply();
		}
		return entity;
	}
	
	/**
	 * 列表显示
	 * @param orderApply
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list", ""})
	public String list(OrderApply orderApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null==orderApply.getBeginTime()||"".equals(orderApply.getBeginTime())) {
			orderApply.setBeginTime(DateUtil.getDate());
		}
		if (null == orderApply.getEndTime() || "".equals(orderApply.getEndTime())) {
			orderApply.setEndTime(DateUtil.getDate());
		}
		Page<OrderApply> page = orderApplyService.findPage(new Page<OrderApply>(request, response), orderApply); 
		model.addAttribute("page", page);
		return "modules/order/orderApplyList";
	}
	
	/**
	 * 申请页面
	 * @param orderApply
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "applyForm")
	public String applyForm(OrderApply orderApply, Model model) {
		model.addAttribute("orderApply", orderApply);
		return "modules/order/orderApplyForm";
	}
	
	/**
	 * 申请
	 * @param orderApply
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "apply")
	public String apply(OrderApply orderApply, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, orderApply)){
			return applyForm(orderApply, model);
		}
		List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
		String[] product_id = request.getParameterValues("product_id");
		String[] sale_num = request.getParameterValues("sale_num");
		String[] total_money = request.getParameterValues("total_money");
		for(int i = 0; i < product_id.length;i++){
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setProduct_id(product_id[i]);
			orderProduct.setSale_num(Integer.parseInt(sale_num[i]));
			orderProduct.setSale_price(Double.parseDouble(total_money[i]));
			orderProductList.add(orderProduct);
		}
		OrderApplyReq orderApplyReq = new OrderApplyReq();
		orderApplyReq.setBcode("04");
		orderApplyReq.setTcode("1003");
		orderApplyReq.setAuth_name(ClientCallSoap.getAuthName());
		orderApplyReq.setAuth_id(ClientCallSoap.getAuthId());
		orderApplyReq.setOrderApply(orderApply);
		orderApplyReq.setOrderProductList(orderProductList);
		String req_xml = orderApplyReq.CreateXml();
		if (null==req_xml) {
			addMessage(redirectAttributes, "申请失败");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		String resp_xml = ClientCallSoap.callPartner(ClientCallSoap.getAuthName(), ClientCallSoap.getAuthId(), req_xml);
		JSONObject object = JSONObject.fromObject(resp_xml);
		if(null==resp_xml){
			addMessage(redirectAttributes, "服务器连接异常");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		OrderCommonResp orderCommonResp = new OrderCommonResp();
		orderCommonResp.setJsonObject(object);
		String tips = orderCommonResp.parseXml();
		if (!tips.equals("0")) {
			addMessage(redirectAttributes, "申请失败    " + tips);
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		addMessage(redirectAttributes, "申请成功");
		return "redirect:" + adminPath + "/order/orderApply/";
	}
	
	/**
	 * 取消页面
	 * @param orderApply
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cancelForm")
	public String cancelForm(OrderApply orderApply, Model model) {
		model.addAttribute("orderApply", orderApply);
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setOrder_id(orderApply.getOrder_id());
		List<OrderProduct> orderProductList = orderProductService.findList(orderProduct);
		model.addAttribute("orderProductList", orderProductList);
		return "modules/order/orderCancelForm";
	}
	
	/**
	 * 取消
	 * @param orderApply
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "cancel")
	public String cancel(OrderApply orderApply, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, orderApply)){
			return cancelForm(orderApply, model);
		}
		OrderCancelReq orderCancelReq = new OrderCancelReq();
		orderCancelReq.setBcode("04");
		orderCancelReq.setTcode("1004");
		orderCancelReq.setAuth_name(ClientCallSoap.getAuthName());
		orderCancelReq.setAuth_id(ClientCallSoap.getAuthId());
		orderCancelReq.setOrderApply(orderApply);
		String req_xml = orderCancelReq.CreateXml();
		if (null==req_xml) {
			addMessage(redirectAttributes, "取消失败");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		String resp_xml = ClientCallSoap.callPartner(ClientCallSoap.getAuthName(), ClientCallSoap.getAuthId(), req_xml);
		JSONObject object = JSONObject.fromObject(resp_xml);
		if(null==resp_xml){
			addMessage(redirectAttributes, "服务器连接异常");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		OrderCommonResp orderCommonResp = new OrderCommonResp();
		orderCommonResp.setJsonObject(object);
		String tips = orderCommonResp.parseXml();
		if (!tips.equals("0")) {
			addMessage(redirectAttributes, "取消失败    " + tips);
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		addMessage(redirectAttributes, "取消成功");
		return "redirect:" + adminPath + "/order/orderApply/";
	}
	
	/**
	 * 确认页面
	 * @param orderApply
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "confirmForm")
	public String confirmForm(OrderApply orderApply, Model model) {
		model.addAttribute("orderApply", orderApply);
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setOrder_id(orderApply.getOrder_id());
		List<OrderProduct> orderProductList = orderProductService.findList(orderProduct);
		for(OrderProduct orderProductP : orderProductList){
			InventoryGoods inventoryGoodsP = new InventoryGoods();
			inventoryGoodsP.setSite_id(orderApply.getSite_id());
			inventoryGoodsP.setProduct_id(orderProductP.getProduct_id());
			List<InventoryGoods> inventoryGoodsList = inventoryGoodsService.findList(inventoryGoodsP);
			orderProductP.setInventoryGoodsList(inventoryGoodsList);
		}
		model.addAttribute("orderProductList", orderProductList);
		return "modules/order/orderConfirmForm";
	}
	
	/**
	 * 确认
	 * @param orderApply
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "confirm")
	public String confirm(OrderApply orderApply, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, orderApply)){
			return confirmForm(orderApply, model);
		}
		List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
		String[] product_id = request.getParameterValues("product_id");
		String[] sale_num = request.getParameterValues("sale_num");
		String[] box_id = request.getParameterValues("box_id");
		for(int i = 0; i < product_id.length;i++){
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setProduct_id(product_id[i]);
			orderProduct.setSale_num(Integer.parseInt(sale_num[i]));
			orderProduct.setBox_id(box_id[i]);
			orderProductList.add(orderProduct);
		}
		AsSite site = siteService.get(orderApply.getSite_id());
		OrderConfirmReq orderConfirmReq = new OrderConfirmReq();
		orderConfirmReq.setBcode("03");
		orderConfirmReq.setTcode("1224");
		orderConfirmReq.setAuth_name(site.getAuth_name());
		orderConfirmReq.setAuth_id(site.getAuth_id());
		orderConfirmReq.setOrderApply(orderApply);
		orderConfirmReq.setOrderProductList(orderProductList);
		String req_xml = orderConfirmReq.CreateXml();
		if (null==req_xml) {
			addMessage(redirectAttributes, "确认失败");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		String resp_xml = ClientCallSoap.callDataSynch(site.getAuth_name(), site.getAuth_id(), req_xml);
		JSONObject object = JSONObject.fromObject(resp_xml);
		if(null==resp_xml){
			addMessage(redirectAttributes, "服务器连接异常");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		OrderConfirmResp orderConfirmResp = new OrderConfirmResp();
		orderConfirmResp.setJsonObject(object);
		Map ret = orderConfirmResp.parseXml(resp_xml);
	    if(orderConfirmResp.getRetcode() == null || !orderConfirmResp.getRetcode().equals("0000")){
	    	addMessage(redirectAttributes, "确认失败    错误代码：" + orderConfirmResp.getRetcode());
			return "redirect:" + adminPath + "/order/orderApply/";
	    }
	    String status = (String) ret.get("status");
	    if(status == null || "".equals(status)){
	    	addMessage(redirectAttributes, "确认失败");
			return "redirect:" + adminPath + "/order/orderApply/";
	    }else if(status.equals("02")){
	    	addMessage(redirectAttributes, "确认失败");
			return "redirect:" + adminPath + "/order/orderApply/";
	    }else if(status.equals("03")){
	    	addMessage(redirectAttributes, "未确认");
			return "redirect:" + adminPath + "/order/orderApply/";
	    }else if(status.equals("04")){
	    	addMessage(redirectAttributes, "无效单号");
			return "redirect:" + adminPath + "/order/orderApply/";
	    }else if(status.equals("05")){
	    	addMessage(redirectAttributes, "重复确认");
			return "redirect:" + adminPath + "/order/orderApply/";
	    }
		addMessage(redirectAttributes, "确认成功");
		return "redirect:" + adminPath + "/order/orderApply/";
	}
	
	/**
	 * 支付页面
	 * @param orderApply
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "payForm")
	public String payForm(OrderApply orderApply, Model model) {
		model.addAttribute("orderApply", orderApply);
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setOrder_id(orderApply.getOrder_id());
		List<OrderProduct> orderProductList = orderProductService.findList(orderProduct);
		model.addAttribute("orderProductList", orderProductList);
		return "modules/order/orderPayForm";
	}
	
	/**
	 * 获取支付二维码
	 * @param orderApply
	 * @param model
	 * @param req
	 * @param resp
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "getPayCode")
	public void getPayCode(OrderApply orderApply, Model model, HttpServletRequest req, HttpServletResponse resp)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> param = new HashMap<String, Object>();
		String sale_price = orderApply.getSale_total() * 100 + "";
		orderApply.setSale_total(Double.parseDouble(sale_price.substring(0,sale_price.indexOf("."))));
		AsSite site = siteService.get(orderApply.getSite_id());
		GetPayCodeReq getPayCodeReq = new GetPayCodeReq();
		getPayCodeReq.setBcode("03");
		getPayCodeReq.setTcode("1245");
		getPayCodeReq.setAuth_name(site.getAuth_name());
		getPayCodeReq.setAuth_id(site.getAuth_id());
		getPayCodeReq.setOrderApply(orderApply);
	    String req_xml=getPayCodeReq.CreateXml(); 
	    if(null==req_xml){
	       param.put("error", "error");
	    }
	    String resp_xml=ClientCallSoap.callDelivery(site.getAuth_name(), site.getAuth_id(), req_xml);
	    if(null==resp_xml){
	    	param.put("error", "error");
	     }
	    JSONObject object = JSONObject.fromObject(resp_xml);
	    GetPayCodeResp getPayCodeResp = new GetPayCodeResp();
	    getPayCodeResp.setJsonObject(object);
	    Map ret = getPayCodeResp.parseXml(resp_xml);
	    if(!getPayCodeResp.getRetcode().equals("0000")){
	    	param.put("error", "error");
	    }
	    String img_url = (String) ret.get("img_url");
	    String OutTradeNo = (String) ret.get("OutTradeNo");
	    if(img_url == null || "".equals(img_url)){
	    	param.put("error", "error");
	    }
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setHeader("Cache-Control", "no-cache");
		param.put("img_url", img_url);
		param.put("OutTradeNo", OutTradeNo);
		try {
			resp.getWriter().write(JSON.toJSONString(param));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 确认支付
	 * @param orderApply
	 * @param model
	 * @param req
	 * @param resp
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "confirmPaid")
	public void confirmPaid(OrderApply orderApply, Model model, HttpServletRequest req, HttpServletResponse resp)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> param = new HashMap<String, Object>();
		orderApply.setPay_state(GParameter.payState_paysuccess);
		OrderRefundReq orderRefundReq = new OrderRefundReq();
		orderRefundReq.setBcode("04");
		orderRefundReq.setTcode("1006");
		orderRefundReq.setAuth_name(ClientCallSoap.getAuthName());
		orderRefundReq.setAuth_id(ClientCallSoap.getAuthId());
		orderRefundReq.setOrderApply(orderApply);
		String req_xml = orderRefundReq.CreateXml();
		if(null==req_xml){
		   param.put("error", "error");
		}
		String resp_xml = ClientCallSoap.callPartner(ClientCallSoap.getAuthName(), ClientCallSoap.getAuthId(), req_xml);
	    if(null==resp_xml){
	    	param.put("error", "error");
	     }
	    JSONObject object = JSONObject.fromObject(resp_xml);
	    OrderCommonResp orderCommonResp = new OrderCommonResp();
	    orderCommonResp.setJsonObject(object);
	    String tips = orderCommonResp.parseXml();
		if (!tips.equals("0")) {
			param.put("error", "error");
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
	
	/**
	 * 查询支付状态
	 * @param orderApply
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "queryPayStatus")
	public void queryPayStatus(OrderApply orderApply, Model model, RedirectAttributes redirectAttributes, HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> param = new HashMap<String, Object>();
		AsSite site = siteService.get(orderApply.getSite_id());
		QueryPayStatusReq queryPayStatusReq = new QueryPayStatusReq();
		queryPayStatusReq.setBcode("03");
		queryPayStatusReq.setTcode("1246");
		queryPayStatusReq.setAuth_name(site.getAuth_name());
		queryPayStatusReq.setAuth_id(site.getAuth_id());
		queryPayStatusReq.setOrderApply(orderApply);
		String req_xml = queryPayStatusReq.CreateXml();
		if (null==req_xml) {
			param.put("error", "error");
		}
		String resp_xml = ClientCallSoap.callDelivery(site.getAuth_name(), site.getAuth_id(), req_xml);
		if(null==resp_xml){
			param.put("error", "error");
		}
		JSONObject object = JSONObject.fromObject(resp_xml);
		QueryPayStatusResp queryPayStatusResp = new QueryPayStatusResp();
		queryPayStatusResp.setJsonObject(object);
	    Map ret = queryPayStatusResp.parseXml(resp_xml);
	    if(!queryPayStatusResp.getRetcode().equals("0000")){
	    	param.put("error", "error");
	    }
	    String pay_state = (String) ret.get("pay_state");
    	if(pay_state == null || "".equals(pay_state)){
    		param.put("error", "error");
    	}
    	resp.setContentType("application/json");
    	resp.setCharacterEncoding("UTF-8");
    	resp.setHeader("Cache-Control", "no-cache");
    	param.put("pay_state", pay_state);
    	try {
    		resp.getWriter().write(JSON.toJSONString(param));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	/**
	 * 支付成功
	 * @param orderApply
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "paySuccess")
	public String paySuccess(OrderApply orderApply, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, orderApply)){
			return applyForm(orderApply, model);
		}
		addMessage(redirectAttributes, "支付成功");
		return "redirect:" + adminPath + "/order/orderApply/";
	}
	
	/**
	 * 提货页面
	 * @param orderApply
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "pickupForm")
	public String pickupForm(OrderApply orderApply, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("orderApply", orderApply);
		OrderBox orderBox = new OrderBox();
		orderBox.setOrder_id(orderApply.getOrder_id());
		List<OrderBox> orderBoxList = orderBoxService.findList(orderBox);
		model.addAttribute("orderBoxList", orderBoxList);
		return "modules/order/orderPickupForm";
	}
	
	/**
	 * 提货
	 * @param orderApply
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "pickup")
	public String pickup(OrderApply orderApply, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, orderApply)){
			return pickupForm(orderApply, model, redirectAttributes);
		}
		List<OrderBox> orderBoxList = new ArrayList<OrderBox>();
		String[] product_id = request.getParameterValues("product_id");
		String[] altake_num = request.getParameterValues("altake_num");
		String[] box_id = request.getParameterValues("box_id");
		for(int i = 0; i < product_id.length;i++){
			OrderBox orderBox = new OrderBox();
			orderBox.setProduct_id(product_id[i]);
			orderBox.setAltake_num(Integer.parseInt(altake_num[i]));
			orderBox.setBox_id(box_id[i]);
			orderBoxList.add(orderBox);
		}
		AsSite site = siteService.get(orderApply.getSite_id());
		OrderPickupReq orderPickupReq = new OrderPickupReq();
		orderPickupReq.setBcode("03");
		orderPickupReq.setTcode("1241");
		orderPickupReq.setAuth_name(site.getAuth_name());
		orderPickupReq.setAuth_id(site.getAuth_id());
		orderPickupReq.setOrderApply(orderApply);
		orderPickupReq.setOrderBoxList(orderBoxList);
		String req_xml = orderPickupReq.CreateXml();
		if (null==req_xml) {
			addMessage(redirectAttributes, "提货失败");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		String resp_xml = ClientCallSoap.callDelivery(site.getAuth_name(), site.getAuth_id(), req_xml);
		JSONObject object = JSONObject.fromObject(resp_xml);
		if(null==resp_xml){
			addMessage(redirectAttributes, "服务器连接异常");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		OrderCommonResp orderCommonResp = new OrderCommonResp();
		orderCommonResp.setJsonObject(object);
		String tips = orderCommonResp.parseXml();
		if (!tips.equals("0")) {
			addMessage(redirectAttributes, "提货失败    " + tips);
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		addMessage(redirectAttributes, "提货成功");
		return "redirect:" + adminPath + "/order/orderApply/";
	}

	/**
	 * 取货页面
	 * @param orderApply
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "fetchForm")
	public String fetchForm(OrderApply orderApply, Model model) {
		model.addAttribute("orderApply", orderApply);
		OrderBox orderBox = new OrderBox();
		orderBox.setOrder_id(orderApply.getOrder_id());
		List<OrderBox> orderBoxList = orderBoxService.findList(orderBox);
		model.addAttribute("orderBoxList", orderBoxList);
		return "modules/order/orderFetchForm";
	}
	
	/**
	 * 取货
	 * @param orderApply
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "fetch")
	public String fetch(OrderApply orderApply, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, orderApply)){
			return fetchForm(orderApply, model);
		}
		AsSite site = siteService.get(orderApply.getSite_id());
		OrderFetchReq orderFetchReq = new OrderFetchReq();
		orderFetchReq.setBcode("03");
		orderFetchReq.setTcode("1242");
		orderFetchReq.setAuth_name(site.getAuth_name());
		orderFetchReq.setAuth_id(site.getAuth_id());
		orderFetchReq.setOrderApply(orderApply);
		String req_xml = orderFetchReq.CreateXml();
		if (null==req_xml) {
			addMessage(redirectAttributes, "取货失败");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		String resp_xml = ClientCallSoap.callDelivery(site.getAuth_name(), site.getAuth_id(), req_xml);
		JSONObject object = JSONObject.fromObject(resp_xml);
		if(null==resp_xml){
			addMessage(redirectAttributes, "服务器连接异常");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		OrderCommonResp orderCommonResp = new OrderCommonResp();
		orderCommonResp.setJsonObject(object);
		String tips = orderCommonResp.parseXml();
		if (!tips.equals("0")) {
			addMessage(redirectAttributes, "取货失败    " + tips);
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		addMessage(redirectAttributes, "取货成功");
		return "redirect:" + adminPath + "/order/orderApply/";
	}
	
	/**
	 * 回收页面
	 * @param orderApply
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "recoverForm")
	public String recoverForm(OrderApply orderApply, Model model) {
		model.addAttribute("orderApply", orderApply);
		OrderBox orderBox = new OrderBox();
		orderBox.setOrder_id(orderApply.getOrder_id());
		List<OrderBox> orderBoxList = orderBoxService.findList(orderBox);
		model.addAttribute("orderBoxList", orderBoxList);
		return "modules/order/orderRecoverForm";
	}
	
	/**
	 * 回收
	 * @param orderApply
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "recover")
	public String recover(OrderApply orderApply, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, orderApply)){
			return recoverForm(orderApply, model);
		}
		AsSite site = siteService.get(orderApply.getSite_id());
		OrderRecoverReq orderRecoverReq = new OrderRecoverReq();
		orderRecoverReq.setBcode("03");
		orderRecoverReq.setTcode("1244");
		orderRecoverReq.setAuth_name(site.getAuth_name());
		orderRecoverReq.setAuth_id(site.getAuth_id());
		orderRecoverReq.setOrderApply(orderApply);
		String req_xml = orderRecoverReq.CreateXml();
		if (null==req_xml) {
			addMessage(redirectAttributes, "回收失败");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		String resp_xml = ClientCallSoap.callDelivery(site.getAuth_name(), site.getAuth_id(), req_xml);
		JSONObject object = JSONObject.fromObject(resp_xml);
		if(null==resp_xml){
			addMessage(redirectAttributes, "服务器连接异常");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		OrderCommonResp orderCommonResp = new OrderCommonResp();
		orderCommonResp.setJsonObject(object);
		String tips = orderCommonResp.parseXml();
		if (!tips.equals("0")) {
			addMessage(redirectAttributes, "回收失败    " + tips);
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		addMessage(redirectAttributes, "回收成功");
		return "redirect:" + adminPath + "/order/orderApply/";
	}
	
	/**
	 * 异常页面
	 * @param orderApply
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "abnormalForm")
	public String abnormalForm(OrderApply orderApply, Model model) {
		model.addAttribute("orderApply", orderApply);
		OrderBox orderBox = new OrderBox();
		orderBox.setOrder_id(orderApply.getOrder_id());
		List<OrderBox> orderBoxList = orderBoxService.findList(orderBox);
		model.addAttribute("orderBoxList", orderBoxList);
		return "modules/order/orderAbnormalForm";
	}
	
	/**
	 * 异常
	 * @param orderApply
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "abnormal")
	public String abnormal(OrderApply orderApply, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, orderApply)){
			return abnormalForm(orderApply, model);
		}
		AsSite site = siteService.get(orderApply.getSite_id());
		OrderAbnormalReq orderAbnormalReq = new OrderAbnormalReq();
		orderAbnormalReq.setBcode("03");
		orderAbnormalReq.setTcode("1243");
		orderAbnormalReq.setAuth_name(site.getAuth_name());
		orderAbnormalReq.setAuth_id(site.getAuth_id());
		orderAbnormalReq.setOrderApply(orderApply);
		String req_xml = orderAbnormalReq.CreateXml();
		if (null==req_xml) {
			addMessage(redirectAttributes, "上报异常失败");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		String resp_xml = ClientCallSoap.callDelivery(site.getAuth_name(), site.getAuth_id(), req_xml);
		JSONObject object = JSONObject.fromObject(resp_xml);
		if(null==resp_xml){
			addMessage(redirectAttributes, "服务器连接异常");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		OrderCommonResp orderCommonResp = new OrderCommonResp();
		orderCommonResp.setJsonObject(object);
		String tips = orderCommonResp.parseXml();
		if (!tips.equals("0")) {
			addMessage(redirectAttributes, "上报异常失败    " + tips);
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		addMessage(redirectAttributes, "上报异常成功");
		return "redirect:" + adminPath + "/order/orderApply/";
	}
	
	/**
	 * 退款页面
	 * @param orderApply
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "refundForm")
	public String refundForm(OrderApply orderApply, Model model) {
		model.addAttribute("orderApply", orderApply);
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setOrder_id(orderApply.getOrder_id());
		List<OrderProduct> orderProductList = orderProductService.findList(orderProduct);
		model.addAttribute("orderProductList", orderProductList);
		return "modules/order/orderRefundForm";
	}
	
	/**
	 * 退款
	 * @param orderApply
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "refund")
	public String refund(OrderApply orderApply, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, orderApply)){
			return cancelForm(orderApply, model);
		}
		orderApply.setPay_state(GParameter.payState_allrefund);
		OrderRefundReq orderRefundReq = new OrderRefundReq();
		orderRefundReq.setBcode("04");
		orderRefundReq.setTcode("1006");
		orderRefundReq.setAuth_name(ClientCallSoap.getAuthName());
		orderRefundReq.setAuth_id(ClientCallSoap.getAuthId());
		orderRefundReq.setOrderApply(orderApply);
		String req_xml = orderRefundReq.CreateXml();
		if (null==req_xml) {
			addMessage(redirectAttributes, "退款失败");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		String resp_xml = ClientCallSoap.callPartner(ClientCallSoap.getAuthName(), ClientCallSoap.getAuthId(), req_xml);
		JSONObject object = JSONObject.fromObject(resp_xml);
		if(null==resp_xml){
			addMessage(redirectAttributes, "服务器连接异常");
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		OrderCommonResp orderCommonResp = new OrderCommonResp();
		orderCommonResp.setJsonObject(object);
		String tips = orderCommonResp.parseXml();
		if (!tips.equals("0")) {
			addMessage(redirectAttributes, "退款失败    " + tips);
			return "redirect:" + adminPath + "/order/orderApply/";
		}
		addMessage(redirectAttributes, "退款成功");
		return "redirect:" + adminPath + "/order/orderApply/";
	}
	
	/**
	 * 明细
	 * @param orderApply
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(OrderApply orderApply, Model model) {
		model.addAttribute("orderApply", orderApply);
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setOrder_id(orderApply.getOrder_id());
		List<OrderProduct> orderProductList = orderProductService.findList(orderProduct);
		model.addAttribute("orderProductList", orderProductList);
		return "modules/order/orderApplyDetail";
	}
	
	/**
	 * 导出
	 * @param orderApply
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(OrderApply orderApply, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "销售订单"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<OrderApply> page = orderApplyService.findPage(new Page<OrderApply>(request, response, -1), orderApply);
            List<OrderApply> orderApplys = page.getList();
    		new ExportExcel("销售订单", OrderApply.class).setDataList(orderApplys).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出销售订单失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/order/orderApply/list?repage";
    }
	
}
