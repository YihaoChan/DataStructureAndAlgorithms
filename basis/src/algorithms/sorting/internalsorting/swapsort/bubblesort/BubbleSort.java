package algorithms.sorting.internalsorting.swapsort.bubblesort;

/**
 * @Description: 冒泡排序
 */
public class BubbleSort {
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
    public BubbleSort(int capacity) {
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
     * @Description: 交换两个元素
     */
    private void swap(Info[] arr, int i, int j) {
        Info temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    /**
     * @Description: 排序
     */
    public void sort(Info[] arr) {
        for (int times = this.size - 1; times >= 0; times--) { // 需要比较的次数
            boolean swapFlag = false; // 是否发生过交换操作

            for (int i = 0; i < times; i++) {
                if (arr[i].key > arr[i + 1].key) {
                    swap(arr, i, i + 1);
                    swapFlag = true;
                }
            }

            // 如果整一趟都没有发生交换操作，则已经为正序，直接跳出
            if (!swapFlag) {
                break;
            }
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
