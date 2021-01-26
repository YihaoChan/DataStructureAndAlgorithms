package algorithms.sorting.internalsorting.swapsort.quicksort;

/**
 * @Description: 快速排序 - 测试类
 */
public class QuickSortTest {
    public static void main(String[] args) {
        // 《清华数据结构》 P275
        QuickSort quickSort = new QuickSort(15);

        quickSort.insert(49, "");
        quickSort.insert(38, "");
        quickSort.insert(49, "*"); // 验证排序算法是否稳定
        quickSort.insert(65, "");
        quickSort.insert(97, "");
        quickSort.insert(76, "");
        quickSort.insert(13, "");
        quickSort.insert(27, "");

        QuickSort.Info[] sequence = quickSort.getSequence();

        quickSort.sort(sequence);

        quickSort.printArr(sequence);
    }
}
