package wy.algorithm.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        //创建 Graph对象
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dijkstra(2);//7 12 0 17 8 13 9
        graph.vv.show();
    }

}

class Graph {
    char[] vertex;
    int[][] matrix;
    VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    // 显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void dijkstra(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArrAndGetNext();
            update(index);
        }

    }

    public void update(int index) {
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            len = vv.dis[index] + matrix[index][i];
            if (vv.alreadyArr[i] == 0 && len < vv.dis[i]) {
                vv.dis[i] = len;
                vv.preVisited[i] = index;
            }
        }

    }
}

class VisitedVertex {
    int[] alreadyArr;
    int[] dis;
    int[] preVisited;

    public VisitedVertex(int length, int index) {
        alreadyArr = new int[length];
        dis = new int[length];
        preVisited = new int[length];
        Arrays.fill(dis,65535);
        alreadyArr[index] = 1;
        dis[index] = 0;
    }

    public int updateArrAndGetNext() {
        int min = 65536;
        int index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] ==  0 && dis[i] < min) {
                index = i;
                min = dis[i];
            }
        }
        alreadyArr[index] = 1;
        return index;
    }

    //显示最后的结果
    //即将三个数组的情况输出
    public void show() {

        System.out.println("==========================");
        //输出already_arr
        for(int i : alreadyArr) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出pre_visited
        for(int i : preVisited) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出dis
        for(int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        //为了好看最后的最短距离，我们处理
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "("+i+") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();

    }
}
