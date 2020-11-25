package priv.yihaochan.algorithms.heapsort;

import java.util.ArrayList;

/**
 * @Description: 堆排序 - 测试类
 */
public class HeapSortTest {
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();

        ArrayList<Comparable[]> list = new ArrayList<>();

        list.add(new Integer[]{79, 66, 43, 83, 30, 87, 38, 55, 91, 72, 49, 9});
        list.add(new String[]{"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E", "J"});

        for (Comparable[] source : list) { // 取出每一个可排序的数组
            for (Object item : heapSort.sort(source)) { // 遍历已排好序的数组元素
                System.out.print(item + "\t");
            }

            System.out.println();
        }
    }
}
