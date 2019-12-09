package com.study.springbootweb.controller;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int arr[] = {5, 4, 3, 1, 2};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        int temp;//用于交换的临时变量
        boolean flag = false;//表示是否进行过交换
        //需要进行arr.length-1轮排序
        for (int i = 0; i < arr.length - 1; i++) {
            //每一轮排序都会将最大的元素放到尾部，下一轮排序就可以将已经排好的忽略掉
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("第" + (i + 1) + "轮排序后的数组");
//            System.out.println(Arrays.toString(arr));
            if (!flag) {
                //如果一轮排序中没有进行过交换，则说明数组已经是有序的，排序完成
                break;
            } else {
                //重置flag，进行下一轮判断
                flag = false;
            }
        }
    }

    //选择排序
    public static void selectSort(int[] arr) {
        //需要进行arr.length-1轮排序
        for (int i = 0; i < arr.length - 1; i++) {
            //假设当前位置就是最小值
            int minIndex = i;
            int min = arr[i];
            //和后面的元素进行比较
            for (int j = i + 1; j < arr.length; j++) {
                //如果当前位置的不是最小值，就重新赋值
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];
                }
            }
            //如果当前位置的不是最小值，则进行交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

    public static void insertSort(int[] arr) {
        //从第二个元素开始插入
        for (int i = 1; i < arr.length; i++) {
            //将此位置的值先存起来
            int insertVal = arr[i];
            //从此元素前一个位置开始比较
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                //如果当前位置的值小于前面的值，则将前面的值位置后移
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //此时insertIndex后面就是插入的位置
            arr[insertIndex + 1] = insertVal;
        }
    }
}
