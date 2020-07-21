package com.algorithm.str;

/**
 * Created by java_zong on 2019/5/16.
 */
public class Solution {


    /**
     * 替换字符串中特定字符问题
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str) {

        if (str == null ) {
            return null;
        }

        StringBuilder newStr=new StringBuilder();
        int strLen=str.length();
        for (int i = 0; i < strLen; i++) {
            char ch=str.charAt(i);
            if (ch==' ') {
                newStr.append('%');
                newStr.append('2');
                newStr.append('0');
            }else {
                newStr.append(ch);
            }
        }
        return newStr.toString();

    }
}
