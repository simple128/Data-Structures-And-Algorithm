package wy.leetcode;

import java.util.Arrays;

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
        Integer[] nums =  new Integer[]{1,4,8,2,6};
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        Arrays.sort(nums, (o1, o2) -> o2 - o1);
        System.out.println(Arrays.toString(nums));


//        P24 p = new P24();
//        ListNode node = p.getNode(new int[]{1,2,3,4});
//        ListNode res = p.swapPairs(node);
//        System.out.println(res.toString());
    }

    public static Long ip2num(String input) {
        String[] nums = input.split("\\.");
        String result = "";
        for (String str : nums) {
            String binaryString = Integer.toBinaryString(Integer.valueOf(str));
            while (binaryString.length() < 8) {
                binaryString = "0" + binaryString;
            }
            result =  result + binaryString;
        }
        return Long.parseLong(result, 2);
    }

    public static String num2Ip(String input) {
        String binaryString = Integer.toBinaryString(Integer.valueOf(input));
        while (binaryString.length() < 32) {
            binaryString = "0" + binaryString;
        }
        String result = "";
        for (int i = 0; i < 4; i++) {
            result = result + Integer.parseInt(binaryString.substring(0, 8), 2) + "\\.";
        }
        return result.substring(0, result.length() - 1);
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
