package wy.datastructures.huffmancode;

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
        byte[] bytes = str.getBytes();
        System.out.println("压缩前：" + Arrays.toString(bytes));
        byte[] zipedFile = huffmanZip(bytes);
        System.out.println("压缩后：" + Arrays.toString(zipedFile));
        byte[] unzipedFile = unzipFile(zipedFile, huffmanCodes);
        System.out.println("解压后：" + Arrays.toString(unzipedFile));
    }

    public static byte[] unzipFile(byte[] huffmanBytes, Map<Byte, String> huffmanCodes) {
        //将bytes转换成二进制数字字符串
        StringBuilder huffmanStr = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = i == huffmanBytes.length - 1;
            huffmanStr.append(byteToBitString(huffmanBytes[i], !flag));
        }
        System.out.println(huffmanStr.toString());
        Map<String, Byte> deHuffmanCodes = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            deHuffmanCodes.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0, index = 0; i < huffmanStr.length(); ) {
            Byte b = deHuffmanCodes.get(huffmanStr.substring(i, index));
            if (b == null) {
                index++;
            } else {
                list.add(b);
                i = index;
            }
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    private static String byteToBitString(byte b, boolean flag) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag || temp < 0) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }

    }

    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> huffmanNodes = getNodes(bytes);
        //获得赫夫曼树
        Node huffmanTree = createHuffmanTree(huffmanNodes);

        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);

        //利用赫夫曼树生成赫夫曼码
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }


    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(huffmanCodes.get(b));
        }
        System.out.println(sb.toString());
        int len = (sb.length() + 7) / 8;
        byte[] huffmanBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;
            if (i + 8 < sb.length()) {
                strByte = sb.substring(i, i + 8);
            } else {
                strByte = sb.substring(i);
            }
            huffmanBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return huffmanBytes;
    }

    /**
     * 获得赫夫曼编码
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
