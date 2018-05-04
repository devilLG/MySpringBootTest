/**
 * Copyright &copy; 2012-2016 <a href="https://shop450532966.taobao.com/">zhilai</a> All rights reserved.
 */
package com.zhilai.master.modules.sys.web;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhilai.master.common.beanvalidator.BeanValidators;
import com.zhilai.master.common.config.Global;
import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.StringUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.utils.excel.ImportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.person.entity.SitePerson;
import com.zhilai.master.modules.person.service.SitePersonService;
import com.zhilai.master.modules.sys.entity.Office;
import com.zhilai.master.modules.sys.entity.Role;
import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.sys.service.OfficeService;
import com.zhilai.master.modules.sys.service.SystemService;
import com.zhilai.master.modules.sys.utils.UserUtils;
import com.zhilai.master.modules.utils.GParameter;
import com.zhilai.master.modules.utils.heartbeat.HeartBeat;
import com.zhilai.master.modules.utils.heartbeat.SiteNoticeHeartBeat;

/**
 * 用户Controller
 * @author zhilai
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {

	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SitePersonService sitePersonService;
	@Autowired
	private SiteNoticeHeartBeat siteNoticeHeartBeat;
	
	@ModelAttribute
	public User get(@RequestParam(required=false) String logid) {
		if (StringUtils.isNotBlank(logid)){
			return systemService.getUser(logid);
		}else{
			return new User();
		}
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"index"})
	public String index(User user, Model model) {
		return "modules/sys/userIndex";
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"list", ""})
	public String list(User user,String officeId, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (officeId != null && !officeId.equals("")) {
			user.setOffice(officeService.get(officeId));
		}
		if (user.getOffice() != null && user.getOffice().getLogid() != null && !"".equals(user.getOffice().getLogid())) {
			Office office = officeService.get(user.getOffice().getLogid());
			if(office.getType().equals("1")){
				user.setCompany(office);
				user.setOffice(new Office());
			}else{
				user.setOffice(office);
				String parentIds[] = office.getParentIds().split(",");
				Office company = null;
				for(int i=parentIds.length-1 ; i>=0 ; i--){
					company = officeService.get(parentIds[i]);
					if(company.getType().equals("1")){
						break;
					}
					
				}
				if(company != null){
					user.setCompany(company);
				}
			}
		}else if (user.getCompany() != null && user.getCompany().getLogid() != null && !"".equals(user.getCompany().getLogid())) {
			Office office = officeService.findByCorpId(user.getCompany().getLogid());
			if(office == null){
				office = officeService.get(user.getCompany().getLogid());
			}
			user.setCompany(office);
		}
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
        model.addAttribute("page", page);
        model.addAttribute("officeId", officeId);
		return "modules/sys/userList";
	}
	
	@ResponseBody
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"listData"})
	public Page<User> listData(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
		return page;
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form(User user, Model model) {
		if (user.getOffice()==null || user.getOffice().getLogid()==null || "".equals(user.getOffice().getLogid())){
			if(StringUtils.isBlank(user.getLogid())){
				user.setOffice(UserUtils.getUser().getOffice());
			}else{
				user.setOffice(user.getCompany());
			}
		}
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/userForm";
	}

	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "save")
	public String save(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		
		Office office = officeService.get(request.getParameter("office.logid"));
		if(office != null && office.getType().equals("1")){
			user.setOffice(new Office());
			user.setCompany(office);
			user.setCorpId(office.getCorpId());
		}else{
			user.setOffice(office);
			String parentIds[] = office.getParentIds().split(",");
			Office company = null;
			for(int i=parentIds.length-1 ; i>=0 ; i--){
				company = officeService.get(parentIds[i]);
				if(company.getType().equals("1")){
					break;
				}
				
			}
			if(company != null){
				user.setCompany(company);
				user.setCorpId(company.getCorpId());
			}
		}
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
		}
		if (!beanValidator(model, user)){
			return form(user, model);
		}
		if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
			return form(user, model);
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		for (Role r : systemService.findAllRole()){
			if (roleIdList.contains(r.getLogid())){
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		
		
		// 保存用户信息
		systemService.saveUser(user);
		//同步站点人员信息
				SitePerson sitePerson = new SitePerson();
				sitePerson.setLogin_id(user.getLoginName());
				List<SitePerson> slist = sitePersonService.findList(sitePerson);
				if(slist.size() > 0 ){
					for (SitePerson sitePerson2 : slist) {
						sitePerson2.setLogin_name(user.getName());
						sitePerson2.setMobile(user.getMobile());
						sitePerson2.setEmail(user.getEmail());
						List<SitePerson> personlist = sitePersonService.findList(sitePerson2);
						if(personlist.size()==0){
							sitePersonService.save(sitePerson2);
							HeartBeat heartBeat = new HeartBeat();
							heartBeat.setSite_id(sitePerson2.getSite_id());
							heartBeat.setSite_name(sitePerson2.getSite_name());
							heartBeat.setTrade_code(GParameter.issued_sitePerson_trade_code);
							heartBeat.setTrade_name(GParameter.issued_sitePerson_trade_desc);
							heartBeat.setCreate_time(DateUtil.getNow());
							heartBeat.setUpdate_time(DateUtil.getNow());
							heartBeat.setPush_id(DateUtil.getLogid());
							heartBeat.setIssued_key(sitePerson2.getLogin_id());
							siteNoticeHeartBeat.pushHeart(heartBeat);
						}	
					}
				}
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
			UserUtils.clearCache();
			//UserUtils.getCacheMap().clear();
		}
		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
	
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "delete")
	public String delete(User user, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		SitePerson sitePerson = null;
		List<SitePerson> slist = sitePersonService.findList(sitePerson);
		for (SitePerson sitePerson2 : slist) {
			if(sitePerson2.getLogin_id().equals(user.getLoginName())){
				addMessage(redirectAttributes, "删除用户失败, 请先删除站点人员信息");
				return "redirect:" + adminPath + "/sys/user/list?repage";
			}
		}
		if (UserUtils.getUser().getLogid().equals(user.getLogid())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}else if (User.isAdmin(user.getLogid())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}else{
			systemService.deleteUser(user);
			addMessage(redirectAttributes, "删除用户成功");
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
	
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			  
            String fileName = "用户信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xls";
            response.setContentType("application/octet-stream;charset=utf-8");  
		    response.setHeader("Content-Disposition", "attachment;filename="  
		            + new String(fileName.getBytes(),"utf-8") + ".xls"); 
            //response.setContentType(arg0);
            response.setCharacterEncoding("utf-8");
            //控制数据权限
            User us = UserUtils.getUser();
            String corp_id = us.getCorpId();
            List<User> userlist = null;
            if(!corp_id.equals("1")){
            	user.setCorpId(corp_id);
            }
            userlist = systemService.findUser(user);
           
            //Page<User> page = systemService.findUser(new Page<User>(request, response, -1), user);
            
    		new ExportExcel("用户数据", User.class).setDataList(userlist).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }

	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<User> list = ei.getDataList(User.class);
			for (User user : list){
				try{
					if ("true".equals(checkLoginName("", user.getLoginName()))){
						user.setPassword(SystemService.entryptPassword("123456"));
						user.setCorpId(user.getCompany().getCorpId());
						BeanValidators.validateWithException(validator, user);
						systemService.saveUser(user);
						successNum++;
					}else if ("false".equals(checkLoginName("", user.getLoginName()))){
						failureMsg.append("<br/>登录名 "+user.getLoginName()+" 已存在; ");
						failureNum++;
					}else if ("wrongFormat".equals(checkLoginName("", user.getLoginName()))){
						failureMsg.append("<br/>登录名 "+user.getLoginName()+" 不符合规范，只能是字母或者手机号; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>登录名 "+user.getLoginName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>登录名 "+user.getLoginName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/";
    }
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据导入模板.xlsx";
    		List<User> list = Lists.newArrayList(); list.add(UserUtils.getUser());
    		new ExportExcel("用户数据", User.class, 2).setDataList(list).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }

	/**
	 * 验证登录名是否有效
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName !=null && loginName.equals(oldLoginName)) {
			return "true";
		} else{
			String regex = "^((((13[0-9]{1})|(15[0-9]{1}))\\d{8}))|[a-zA-Z]*{100}$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(loginName);
			if(false == m.matches()){
				return "wrongFormat";
			}else if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
				return "true";
			}
		} 
		return "false";
	}

	/**
	 * 用户信息显示及保存
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "info")
	public String info(User user, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getName())){
			if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userInfo";
			}
			currentUser.setEmail(user.getEmail());
			currentUser.setPhone(user.getPhone());
			currentUser.setMobile(user.getMobile());
			currentUser.setRemarks(user.getRemarks());
			currentUser.setPhoto(user.getPhoto());
			systemService.updateUserInfo(currentUser);
			model.addAttribute("message", "保存用户信息成功");
		}
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "modules/sys/userInfo";
	}

	/**
	 * 返回用户信息
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "infoData")
	public User infoData() {
		return UserUtils.getUser();
	}
	
	/**
	 * 修改个人用户密码
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)){
			if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userModifyPwd";
			}
			if (SystemService.validatePassword(oldPassword, user.getPassword())){
				systemService.updatePasswordById(user.getLogid(), user.getLoginName(), newPassword);
				model.addAttribute("message", "修改密码成功");
			}else{
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", user);
		return "modules/sys/userModifyPwd";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<User> list = systemService.findUserByOfficeId(officeId);
		for (int i=0; i<list.size(); i++){
			User e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_"+e.getLoginName());
			map.put("pId", officeId);
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			map.put("description",StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}
    
//	@InitBinder
//	public void initBinder(WebDataBinder b) {
//		b.registerCustomEditor(List.class, "roleList", new PropertyEditorSupport(){
//			@Autowired
//			private SystemService systemService;
//			@Override
//			public void setAsText(String text) throws IllegalArgumentException {
//				String[] ids = StringUtils.split(text, ",");
//				List<Role> roles = new ArrayList<Role>();
//				for (String id : ids) {
//					Role role = systemService.getRole(Long.valueOf(id));
//					roles.add(role);
//				}
//				setValue(roles);
//			}
//			@Override
//			public String getAsText() {
//				return Collections3.extractToString((List) getValue(), "id", ",");
//			}
//		});
//	}
}
