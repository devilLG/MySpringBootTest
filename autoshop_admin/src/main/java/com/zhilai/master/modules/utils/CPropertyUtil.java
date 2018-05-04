package com.zhilai.master.modules.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


public class CPropertyUtil  extends PropertyPlaceholderConfigurer{
	private static Map<String, Object> ctxPropertiesMap;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) {
		// super.processProperties(beanFactoryToProcess, props);
		ctxPropertiesMap = new HashMap();
		for (Iterator localIterator = props.keySet().iterator(); localIterator.hasNext();) {
			Object key = localIterator.next();
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
	}

	public static Object getContextProperty(String name) {
		if(null==ctxPropertiesMap){
			return name;
		}
		return ctxPropertiesMap.get(name);
	}

}