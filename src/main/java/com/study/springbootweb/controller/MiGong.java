package com.study.springbootweb.controller;

public class MiGong {
    public static void main(String[] args) {
        //创建二维数组模拟迷宫
        int[][] map = new int[8][7];
        //将上下左右全都置为1
        for (int i = 0 ; i < 7;i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0 ; i < 8;i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        for (int i = 0;i < 8;i++) {
            for (int j = 0;j < 7;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------");
        setWay(map,1,1);

        for (int i = 0;i < 8;i++) {
            for (int j = 0;j < 7;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路,map表示地图，【i,j】表示起点的坐标；
     * 如果小球能到map[6,5]，表示找到了通路；
     * 当map[x,y]为0时，表示该点没有走过，为1时表示墙，为2时表示通路可以走，为3时表示该点已经走过但走不通；
     * 确定一个策略，下-->右-->上-->左，如果该点走不通，再回溯；
     * @param map
     * @param i
     * @param j
     * @return 找到通路返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        //通路已经找到
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                //当前这个点还没走过时按照策略走
                //假设该点可以走通
                map[i][j] = 2;
                if (setWay(map,i+1,j)) { //向下
                    return true;
                } else if (setWay(map,i,j+1)) { //向右
                    return true;
                } else if (setWay(map,i-1,j)) { //向上
                    return true;
                } else if (setWay(map,i,j-1)) { //向左
                    return true;
                } else {
                    //该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
