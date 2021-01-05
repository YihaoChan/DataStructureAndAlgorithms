package datastructure.heap.maxbinaryheap;

/**
 * @Description: 用数组实现二叉堆中的大顶堆(最大堆)插入、删除 - 测试类
 */
public class MaxBinaryHeapTest {
    public static void main(String[] args) {
        MaxBinaryHeap<String> maxHeap = new MaxBinaryHeap<>(20);

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
