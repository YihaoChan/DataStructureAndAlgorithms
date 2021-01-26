package algorithms.sorting.internalsorting.insertionsort.straightinsertionsort;


/**
 * @Description: 直接插入排序
 */
public class StraightInsertionSort {
    class Info {
        // 关键字
        private int key;

        // 是否与其他元素关键字相等
        private String tag;

        public Info(int key, String tag) {
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
    public StraightInsertionSort(int capacity) {
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
     */
    public void sort(Info[] arr) {
        int i, j;

        for (i = 2; i <= this.size; i++) { // 第0个元素为哨兵，认为第1个元素本身有序
            if (arr[i].key < arr[i - 1].key) {
                arr[0] = arr[i]; // 待插入元素拷贝至哨兵位置

                arr[i] = arr[i - 1]; // 比较过的元素后移

                /* 已经比较过arr[i]和arr[i - 1]，所以内层循环从i - 2开始，就不需要再比一遍 */
                for (j = i - 2; arr[0].key < arr[j].key ; j--) {
                    // 待插入元素 < 当前元素 => 当前元素后移
                    arr[j + 1] = arr[j];
                }

                // 找到插入位置
                arr[j + 1] = arr[0];
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
