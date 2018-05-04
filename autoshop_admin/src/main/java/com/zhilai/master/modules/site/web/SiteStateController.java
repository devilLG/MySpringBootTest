package com.zhilai.master.modules.site.web;

import java.util.ArrayList;
import java.util.List;

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
import com.zhilai.master.modules.site.entity.SiteSta;
import com.zhilai.master.modules.site.entity.SiteState;
import com.zhilai.master.modules.site.service.SiteStateService;

/**
 * @author Administrator
 * @date 2017-12-12 下午4:36:40
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/site/siteState")
public class SiteStateController extends BaseController {

	@Autowired
	private SiteStateService siteStateService;
//	@Autowired
//	private SequenceIdService sequenceIdService;
	
	@ModelAttribute
	public SiteState get(@RequestParam(required=false) String id) {
		SiteState entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = siteStateService.get(id);
		}
		if (entity == null){
			entity = new SiteState();
		}
		return entity;
	}
	
//	@RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = {"list", ""})
	public String list(SiteState siteState, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SiteState> page = siteStateService.findPage(new Page<SiteState>(request, response), siteState); 
		model.addAttribute("page", page);
		return "modules/site/siteStateList";
	}
	//资源状态
//	@RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = {"stateList", ""})
	public String stateList(SiteState siteState, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SiteState> page = siteStateService.findPage(new Page<SiteState>(request, response), siteState); 
		model.addAttribute("page", page);
		return "modules/site/siteStateList2";
	}

//	@RequiresPermissions("product:productBrand:view")
	@RequestMapping(value = "form")
	public String form(SiteState siteState, Model model) {
		model.addAttribute("siteState", siteState);
		return "modules/site/siteStateForm";
	}

//	@RequiresPermissions("product:productBrand:edit")
	@RequestMapping(value = "save")
	public String save(SiteState siteState, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, siteState)){
			return form(siteState, model);
		}
		//productBrand.setBrand_id(productBrand.getCorp_id() + "-" + sequenceIdService.findNextVal("productBrand_id", 7));
		siteStateService.save(siteState);
		addMessage(redirectAttributes, "保存站点状态信息成功");
		return "redirect:" + adminPath + "/site/siteState/";
	}
	
//	@RequiresPermissions("product:productBrand:edit")
	@RequestMapping(value = "delete")
	public String delete(SiteState siteState, RedirectAttributes redirectAttributes) {
		siteStateService.delete(siteState);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:" + adminPath + "/site/siteState/";
	}
	
	/**
	 * 明细
	 * @param productBrand
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("stock:productBrand:view")
	@RequestMapping(value = "detail")
	public String detail(SiteState siteState, Model model) {
		model.addAttribute("siteState", siteState);
		return "modules/site/siteStateDetail";
	}
	
	/**
	 * 导出
	 * @param productBrand
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SiteState siteState, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "网络状态"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SiteState> page = siteStateService.findPage(new Page<SiteState>(request, response, -1), siteState);
            List<SiteState> siteStates = page.getList();
            List<SiteState> siteSta = new ArrayList<SiteState>();
            for (SiteState siteState2 : siteStates) {
            	SiteState state=new SiteState();
            	state.setSite_id(siteState2.getSite_id());
            	state.setSite_name(siteState2.getSite_name());
            	state.setRefresh_state(siteState2.getRefresh_state());
            	state.setRefresh_time(siteState2.getRefresh_time());
            	state.setRefresh_num(siteState2.getRefresh_num());
            	siteSta.add(state);
			}
    		new ExportExcel("网络状态", SiteState.class).setDataList(siteSta).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出站点网络状态！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/site/siteState/list?repage";
    }
	/**
	 * 导出资源状态
	 * @param productBrand
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export2", method=RequestMethod.POST)
    public String exportFiles(SiteState siteState, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "资源状态"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SiteState> page = siteStateService.findPage(new Page<SiteState>(request, response, -1), siteState);
            List<SiteState> siteStates = page.getList();
            List<SiteSta> siteSta = new ArrayList<SiteSta>();
            for (SiteState siteState2 : siteStates) {
            	SiteSta state=new SiteSta();
            	state.setSite_id(siteState2.getSite_id());
            	state.setSite_name(siteState2.getSite_name());
            	state.setRefresh_time(siteState2.getRefresh_time());
            	state.setBox_all(siteState2.getBox_all());
            	state.setBox_empty(siteState2.getBox_empty());
            	state.setBox_damage(siteState2.getBox_damage());
            	state.setBox_short(siteState2.getBox_short());
            	siteSta.add(state);
			}
            
    		new ExportExcel("资源状态", SiteSta.class).setDataList(siteSta).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出站点资源状态！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/site/siteState/stateList?repage";
    }

}
