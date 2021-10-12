package wy.leetcode;

/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 */
public class P35 {

    public int searchInsert(int[] nums, int target) {

        return indexOf(nums, target, 0, nums.length - 1);

    }

    public int indexOf(int[] nums, int target, int beginIndex, int endIndex) {

        if (endIndex - beginIndex <= 1) {
            if (target <= nums[beginIndex]) {
                return beginIndex;
            } else if (target > nums[endIndex]) {
                return endIndex + 1;
            } else {
                return endIndex;
            }
        }

        int middle = nums[(endIndex + beginIndex) / 2];
        if (middle == target) {
            return (endIndex + beginIndex) / 2;
        } else if (middle > target) {
            return indexOf(nums, target, beginIndex, (endIndex + beginIndex) / 2);
        } else {
            return indexOf(nums, target, (endIndex + beginIndex) / 2, endIndex);
        }

    }

    public static void main(String[] args) {

        //[1,3,5,6]
        //5
        P35 p = new P35();
        int[] nums = new int[]{1,3};
        int target = 1;
        System.out.println(p.searchInsert(nums, target));

    }
    
}
