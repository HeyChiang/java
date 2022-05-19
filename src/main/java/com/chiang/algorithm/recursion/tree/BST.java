package com.chiang.algorithm.recursion.tree;

import lombok.Data;

/**
 * 二分搜索树
 */
@Data
public class BST<E extends Comparable<E>> {

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

    public BST() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        if (root == null) {
            size++;
            root = new Node(e);
        } else {
            add(root, e);
        }
    }

    /**
     * 如果参数一样，就忽略掉
     */
    private void add(Node root, E e) {
        if (root.e.compareTo(e) > 0) {
            if (root.left == null) {
                size ++;
                root.left = new Node(e);
            } else {
                add(root.left, e);
            }
        } else if (root.e.compareTo(e) < 0) {
            if (root.right == null) {
                size++;
                root.right = new Node(e);
            } else {
                add(root.right, e);
            }
        }
    }

    public static void main(String[] args) {
        BST<Integer> linkList = new BST<>();
        linkList.add(2);
        linkList.add(1);
        linkList.add(3);
        linkList.add(5);
        System.out.println(linkList);
    }

}
