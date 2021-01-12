package datastructure.heap.maxbinaryheap;

/**
 * @Description: 最大堆(大顶堆) - 测试类
 */
public class MaxBinaryHeapTest {
    public static void main(String[] args) {
        MaxBinaryHeap<String> maxHeap = new MaxBinaryHeap<>(7);

        /* 插入元素 */
        maxHeap.insert("A");
        maxHeap.insert("B");
        maxHeap.insert("C");
        maxHeap.insert("D");
        maxHeap.insert("E");
        maxHeap.insert("F");
        maxHeap.insert("G");

        /* 删除顶端最大元素并查看 */
        while (maxHeap.size() != 0) {
            System.out.print(maxHeap.deleteMax() + "\t");
        }
    }
}
