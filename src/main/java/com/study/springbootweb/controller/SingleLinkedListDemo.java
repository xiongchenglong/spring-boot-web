package com.study.springbootweb.controller;

/**
 * Created by xiong on 2019/11/1 17:16
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //singleLinkedList.add(node1);
        //singleLinkedList.add(node2);
        //singleLinkedList.add(node3);
        //singleLinkedList.add(node4);

        singleLinkedList.addByOrder(node1);
        singleLinkedList.addByOrder(node4);
        singleLinkedList.addByOrder(node3);
        singleLinkedList.addByOrder(node2);

        //HeroNode node = new HeroNode(2, "小卢", "墨麒麟");
        //singleLinkedList.update(node);

        //singleLinkedList.delete(1);

        HeroNode heroNode = singleLinkedList.find(5);
        System.out.println(heroNode);
        //singleLinkedList.list();
    }
}

/**
 * 定义SingleLinkedList 管理英雄
 */
class SingleLinkedList {

    //先初始化一个头节点
    private HeroNode head = new HeroNode(0,"","");

    //添加节点到单向链表的尾部
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        //遍历链表，找到最后的节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //将最后的节点的next指向新的节点
        temp.next = heroNode;
    }

    //根据排名将英雄插入到链表的指定位置,需要找到添加位置的前一个节点
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
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
            node.next = temp.next;
            temp.next = node;
        }
    }

    //根据编号 修改节点信息，编号不能改，改编号就相当于添加节点了
    public void update(HeroNode node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;
        HeroNode temp = head;
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

    //删除节点，需要找到被删除节点的前一个节点
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }else if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d节点不存在",no);
        }
    }

    //查找节点
    public HeroNode find(int no){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }else if (temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            return temp;
        }else {
            return null;
        }
    }

    //显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
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
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
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