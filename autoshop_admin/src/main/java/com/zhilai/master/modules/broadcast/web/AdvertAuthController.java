package com.zhilai.master.modules.broadcast.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.broadcast.entity.AdvertAuth;
import com.zhilai.master.modules.broadcast.service.AdvertAuthService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:AdvertAuthController
 *Description:播控权限控制层
 *@author ChengJiawei
 *@date 2018年4月18日 下午2:20:41
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/broadcast/advertAuth")
public class AdvertAuthController extends BaseController{

	@Autowired
	private AdvertAuthService advertAuthService;
	
	@ModelAttribute
	public AdvertAuth get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return advertAuthService.get(logid);
		} else {
			return new AdvertAuth();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(AdvertAuth advertAuth,HttpServletRequest req,HttpServletResponse resp,Model model){
		if (null!=advertAuth.getTargetId()&&!advertAuth.getTargetId().equals("")) {
			String[] targetIds = advertAuth.getTargetId().split(",");
			for (int i = 0; i < targetIds.length; i++) {
				if (null!=targetIds[i]&&!targetIds[i].equals("")) {
					advertAuth.setTargetId(targetIds[i]);
				}
			}
		}
		Page<AdvertAuth> page = advertAuthService.findPage(new Page<AdvertAuth>(req, resp), advertAuth);
		List<Dict> targetTypeList = DictUtils.getDictList(GParameter.targetType_key);
		Map<String, String> targetTypeMap = DictUtils.getDictMap(GParameter.targetType_key);
		Map<String, String> advertConfigStateMap = DictUtils.getDictMap(GParameter.advertConfigState_key);
		model.addAttribute("page", page);
		model.addAttribute("targetTypeMap", targetTypeMap);
		model.addAttribute("targetTypeList", targetTypeList);
		model.addAttribute("advertConfigStateMap", advertConfigStateMap);
 		return "/modules/broadcast/advertAuthList";
	}
	
	//跳转添加媒体页面
	@RequestMapping(value="form")
	public String form(AdvertAuth advertAuth,Model model,HttpServletRequest req,HttpServletResponse resp){
		return "/modules/broadcast/advertAuthForm";
	}
	
    //保存信息
	@RequestMapping(value = "save")
	public String save(AdvertAuth advertAuth,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		String targetName = advertAuth.getTargetName();
		String targetId = advertAuth.getTargetId();
		String[] targetNames = targetName.split(",");
		String[] targetIds = targetId.split(",");
		for (int i = 0; i < targetIds.length; i++) {
			if (null!=targetNames[i]&&!targetNames[i].equals("")) {
				advertAuth.setTargetName(targetNames[i]);
			}	
			if (null!=targetIds[i]&&!targetIds[i].equals("")) {
				advertAuth.setTargetId(targetIds[i]);
			}
		}
		advertAuth.setStateTime(DateUtils.getDateTime());
		advertAuthService.save(advertAuth);
		addMessage(redirectAttributes, "保存播控权限成功");
		return "redirect:" + adminPath + "/broadcast/advertAuth/list?repage";
	}
	
	//删除
	@RequestMapping(value = "delete")
	public String delete(AdvertAuth advertAuth, RedirectAttributes redirectAttributes ,HttpServletRequest req){
		DateFormat df  = new SimpleDateFormat("yyyy-MM-dd");
		Date now;
		try {
			now = df.parse(DateUtils.getDate());
			Date sTime = df.parse(advertAuth.getPlanStime());
			Date eTime = df.parse(advertAuth.getPlanEtime());
			if ((now.getTime()>=sTime.getTime())&&(now.getTime()<=eTime.getTime())) {
				addMessage(redirectAttributes, "删除失败，计划播放时间内，不允许删除!");
				return "redirect:" + adminPath + "/broadcast/advertAuth/";
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		advertAuthService.delete(advertAuth);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/broadcast/advertAuth/";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(AdvertAuth advertAuth, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "播控权限"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            if (advertAuth.getTargetId().equals(",")) {
            	advertAuth.setTargetId("");
			} else {
				String[] targetIds = advertAuth.getTargetId().split(",");
	    		for (int i = 0; i < targetIds.length; i++) {
	    			if (null!=targetIds[i]&&!targetIds[i].equals("")) {
	    				advertAuth.setTargetId(targetIds[i]);
	    			}
	    		}
			}
            Page<AdvertAuth> page = advertAuthService.findPage(new Page<AdvertAuth>(request, response, -1), advertAuth);
            List<AdvertAuth> advertAuths = page.getList();
    		new ExportExcel("播控权限", AdvertAuth.class).setDataList(advertAuths).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出播控权限失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/broadcast/advertAuth/list?repage";
    }
}
