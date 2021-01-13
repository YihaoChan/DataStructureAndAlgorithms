package datastructure.heap.maxbinaryheap;

/**
 * @Description: 最大堆(大顶堆)
 */
public class MaxBinaryHeap {
    // 存储堆中元素的数组
    private Integer[] heap;

    // 堆中元素个数，同时也是数组中最后一个元素的索引(因为0号索引为哨兵，不参与数组计算)
    private int size;

    /* 构造方法 */
    public MaxBinaryHeap(int capacity) {
        this.heap = new Integer[++capacity]; // 0号位置为哨兵，所以要多申请一个空间
        this.heap[0] = Integer.MAX_VALUE; // 哨兵定义为正无穷，大于堆中的所有元素
        this.size = 0;
    }

    /* 获取堆中元素数量 */
    public int size() {
        return size;
    }

    /**
     * @Description: 向堆中插入一个元素
     */
    public void insert(int t) {
        // 数组中存放的最后一个元素的索引+1，用于存放新元素
        size++;

        // 将新元素放入数组
        heap[size] = t;

        // 根据新添加的元素索引，使新添加的元素通过上滤找到插入的合适位置
        percolateUp(size);
    }

    /**
     * @Description: 末端插入 - 上滤
     * 在数组的末端插入元素，并使该元素通过上浮正确插入到堆中，以保持堆的秩序
     */
    private void percolateUp(int k) {
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
        while (heap[k].compareTo(heap[k / 2]) > 0) {
            // 交换新元素和父结点元素
            Integer temp = heap[k / 2];
            heap[k / 2] = heap[k];
            heap[k] = temp;

            // k步进，让添加的元素上浮，继续进行判断
            k = k / 2;
        }
    }

    /**
     * @Description: 从堆中删除最大元素，即堆顶元素，并返回该元素
     */
    public int deleteMax() {
        // 0号元素为哨兵，最大堆的1号元素为删除的最大元素
        int maxEle = heap[1];

        // 拿出最大元素，并通过下滤调整其他元素的位置，使堆保持原有秩序
        percolateDown(1);

        // 元素数量减1
        size--;

        return maxEle;
    }

    /**
     * @Description: 顶端删除 - 下滤
     * 将最大元素删除，并让其他元素不断比较，进行空结点的填充
     */
    private void percolateDown(int k) {
        // 原来的被删除结点的子结点中较大者的索引
        int largerChildIndex;

        // 让被移动的元素的子结点中的较大者移动到原来的父结点处
        while (k <= size || k + 1 <= size) {
            if (2 * k > size || 2 * k + 1 > size) {
                // 子结点下标超过堆的长度，说明已经下滤到最底层，跳出
                break;
            }

            if (heap[2 * k] == null && heap[2 * k + 1] == null) {
                // 跳出while单独处理
                break;
            }

            if (heap[2 * k] != null && heap[2 * k + 1] != null) {
                // 左右子结点都不为空
                largerChildIndex = heap[2 * k].compareTo(heap[2 * k + 1]) > 0 ? 2 * k : 2 * k + 1;
            } else {
                // 有任意一个为空，则找到非空结点
                largerChildIndex = heap[2 * k] != null ? 2 * k : 2 * k + 1;
            }

            // 移动较大子结点进行被移动结点的填充
            heap[k] = heap[largerChildIndex];

            // 删除移动的结点
            heap[largerChildIndex] = null;

            // k步进
            k = largerChildIndex;
        }

        // 如果两个子结点都为空，说明已经下滤到最底层，则将数组末端元素移动到该位置进行填充，并将数组末端元素置空
        heap[k] = heap[size];
        heap[size] = null;
    }
}
