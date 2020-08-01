package wy.algorithm.dac;

public class HanoiTower {

    public static void main(String[] args) {
        hanoiTower(4, 'A', 'B', 'C');
    }

    /**
     * @param num 需要移动的盘
     * @param a   源位置
     * @param b   中间位置
     * @param c   目标位置
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从" + a + "移动到" + c);
        } else {
            //将n-1个盘从A移动到B
            hanoiTower(num - 1, a, c, b);
            //将第n个盘从A移动到C
            System.out.println("第" + num + "个盘从" + a + "移动到" + c);
            //将n-1个盘从B移动到C
            hanoiTower(num - 1, b, a, c);
        }
    }

}
