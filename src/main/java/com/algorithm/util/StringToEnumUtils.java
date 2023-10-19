package com.algorithm.util;

public class StringToEnumUtils {
    public static void main(String[] args){
        String strs="user_status\n" +
                "user_status_detail\n" +
                "curr_inloan_corpus_amt\n" +
                "curr_available_credit_amt\n" +
                "user_trim_tag\n" +
                "total_profit_loan_succ_cnt";
        String[] strArray=strs.split("\n");
        for (String s : strArray) {
            System.out.println(s.toUpperCase()+"(\""+s+"\",\"\"),");
        }
    }
}
