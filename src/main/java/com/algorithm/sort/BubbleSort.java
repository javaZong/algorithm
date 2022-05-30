package com.algorithm.sort;

/**
 * 冒泡排序
 * 每次遍历都是将最大值下沉
 * @author zongchao
 */
public class BubbleSort {

    public static void bubbleSort(int[] targetArray) {
        if (targetArray == null || targetArray.length <= 1) {
            return;
        }
        int temp = 0;
        for (int i = targetArray.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                int next = j + 1;
                if (targetArray[j] > targetArray[next]) {
                    temp = targetArray[next];
                    targetArray[next] = targetArray[j];
                    targetArray[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array={3,6,7,2,10};
        bubbleSort(array);
        System.out.println();
    }
}
