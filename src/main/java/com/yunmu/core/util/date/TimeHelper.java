package com.yunmu.core.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 获得当前本地时间的类
 */
public class TimeHelper {
	/**
	 * 返回"5小时前",1分钟前;
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendlyTime(Date time) {
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");
		// 判断是否是同一天
		String curDate = dateFormater2.format(cal.getTime());
		String paramDate = dateFormater2.format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0) {
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			} else {
				ftime = hour + "小时前";
			}
			return ftime;
		}
		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0) {
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			} else {
				ftime = hour + "小时前";
			}
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = dateFormater2.format(time);
		}
		return ftime;
	}

	/**
	 * 返回相隔多少天,多少分钟，多少小时,多少秒
	 * 
	 * @param arg0
	 * @param arg1
	 * @return 需要改进。用迭代或者递归，，赶时间
	 */
	public static String getDiffStr(Date stTime, Date endTime) {
		long diff = endTime.getTime() - stTime.getTime();
		if (diff < 0) {
			return "over";
		}
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long day = diff / nd;// 计算差多少天
		long hour = diff % nd / nh;// 计算差多少小时
		long min = diff % nd % nh / nm;// 计算差多少分钟
		long sec = diff % nd % nh % nm / ns;// 计算差多少秒

		return day + "天" + hour + "时" + min + "分" + sec + "秒";
	}

