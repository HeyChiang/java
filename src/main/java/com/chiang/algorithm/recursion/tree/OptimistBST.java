package com.chiang.algorithm.recursion.tree;

import lombok.Data;

/**
 * 二分搜索树
 */
@Data
public class OptimistBST<E extends Comparable<E>> {

    public class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public OptimistBST() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        add(root, e);
    }

    /**
     * 如果参数一样，就忽略掉
     */
    private Node add(Node root, E e) {
        if(root == null){
            size++;
            return new Node(e);
        }

        if (root.e.compareTo(e) > 0) {
            root.left = add(root.left, e);
        } else if (root.e.compareTo(e) < 0) {
           root.right = add(root.right, e);
        }

        return root;
    }

    public static void main(String[] args) {
        OptimistBST<Integer> linkList = new OptimistBST<>();
        linkList.add(2);
        linkList.add(1);
        linkList.add(3);
        linkList.add(5);
        System.out.println(linkList);
    }

}
