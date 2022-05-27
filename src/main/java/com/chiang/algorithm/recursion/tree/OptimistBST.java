package com.chiang.algorithm.recursion.tree;

import lombok.Data;

/**
 * 二分搜索树，添加和查找节点
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
        root = add(root, e);
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

    public boolean contains(E e){
        return contains(root,e);
    }

    private boolean contains(Node root, E e) {
        if(root == null){
            return false;
        }

        if(root.e.compareTo(e) == 0){
            return true;
        }else if(root.e.compareTo(e) < 0){
            return contains(root.right,e);
        }else{
            return contains(root.left,e);
        }
    }

    /**
     *  从左到有的打印数据，先把最左边的打印完成，然后打印右边的
     */
    public void preOrder(){
        preOrder(root);
    }

    @Override
    public String toString(){
        if(root != null){
            StringBuilder builder = new StringBuilder();
            generatePrint(builder,0,root);
            return builder.toString();
        }
        return null;
    }

    private void generatePrint(StringBuilder builder,int depth,Node root) {
        if(root == null){
            return;
        }
        builder.append("\n");
        for (int i = 0; i < depth; i++) {
            builder.append("-");
        }
        builder.append(root.e);
        depth++;
        generatePrint(builder,depth,root.left);
        generatePrint(builder,depth,root.right);
    }

    private void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }



    public static void main(String[] args) {
        OptimistBST<Integer> linkList = new OptimistBST<>();
        linkList.add(5);
        linkList.add(3);
        linkList.add(6);
        linkList.add(8);
        linkList.add(4);
        linkList.add(2);
        System.out.println(linkList);

        System.out.println(linkList.contains(2));
        System.out.println(linkList.contains(9));

        linkList.preOrder();

        System.out.println(linkList);
    }

}

