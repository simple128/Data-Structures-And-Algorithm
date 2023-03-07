package wy.leetcode;

/**
 * @author wangying
 * @version 1.0
 * @description: Definition for a binary tree node.
 * @date 2022/10/14 18:46
 */
public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
  }
}

