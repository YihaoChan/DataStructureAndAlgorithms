package algorithms.sorting.internalsorting.insertionsort.shellsort;


/**
 * @Description: 希尔排序
 */
public class ShellSort {
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
    public ShellSort(int capacity) {
        this.capacity = capacity;

        this.size = 0;

        this.sequence = new Info[++capacity]; // 0号元素为哨兵
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

        this.sequence[++this.size] = new Info(key, tag);
    }

    /**
     * @Description: 排序
     * 注意：希尔排序设置岗哨后，仍然需要判断内层循环下标是否大于0，
     * 因为内层循环下标减去gap时，可能会减成负数导致数组越界.
     */
    public void sort(Info[] arr) {
        for (int gap = this.size / 2; gap > 0; gap /= 2) { // 增量序列
            int i, j;

            // 对每个区间中的元素从后往前进行插入排序
            for (i = gap + 1; i <= this.size; i++) { // 第0个元素为哨兵，所以要+1
                if (arr[i].key < arr[i - gap].key) {
                    arr[0] = arr[i]; // 待插入元素拷贝至哨兵位置

                    arr[i] = arr[i - gap]; // 比较过的元素后移

                    /* 已经比较过arr[i]和arr[i - gap]，所以内层循环从i - 2 * gap开始，就不需要再比一遍 */
                    for (j = i - 2 * gap; j > 0 && arr[0].key < arr[j].key; j -= gap) {
                        // 待插入元素 < 当前元素 => 当前元素后移
                        arr[j + gap] = arr[j];
                    }

                    // 找到插入位置
                    arr[j + gap] = arr[0];
                }
            }
        }
    }

    /**
     * @Description: 查看数组元素
     */
    public void printArr(Info[] arr) {
        for (int i = 1; i <= this.size; i++) {
            System.out.println(arr[i].key + ": " + arr[i].tag);
        }
    }
}
