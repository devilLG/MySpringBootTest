package com.zhilai.master.modules.notice.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.notice.entity.NoticeTemplate;
import com.zhilai.master.modules.notice.service.NoticeTemplateService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:NoticeTemplateController
 *Description:公共通知模板控制层
 *@author ChengJiawei
 *@date 2018年4月19日 下午2:57:04
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/notice/noticeTemplate")
public class NoticeTemplateController extends BaseController{

	@Autowired
	private NoticeTemplateService noticeTemplateService;
	
	@ModelAttribute
	public NoticeTemplate get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return noticeTemplateService.get(logid);
		} else {
			return new NoticeTemplate();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(NoticeTemplate noticeTemplate,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<NoticeTemplate> page = noticeTemplateService.findPage(new Page<NoticeTemplate>(req, resp), noticeTemplate);
		List<Dict> mainTypeList = DictUtils.getDictList(GParameter.mainType_key);
		Map<String, String> mainTypeMap = DictUtils.getDictMap(GParameter.mainType_key);
		Map<String, String> noticeTypeMap = DictUtils.getDictMap(GParameter.noticeType_key);
		Map<String, String> curStateMap = DictUtils.getDictMap(GParameter.curState_key);
		Map<String, String> levelMap = DictUtils.getDictMap(GParameter.noticeLevel_key);
		model.addAttribute("page", page);
		model.addAttribute("mainTypeList", mainTypeList);
		model.addAttribute("mainTypeMap", mainTypeMap);
		model.addAttribute("noticeTypeMap", noticeTypeMap);
		model.addAttribute("levelMap", levelMap);
		model.addAttribute("curStateMap", curStateMap);
 		return "/modules/notice/noticeTemplateList";
	}
	
	//跳转添加公共通知模板页面
	@RequestMapping(value="form")
	public String form(NoticeTemplate noticeTemplate,Model model,HttpServletRequest req,HttpServletResponse resp){
		if (null == noticeTemplate.getLogid() || noticeTemplate.getLogid().equals("")) {
			noticeTemplate.setMain_type("01");
			noticeTemplate.setLevel("3");
			noticeTemplate.setSend_num(3);
		} else {
			noticeTemplate.setSub_type(noticeTemplate.getSub_type().substring(2, noticeTemplate.getSub_type().length()));
		}
		return "/modules/notice/noticeTemplateForm";
	}
	
	//根据一级类型查询二级类型
	@ResponseBody
	@RequestMapping(value="typeChange")
	public void typeChange(HttpServletResponse response, HttpServletRequest req) throws IOException{
		PrintWriter out = response.getWriter();
		String type = req.getParameter("type");
		List<Dict> subTypeList = new ArrayList<Dict>();
		if (type.equals("01")) {//销售订单
			subTypeList = DictUtils.getDictList(GParameter.orderApplyState_key);
		} else if (type.equals("02")) {// 站点监控
			subTypeList = DictUtils.getDictList(GParameter.siteState_key);
		} else if (type.equals("03")) {// 工单服务
			subTypeList = DictUtils.getDictList(GParameter.workOperAction_key);
		} else if (type.equals("04")) {// 站点库存
			/*subTypeList = DictUtils.getDictList(GParameter.);*/
		} else if (type.equals("05")) {// 站点补货
			subTypeList = DictUtils.getDictList(GParameter.replenishmentCurState_key);
		} else if (type.equals("06")) {// 站点换货
			subTypeList = DictUtils.getDictList(GParameter.barterState_key);
		} else if (type.equals("07")) {// 站点盘点
			subTypeList = DictUtils.getDictList(GParameter.checkState_key);
		} else if (type.equals("08")) {// 人员注册及更新
			/*subTypeList = DictUtils.getDictList(GParameter.);*/
		} else if (type.equals("09")) {// 对账单
			subTypeList = DictUtils.getDictList(GParameter.paymentState_key);
		}
		out.print( JSON.toJSONString(subTypeList) );
		out.close();
	}
	
    //保存信息
	@RequestMapping(value = "save")
	public String save(NoticeTemplate noticeTemplate,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		noticeTemplate.setSub_type(noticeTemplate.getMain_type()+noticeTemplate.getSub_type());
		Map<String, String> roleTypeMap = DictUtils.getDictMap(GParameter.roleType_key);
		String role_name = roleTypeMap.get(noticeTemplate.getRole_id());
		noticeTemplate.setRole_name(role_name);
		noticeTemplate.setCur_state("0");
		noticeTemplateService.save(noticeTemplate);
		addMessage(redirectAttributes, "保存公共通知模板成功");
		return "redirect:" + adminPath + "/notice/noticeTemplate/list?repage";
	}
	
	//修改状态
	@RequestMapping(value = "updateState")
	public void updateState(NoticeTemplate noticeTemplate, HttpServletRequest request, HttpServletResponse response) {
		try {
			Writer out = response.getWriter();
			noticeTemplate = get(request.getParameter("logid"));
			noticeTemplate.setCur_state(request.getParameter("cur_state"));
			noticeTemplateService.save(noticeTemplate);
			out.write("success");
			out.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	//删除
	@RequestMapping(value = "delete")
	public String delete(NoticeTemplate noticeTemplate, RedirectAttributes redirectAttributes ,HttpServletRequest req){
		if (noticeTemplate.getCur_state().equals("0")) {
			addMessage(redirectAttributes, "删除失败，只有失效模板可以删除");
			return "redirect:" + adminPath + "/notice/noticeTemplate/";
		}
		noticeTemplateService.delete(noticeTemplate);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/notice/noticeTemplate/";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(NoticeTemplate noticeTemplate, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "公共通知模板"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<NoticeTemplate> page = noticeTemplateService.findPage(new Page<NoticeTemplate>(request, response, -1), noticeTemplate);
            List<NoticeTemplate> noticeTemplates = page.getList();
    		new ExportExcel("公共通知模板", NoticeTemplate.class).setDataList(noticeTemplates).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出公共通知模板失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/notice/noticeTemplate/list?repage";
    }
}
