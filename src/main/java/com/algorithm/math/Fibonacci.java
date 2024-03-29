package com.algorithm.math;

/**
 * 斐波那契数列问题
 * Created by java_zong on 2019/5/18.
 */
public class Fibonacci {
    /**
     * 递归实现
     * 逻辑简单，但是会重复计算节点
     * @param n
     * @return
     */
    public static int fibonacciRecursive(int n) {
        System.out.println(n);
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static void main(String[] args) {
        fibonacciRecursive(3);
    }

    /**
     * 获取斐波那契数列第n项
     * 类似双指针滑动
     * @param n
     * @return
     */
    public int fibonacciLoop(int n) {
        if (n <= 1) {
            return n;
        }
        int firstNum;
        int secondNum = 1;
        int targetNum = 1;
        for (int i = 2; i < n; i++) {
            firstNum = secondNum;
            secondNum = targetNum;
            targetNum = firstNum + secondNum;
        }
        return targetNum;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）
     *
     * @param n
     * @return
     */
    public int JumpFloor(int n) {
        if (n <= 1) {
            return n;
        }
        int sum = 1;
        int subSum = 0;
        int i = 0;
        int tmp;
        while (i < n) {
            tmp = sum;
            sum = sum + subSum;
            subSum = tmp;
            ++i;
        }
        return sum;
    }

    public int jumpFloorII(int n) {
        if (n <= 2) {
            return n;
        }
        int sum;
        int totalSum = 1;
        for (int i = 0; i < n - 1; ++i) {
            sum = totalSum;
            totalSum = sum * 2;
        }

        return totalSum;
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

    public double Power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        boolean isPlus = exponent > 0;
        int ex = isPlus ? exponent : -exponent;

        double res = 1;

        while (ex != 0) {
            if ((ex & 1) == 1)
                res *= base;
            base *= base;// 翻倍
            ex >>= 1;// 右移一位
        }
//        for (int i=0;i<ex;i++){
//            res*=base;
//        }
        return isPlus ? res : 1 / res;
    }

}

