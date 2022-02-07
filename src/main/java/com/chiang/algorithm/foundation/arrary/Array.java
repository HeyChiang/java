package com.chiang.algorithm.foundation.arrary;

import java.util.Stack;

/**
 * 自定义泛型数组
 *
 * @author Chiang
 */
public class Array<E> {

    private E[] elements;

    public Array() {
        // Java不支持直接创建泛型数组，可以通过Object去代替
        elements = (E[]) new Object[10];
    }

    /**
     * 数组删除一个元素，需要将删除元素位置的后面元素向前移
     */
    public E remove(int index) {
        E element = elements[index];

        for (int i = index; i < elements.length; i++) {
            elements[i] = elements[index + 1];
        }
//        最后还要减去一个元素
//        elements.length--;
        return element;
    }

    /**
     *  使用栈进行左右括号的匹配
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果左边符号就先保存
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 没有括号就直接返回
                if (stack.isEmpty()) {
                    return false;
                }

                char topChar = stack.pop();

                // 当前c是右括号，栈顶的topChar必须是左括号匹配
                if(c == ')' && topChar == '('){
                    return false;
                }else if(c == ']' && topChar == '['){
                    return false;
                }else if(c == '}' && topChar == '{'){
                    return false;
                }
            }
        }

        // 所有的左右括号都匹配，没有问题就返回true
        return stack.isEmpty();
    }
}
