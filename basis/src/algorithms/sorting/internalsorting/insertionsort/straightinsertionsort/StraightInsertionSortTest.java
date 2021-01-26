package algorithms.sorting.internalsorting.insertionsort.straightinsertionsort;


/**
 * @Description: 直接插入排序 - 测试类
 */
public class StraightInsertionSortTest {
    public static void main(String[] args) {
        // 《清华数据结构》 P266
        StraightInsertionSort straightInsertionSort = new StraightInsertionSort(15);

        straightInsertionSort.insert(49, "");
        straightInsertionSort.insert(38, "");
        straightInsertionSort.insert(65, "");
        straightInsertionSort.insert(97, "");
        straightInsertionSort.insert(76, "");
        straightInsertionSort.insert(13, "");
        straightInsertionSort.insert(27, "");
        straightInsertionSort.insert(49, "*"); // 验证排序算法是否稳定

        StraightInsertionSort.Info[] sequence = straightInsertionSort.getSequence();

        straightInsertionSort.sort(sequence);

        straightInsertionSort.printArr(sequence);
    }
}
