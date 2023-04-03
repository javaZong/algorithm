package com.algorithm.str;

public class KmpSolution {

    /**
     * 请你在 text 字符串中找出 str 字符串的第一个匹配项的下标（下标从 0 开始），不匹配返回-1
     *
     * @param text
     * @param str
     * @return
     */
    public int getIndexOfByKmp(String text, String str) {
        if (text == null || text.length() < 1 || str == null || str.length() < 1 || text.length() < str.length()) {
            return -1;
        }
        char[] textChars = text.toCharArray();
        char[] strChars = str.toCharArray();
        int[] next = getEqualLongestOfPrefixAndSuffix(str);
        int textIndex = 0;
        int strIndex = 0;
        while (textIndex < textChars.length && strIndex < strChars.length) {
            if (textChars[textIndex] == strChars[strIndex]) {
                textIndex++;
                strIndex++;
                continue;
            }
            // strIndex位于0的话，只能让textIndex后移比较
            if (strIndex == 0) {
                textIndex++;
                continue;
            }
            // 寻找最大的公共部分，这样只需要拿前缀的下一个位置与当前textIndex比较即可
            // 因为textIndex前和strIndex前的位置都是相等，此刻假设strIndex前最大相等长度是3
            // 则textIndex前面3个字符与strIndex公共相等的部分是一样的
            strIndex = next[strIndex];
        }
        return strIndex == strChars.length ? textIndex - strIndex : -1;
    }

    /**
     * 寻找字符串下，每个字符前面部门，前缀和后缀的最大相等长度
     * 例如：
     * happyandhappys  s的最大公共长度位5  前缀happy=后缀hasspy
     * h的公共前缀没有，认为-1
     * a的公共前缀只有一个，认为是整体部分，不计入，为0
     *
     * @param str
     * @return
     */
    public int[] getEqualLongestOfPrefixAndSuffix(String str) {
        if (str == null || str.length() < 1) {
            return new int[]{-1};
        }

        char[] chars = str.toCharArray();
        int[] array = new int[chars.length];
        array[0] = -1;
        array[1] = 0;
        // 前缀的下一个位置（等同于i的上的最大公共长度）
        int left = 0;
        int currentIndex = 2;
        while (currentIndex < chars.length) {

            if (chars[currentIndex - 1] == chars[left]) {
                // 这里为什么不适用 array[currentIndex]=array[currentIndex-1]+1,因为left的位置是在变化的，不能和currentIndex-1画等号
                array[currentIndex++] = ++left;
            } else if (array[left] < 0) {
                array[currentIndex++] = 0;
            } else {
                left = array[left];
            }
        }
        return array;
    }

    public static void main(String[] args) {
        KmpSolution kmpSolution=new KmpSolution();
        System.out.println(kmpSolution.getIndexOfByKmp("sadbutsad","tsad"));

    }

}
