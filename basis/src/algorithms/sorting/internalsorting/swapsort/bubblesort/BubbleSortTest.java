package algorithms.sorting.internalsorting.swapsort.bubblesort;

/**
 * @Description: 冒泡排序 - 测试类
 */
public class BubbleSortTest {
    public static void main(String[] args) {
        // 《清华数据结构》 P273
        BubbleSort bubbleSort = new BubbleSort(15);

        bubbleSort.insert(49, "");
        bubbleSort.insert(38, "");
        bubbleSort.insert(65, "");
        bubbleSort.insert(97, "");
        bubbleSort.insert(76, "");
        bubbleSort.insert(13, "");
        bubbleSort.insert(27, "");
        bubbleSort.insert(49, "*"); // 验证排序算法是否稳定

        BubbleSort.Info[] sequence = bubbleSort.getSequence();

        bubbleSort.sort(sequence);

        bubbleSort.printArr(sequence);
    }
}
