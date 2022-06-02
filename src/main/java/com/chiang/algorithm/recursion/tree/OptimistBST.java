package com.chiang.algorithm.recursion.tree;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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


    /**
     * 使用非递归的方式打印二分搜索树的数据，同前序遍历
     */
    public void preOrderNR(){
        Stack<Node> stacks =new Stack<>();
        stacks.push(root);

        while (!stacks.isEmpty()){
            Node pop = stacks.pop();
            System.out.println(pop.e);

            if(pop.right != null){
                stacks.push(pop.right);
            }
            if(pop.left != null){
                stacks.push(pop.left);
            }
        }
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

    /**
     * 前序遍历
     */
    private void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }


    /**
     * 中序遍历，也叫做顺序遍历
     */
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后序遍历，主要应用在内存释放的场景，先要释放所有的子节点，然后释放根节点
     */
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 广度搜索遍历，按照层级来打印数据
     */
    public void levelOrder(){
        Queue<Node> linkList= new LinkedList<>();
        linkList.add(root);
        while (!linkList.isEmpty()){
            Node node = linkList.remove();
            System.out.println(node.e);

            if(node.left != null){
                linkList.add(node.left);
            }
            if(node.right != null){
                linkList.add(node.right);
            }
        }
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

        // 前序遍历
        System.out.println("前序遍历：");
        linkList.preOrder();

        // 中序遍历=顺序遍历
        System.out.println("顺序遍历：");
        linkList.inOrder();

        // 后续遍历，类似于释放内存
        System.out.println("后序遍历：");
        linkList.postOrder();

        System.out.print("ToString：");
        System.out.println(linkList);

        System.out.println("非递归遍历:");
        linkList.preOrderNR();

        System.out.println("广度搜索遍历：");
        linkList.levelOrder();
    }

}

