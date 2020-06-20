package com.ouchen.core.util;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;

/**
 * ClassName:CalculateUtils
 *
 * @author licho
 * @create 2017-11-30 19:26
 */
public class CalculateUtils {

    private static final Logger log= LoggerFactory.getLogger(CalculateUtils.class);//日志记录

    /**
     * 对bean中所有Double类型的变量按照scale位精度进行处理，结果值除以100
     * @param bean
     * @param scale
     * @param <T>
     * @return
     */
    public static <T> void calculateMoneyToYuan(T bean,int scale){
        if(bean==null)
            return ;
        Class clazz=bean.getClass();
        Method[] allMethods=clazz.getDeclaredMethods();
        List<Method> getterMethods= Lists.newArrayList(); //表示其所有的getter方法
        //筛选出getter方法
        for (Method allMethod : allMethods) {
            if(allMethod.getName().startsWith("get")){
                getterMethods.add(allMethod);
            }
        }
        try {
            for (Method getterMethod : getterMethods) {
                    String setterMethodName="set"+getterMethod.getName().substring(3);
                    Object value=getterMethod.invoke(bean);//获取到属性值
                    if(null==value)
                        continue;
                    Class valueType=value.getClass();//获取其值的类型
                    //如果是Double或者double类型，对其进行处理
                    Double tempValue=null;
                    if(valueType.equals(Double.class)){
                        if(value!=null)
                            tempValue=(Double) value;
                    }else if(valueType.equals(double.class)){
                        tempValue=new Double((double)value);
                    }else{
                        continue;
                    }
                    double resultValue= DecimalCalculate.div(tempValue,100d,scale);
                Method setterMethod = clazz.getDeclaredMethod(setterMethodName, valueType);
                setterMethod.invoke(bean,resultValue);
            }
        } catch (IllegalAccessException e) {
            log.debug("错误的类权限访问",e);
        } catch (InvocationTargetException e) {
            log.debug("错误的调用异常",e);
        } catch (NoSuchMethodException e) {
            log.debug("未找到该属性的getter方法");
        }
    }

    public static <T> void calculateMoneyToYuan(Collection<T> bean,int scale){
        for (T t : bean) {
            calculateMoneyToYuan(t,2);
        }
    }

    /**
     * 将value数值进行进度转化
     * @param value
     * @param scale
     * @return
     */
    public static String toDecimal(Object value,int scale){
        if(scale<0){
            scale=0;
        }
        StringBuilder builder=new StringBuilder("0");
        if(scale>0){
            builder.append(".");
            for (int i=scale;i>0;i--){
                builder.append("0");
            }
        }
        DecimalFormat format=new DecimalFormat(builder.toString());
        BigDecimal decimal=null;
        if(null==value){
            throw new RuntimeException("进行数值转化的值不能为空");
        }
        Class clzz=value.getClass();
        if(clzz.equals(String.class)){
            String v1=(String)value;
            decimal=new BigDecimal(v1);
        }else if(clzz.equals(Integer.class)||clzz.equals(Integer.TYPE)){
            int v2=(int) value;
            decimal=new BigDecimal(v2);
        }else if(clzz.equals(Long.class)||clzz.equals(Long.TYPE)){
            long v3=(long) value;
            decimal=new BigDecimal(v3);
        }else if(clzz.equals(Float.class)||clzz.equals(Float.TYPE)){
            float v4=(float)value;
            decimal=new BigDecimal(v4);
        }else if(clzz.equals(Double.class)||clzz.equals(Double.TYPE)){
            double v5=(double) value;
            decimal=new BigDecimal(v5);
        }else {
            throw  new RuntimeException("不支持的转化类型");
        }
        double result = decimal.doubleValue();
        String format1 = format.format(result);
        return format1;
    }
}
