package com.study.springbootweb.controller;

/**
 * Created by xiong on 2019/11/13 18:16
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 node1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 node3 = new HeroNode2(3, "卢俊义", "玉麒麟");
        HeroNode2 node5 = new HeroNode2(5, "吴用", "智多星");
        HeroNode2 node7 = new HeroNode2(7, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.addByOrder(node1);
        doubleLinkedList.addByOrder(node7);
        doubleLinkedList.addByOrder(node5);
        doubleLinkedList.addByOrder(node3);

        doubleLinkedList.list();
    }
}

//创建一个双向链表类
class DoubleLinkedList {

    public HeroNode2 getHead() {
        return head;
    }

    //先初始化一个头节点
    private HeroNode2 head = new HeroNode2(0, "", "");

    //添加节点到双向链表的尾部
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        //遍历链表，找到最后的节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //将最后的节点的next指向新的节点
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //根据排名将英雄插入到链表的指定位置,需要找到添加位置的前一个节点
    public void addByOrder(HeroNode2 node) {
        HeroNode2 temp = head;
        boolean flag = false;//标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                flag = true;//说明当前编号已经存在
            }
            temp = temp.next;
        }
        if (flag){
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n",node.no);
        }else {
            //插入位置在最后
            if (temp.next == null) {
                temp.next = node;
                node.pre = temp;
            } else {
                //插入位置在中间
                node.next = temp.next;
                node.pre = temp;
                node.next.pre = node;
                node.pre.next = node;
            }
        }
    }

    //根据编号 修改节点信息，编号不能改，改编号就相当于添加节点了
    public void update(HeroNode2 node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = node.name;
            temp.nickName = node.nickName;
        } else {
            System.out.printf("没有找到编号%d的节点，不能修改\n", node.no);
        }
    }

    //删除节点
    public void delete(int no){
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }else if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //排除删除的是最后一个节点
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的%d节点不存在",no);
        }
    }

    //显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

/**
 * 每个heronode对象就是一个节点
 */
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向前一个节点

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}