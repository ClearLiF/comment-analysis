package com.cuit.util;

import java.util.List;

/**
 * @Author Jwei
 * @Date 2020/6/3 13:04
 */
public class ListUtil {

    /**
     * @return java.lang.String
     * @date 2020/6/3 13:06
     * @author jwei
     */
    public static String listToString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s);
            stringBuilder.append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
