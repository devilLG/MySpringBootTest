package com.zhilai.master.common.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author zhilai
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd",
			"yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

	/**24小时制yyyy-MM-dd HH:mm:ss **/
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";// 
	private static final String DATE_INIT = "yyyy-MM-dd 00:00:00";// 24小时制
	private static final String DATE_NUMBER = "yyyyMMddHHmmss";
	public static final String DATE_SIMP = "yyyy-MM-dd";
	public static final String SIMP_DATE = "MM-dd";
	private static final String MONTH_FORMAT = "yyyy-MM";
	private static final String DATE_FORMAT_en_US = "MM/dd/yyyy HH:mm:ss";
	private static final String DATE_FORMAT_en = "MM/dd/yyyy";
	/**一天的总分钟数**/
	public static final int totMinus=60*24;

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate(DATE_SIMP);
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getYearMonth() {
		return getDate(MONTH_FORMAT);
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date,DATE_SIMP);
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60
				* 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "."
				+ sss;
	}

	public static String fmDateStr(long beginTime) {

		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
				.format(beginTime);
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	// public static void main(String[] args) throws ParseException {
	// System.out.println(formatDate(parseDate("2010/3/6")));
	// System.out.println(getDate("yyyy年MM月dd日 E"));
	// long time = new Date().getTime()-parseDate("2012-11-19").getTime();
	// System.out.println(time/(24*60*60*1000));

	// String a =
	// getTimeIntervalMap("2015-01-21 00:25:23","2015-01-21 23:25:23",
	// "second");
	// System.out.println(a);
	// double a = 82800;
	// long b = 86400;
	// System.out.println(a / b);
	// System.out.println(addHour("2015-04-17 11:10:10", 3));
	/*
	 * CacheManager cacheManager =
	 * CacheManager.create("src/main/resources/ehcache.xml");
	 * 
	 * Cache cache = cacheManager.getCache("payErrorCache");
	 * 
	 * Element e = new Element("hz", "891202"); cache.put(e);
	 * 
	 * Element element = cache.get("hz"); Serializable value =
	 * element.getValue(); System.out.println(value);
	 */
	// System.out.println(addMinute(-5));
	// }

	/**
	 * 当天的 完整时间 yyyy-MM-dd 00:00:00
	 * 
	 * @return
	 */
	public static String InitDate() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_INIT);
		Date myDate = new Date();
		return df.format(myDate);
	}

	/**
	 * 当天的 完整时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currDate() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		Date myDate = new Date();
		return df.format(myDate);
	}

	/**
	 * 当天的完整日期yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String Date() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_SIMP);
		Date myDate = new Date();
		return df.format(myDate);
	}

	/**
	 * 判断为空
	 * 
	 * @param val
	 * @return 判断为空。
	 */
	public static boolean isNull(String val) {
		if (val == null || "".equals(val.trim()))
			return false;
		else
			return true;
	}

	@SuppressWarnings("deprecation")
	public static String DateToString(String dformat, Date date) {
		if (null == date) {
			return null;
		}
		if (isNull(dformat) && !DATE_FORMAT.equals(dformat)
				&& !DATE_NUMBER.equals(dformat) && !DATE_SIMP.equals(dformat)
				&& !MONTH_FORMAT.equals(dformat)
				&& !DATE_FORMAT_en_US.equals(dformat)
				&& !DATE_FORMAT_en.equals(dformat)
				&& !SIMP_DATE.equals(dformat)) {

			dformat = DATE_FORMAT;
		}
		SimpleDateFormat df = new SimpleDateFormat(dformat);
		String retDate = df.format(date);

		// 这里对时间格式作一些修改，修改为12小时制
		if (dformat.equals(DATE_FORMAT_en_US)) {
			String dateLeft = retDate.substring(0, 11);
			String dateCenter = retDate.substring(11, 13);
			int hour = date.getHours();
			if (hour < 12) {
				retDate += " AM";
			} else if (hour > 12) {
				hour = hour - 12;
				retDate += " PM";
			} else {
				retDate += " PM";
			}
			if (hour == 0) {
				hour = 12;
				dateCenter = String.valueOf(hour);
			}
			if (hour < 10) {
				dateCenter = "0" + hour;
			}
			String dateRight = retDate.substring(13, retDate.length());
			retDate = dateLeft + dateCenter + dateRight;
		}
		return retDate;
	}

	public static String DateToString(String dformat) {
		return DateToString(dformat, new Date());
	}

	public static String DateToString() {
		return DateToString(DATE_FORMAT, new Date());
	}

	public static String DateString() {
		return DateToString(DATE_SIMP, new Date());
	}

	public static String mDate() {
		return DateToString(SIMP_DATE, new Date());
	}

	/*
	 * 
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String mDay(int day) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.roll(Calendar.DAY_OF_YEAR, day);
		Date newDate = cal.getTime();
		// return newDate.toLocaleString();
		return DateToString(SIMP_DATE, newDate);
	}

	public static String AddMonthToString(String dformat, int months) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		int m = cal.get(Calendar.MONTH) + months;
		if (m < 0)
			m += -12;
		cal.roll(Calendar.YEAR, m / 12);
		cal.roll(Calendar.MONTH, months);
		Date newDate = cal.getTime();
		// return newDate.toLocaleString();
		return DateToString(dformat, newDate);
	}

	public static String AddMonthToString(int months) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		int m = cal.get(Calendar.MONTH) + months;
		if (m < 0)
			m += -12;
		cal.roll(Calendar.YEAR, m / 12);
		cal.roll(Calendar.MONTH, months);
		Date newDate = cal.getTime();
		// return newDate.toLocaleString();
		return DateToString(DATE_FORMAT, newDate);
	}

	public static String MonthToString(int months) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		int m = cal.get(Calendar.MONTH) + months;
		if (m < 0)
			m += -12;
		cal.roll(Calendar.YEAR, m / 12);
		cal.roll(Calendar.MONTH, months);
		Date newDate = cal.getTime();
		// return newDate.toLocaleString();
		return DateToString(MONTH_FORMAT, newDate);
	}

	public static String getMonth(int months) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		int m = cal.get(Calendar.MONTH) + months;
		if (m < 0)
			m += -12;
		cal.roll(Calendar.YEAR, m / 12);
		cal.roll(Calendar.MONTH, months);
		String dayBefore = new SimpleDateFormat(MONTH_FORMAT).format(cal
				.getTime());
		return dayBefore;
	}

	/**
	 * 在指定的时间上面加上或者减去几天时间
	 * 
	 * @param specifiedDay
	 * @param days
	 * @return
	 */
	public static String AddDateToString(String specifiedDay, int days) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_SIMP).parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DAY_OF_YEAR, day + days);

		String dayBefore = new SimpleDateFormat(DATE_SIMP).format(c.getTime());
		return dayBefore;
	}

	/**
	 * yyyyMMddHHmmss
	 * 
	 * @param day
	 * @return
	 */
	public static String AddDayToString(int day) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.roll(Calendar.DAY_OF_YEAR, day);
		Date newDate = cal.getTime();
		// return newDate.toLocaleString();
		return DateToString(DATE_NUMBER, newDate);
	}

	/*
	 * 
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String AddDay(int day) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.roll(Calendar.DAY_OF_YEAR, day);
		Date newDate = cal.getTime();
		// return newDate.toLocaleString();
		return DateToString(DATE_FORMAT, newDate);
	}

	/*
	 * 
	 * yyyy-MM-dd
	 */
	public static String AddDays(int day) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.roll(Calendar.DAY_OF_YEAR, day);
		Date newDate = cal.getTime();
		// return newDate.toLocaleString();
		return DateToString(DATE_SIMP, newDate);
	}

	/**
	 * 指定日期，加减天数
	 * 
	 * @param specifiedDay
	 * @param day
	 * @return
	 */
	public static String AddDays(String specifiedDay, int day) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(parseDate(specifiedDay));
		cal.roll(Calendar.DAY_OF_YEAR, day);
		Date newDate = cal.getTime();
		// return newDate.toLocaleString();
		return DateToString(DATE_SIMP, newDate);
	}

	/*
	 * 
	 * yyyy-MM-dd 00:00:00
	 */
	public static String AddDayStr(int day) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.roll(Calendar.DAY_OF_YEAR, day);
		Date newDate = cal.getTime();
		// return newDate.toLocaleString();
		return DateToString(DATE_SIMP, newDate) + " 00:00:00";
	}

	public static String AddDayToString(int day, String format) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.roll(Calendar.DAY_OF_YEAR, day);
		Date newDate = cal.getTime();
		return DateToString(format, newDate);
	}

	public static String DateNumberToString() {
		return DateToString(DATE_NUMBER, new Date());
	}

	public static String DateToString(Date date) {
		return DateToString(DATE_FORMAT, date);
	}

	@SuppressWarnings("deprecation")
	public static String Week() {
		// Calendar today = Calendar.getInstance();
		String[] Week = new String[] { "日", "一", "二", "三", "四", "五", "六" };
		Date date = new Date();
		return Week[date.getDay()];
	}

	public static Calendar getCalendar(String start) {
		try {
			Calendar startDay = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat(DATE_SIMP);
			startDay.setTime(df.parse(start));
			return startDay;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 比较两个日期的值,前者大于后者返回1，小于后者返回-1，等于返回0
	 */
	public static int compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				// System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				// System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss 与当前系统时间相比较，大于当前时间返回１，小于当前时间返回－１，等于０
	 * 
	 * @param dateStr
	 *            　　传入时间字符串
	 * @return
	 */
	public static int compare_date(String dateStr) {
		DateFormat df = null;
		if (null == dateStr || "".equals(dateStr.trim())) {
			return 0;
		}
		if (dateStr.length() <= 10) {
			df = new SimpleDateFormat(DATE_SIMP);
		} else {
			df = new SimpleDateFormat(DATE_FORMAT);
		}
		try {
			Date dt1 = df.parse(dateStr);
			Date dt2 = new Date();
			long inNum = dt1.getTime();
			long curNum = dt2.getTime();
			if (inNum > curNum) {
				// System.out.println("dt1 在dt2前");
				return 1;
			} else if (inNum < curNum) {
				// System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			// exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 返回日期相差的天数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDaysNum(String start, String end) {
		long daysNum = 0;
		try {
			SimpleDateFormat df = new SimpleDateFormat(DATE_SIMP);
			if (null == end || "".equals(end)) {
				end = DateString();
			}
			Date startDate = df.parse(start);
			Date endDate = df.parse(end);
			Calendar startDay = Calendar.getInstance();
			Calendar endDay = Calendar.getInstance();
			startDay.setTime(startDate);
			endDay.setTime(endDate);
			daysNum = endDay.getTimeInMillis() - startDay.getTimeInMillis();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(String.valueOf(daysNum / (3600 * 24 * 1000)));
	}

	/**
	 * 返回一段连续的日期
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> printDays(String start, String end) {
		List<String> dateList = new ArrayList<String>();

		try {
			SimpleDateFormat df = new SimpleDateFormat(DATE_SIMP);
			Calendar startDay = Calendar.getInstance();
			Calendar endDay = Calendar.getInstance();

			startDay.setTime(df.parse(start));
			endDay.setTime(df.parse(end));
			// 给出的日期开始日比终了日大则不执行打印
			if (startDay.compareTo(endDay) > 0) {
				return dateList;
			} else if (startDay.compareTo(endDay) == 0) {
				dateList.add(end);
				return dateList;
			}
			dateList.add(start);
			// 现在打印中的日期
			Calendar currentPrintDay = startDay;
			while (true) {
				// 日期加一
				currentPrintDay.add(Calendar.DATE, 1);
				// 日期加一后判断是否达到终了日，达到则终止打印
				if (currentPrintDay.compareTo(endDay) == 0) {
					break;
				}
				// 打印日期
				dateList.add(df.format(currentPrintDay.getTime()));
			}
			dateList.add(end);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateList;
	}

	/**
	 * 返回一段连续的月份
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> printMonths(String start, String end) {
		List<String> monthList = new ArrayList<String>();

		try {
			SimpleDateFormat df = new SimpleDateFormat(MONTH_FORMAT);
			Calendar startDay = Calendar.getInstance();
			Calendar endDay = Calendar.getInstance();

			startDay.setTime(df.parse(start));
			endDay.setTime(df.parse(end));

			// 给出的日期开始日比终了日大则不执行打印
			if (startDay.compareTo(endDay) > 0) {
				return monthList;
			} else if (startDay.compareTo(endDay) == 0) {
				monthList.add(end);
				return monthList;
			}
			monthList.add(start);
			// 现在打印中的日期
			Calendar currentPrintDay = startDay;
			while (true) {
				// 日期加一
				currentPrintDay.add(Calendar.MONTH, 1);
				// 日期加一后判断是否达到终了日，达到则终止打印
				if (currentPrintDay.compareTo(endDay) == 0) {
					break;
				}
				// 打印日期
				monthList.add(df.format(currentPrintDay.getTime()));
			}
			monthList.add(end);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return monthList;
	}

	/**
	 * 获取开始日期和结束日期的每天的间隔时间
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<Date> printDateDays(String start, String end) {
		List<Date> dateList = new ArrayList<Date>();

		try {
			SimpleDateFormat df = new SimpleDateFormat(DATE_SIMP);
			Calendar startDay = Calendar.getInstance();
			Calendar endDay = Calendar.getInstance();

			startDay.setTime(df.parse(start));
			endDay.setTime(df.parse(end));

			// 给出的日期开始日比终了日大则不执行打印
			if (startDay.compareTo(endDay) > 0) {
				return dateList;
			} else if (startDay.compareTo(endDay) == 0) {
				dateList.add(df.parse(end));
				return dateList;
			}
			dateList.add(df.parse(start));
			// 现在打印中的日期
			Calendar currentPrintDay = startDay;
			while (true) {
				// 日期加一
				currentPrintDay.add(Calendar.DATE, 1);
				// 日期加一后判断是否达到终了日，达到则终止打印
				if (currentPrintDay.compareTo(endDay) == 0) {
					break;
				}
				// 打印日期
				dateList.add(currentPrintDay.getTime());
			}
			dateList.add(df.parse(end));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateList;
	}

	/**
	 * 将字符串转换成日期
	 * 
	 * @param start
	 * @param formart
	 * @return
	 */
	public static Date StringToDate(String start, String formart) {
		Date date = null;
		try {
			if (null == start || "".equals(start)) {
				return null;
			}
			if (null == formart || "".equals(formart)) {
				formart = DATE_FORMAT;
			}
			SimpleDateFormat df = new SimpleDateFormat(formart);
			// Calendar startDay = Calendar.getInstance();
			// startDay.setTime(df.parse(start));
			date = df.parse(start);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将特定字符串转换成指定格式的日期字符串 英文字符格式转化为中文格式
	 * 
	 * @param start
	 * @param formart
	 * @return
	 */
	public static String EnToCn(String start) {
		if (null == start || start.indexOf("/") <= 0) {
			return start;
		}
		if (start.length() < 19) {
			// return DateUtils.StringToDateStr(start,
			// Constants.DATE_CN,Constants.DATE_US );
			return DateUtils.StringToDateStr(start, DATE_US, DATE_CN);
		}
		return DateUtils.StringToDateStr(start, DATE_TIME_US, DATE_TIME_CN);
	}

	public static final String DATE_TIME_CN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_CN = "yyyy-MM-dd";
	public static final String DATE_MONTH_CN = "yyyy-MM";
	public static final String DATE_TIME_US = "MM/dd/yyyy HH:mm:ss";
	public static final String DATE_US = "MM/dd/yyyy";
	public static final String DATE_MONTH_US = "MM/yyyy";

	/**
	 * 将特定字符串转换成指定格式的日期字符串 英文字符格式转化为中文格式
	 * 
	 * @param start
	 * @param formart
	 * @return
	 */
	public static String CnToEn(String start) {
		if (null == start || start.indexOf("-") <= 0) {
			return start;
		}
		if (start.length() < 19) {
			return DateUtils.StringToDateStr(start, DATE_CN, DATE_US);
		}
		return DateUtils.StringToDateStr(start, DATE_CN, DATE_US);
	}

	/**
	 * 将特定字符串转换成指定格式的日期字符串 英文字符格式转化为中文格式
	 * 
	 * @param start
	 * @param formart
	 * @return
	 */
	public static String CnToUs(String start) {
		if (null == start || start.indexOf("-") <= 0) {
			return start;
		}
		if (!isValidDate(start)) {
			return start;
		}
		if (start.length() < 19) {
			return DateUtils.StringToDateStr(start, DATE_CN, DATE_US);
		}
		return DateUtils.StringToDateStr(start, DATE_TIME_CN, DATE_TIME_US);
	}

	/**
	 * 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写； 设置lenient为false.
	 * 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValidDate(String str) {
		boolean convertSuccess = false;
		SimpleDateFormat format = new SimpleDateFormat(DATE_CN);
		try {
			format.setLenient(false);
			format.parse(str);
			convertSuccess = true;
		} catch (ParseException e) {
			convertSuccess = false;
		}
		if (convertSuccess) {
			return convertSuccess;
		}
		format = new SimpleDateFormat(DATE_TIME_CN);
		try {
			format.setLenient(false);
			format.parse(str);
			convertSuccess = true;
		} catch (ParseException e) {
			convertSuccess = false;
		}
		if (convertSuccess) {
			return convertSuccess;
		}

		return convertSuccess;
	}

	/**
	 * 将特定字符串转换成指定格式的日期字符串
	 * 
	 * @param start
	 * @param formart
	 * @return
	 */
	public static String StringToDateStr(String start, String formart,
			String formart2) {
		String dateStr = null;
		try {
			if (null != start && !"".equals(start)) {
				Date date = StringToDate(start, formart);
				dateStr = DateToString(formart2, date);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	/**
	 * 字符串转时间
	 */
	public static Date stringParseDate(String str, String parseFormat) {
		DateFormat format = null;
		Date date = null;
		try {
			format = new SimpleDateFormat(parseFormat);
			date = format.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException("字符串转时间失败", e);
		}
		return date;
	}

	/**
	 * 字符串转时间
	 */
	public static String parseDateStr(String str, String parseFormat) {
		DateFormat format = null;
		Date date = null;
		try {
			format = new SimpleDateFormat(parseFormat);
			date = format.parse(str);
		} catch (ParseException e) {
			date = new Date();
		}
		return DateToString(parseFormat, date);
	}

	public static String getSuccessDate(String successDate) {
		String alipayDate = null;
		if (null == successDate || "".equals(successDate)
				|| successDate.length() <= 0) {
			alipayDate = DateUtils.DateToString();
		} else {
			alipayDate = successDate;
		}
		return alipayDate;
	}

	/**
	 * 获取unix时间，从1970-01-01 00:00:00开始的秒数
	 * 
	 * @param date
	 * @return long
	 */
	public static long getUnixTime() {
		Date now = new Date();
		return now.getTime() / 1000;
	}

	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}

	/**
	 * 获取查询日志的默认查询时间（默认为今天） 主要用来对控制台日志查询设置一个默认查询时间
	 */
	public static String[] getDefaultDateForQueryLog(String startQueryTime,
			String endQueryTime) {
		if (startQueryTime != null && !"".equals(startQueryTime)) {
			startQueryTime += " 00:00:00";
			if (endQueryTime != null && !"".equals(endQueryTime)) {
				endQueryTime += " 23:59:59";
			} else {
				endQueryTime = DateToString();
			}
		} else {
			startQueryTime = DateUtils.DateString() + " 00:00:00";
			endQueryTime = DateUtils.DateString() + " 23:59:59";
		}
		String[] queryDate = { startQueryTime, endQueryTime };
		return queryDate;
	}

	/**
	 * 时间运算,返回一个Map,有年、月、日、时、秒、毫秒、的相差时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws RuntimeException
	 * @throws Exception
	 */
	public static String getTimeIntervalMap(String minDateString,
			String maxDateString, String expression) throws RuntimeException,
			Exception {
		if (minDateString == null || "".equals(minDateString)) {
			throw new NullPointerException("开始时间不能为空");
		}
		if (maxDateString == null || "".equals(maxDateString)) {
			throw new NullPointerException("结束时间不能为空");
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date minDate = null;
		Date maxDate = null;
		try {
			minDate = dateFormat.parse(minDateString);
			maxDate = dateFormat.parse(maxDateString);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (minDate.getTime() > maxDate.getTime()) {
			throw new RuntimeException("开始时间不能大于结束时间");
		}

		// 获取两个日期的相差秒时间
		double intervalMilliseconds = (maxDate.getTime() - minDate.getTime()) / 1000;
		if ("day".equals(expression)) {
			double days = intervalMilliseconds / 60 / 60 / 24;
			DecimalFormat format = new DecimalFormat("#.##");
			return format.format(days);
		} else if ("hour".equals(expression)) {
			double hours = intervalMilliseconds / 60 / 60;
			DecimalFormat format = new DecimalFormat("#.##");
			return format.format(hours);
		} else if ("minute".equals(expression)) {
			double minutes = intervalMilliseconds / 60;
			DecimalFormat format = new DecimalFormat("#.##");
			return format.format(minutes);
		} else if ("second".equals(expression)) {
			return intervalMilliseconds + "";
		} else {
			return intervalMilliseconds + "";
		}
	}

	/**
	 * 加小时数
	 * 
	 * @param day
	 * @param x
	 * @return
	 */
	public static String addMinute(int x) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);// 24小时制
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, x);// 24小时制
		// cal.add(Calendar.HOUR, x);12小时制
		date = cal.getTime();
		System.out.println("front:" + date);
		cal = null;
		return format.format(date);
	}

	/**
	 * 加小时数
	 * 
	 * @param day
	 * @param x
	 * @return
	 */
	public static String addHour(String day, int x) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);// 24小时制
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, x);// 24小时制
		// cal.add(Calendar.HOUR, x);12小时制
		date = cal.getTime();
		System.out.println("front:" + date);
		cal = null;
		return format.format(date);
	}

	/**
	 * 加秒数
	 * 
	 * @param day
	 * @param x
	 * @return
	 */
	public static String addSecond(String day, int x) {

		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);// 24小时制
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, x);// 24小时制
		// cal.add(Calendar.HOUR, x);12小时制
		date = cal.getTime();
		System.out.println("front:" + date);
		cal = null;
		return format.format(date);
	}

	/**
	 * 获取今天00点00分00秒至现在相差的时分
	 * 
	 * @param date
	 * @return
	 */
	public static String getHoursMin(String date) {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long between = 0;
		try {
			Date begin = dfs.parse(getDate() + " 00:00:00");
			Date end = dfs.parse(date);
			between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		long day = between / (24 * 60 * 60 * 1000);
		long hour = (between / (60 * 60 * 1000) - day * 24);
		long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
		// long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min
		// * 60);
		// long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 *
		// 1000
		// - min * 60 * 1000 - s * 1000);
		// System.out.println(day + "天" + hour + "小时" + min + "分" + s + "秒" + ms
		// + "毫秒");
		return hour + "小时" + min + "分";
	}

	/**
	 * 获取今天00点00分00秒至现在相差的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static double getMin(String date) {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long between = 0;
		try {
			Date begin = dfs.parse(getDate() + " 00:00:00");
			Date end = dfs.parse(date);
			between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// long day = between / (24 * 60 * 60 * 1000);
		// long hour = (between / (60 * 60 * 1000) - day * 24);
		double min = ((between / (60 * 1000)));
		// long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min
		// * 60);
		// long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 *
		// 1000
		// - min * 60 * 1000 - s * 1000);
		// System.out.println(day + "天" + hour + "小时" + min + "分" + s + "秒" + ms
		// + "毫秒");
		return min;
	}

	/**
	 * 获取两个时间差
	 * @param endTime 24小时制yyyy-MM-dd HH:mm:ss  不能为空，为空返回0
	 * @return 分钟=当前时间-endTime
	 */
	public static long diff(String endTime) {
		return diff(endTime,null);
	}
	/**
	 * 获取两个时间差
	 * @param endTime 24小时制yyyy-MM-dd HH:mm:ss  不能为空，为空返回0
	 * @param beginTime 24小时制yyyy-MM-dd HH:mm:ss为空时取当前时间值
	 * @return 分钟=beginTime-endTime
	 */
	public static long diff(String endTime, String beginTime) {		
		return diff(endTime,beginTime,0);
	}
	
	/**
	 * 获取两个时间差
	 * @param endTime 24小时制yyyy-MM-dd HH:mm:ss 不能为空，为空返回0
	 * @param beginTime 24小时制yyyy-MM-dd HH:mm:ss为空时取当前时间值
	 * @param flg 0min1hour2day3second 默认为0
	 * @return beginTime-endTime
	 */
	public static long diff(String endTime, String beginTime,int flg) {		
		SimpleDateFormat dfs = new SimpleDateFormat(DATE_FORMAT);
		long between = 0;
		Date begin =null;
		try {
			if(null==endTime||"".equals(endTime.trim())){
				return between;
			}
			if(null==beginTime||"".equals(beginTime.trim())){
				begin=new Date();
			}else{
				begin = dfs.parse(beginTime);
			}
			
			Date end = dfs.parse(endTime);
			between = begin.getTime()-end.getTime();// 得到两者的毫秒数
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		long day = between / (24 * 60 * 60 * 1000);
		long hour =between / (60 * 60 * 1000);
		long min =between / (60 * 1000);
		long second=between /1000;
		if(flg==0){
			return min;
		}
		if(flg==1){
			return hour;
		}
		if(flg==2){
			return day;
		}
		if(flg==3){
			return second;
		}
		return min;
	}

	/**
	 * 获取两个时间相差的分钟
	 * 
	 * @param date
	 * @return day + "天" + hour + "小时" + min + "分"
	 */
	public static String diffTime(String endTime, String beginTime) {
		SimpleDateFormat dfs = new SimpleDateFormat(DATE_FORMAT);
		long between = 0;
		try {
			Date begin = dfs.parse(beginTime);
			Date end = dfs.parse(endTime);
			between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		long day = between / (24 * 60 * 60 * 1000);
		long hour = (between / (60 * 60 * 1000) - day * 24);
		long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
		// long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min
		// * 60);
		// long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 *
		// 1000
		// - min * 60 * 1000 - s * 1000);
		return day + "天" + hour + "小时" + min + "分";
	}
	
	/**
	 * 获取两个时间相差的分钟
	 * 当前时间减去传入的时间,时间格式24小时制yyyy-MM-dd HH:mm:ss 
	 * @param date
	 * @return
	 */
	public static long diffMin(String endTime) {
		SimpleDateFormat dfs = new SimpleDateFormat(DATE_FORMAT);
		long between = 0;
		try {
			Date begin = new Date();
			Date end = dfs.parse(endTime);
			between = (begin.getTime()-end.getTime());// 得到两者的毫秒数
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		long min = between / (60*1000);	
		return min;
	}

	public static void main(String[] args) {
		//System.out.println(diffTime(DateUtil.getNow(), "2017-11-15 15:14:00"));
//		System.out.println(diffMin("2017-11-28 16:10:10")+" "+diff("2017-11-28 16:10:10","",2));
		System.out.println(DateUtils.addHour("2018-01-04 10:13:49".substring(0,14) + "00:00", Integer.parseInt("30")));
	}
}
