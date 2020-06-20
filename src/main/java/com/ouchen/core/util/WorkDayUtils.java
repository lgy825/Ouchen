package com.ouchen.core.util;

/**
 * ClassName:Temp
 *
 * @author ligy-008494
 * @create 2019-12-30 11:55
 */
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工作日计算工具类
 * Created by MJ·J on 2019-05-24
 */
public class WorkDayUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /*private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");*/
    /**
     * 获取当前时间之前n个工作日的日期
     *
     * @param holidays 节假日（日期格式：2019-01-01,2019-01-04,2019-01-05,......）
     * @param today    当前日期（日期格式：2019-01-01 08:08:08）
     * @param num      需要设置的n个工作日
     * @return
     * @throws Exception
     */
    public static String getWorkDayStart(String holidays,String makeUpDates, String today, int num) throws Exception {
        // 转化为数组
        String[] dayArr = holidays.split(",");
        String[] upArr = holidays.split(",");
        List<String> holidayList = new ArrayList<String>(Arrays.asList(dayArr));
        List<String> maleupList = new ArrayList<String>(Arrays.asList(upArr));
        // 将字符串转换成日期
        Date date = sdf.parse(today);

        // 获取工作日
        Date workDay = getWorkDay(holidayList, maleupList,num, date, -1);
        String workDayStr = sdf.format(workDay);
        long workTime = getTime(today, workDayStr) - 1000;    // 减1秒

        return sdf.format(new Date(workTime));
    }

    /**
     * 获取当前时间之后n个工作日的日期
     *
     * @param dd    当前日期
     * @param num      需要设置的n个工作日
     * @return
     * @throws Exception
     */
    public static Date getWorkDayEnd( Date dd, int num) throws Exception {
        // 节假日
        String holidays = "2020-01-01,2020-01-24,2020-01-25,2020-01-26,2020-01-27,2020-01-28,2020-01-29,2020-01-30,2020-04-05,2020-04-06,2020-04-07,2020-05-01,2020-05-02,2020-05-03,2020-05-04,2020-05-05,2020-06-25,2020-06-26,2020-06-27,2020-10-01,2020-10-02,2020-10-03,2020-10-04,2020-10-05,2020-10-06,2020-10-07";
        String makeUpDates="2020-01-19,2020-02-01,2020-04-26,2020-05-09,2020-06-28,2020-09-27,2020-10-10,";
        // 当前时间
        String today = sdf.format(dd);
        // 转化为数组
        String[] dayArr = holidays.split(",");
        String[] upArr=makeUpDates.split(",");
        List<String> holidayList = new ArrayList<String>(Arrays.asList(dayArr));
        List<String> upDateList=new ArrayList<String>(Arrays.asList(upArr));

        // 将字符串转换成日期
        Date date = sdf.parse(today);

        // 获取工作日
        Date workDay = getWorkDay(holidayList,upDateList, num, date, 1);
        String workDayStr = sdf.format(workDay);
        long workTime = getTime(today, workDayStr) + 1000;    // 加1秒

        return new Date(workTime);

    }

    /**
     * 获取当前时间之后n个工作日的日期
     *
     * @param holidays 节假日（日期格式：2019-01-01,2019-01-04,2019-01-05,......）
     * @param today    当前日期（日期格式：2019-01-01 08:08:08）
     * @param num      需要设置的n个工作日
     * @return
     * @throws Exception
     */
    public static String getWorkDayEnd(String holidays,String makeUpDates, String today, int num) throws Exception {
        // 转化为数组
        String[] dayArr = holidays.split(",");
        String[] upArr=makeUpDates.split(",");
        List<String> holidayList = new ArrayList<String>(Arrays.asList(dayArr));
        List<String> upDateList=new ArrayList<String>(Arrays.asList(upArr));

        // 将字符串转换成日期
        Date date = sdf.parse(today);

        // 获取工作日
        Date workDay = getWorkDay(holidayList,upDateList,num, date, 1);
        String workDayStr = sdf.format(workDay);
        long workTime = getTime(today, workDayStr) + 1000;    // 加1秒

        return sdf.format(new Date(workTime));

    }

    /**
     * 获取当前时间之后n个工作日的日期
     * @param today    当前日期（日期格式：2019-01-01 08:08:08）
     * @param num      需要设置的n个工作日
     * @return
     * @throws Exception
     */
    public static String getWorkDayEnd( String today, int num) throws Exception {
        // 节假日
        String holidays = "2019-01-01,2019-01-04,2019-01-05,2019-01-06,2019-01-07,2019-01-08,2019-01-09,2019-01-10,2019-04-05,2019-04-06,2019-04-07,2019-05-01,2019-06-07,2019-06-08,2019-06-09,2019-09-13,2019-09-14,2019-09-15,2019-10-01,2019-10-02,2019-10-03,2019-10-04,2019-10-05,2019-10-06,2019-10-07";
        String makeUpDates="2020-01-19,2020-02-01,2020-04-26,2020-05-09,2020-06-28,2020-09-27,2020-10-10,";
        // 转化为数组
        String[] dayArr = holidays.split(",");
        String[] upArr=makeUpDates.split(",");
        List<String> holidayList = new ArrayList<String>(Arrays.asList(dayArr));
        List<String> upDateList=new ArrayList<String>(Arrays.asList(upArr));

        // 将字符串转换成日期
        Date date = sdf.parse(today);

        // 获取工作日
        Date workDay = getWorkDay(holidayList,upDateList, num, date, 1);
        String workDayStr = sdf.format(workDay);
        long workTime = getTime(today, workDayStr) + 1000;    // 加1秒

        return sdf.format(new Date(workTime));

    }

    /**
     * 获取工作日
     *
     * @param holidayList 节假日（日期格式：2019-01-01,2019-01-04,2019-01-05,......）
     * @param num         需要设置的n个工作日
     * @param day         目标日期
     * @return
     * @throws Exception
     */
    public static Date getWorkDay(List<String> holidayList,List<String> updateList ,int num, Date day, int n) throws Exception {
        int delay = 1;
        while (delay <= num) {
            // 获取前一天或后一天日期
            Date endDay = getDate(day, n);
            String time = sdf.format(endDay);

            //当前日期+1即tomorrow,判断是否是节假日,同时要判断是否是周末,都不是则将scheduleActiveDate日期+1,直到循环num次即可
            if(isMakeUpDate(time, holidayList)){
                delay++;
            }else if (!isWeekend(time) && !isHoliday(time, holidayList)) {
                delay++;
            }/* else if (isWeekend(time)) {
                System.out.println(time + "::是周末");
            } else if (isHoliday(time, holidayList)) {
                System.out.println(time + "::是节假日");
            }*/
            day = endDay;
        }
        return day;
    }

    public static boolean isMakeUpDate(String sdate, List<String> list){

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (sdate.equals(list.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * yyyy-MM-dd HH:mm:ss格式日期---获取时间戳精确到秒
     *
     * @param start 开始日期（日期格式：2019-01-01 08:08:08）
     * @param end   结束日期（日期格式：2019-01-01 08:08:08）
     * @return
     * @throws Exception
     */
    public static long getTime(String start, String end) throws Exception {
        if (org.apache.commons.lang.StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
            throw new RuntimeException("today is empty");
        }

        long time1 = sdf.parse(start).getTime();
        long time2 = sdf.parse(start).getTime();
        long time3 = sdf.parse(end).getTime();

        long time = time3 + (time1 - time2);

        return time;
    }

    /**
     * 获取前一天或后一天日期
     *
     * @param date 日期
     * @param n    判断参数
     * @return
     */
    public static Date getDate(Date date, int n) {
        if (n > 0) {    // 获取前一天
            date = getTomorrow(date);
        }
        if (n < 0) {    // 获取后一天
            date = getYesterday(date);
        }
        return date;
    }

    /**
     * 获取后一天的日期
     *
     * @param date
     * @return
     */
    public static Date getTomorrow(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取前一天的日期
     *
     * @param date
     * @return
     */
    public static Date getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 判断是否是周末
     *
     * @param sdate
     * @return
     * @throws Exception
     */
    public static boolean isWeekend(String sdate) throws Exception {
        Date date = sdf.parse(sdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 判断是否是节假日
     *
     * @param sdate
     * @param list
     * @return
     * @throws Exception
     */
    public static boolean isHoliday(String sdate, List<String> list) throws Exception {
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (sdate.equals(list.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}


