package com.algorithm.dynamic;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private static char[][] ccs = {{'L', 'R'}, {'U', 'D'}};

    public static String alphabetBoardPath(String target) {
        if (target == null || target.length() < 1) {
            return null;
        }
        char[] chars = target.toCharArray();
        int preCharColumnIndex = 0;
        int preCharRowIndex = 0;
        int[][] array = new int[chars.length][2];
        boolean isZ = false;
        char preChar='a';
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int colunmIndex = (c - 'a') / 5;
            int rowIndex = (c - 'a') % 5;
            array[i][0] = colunmIndex - preCharColumnIndex;
            array[i][1] = rowIndex - preCharRowIndex;
            if (preChar==c){
                continue;
            }
            preCharColumnIndex = colunmIndex;
            preCharRowIndex = rowIndex;
            preChar=c;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            int upOrDownMoveTime = array[i][0];
            int leftOrRightMoveTimes = array[i][1];
            if (upOrDownMoveTime<0){
                append(str, ccs[1], upOrDownMoveTime);
                append(str, ccs[0], leftOrRightMoveTimes);
                str.append("!");
                continue;
            }
            if (leftOrRightMoveTimes<0){
                append(str, ccs[0], leftOrRightMoveTimes);
                append(str, ccs[1], upOrDownMoveTime);
                str.append("!");
                continue;
            }
            append(str, ccs[1], upOrDownMoveTime);
            append(str, ccs[0], leftOrRightMoveTimes);
            str.append("!");
        }
        return str.toString();
    }


    private static void append(StringBuilder stringBuilder, char[] chars, int times) {
        char c;
        if (times < 0) {
            c = chars[0];
        } else if (times > 0) {
            c = chars[1];
        } else {
            return;
        }
        times = Math.abs(times);
        for (int i = 0; i < times; i++) {
            stringBuilder.append(c);
        }
    }

    public static void main(String[] args) {
        List<String> list=letterCombinations("23");
        System.out.println(JSON.toJSONString(list));
    }

    private static Map<String,String> map=new HashMap();
    static{
        map.put("2","abc");
        map.put("3","def");
        map.put("4","ghi");
        map.put("5","jkl");
        map.put("6","mno");
        map.put("7","pqrs");
        map.put("8","tuv");
        map.put("9","wxyz");
    }
    public static List<String> letterCombinations(String digits) {
        List<String> list=new ArrayList();
        if(digits==null||digits.length()<1){
            return list;
        }
        char[] chars=digits.toCharArray();
        for(int i=0;i<chars.length;i++){
            test(i,chars,new StringBuilder(),list);
        }
        return list;
    }

    public static void test(int i,char[] chars,StringBuilder builderStr,List<String> list){
        if(i>=chars.length){
            list.add(builderStr.toString());
            return;
        }
        if(builderStr.length()==chars.length){
            list.add(builderStr.toString());
            return;
        }
        char numer=chars[i];
        String str=map.get(String.valueOf(numer));

        for(char c:str.toCharArray()){
            builderStr.append(c);
            test(i+1,chars,builderStr,list);
        }

    }
}
