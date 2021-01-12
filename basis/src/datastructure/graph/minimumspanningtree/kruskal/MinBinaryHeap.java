package datastructure.graph.minimumspanningtree.kruskal;

/**
 * @Description: 用最小堆存放图中的边(包含起点和终点)和权重
 */
public class MinBinaryHeap {
    /**
     * @Description: 每一个堆中元素都有三个域：边的起点、边的终点、边的权重
     */
    class Edge {
        // 起始顶点
        private int start;

        // 末端顶点
        private int end;

        // 边的权重
        private int weight;

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }

        public int getWeight() {
            return this.weight;
        }

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    // 存储堆中元素的数组
    private Edge[] heap;

    // 堆中元素个数，同时也是数组中最后一个元素的索引(因为0号索引为哨兵，不参与数组计算)
    private int size;

    /* 构造方法 */
    public MinBinaryHeap(int capacity) {
        this.heap = new Edge[++capacity]; // 0号元素为哨兵
        this.heap[0] = new Edge(-1, -1, Integer.MIN_VALUE); // 哨兵无起点、终点，权值负无穷
        this.size = 0;
    }

    public Edge[] getHeap() {
        return this.heap;
    }

    public int getLength() {
        return this.heap.length;
    }

    /* 获取堆中元素数量 */
    public int getSize() {
        return this.size;
    }

    /**
     * @Description: 向堆中插入一个元素
     */
    public void insert(Edge edge) {
        // 数组中存放的最后一个元素的索引+1，用于存放新元素
        size++;

        // 将新元素放入数组
        heap[size] = edge;

        // 根据新添加的元素索引，使新添加的元素通过上滤找到插入的合适位置
        percolateUp(size);
    }

    /**
     * @Description: 末端插入 - 上滤
     * 在数组的末端插入元素，并使该元素通过上浮正确插入到堆中，以保持堆的秩序
     */
    private void percolateUp(int k) {
        /*
         * 如果添加的新结点的元素小于其父结点的元素，则交换位置，直至父结点元素小于等于子结点为止.
         * 1. 设置哨兵为负无穷的好处在于：如果没有哨兵，则每一次都要判断当前调整的元素是否为堆顶元素，
         *    即每一次都要判断下标是否为0。如果调整的元素是堆顶元素，调整后直接结束。
         *    但是这样子每一次除了要判断结点的大小关系外，还要进行下标的判断，很浪费时间。
         * 2. 所以设置哨兵后，直到发现当前结点大于父结点时，有两种情况：
         *    2.1 还没调整到堆顶结点时就已经调整完毕；
         *    2.2 遇到了负无穷，即调整到了堆顶。
         *    设置哨兵后，就不需要每一次再多去判断下标是否碰到0，只需要比较结点的大小即可，节省时间。
         */
        while (heap[k].weight < heap[k / 2].weight) {
            // 交换新元素和父结点元素
            Edge temp = heap[k / 2];
            heap[k / 2] = heap[k];
            heap[k] = temp;

            // k步进，让添加的元素上浮，继续进行判断
            k = k / 2;
        }
    }

    /**
     * @Description: 从堆中删除最小元素，即堆顶部元素，并返回该元素
     */
    public Edge deleteMin() {
        // 0号元素为哨兵，最小堆的1号元素为堆的最小元素
        Edge minEle = heap[1];

        // 弹出最小元素，其他元素通过下滤调整位置，维护堆的有序性
        percolateDown(1);

        // 元素数量减1
        size--;

        return minEle;
    }

    /**
     * @Description: 顶端删除 - 下滤
     * 将最小元素删除，并让其他元素不断比较，进行空结点的填充
     */
    private void percolateDown(int k) {
        // 原来的被删除结点的子结点中较小者的索引
        int smallerChildIndex;

        // 让被移动的元素的子结点中的较小者移动到原来的父结点处
        while (k <= size || k + 1 <= size) {
            if (2 * k > size || 2 * k + 1 > size) {
                // 子结点下标超过堆的长度，说明已经下滤到最底层，跳出
                break;
            }

            if (heap[2 * k] == null && heap[2 * k + 1] == null) {
                // 如果两个子结点都为空，说明已经下滤到最底层，则将数组末端元素移动到该位置进行填充
                // 跳出while单独处理
                break;
            }

            if (heap[2 * k] != null && heap[2 * k + 1] != null) {
                // 左右子结点都不为空
                smallerChildIndex = heap[2 * k].weight < heap[2 * k + 1].weight ? 2 * k : 2 * k + 1;
            } else {
                // 有任意一个为空，则找到非空结点
                smallerChildIndex = heap[2 * k] != null ? 2 * k : 2 * k + 1;
            }

            // 移动较小子结点进行被移动结点的填充
            heap[k] = heap[smallerChildIndex];

            // 删除移动的结点
            heap[smallerChildIndex] = null;

            // k步进
            k = smallerChildIndex;
        }

        // 如果两个子结点都为空，说明已经下滤到最底层，则将数组末端元素移动到该位置进行填充，并将数组末端元素置空
        heap[k] = heap[size];
        heap[size] = null;
    }
}
