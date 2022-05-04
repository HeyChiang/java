package com.chiang.algorithm.recursion.linked;

/**
 * 在Java里传递参数到方法中，改变引用地址不会影响方法外的参数。
 * 因为传入方法里的只是一个复制的引用地址，实际编译过程b=2相当于再次new了一个对象，脱离方法就死掉了。
 *
 * 只有修改类里的属性是可以保证存在的。
 * @author Chiang
 */
public class QuoteNode<E> {

    public E e;
    public QuoteNode next;

    public QuoteNode(E e, QuoteNode next) {
        this.e = e;
        this.next = next;
    }

    public QuoteNode(E e) {
        this(e, null);
    }

    public QuoteNode() {
        this(null, null);
    }

    @Override
    public String toString() {
        return "Node{" +
                "e=" + e +
                ", next=" + next +
                '}';
    }

    public static void main(String[] args) {
        QuoteNode<String> node = new QuoteNode<>("A");
        node.next = new QuoteNode("B",new QuoteNode("C"));
        replace(node.next);
        System.out.println(node);

        String a ="a";
        replaceStr(a);
        System.out.println(a);

        int b= 1;
        replaceInt(b);
        System.out.println(b);
    }

    private static void replaceInt(int b) {
        b= 2;
    }

    private static void replaceStr(String a) {
        a="b";
    }

    public static void replace(QuoteNode<String> node){
        System.out.println("收到了："+node.e);
        node = new QuoteNode("KK");
    }
}
