 package com.zhilai.master.modules.site.web;

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
import com.zhilai.master.modules.product.entity.ProductBrand;
import com.zhilai.master.modules.site.entity.AsSite;
import com.zhilai.master.modules.site.entity.SiteDevice;
import com.zhilai.master.modules.site.service.AsSiteService;
import com.zhilai.master.modules.site.service.SiteDeviceService;
import com.zhilai.master.modules.standard.entity.CabinetAttr;
import com.zhilai.master.modules.standard.service.CabinetAttrService;
import com.zhilai.master.modules.utils.GParameter;

@Controller
@RequestMapping(value = "${adminPath}/site/siteDevice")
public class SiteDeviceController extends BaseController {

	@Autowired
	private SiteDeviceService siteDeviceService;
	@Autowired
	private AsSiteService siteService;
	@Autowired
	private CabinetAttrService cabinetAttrService;
//	@Autowired
//	private SequenceIdService sequenceIdService;
//	
	@ModelAttribute
	public SiteDevice get(@RequestParam(required=false) String logid) {
		SiteDevice entity = null;
		if (StringUtils.isNotBlank(logid)){
			entity = siteDeviceService.get(logid);
		}
		if (entity == null){
			entity = new SiteDevice();
		}
		return entity;
	}
	
//	@RequiresPermissions("product:siteDevice:view")
	@RequestMapping(value = {"list", ""})
	public String list(SiteDevice siteDevice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SiteDevice> page = siteDeviceService.findPage(new Page<SiteDevice>(request, response), siteDevice); 
		model.addAttribute("page", page);
		return "modules/site/siteDeviceList";
	}

//	@RequiresPermissions("product:siteDevice:view")
	@RequestMapping(value = "form")
	public String form(SiteDevice siteDevice, Model model) {
		model.addAttribute("siteDevice", siteDevice);
		return "modules/site/siteDeviceForm";
	}

//	@RequiresPermissions("product:siteDevice:edit")
	@RequestMapping(value = "save")
	public String save(SiteDevice siteDevice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, siteDevice)){
			return form(siteDevice, model);
		}
		String site_id = siteDevice.getSite_id();
		AsSite site = new AsSite();
		site.setSite_id(site_id);
		site = siteService.get(site);
		siteDevice.setOwner_id(site.getOwner_id());
		siteDevice.setCabinet_type(siteDevice.getCabinettype_name());//柜型名称
		
			SiteDevice entity = new SiteDevice();
			entity.setSite_id(site_id);
			entity.setCabinet_type(siteDevice.getCabinettype_name());
			List<SiteDevice> slist = siteDeviceService.findList(entity);
			int i = 0;
			if(slist.size()>0){
				i = slist.get(slist.size()-1).getCabinet_location();
			}
			siteDevice.setCabinet_location(i+1);
			siteDevice.setCabinet_seq(Integer.toString(i+1));
		
		siteDevice.setCabinet_batch_num("1");
		// 判断是否有箱柜信息
		CabinetAttr cabinetAttr = new CabinetAttr();
		cabinetAttr.setCabinettype_id(siteDevice.getCabinettype_id());
		List<CabinetAttr> cabinetAttrList = cabinetAttrService.findList(cabinetAttr);
		CabinetAttr cabinetAttrTempP = null;
		if (cabinetAttrList.size() <= 0) {
			addMessage(redirectAttributes, "箱柜关系信息中没有配置该规格柜子的数据！");
			return "redirect:" + adminPath + "/site/siteDevice/";
		}
		cabinetAttrTempP = cabinetAttrList.get(0);
		// 判断柜子的cabinet_location是否重复
		SiteDevice obj = new SiteDevice();
		SiteDevice obj1 = new SiteDevice();
		obj1.setSite_id(siteDevice.getSite_id());
		obj1.setCabinet_seq(siteDevice.getCabinet_seq());
		obj.setSite_id(siteDevice.getSite_id());
		obj.setCabinet_location(siteDevice.getCabinet_location());
		List<SiteDevice> objlist = siteDeviceService.findList(obj);
		if (objlist.size() > 0) {
			addMessage(redirectAttributes, "该位置已存在！");
			return "redirect:" + adminPath + "/site/siteDevice/";
		}
		// 判断Cabinet_seq是否有重复2次以上
		List<SiteDevice> siteDeviceList = siteDeviceService.findList(obj1);
		if (siteDeviceList.size() > 0) {
			addMessage(redirectAttributes, "柜子序列号重复，请更换柜子序列号！");
			return "redirect:" + adminPath + "/site/siteDevice/";
		}
		siteDeviceService.save(siteDevice);
		addMessage(redirectAttributes, "保存信息成功");
		return "redirect:" + adminPath + "/site/siteDevice/";
	}
	
//	@RequiresPermissions("product:siteDevice:edit")
	@RequestMapping(value = "delete")
	public String delete(SiteDevice siteDevice, RedirectAttributes redirectAttributes) {
		siteDeviceService.delete(siteDevice);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:" + adminPath + "/site/siteDevice/";
	}
	
	/**
	 * 明细
	 * @param siteDevice
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("stock:siteDevice:view")
	@RequestMapping(value = "detail")
	public String detail(SiteDevice siteDevice, Model model) {
		model.addAttribute("siteDevice", siteDevice);
		return "modules/site/siteDeviceDetail";
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
    public String exportFile(SiteDevice siteDevice, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "柜箱排列"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SiteDevice> page = siteDeviceService.findPage(new Page<SiteDevice>(request, response, -1), siteDevice);
            List<SiteDevice> siteDevices = page.getList();
    		new ExportExcel("柜箱排列", SiteDevice.class).setDataList(siteDevices).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出柜子排列失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/site/siteDevice/list?repage";
    }

}
