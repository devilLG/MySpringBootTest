/**  
 * @Title: JSONUtils.java
 * @Package com.lmxf.post.core.utils
 * @Description: TODO
 * @author wsj
 * @date 2016-5-13
 */
package com.zhilai.master.modules.zxml;

import com.zhilai.master.modules.utils.GConstent;

import net.sf.json.JSONObject;

/**
 * 
 * @Title: JSONUtils.java
 * @Package com.lmxf.post.core.utils
 * @Description: TODO
 * @author wsj
 * @date 2016-5-13
 */
public class JSONUtils {

	/***
	 * 将对象转换为JSON对象
	 * 
	 * @param object
	 * @return
	 */
	public static JSONObject toJSONObject(Object object) {
		return JSONObject.fromObject(object);
	}

	/**
	 * 
	 * @Description: 获取协议中的头信息
	 * @param @param json
	 * @param @param key
	 * @param @return
	 * @return String
	 * @throws
	 * @author wsj
	 * @date 2016-5-13
	 */
	public static JSONObject getRoot(JSONObject jSONObject) {
		return jSONObject.getJSONObject(GConstent.ZxmlRoot);

	}

	/**
	 * 
	 * @Description: 获取协议中的头信息
	 * @param @param json
	 * @param @param key
	 * @param @return
	 * @return String
	 * @throws
	 * @author wsj
	 * @date 2016-5-13
	 */
	public static JSONObject getHeader(JSONObject jSONObject) {
		return jSONObject.getJSONObject(GConstent.ZxmlHead);
	}

	/**
	 * 
	 * @Description: 获取协议中的主体信息
	 * @param @param json
	 * @param @param key
	 * @param @return
	 * @return String
	 * @throws
	 * @author wsj
	 * @date 2016-5-13
	 */
	public static JSONObject getBody(JSONObject jSONObject) {
		return jSONObject.getJSONObject(GConstent.ZxmlBody);
	}
	
	/**
	 * 
	 * @Description: 获取协议中的主体信息
	 * @param @param json
	 * @param @param key
	 * @param @return
	 * @return String
	 * @throws
	 * @author wsj
	 * @date 2016-5-13
	 */
	public static JSONObject getBody(String jSON) {
		JSONObject jsonNObject = toJSONObject(jSON);
		jsonNObject = getRoot(jsonNObject);
		return getBody(jsonNObject);
	}
	
	/**
	 * 
	 * @Description: 获取协议中的主体信息
	 * @param @param json
	 * @param @param key
	 * @param @return
	 * @return String
	 * @throws
	 * @author wsj
	 * @date 2016-5-13
	 */
	public static JSONObject getHeader(String jSON) {
		JSONObject jsonNObject = toJSONObject(jSON);
		jsonNObject = getRoot(jsonNObject);
		return getHeader(jsonNObject);
	}

}