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
import com.zhilai.master.modules.pay.entity.PayManageInfo;
import com.zhilai.master.modules.pay.service.PayManageInfoService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:PayManageInfoController
 *Description:管理台账单控制层
 *@author ChengJiawei
 *@date 2018年4月23日 下午2:50:08
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/payManageInfo")
public class PayManageInfoController extends BaseController{

	@Autowired
	private PayManageInfoService payManageInfoService;
	
	@ModelAttribute
	public PayManageInfo get(@RequestParam(required=false) String trade_number) {
		if (StringUtils.isNotBlank(trade_number)) {
			return payManageInfoService.get(trade_number);
		}else {
			return new PayManageInfo();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(PayManageInfo payManageInfo,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<PayManageInfo> page = payManageInfoService.findPage(new Page<PayManageInfo>(req, resp), payManageInfo);
		List<Dict> paymentStateList = DictUtils.getDictList(GParameter.paymentState_key);
		Map<String, String> payChannelMap = DictUtils.getDictMap(GParameter.payChannel_key);
		Map<String, String> currencyMap = DictUtils.getDictMap(GParameter.currency_key);
		Map<String, String> paymentStateMap = DictUtils.getDictMap(GParameter.paymentState_key);
		model.addAttribute("page", page);
		model.addAttribute("paymentStateList", paymentStateList);
		model.addAttribute("payChannelMap", payChannelMap);
		model.addAttribute("currencyMap", currencyMap);
		model.addAttribute("paymentStateMap", paymentStateMap);
 		return "/modules/pay/payManageInfoList";
	}
	
	//跳转通知支付平台账单详情页面
	@RequestMapping(value="form")
	public String form(PayManageInfo payManageInfo,Model model,HttpServletRequest req,HttpServletResponse resp){
		return "/modules/pay/payManageInfoForm";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PayManageInfo payManageInfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "通知支付平台账单"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PayManageInfo> page = payManageInfoService.findPage(new Page<PayManageInfo>(request, response, -1), payManageInfo);
            List<PayManageInfo> payManageInfos = page.getList();
    		new ExportExcel("通知支付平台账单", PayManageInfo.class).setDataList(payManageInfos).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出通知支付平台账单失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/pay/payManageInfo/list?repage";
    }
}
