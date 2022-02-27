package com.chiang.algorithm.linked;

/**
 * 反转链表
 */
public class ReverseList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     *  5、4、3、2、1、null -> null、1、2、3、4、5
     * 同时保存cur、pre、next三个元素，不断前移替换。
     *  1. 把 next = pre ，指向下一个的变成指向前面一个
     *  2. 把 pre = cur
     *  3. cur =
     */
    public ListNode reverseList(ListNode head) {
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
}
