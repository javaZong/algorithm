package com.algorithm.util;

public class RequestUtils {


    public static String getDomainByHost(String host) {
        host = host.substring(host.indexOf(".") + 1);
        return host;
    }

    public static void main(String[] args) {
        String domain = getDomainByHost("jieqian.sc.weibo.com");
        System.out.println(domain);
    }
}
