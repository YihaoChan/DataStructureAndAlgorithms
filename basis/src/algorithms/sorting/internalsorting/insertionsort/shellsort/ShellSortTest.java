package algorithms.sorting.internalsorting.insertionsort.shellsort;

/**
 * @Description: 希尔排序 - 测试类
 */
public class ShellSortTest {
    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort(15);

        shellSort.insert(49, "");
        shellSort.insert(97, "");
        shellSort.insert(65, "");
        shellSort.insert(49, "*"); // 验证排序算法是否稳定
        shellSort.insert(38, "");
        shellSort.insert(27, "");
        shellSort.insert(13, "");
        shellSort.insert(76, "");

        ShellSort.Info[] sequence = shellSort.getSequence();

        shellSort.sort(sequence);

        shellSort.printArr(sequence);
    }
}
