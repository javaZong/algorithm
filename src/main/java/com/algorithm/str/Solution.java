package com.algorithm.str;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by java_zong on 2019/5/16.
 */
public class Solution {


    /**
     * 替换字符串中特定字符问题
     *
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str) {

        if (str == null) {
            return null;
        }

        StringBuilder newStr = new StringBuilder();
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            char ch = str.charAt(i);
            if (ch == ' ') {
                newStr.append('%');
                newStr.append('2');
                newStr.append('0');
            } else {
                newStr.append(ch);
            }
        }
        return newStr.toString();

    }

    /**
     * 无重复字符的最长子串
     * 基础思想： 窗口滑动思想，快慢指针，利用map集合来判断当前字符是否在快慢指针范围内遍历过
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int slow = 0;
        int fast = 0;
        int maxLength = 0;
        while (fast < s.length()) {
            Integer index = map.get(chars[fast]);
            if (index != null&&index>=slow) {
                slow = index + 1;
            }else {
                int length = fast - slow + 1;
                if (length > maxLength) {
                    maxLength = length;
                }
            }
            map.put(chars[fast], fast);
            fast++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("wobgrovw"));
    }
}
