package wy.algorithm.dynamic;

/**
 * 动态规划解决背包问题
 * 状态转移方程：res[i][j]=Math.max(res[i-1][j], v[i]+res[i-1][j-w[i]]);
 *
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        int[] val = new int[]{1500, 3000, 2000, 3500, 2800};
        int[] weight = new int[]{1, 4, 3, 5, 2};
        int capacity = 10;
        int maxVal = dpByIteration(val, weight, capacity);
        System.out.println("maxVal:" + maxVal);
    }

    /**
     * 递归实现
     * @param val   物品的价值
     * @param weight    物品的重量
     * @param capacity  背包容量
     * @return
     */
    public static int dpByRecursion(int[] val, int[] weight, int capacity) {
        int[][] res = new int[val.length + 1][capacity + 1];
        dpByRecursion(res, 1, 1, val, weight, capacity);
        return res[val.length][capacity];
    }

    /**
     *
     * @param res   背包容量和总价值的二维数组
     * @param i 要放入背包物品编号
     * @param j 正在计算的容量
     */
    public static void dpByRecursion(int[][] res, int i, int j, int[] val, int[] weight, int capacity) {
        if (weight[i - 1] > j) {
            res[i][j] = res[i - 1][j];
        } else {
            res[i][j] = Math.max(res[i - 1][j], val[i - 1] + res[i - 1][j - weight[i - 1]]);
        }
        if (i < val.length) {
            dpByRecursion(res, i + 1, j, val, weight, capacity);
        }
        if (j < capacity) {
            dpByRecursion(res, i, j + 1, val, weight, capacity);
        }
    }

    /**
     * 迭代实现
     * @param val   物品的价值
     * @param weight    物品的重量
     * @param capacity  背包容量
     * @return
     */
    public static int dpByIteration(int[] val, int[] weight, int capacity) {
        int[][] res = new int[val.length + 1][capacity + 1];

        for (int i = 1; i < res.length; i++) {
            for (int j = 1; j < res[i].length; j++) {
                if (weight[i - 1] > j) {
                    res[i][j] = res[i - 1][j];
                } else {
                    res[i][j] = Math.max(res[i - 1][j], val[i - 1] + res[i - 1][j - weight[i - 1]]);
                }
            }
        }
        return res[val.length][capacity];
    }

}
