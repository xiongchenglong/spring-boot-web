package com.study.springbootweb.controller;

/**
 * Created by xiong on 2019/11/18 10:24
 */
public class Calculator {
    public static void main(String[] args) {
        String exp = "700+2*6-12";
        CalStack numStack = new CalStack(10);
        CalStack operStack = new CalStack(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        int oper = 0;
        char ch = ' ';
        String keepNum = "";//用来拼接多位数
        while (true) {
            //依次得到exp中的每个字符
            ch = exp.substring(index, index + 1).charAt(0);
            //判断ch是数字还是运算符
            if (operStack.isOper(ch)) {
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //判断运算符之间的优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.calc(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }
            } else {
                keepNum += ch;
                //如果ch已经是exp的最后一位，就直接入栈
                if (index == exp.length()-1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(exp.substring(index+1,index+2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= exp.length()) {
                break;
            }
        }

        //表达式遍历完后顺序执行
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.calc(num1, num2, oper);
            numStack.push(res);
        }
        System.out.println(exp + "结果为：" + numStack.pop());
    }
}

//定义一个ArrayStack表示栈
class CalStack {
    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈，数据存在数组中
    private int top = -1; // 栈顶，初始化为-1

    public CalStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是否是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int calc(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '-':
                result = num2 - num1;//注意两者的顺序
                break;
            case '/':
                result = num2 / num1;//注意两者的顺序
                break;
            default:
                    break;
        }
        return result;
    }

    //返回栈顶的元素
    public int peek() {
        return stack[top];
    }
}