package com.algorithm.bitoperation;

import java.util.Arrays;

/**
 * 位运算相关
 */
public class BitOperation {


    /**
     * 十进制转二进制
     * Integer.toString(n,2);
     *Integer.toBinaryString(n);
     * @param n
     * @return
     */
    public String getBinary(int n) {

        StringBuilder stringBuilder = new StringBuilder();
        while (n != 0) {
            stringBuilder.append(n % 2);
            n = n >> 1;
        }
        return stringBuilder.reverse().toString();
    }

    public String baseNeg2(int n) {

        System.out.println(n);
        StringBuilder stringBuilder = new StringBuilder();
        while (n != 0) {
            stringBuilder.append(n % -2);
            n = n >> 1;
        }
        return stringBuilder.toString();
    }

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


    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示
     *
     * @param n
     * @return
     */
    public static int umberOf1(int n) {
        int count = 0;
        while (n != 0) {
            ++count;
            n = (n - 1) & n;
        }
        return count;
    }

    public static void main(String[] args) {
        BitOperation operation = new BitOperation();
        System.out.println(operation.baseNeg2(2));
        char[] chars = {'a', 'b'};
        System.out.println(new String(chars));

    }
}
