package wy.datastructures.array;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SparseArrayDemo {

    public static String saveGame(int[][] chessArr) throws Exception{
        System.out.println("转换前的二维数组~~");
        printArr(chessArr);
        String savePath = "";
        //1、获取棋盘上的棋子数量
        int sum = 0;
        for (int[] row : chessArr) {
            for (int item : row) {
                if (item != 0) sum++;
            }
        }

        //2、将二维数组转换成稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        int count = 0;
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }
        System.out.println("转换后的稀疏数组~~");
        printArr(sparseArr);

        //3、存文件
        File file = new File(System.getProperty("user.dir") + File.separator + "map"+ System.currentTimeMillis() +".data");
        savePath = file.getAbsolutePath();
        FileWriter writer = new FileWriter(file);
        for (int[] row : sparseArr) {
            for (int data : row) {
                writer.write(data + ",");
            }
            writer.write("\n");
        }
        writer.flush();
        writer.close();
        return savePath;
    }

    public static int[][] readGame(String savePath)  throws Exception{
        // 1、读文件创建稀疏数组
        FileReader fileReader = new FileReader(savePath);
        BufferedReader reader = new BufferedReader(fileReader);
        String str = null;
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        while ((str = reader.readLine()) != null) {
            String[] strs = str.split(",");
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (String s : strs) {
                row.add(Integer.parseInt(s));
            }
            lists.add(row);
        }
        reader.close();
        fileReader.close();

        // 2、稀疏数组转二维数组
        int[][] chessArr = new int[lists.get(0).get(0)][lists.get(0).get(1)];
        for (int i = 1; i < lists.size(); i++) {
            chessArr[lists.get(i).get(0)][lists.get(i).get(1)] = lists.get(i).get(2);
        }

        System.out.println("读取后的二维数组~~");
        printArr(chessArr);
        return chessArr;
    }

    public static void main(String[] args) throws Exception{
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][3] = 1;
        chessArr[1][5] = 2;
        chessArr[3][2] = 1;
        chessArr[3][3] = 2;
        chessArr[5][5] = 1;

        String savePath = saveGame(chessArr);
        readGame(savePath);
    }


    public static void printArr(int[][] array) {
        for (int[] row : array) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }
        System.out.println();
    }

}
