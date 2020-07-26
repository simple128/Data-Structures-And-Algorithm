package wy.datastructures.huffmancode;

import java.io.*;
import java.util.*;

/**
 * 使用赫夫曼编码压缩/解压缩文件
 */
public class HuffmanCodeDemo {

    static StringBuilder stringBuilder = new StringBuilder();
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        //统计每个字符出现的次数
//        byte[] bytes = str.getBytes();
//        System.out.println("压缩前：" + Arrays.toString(bytes));
//        byte[] zipedFile = huffmanZip(bytes);
//        System.out.println("压缩后：" + Arrays.toString(zipedFile));
//        byte[] unzipedFile = decode(zipedFile, huffmanCodes);
//        System.out.println("解压后：" + Arrays.toString(unzipedFile));

        String srcFile = "C:\\Users\\wyall\\Desktop\\文件\\新建文本文档.txt";
        String distFile = "C:\\Users\\wyall\\Desktop\\文件\\新建文本文档.zip";
        zipFile(srcFile, distFile);

        String distUnzipFile = "C:\\Users\\wyall\\Desktop\\文件\\新建文本文档1.txt";
        unzipFile(distFile, distUnzipFile);
    }

    /**
     * 使用赫夫曼编码压缩文件
     * @param srcFile 待压缩的文件
     * @param distFile 压缩后的文件路径
     */
    public static void zipFile(String srcFile, String distFile) {
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            is.read(b);
            byte[] hufffmanBytes = huffmanZip(b);
            os = new FileOutputStream(distFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(hufffmanBytes);
            oos.writeObject(huffmanCodes);
            oos.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("文件压缩完毕~");
    }

    /**
     * 使用赫夫曼编码解压文件
     * @param srcFile 待解压的文件
     * @param distFile 解压后的文件路径
     */
    public static void unzipFile(String srcFile, String distFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(srcFile);
            ois = new ObjectInputStream(is);
            byte[] hufffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCode = (Map<Byte, String>) ois.readObject();
            byte[] byes = decode(hufffmanBytes, huffmanCode);
            os = new FileOutputStream(distFile);
            os.write(byes);
            os.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("文件解压完毕~");
    }

    /**
     * 将数据按赫夫曼编码解码
     * @param huffmanBytes  需要解码的数据
     * @param huffmanCodes  赫夫曼编码
     * @return
     */
    public static byte[] decode(byte[] huffmanBytes, Map<Byte, String> huffmanCodes) {
        //将bytes转换成二进制数字字符串
        StringBuilder huffmanStr = new StringBuilder();
        int lastCodeLength = huffmanBytes[0];
        for (int i = 1; i < huffmanBytes.length; i++) {
            boolean flag = i == huffmanBytes.length - 1;
            huffmanStr.append(byteToBitString(huffmanBytes[i], !flag, lastCodeLength));
        }
//        System.out.println(huffmanStr.toString());
//        System.out.println(huffmanStr.toString().length());
        Map<String, Byte> deHuffmanCodes = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            deHuffmanCodes.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < huffmanStr.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                String key = huffmanStr.substring(i, i + count);
                b = deHuffmanCodes.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 将字节转换成二进制表示的数字字符串
     * @param b 需要转换的字节
     * @param flag true:不是最后一组，false：最后一组编码
     * @param lastCodeLength lastCodeLength表示最后一个byte原本的长度
     * @return
     */
    private static String byteToBitString(byte b, boolean flag, int lastCodeLength) {
        int temp = b;
        temp |= 256;
        String str = Integer.toBinaryString(temp);
        if (flag || temp < 0) { //如果不是最后一组，或者最后一组是负数
            return str.substring(str.length() - 8);
        } else { //如果是最后一组且最后一组是正数或0
            return str.substring(str.length() - lastCodeLength);
        }

    }

    /**
     * 使用赫夫曼编码压缩数据
     * @param bytes 需要压缩的数据
     * @return
     */
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> huffmanNodes = getNodes(bytes);
        //获得赫夫曼树
        Node huffmanTree = createHuffmanTree(huffmanNodes);

        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);

        //利用赫夫曼树生成赫夫曼码
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }

    /**
     * 将字节数组按赫夫曼编码表压缩
     * @param bytes 需要压缩的字节数组
     * @param huffmanCodes 赫夫曼编码表
     * @return
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(huffmanCodes.get(b));
        }
//        System.out.println(sb.toString());
//        System.out.println(sb.toString().length());
        int len = (sb.length() + 7) / 8;
        byte[] huffmanBytes = new byte[len+1];
        //将赫夫曼编码按8位一组分组，将最后一组的位数保存在byte数组的第一位
        String lastCodelenth = "" + (sb.length() % 8);
        huffmanBytes[0] = (byte) Integer.parseInt(lastCodelenth);
        int index = 1;

        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;
            if (i + 8 > sb.length()) {
                strByte = sb.substring(i);
            } else {
                strByte = sb.substring(i, i + 8);
            }
            huffmanBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return huffmanBytes;
    }

    /**
     * 获得赫夫曼编码表
     *
     * @param huffmanTree
     * @return
     */
    private static Map<Byte, String> getCodes(Node huffmanTree) {
        if (huffmanTree == null) {
            return null;
        }
        getCodes("0", huffmanTree.left, stringBuilder);
        getCodes("1", huffmanTree.right, stringBuilder);
        return huffmanCodes;
    }

    /**
     * 递归生成赫夫曼编码表
     * @param code
     * @param node
     * @param stringBuilder
     */
    private static void getCodes(String code, Node node, StringBuilder stringBuilder) {
        StringBuilder sb = new StringBuilder(stringBuilder);
        sb.append(code);
        if (node != null) {
            if (node.data == null) { //非叶子节点，拼接编码
                getCodes("0", node.left, sb);
                getCodes("1", node.right, sb);
            } else {
                huffmanCodes.put(node.data, sb.toString()); //找到了叶子节点
            }
        }
    }


    /**
     * 生成Nodes数组
     *
     * @param bytes
     * @return
     */
    private static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();
        for (Byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 生成赫夫曼树
     *
     * @param nodes
     * @return
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        Collections.sort(nodes);
        while (nodes.size() > 1) {
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(parent);
            Collections.sort(nodes);
        }
        return nodes.get(0);
    }


}

class Node implements Comparable<Node> {
    int weight;
    Byte data;
    Node left;
    Node right;

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
