package wy.leetcode;

/**
 * 832. 翻转图像
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 *
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 *
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 *
 * 输入：[[1,1,0],[1,0,1],[0,0,0]]

 * 解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 *      然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 * 输出：[[1,0,0],[0,1,0],[1,1,1]]
 *
 * 输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]

 * 解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 *      然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *
 * 输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *
 * 链接：https://leetcode-cn.com/problems/flipping-an-image
 *
 * @author wangying
 * @description: TODO
 * @date 2021/2/24 11:15
 */
public class P832 {

    public int[][] flipAndInvertImage(int[][] A) {
        int mid = A.length / 2 + A.length % 2;

        for (int i = 0; i < A.length; i++) {

            for (int j = 0; j < mid; j++) {
                int a = A[i][j];
                int b = A[i][A.length - j - 1];
                A[i][j] = b ^= 1;
                if (j != A.length - j - 1) {
                    A[i][A.length - j - 1] = a ^= 1;
                }
            }

        }
        return A;
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
