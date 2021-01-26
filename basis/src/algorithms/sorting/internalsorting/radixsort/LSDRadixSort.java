package algorithms.sorting.internalsorting.radixsort;


/**
 * @Description: 次位优先法基数排序
 */
public class LSDRadixSort {
    /**
     * @Description: 链表中的每个结点
     */
    class Node {
        // 关键字
        private int key;

        // 是否与其他元素关键字相等
        private String tag;

        // next域
        private Node next;

        private Node(int key, String tag, Node next) {
            this.key = key;
            this.tag = tag;
            this.next = next;
        }

        private Node(int key, String tag) {
            this.key = key;
            this.tag = tag;
        }
    }

    /**
     * @Description: 桶链表
     */
    private class LinkedList {
        // 第一个结点
        private Node head;

        // 尾结点
        private Node tail;

        private LinkedList() {
            this.head = null;
            this.tail = null;
        }
    }

    // 根据基数创建的桶
    private LinkedList[] bucket;

    // 待排序序列
    private Node[] sequence;

    // 数组容量
    private int capacity;

    // 数组中元素个数
    private int size;

    // 关键字数量
    private int maxDigit;

    // 基数
    private int radix;

    /**
     * @Description: 构造方法
     */
    public LSDRadixSort(int capacity, int radix) {
        this.capacity = capacity;

        this.sequence = new Node[capacity];

        this.maxDigit = 0;

        this.radix = radix;

        this.bucket = new LinkedList[this.radix];

        for (int i = 0; i < this.radix; i++) {
            this.bucket[i] = new LinkedList();
        }
    }

    public Node[] getSequence() {
        return this.sequence;
    }

    /**
     * @Description: 插入元素
     */
    public void insert(int key, String tag) {
        this.sequence[this.size++] = new Node(key, tag);

        int digit = getDigitNum(key);

        if (digit > this.maxDigit) {
            this.maxDigit = digit;
        }
    }

    /**
     * @Description: 返回数字在给定基数下共有几位
     */
    private int getDigitNum(int x) {
        int count = 0;

        while (x != 0) {
            x /= this.radix;

            count++;
        }

        return count;
    }

    /**
     * @Description: 返回数字x的第d位数字
     */
    private int getDigit(int x, int d) {
        int remainder = 0;

        for (int i = 1; i <= d; i++) {
            remainder = x % this.radix;
            x /= this.radix;
        }

        return remainder;
    }

    /**
     * @Description: 排序
     */
    public void sort(Node[] arr) {
        Node list = null; // 用于收集的链表

        // 将原始序列存入初始链表中
        for (int i = 0; i < this.size; i++) {
            if (list == null) {
                list = new Node(arr[i].key, arr[i].tag, null);
            } else {
                // 插入到链表尾部
                Node node = list;

                while (node.next != null) {
                    node = node.next;
                }

                node.next = new Node(arr[i].key, arr[i].tag, null);
            }
        }

        // 分配
        for (int d = 1; d <= this.maxDigit; d++) {
            for (Node item = list; item != null; item = item.next) {
                // 获取当前元素的某一位数字
                int di = getDigit(item.key, d);

                // 创建结点
                Node newNode = new Node(item.key, item.tag);

                // 插入到链表尾端
                if (bucket[di].head == null) {
                    bucket[di].head = bucket[di].tail = newNode;
                } else {
                    bucket[di].tail.next = newNode;
                    bucket[di].tail = newNode;
                }
            }

            // 收集，形成新的记录链
            list = null;

            for (int j = this.radix - 1; j >= 0; j--) {
                if (bucket[j].head != null) {
                    // 整个桶插入表头
                    bucket[j].tail.next = list;

                    list = bucket[j].head;

                    // 清空桶
                    bucket[j].head = bucket[j].tail = null;
                }
            }
        }

        // 将list倒回原数组
        for (int k = 0; k < this.size; k++) {
            arr[k].key = list.key;
            arr[k].tag = list.tag;

            list = list.next;
        }
    }

    /**
     * @Description: 查看数组元素
     */
    public void printArr(Node[] arr) {
        for (int i = 0; i < this.size; i++) {
            System.out.println(arr[i].key + ": " + arr[i].tag);
        }
    }
}
