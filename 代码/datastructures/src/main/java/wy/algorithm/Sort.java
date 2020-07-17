package wy.algorithm;

import java.util.Arrays;

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
        int index = nums.length;
        int temp = 0;
        while ((index /= 2) > 0) {
            for (int i = index; i < nums.length; i++) {
                for (int j = i - index; j >= 0 ; j -= index) {
                    if (nums[j] > nums[j + index]) {
                        temp = nums[j];
                        nums[j] = nums[j + index];
                        nums[j + index] = temp;
                    }
                }
            }
        }
        return nums;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{12, 23, 56, 8, 5, 4, 1};
        System.out.println(Arrays.toString(shellSort(nums)));
    }
}
