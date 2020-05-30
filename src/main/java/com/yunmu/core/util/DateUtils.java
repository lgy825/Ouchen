package com.yunmu.core.util;

import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {
	
	private static final String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HHmm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };
	private static final int[] dayOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

	public static final String DATETIME_PATTERN="yyyy-MM-dd HH:mm:ss";

	public static final String DATE_PATTERN="yyyy-MM-dd";
	/**
	 * 
	 * 将CST格式的日期串转为标准格式
	 * @param text
	 * @param format
	 * @return
	 */
	public static String datetimeFormatCst(String text, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		Date date = null;
		try {
			date = simpleDateFormat.parse(text);
			SimpleDateFormat ss = new SimpleDateFormat(format);
			return ss.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 获取明天日期字符串 格式（yyyy-MM-dd）
	 * @return
	 */
	public static String getTomorrow() {
		return formatDate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000), "yyyy-MM-dd");
	}
	
	/**
	 * 获取后天日期字符串 格式（yyyy-MM-dd）
	 * @return
	 */
	public static String getAfterTomorrow() {
		return formatDate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000 * 2), "yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 * @param pattern 格式
	 * @return
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 * @param date		时间
	 * @param pattern	格式
	 * @return
	 */
	public static String formatDate(Date date, Object... pattern) {
		if (date == null) return "";
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 * @param timestamp 时间
	 * @param pattern	 格式
	 * @return
	 */
	public static String formatDate(Timestamp timestamp, Object... pattern) {
		return formatDate(new Date(timestamp.getTime()), pattern);
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
	
	////////////////////////////////////////////////////////////
	/**
	 * 获得当前月份最大天数
	 * @return
	 */
	public static int getMaxDayOfMonth() {
		Calendar gregorianCalendar = GregorianCalendar.getInstance();
		return getMaxDayOfMonth(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获得指定月份最大天数
	 * @param year	 年份
	 * @param month 月份
	 * @return
	 */
	public static int getMaxDayOfMonth(int year, int month) {
		if (month == 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
			return 29;
		} else {
			return dayOfMonth[month - 1];
		}
	}

	/**
	 * 当前是第几周
	 * @return
	 */
	public static int getWeekOfYear() {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		return getWeekOfYear(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH) + 1, gregorianCalendar.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 当前指定的日期是第几周
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static int getWeekOfYear(int year, int month, int day) {
		month = month - 1; // 从0开始
		Calendar gregorianCalendar = GregorianCalendar.getInstance();
		gregorianCalendar.set(year, month, day);
		return gregorianCalendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 当前指定的日期是第几个季度
	 * @param date
	 * @return
	 */
	public static int getQuarterOfYear(Date date) {
		int month = Integer.valueOf(getMonth(date));
		if (month > 0 && month < 4)
			return 1;
		else if (month > 3 && month < 7)
			return 2;
		else if (month > 6 && month < 10)
			return 3;
		else
			return 4;

	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}
	
	/**
	 * 获取传入的时间的年 格式（yyyy）
	 * @param date
	 * @return
	 */
	public static String getYear(Date date) {
		return formatDate(date, "yyyy");
	}
	
	/**
	 * 获取传入的时间的年 格式（yyyy）
	 * @param timestamp
	 * @return
	 */
	public static String getYear(Timestamp timestamp) {
		return formatDate(timestamp, "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}
	
	/**
	 * 得到传入时间的月份字符串 格式（MM）
	 * @param date
	 * @return
	 */
	public static String getMonth(Date date) {
		return formatDate(date, "MM");
	}
	
	/**
	 * 得到传入时间的月份字符串 格式（MM）
	 * @param timestamp
	 * @return
	 */
	public static String getMonth(Timestamp timestamp) {
		return formatDate(timestamp, "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}
	
	/**
	 * 得到传入时间的天字符串 格式（dd）
	 * @param date
	 * @return
	 */
	public static String getDay(Date date) {
		return formatDate(date, "dd");
	}
	
	/**
	 * 得到传入时间的天字符串 格式（dd）
	 * @param timestamp
	 * @return
	 */
	public static String getDay(Timestamp timestamp) {
		return formatDate(timestamp, "dd");
	}
	
	/**
	 * 获取星期数字
	 * @param date
	 * @return
	 */
	public static String getWeekNo(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0) {
			week = 7;
		}
		return String.valueOf(week);
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 得到传入时间的星期字符串 格式（E）星期几
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date) {
		return formatDate(date, "E");
	}
	
	/**
	 * 得到传入时间的星期字符串 格式（E）星期几
	 * @param timestamp
	 * @return
	 */
	public static String getWeek(Timestamp timestamp) {
		return formatDate(timestamp, "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
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
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}
	
	/**
	 * 获取过去的分钟数
	 * @param date
	 * @return
	 */
	public static long pastMinute(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}
	
	/**
	 * 获取过去的秒数
	 * @param date
	 * @return
	 */
	public static long pastSecond(Date date) {
		long t = new Date().getTime() - date.getTime() ;
		return t / 1000 ;
	}
	
	/**
	 * 获得指定时间与当前时间比较后的别名，精确到分钟
	 * @param timestamp
	 * @return text 如:昨天10:58、去年3月8日15:13
	 */
	public static String getAliasDatetime(Timestamp timestamp){
		Date compareDate = new Date(timestamp.getTime());
		SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");
		String hourAndMin = timeFormat.format(compareDate);
		return getAliasDate(timestamp) + hourAndMin;
	}
	
	/**
	 * 获得指定时间与当前时间比较后的别名，精确到分钟
	 * @param date 日期格式：yyyy-MM-dd HH:mm
	 * @return text 如:昨天10:58、去年3月8日15:13
	 */
	public static String getAliasDatetime(String date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(format.parse(date).getTime());
		} catch (Exception e) {
			return date;
		}
		return getAliasDatetime(timestamp);
	}
	
	/**
	 * 获得指定日期与当前日期比较后的别名，精确到天
	 * @param timestamp
	 * @return text 如:昨天、去年3月8日
	 */
	public static String getAliasDate(Date timestamp) {
		long compare = timestamp.getTime();
		long now = parseDate(getDate()).getTime();
		long difOfDay = (compare - now) / (1000 * 60 * 60 * 24);
		Date compareDate = new Date(timestamp.getTime());
		String result = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日");
		String zhDate = dateFormat.format(compareDate);
		if (difOfDay == 0) {
			result = "今天";
		} else if (difOfDay == -1) {
			result = "昨天";
		} else if (difOfDay == -2) {
			result = "前天";
		} else if (difOfDay == 1) {
			result = "明天";
		} else if (difOfDay == 2) {
			result = "后天";
		} else if (difOfDay > 2) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(compareDate);
	        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        if (w < 0)
	            w = 0;
			result = weekDays[w];
		} else {
			int nowYear = Integer.parseInt(getYear(new Date()));
			int compareYear = Integer.parseInt(getYear(compareDate));
			int difOfYear = compareYear - nowYear;
			if (difOfYear == 0) {
				result = "今年";
			} else if (difOfYear == -1) {
				result = "去年";
			} else if (difOfYear == -2) {
				result = "前年";
			} else if (difOfYear == 1) {
				result = "明年";
			} else {
				result = compareYear + "年";
			}
		}
		return result += zhDate;
	}
	
	/**
	 * 获得指定日期与当前日期比较后的别名，精确到天
	 * @param date 日期格式：yyyy-MM-dd
	 * @return text 如:昨天、今天、去年3月8日
	 */
	public static String getAliasDate (String date){
		Date time = parseDate(date);
		return getAliasDate(time);
	}
	
	/**
	 * 获得指定日期的时间措
	 * @param text 日期格式yyyy-MM-dd
	 * @return 时间措
	 */
	public static long getTimes(String text) {
		return getTimes(text, "yyyy-MM-dd");
	}

	/**
	 * 按照指定格式获得指定日期的时间措
	 * 
	 * @param text  text
	 * @param format 日期格式
	 * @return 时间措
	 */
	public static long getTimes(String text, String format) {
		if(StringUtils.isBlank(text)) {
			return 0;
		}
		if(StringUtils.isBlank(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(format);
		try {
			return datetimeFormat.parse(text).getTime();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return 0;
	}
	
	public static Calendar getDateOfNextMonth(Calendar date) {  
	    Calendar lastDate = (Calendar) date.clone();  
	    lastDate.add(Calendar.MONTH, 1);  
	    return lastDate;  
	}  
	  
	/**
	 * 获取下一个月的同一天
	 * @param dateStr
	 * @return
	 */
	public static Calendar getDateOfNextMonth(String dateStr) {  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    try {  
	        Date date = sdf.parse(dateStr);  
	        Calendar c = Calendar.getInstance();  
	        c.setTime(date);  
	        return getDateOfNextMonth(c);  
	    } catch (ParseException e) {  
	        throw new IllegalArgumentException("Invalid date format(yyyy-MM-dd): " + dateStr);  
	    }  
	}  
	/**
	 * 获取今天周几
	 * @return
	 */
	public static int getWeekNum4Today(){
		return getWeekNum(new Date());
	}
	/**
	 * 获取周几
	 * @param date
	 * @return
	 */
	public static int getWeekNum(Date date){
		int[] weekDays = { 7, 1, 2, 3, 4, 5, 6 };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
//		Calendar cal = Calendar.getInstance();
//		  cal.setTime(date);
//		  return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 获取在当前日期延时指定天数后的日期
	 * @param days 指定的天数
	 * @return
	 */
	public static Date getAddDate(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		
		return calendar.getTime();
	}

	/**
	 * 时间分钟加减计算
	 * @param type add加分钟 sub减分钟
	 * @param minutes
	 * @return
	 */
	public static Date minuteCalculate(String type, int minutes){
		Calendar calendar = Calendar.getInstance();
		if(StringUtils.equals(type, "add")){
			calendar.add(Calendar.MINUTE, minutes);
		}else if(StringUtils.equals(type, "sub")){
			calendar.add(Calendar.MINUTE, -minutes);
		}
		return calendar.getTime();
	}
	/**
	 * 时间分钟加减计算
	 * @param type add加分钟 sub减分钟
	 * @param minutes
	 * @return
	 */
	public static Date minuteCalculate(Date date, String type, int minutes){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if(StringUtils.equals(type, "add")){
			calendar.add(Calendar.MINUTE, minutes);
		}else if(StringUtils.equals(type, "sub")){
			calendar.add(Calendar.MINUTE, -minutes);
		}
		return calendar.getTime();
	}

	public static Set<String> getMonthBetween(Date minDate, Date maxDate) throws ParseException {
		Set<String> result = Sets.newHashSet();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(minDate);
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(maxDate);
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()).split("-")[1]);
			curr.add(Calendar.MONTH, 1);
		}

		return result;
	}
	public static Set<String> getDaysBetween(String starDate, String endDate) throws ParseException {
		Set<String> result = Sets.newHashSet();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月

		result.add(starDate);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(sdf.parse(starDate));
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(sdf.parse(endDate));
		// 测试此日期是否在指定日期之后
		while (sdf.parse(endDate).after(calBegin.getTime()))
		{
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			result.add(sdf.format(calBegin.getTime()));
		}

		return result;
	}
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		/*	Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		System.out.println(getMonth(m));

		String mon = dateFormat.format(m);
		System.out.println("过去一个月："+mon);*/

//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
//		System.out.println(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss a"));

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	    System.out.println(sdf.format(getDateOfNextMonth("2000-03-30").getTime()));
//	    System.out.println(sdf.format(getDateOfNextMonth("2000-03-31").getTime()));
//		System.out.println(getWeekNum4Today());
//		System.out.println(getWeekNo(new Date()));

//		System.out.println(getAddDate(90));
//		System.out.println(formatDate(getAddDate(90)));
//		System.out.println(formatDate(getAddDate(90), "yyyy-MM-dd hh:mm:ss"));
//		System.out.println(minuteCalculate("add", 10));
//		System.out.println(minuteCalculate("sub", 10));
		System.out.println(getWeekNum(addDays(new Date(), 2)));


		//获取前一个月第一天
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.MONTH, -1);
		calendar1.set(Calendar.DAY_OF_MONTH,1);
		String firstDay = dateFormat.format(calendar1.getTime());
		//获取前一个月最后一天
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.DAY_OF_MONTH, 0);
		String lastDay = dateFormat.format(calendar2.getTime());
		System.out.println(lastDay);
	}


	public static boolean before(String date) {
		SimpleDateFormat simpleDateFormat=null;
		String today =DateUtils.getDate("yyyy-MM-dd");
		if (simpleDateFormat==null) {
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			Date todayDate = simpleDateFormat.parse(today);
			Date compareDate = simpleDateFormat.parse(date);
			if (compareDate.before(todayDate)){
				return true;
			}
		} catch (ParseException e) {
		  e.printStackTrace();
		}
		return false;
	}

	public static  String parseToDateTimeStr(Date date){
		return parseDateToStr(date,DATETIME_PATTERN);
	}

	public static  String parseToDateStr(Date date){
		return parseDateToStr(date,DATE_PATTERN);
	}

	public static String parseDateToStr(Date date,String pattern){
		if(date==null|| StringUtils.isEmpty(pattern))
			return null;
		SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
		String result = dateFormat.format(date);
		return result;
	}
}
