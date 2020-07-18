package wy.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 查找算法
 */
public class Search {

    public static List<Integer> insertSearch(int[] nums, int left, int right, int findVal) {
        System.out.println("xxxxxxxxxxxxxxxx");
        if (left > right || findVal < nums[0] || findVal > nums[nums.length - 1]) {
            return new ArrayList<>();
        }
        int mid = left + (right - left) * (findVal - nums[left]) / (nums[right] - nums[left]);
        if (findVal > nums[mid]) {
            return insertSearch(nums, mid + 1, right, findVal);
        } else if (findVal < nums[mid]) {
            return insertSearch(nums, left, mid, findVal);
        } else {
            List<Integer> list = new ArrayList<>();

            int temp = mid - 1;
            while (true) {
                if (temp < 0 || nums[temp] != findVal) {
                    break;
                }
                list.add(temp--);
            }
            list.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > nums.length - 1 || nums[temp] != findVal) {
                    break;
                }
                list.add(temp++);
            }
            return list;
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = (int)(Math.random()*1000);
        }
        Sort.bucketSort(nums);
        System.out.println(Arrays.toString(nums));
        List<Integer> list = insertSearch(nums, 0, nums.length - 1, 22);
        System.out.println(list);
    }

}
