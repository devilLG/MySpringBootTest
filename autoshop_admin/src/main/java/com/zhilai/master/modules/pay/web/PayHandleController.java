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
import com.zhilai.master.modules.pay.entity.PayHandle;
import com.zhilai.master.modules.pay.service.PayHandleService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:PayHandleController
 *Description:对账异常处理控制层
 *@author ChengJiawei
 *@date 2018年4月23日 下午5:38:17
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/payHandle")
public class PayHandleController extends BaseController{

	@Autowired
	private PayHandleService payHandleService;
	
	@ModelAttribute
	public PayHandle get(@RequestParam(required=false) String trade_number) {
		if (StringUtils.isNotBlank(trade_number)) {
			return payHandleService.get(trade_number);
		}else {
			return new PayHandle();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(PayHandle payHandle,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<PayHandle> page = payHandleService.findPage(new Page<PayHandle>(req, resp), payHandle);
		List<Dict> payResultCurStateList = DictUtils.getDictList(GParameter.payResultCurState_key);
		Map<String, String> payResultCurStateMap = DictUtils.getDictMap(GParameter.payResultCurState_key);
		Map<String, String> payChannelMap = DictUtils.getDictMap(GParameter.payChannel_key);
		Map<String, String> currencyMap = DictUtils.getDictMap(GParameter.currency_key);
		Map<String, String> procStatusMap = DictUtils.getDictMap(GParameter.procStatus_key);
		model.addAttribute("page", page);
		model.addAttribute("payResultCurStateList", payResultCurStateList);
		model.addAttribute("payChannelMap", payChannelMap);
		model.addAttribute("payResultCurStateMap", payResultCurStateMap);
		model.addAttribute("currencyMap", currencyMap);
		model.addAttribute("procStatusMap", procStatusMap);
 		return "/modules/pay/payHandleList";
	}
	
	//跳转对账异常信息详情页面
	@RequestMapping(value="form")
	public String form(PayHandle payHandle,Model model,HttpServletRequest req,HttpServletResponse resp){
		return "/modules/pay/payHandleForm";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PayHandle payHandle, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "对账异常信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PayHandle> page = payHandleService.findPage(new Page<PayHandle>(request, response, -1), payHandle);
            List<PayHandle> payHandles = page.getList();
    		new ExportExcel("对账异常信息", PayHandle.class).setDataList(payHandles).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出对账异常信息模板失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/pay/payHandle/list?repage";
    }
}
