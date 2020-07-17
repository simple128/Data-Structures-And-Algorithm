package wy.algorithm;

import java.util.Arrays;

/**
 *
 * 10   5   1   3   9   7   6   8   4
 * 4    5   1   3   9   7   6   8   10
 * 1    5   4   3   9   7   6   8   10
 * 1    3   4   5   9   7   6   8   10
 * 1    3   4   5   6   7   8   9   10
 *
 *
 */
public class Sort {

    public static int[] selectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int min = nums[i];
            int index = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < min) {
                    index = j;
                    min = nums[j];
                }
            }
            nums[index] = nums[i];
            nums[i] = min;
        }
        return nums;
    }

    public static int[] insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int insertVal = nums[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < nums[insertIndex]) {
                nums[insertIndex + 1] = nums[insertIndex];
                insertIndex--;
            }
            nums[insertIndex + 1] = insertVal;
        }
        return nums;
    }

    public static int[] shellSort(int[] nums) {
        int gap = nums.length;
        int temp = 0;
        while ((gap /= 2) > 0) {
            for (int i = gap; i < nums.length; i++) {
                for (int j = i - gap; j >= 0 ; j -= gap) {
                    if (nums[j] > nums[j + gap]) {
                        temp = nums[j];
                        nums[j] = nums[j + gap];
                        nums[j + gap] = temp;
                    }
                }
            }
        }
        return nums;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{12, 23, 56, 8, 5, 4, 1, 16, 22 , 9};
        System.out.println(Arrays.toString(shellSort(nums)));
    }
}
