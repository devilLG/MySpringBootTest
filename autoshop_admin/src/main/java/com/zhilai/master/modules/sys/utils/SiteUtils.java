package com.zhilai.master.modules.sys.utils;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhilai.master.common.utils.CacheUtils;
import com.zhilai.master.common.utils.SpringContextHolder;
import com.zhilai.master.modules.site.dao.MqttSiteDao;
import com.zhilai.master.modules.site.entity.MqttSite;
import com.zhilai.master.modules.sys.dao.UserDao;
import com.zhilai.master.modules.sys.entity.Dict;
import com.zhilai.master.modules.sys.entity.Role;
import com.zhilai.master.modules.sys.entity.User;

/**
 * 站点工具类
 * @author Administrator
 *
 */

public class SiteUtils {
	public static final String SITE_CACHE = "siteCache";
	public static final String SITE_CACHE_SITE_NAME_ = "siteCache";
	public static final String CACHE_SITE_MAP = "SiteMap";
	public static final String CACHE_SITE_LIST = "SiteList";
	private static MqttSiteDao mqttSiteDao = SpringContextHolder.getBean(MqttSiteDao.class);
	/**
	 * 根据ID获取站点信息
	 * @param id
	 * @return 取不到返回null
	 */
	public static MqttSite get(String id){
		MqttSite mqttSite = (MqttSite)CacheUtils.get(SITE_CACHE, id);
		if (mqttSite ==  null){
			mqttSite = mqttSiteDao.get(id);
			if (mqttSite == null){
				return null;
			}
			CacheUtils.put(SITE_CACHE, mqttSite.getLogid(), mqttSite);
		}
		return mqttSite;
	}
	
	/**
	 * 
	 * 查询缓存中的所有站点
	 * @param type
	 * @return
	 */
	public static List<MqttSite> getDictList(){
		@SuppressWarnings("unchecked")
		List<MqttSite>list = (List<MqttSite>) CacheUtils.get(CACHE_SITE_LIST);
		if (list==null){
			list=mqttSiteDao.findAllList(new MqttSite());
			CacheUtils.put(CACHE_SITE_LIST,list);
		}
		List<MqttSite> mqttList = (List<MqttSite>) CacheUtils.get(CACHE_SITE_LIST);
		if (mqttList == null){
			mqttList = Lists.newArrayList();
		}
		return mqttList;
	}
	
}
