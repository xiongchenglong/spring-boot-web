package com.study.springbootweb.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by xiong on 2019/10/30 15:47
 */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建原始二维数组[11][11]
        //0：表示没有棋子，1：表示黑子，2：表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        System.out.println("-------二维数组----------");
        for (int[] ints : chessArr1) {
            for (int data : ints) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //遍历二维数组，得到非零数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        /**
         * 将二维数组转化成稀疏数组
         */
        //创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给第一行赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非零的值存放到sparseArr中
        int count = 0;//用于记录是第几个非零数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println("----------稀疏数组---------------");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }


        /**
         * 将稀疏数组存入文件
         */
        BufferedWriter bw = new BufferedWriter(new FileWriter("a.txt"));
        for (int i = 0; i < sparseArr.length; i++) {
            bw.write(sparseArr[i][0] + " " + sparseArr[i][1] + " " + sparseArr[i][2]);
            bw.newLine();
            bw.flush();
        }
        bw.close();

        /**
         * 从文件中读取稀疏数组
         */
        BufferedReader br = new BufferedReader(new FileReader("a.txt"));
        String line = null;
        boolean flag = true;
        int[][] chessArr3 = new int[1][1];
        while ((line = br.readLine()) != null) {
            String[] s = line.split("\\s+");
            if (flag) {
                chessArr3 = new int[Integer.parseInt(s[0])][Integer.parseInt(s[1])];
                flag = false;
            } else {
                chessArr3[Integer.parseInt(s[0])][Integer.parseInt(s[1])] = Integer.parseInt(s[2]);
            }
        }
        br.close();
        System.out.println("-----------从文件中读取稀疏数组-----------");
        for (int[] ints : chessArr3) {
            for (int data : ints) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        /**
         * 将稀疏数组转化成二维数组
         */
        //先读取稀疏数组的第一行，根据第一行数据创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        //再读取稀疏数组后几行的数据(第二行开始)，并赋给原始的二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("----------恢复后的二维数组---------------");
        for (int[] ints : chessArr2) {
            for (int data : ints) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }
}
