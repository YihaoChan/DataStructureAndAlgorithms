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

    // 堆中元素个数
    private int size;

    // 数组容量
    private int capacity;

    /**
     * @Description: 构造方法
     */
    public MinBinaryHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new Edge[++capacity]; // 0号元素为哨兵
        this.heap[0] = new Edge(-1, -1, Integer.MIN_VALUE); // 哨兵无起点、终点，权值负无穷
        this.size = 0;
    }

    public Edge[] getHeap() {
        return this.heap;
    }

    /**
     * @Description: 堆中元素数量
     */
    public int getSize() {
        return this.size;
    }

    private boolean isFull() {
        return this.size == this.capacity;
    }

    /**
     * @Description: 插入元素
     */
    public void insert(Edge edge) {
        if (isFull()) {
            throw new RuntimeException("堆已满");
        }

        int i = ++this.size; // 从数组末尾开始上滤

        for (; edge.weight < heap[i / 2].weight; i /= 2) {
            heap[i] = heap[i / 2]; // 上滤待插入元素
        }

        heap[i] = edge; // 插入
    }

    /**
     * @Description: 从堆中取出最小权值的边
     */
    public Edge getMinWeightEdge() {
        Edge minWeightEdge = heap[1];

        Edge x = heap[this.size--]; // 取出堆的最后一个元素，之后堆元素个数-1

        int parent, child;

        for (parent = 1; parent * 2 <= this.size; parent = child) {
            child = parent * 2; // 左子结点下标

            if (child != this.size && heap[child].weight > heap[child + 1].weight) {
                child++; // child指向左右子结点的较大者
            }

            if (x.weight > heap[child].weight) {
                heap[parent] = heap[child]; // 下滤原先堆的最后一个元素
            } else {
                break; // 调整结束
            }
        }

        heap[parent] = x; // 原先堆的最后一个元素放到新位置

        return minWeightEdge;
    }
}
