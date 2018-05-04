package com.zhilai.master.common.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LocaleChangeInterceptor extends HandlerInterceptorAdapter {

	public static final String DEFAULT_PARAM_NAME = "locale";

	private String paramName = DEFAULT_PARAM_NAME;

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		String localeString = req.getParameter(this.paramName);
		Locale locale = null;
		try {
			locale = (Locale) req.getSession().getAttribute(Constants.CURRENT_LANGUAGE);
		} catch (Exception e) {
			locale = null;
		}

		if (null == locale) {
			if ("en_US".equals(localeString)) {
				req.getSession().setAttribute(Constants.CURRENT_LANGUAGE, Locale.US);
			} else if ("en_UK".equals(localeString)) {
				req.getSession().setAttribute(Constants.CURRENT_LANGUAGE, Locale.UK);
			} else if ("zh_CN".equals(localeString)) {
				req.getSession().setAttribute(Constants.CURRENT_LANGUAGE, Locale.CHINA);
			} else {
				req.getSession().setAttribute(Constants.CURRENT_LANGUAGE, Locale.CHINA);
			}
		} else {

			if (null != localeString && !localeString.equals(locale.getCountry() + "_" + locale.getLanguage())) {
				if ("en_US".equals(localeString)) {
					req.getSession().setAttribute(Constants.CURRENT_LANGUAGE, Locale.US);
				} else if ("en_UK".equals(localeString)) {
					req.getSession().setAttribute(Constants.CURRENT_LANGUAGE, Locale.UK);
				} else if ("zh_CN".equals(localeString)) {
					req.getSession().setAttribute(Constants.CURRENT_LANGUAGE, Locale.CHINA);
				} else {
					req.getSession().setAttribute(Constants.CURRENT_LANGUAGE, Locale.CHINA);
				}
			}
		}
		localeString = null;
		return super.preHandle(req, resp, handler);
	}

}
