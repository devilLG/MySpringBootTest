/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.person.web;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhilai.master.common.config.Global;
import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.person.entity.SitePerson;
import com.zhilai.master.modules.person.service.SitePersonService;
import com.zhilai.master.modules.site.entity.AsSite;
import com.zhilai.master.modules.site.service.AsSiteService;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.sys.service.SequenceIdService;
import com.zhilai.master.modules.sys.service.SystemService;
import com.zhilai.master.modules.sys.utils.UserUtils;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.utils.heartbeat.HeartBeat;
import com.zhilai.master.modules.utils.heartbeat.SiteNoticeHeartBeat;

/**
 * 站点Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/person/sitePerson")
public class SitePersonController extends BaseController {

	@Autowired
	private SitePersonService sitePersonService;
	@Autowired
	private SiteNoticeHeartBeat siteNoticeHeartBeat;
	@Autowired
	private SystemService systemService;
	@Autowired
	private SequenceIdService sequenceIdService;
	@Autowired
	private AsSiteService siteService;
	
	
	@ModelAttribute
	public SitePerson get(@RequestParam(required=false) String logid) {
		if (StringUtils.isNotBlank(logid)){
			return sitePersonService.get(logid);
		}else{
			return new SitePerson();
		}
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = {"list", ""})
	public String list(SitePerson sitePerson, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		String id = user.getCorpId();
		sitePerson.setCorp_id(id);
        Page<SitePerson> page = sitePersonService.findPage(new Page<SitePerson>(request, response), sitePerson);
        model.addAttribute("page", page);
		return "modules/person/sitePersonList";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "form")
	public String form(SitePerson sitePerson, Model model) {
		model.addAttribute("sitePerson", sitePerson);
		return "modules/person/sitePersonForm";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "save")
	public String save(SitePerson sitePerson, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sitePerson)){
			return form(sitePerson, model);
		}
		User user = systemService.getUserByLoginName(sitePerson.getLogin_id());
		String site_id = sitePerson.getSite_id();
		
		String crop_id = user.getCorpId();
		String crop_name = user.getCompanyName();
		sitePerson.setLogin_id(user.getLoginName());
		SitePerson Person = new SitePerson();
		Person.setSite_id(site_id);
		Person.setLogin_id(user.getLoginName());
		Person.setLogin_name(user.getName());
		Person.setMobile(user.getMobile());
		Person.setEmail(user.getEmail());
		List<SitePerson> personlist = sitePersonService.findList(Person);
		if(sitePerson.getLogid()==null||sitePerson.getLogid().trim().equals("")){
			if(personlist.size()>0){
				addMessage(redirectAttributes, "该站点已存在该人员信息");
				return "redirect:" + adminPath + "/person/sitePerson/?repage";
			}
		}
		sitePerson.setCorp_id(crop_id);
		sitePerson.setCorp_name(crop_name);
		sitePerson.setLogin_name(user.getName());
		sitePerson.setMobile(user.getMobile());
		sitePerson.setEmail(user.getEmail());
		sitePerson.setConfig_id(crop_id + "-" + sequenceIdService.findNextVal(crop_id, "config_id", 7));
		//assite.setSite_id(assite.getCorp_id() + "-" + sequenceIdService.findNextVal(assite.getCorp_id(), "site_id", 4));
		sitePersonService.save(sitePerson);
		HeartBeat heartBeat = new HeartBeat();
		heartBeat.setSite_id(sitePerson.getSite_id());
		heartBeat.setSite_name(sitePerson.getSite_name());
		heartBeat.setTrade_code(GParameter.issued_sitePerson_trade_code);
		heartBeat.setTrade_name(GParameter.issued_sitePerson_trade_desc);
		heartBeat.setCreate_time(DateUtil.getNow());
		heartBeat.setUpdate_time(DateUtil.getNow());
		heartBeat.setPush_id(DateUtil.getLogid());
		heartBeat.setIssued_key(sitePerson.getLogin_id());
		siteNoticeHeartBeat.pushHeart(heartBeat);
		addMessage(redirectAttributes, "保存'" + sitePerson.getLogin_name() + "'成功");
		return "redirect:" + adminPath + "/person/sitePerson/?repage";
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "delete")
	public String delete(SitePerson sitePerson, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/person/sitePerson/?repage";
		}
		if(sitePerson.getCur_state()==GParameter.siteCur_state_no&&sitePerson.getDown_state().equals(GParameter.downState_confrim)){
			sitePersonService.delete(sitePerson);
			addMessage(redirectAttributes, "删除成功");
		}else{
			addMessage(redirectAttributes, "该状态不允许删除");
			return "redirect:" + adminPath + "/person/sitePerson/?repage";
		}
			
		return "redirect:" + adminPath + "/person/sitePerson/?repage";
	}
	/**
	 * 明细
	 * @param productBrand
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("stock:productBrand:view")
	@RequestMapping(value = "detail")
	public String detail(SitePerson sitePerson, Model model) {
		model.addAttribute("sitePerson", sitePerson);
		return "modules/person/sitePersonDetail";
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
    public String exportFile(SitePerson sitePerson, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "站点人员信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SitePerson> page = sitePersonService.findPage(new Page<SitePerson>(request, response, -1), sitePerson);
    		new ExportExcel("站点人员数据", SitePerson.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出站点失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/person/sitePerson/list?repage";
    }
	
	/**
	 * 获取地点人员JSON数据。
	 * @param extId 排除的ID  注意id后台与前端的命名
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "tableData")
	public List<Map<String, String>> tableData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, String>> mapList = Lists.newArrayList();
		SitePerson sitePerson = new SitePerson();
//		sitePerson.setSite_id(extId);
		sitePerson.setEmp_type(type);
		List<SitePerson> list = sitePersonService.findList(sitePerson);
		for (SitePerson e : list) {
			Map<String, String> map = Maps.newLinkedHashMap();
			map.put("登录账号", e.getLogin_id());
			map.put("用户姓名 ", e.getLogin_name());			
			map.put("手机号", e.getMobile());			
			mapList.add(map);
		}
		return mapList;
	}
	
	@RequestMapping(value = "showSitePersonInfo")
	public String showSitePersonInfo(SitePerson sitePerson, HttpServletRequest request, HttpServletResponse response, Model model) {
		sitePerson.setEmp_type(GParameter.empType_replenishment);
		Page<SitePerson> page = sitePersonService.findPage(new Page<SitePerson>(request, response), sitePerson); 
		model.addAttribute("page", page);
		return "modules/person/sitePersonTableSelect";
	}

}