	/**
	 * 格式化时间的方法
	 * 
	 * @param Timestamp
	 * @return 今天/昨天/前天 15:00
	 * @author Ray
	 * @时间 2011-08-29
	 */
	public static String getTime(Date time) {
		if (time == null) {
			return "";
		}

		StringBuilder timeStr = new StringBuilder();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
		Date nowTime = new Date();
		Date oldTime = time;
		try {
			oldTime = ft.parse(ft.format(time));
			nowTime = ft.parse(ft.format(nowTime));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		long diff = nowTime.getTime() - oldTime.getTime();
		long days = diff / 1000 / 60 / 60 / 24;
		String temp = time.toString().substring(11, 16);
		if (days == 0L) {
			timeStr.append("今天" + temp);

			return timeStr.toString();
		} else if (days == 1L) {
			timeStr.append("昨天" + temp);

			return timeStr.toString();
		} else if (days == 2L) {
			timeStr.append("前天" + temp);

			return timeStr.toString();
		} else {
			String is00 = time.toString().substring(5, 6);
			String is01 = time.toString().substring(8, 9);
			if ((!is00.equals("0")) && (!is01.equals("0"))) {
				timeStr.append(time.toString().substring(5, 7) + "月"
						+ time.toString().substring(8, 10) + "日"
						+ time.toString().substring(11, 16));
				return timeStr.toString();
			} else if ((!is00.equals("0")) && (is01.equals("0"))) {
				timeStr.append(time.toString().substring(5, 7) + "月"
						+ time.toString().substring(9, 10) + "日"
						+ time.toString().substring(11, 16));

				return timeStr.toString();
			} else if ((is00.equals("0")) && (!is01.equals("0"))) {
				timeStr.append(time.toString().substring(6, 7) + "月"
						+ time.toString().substring(8, 10) + "日"
						+ time.toString().substring(11, 16));

				return timeStr.toString();
			} else {
				timeStr.append(time.toString().substring(6, 7) + "月"
						+ time.toString().substring(9, 10) + "日"
						+ time.toString().substring(11, 16));

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
	 * @param format
	 *            :格式化时间样式
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
	public static String formatDate(long time) {
		Date date = new Date(time);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = formatter.format(date);

		return timeStr;
	}

	/**
	 * 描述:格式化日期方法
	 * **/
	public static String formatDate(long time, String format) {
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
	public static String getDefaultCurrentLocatlTime() {
		return getCurrentLocalTime("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 描述:字符串转日期方法
	 * 
	 * @param dateStr
	 *            :日期字符串
	 * @return Date:日期对象
	 * **/
	public static Date charConvertDate(String dateStr) {
		try {
			return charConvertDate("yyyy-MM-dd HH:mm:ss", dateStr);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 描述:字符串转日期方法
	 * 
	 * @param format
	 *            :日期格式
	 * @param dateStr
	 *            :日期字符串
	 * @return Date:日期对象
	 * **/
	public static Date charConvertDate(String format, String dateStr) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);

			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取上传的图片的路径 return string
	 * **/
	public static String getTimeFolder(String uid) {
		try {
			String temp = "/";
			StringBuilder floder = new StringBuilder();
			floder.append(getSomeTime("year") + temp);
			floder.append(getSomeTime("month") + temp);
			floder.append(getSomeTime("date") + temp);
			floder.append(uid);
			return floder.toString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 描述:获取当前时间中的属性
	 * 
	 * @param 要获取的属性
	 * **/
	@SuppressWarnings("deprecation")
	public static int getSomeTime(String con) {
		try {
			Date d = new Date();
			if (con.equals("year")) {
				return d.getYear() + 1900;
			} else if (con.equals("month")) {
				return d.getMonth() + 1;
			} else if (con.equals("date")) {
				return d.getDate();
			} else if (con.equals("hour")) {
				return d.getHours();
			} else if (con.equals("min")) {
				return d.getMinutes();
			} else if (con.equals("second")) {
				return d.getSeconds();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 得到指定月的天数
	 * */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 格式化时间的方法
	 * 
	 * @param Timestamp
	 * @return 今天/昨天/前天/4月25日
	 * @author Ray
	 * @时间 2011-08-29
	 */
	public static String getMonthDateTime(Date time) {
		if (time == null) {
			return "";
		}
		try {
			StringBuilder timeStr = new StringBuilder();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
			Date nowTime = new Date();
			Date oldTime = time;

			oldTime = ft.parse(ft.format(time));
			nowTime = ft.parse(ft.format(nowTime));

			long diff = nowTime.getTime() - oldTime.getTime();
			long days = diff / 1000 / 60 / 60 / 24;
			Calendar cal = Calendar.getInstance();
			cal.setTime(time);
			String date = cal.get(Calendar.DATE) + "";
			String month = (cal.get(Calendar.MONTH) + 1) + "";
			if (days == 0L) {
				timeStr.append("今天");
				return timeStr.toString();
			} else if (days == 1L) {
				timeStr.append("昨天");
				return timeStr.toString();
			} else if (days == 2L) {
				timeStr.append("前天");
				return timeStr.toString();
			}
			timeStr.append(month + "月" + date + "日");
			return timeStr.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 描述:判断相隔多少天方法
	 * **/
	public static long dayDiff(String startTime, String endTime, String format) {
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		// long nh = 1000*60*60;//一小时的毫秒数
		// long nm = 1000*60;//一分钟的毫秒数
		// long ns = 1000;//一秒钟的毫秒数
		long diff;

		try {
			SimpleDateFormat sd = new SimpleDateFormat(format);
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			long day = diff / nd;// 计算差多少天
			// long hour = diff%nd/nh;//计算差多少小时
			// long min = diff%nd%nh/nm;//计算差多少分钟
			// long sec = diff%nd%nh%nm/ns;//计算差多少秒
			// 输出结果

			return day;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 描述:判断相隔多少天方法
	 * **/
	public static long dayDiff(Date startTime, Date endTime) {
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		// long nh = 1000*60*60;//一小时的毫秒数
		// long nm = 1000*60;//一分钟的毫秒数
		// long ns = 1000;//一秒钟的毫秒数
		long diff;

		try {
			// 获得两个时间的毫秒时间差异

			// System.out.println(endTime.getTime() + " " + endTime.getTime());
			diff = endTime.getTime() - startTime.getTime();

			// System.out.println("diff:" + diff);

			long day = diff / nd;// 计算差多少天
			// long hour = diff%nd/nh;//计算差多少小时
			// long min = diff%nd%nh/nm;//计算差多少分钟
			// long sec = diff%nd%nh%nm/ns;//计算差多少秒
			// 输出结果

			return day;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取当前一周内的日期数据
	 * 
	 * @param mdate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<Date> dateToWeek(Date mdate, String type) {
		int b = mdate.getDay();
		Date fdate;
		List<Date> list = new ArrayList<Date>();
		Long fTime = mdate.getTime() - b * 24 * 3600000;

		if (type != null && type.equals("workday")) {
			for (int a = 1; a < 6; a++) {
				fdate = new Date();
				fdate.setTime(fTime + (a * 24 * 3600000));
				list.add(fdate);
			}
		} else if (type != null && type.equals("all")) {
			for (int a = 0; a < 7; a++) {
				fdate = new Date();
				fdate.setTime(fTime + (a * 24 * 3600000));
				list.add(fdate);
			}
		} else {
			return null;
		}

		return list;
	}

	/**
	 * 月初
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (1 - index));
		return calendar.getTime();
	}

	/**
	 * 月末
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (-index));
		return calendar.getTime();
	}

	/**
	 * 下一个月
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNext(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 当前季度的开始时间，即2012-01-1 00:00:00
	 * 
	 * @return
	 */
	public Date getCurrentQuarterStartTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 0);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 3);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 4);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 9);
			}
			c.set(Calendar.DATE, 1);
			now = TimeHelper.charConvertDate(TimeHelper.formatDate(c.getTime(),
					"yyyy-MM-dd") + " 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 当前季度的结束时间，即2012-03-31 23:59:59
	 * 
	 * @return
	 */
	public Date getCurrentQuarterEndTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 2);
				c.set(Calendar.DATE, 31);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 8);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}
			now = TimeHelper.charConvertDate(TimeHelper.formatDate(c.getTime(),
					"yyyy-MM-dd") + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	public static void main(String[] args) {
		String[] array = new String[] { "00:00-00:59", "01:00-01:59",
				"02:00-02:59", "03:00-03:59", "04:00-04:59", "05:00-05:59",
				"06:00-06:59", "07:00-07:59", "08:00-08:59", "09:00-09:59",
				"10:00-10:59", "11:00-11:59", "12:00-12:59", "13:00-13:59",
				"14:00-14:59", "15:00-15:59", "16:00-16:59", "17:00-17:59",
				"18:00-18:59", "19:00-19:59", "20:00-20:59", "21:00-21:59",
				"22:00-22:59", "23:00-23:59" };

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear - 6);

		String sql = " select count(*) as count,name from em_statistics_base where create_date>=time1 and create_date<=time2 ";
		StringBuffer sb = new StringBuffer();
		long endTime = new Date().getTime();
		String temp1 = "", temp2 = "", temp = "";
		for (long i = cal.getTime().getTime(); i <= endTime; i += 3600 * 1000 * 24) {

			for (String s : array) {
				temp = (TimeHelper.formatDate(i).substring(0, 10) + " " + s);

				temp1 = temp.substring(0, 16);
				temp2 = temp.replace(temp1 + "-", temp1.substring(0, 11));
				sb.append(" union ");
				sb.append(sql.replace("time1", "'" + temp1 + "'")
						.replace("time2", "'" + temp2 + "'")
						.replace("name", "'" + temp + "' as name"));
			}

		}
		sql = sb.toString().replaceFirst("union", "");
		// System.out.println(sql);

		Date date = charConvertDate("yyyy-mm", "2016-08");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (-index));
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-mm-dd");
		System.out.println(sd.format(calendar.getTime()));

	}
}
