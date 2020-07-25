package wy.datastructures.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = new int[]{13, 7, 8, 3, 29, 6, 1};
        Node huffmantree = createHuffmanTree(arr);
        huffmantree.preOrder();

    }

    public static Node createHuffmanTree(int[] arr) {
        List<Node> list = new ArrayList<>();
        for (int i : arr) {
            list.add(new Node(i));
        }
        Collections.sort(list);
        while (list.size() > 1) {
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            list.remove(0);
            list.remove(0);
            list.add(parent);
            Collections.sort(list);
        }
        return list.get(0);
    }

}


class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public void preOrder(){
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}