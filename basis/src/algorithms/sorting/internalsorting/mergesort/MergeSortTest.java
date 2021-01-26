package algorithms.sorting.internalsorting.mergesort;


/**
 * @Description: 归并排序 - 测试类
 */
public class MergeSortTest {
    public static void main(String[] args) {
        // 《清华数据结构》 P283
        MergeSort mergeSort = new MergeSort(15);

        mergeSort.insert(49, "");
        mergeSort.insert(38, "");
        mergeSort.insert(49, "*"); // 验证排序算法是否稳定
        mergeSort.insert(65, "");
        mergeSort.insert(97, "");
        mergeSort.insert(76, "");
        mergeSort.insert(13, "");
        mergeSort.insert(27, "");

        MergeSort.Info[] sequence = mergeSort.getSequence();

        mergeSort.sort(sequence);

        mergeSort.printArr(sequence);
    }
}
