package com.study.springbootweb.controller;

/**
 * Created by xiong on 2019/11/15 17:45
 */
public class SingleLinkedListToStackDemo {
    public static void main(String[] args) {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        SingleStack stack = new SingleStack();
        stack.push(node0);
        stack.push(node1);
        stack.push(node2);
        stack.push(node3);
        stack.push(node4);
        stack.list();
        Object pop = stack.pop();
        System.out.println(pop);
    }
}

class SingleStack{
    private Node head = new Node(null);

    //入栈
    public void push(Object value) {
        Node node = new Node(value);
        //将新入栈的节点插入到头节点后面
        if (head.getNext() == null) {
            head.setNext(node);
        } else {
            node.setNext(head.getNext());
            head.setNext(node);
        }
    }

    //出栈
    public Object pop() {
        if (head.getNext() == null) {
            System.out.println("栈为空");
            return null;
        } else {
            Object value = head.getNext().getValue();
            head.setNext(head.getNext().getNext());
            return value;
        }
    }

    //遍历栈
    public void list() {
        if (head.getNext() == null) {
            System.out.println("栈为空");
            return;
        }
        Node temp = head.getNext();
        while (temp != null) {
            System.out.println(temp.getValue() + "");
            temp = temp.getNext();
        }
    }
}

//表示一个节点
class Node{
    private Node next; //指向下一个节点
    private Object value;//本节点的值

    public Node(Object value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}