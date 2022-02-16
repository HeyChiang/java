package com.chiang.algorithm.linked;

/**
 * 链表数据结构算法
 *
 * @author Chiang
 */
public class LinkedList<E> {

    private int size;
    private Node head;

    private class Node{
        private E e;
        private Node next;

        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", next=" + next +
                    '}';
        }
    }
}
