package com.chiang.algorithm.linked;

/**
 * 链表数据结构算法，更适合在头部添加数据时间复杂度为 O(1)
 *
 * @author Chiang
 */
public class LinkedList<E> {
    
    private int size;

    /**
     * 永远是最头部的节点
     */
    private Node dummyHead;

    public LinkedList(){
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     *  通过索引添加节点，一般链表是不提供这个功能的
     *  
     * @param index 添加元素的位置
     * @param e 节点数据
     */
    public void add(int index,E e){
        if(index > size || index < 0){
            throw new RuntimeException("非法参数");
        }

//        因为dummyHead虚拟头可以省略这些代码
//        if(index == 0){
//            addFirst(e);
//        }else {
            Node curNode = dummyHead;
            for (int i = 0; i < index; i++) {
                curNode = curNode.next;
            }

            // 创建一个类作为当前元素的下一个
            // 将当前的元素的下一个赋值到新类的next
            curNode.next = new Node(e,curNode.next);
//        }
    }


    /**
     * 获取指定索引的元素
     */
    public E get(int index){
        if(index < 0 || index > size){
            throw new RuntimeException("非法参数");
        }

        // dummyHead是虚拟存在，他的next就是第0个
        Node curNode = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }

    public void set(int index, E e){
        if(index < 0 || index > size){
            throw new RuntimeException("非法参数");
        }

        Node curNode = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        curNode.e = e;
    }

    public boolean contains(E e){
        Node curNode = dummyHead.next;
        while (curNode != null){
            if(curNode.e.equals(e)){
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        Node curNode = dummyHead.next;
        while (curNode != null){
            builder.append(curNode.e +" -> ");
            curNode = curNode.next;
        }
        builder.append("NULL");

        return "LinkedList{" +
                "size=" + size +
                ", data=" + builder +
                '}';
    }

    /**
     * 在最头部添加一个节点
     */
    public void addFirst(E e){
//        创建一个节点，并将当前第一个节点作为它后面的节点
//        Node node = new Node(e);
//        node.next = head;
//        head = node;

        // 简洁写法
        add(0,e);
        size++;
    }

    public void addLast(E e){
        add(size,e);
    }

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
