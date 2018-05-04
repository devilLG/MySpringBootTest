package com.zhilai.master.modules.trade.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.sys.service.SequenceIdService;
import com.zhilai.master.modules.trade.entity.TradeDefine;
import com.zhilai.master.modules.trade.service.TradeDefineService;

/**
 * @author guowei
 * @date 2017-12-12 下午4:36:40
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/trade/tradeDefine")
public class TradeDefineController extends BaseController {

	@Autowired
	private TradeDefineService tradeDefineService;
	@Autowired
	private SequenceIdService sequenceIdService;

	@ModelAttribute
	public TradeDefine get(@RequestParam(required = false) String trade_code) {
		TradeDefine entity = null;
		if (StringUtils.isNotBlank(trade_code)) {
			entity = tradeDefineService.get(trade_code);
		}
		if (entity == null) {
			entity = new TradeDefine();
		}
		return entity;
	}

	// @RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = { "list", "" })
	public String list(TradeDefine tradeDefine, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TradeDefine> page = tradeDefineService.findPage(new Page<TradeDefine>(request, response), tradeDefine);
		model.addAttribute("page", page);
		return "modules/trade/tradeDefineList";
	}

	// @RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = "form")
	public String form(TradeDefine tradeDefine, Model model) {
		model.addAttribute("tradeDefine", tradeDefine);
		return "modules/trade/tradeDefineForm";
	}

	// @RequiresPermissions("product:productBrand:edit")
	@RequestMapping(value = "save")
	public String save(TradeDefine tradeDefine, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tradeDefine)) {
			return form(tradeDefine, model);
		}
		if (tradeDefine.getTrade_code() == null || "".equals(tradeDefine.getTrade_code().trim())) {
			tradeDefine.setTrade_code(sequenceIdService.findNextVal("2", "trade_code", 4));
			tradeDefineService.save(tradeDefine);
		} else {
			tradeDefineService.save(tradeDefine);
		}
		addMessage(redirectAttributes, "保存信息成功");
		return "redirect:" + adminPath + "/trade/tradeDefine/";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "delete")
	public String delete(TradeDefine tradeDefine, RedirectAttributes redirectAttributes) {
		tradeDefineService.delete(tradeDefine);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:" + adminPath + "/trade/tradeDefine/";
	}

}
