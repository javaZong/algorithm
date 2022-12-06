package com.algorithm.str;

import java.util.*;

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
     *
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
            if (index != null && index >= slow) {
                slow = index + 1;
            } else {
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

    /**
     * 最长回文字符串-中心扩散法
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {

        char[] chars = s.toCharArray();
        int max = 1;
        int maxLengthIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] a = subLongestPalindromeLength(chars, i, i);
            if (a[0] > max) {
                max = a[0];
                maxLengthIndex = a[1];
            }
            int[] b = subLongestPalindromeLength(chars, i, i + 1);
            if (b[0] > max) {
                max = b[0];
                maxLengthIndex = b[1];
            }

        }
        return s.substring(maxLengthIndex, maxLengthIndex + max);
    }


    public static int[] subLongestPalindromeLength(char[] chars, int leftCenter, int rightCenter) {
        int l = 0;
        int r = 0;
        while (leftCenter >= 0 && rightCenter < chars.length && chars[leftCenter] == chars[rightCenter]) {
            l = leftCenter;
            r = rightCenter;
            leftCenter--;
            rightCenter++;
        }
        int length = r - l + 1;
        int[] nums = new int[2];
        nums[0] = length;
        nums[1] = l;
        return nums;

    }

    /**
     * 最大回文子字符串-动态规划
     * leetcode运行时间比中心扩散法长很多
     *
     * @param str
     * @return
     */
    public static String longestPalindromeByDp(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        char[] chars = str.toCharArray();
        boolean[][] palindromeMarks = new boolean[chars.length][chars.length];

        for (int i = 0; i < chars.length; i++) {
            palindromeMarks[i][i] = true;
        }
        int maxLength = 1;
        int beginIndex = 0;
        for (int l = 2; l <= chars.length; l++) {
            for (int i = 0; i < chars.length; i++) {
                int j = l + i - 1;
                if (j > chars.length - 1) {
                    break;
                }
                if (chars[j] == chars[i]) {
                    if (j - i < 3) {
                        palindromeMarks[i][j] = true;
                    } else {
                        palindromeMarks[i][j] = palindromeMarks[i + 1][j - 1];
                    }
                } else {
                    palindromeMarks[i][j] = false;
                }
                if (palindromeMarks[i][j]) {
                    int length = j - i + 1;
                    System.out.println(length);
                    if (length > maxLength) {
                        maxLength = length;
                        beginIndex = i;
                    }
                }
            }
        }
        System.out.println(beginIndex + "," + maxLength);
        return str.substring(beginIndex, beginIndex + maxLength);
    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * f(n)=(+f(j)+)+f(n-j-1)
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        if (n < 1) {
            return null;
        }
        List<List<String>> totalList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("");
        totalList.add(list);
        list = new ArrayList<>();
        list.add("()");
        totalList.add(list);
        for (int i = 2; i < n + 1; i++) {
            list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                List<String> list_j = totalList.get(j);
                List<String> list_n_j = totalList.get(i - j - 1);
                for (String strA : list_j) {
                    for (String strB : list_n_j) {
                        String s = "(" + strA + ")" + strB;
                        list.add(s);
                    }
                }
            }
            totalList.add(list);
        }
        return totalList.get(totalList.size() - 1);
    }


    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
