/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.site.entity.Group;
import com.zhilai.master.modules.site.service.GroupService;

/**
 * 标签Controller
 * @author zhilai
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/tag")
public class TagController extends BaseController {
	@Autowired
	private GroupService groupService;
	/**
	 * 树结构选择标签（treeselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "treeselect")
	public String treeselect(HttpServletRequest request, Model model) {
		model.addAttribute("url", request.getParameter("url")); 	// 树结构数据URL
		model.addAttribute("extId", request.getParameter("extId")); // 排除的编号ID
		model.addAttribute("checked", request.getParameter("checked")); // 是否可复选
		model.addAttribute("selectIds", request.getParameter("selectIds")); // 指定默认选中的ID
		model.addAttribute("isAll", request.getParameter("isAll")); 	// 是否读取全部数据，不进行权限过滤
		model.addAttribute("module", request.getParameter("module"));	// 过滤栏目模型（仅针对CMS的Category树）
		return "modules/sys/tagTreeselect";
	}
	
	/**
	 * 图标选择标签（iconselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "iconselect")
	public String iconselect(HttpServletRequest request, Model model) {
		model.addAttribute("value", request.getParameter("value"));
		return "modules/sys/tagIconselect";
	}
	
	/**
	 * 表格选择标签（treeselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "tableselect")
	public String tableselect(HttpServletRequest request, Model model) {
		model.addAttribute("url", request.getParameter("url")); 	// 树结构数据URL
		model.addAttribute("extId", request.getParameter("extId")); // 排除的编号ID
		model.addAttribute("checked", request.getParameter("checked")); // 是否可复选
		model.addAttribute("selectIds", request.getParameter("selectIds")); // 指定默认选中的ID
		model.addAttribute("isAll", request.getParameter("isAll")); 	// 是否读取全部数据，不进行权限过滤
		model.addAttribute("module", request.getParameter("module"));	// 过滤栏目模型（仅针对CMS的Category树）
		return "modules/sys/tagTableselect";
	}
	
	/**
	 * 站点重启（treeselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "groupselect")
	public String groupSelect(HttpServletRequest request, Model model, HttpServletResponse response) {
		Page<Group> page1=new Page<Group>(request, response);
		page1.setPageSize(2000);
		Page<Group> page=groupService.getSiteGroup(page1);
	    model.addAttribute("page", page);
		return "modules/sys/tagGroupselect";
	}
	
	/**
	 * 站点升级（upgradeselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "upgradeselect")
	public String upgradeSelect(HttpServletRequest request, Model model, HttpServletResponse response) {
		Page<Group> page1=new Page<Group>(request, response);
		page1.setPageSize(2000);
		Page<Group> page=groupService.getSiteGroup(page1);
	    model.addAttribute("page", page);
		return "modules/sys/tagUpgradeselect";
	}
	
	/**
	 * 位置上报（treeselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "positionselect")
	public String positionSelect(HttpServletRequest request, Model model, HttpServletResponse response) {
		Page<Group> page1=new Page<Group>(request, response);
		page1.setPageSize(2000);
		Page<Group> page=groupService.getSiteGroup(page1);
	    model.addAttribute("page", page);
		return "modules/sys/tagPositionSelect";
	}
	
	/**
	 * 参数配置（configselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "configselect")
	public String configSelect(HttpServletRequest request, Model model, HttpServletResponse response) {
		Page<Group> page1=new Page<Group>(request, response);
		page1.setPageSize(2000);
		Page<Group> page=groupService.getSiteGroup(page1);
	    model.addAttribute("page", page);
		return "modules/sys/tagConfigSelect";
	}
	
	/**
	 * 参数备份（configselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "backupselect")
	public String backupSelect(HttpServletRequest request, Model model, HttpServletResponse response) {
		Page<Group> page1=new Page<Group>(request, response);
		page1.setPageSize(2000);
		Page<Group> page=groupService.getSiteGroup(page1);
	    model.addAttribute("page", page);
		return "modules/sys/tagBackupSelect";
	}
	
	/**
	 * 流量上报（configselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "flowReportSelect")
	public String flowReport(HttpServletRequest request, Model model, HttpServletResponse response) {
		Page<Group> page1=new Page<Group>(request, response);
		page1.setPageSize(2000);
		Page<Group> page=groupService.getSiteGroup(page1);
	    model.addAttribute("page", page);
		return "modules/sys/tagFlowReportSelect";
	}
	
	/**
	 * 流量上报（configselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "alarmselect")
	public String alarmselect(HttpServletRequest request, Model model, HttpServletResponse response) {
		Page<Group> page1=new Page<Group>(request, response);
		page1.setPageSize(2000);
		Page<Group> page=groupService.getSiteGroup(page1);
	    model.addAttribute("page", page);
		return "modules/sys/tagAlarmSelect";
	}
	
	
	/**
	 * 流量配置（flowSelect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "flowSelect")
	public String flowSelect(HttpServletRequest request, Model model, HttpServletResponse response) {
		Page<Group> page1=new Page<Group>(request, response);
		page1.setPageSize(2000);
		Page<Group> page=groupService.getSiteGroup(page1);
	    model.addAttribute("page", page);
		return "modules/sys/tagFlowSelect";
	}
}
