package wy.datastructures.queen;

public class ArrayQueen {

    private int[] array;

    private int length;

    private int head;

    private int tail;

    public ArrayQueen(int length) {
        if (length == 0) throw new RuntimeException("队列长度不能为0！");
        this.head = 0;
        this.tail = 0;
        this.length = length+1;
        this.array = new int[length+1];
    }

    public int peek() {
        if (isEmpty()) throw new RuntimeException("队列为空！");
        return array[head];
    }

    public int pop() {
        if (isEmpty()) throw new RuntimeException("队列为空！");
        int res = array[head];
        array[head] = 0;
        head = (head + 1) % length;
        return res;
    }

    public void push(int num) {
        if (isFull()) {
            throw new RuntimeException("队列已满！");
        }
        array[tail] = num;
        tail = (tail + 1) % length;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (tail + 1) % length == head;
    }

    public int size() {
        return (tail - head + length) % length;
    }

    public static void main(String[] args) {
        ArrayQueen queen = new ArrayQueen(5);
        System.out.println("队列是否为空:" + queen.isEmpty());
        for (int i = 0; i < 5; i++) {
            queen.push(i);
        }
        System.out.println("队列长度：" + queen.size());
        System.out.println("看一下头部元素：" + queen.peek());
        System.out.println("队列长度：" + queen.size());
        System.out.println("弹出头部元素" + queen.pop());
        System.out.println("队列长度：" + queen.size());
        System.out.println("弹出头部元素" + queen.pop());
        System.out.println("队列长度：" + queen.size());
        System.out.println("弹出头部元素" + queen.pop());
        System.out.println("队列长度：" + queen.size());
        queen.push(18);
        System.out.println("队列长度：" + queen.size());
        queen.push(20);
        System.out.println("队列长度：" + queen.size());
        queen.push(55);
        System.out.println("队列长度：" + queen.size());
        queen.push(31);
        System.out.println("队列长度：" + queen.size());
        queen.push(20);
        System.out.println("队列长度：" + queen.size());
        System.out.println(queen);

    }
}
