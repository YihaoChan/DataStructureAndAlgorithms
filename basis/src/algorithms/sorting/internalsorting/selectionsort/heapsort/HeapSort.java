package algorithms.sorting.internalsorting.selectionsort.heapsort;

/**
 * @Description: 堆排序
 */
public class HeapSort {
    class Info {
        // 关键字
        private int key;

        // 是否与其他元素关键字相等
        private String tag;

        private Info(int key, String tag) {
            this.key = key;
            this.tag = tag;
        }
    }

    // 待排序序列
    private Info[] sequence;

    // 数组中元素个数
    private int size;

    // 数组容量
    private int capacity;

    public Info[] getSequence() {
        return this.sequence;
    }

    /**
     * @Description: 构造方法
     */
    public HeapSort(int capacity) {
        this.capacity = capacity;

        this.size = 0;

        this.sequence = new Info[capacity]; // 实际排序中，"用户"不会像用最大堆存放数据一样会留出一个位置用做哨兵
    }

    private boolean isFull() {
        return this.size == this.capacity;
    }

    /**
     * @Description: 添加元素
     */
    public void insert(int key, String tag) {
        if (isFull()) {
            throw new RuntimeException("数组已满");
        }

        this.sequence[this.size++] = new Info(key, tag);
    }

    /**
     * @Description: 交换两个元素
     */
    private void swap(Info[] arr, int i, int j) {
        Info temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
    
    /**
     * @Description: 将给定起点到终点的堆下滤调整
     */
    private void percolateDown(Info[] arr, int start, int end) {
        Info x = arr[start];
        
        int parent, child;
        
        for (parent = start; parent * 2 + 1 < end; parent = child) {
            child = parent * 2 + 1; // 左子结点下标
            
            if (child != end - 1 && arr[child].key < arr[child + 1].key) {
                child++; // child指向左右子结点的较大者
            }

            if (x.key < arr[child].key) {
                arr[parent] = arr[child]; // 下滤
            } else {
                break; // 调整结束
            }
        }

        arr[parent] = x;
    }
    
    /**
     * @Description: 排序 
     */
    public void sort(Info[] arr) {
        int i;
        
        /* 建立最大堆 */
        for (i = this.size / 2 - 1; i >= 0; i--) {
            percolateDown(arr, i, this.size);
        }
        
        /* 交换堆顶元素和最后一个参与下滤的元素，并对除了交换到数组末尾的原堆顶元素的其他所有元素进行调整 */
        for (i = this.size - 1; i > 0; i--) {
            swap(arr, 0, i);
            percolateDown(arr, 0, i);
        }
    }

    /**
     * @Description: 查看数组元素
     */
    public void printArr(Info[] arr) {
        for (int i = 0; i < this.size; i++) {
            System.out.println(arr[i].key + ": " + arr[i].tag);
        }
    }
}
