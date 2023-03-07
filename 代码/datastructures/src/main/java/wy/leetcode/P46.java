package wy.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @description: TODO
 * @author wangying
 * @date 2022/1/25 10:08
 * @version 1.0
 */
public class P46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, ans, combine, used);
        return ans;
    }

    public void dfs(int[] nums, List<List<Integer>> ans, List<Integer> combine, boolean[] used) {
        if (combine.size() == nums.length) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                combine.add(nums[i]);
                used[i] = true;
                dfs(nums, ans, combine, used);
                used[i] = false;
                combine.remove(combine.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        P46 p = new P46();
        int[] candidates = new int[]{1,2,3};
        System.out.println(p.permute(candidates));
    }


}
