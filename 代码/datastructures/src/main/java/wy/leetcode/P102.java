package wy.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wangying
 * @version 1.0
 * @description: TODO
 * @date 2022/10/14 18:45
 */
public class P102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            List<Integer> subList = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                root = queue.poll();
                subList.add(root.val);
            }
            list.add(subList);
            if(root.left != null) {
                queue.offer(root.left);
            }
            if(root.right != null) {
                queue.offer(root.right);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        P832 p = new P832();
        int[][] A = {{1,1,0},{1,0,1},{0,0,0}};
        int[][] B = p.flipAndInvertImage(A);
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[i].length; j++) {
                System.out.print(B[i][j] + ",");
            }
            System.out.println();
        }
    }

}
