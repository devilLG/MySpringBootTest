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
import com.zhilai.master.modules.pay.entity.PayResult;
import com.zhilai.master.modules.pay.service.PayResultService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:PayResultController
 *Description:对账结果信息控制层
 *@author ChengJiawei
 *@date 2018年4月23日 下午4:00:09
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/payResult")
public class PayResultController extends BaseController{

	@Autowired
	private PayResultService payResultService;
	
	@ModelAttribute
	public PayResult get(@RequestParam(required=false) String trade_number) {
		if (StringUtils.isNotBlank(trade_number)) {
			return payResultService.get(trade_number);
		}else {
			return new PayResult();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(PayResult payResult,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<PayResult> page = payResultService.findPage(new Page<PayResult>(req, resp), payResult);
		List<Dict> payResultCurStateList = DictUtils.getDictList(GParameter.payResultCurState_key);
		Map<String, String> payResultCurStateMap = DictUtils.getDictMap(GParameter.payResultCurState_key);
		Map<String, String> currencyMap = DictUtils.getDictMap(GParameter.currency_key);
		model.addAttribute("page", page);
		model.addAttribute("payResultCurStateList", payResultCurStateList);
		model.addAttribute("payResultCurStateMap", payResultCurStateMap);
		model.addAttribute("currencyMap", currencyMap);
 		return "/modules/pay/payResultList";
	}
	
	//跳转对账结果信息详情页面
	@RequestMapping(value="form")
	public String form(PayResult payResult,Model model,HttpServletRequest req,HttpServletResponse resp){
		return "/modules/pay/payResultForm";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PayResult payResult, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "对账结果信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PayResult> page = payResultService.findPage(new Page<PayResult>(request, response, -1), payResult);
            List<PayResult> payResults = page.getList();
    		new ExportExcel("对账结果信息", PayResult.class).setDataList(payResults).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出对账结果信息模板失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/pay/payResult/list?repage";
    }
}
