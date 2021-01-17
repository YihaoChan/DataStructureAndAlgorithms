package datastructure.heap.maxbinaryheap;

/**
 * @Description: 最大堆(大顶堆)
 */
public class MaxBinaryHeap {
    // 存储元素的数组
    private int[] heap;

    // 堆中元素个数
    private int size;

    // 数组容量
    private int capacity;

    /* 构造方法 */
    public MaxBinaryHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[++capacity]; // 0号位置为哨兵
        this.heap[0] = Integer.MAX_VALUE; // 哨兵为正无穷，大于堆中的所有元素
    }

    /* 堆中元素数量 */
    public int size() {
        return this.size;
    }

    private boolean isFull() {
        return this.size == this.capacity;
    }

    /**
     * @Description: 插入元素
     */
    public void insert(int x) {
        if (isFull()) {
            throw new RuntimeException("堆已满");
        }

        int i = ++this.size; // 从数组末尾开始上滤

        /*
         * 如果添加的新结点的元素大于其父结点的元素，则交换位置，直至父结点元素大于等于子结点为止.
         * 1. 设置哨兵为正无穷的好处在于：如果没有哨兵，则每一次都要判断当前调整的元素是否为堆顶元素，
         *    即每一次都要判断下标是否为0。如果调整的元素是堆顶元素，调整后直接结束。
         *    但是这样子每一次除了要判断结点的大小关系外，还要进行下标的判断，很浪费时间。
         * 2. 所以设置哨兵后，直到发现当前结点小于父结点时，有两种情况：
         *    2.1 还没调整到堆顶结点时就已经调整完毕；
         *    2.2 遇到了正无穷，即调整到了堆顶。
         *    设置哨兵后，就不需要每一次再多去判断下标是否碰到0，只需要比较结点的大小即可，节省时间。
         */
        for (; x > heap[i / 2]; i /= 2) {
            heap[i] = heap[i / 2]; // 将父结点换下来，上滤x
        }

        heap[i] = x; // 插入
    }

    /**
     * @Description: 从堆中删除最大元素，即堆顶元素
     */
    public int deleteMax() {
        int maxItem = heap[1]; // 取出堆的最大值

        int x = heap[this.size--]; // 取出堆的最后一个元素，同时堆元素个数-1

        int parent, child;

        for (parent = 1; parent * 2 <= this.size; parent = child) {
            child = parent * 2; // 左子结点下标

            if (child != this.size && heap[child] < heap[child + 1]) {
                child++; // child指向左右子结点的较大者
            }

            if (x < heap[child]) {
                heap[parent] = heap[child]; // 下滤
            } else {
                break; // 调整结束
            }
        }

        heap[parent] = x; // 原先堆的最后一个元素放到新位置

        return maxItem;
    }
}
