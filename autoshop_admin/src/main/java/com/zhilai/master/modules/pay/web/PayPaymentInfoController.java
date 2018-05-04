package com.zhilai.master.modules.pay.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.zhilai.master.modules.pay.entity.PayPaymentInfo;
import com.zhilai.master.modules.pay.service.PayPaymentInfoService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:PayPaymentInfoController
 *Description:支付平台账单控制层
 *@author ChengJiawei
 *@date 2018年4月23日 上午10:17:20
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/payPaymentInfo")
public class PayPaymentInfoController extends BaseController{

	@Autowired
	private PayPaymentInfoService payPaymentInfoService;
	
	@ModelAttribute
	public PayPaymentInfo get(@RequestParam(required=false) String trade_number) {
		if (StringUtils.isNotBlank(trade_number)) {
			return payPaymentInfoService.get(trade_number);
		}else {
			return new PayPaymentInfo();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(PayPaymentInfo payPaymentInfo,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<PayPaymentInfo> page = payPaymentInfoService.findPage(new Page<PayPaymentInfo>(req, resp), payPaymentInfo);
		List<Dict> paymentStateList = DictUtils.getDictList(GParameter.paymentState_key);
		Map<String, String> payChannelMap = DictUtils.getDictMap(GParameter.payChannel_key);
		Map<String, String> currencyMap = DictUtils.getDictMap(GParameter.currency_key);
		Map<String, String> paymentStateMap = DictUtils.getDictMap(GParameter.paymentState_key);
		model.addAttribute("page", page);
		model.addAttribute("paymentStateList", paymentStateList);
		model.addAttribute("payChannelMap", payChannelMap);
		model.addAttribute("currencyMap", currencyMap);
		model.addAttribute("paymentStateMap", paymentStateMap);
 		return "/modules/pay/payPaymentInfoList";
	}
	
	//跳转通知支付平台账单详情页面
	@RequestMapping(value="form")
	public String form(PayPaymentInfo payPaymentInfo,Model model,HttpServletRequest req,HttpServletResponse resp){
		return "/modules/pay/payPaymentInfoForm";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PayPaymentInfo payPaymentInfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "通知支付平台账单"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PayPaymentInfo> page = payPaymentInfoService.findPage(new Page<PayPaymentInfo>(request, response, -1), payPaymentInfo);
            List<PayPaymentInfo> payPaymentInfos = page.getList();
    		new ExportExcel("通知支付平台账单", PayPaymentInfo.class).setDataList(payPaymentInfos).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出通知支付平台账单失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/pay/payPaymentInfo/list?repage";
    }
}
