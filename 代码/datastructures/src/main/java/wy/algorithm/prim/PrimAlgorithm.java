package wy.algorithm.prim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 使用普里姆算法解决修路问题
 */
public class PrimAlgorithm {

    public static void main(String[] args) {
        //测试看看图是否创建ok
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int [][]weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};

        //创建MGraph对象
        MGraph graph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        //输出
        minTree.showGraph(graph);
        //测试普利姆算法
        System.out.println(minTree.prim(graph, 0));
    }

}

/**
 * 最小连接树
 */
class MinTree {

    /**
     * 创建图的邻接矩阵
     * @param graph 图对象
     * @param verxs 图对应的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for(i = 0; i < verxs; i++) {//顶点
            graph.data[i] = data[i];
            for(j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph) {
        for(int[] link: graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    public List<Character> prim(MGraph graph, int v) {
        List<Character> path = new ArrayList<>(graph.verxs);    //保存最短路径的集合
        path.add(graph.data[v]);
        int[] visited = new int[graph.verxs];   //标识顶点是否已被访问
        visited[v] = 1;

        int minLength = 10000;
        int index1 = -1;    //下一次最短路径的起点，即下一次修路从index1->index2
        int index2 = -1;    //index2表示下一个要访问的路径(下一条要修路的点)

        //最外层循环用来生成连接顶点的边，因为有(顶点数-1)个边，所以 k < graph.verxs - 1
        for (int k = 0; k < graph.verxs - 1; k++) {

            //找出这一轮循环中路径最短的边
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    //从已访问过的顶点到未访问过的顶点最短的边，也体现了贪心算法的思想，每一次都选最优路径
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minLength) {
                        minLength = graph.weight[i][j];
                        index1 = i;
                        index2 = j;
                    }
                }
            }

            //生成边
            System.out.println("边<" + graph.data[index1] + "," + graph.data[index2] + "> 权值:" + minLength);
            path.add(graph.data[index2]);
            visited[index2] = 1;
            minLength = 10000;
        }
        return path;
    }
}

class MGraph {
    int verxs; //表示图的节点个数
    char[] data;//存放结点数据
    int[][] weight; //存放边，就是我们的邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}