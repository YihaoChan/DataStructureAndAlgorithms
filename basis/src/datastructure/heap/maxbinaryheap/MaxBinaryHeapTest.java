package datastructure.heap.maxbinaryheap;

/**
 * @Description: 最大堆(大顶堆) - 测试类
 */
public class MaxBinaryHeapTest {
    private static void testEgOne() {
        // 《数据结构与算法分析》 P159
        MaxBinaryHeap<Integer> maxHeap = new MaxBinaryHeap<>(11);

        /* 插入元素 */
        maxHeap.insert(32);
        maxHeap.insert(65);
        maxHeap.insert(26);
        maxHeap.insert(19);
        maxHeap.insert(68);
        maxHeap.insert(24);
        maxHeap.insert(31);
        maxHeap.insert(16);
        maxHeap.insert(21);
        maxHeap.insert(13);
        maxHeap.insert(14);

        /* 删除顶端最大元素并查看 */
        while (maxHeap.size() != 0) {
            System.out.print(maxHeap.deleteMax() + "\t");
        }
    }

    private static void testEgTwo() {
        MaxBinaryHeap<String> maxHeap = new MaxBinaryHeap<>(11);

        /* 插入元素 */
        maxHeap.insert("G");
        maxHeap.insert("I");
        maxHeap.insert("H");
        maxHeap.insert("E");
        maxHeap.insert("O");
        maxHeap.insert("A");
        maxHeap.insert("N");
        maxHeap.insert("P");
        maxHeap.insert("S");
        maxHeap.insert("R");
        maxHeap.insert("T");

        /* 删除顶端最大元素并查看 */
        while (maxHeap.size() != 0) {
            System.out.print(maxHeap.deleteMax() + "\t");
        }
    }

    private static void testEgThree() {
        MaxBinaryHeap<String> maxHeap = new MaxBinaryHeap<>(6);

        /* 插入元素 */
        maxHeap.insert("B");
        maxHeap.insert("C");
        maxHeap.insert("A");
        maxHeap.insert("E");
        maxHeap.insert("F");
        maxHeap.insert("D");

        /* 删除顶端最大元素并查看 */
        while (maxHeap.size() != 0) {
            System.out.print(maxHeap.deleteMax() + "\t");
        }
    }
    public static void main(String[] args) {
        testEgOne();

        System.out.println();

        testEgTwo();

        System.out.println();

        testEgThree();
    }
}
