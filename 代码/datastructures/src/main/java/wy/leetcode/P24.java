package wy.leetcode;

/**
 * @author wangying
 * @description: TODO
 * @date 2021/2/2 10:32
 */
public class P24 {

    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        P24 p = new P24();
        ListNode node = p.getNode(new int[]{1,2,3,4});
        ListNode res = p.swapPairs(node);
        System.out.println(res.toString());
    }


    public ListNode getNode(int[] numbers) {
        ListNode node = new ListNode();
        ListNode head = node;
        for (int num : numbers) {
            node.next = new ListNode(num);
            node = node.next;
        }
        return head.next;
    }

}
