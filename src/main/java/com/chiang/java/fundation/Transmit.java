package com.chiang.java.fundation;

import lombok.Data;

/**
 * 在java里面使用的都是值传递，C可以是引用或者是数值传递
 * <p>
 * 在C里int& a，就代表引用传递
 */
public class Transmit {

    public static final String MYA = "mya";

    public static void main(String[] args) {
        int intA = 1;
        String strA = "a";
        testInt(intA);
        testStr(strA);
        System.out.println("intA：" + intA);
        System.out.println("strA：" + strA);
        System.out.println(strA == "a");
        System.out.println("MYA == \"mya\" :" + ( MYA == "mya"));

        new Transmit().test();
    }

    /**
     * Java 类变量在栈中存储的是引用地址，这个地址指向堆中具体的值
     * 当调用change()方法传入变量时，也是拷贝变量，但是这里的拷贝只是栈中的引用地址，并不会拷贝堆中的数据
     */
    public void test() {
        User user = new User();
        user.setAge(18);
        change(user);
        System.out.println("年龄:" + user.getAge());
    }

    private void change(User user) {
        user.setAge(19);
    }

    @Data
    class User {
        int age;
    }


    private static void testStr(String a) {
        a = "b";
    }

    private static void testInt(int a) {
        a = 2;
    }
}
