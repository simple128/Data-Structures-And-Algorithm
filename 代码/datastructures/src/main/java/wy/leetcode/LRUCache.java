package wy.leetcode;

import java.util.HashMap;

/**
 * @author wangying
 * @description: TODO
 * @date 2021/3/17 10:05
 */
public class LRUCache<K,V> {
    class Node<K,V> {
        K key;
        V value;
        Node<K,V> prev, next;
        public Node(){}
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private int capacity;
    private HashMap<K,Node> map;
    private Node<K,V> head;
    private Node<K,V> tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        Node<K,V> node = map.get(key);
        if (node == null) {
            return null;
        }
        moveNodeToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        Node<K,V> node = map.get(key);
        if (node == null) {
            if (map.size() >= capacity) {
                map.remove(tail.prev.key);
                removeTailNode();
            }
            Node<K,V> newNode = new Node<>(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        } else {
            node.value = value;
            moveNodeToHead(node);
        }
    }

    private void addToHead(Node<K,V> newNode) {
        newNode.prev = head;
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
    }

    private void moveNodeToHead(Node<K,V> node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void removeTailNode() {
        removeNode(tail.prev);
    }

    public static void main(String[] args) {
        LRUCache<Integer,Integer> lruCache = new LRUCache<>(3);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        lruCache.get(1);
        lruCache.put(4,4);
        System.out.println(lruCache); // toString 我就没贴了，代码太长了
    }
}
