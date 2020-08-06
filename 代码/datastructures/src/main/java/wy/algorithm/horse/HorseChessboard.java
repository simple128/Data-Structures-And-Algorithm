package wy.algorithm.horse;

import java.awt.Point;
import java.util.*;

/**
 * 马踏棋盘算法
 */
public class HorseChessboard {

    public static void main(String[] args) {
        HorseChessboard horseChessboard = new HorseChessboard(8, 8);
        horseChessboard.traversalChessboard(0,0, 1);
        horseChessboard.show();
    }

    private int[][] chessboard;
    private boolean[] visited;
    private int X;
    private int Y;
    private boolean finished;

    public HorseChessboard(int x, int y) {
        this.X = x;
        this.Y = y;
        this.chessboard = new int[x][y];
        this.visited = new boolean[x*y];
    }

    public void traversalChessboard(int row, int col, int step) {
        visited[row * X + col] = true;
        chessboard[row][col] = step;

        List<Point> ps = next(new Point(row, col));
        sort(ps);   //贪心算法

        while (!ps.isEmpty()) {
            Point next = ps.remove(0);
            if (!visited[next.x * X + next.y]) {
                traversalChessboard(next.x, next.y, step + 1);
            }
        }

        if (step < X * Y && !finished) {    //回溯
            chessboard[row][col] = 0;
            visited[row * X + col] = false;
        } else {
            finished = true;
        }
    }

    public List<Point> next(Point curr) {
        List<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        if ((p1.x = curr.x - 1) >= 0 && (p1.y = curr.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curr.x - 2) >= 0 && (p1.y = curr.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curr.x - 2) >= 0 && (p1.y = curr.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curr.x - 1) >= 0 && (p1.y = curr.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curr.x + 1) < X && (p1.y = curr.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curr.x + 2) < X && (p1.y = curr.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curr.x + 2) < X && (p1.y = curr.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curr.x + 1) < X && (p1.y = curr.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    public void sort(List<Point> ps){
        Collections.sort(ps, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size() - next(o2).size();
            }
        });
    }

    public void show() {
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                System.out.print(chessboard[i][j]+ "\t");
            }
            System.out.println();
        }
    }
}

