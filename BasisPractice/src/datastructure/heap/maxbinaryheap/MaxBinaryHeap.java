package datastructure.heap.maxbinaryheap;

/**
 * @Description: 用数组实现二叉堆中的大顶堆(最大堆)
 */
public class MaxBinaryHeap<T extends Comparable<T>> {
    // 存储堆中元素的数组
    private T[] heap;

    // 堆中元素个数，同时也是数组中最后一个元素的索引(因为0号索引为哨兵，不参与数组计算)
    private int n;

    /* 构造方法 */
    public MaxBinaryHeap(int capacity) {
        this.heap = (T[]) new Comparable[++capacity];
        this.n = 0;
    }

    /* 获取堆中元素数量 */
    public int size() {
        return n;
    }

    /**
     * @Description: 向堆中插入一个元素
     */
    public MaxBinaryHeap insert(T t) {
        // 数组中存放的最后一个元素的索引+1，用于存放新元素
        n++;

        // 将新元素放入数组
        heap[n] = t;

        // 根据新添加的元素索引，使新添加的元素通过上滤找到插入的合适位置
        percolateUp(n);

        return this;
    }

    /**
     * @Description: 末端插入 - 上滤
     * 在数组的末端插入元素，并使该元素通过上浮正确插入到堆中，以保持堆的秩序
     */
    private void percolateUp(int k) {
        // 如果添加的新结点的元素大于其父结点的元素，则交换位置，直至父结点元素大于等于子结点为止
        // 由于0号元素为哨兵，所以索引k不能碰到0。由于k会进行k / 2操作，因此k本身需要大于1
        T temp;

        while (k > 1) {
            if (heap[k].compareTo(heap[k / 2]) > 0) {
                // 交换新元素和父结点元素
                temp = heap[k / 2];
                heap[k / 2] = heap[k];
                heap[k] = temp;

                // k步进，让添加的元素上浮，继续进行判断
                k = k / 2;
            } else {
                break;
            }
        }
    }

    /**
     * @Description: 从堆中删除最大元素，即堆顶部元素，并返回该元素
     */
    public T deleteMax() {
        // 0号元素为哨兵，最大堆的1号元素为删除的最大元素
        T maxEle = heap[1];

        // 拿出最大元素，并通过下滤调整其他元素的位置，使堆保持原有秩序
        percolateDown(1);

        // 元素数量减1
        n--;

        return maxEle;
    }

    /**
     * @Description: 顶端删除 - 下滤
     * 将最大元素删除，并让其他元素不断比较，进行空结点的填充
     */
    private void percolateDown(int k) {
        // 删掉结点后，原来的被删除结点的子结点中较大者替换被删除结点的位置
        T largerChild;

        // 原来的被删除结点的子结点中较大者的索引
        int largerChildIndex;

        // 让被移动的元素的子结点中的较大者移动到原来的父结点处
        while (k <= n || k + 1 <= n) {
            if (heap[2 * k] == null && heap[2 * k + 1] == null) {
                // 如果两个子结点都为空，说明已经下滤到最底层，则将数组末端元素移动到该位置进行填充
                // 跳出while单独处理
                break;
            }

            if (heap[2 * k] != null && heap[2 * k + 1] != null) {
                // 左右子结点都不为空
                largerChild = heap[2 * k].compareTo(heap[2 * k + 1]) > 0 ? heap[2 * k] : heap[2 * k + 1];
                largerChildIndex = heap[2 * k].compareTo(heap[2 * k + 1]) > 0 ? 2 * k : 2 * k + 1;
            } else {
                // 有任意一个为空，则找到非空结点
                largerChild = heap[2 * k] != null ? heap[2 * k] : heap[2 * k + 1];
                largerChildIndex = heap[2 * k] != null ? 2 * k : 2 * k + 1;
            }

            // 移动较大子结点进行被移动结点的填充
            heap[k] = largerChild;

            // 删除移动的结点
            heap[largerChildIndex] = null;

            // k步进
            k = largerChildIndex;
        }

        // 如果两个子结点都为空，说明已经下滤到最底层，则将数组末端元素移动到该位置进行填充，并将数组末端元素置空
        heap[k] = heap[n];
        heap[n] = null;
    }
}
