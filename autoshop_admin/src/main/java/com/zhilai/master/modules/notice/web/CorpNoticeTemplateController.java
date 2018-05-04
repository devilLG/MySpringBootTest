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
import com.zhilai.master.modules.notice.entity.CorpNoticeTemplate;
import com.zhilai.master.modules.notice.service.CorpNoticeTemplateService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:CorpNoticeTemplateController
 *Description:通知模板配置控制层
 *@author ChengJiawei
 *@date 2018年4月20日 下午5:42:02
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/notice/corpNoticeTemplate")
public class CorpNoticeTemplateController extends BaseController{

	@Autowired
	private CorpNoticeTemplateService corpNoticeTemplateService;
	
	@ModelAttribute
	public CorpNoticeTemplate get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return corpNoticeTemplateService.get(logid);
		} else {
			return new CorpNoticeTemplate();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(CorpNoticeTemplate corpNoticeTemplate,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<CorpNoticeTemplate> page = corpNoticeTemplateService.findPage(new Page<CorpNoticeTemplate>(req, resp), corpNoticeTemplate);
		List<Dict> mainTypeList = DictUtils.getDictList(GParameter.mainType_key);
		Map<String, String> mainTypeMap = DictUtils.getDictMap(GParameter.mainType_key);
		Map<String, String> noticeTypeMap = DictUtils.getDictMap(GParameter.noticeType_key);
		Map<String, String> curStateMap = DictUtils.getDictMap(GParameter.curState_key);
		model.addAttribute("page", page);
		model.addAttribute("mainTypeList", mainTypeList);
		model.addAttribute("mainTypeMap", mainTypeMap);
		model.addAttribute("noticeTypeMap", noticeTypeMap);
		model.addAttribute("curStateMap", curStateMap);
 		return "/modules/notice/corpNoticeTemplateList";
	}
	
	//跳转添加通知模板配置页面
	@RequestMapping(value="form")
	public String form(CorpNoticeTemplate corpNoticeTemplate,Model model,HttpServletRequest req,HttpServletResponse resp){
		if (null == corpNoticeTemplate.getLogid() || corpNoticeTemplate.getLogid().equals("")) {
			corpNoticeTemplate.setMain_type("01");
			corpNoticeTemplate.setLevel("3");
			corpNoticeTemplate.setSend_num(3);
		} else {
			corpNoticeTemplate.setSub_type(corpNoticeTemplate.getSub_type().substring(2, corpNoticeTemplate.getSub_type().length()));
		}
		return "/modules/notice/corpNoticeTemplateForm";
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
	public String save(CorpNoticeTemplate corpNoticeTemplate,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		corpNoticeTemplate.setSub_type(corpNoticeTemplate.getMain_type()+corpNoticeTemplate.getSub_type());
		Map<String, String> mainTypeMap = DictUtils.getDictMap(GParameter.mainType_key);
		String role_name = mainTypeMap.get(corpNoticeTemplate.getRole_id());
		corpNoticeTemplate.setRole_name(role_name);
		corpNoticeTemplate.setCur_state("0");
		corpNoticeTemplateService.save(corpNoticeTemplate);
		addMessage(redirectAttributes, "保存通知模板配置成功");
		return "redirect:" + adminPath + "/notice/corpNoticeTemplate/list?repage";
	}
	
	//修改状态
	@RequestMapping(value = "updateState")
	public void updateState(CorpNoticeTemplate corpNoticeTemplate, HttpServletRequest request, HttpServletResponse response) {
		try {
			Writer out = response.getWriter();
			corpNoticeTemplate = get(request.getParameter("logid"));
			corpNoticeTemplate.setCur_state(request.getParameter("cur_state"));
			corpNoticeTemplateService.save(corpNoticeTemplate);
			out.write("success");
			out.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	//删除
	@RequestMapping(value = "delete")
	public String delete(CorpNoticeTemplate corpNoticeTemplate, RedirectAttributes redirectAttributes ,HttpServletRequest req){
		if (corpNoticeTemplate.getCur_state().equals("0")) {
			addMessage(redirectAttributes, "删除失败，只有失效模板可以删除");
			return "redirect:" + adminPath + "/notice/corpNoticeTemplate/";
		}
		corpNoticeTemplateService.delete(corpNoticeTemplate);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/notice/corpNoticeTemplate/";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CorpNoticeTemplate corpNoticeTemplate, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "通知模板配置"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<CorpNoticeTemplate> page = corpNoticeTemplateService.findPage(new Page<CorpNoticeTemplate>(request, response, -1), corpNoticeTemplate);
            List<CorpNoticeTemplate> corpNoticeTemplates = page.getList();
    		new ExportExcel("通知模板配置", CorpNoticeTemplate.class).setDataList(corpNoticeTemplates).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出通知模板配置失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/notice/corpNoticeTemplate/list?repage";
    }
}
