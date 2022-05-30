package com.algorithm.util;

import java.util.Arrays;
import java.util.Random;

public class ArrayUtils {

    public static int[] buildRandomArray(int length) {
        Random random = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(length);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = buildRandomArray(5);
        System.out.println(Arrays.toString(array));
    }
}
