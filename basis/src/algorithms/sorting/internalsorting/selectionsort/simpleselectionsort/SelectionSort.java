package algorithms.sorting.internalsorting.selectionsort.simpleselectionsort;


/**
 * @Description: 选择排序
 */
public class SelectionSort {
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
    public SelectionSort(int capacity) {
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
     * @Description: 排序
     */
    public void sort(Info[] arr) {
        int i, j;

        // 最后一个元素不用进行扫描，因为就只剩下它这一个最大元素
        for (i = 0; i < this.size - 1; i++) {
            int minIndex = i; // 最小元素下标

            for (j = i; j < this.size; j++) {
                if (arr[j].key < arr[minIndex].key) {
                    minIndex = j;
                }
            }

            // 交换外层循环起点元素和最小元素
            if (minIndex != i) {
                Info temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
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
