package com.algorithm.bitmap;

/**
 * 自定义bitmap
 */
public class MyBitMap {

    private int[] array;

    /**
     * size=数组个数*32bit (maxValue)
     *
     * @param sizeBit
     */
    public MyBitMap(int sizeBit) {
        array = new int[sizeBit / 32];
    }

    /**
     * 需要第index位上状态（0或1）
     *
     * @param index
     * @return
     */
    public int findBitValue(int index) {
        int arrayIndex = index / 32;
        int bitIndex = index % 32;
        return (array[arrayIndex] >> bitIndex) & 1;
    }

    /**
     * 把第i位设置成1
     * @param index
     */
    public void setBiValueTrue(int index) {
        int arrayIndex = index / 32;
        int bitIndex = index % 32;
        array[arrayIndex] = array[arrayIndex] | (1 << bitIndex);
    }

    public void setBiValueFalse(int index) {
        int arrayIndex = index / 32;
        int bitIndex = index % 32;
        array[arrayIndex] = array[arrayIndex] &(~(1 << bitIndex));
    }
}
