package algorithms.sorting.internalsorting.mergesort;


/**
 * @Description: 归并排序
 */
public class MergeSort {
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
    public MergeSort(int capacity) {
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
     * @Description: 合并两个有序子序列
     * @param left 左子序列起始位置
     * @param right 右子序列起始位置
     * @param rightEnd 右子序列终点位置
     */
    private void merge(Info[] arr, Info[] tempArr, int left, int right, int rightEnd) {
        int leftEnd = right - 1; // 左边终点

        int temp = left; // 有序序列起始位置

        int numElements = rightEnd - left + 1; // 两个子序列的元素总和

        while (left <= leftEnd && right <= rightEnd) {
            // 将较小者拷贝进临时数组
            if (arr[left].key <= arr[right].key) {
                tempArr[temp++] = arr[left++];
            } else {
                tempArr[temp++] = arr[right++];
            }
        }

        while (left <= leftEnd) {
            tempArr[temp++] = arr[left++]; // 拷贝左子序列剩下全部元素
        }

        while (right <= rightEnd) {
            tempArr[temp++] = arr[right++]; // 拷贝右子序列剩下全部元素
        }

        // 拷贝回原数组
        for (int i = 0; i < numElements; i++, rightEnd--) {
            arr[rightEnd] = tempArr[rightEnd];
        }
    }

    /**
     * @Description: 归并排序 - 逻辑
     */
    private void mergeSort(Info[] arr, Info[] tempArr, int left, int rightEnd) {
        int center;

        // 如果只剩一个元素，left == rightEnd，不用排序直接返回
        if (left < rightEnd) {
            center = (left + rightEnd) / 2; // 折半

            mergeSort(arr, tempArr, left, center); // 递归解决左边
            mergeSort(arr, tempArr, center + 1, rightEnd); // 递归解决右边

            merge(arr, tempArr, left, center + 1, rightEnd);
        }
    }

    /**
     * @Description: 排序
     */
    public void sort(Info[] arr) {
        mergeSort(arr, new Info[this.size], 0, this.size - 1);
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
