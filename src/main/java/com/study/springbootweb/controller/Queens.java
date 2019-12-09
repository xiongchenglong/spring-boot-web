package com.study.springbootweb.controller;

public class Queens {

    int max = 8;
    //数组下标i+1表示第i+1个皇后放在第i+1行，array[i]表示第几列
    int[] array = new int[max];
     static int count = 0;
    public static void main(String[] args) {
        Queens queens = new Queens();
        queens.check(0);
        System.out.printf("一共有%d种解法",count);
    }

    //放置第n个皇后
    public void check(int n) {
        if (n == max) { //此时8个皇后都放置完毕
            print();
            return;
        }
        //使用循环能让第n个皇后将所有能放置的位置都放一边，这样得到的就是所有的摆放结果
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)) { //不冲突时继续放置下一个皇后
                check(n + 1);
            }
            //如果冲突就放置在下一列
        }
    }

    //查看当我们放置第n个皇后时，是否和前面已经摆放的皇后有冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //array[i] == array[n]时表示两个皇后在同一列
            //Math.abs(n - i) == Math.abs(array[n] - array[i])时表示两个皇后在同一条斜线上
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //打印数组
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
