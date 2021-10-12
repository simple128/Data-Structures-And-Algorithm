package wy.leetcode;

/**
 *
 * 1052. 爱生气的书店老板
 *
 * https://leetcode-cn.com/problems/grumpy-bookstore-owner/
 *
 * @author wangying
 * @description: TODO
 * @date 2021/2/23 16:52
 */
public class P1052 {

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int maxCustomers = 0;
        int maxIncreaseCustomers = 0;
        int lastIncreaseCustomers = 0;
        for(int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                maxCustomers += customers[i];
            }
        }

        for (int i = 0; i < X; i++) {
            lastIncreaseCustomers += customers[i] * grumpy[i];
        }

        maxIncreaseCustomers = lastIncreaseCustomers;
        for(int i = X; i < customers.length; i++) {

            lastIncreaseCustomers = lastIncreaseCustomers - customers[i - X] * grumpy[i - X] + customers[i] * grumpy[i];
            maxIncreaseCustomers = Math.max(lastIncreaseCustomers, maxIncreaseCustomers);
        }

        return maxCustomers + maxIncreaseCustomers;
    }
//[1,0,1,2,1,1,7,5]
//        [0,1,0,1,0,1,0,1]
//        3

    public static void main(String[] args) {
        P1052 p = new P1052();
        int[] customers = new int[]{3};
        int[] grumpy =    new int[]{1};
        int X = 1;
        System.out.println(p.maxSatisfied(customers, grumpy, X));
    }

}
