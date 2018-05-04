package com.zhilai.master.modules.notice.web;

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
import com.zhilai.master.modules.notice.entity.CorpNoticeTemplate;
import com.zhilai.master.modules.notice.entity.NoticeField;
import com.zhilai.master.modules.notice.entity.NoticeTemplate;
import com.zhilai.master.modules.notice.service.CorpNoticeTemplateService;
import com.zhilai.master.modules.notice.service.NoticeFieldService;
import com.zhilai.master.modules.notice.service.NoticeTemplateService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:NoticeFieldController
 *Description:公共变量配置控制层
 *@author ChengJiawei
 *@date 2018年4月20日 上午11:50:03
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/notice/noticeField")
public class NoticeFieldController extends BaseController{

	@Autowired
	private NoticeFieldService noticeFieldService;
	
	@Autowired
	private NoticeTemplateService noticeTemplateService;
	
	@Autowired
	private CorpNoticeTemplateService corpNoticeTemplateService;
	
	@ModelAttribute
	public NoticeField get(@RequestParam(required = false)String logid){
		if (StringUtils.isNotBlank(logid)) {
			return noticeFieldService.get(logid);
		} else {
			return new NoticeField();
		}
	}
	
	//跳转主页
	@RequestMapping(value = {"list",""})
	public String list(NoticeField noticeField,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<NoticeField> page = noticeFieldService.findPage(new Page<NoticeField>(req, resp), noticeField);
		List<Dict> mainTypeList = DictUtils.getDictList(GParameter.mainType_key);
		Map<String, String> mainTypeMap = DictUtils.getDictMap(GParameter.mainType_key);
		model.addAttribute("page", page);
		model.addAttribute("mainTypeList", mainTypeList);
		model.addAttribute("mainTypeMap", mainTypeMap);
 		return "/modules/notice/noticeFieldList";
	}
	
	//跳转添加公共通知模板页面
	@RequestMapping(value="form")
	public String form(NoticeField noticeField,Model model,HttpServletRequest req,HttpServletResponse resp){
		if (null == noticeField.getLogid() || noticeField.getLogid().equals("")) {
			noticeField.setMain_type("01");
			
		} else {
			/*noticeField.setSub_type(noticeField.getSub_type().substring(2, noticeField.getSub_type().length()));*/
		}
		return "/modules/notice/noticeFieldForm";
	}
	
	//根据业务大类查数据库表
	
    //保存信息
	@RequestMapping(value = "save")
	public String save(NoticeField noticeField,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		NoticeField obj = noticeFieldService.findOneByCode(noticeField.getField_code());
		if (noticeField.getLogid().equals("")) {
			if (null!=obj&&!obj.getLogid().equals("")) {
				addMessage(redirectAttributes, "添加失败，该字段编号已经存在！");
				return "redirect:" + adminPath + "/notice/noticeField/list?repage";
			}
		} else if(!obj.getLogid().equals(noticeField.getLogid())) {
			addMessage(redirectAttributes, "修改失败，该字段编号已经存在！");
			return "redirect:" + adminPath + "/notice/noticeField/list?repage";
		}
		noticeFieldService.save(noticeField);
		addMessage(redirectAttributes, "保存公共通知模板成功");
		return "redirect:" + adminPath + "/notice/noticeField/list?repage";
	}
	
	//删除
	@RequestMapping(value = "delete")
	public String delete(NoticeField noticeField, RedirectAttributes redirectAttributes ,HttpServletRequest req){
		List<NoticeTemplate> noticeTemplateList = noticeTemplateService.findListByfileCode(noticeField.getField_code());
		List<CorpNoticeTemplate> corpNoticeTemplateList = corpNoticeTemplateService.findListByfileCode(noticeField.getField_code());
		if (null!=noticeTemplateList&&noticeTemplateList.size()>0) {
			addMessage(redirectAttributes, "删除失败，已被模板引用！");
			return "redirect:" + adminPath + "/notice/noticeField/";
		} else if (null!=corpNoticeTemplateList&&corpNoticeTemplateList.size()>0) {
			addMessage(redirectAttributes, "删除失败，已被通知模板引用！");
			return "redirect:" + adminPath + "/notice/noticeField/";
		}
		noticeFieldService.delete(noticeField);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/notice/noticeField/";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(NoticeField noticeField, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "公共通知模板"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<NoticeField> page = noticeFieldService.findPage(new Page<NoticeField>(request, response, -1), noticeField);
            List<NoticeField> noticeFields = page.getList();
    		new ExportExcel("公共通知模板", NoticeField.class).setDataList(noticeFields).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出公共通知模板失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/notice/noticeField/list?repage";
    }
	
	//放大镜
	@RequestMapping(value = "showTemplate")
	public String showTemplate(NoticeField noticeField,Model model,HttpServletRequest req,HttpServletResponse resp){
		List<NoticeField> noticeFieldList = noticeFieldService.findList(noticeField);
		model.addAttribute("noticeFieldList", noticeFieldList);
		return "/modules/notice/noticeFieldSelect";
	}
}
