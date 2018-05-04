package com.zhilai.master.modules.site.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.putils.utils.DateUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.zhilai.master.modules.site.entity.AsSite;
import com.zhilai.master.modules.site.entity.SiteCmd;
import com.zhilai.master.modules.site.service.AsSiteService;
import com.zhilai.master.modules.site.service.SiteCmdService;
import com.zhilai.master.modules.sys.service.SequenceIdService;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.utils.heartbeat.HeartBeat;
import com.zhilai.master.modules.utils.heartbeat.SiteNoticeHeartBeat;

/**
 * 站点Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/site/siteCmd")
public class SiteCmdController extends BaseController {

	@Autowired
	private SiteCmdService siteCmdService;
	@Autowired
	private SequenceIdService sequenceIdService;
	@Autowired
	private AsSiteService siteService;
	@Autowired
	private SiteNoticeHeartBeat siteNoticeHeartBeat;
	
	@ModelAttribute
	public SiteCmd get(@RequestParam(required=false) String cmd_id) {
		if (StringUtils.isNotBlank(cmd_id)){
			return siteCmdService.get(cmd_id);
		}else{
			return new SiteCmd();
		}
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = {"list", ""})
	public String list(SiteCmd siteCmd, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<SiteCmd> page = siteCmdService.findPage(new Page<SiteCmd>(request, response), siteCmd);
        model.addAttribute("page", page);
		return "modules/site/siteCmdList";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "form")
	public String form(SiteCmd siteCmd, Model model) {
		model.addAttribute("siteCmd", siteCmd);
		return "modules/site/siteCmdForm";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "save")
	public String save(SiteCmd siteCmd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, siteCmd)){
			return form(siteCmd, model);
		}
		if(siteCmd.getDown_state() != null && siteCmd.getDown_state().equals(GParameter.downState_confrim)){
			addMessage(redirectAttributes, "此状态不允许修改");
			return "redirect:" + adminPath + "/site/siteCmd/?repage";
		}
		AsSite site = siteService.get(siteCmd.getSite_id());
		siteCmd.setCorp_id(site.getOwner_id());
		if(siteCmd.getCmd_obj().equals(GParameter.siteCmdObj_site)){
			siteCmd.setCmd_val(siteCmd.getSite_id());
		}
		if(siteCmd.getCmd_id() == null || "".equals(siteCmd.getCmd_id().trim())){
			siteCmd.setCmd_id(sequenceIdService.findNextVal(siteCmd.getCorp_id(), "cm_id", 7));
	     	siteCmdService.save(siteCmd);
		}else {
			siteCmdService.save(siteCmd);
		}
		HeartBeat heartBeat = new HeartBeat();
		heartBeat.setSite_id(siteCmd.getSite_id());
		heartBeat.setSite_name(siteCmd.getSite_name());
		heartBeat.setTrade_code(GParameter.issued_siteCmd_trade_code);
		heartBeat.setTrade_name(GParameter.issued_siteCmd_trade_desc);
		heartBeat.setCreate_time(DateUtil.getNow());
		heartBeat.setUpdate_time(DateUtil.getNow());
		heartBeat.setPush_id(DateUtil.getLogid());
		heartBeat.setIssued_key(siteCmd.getCmd_id());
		siteNoticeHeartBeat.pushHeart(heartBeat);
		
		addMessage(redirectAttributes, "保存站点控制" + siteCmd.getSite_name() + "成功");
		return "redirect:" + adminPath + "/site/siteCmd/";
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "delete")
	public String delete(SiteCmd siteCmd, RedirectAttributes redirectAttributes) {
		siteCmdService.delete(siteCmd);
		addMessage(redirectAttributes, "删除站点控制成功");
		return "redirect:" + adminPath + "/site/siteCmd/?repage";
	}
	/**
	 * 导出站点数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("site:assite:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SiteCmd siteCmd, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "站点控制信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SiteCmd> page = siteCmdService.findPage(new Page<SiteCmd>(request, response, -1), siteCmd);
    		new ExportExcel("站点控制信息", SiteCmd.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/site/siteCmd/list?repage";
    }

	
}
