package com.chiang.algorithm.linked;

public class TestMain {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
        }
        System.out.println(linkedList);
        linkedList.remove(1);
        System.out.println(linkedList);
    }
}
