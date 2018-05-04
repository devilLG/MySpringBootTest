/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.site.web;

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
import com.zhilai.master.modules.monitor.entity.CabinetDevice;
import com.zhilai.master.modules.monitor.entity.DeviceEnv;
import com.zhilai.master.modules.monitor.entity.DeviceInfo;
import com.zhilai.master.modules.monitor.entity.SiteDeviceConfig;
import com.zhilai.master.modules.monitor.entity.SiteEnv;
import com.zhilai.master.modules.monitor.service.CabinetDeviceService;
import com.zhilai.master.modules.monitor.service.DeviceEnvService;
import com.zhilai.master.modules.monitor.service.DeviceInfoService;
import com.zhilai.master.modules.monitor.service.SiteDeviceConfigService;
import com.zhilai.master.modules.monitor.service.SiteEnvService;
import com.zhilai.master.modules.site.entity.AsSite;
import com.zhilai.master.modules.site.service.AsSiteService;
import com.zhilai.master.modules.sys.entity.Area;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.sys.service.AreaService;
import com.zhilai.master.modules.sys.service.SequenceIdService;
import com.zhilai.master.modules.sys.utils.UserUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 站点Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/site/assite")
public class AsSiteController extends BaseController {

	@Autowired
	private AsSiteService siteService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private SequenceIdService sequenceIdService;
	@Autowired
	private CabinetDeviceService cabinetDeviceService;
	@Autowired
	private DeviceInfoService deviceInfoService;
	@Autowired
	private SiteDeviceConfigService siteDeviceConfigService;
	@Autowired
	private DeviceEnvService deviceEnvService;
	@Autowired
	private SiteEnvService siteEnvService;
	
