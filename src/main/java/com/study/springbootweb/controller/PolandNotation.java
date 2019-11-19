package com.study.springbootweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by xiong on 2019/11/19 14:36
 */
public class PolandNotation {
    public static void main(String[] args) {
        //为了方便将表达式用空格隔开
        String suffixExp = "3 4 + 5 * 6 -";
        List<String> list = getList(suffixExp);
        int result = calc(list);
        System.out.println("结果为：" + result);
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
