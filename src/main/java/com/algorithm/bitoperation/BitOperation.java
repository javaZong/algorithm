package com.algorithm.bitoperation;

/**
 * 位运算相关
 */
public class BitOperation {


    /**
     * 只使用位运算计算两数之和
     *
     * @param a
     * @param b
     * @return
     */
    public int sum(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    /**
     * 获得一个数的相反数
     *
     * @param n
     * @return
     */
    public int negNum(int n) {
        return sum(~n, 1);
    }
}
