package com.study.springbootweb.controller;

/**
 * Created by xiong on 2019/11/14 11:13
 */
public class JosephuDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.list();
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建一个环形单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = null;

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     *
     * @param startNo  表示从第几个小孩开始数
     * @param countNum 表示数几下
     * @param nums     表示最初圈中小孩的个数
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //对数据进行校验
        if (first == null || startNo < 1 || startNo > nums || nums < 1 || countNum < 1) {
            System.out.println("参数不合理");
            return;
        }
        Boy helper = first;
        //将helper指向环形链表的最后一个节点
        while (true){
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //报数前先让first和helper移动startNo-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //报数时让first和helper移动countNum-1次
        //循环处理，直到圈中只有一个节点
        while (true) {
            //first和helper相等的时候说明圈中只有一个节点
            if (helper == first) {
                break;
            }
            //找到出圈的小孩
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈\n", first.getNo());
            //将小孩从圈中除去
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号：%d\n", first.getNo());
    }

    //添加小孩节点，构建环形链表
    public void addBoy(int nums) {
        //对nums做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不对");
            return;
        }
        Boy cur = null;//辅助指针，帮助构建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);
                cur = first;
            } else {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }

    public void list() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，因此需要一个辅助指针完成遍历
        Boy cur = first;
        while (true) {
            System.out.printf("小孩的编号:%d \n", cur.getNo());
            if (cur.getNext() == first) {
                //此时链表循环结束
                break;
            } else {
                //让节点后移
                cur = cur.getNext();
            }
        }
    }
}

//创建Boy类，表示一个节点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy(int no) {
        this.no = no;
    }

}