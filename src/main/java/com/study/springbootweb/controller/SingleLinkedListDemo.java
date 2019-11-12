package com.study.springbootweb.controller;

import java.util.Stack;

/**
 * Created by xiong on 2019/11/1 17:16
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node3 = new HeroNode(3, "卢俊义", "玉麒麟");
        HeroNode node5 = new HeroNode(5, "吴用", "智多星");
        HeroNode node7 = new HeroNode(7, "林冲", "豹子头");

        HeroNode node2 = new HeroNode(2, "孙悟空", "齐天大圣");
        HeroNode node4 = new HeroNode(3, "猪八戒", "天蓬元帅");
        HeroNode node6 = new HeroNode(6, "沙和尚", "卷帘大将");
        HeroNode node8 = new HeroNode(8, "唐僧", "金蝉子");

        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();

        singleLinkedList1.addByOrder(node1);
        singleLinkedList1.addByOrder(node5);
        singleLinkedList1.addByOrder(node3);
        singleLinkedList1.addByOrder(node7);

        singleLinkedList2.addByOrder(node2);
        singleLinkedList2.addByOrder(node4);
        singleLinkedList2.addByOrder(node6);
        singleLinkedList2.addByOrder(node8);

        merge(singleLinkedList1.getHead(), singleLinkedList2.getHead());
        singleLinkedList2.list();

        //HeroNode node = new HeroNode(2, "小卢", "墨麒麟");
        //singleLinkedList.update(node);

        //singleLinkedList.delete(1);

        //HeroNode heroNode = singleLinkedList.find(5);
        //singleLinkedList.list();
        //int length = getLength(singleLinkedList.getHead());
        //System.out.println(length);

        //HeroNode indexNode = findIndexNode(singleLinkedList.getHead(), 2);
        //System.out.println(indexNode);
        //reverseList(singleLinkedList.getHead());
        //singleLinkedList.list();

        //reversePrint(singleLinkedList.getHead());

        //HeroNode haveRing = isHaveRing(singleLinkedList.getHead());
        //System.out.println(haveRing);
    }


    /**
     * 获取单链表的节点个数（有头节点的不统计头节点）
     * @param head 头节点
     * @return
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode node = head.next;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第K个节点；
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }

        HeroNode no = head.next;
        for (int i = 0; i < size - index; i++) {
            no = no.next;
        }
        return no;
    }

    /**
     * 单链表的反转
     * @param head
     */
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        HeroNode cur = head.next;
        HeroNode next = null;
        //定义一个新的头节点
        HeroNode reverseHead = new HeroNode(0, "", "");

        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur != null) {
            next = cur.next;//记录cur的下一个节点
            cur.next = reverseHead.next;//将reverseHead后面的节点放到cur的后面
            reverseHead.next = cur;//将cur插入到reverseHead的后面
            cur = next;//遍历下一个节点
        }
        //替换头节点
        head.next = reverseHead.next;
    }

    /**
     * 利用栈的先进后出特性，实现逆序打印的效果
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }

        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将节点压入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 判断单链表是否有环，有环则返回入环的节点，使用快慢指针方法
     * @param head
     * @return
     */
    public static HeroNode isHaveRing(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，不可能有环
        if (head.next == null || head.next.next == null) {
            return null;
        }

        HeroNode index1 = head;
        HeroNode index2 = head;

        try {
            while (true) {
                index1 = index1.next;
                index2 = index2.next.next;

                if (index1 == null || index2 == null) {
                    return null;
                }

                //有环
                if (index1 == index2) {
                    index2 = head;
                    while (true) {
                        index1 = index1.next;
                        index2 = index2.next;
                        //再次相等的节点为入环的位置
                        if (index1 == index2) {
                            return index1;
                        }
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 合并两个有序的单链表，合并之后的链表依然有序
     * @param head1
     * @param head2
     */
    public static void merge(HeroNode head1, HeroNode head2) {
        HeroNode cur = head1;
        HeroNode node = head2;
        HeroNode next = null;
        boolean flag = false;
        while (cur != null) {
            next = cur.next;
            while (true) {
                if (node.next == null) {
                    break;
                } else if (node.next.no > cur.no) {
                    break;
                } else if (node.next.no == cur.no) {
                    flag = true;
                    break;
                }
                node = node.next;
            }
            if (flag) {
                flag = false;
                System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n",cur.no);
            } else {
                cur.next = node.next;
                node.next = cur;
            }
            cur = next;
        }
    }
}

/**
 * 定义SingleLinkedList 管理英雄
 */
class SingleLinkedList {

    public HeroNode getHead() {
        return head;
    }

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