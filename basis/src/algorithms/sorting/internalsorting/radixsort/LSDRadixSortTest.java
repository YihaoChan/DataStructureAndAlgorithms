package algorithms.sorting.internalsorting.radixsort;

/**
 * @Description: 次位优先法基数排序
 */
public class LSDRadixSortTest {
    public static void main(String[] args) {
        // 《浙江大学数据结构》 P281
        LSDRadixSort lsdRadixSort = new LSDRadixSort(15, 2);

        lsdRadixSort.insert(64, "");
        lsdRadixSort.insert(8, "");
        lsdRadixSort.insert(216, "");
        lsdRadixSort.insert(512, "");
        lsdRadixSort.insert(27, "");
        lsdRadixSort.insert(729, "");
        lsdRadixSort.insert(27, "*"); // 验证排序算法是否稳定
        lsdRadixSort.insert(0, "");
        lsdRadixSort.insert(1, "");
        lsdRadixSort.insert(343, "");
        lsdRadixSort.insert(125, "");

        LSDRadixSort.Node[] sequence = lsdRadixSort.getSequence();

        lsdRadixSort.sort(sequence);

        lsdRadixSort.printArr(sequence);
    }
}
