package com.algorithm;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class TestMain {

    public static void main(String[] args) {
        List list=new ArrayList();
        list.add("a");
       List l= new ArrayList(list);
       int[] i=new int[10];
       int[] j=Arrays.copyOf(i,i.length);
       list=Arrays.asList(i);
    }

    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];

            if (maxSum < sum) {
                maxSum = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    public static int numDifferentIntegers(String word) {
        Set<String> set=new HashSet();
        char[] chars=word.toCharArray();
        String strNum="";
        boolean existNum=false;
        int size=0;
        for(int i=0;i<chars.length;i++){
            int temp=chars[i];
            if(temp<57){
                if(temp==48&!existNum){
                    existNum=false;
                    continue;
                }
                existNum=true;
                strNum+=chars[i];
                if (i==chars.length-1){
                    set.add(strNum);
                    continue;
                }
            }else{
                if(existNum){
                    set.add(strNum);
                    strNum="";
                }
                existNum=false;
            }
        }
        return set.size();
    }
}
