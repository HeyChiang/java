package com.chiang.algorithm.linked;

/**
 * 反转链表
 */
public class ReverseList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();

            ListNode curNode = this;
            while (curNode != null){
                builder.append(curNode.val).append(" -> ");
                curNode = curNode.next;
            }
            builder.append("NULL");

            return "LinkedList{" +
                    "data=" + builder +
                    '}';
        }
    }

    /**
     * 链表反转： 5、4、3、2、1、null -> null、1、2、3、4、5
     * 同时保存cur、pre、next三个元素，不断前移替换。
     *  1. 保存cur.next 临时变量，避免下一个指向前一个而导致下一个找不到节点了
     *  2. 把 cur.next = pre ，指向下一个的变成指向前面一个，没有pre也就无法指向上一个了
     *  3. 把 pre = cur ,上一个变成当前，重复的将下一个指向前一个，不断前移替换
     *  4. cur = next ,当前的变量指向下一个，不断前移替换
     */
    public ListNode reverseList1(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;

        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 递归的方式反转链表，head和rev是相反的链表，但是引用的同一个对象。
     *
     * head：代表正序的  0、1、2、3、4、5、null
     * rev：代表道程序的 5、4、3、2、1、0、null
     *
     */
    public ListNode reverseList2(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        ListNode rev = reverseList2(head.next);

        if(head.val == 4){
            System.out.println("debug");
        }

        // 把正序的4、5、null 改变 5、4、null，正序和倒叙引用的是同一个类
        head.next.next = head;
        // 把4后面的5 next换成null，不会删除rev里的4
        head.next = null;

        return rev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        generate(head);

        System.out.println("正序：");
        System.out.println(head);
        ListNode node = new ReverseList().reverseList1(head);
        System.out.println("倒叙：");
        System.out.println(node);

        head = new ListNode();
        generate(head);
        System.out.println("倒叙2：");
        System.out.println(new ReverseList().reverseList2(head));
    }

    /**
     * 生成链表节点数据
     */
    private static void generate(ListNode head) {
        ListNode cur = head;
        for (int i = 1; i < 6; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
    }
}
