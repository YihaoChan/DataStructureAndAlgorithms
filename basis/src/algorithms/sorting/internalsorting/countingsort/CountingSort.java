package algorithms.sorting.internalsorting.countingsort;

/**
 * @Description: 计数排序
 */
public class CountingSort {
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
    
    // 存放排序输出
    private Info[] out;
    
    // 记录小于x的元素个数
    private int[] less;
    
    // 序列中元素的最大值
    private int maxNum;
    
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
    public CountingSort(int capacity) {
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
        
        if (key > this.maxNum) {
            this.maxNum = key;
        }
    }
    
    /**
     * @Description: 排序 
     */
    public void sort(Info[] arr) {
        // 初始化
        less = new int[this.maxNum + 1];
        
        for (int i = 0; i < less.length; i++) {
            less[i] = 0;
        }

        out = new Info[this.size];

        for (int j = 0; j < this.size; j++) {
            out[j] = null;
        }

        // less[p]的值为等于p的元素个数
        for (int k = 0; k < this.size; k++) {
            less[arr[k].key] = less[arr[k].key] + 1;
        }
        
        // less[p]的值为小于等于p的元素个数
        for (int l = 0; l < less.length; l++) {
            if (l > 0) {
                less[l] = less[l] + less[l - 1];
            }
        }

        // 取出元素放入输出数组，从后往前取，维护稳定性
        for (int m = this.size - 1; m >= 0; m--) {
            out[less[arr[m].key] - 1] = arr[m];
            less[arr[m].key] = less[arr[m].key] - 1;
        }

        // 复制回原数组
        for (int n = 0; n < this.size; n++) {
            arr[n] = out[n];
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
