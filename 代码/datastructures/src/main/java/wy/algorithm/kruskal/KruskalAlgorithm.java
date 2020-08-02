package wy.algorithm.kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 使用克鲁斯卡尔算法解决最短总路径问题
 */
public class KruskalAlgorithm {


    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int INF = Integer.MAX_VALUE;
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        //大家可以在去测试其它的邻接矩阵，结果都可以得到最小生成树.
        Graph gragh = new Graph(vertexs, matrix);
        kruskal(gragh);
    }

    public static int kruskal(Graph graph) {
        Edge[] edges = graph.edges;
        char[] vertexs = graph.vertexs;
        int[] ends = new int[edges.length];
        int sum = 0;

        for (int i = 0; i < edges.length; i++) {
            int start = edges[i].start;
            int end = edges[i].end;
            int weight = edges[i].weight;
            int parentOfStart = findParentRoot(start, ends);
            int parentOfEnd = findParentRoot(end, ends);
            if (parentOfStart != parentOfEnd) {
                ends[parentOfStart] = parentOfEnd;
                sum += weight;
                System.out.println("连接顶点：" + vertexs[start] + " ->" + vertexs[end] + " 权值：" + weight);
            }
        }
        System.out.println("最短总路径：" + sum);
        return sum;
    }

    private static int findParentRoot(int target, int[] ends) {
        while (ends[target] != 0) {
            target = ends[target];
        }
        return target;
    }

}

class Graph {

    char[] vertexs;
    Edge[] edges;
    int[][] matrix;

    public Graph(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
        buildEdgs();
    }

    public void buildEdgs() {
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != Integer.MAX_VALUE) {
                    edgeList.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }
        edges = edgeList.toArray(new Edge[0]);
        Arrays.sort(edges);
    }

}

class Edge implements Comparable<Edge>{
    int start;
    int end;
    int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}
