package com.panku.util;

import com.panku.enums.RegexEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: Regular tool
 * @author: UAIKE
 * @create: 2020-11-25
 */
public class RegexUtils {

    /**
     * regular Filter
     * @param input
     * @param type
     * @return
     */
    public static boolean regularFilter(final String input, final String type) {
        boolean isMatcher = false;
        if (StringUtils.isNotEmpty(input) && StringUtils.isNotEmpty(type)){
            RegexEnum regexEnum = RegexEnum.get(type);
            if (null != regexEnum){
                try {
                    String filterKey = regexEnum.getFilterKey();
                    //获取对应正在公式
                    String regEx = PropertiesUtils.getValueByKey("regular.properties",filterKey);
                    // 编译正则表达式
                    Pattern pattern = Pattern.compile(regEx);
                    Matcher matcher = pattern.matcher(input);
                    // 字符串是否与正则表达式相匹配
                    isMatcher = matcher.matches();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return isMatcher;
    }


}
