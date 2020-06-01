package com.cuit.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 * @author wukong
 * @since 2017-06-30.
 */
public final class StringUtil {
    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }
    /**
     * @description 判断字符串是否为汉字
     * @author ClearLi
     * @date 2020/1/7 11:01
     * @param str
     * @return boolean 返回为true就是全都是汉字
     */
    public static boolean isAllChinese(String str) {

        if (str == null) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!isChinese(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @description 判断单个字符是否为汉字
     * @author ClearLi
     * @date 2020/1/7 11:02
     * @param c
     * @return java.lang.Boolean
     */
    public static Boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9Fa5;
    }


    /**
     * 判断字符串是否非空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
