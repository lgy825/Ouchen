package com.yunmu.core.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类 ClassName:RegxUtils
 *
 * @author yangbin
 * @create 2017-11-27 18:00
 */
public class RegxUtils {
    public static List<String> getValueFromJson(String regx, String json) {
        Matcher matcher= Pattern.compile(regx).matcher(json);
        List<String> result = Lists.newArrayList();
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }

    public static Boolean valid(String regx, String json) {
        Matcher matcher= Pattern.compile(regx).matcher(json);
        boolean succ = false;
        if (matcher.find()) {
            succ = true;
        }
        return succ;
    }
}
