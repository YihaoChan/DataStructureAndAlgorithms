package algorithms.sorting.internalsorting.countingsort;

/**
 * @Description: 计数排序 - 测试类
 */
public class CountingSortTest {
    public static void main(String[] args) {
        // 《算法导论》 P109
        CountingSort countingSort = new CountingSort(15);

        countingSort.insert(2, "");
        countingSort.insert(5, "");
        countingSort.insert(3, "");
        countingSort.insert(0, "");
        countingSort.insert(2, "*"); // 验证排序算法是否稳定
        countingSort.insert(3, "*"); // 验证排序算法是否稳定
        countingSort.insert(0, "*"); // 验证排序算法是否稳定
        countingSort.insert(3, "**"); // 验证排序算法是否稳定

        CountingSort.Info[] sequence = countingSort.getSequence();

        countingSort.sort(sequence);

        countingSort.printArr(sequence);
    }
}
