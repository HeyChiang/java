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


    /**
     * 删除最小的数值
     */
    public Node removeMinNode(){
        root = removeMinNode(root);
        return root;
    }


    /**
     * 删除最左节点，保留其右侧的节点
     */
    private Node removeMinNode(Node root) {

        // 清理掉右节点，并将右节点返回作为上级的左节点
        if(root.left == null){
            System.out.println("Remove:"+root.e);
            Node tempRight = root.right;
            root.right = null;
            size--;
            return tempRight;
        }

        // 改变当前左节点数据
        root.left = removeMinNode(root.left);
        return root;
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
        // 后续遍历，释放内存
        System.out.println("后序遍历：");
        linkList.postOrder();

        System.out.println(linkList);

        System.out.println("删除最小的");
        while (!linkList.isEmpty()){
            linkList.removeMinNode();
        }
    }

}

