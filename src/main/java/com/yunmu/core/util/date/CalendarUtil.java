package com.yunmu.core.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获得当前本地时间的类
 */
public class CalendarUtil {
	
	/**
	 * 格式化时间的方法
	 * @param Timestamp
	 * @return 今天/昨天/前天 15:00
	 * @author Ray 
	 * @时间 2011-08-29
	 */
	public static String getTime(Date time){
		if(time==null){
			return "";
		}
		
		StringBuilder timeStr = new StringBuilder();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
		Date nowTime=new Date();
		Date oldTime=time;
		try {
			oldTime=ft.parse(ft.format(time));
			nowTime=ft.parse(ft.format(nowTime));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	    long diff = nowTime.getTime() - oldTime.getTime();
	    long days = diff / 1000 / 60 / 60 / 24;
	    String temp = time.toString().substring(11,16);
	    if(days==0L){
	    	timeStr.append("今天"+temp);
	    	
	    	return timeStr.toString();
	    }else if(days==1L){
	    	timeStr.append("昨天"+temp);
	    	
	    	return timeStr.toString();
	    }else if(days==2L){
	    	timeStr.append("前天"+temp);
	    	
	    	return timeStr.toString();
	    }else{
	    	String is00=time.toString().substring(5,6);
	    	String is01=time.toString().substring(8,9);
	    	if((!is00.equals("0"))&&(!is01.equals("0"))){
	    		timeStr.append(time.toString().substring(5,7)+"月"+time.toString().substring(8,10)+"日"+time.toString().substring(11,16));
	    		return timeStr.toString();
	    	}else if((!is00.equals("0"))&&(is01.equals("0"))){
	    		timeStr.append(time.toString().substring(5,7)+"月"+time.toString().substring(9,10)+"日"+time.toString().substring(11,16));
	    		
	    		return timeStr.toString();
	    	}else if((is00.equals("0"))&&(!is01.equals("0"))){
	    		timeStr.append(time.toString().substring(6,7)+"月"+time.toString().substring(8,10)+"日"+time.toString().substring(11,16));
	    		
	    		return timeStr.toString();
	    	}else{
	    		timeStr.append(time.toString().substring(6,7)+"月"+time.toString().substring(9,10)+"日"+time.toString().substring(11,16));
			
	    		return timeStr.toString();
	    	}	    
	    }	
	}

	/**
	 * 描述:获得当前本地时间
	 */
	public static Date getCurrentLocalTime() {
		
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * 描述:获得当前本地时间
	 * 
	 * @param format:格式化时间样式
	 * @return 当前本地化时间
	 */
	public static String getCurrentLocalTime(String format) {
		// 月份范围为0－11
		Date d = Calendar.getInstance().getTime();
		// 24小时制
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(d).toString();
	}
	
	/**
	 * 描述:格式化日期方法
	 * **/
	public static String formatDate(long time){
		Date date = new Date(time);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = formatter.format(date);
		
		return timeStr;
	}
	
	/**
	 * 描述:格式化日期方法
	 * **/
	public static String formatDate(long time, String format){
		Date date = new Date(time);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String timeStr = formatter.format(date);
		
		return timeStr;
	}
	
	/**
	 * 描述:格式化日期方法
	 * **/
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = formatter.format(date);
		
		return time;
	}
	
	/**
	 * 描述:格式化日期方法
	 * **/
	public static String formatDate(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String time = formatter.format(date);
		
		return time;
	}
	
	/**
	 * 描述:获得默认当前时间
	 * **/
	public static String getDefaultCurrentLocatlTime(){
		return  getCurrentLocalTime("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 描述:字符串转日期方法
	 * @param	dateStr:日期字符串
	 * @return	Date:日期对象
	 * **/
	public static Date charConvertDate(String dateStr) {
		try{
			return charConvertDate("yyyy-MM-dd HH:mm:ss",dateStr);
		}catch(RuntimeException e){
			throw e;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 描述:字符串转日期方法
	 * @param	format:日期格式
	 * @param	dateStr:日期字符串
	 * @return	Date:日期对象
	 * **/
	public static Date charConvertDate(String format, String dateStr) {
		try{
			DateFormat dateFormat = new SimpleDateFormat(format);
			
			return dateFormat.parse(dateStr);
		}catch(ParseException e){
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 获取上传的图片的路径
	 * return string
	 * **/
	public static String getTimeFolder(String uid){
		try {
			String temp="/";
			StringBuilder floder=new StringBuilder();
	        floder.append(getSomeTime("year")+temp);
	        floder.append(getSomeTime("month")+temp);
	        floder.append(getSomeTime("date")+temp);
	        floder.append(uid);
			return floder.toString();
		} catch (Exception e) {
			return "";
		}
	}
	
	
	
	
	/**
	 * 描述:获取当前时间中的属性
	 * @param	要获取的属性
	 * **/
	@SuppressWarnings("deprecation")
	public static int getSomeTime(String con) {
		try{
			Date d = new Date();
			if(con.equals("year")){
				return d.getYear()+1900;
			}else if(con.equals("month")){
				return d.getMonth()+1;
			}else if(con.equals("date")){
				return d.getDate();
			}else if(con.equals("hour")){
				return d.getHours();
			}else if(con.equals("min")){
				return d.getMinutes();
			}else if(con.equals("second")){
				return d.getSeconds();
			}else{
				return 0;
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/** 
	 * 得到指定月的天数 
	 * */  
	public static int getMonthLastDay(int year, int month)  
	{  
	    Calendar a = Calendar.getInstance();
	    a.set(Calendar.YEAR, year);
	    a.set(Calendar.MONTH, month - 1);
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
	    int maxDate = a.get(Calendar.DATE);
	    return maxDate;  
	}  

}

