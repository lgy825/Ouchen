package com.yunmu;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();

        String first = null;//第一天
        String last = null;//第二天
       // c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        first = sdf.format(c.getTime());
        System.out.println(first);

//        //判断获取的参数如果为-1
//        if(month.equals("-1")){
//            c.add(Calendar.MONTH, -1);
//            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
//            first = sdf.format(c.getTime());
//
//            //获取上月的最后一天
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.DAY_OF_MONTH, 1);
//            calendar.add(Calendar.DATE, -1);
//            last = sdf.format(calendar.getTime());
//        }else{
//            c.add(Calendar.MONTH, 0);
//            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
//            first = sdf.format(c.getTime());
//
//            //获取当前月最后一天
//            Calendar ca = Calendar.getInstance();
//            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
//            last = sdf.format(ca.getTime());
//        }

    }
}
