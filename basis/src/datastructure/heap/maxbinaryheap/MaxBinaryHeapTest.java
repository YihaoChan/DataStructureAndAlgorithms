package datastructure.heap.maxbinaryheap;

/**
 * @Description: 最大堆(大顶堆) - 测试类
 */
public class MaxBinaryHeapTest {
    public static void main(String[] args) {
        // 《浙江大学数据结构》 P145
        MaxBinaryHeap maxBinaryHeap = new MaxBinaryHeap(6);

        maxBinaryHeap.insert(18);
        maxBinaryHeap.insert(10);
        maxBinaryHeap.insert(31);
        maxBinaryHeap.insert(25);
        maxBinaryHeap.insert(44);
        maxBinaryHeap.insert(58);

        /* 删除堆顶最大元素并查看 */
        while (maxBinaryHeap.size() != 0) {
            System.out.print(maxBinaryHeap.deleteMax() + "\t");
        }
    }
}
