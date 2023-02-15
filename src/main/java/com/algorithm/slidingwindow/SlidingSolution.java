package com.algorithm.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口
 *
 * @author zongchao
 */
public class SlidingSolution {

    /**
     * 76. 最小覆盖子串
     * https://leetcode.cn/problems/minimum-window-substring/
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * 注意：
     * <p>
     * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
     * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
     * 示例 1：
     * <p>
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
     * 示例 2：
     * <p>
     * 输入：s = "a", t = "a"
     * 输出："a"
     * 解释：整个字符串 s 是最小覆盖子串。
     * 示例 3:
     * <p>
     * 输入: s = "a", t = "aa"
     * 输出: ""
     * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
     * 因此没有符合条件的子字符串，返回空字符串。
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        char[] charsOfS = s.toCharArray();
        char[] charsOfT = t.toCharArray();
        Map<Character, Integer> mapOfT = new HashMap<>();
        for (char c : charsOfT) {
            Integer size = mapOfT.get(c);
            if (size != null) {
                mapOfT.put(c, ++size);
            } else {
                mapOfT.put(c, 1);
            }
        }
        Map<Character, Integer> mapOfS = new HashMap<>();
        int left = 0;
        int right = 0;
        int minStringIndex = 0;
        int minLength = 0;
        while (right < charsOfS.length) {
            char charOfS = charsOfS[right];
            if (mapOfT.containsKey(charOfS)) {
                Integer size = mapOfS.get(charOfS);
                if (size != null) {
                    mapOfS.put(charOfS, ++size);
                } else {
                    mapOfS.put(charOfS, 1);
                }
            }
            while (mapEqual(mapOfS, mapOfT)) {
                int length = right - left + 1;
                if (minLength > length || minLength == 0) {
                    minLength = length;
                    minStringIndex = left;
                }
                Integer j = mapOfS.get(charsOfS[left]);
                if (j != null) {
                    mapOfS.put(charsOfS[left], j - 1);
                }
                left++;
            }
            right++;
        }
        return s.substring(minStringIndex, minStringIndex + minLength);
    }

    private boolean mapEqual(Map<Character, Integer> mapOfS, Map<Character, Integer> mapOfT) {
        if (mapOfS.size() != mapOfT.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : mapOfS.entrySet()) {
            Character c = entry.getKey();
            Integer value = entry.getValue();
            if (value < mapOfT.get(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SlidingSolution slidingSolution = new SlidingSolution();

    }
}
