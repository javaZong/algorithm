package com.algorithm;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

public class TestMain {

    public static void main(String[] args) {
        char a='0';
        int c=a;
        System.out.println(c);
        System.out.println(numDifferentIntegers("xtimt5kqkz9osexe56ezwwninlyeeqsq5m99904os3ygs12t31n1et4uwzmt5kvv6teisobuxt10k33v1aaxysg4y8nsivxdp3fo9dr7x58m8uc4ofm41ai77u8cvzr5r3s97f5otns59ubqk57xwl00xsp9w2oodt6yxcbscloyr9c2su8gca1ly6rrjufm25luhxhesxwn7bk1as9na4cbabxk"));
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
