package com.zhilai.master.modules.utils;

import java.io.StringWriter;
import java.lang.reflect.Field;

import org.apache.commons.putils.utils.DateUtil;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;

public class TemplateUtil {
	private static Logger log = Logger.getLogger(TemplateUtil.class);
	private static NullLogChute nullLogChute = new NullLogChute();
	
	@SuppressWarnings("rawtypes")
	public static String velocityTranslate(Object dataObj,String temp_cont){
		if(temp_cont == null || "".equals(temp_cont)){
			return "";
		}
		//将数据来源中的信息存到模板的字段中
		VelocityContext context = new VelocityContext();
		Class clazz = dataObj.getClass();
		try {
			Field[] fields = clazz.getDeclaredFields();
			for(Field field : fields){
					field.setAccessible(true);
					Object name = field.get(dataObj);
					//得到context
					context.put(field.getName(), name);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		//使用模板引擎替换生成内容
		StringWriter w = null;
		/* 初始化运行时引擎， 默认初始化 */
		Velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM, nullLogChute);// 配置关闭velocity运行时日志
		Velocity.init();
		/* 解析后数据的输出目标，java.io.Writer的子类 */
		w = new StringWriter();
		/* 进行解析 */
		boolean flag = false;
		flag = Velocity.evaluate(context, w, DateUtil.getMilNow(), temp_cont);
		log.info("Velocity.evaluate ,result:" + flag);
		String cont = w.toString();
		return cont;
	}

}
