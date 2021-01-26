package algorithms.sorting.internalsorting.bucketsort;

/**
 * @Description: 桶排序 - 测试类
 */
public class BucketSortTest {
    public static void main(String[] args) {
        // 《算法导论》 P112
        BucketSort bucketSort = new BucketSort(15, 10);

        bucketSort.insert(0.78, "");
        bucketSort.insert(0.17, "");
        bucketSort.insert(0.39, "");
        bucketSort.insert(0.26, "");
        bucketSort.insert(0.72, "");
        bucketSort.insert(0.94, "");
        bucketSort.insert(0.21, "");
        bucketSort.insert(0.26, "*"); // 验证排序算法是否稳定
        bucketSort.insert(0.12, "");
        bucketSort.insert(0.23, "");
        bucketSort.insert(0.68, "");

        BucketSort.Node[] sequence = bucketSort.getSequence();

        bucketSort.sort(sequence);

        bucketSort.printArr(sequence);
    }
}
