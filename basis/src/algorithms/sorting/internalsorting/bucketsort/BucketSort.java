package algorithms.sorting.internalsorting.bucketsort;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @Description: 桶排序
 */
public class BucketSort {
    /**
     * @Description: 链表中的每个结点
     */
    class Node {
        // 关键字
        private Double key;

        // 是否与其他元素关键字相等
        private String tag;

        private Node(Double key, String tag) {
            this.key = key;
            this.tag = tag;
        }
    }

    // 元素的取值范围
    private int range;

    // 根据取值范围创建的桶
    private ArrayList<LinkedList<Node>> bucket;

    // 待排序序列
    private Node[] sequence;

    // 数组容量
    private int capacity;

    // 数组中元素个数
    private int size;

    /**
     * @Description: 构造方法
     */
    public BucketSort(int capacity, int range) {
        this.capacity = capacity;

        this.range = range;

        this.sequence = new Node[capacity];

        this.bucket = new ArrayList<>(capacity);

        for (int i = 0; i < this.range; i++) {
            this.bucket.add(new LinkedList<>());
        }
    }

    public Node[] getSequence() {
        return this.sequence;
    }

    /**
     * @Description: 插入元素
     */
    public void insert(double key, String tag) {
        this.sequence[this.size++] = new Node(key, tag);
    }

    /**
     * @Description: 返回浮点数的小数点第一位
     */
    private int getDecimalFirst(double x) {
        return (int) (x / 0.1);
    }

    /**
     * @Description: 排序
     */
    public void sort(Node[] arr) {
        // 将数组元素插入到桶中
        for (int i = 0; i < this.size; i++) {
            int bucketNum = getDecimalFirst(arr[i].key);

            // 从数组中取出一个元素，如果直接将它这整个结点添加到链表中，那么此时数组当下这个位置和链表的结点用的是同一个地址
            // 那么后续将桶元素倒回到数组时，如果覆盖了数组中一个地址的元素，那么链表中对应地址的元素也会被修改！
            // 因此不能直接将整个结点添加到链表中，而应该根据key和tag，创建一个新结点再插入
            this.bucket.get(bucketNum).addLast(new Node(arr[i].key, arr[i].tag));
        }

        // 对桶链表中的结点进行排序
        for (int j = 0; j < this.bucket.size(); j++) {
            bucket.get(j).sort(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.key.compareTo(o2.key);
                }
            });

            /* 也可以这样排序 */
            // Collections.sort(bucket.get(j), new Comparator<Node>() {
            // //     @Override
            // //     public int compare(Node o1, Node o2) {
            // //         return o1.key.compareTo(o2.key);
            // //     }
            // // });
        }

        // 将桶内元素倒回原数组
        int k = 0;

        for (LinkedList<Node> list : this.bucket) {
            for (Node node : list) {
                arr[k].key = node.key;
                arr[k].tag = node.tag;
                k++;
            }
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