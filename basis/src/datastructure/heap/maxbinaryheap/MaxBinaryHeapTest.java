package datastructure.heap.maxbinaryheap;

/**
 * @Description: 最大堆(大顶堆) - 测试类
 */
public class MaxBinaryHeapTest {
    public static void main(String[] args) {
        // 《数据结构与算法分析》 P159
        MaxBinaryHeap maxHeap = new MaxBinaryHeap(11);

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
}
