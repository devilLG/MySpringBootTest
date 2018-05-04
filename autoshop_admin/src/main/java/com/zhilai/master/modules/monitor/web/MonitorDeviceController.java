package com.zhilai.master.modules.monitor.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhilai.master.common.persistence.Page;
import com.zhilai.master.common.utils.DateUtils;
import com.zhilai.master.common.utils.excel.ExportExcel;
import com.zhilai.master.common.web.BaseController;
import com.zhilai.master.modules.monitor.entity.CabinetDevice;
import com.zhilai.master.modules.monitor.entity.DeviceEnv;
import com.zhilai.master.modules.monitor.entity.DeviceInfo;
import com.zhilai.master.modules.monitor.service.CabinetDeviceService;
import com.zhilai.master.modules.monitor.service.DeviceEnvService;
import com.zhilai.master.modules.monitor.service.DeviceInfoService;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.service.SequenceIdService;
import com.zhilai.master.modules.sys.utils.DictUtils;
import com.zhilai.master.modules.utils.GParameter;

/**
 * 
 *Title:MonitorDeviceController
 *Description:设备管理控制层
 *@author ChengJiawei
 *@date 2018年4月2日 上午9:30:05
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/monitor/monitorDevice")
public class MonitorDeviceController extends BaseController{
	
	@Autowired
	private DeviceInfoService deviceInfoService;
	
	@Autowired
	private DeviceEnvService deviceEnvService;
	
	@Autowired
	private SequenceIdService sequenceIdService;
	
	@Autowired
	private CabinetDeviceService cabinetDeviceService;
	
	@ModelAttribute
	public DeviceInfo get(@RequestParam(required = false)String deviceId){
		if (org.apache.commons.lang3.StringUtils.isNotBlank(deviceId)) {
			DeviceInfo deviceInfo =  deviceInfoService.get(deviceId);
			if (deviceInfo == null) {
				return new DeviceInfo();
			}
			List<DeviceEnv> deviceEnvList = deviceEnvService.findByDeviceId(deviceId);
			if (deviceEnvList.size()>0) {
				deviceInfo.setDeviceEnvList(deviceEnvList);
			} else {
				deviceEnvList = new ArrayList<DeviceEnv>();
				deviceInfo.setDeviceEnvList(deviceEnvList);
			}
			return deviceInfo;
		} else {
			DeviceInfo deviceInfo = new DeviceInfo();
			List<DeviceEnv> deviceEnvList = new ArrayList<DeviceEnv>();
			deviceInfo.setDeviceEnvList(deviceEnvList);
			return deviceInfo;
		}
	}
	
	//跳转主页
	//@RequiresPermissions("sys:deviceInfo:view")
	@RequestMapping(value = {"list",""})
	public String list(DeviceInfo deviceInfo,HttpServletRequest req,HttpServletResponse resp,Model model){
		Page<DeviceInfo> page = deviceInfoService.findPage(new Page<DeviceInfo>(req, resp), deviceInfo);
		List<Dict> deviceInfoList = DictUtils.getDictList(GParameter.deviceInfo_key);
		List<Dict> deviceModelList = DictUtils.getDictList(GParameter.deviceModel_key);
		List<Dict> deviceBardList = DictUtils.getDictList(GParameter.deviceBard_key);
		List<Dict> deviceMfrsList = DictUtils.getDictList(GParameter.deviceMfrs_key);
		Map<String, String> deviceInfoMap = DictUtils.getDictMap(GParameter.deviceInfo_key);
		Map<String, String> deviceModelMap = DictUtils.getDictMap(GParameter.deviceModel_key);
		Map<String, String> deviceBardMap = DictUtils.getDictMap(GParameter.deviceBard_key);
		Map<String, String> deviceMfrsMap = DictUtils.getDictMap(GParameter.deviceMfrs_key);
		Map<String, String> errorLevelInfoMap = DictUtils.getDictMap(GParameter.errorLevelInfo_key);
		model.addAttribute("page", page);
		model.addAttribute("deviceInfoList", deviceInfoList);
		model.addAttribute("deviceModelList", deviceModelList);
		model.addAttribute("deviceBardList", deviceBardList);
		model.addAttribute("deviceMfrsList", deviceMfrsList);
		model.addAttribute("deviceInfoMap", deviceInfoMap);
		model.addAttribute("deviceModelMap", deviceModelMap);
		model.addAttribute("deviceBardMap", deviceBardMap);
		model.addAttribute("deviceMfrsMap", deviceMfrsMap);
		model.addAttribute("errorLevelInfoMap", errorLevelInfoMap);
 		return "/modules/monitor/monitorDeviceList";
	}
	
	//跳转添加设备信息页面
	@RequestMapping(value="form")
	public String form(DeviceInfo deviceInfo,Model model,HttpServletRequest req,HttpServletResponse resp){
		if (deviceInfo.getLogid()==null) {
			String deviceId = sequenceIdService.findNextVal("8888","device_id", 7); 
			deviceInfo.setDeviceId(deviceId);
		} else {
			String deviceSize = deviceInfo.getDeviceSize();
			String[] size = deviceSize.split("\\*");
			deviceInfo.setDeviceLength(size[0]);
			deviceInfo.setDeviceWidth(size[1]);
			deviceInfo.setDeviceHeight(size[2]);
		}
		List<Dict> deviceInfoList = DictUtils.getDictList(GParameter.deviceInfo_key);
		List<Dict> errorLevelList = DictUtils.getDictList(GParameter.errorLevelEnv_key);
		List<Dict> deviceUnitList = DictUtils.getDictList(GParameter.deviceUnit_key);
		List<String> imageList = new ArrayList<String>();
		if (deviceInfo.getDevicePic()!=null&&deviceInfo.getDevicePic()!="") {
			String[] images = deviceInfo.getDevicePic().split("\\|");
			for (int i = 1; i < images.length; i++) {
				imageList.add(images[i]);
			}
		}
		List<DeviceEnv> deviceEnvList = deviceEnvService.findByDeviceId(deviceInfo.getDeviceId());
		deviceInfo.setDeviceEnvList(deviceEnvList);
		model.addAttribute("deviceInfo", deviceInfo);
		model.addAttribute("imageList", imageList);
		model.addAttribute("errorLevelList", errorLevelList);
		model.addAttribute("deviceInfoList", deviceInfoList);
		model.addAttribute("deviceUnitList", deviceUnitList);
		return "/modules/monitor/monitorDeviceForm";
	}
	
	//跳轉圖片上傳頁面
    @RequestMapping(value="toImgUpload/{deviceId}")
	public String toImgUpload(@PathVariable("deviceId") String deviceId,Model model,HttpServletRequest req) throws JsonProcessingException{
    	String path =  req.getSession().getServletContext().getRealPath("/")+"upload\\materielImg\\"+deviceId;
        File file = new File(path);
		String[] images = file.list();
		List<String> imageList = new ArrayList<String>();
		if (null!=images&&images.length>0) {
			for (int i = 0; i < images.length; i++) {
				imageList.add("\\upload\\materielImg\\"+deviceId+"\\"+images[i]);
			}
		}
    	ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("materielImgList", mapper.writeValueAsString(imageList));
		return "modules/monitor/imageUpload";
	}
    
    //页面显示上传图片
    @RequestMapping(value="findImgUrl")
    @ResponseBody
    public List<String> findImgUrl(HttpServletRequest req){
    	String deviceId = req.getParameter("deviceId");
    	String path =  req.getSession().getServletContext().getRealPath("/")+"upload\\materielImg\\"+deviceId;
        File file = new File(path);
		String[] images = file.list();
		List<String> imageList = new ArrayList<String>();
		if (null!=images&&images.length>0) {
			for (int i = 0; i < images.length; i++) {
				imageList.add("\\upload\\materielImg\\"+deviceId+"\\"+images[i]);
			}
		}
    	return imageList;
    }  
    
    //上传图片
    @RequestMapping(value="imgUpload")
    @ResponseBody
    public Map<String, Object> imgUpload(@RequestParam(value="images",required=false) MultipartFile images, HttpServletRequest req,HttpServletResponse resp ,Model model) throws IOException{
    	String deviceId = req.getParameter("deviceId");
    	String path = req.getSession().getServletContext().getRealPath("/upload")+"/materielImg/"+deviceId+"/";
    	String originalFileName = images.getOriginalFilename();
    	String fileName = deviceId+"-"+System.nanoTime()+originalFileName.substring(originalFileName.lastIndexOf("."));
    	InputStream imgageInStream = images.getInputStream();
    	File file = new File(path);
    	if(!file.exists()){
    		file.mkdirs();
    	}
    	File outFile = new File(path+fileName);
    	outFile.createNewFile();
    	FileOutputStream outFileOs = new FileOutputStream(outFile);
    	byte[] bbuf = new byte[1024];
    	int hasRead = 0;
    	PrintWriter pw = resp.getWriter();
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
			while((hasRead = imgageInStream.read(bbuf))>0){
				outFileOs.write(bbuf);
			}
            map.put("success", "上传成功");
            map.put("originalFilename", images.getOriginalFilename());
		} catch (IOException e) {
			pw.write("default");
			map.put("error", "上传失败");
			map.put("originalFilename", images.getOriginalFilename());
		}
    	outFileOs.close();
    	return map;
    }
    
    //删除图片
    @RequestMapping(value="imgDelete")
    @ResponseBody
    public Map<String, Object> imgDelete(HttpServletRequest req,Model model){
    	String deviceId = req.getParameter("deviceId");
    	String originalName = req.getParameter("originalName");
    	String path =  req.getSession().getServletContext().getRealPath("/")+"upload\\materielImg\\"+deviceId+"\\"+originalName;
    	File file = new File(path);
    	file.delete();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("target", deviceId);
    	map.put("originalName", originalName);
    	return map;
    }
    
/*	//检测DeviceName是否重复
	@ResponseBody
	@RequestMapping(value = "checkDeviceName/{deviceName}")
	public String checkDeviceName(@PathVariable("deviceName") String deviceName) {
		String code[] = deviceName.split(",");
		DeviceInfo deviceInfo = deviceInfoService.findOneByDeviceName(code[0]);
		if (code.length == 1) {
			if (deviceInfo!=null) {
				return "0";
			}
		} else {
			if (deviceInfo.getDeviceId().equals(code[1])) {
				return "1";
			} else {
				return "0";
			}
		}
		return "1";
	}*/

    //保存信息
	@RequestMapping(value = "save")
	public String save(DeviceInfo deviceInfo,HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		deviceInfo.setLogid(request.getParameter("logid"));
		deviceInfo.setDeviceId(request.getParameter("deviceId"));
		//保存图片地址
		String path =  request.getSession().getServletContext().getRealPath("/")+"upload\\materielImg\\"+deviceInfo.getDeviceId();
        File file = new File(path);
		String[] images = file.list();
		String devicePic = "";
		if (null!=images&&images.length>0) {
			for (int i = 0; i < images.length; i++) {
				devicePic+="|"+"\\upload\\materielImg\\"+deviceInfo.getDeviceId()+"\\"+images[i];
			}
		}
		deviceInfo.setDevicePic(devicePic);
		//保存设备尺寸 
		String deviceSize = "";
		String deviceLength = request.getParameter("deviceLength");
		String deviceWidth = request.getParameter("deviceWidth");
		String deviceHeight = request.getParameter("deviceHeight");
		deviceSize = deviceLength+"*"+deviceWidth+"*"+deviceHeight;
		deviceInfo.setDeviceSize(deviceSize);
		deviceInfoService.save(deviceInfo);
		deviceEnvService.deleteList(deviceInfo.getDeviceId());
		String [] envCodeList = request.getParameterValues("envCode");
		String [] envNameList = request.getParameterValues("envName");
		String [] envSvalueList = request.getParameterValues("envSvalue");
		String [] envEvalueList = request.getParameterValues("envEvalue");
		String [] envWsvalueList = request.getParameterValues("envWsvalue");
		String [] envWevalueList = request.getParameterValues("envWevalue");
		String [] unitNameList = request.getParameterValues("unitName");
		String [] errorLevelList = request.getParameterValues("errorLevelEvn");
		if (envCodeList!=null&&envCodeList.length>0) {
			for (int i = 0; i < envCodeList.length; i++) {
				DeviceEnv deviceEnv = new DeviceEnv();
				deviceEnv.setDeviceId(deviceInfo.getDeviceId());
				deviceEnv.setDeviceCode(deviceInfo.getDeviceCode());
				deviceEnv.setDeviceName(deviceInfo.getDeviceName());
				deviceEnv.setDeviceModel(deviceInfo.getDeviceModel());
				deviceEnv.setEnvCode(envCodeList[i]);
				deviceEnv.setEnvName(envNameList[i]);
				deviceEnv.setEnvSvalue(envSvalueList[i]);
				deviceEnv.setEnvEvalue(envEvalueList[i]);
				deviceEnv.setEnvWsvalue(envWsvalueList[i]);
				deviceEnv.setEnvWevalue(envWevalueList[i]);
				deviceEnv.setUnitName(unitNameList[i]);
				deviceEnv.setErrorLevel(errorLevelList[i]);
				deviceEnvService.save(deviceEnv);
			}
		}
		addMessage(redirectAttributes, "保存设备信息成功");
		return "redirect:" + adminPath + "/monitor/monitorDevice/list?repage";
	}
    
	//删除
	@RequestMapping(value = "delete")
	public String delete(DeviceInfo deviceInfo, RedirectAttributes redirectAttributes ,HttpServletRequest req){
		CabinetDevice obj = new CabinetDevice();
		obj.setDeviceId(deviceInfo.getDeviceId());
		List<CabinetDevice> cabinetDeviceList = cabinetDeviceService.findList(obj);
		if (cabinetDeviceList.size()>0) {
			addMessage(redirectAttributes, "删除失败，已在柜子设备中配置！");
			return "redirect:" + adminPath + "/monitor/monitorDevice/";
		}
		deviceInfoService.delete(deviceInfo);
		//删除图片
		String path =  req.getSession().getServletContext().getRealPath("/")+"upload\\materielImg\\"+deviceInfo.getDeviceId();
		File file = new File(path);
		file.delete();
		deviceEnvService.deleteList(deviceInfo.getDeviceId());
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/monitor/monitorDevice/";
	}
	
	//导出
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(DeviceInfo deviceInfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "设备信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<DeviceInfo> page = deviceInfoService.findPage(new Page<DeviceInfo>(request, response, -1), deviceInfo);
            List<DeviceInfo> deviceInfos = page.getList();
    		new ExportExcel("设备信息", DeviceInfo.class).setDataList(deviceInfos).write(request, response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出设备信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/monitor/monitorDevice/list?repage";
    }
	
	//方法镜查询设备
	@RequestMapping(value="showDeviceInfo")
	public String showDeviceInfo(Model model){
		DeviceInfo deviceInfo = new DeviceInfo();
		List<DeviceInfo> deviceInfos =deviceInfoService.findList(deviceInfo);
		Map<String, String> deviceInfoMap = DictUtils.getDictMap(GParameter.deviceInfo_key);
		Map<String, String> deviceModelMap = DictUtils.getDictMap(GParameter.deviceModel_key);
		Map<String, String> deviceBardMap = DictUtils.getDictMap(GParameter.deviceBard_key);
		Map<String, String> deviceMfrsMap = DictUtils.getDictMap(GParameter.deviceMfrs_key);
		model.addAttribute("deviceInfoList", deviceInfos);
		model.addAttribute("deviceInfoMap", deviceInfoMap);
		model.addAttribute("deviceModelMap", deviceModelMap);
		model.addAttribute("deviceBardMap", deviceBardMap);
		model.addAttribute("deviceMfrsMap", deviceMfrsMap);
		return "modules/monitor/monitorDeviceSelect";
	}

}
