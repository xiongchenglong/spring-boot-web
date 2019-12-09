package com.study.springbootweb.controller;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by xiong on 2019/11/19 14:36
 */
public class PolandNotation {
    public static void main(String[] args) {
        //为了方便将表达式用空格隔开
        String suffixExp = "1 + ( ( 2 + 3 ) * 4 ) - 5";
        List<String> list = getList(suffixExp);
        System.out.println("中缀表达式：" + list);
        List<String> result = parseSuffixExpreesionList(list);
        int num = calc(result);
        System.out.println("后缀表达式：" + result);
        System.out.println(num);
    }

    public static List<String> parseSuffixExpreesionList(List<String> ls) {
        //符号栈
        Stack<String> s1 = new Stack<>();
        //因为s2这个栈在整个转换过程中没有pop操作，还需要逆序输出，所以使用list代替
        //存储中间结果
        ArrayList<String> s2 = new ArrayList<>();

        for (String item : ls) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) {
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    //将字符串放入list中
    public static List<String> getList(String suffixExp) {
        String[] split = suffixExp.split(" ");
        List<String> list = new ArrayList<String>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    public static int calc(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            //匹配多位数
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(s)) {
                    res = num1 + num2;
                } else if ("-".equals(s)) {
                    res = num2 - num1;
                } else if ("*".equals(s)) {
                    res = num1 * num2;
                } else if ("/".equals(s)) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符！");
                break;
        }
        return result;
    }
}