package com.zhilai.master.common.utils;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class MessageSourceHelper {

	private static MessageSource messageSource;

	public void setMessageSource(
			ReloadableResourceBundleMessageSource messageSource) {
		MessageSourceHelper.messageSource = messageSource;
	}
	
	public static MessageSource getMessageSource() {
		return messageSource;
	}
	
	public static String getMessage(String code) {
		String msg =null;
		try {
			Locale cuLocal=currentLocale();
			msg=messageSource.getMessage(code, null, "",cuLocal);
		} catch (Exception e) {
			return code;
		}
		return msg != null&&!"".equals(msg.trim()) ? msg.trim() : code;
		
	}
	
	public static String getMessageValue(String code) {
		String msg = messageSource.getMessage(code, null, "",currentLocale());
		return msg != null&&!"".equals(msg.trim()) ? msg.trim() : code;
		
	}
	
	public static String getMessage(String code,Locale locale) {
		String msg = messageSource.getMessage(code, null, "",locale);
		return msg != null ? msg.trim() : "";
		
	}
	
	public static String getMessage(String code, Object[] args) {
		String msg = messageSource.getMessage(code, args, "",currentLocale());
		return msg != null ? msg.trim() : "";
	}
	
	 public static Locale currentLocale() {		  
		 return LocaleContextHolder.getLocale();
	 }
	 
	 public static Boolean isUs() {		  
		 if (MessageSourceHelper.currentLocale().getCountry().equals("US")) {
				return true;
			}
		 return false;
	 }
	 
}
