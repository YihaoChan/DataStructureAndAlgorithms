package algorithms.sort.heapsort;

/**
 * @Description: 堆排序 - 测试类
 */
public class HeapSortTest {
    public static void main(String[] args) {
        // 《清华数据结构》 P280
        HeapSort heapSort = new HeapSort(8);

        heapSort.insert(38, "");
        heapSort.insert(65, "");
        heapSort.insert(97, "");
        heapSort.insert(49, "");
        heapSort.insert(76, "");
        heapSort.insert(13, "");
        heapSort.insert(27, "");
        heapSort.insert(49, "*"); // 验证排序算法是否稳定

        HeapSort.Info[] sequence = heapSort.getSequence();

        heapSort.sort(sequence);

        heapSort.printArr(sequence);
    }
}
