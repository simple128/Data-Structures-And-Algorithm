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

    public static int fibonacciSearch(int nums[], int key) {
        int low = 0;
        int high = nums.length - 1;
        int k = 0;
        int mid = 0;
        int[] fib = fib();
        //获得大于等于数组长度的斐波那契数
        while (fib[k] < high) {
            k++;
        }
        //创建中间数组，并填充超过源数组长度的位置
        int[] temp = Arrays.copyOf(nums,fib[k]);
        for (int i = nums.length; i < fib[k]; i++) {
            temp[i] = nums[high];
        }
        /*
         * 循环查找目标值，
         * 若key > nums[mid]，继续在数组后半段查找
         * 若key < nums[mid]，继续在数组前半段查找
         */
        while (low <= high) {
            mid = low + fib[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k-=2;
            } else {
                if (mid <= high) {
                    return mid; //找到的下标在源数组长度内
                } else {
                    return high; //找到的下标超过了源数组长度，返回源数组最后一个下标
                }
            }
        }
        return -1;
    }

    public static int[] fib() {
        int[] fib = new int[20];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,7,9,55,123,666,779};
        System.out.println(Arrays.toString(nums));
        System.out.println(fibonacciSearch(nums, 666));
    }

}
