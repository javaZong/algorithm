package com.algorithm.prefix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 前缀和
 */
public class PrefixSumSolution {

    /**
     * 给定一个放有字母和数字的数组，找到最长的子数组，且包含的字母和数字的个数相同。
     *
     * 返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。
     *
     * 示例 1:
     *
     * 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
     *
     * 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
     * 示例 2:
     *
     * 输入: ["A","A"]
     *
     * 输出: []
     * @param array
     * @return
     */
    public String[] findLongestSubarray(String[] array) {
        int targetStart=0;
        int sum=0;
        Map<Integer,Integer> map=new HashMap();
        map.put(0,-1);
        int maxLength=0;
        for(int i=0;i<array.length;i++){
            if(isDigit(array[i])){
                sum++;
            }else{
                sum--;
            }
            Integer index=map.get(sum);
            if(index!=null){
                int length=i-index;
                if(length>maxLength){
                    targetStart=index+1;
                    maxLength=length;
                }
            }else{
                map.put(sum,i);
            }
        }

        if(maxLength<1){
            return new String[0];
        }

        System.out.println(targetStart+"_"+maxLength);
        return Arrays.copyOfRange(array,targetStart,targetStart+maxLength);
    }

    private boolean isDigit(String str){
        return Character.isDigit(str.charAt(0));
    }
}
