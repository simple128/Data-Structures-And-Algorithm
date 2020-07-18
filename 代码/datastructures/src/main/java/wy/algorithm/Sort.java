package wy.algorithm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 10   5   1   3   9   7   6   8   4
 * 4    5   1   3   9   7   6   8   10
 * 1    5   4   3   9   7   6   8   10
 * 1    3   4   5   9   7   6   8   10
 * 1    3   4   5   6   7   8   9   10
 */
public class Sort {

    /**
     * 选择排序
     * @param nums
     * @return
     */
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

    /**
     * 插入排序
     * @param nums
     * @return
     */
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

    /**
     * 希尔排序-交换法
     * @param nums
     * @return
     */
    public static int[] shellSort(int[] nums) {
        int gap = nums.length;
        int temp = 0;
        while ((gap /= 2) > 0) {
            for (int i = gap; i < nums.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
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

    /**
     * 归并排序
     * <p>
     * 采用分治策略(divide-and-conquer)
     *
     * @param nums
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(nums, left, mid, temp);
            mergeSort(nums, mid + 1, right, temp);
            merge(nums, left, mid, right, temp);
        }
    }

    public static void merge(int[] nums, int left, int mid, int right, int[] temp) {
        int i = left;       //指向nums数组左边的元素
        int j = mid + 1;    //指向nums数组右边的元素
        int t = left;       //将nums数组放入temp数组的开头位置

        //将左右两个数组按顺序放入到temp中
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                temp[t++] = nums[i++];
            } else {
                temp[t++] = nums[j++];
            }
        }

        //将左右两个数组中剩余的元素放入到temp中
        while (i <= mid) {
            temp[t++] = nums[i++];
        }
        while (j <= right) {
            temp[t++] = nums[j++];
        }

        //将排序好的temp数组的值赋给nums
        while (left <= right) {
            nums[left] = temp[left];
            left++;
        }
    }

    /**
     * 桶排序
     *
     * @param nums
     */
    public static void bucketSort(int[] nums) {
        //确定数组的最大值和最小值，以得到桶的大小
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        int[] bucket = new int[max - min + 1];
        //遍历数组元素，在桶中计数
        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i] - min]++;
        }
        //遍历桶，按顺序将元素放入数组中
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                nums[index++] = i + min;
                bucket[i]--;
            }
        }

    }

    /**
     * 基数排序
     *
     * 针对有负数或者0的情况，解决方法：
     * 有负数：
     *      1、找到数组的最小值，将所有元素加上（最小值的绝对值 + 1）
     *      2、多申请一个数组，将用于存放负数，最后合并正数数组和负数数组
     * 有0：所有数加 1
     *
     * 注意：上面解决方案要考虑整数的溢出
     *
     * @param nums
     */
    public static void radixSort(int[] nums) {
        //确定桶的深度
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        int loopCount = String.valueOf(max).length();
        int[][] bucket = new int[10][nums.length];//按位记录数组中的元素
        int[] bucketEleCount = new int[10];//记录每个桶中放了几个元素
        for (int i = 0; i < loopCount; i++) {
            int t = 0;
            for (int j = 0; j < nums.length; j++) {
                int digitOfElement = nums[j] / (int)(Math.pow(10, i)) % 10;
                bucket[digitOfElement][bucketEleCount[digitOfElement]] = nums[j];
                bucketEleCount[digitOfElement]++;
            }
            for (int k = 0; k < 10; k++) {
                for (int l = 0; l < bucketEleCount[k]; l++) {
                    nums[t++] = bucket[k][l];
                }
                bucketEleCount[k] = 0;
            }
        }
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //getTestArray(8000000);
        int[] nums = new int[]{12, -23, 56, 8, 5, 4, 1, -16, 22, 9, 0};
        //int[] temp = new int[nums.length];
        System.out.println("排序前：" + sdf.format(new Date()));
        bucketSort(nums);
        System.out.println("排序后：" + sdf.format(new Date()));
        System.out.println(Arrays.toString(nums));
    }


    public static int[] getTestArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
        return arr;
    }
}
