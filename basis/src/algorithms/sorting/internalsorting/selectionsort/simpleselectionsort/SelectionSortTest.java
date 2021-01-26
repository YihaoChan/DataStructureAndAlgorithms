package algorithms.sorting.internalsorting.selectionsort.simpleselectionsort;

/**
 * @Description: 选择排序 - 测试类
 */
public class SelectionSortTest {
    public static void main(String[] args) {
        // 《清华数据结构》 P271
        SelectionSort selectionSort = new SelectionSort(15);

        selectionSort.insert(49, "");
        selectionSort.insert(38, "");
        selectionSort.insert(65, "");
        selectionSort.insert(97, "");
        selectionSort.insert(76, "");
        selectionSort.insert(13, "");
        selectionSort.insert(27, "");
        selectionSort.insert(49, "*"); // 验证排序算法是否稳定
        selectionSort.insert(55, "");
        selectionSort.insert(4, "");

        SelectionSort.Info[] sequence = selectionSort.getSequence();

        selectionSort.sort(sequence);

        selectionSort.printArr(sequence);
    }
}
