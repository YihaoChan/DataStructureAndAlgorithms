package algorithms.sorting.internalsorting.swapsort.quicksort;


/**
 * @Description: 快速排序
 */
public class QuickSort {
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
    public QuickSort(int capacity) {
        this.capacity = capacity;

        this.size = 0;

        this.sequence = new Info[capacity];
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
     * @Description: 根据枢轴将数组划分成两边，返回枢轴下标
     */
    private int partition(Info[] arr, int low, int high) {
        Info pivot = arr[low];

        while (low < high) {
            while (low < high && arr[high].key >= pivot.key) {
                high--;
            }

            arr[low] = arr[high]; // 比pivot小的换到前面

            while (low < high && arr[low].key <= pivot.key) {
                low++;
            }

            arr[high] = arr[low]; // 比pivot大的换到后面
        }

        arr[low] = pivot; // 放置枢轴

        return low;
    }

    /**
     * @Description: 快速排序 - 逻辑
     */
    private void quickSort(Info[] arr, int low, int high) {
        if (low < high) {
            int pivotLoc = partition(arr, low, high);

            quickSort(arr, low, pivotLoc); // 递归完成左边

            quickSort(arr, pivotLoc + 1, high); // 递归完成右边
        }
    }

    /**
     * @Description: 排序
     */
    public void sort(Info[] arr) {
        quickSort(arr, 0, this.size - 1);
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