	@ModelAttribute
	public AsSite get(@RequestParam(required=false) String site_id) {
		AsSite entity = null;
		if (StringUtils.isNotBlank(site_id)){
			entity = siteService.get(site_id);
		}
		if (entity == null){
			entity = new AsSite();
		}
		return entity;
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = {"list", ""})
	public String list(AsSite assite, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		String own_id = user.getCorpId();
		String tree_id = assite.getTree_id();
		String code_id = null;
		Area area = new Area();
		
		if(tree_id!=null&&!tree_id.equals("")){
			area = areaService.get(tree_id);
			if(area!=null){
				code_id = area.getCode();
				assite.setTree_id(code_id);
			}
			
		}

		if(own_id.equals("1")||own_id.equals("8888")){
			Page<AsSite> page = siteService.findPage(new Page<AsSite>(request, response), assite);
	        model.addAttribute("page", page);
			return "modules/site/assiteList";
		}else{
			assite.setOwner_id(own_id);
			Page<AsSite> page = siteService.findPage(new Page<AsSite>(request, response), assite);
	        model.addAttribute("page", page);
			return "modules/site/assiteList";
		}
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "form")
	public String form(AsSite assite, Model model) {
		User user = UserUtils.getUser();
		if((assite.getOwner_id() == null || "".equals(assite.getOwner_id())) && null != user){
			assite.setOwner_id(user.getCorpId());
			assite.setOwner_name(user.getCompanyName());
		}
		model.addAttribute("assite", assite);
		model.addAttribute("user", user);
		return "modules/site/assiteForm";
	}

	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "save")
	public String save(AsSite assite, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, assite)){
			return form(assite, model);
		}
		User user = UserUtils.getUser();
		if((assite.getOwner_id() == null || "".equals(assite.getOwner_id())) && null != user){
			assite.setOwner_id(user.getCorpId());
			assite.setOwner_name(user.getCompanyName());
		}
		String tree_id = assite.getTree_id();
		Area area = areaService.get(tree_id);
		if(area != null){
			String code_id = area.getCode();
			assite.setTree_id(code_id);
		}
		assite.setCorp_id(assite.getOwner_id());
		assite.setDown_state(GParameter.downState_wait);
		assite.setSite_state(GParameter.siteUseState_offline);
		assite.setComm_type(GParameter.commtype_http);
		assite.setIs_replenishment(GParameter.is_replenishment_no);
		assite.setWarn_type(GParameter.warn_type_no);
		assite.setWarn_state(GParameter.warn_state_nomal);
		assite.setCur_temperature("0.0");
		assite.setTemperature_state(GParameter.temperature_nomal);
		if(assite.getCabinet_type()==null ||assite.getCabinet_type().trim().equals("")){
			assite.setCabinet_type(assite.getCabinetStandard_id());
			assite.setCabinet_type_name(assite.getCabinetStandard_name());
		}
		assite.setState_time(DateUtil.getNow());
		if(assite.getSite_id() == null || "".equals(assite.getSite_id().trim())){
			assite.setSite_id(assite.getCorp_id() + "-" + sequenceIdService.findNextVal(assite.getCorp_id(), "site_id", 4));
			siteService.save(assite);
			//初始化站点设备信息
			CabinetDevice cabinetDevice = new CabinetDevice();
			cabinetDevice.setCabconfigId(assite.getCabinetStandard_id());
			List<CabinetDevice> cabinetDeviceList = cabinetDeviceService.findList(cabinetDevice);
			for (CabinetDevice obj : cabinetDeviceList) {
				DeviceInfo deviceInfo = deviceInfoService.findOneByDeviceId(obj.getDeviceId());
				SiteDeviceConfig siteDeviceConfig = new SiteDeviceConfig();
				siteDeviceConfig.setSiteId(assite.getSite_id());
				siteDeviceConfig.setSiteName(assite.getSite_name());
				siteDeviceConfig.setDeviceId(deviceInfo.getDeviceId());
				siteDeviceConfig.setDeviceCode(deviceInfo.getDeviceCode());
				siteDeviceConfig.setDeviceName(deviceInfo.getDeviceName());
				siteDeviceConfig.setDeviceModel(deviceInfo.getDeviceModel());
				siteDeviceConfig.setDeviceBard(deviceInfo.getDeviceBard());
				siteDeviceConfig.setDeviceMfrs(deviceInfo.getDeviceMfrs());
				siteDeviceConfig.setErrorLevel(deviceInfo.getErrorLevel());
				siteDeviceConfig.setCurState("1");
				siteDeviceConfig.setStateTime(DateUtils.getDateTime());
				siteDeviceConfig.setMaintenance(deviceInfo.getMaintenance());
				siteDeviceConfig.setMaintenance(deviceInfo.getMaintenance());
				siteDeviceConfig.setCheckState("1");
				siteDeviceConfigService.save(siteDeviceConfig);
				List<DeviceEnv> deviceEnvList = deviceEnvService.findByDeviceId(deviceInfo.getDeviceId());
				for (DeviceEnv deviceEnv : deviceEnvList) {
					SiteEnv siteEnv = new SiteEnv();
					siteEnv.setSdeviceId(siteDeviceConfig.getSdeviceId());
					siteEnv.setSiteId(siteDeviceConfig.getSiteId());
					siteEnv.setSiteName(siteDeviceConfig.getSiteName());
					siteEnv.setCorpId(assite.getOwner_id());
					siteEnv.setCorpName(assite.getOwner_name());
					siteEnv.setDeviceId(deviceInfo.getDeviceId());
					siteEnv.setDeviceCode(deviceInfo.getDeviceCode());
					siteEnv.setDeviceName(deviceInfo.getDeviceName());
					siteEnv.setDeviceModel(deviceInfo.getDeviceModel());
					siteEnv.setEnvCode(deviceEnv.getEnvCode());
					siteEnv.setEnvName(deviceEnv.getEnvName());
					siteEnv.setEnvSvalue(deviceEnv.getEnvSvalue());
					siteEnv.setEnvEvalue(deviceEnv.getEnvEvalue());
					siteEnv.setEnvWsvalue(deviceEnv.getEnvWsvalue());
					siteEnv.setEnvWevalue(deviceEnv.getEnvWevalue());
					siteEnv.setUnitId(deviceEnv.getUnitId());
					siteEnv.setUnitName(deviceEnv.getUnitName());
					siteEnv.setCurState("1");
					siteEnv.setStateTime(DateUtils.getDateTime());
					siteEnvService.save(siteEnv);
				}
			}
		}else {
			siteService.save(assite);
		}
		addMessage(redirectAttributes, "保存站点'" + assite.getSite_name() + "'成功");
		return "redirect:" + adminPath + "/site/assite/?repage";
	}
	
	@RequiresPermissions("site:assite:view")
	@RequestMapping(value = "delete")
	public String delete(AsSite assite, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/site/assite/?repage";
		}
			siteService.delete(assite);
			//addMessage(redirectAttributes, ("删除站点成功");
		
		return "redirect:" + adminPath + "/site/assite/?repage";
	}
	/**
	 * 导出站点数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(AsSite assite, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "站点信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            User user = UserUtils.getUser();
    		String own_id = user.getCorpId();
    		if(own_id.equals("1")||own_id.equals("8888")){
    			assite.setOwner_id("");
    	       
    		}else{
    			assite.setOwner_id(own_id);
    		}
            Page<AsSite> page = siteService.findPage(new Page<AsSite>(request, response, -1), assite);
    		new ExportExcel("用户数据", AsSite.class).setDataList(page.getList()).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出站点失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/site/assite/list?repage";
    }
	/**
	 * 获取站点JSON数据。
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
		List<AsSite> list = siteService.findList(new AsSite());
		for (AsSite e : list) {
			Map<String, String> map = Maps.newLinkedHashMap();
			map.put("站点编号", e.getSite_id());
			map.put("站点名称 ", e.getSite_name());	
			map.put("c_归属公司编号 ", e.getOwner_id());	
			map.put("区域名称", e.getTree_name());
			mapList.add(map);
		}
		return mapList;
	}
	
	/**
	 * 明细
	 * @param assite
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(AsSite assite, Model model) {
		model.addAttribute("assite", assite);
		return "modules/site/asSiteDetail";
	}
	
}
