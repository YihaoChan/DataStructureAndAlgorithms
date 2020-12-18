package algorithms.heapsort;

/**
 * @Description: 根据原始数组构造最大堆，再调整顺序排序，按从小到大输出
 */
public class HeapSort<T extends Comparable<? super T>> {
    /**
     * @Description: 构建最大堆
     */
    private T[] createHeap(T[] source) {
        // T不能直接初始化；T中存储的元素为Comparable的实现类对象，所以实例化的时候要用Comparable
        T[] heap = (T[]) new Comparable[source.length];

        System.arraycopy(source, 0, heap, 0, source.length);

        // 从最后一个有叶子结点的结点开始，进行堆的判断及堆的调整，并检查调整后的结果
        for (int i = (heap.length / 2 - 1); i >= 0; i--) {
            if (!isMaxHeap(heap, i, 2 * i + 2)) {
                // 如果传入的小堆不是最大堆，则需要调整
                percolateDown(heap, i, heap.length - 1);
            }

            // 检查调整之后的结果，查看新堆的子堆是否也为一个最大堆
            if (!isMaxHeap(heap, 2 * i + 1, 2 * (2 * i + 1) + 1)) {
                percolateDown(heap, 2 * i + 1, 2 * (2 * i + 1) + 1);
            } else if (!isMaxHeap(heap, 2 * i + 2, 2 * (2 * i + 2) + 2)) {
                percolateDown(heap, 2 * i + 2, 2 * (2 * i + 2) + 2);
            }
        }

        return heap;
    }

    /**
     * @param heap:  要进行判断的堆
     * @param start: 所判断堆的起始索引
     * @param end:   所判断堆的终点索引
     * @Description: 判断传入的堆是否满足最大堆
     */
    private boolean isMaxHeap(T[] heap, int start, int end) {
        // 待堆排序的结点的子结点中的非空者
        T notNullChild;

        // 如果子结点索引超过数组范围，则说明此时的堆已是最大堆，无需再进行比较
        if (end > heap.length - 1) return true;

        // 判断堆的父结点有无左右子结点时，可能索引会直接导致数组越界
        try {
            if (heap[2 * start + 1] != null && heap[2 * start + 2] != null) {
                // 有左右子结点
                return heap[start].compareTo(heap[2 * start + 1]) > 0 && heap[start].compareTo(heap[2 * start + 2]) > 0;
            } else {
                // 其中一个为空，则找到非空结点
                notNullChild = heap[2 * start + 1] != null ? heap[2 * start + 1] : heap[2 * start + 2];
                return heap[start].compareTo(notNullChild) > 0;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // 如果数组越界，说明只有左子结点没有右子结点，或者没有子结点
            if (2 * start + 1 < heap.length) {
                // 如果有左子结点，则比较大小
                return heap[start].compareTo(heap[2 * start + 1]) > 0;
            } else {
                // 如果没有子结点，说明已经满足大顶堆
                return true;
            }
        }
    }

    /**
     * @param heap:  要进行调整的堆
     * @param start: 所调整的堆的起始索引
     * @param end:   所调整的堆的终点索引
     * @Description: 对于不满足最大堆的堆，对它整个堆进行下滤，调整为最大堆
     */
    private void percolateDown(T[] heap, int start, int end) {
        // 用于交换的临时变量
        T temp;

        // 待堆排序的结点的子结点中的较大者
        T largerChild;

        // 原来的被删除结点的子结点中较大者的索引
        int largerChildIndex;

        while (2 * start + 2 <= end) {
            /* 在堆排序时进行的判断，创建堆时不需要 */
            if (2 * start + 1 > end) {
                // 堆排序时，右子结点已经被列入不排序的元素中
                if (heap[2 * start - 1].compareTo(heap[start - 1]) > 0) {
                    // 如果左子结点大于父结点，则交换
                    temp = heap[2 * start - 1];
                    heap[2 * start - 1] = heap[start - 1];
                    heap[start - 1] = temp;
                    break;
                } else {
                    // 左子结点小于父结点，直接跳出
                    break;
                }
            }

            if (heap[2 * start + 1] != null && heap[2 * start + 2] != null) {
                // 左右子结点都不为空，先判断是否有子结点大于父结点，若有则找到子结点中的较大者
                if (heap[2 * start + 1].compareTo(heap[start]) > 0 ||
                        heap[2 * start + 2].compareTo(heap[start]) > 0) {
                    largerChild = heap[2 * start + 1].compareTo(heap[2 * start + 2]) > 0 ?
                            heap[2 * start + 1] : heap[2 * start + 2];
                    largerChildIndex = heap[2 * start + 1].compareTo(heap[2 * start + 2]) > 0 ?
                            2 * start + 1 : 2 * start + 2;
                } else {
                    // 如果左右结点都比父结点小，则已经满足堆
                    break;
                }
            } else {
                // 有任意一个为空，则一定是右结点为空，因为这是完全二叉树
                if (heap[2 * start + 1].compareTo(heap[start]) > 0) {
                    // 判断左子结点是否大于父结点
                    largerChild = heap[2 * start + 1];
                    largerChildIndex = 2 * start + 1;
                } else {
                    // 如果左结点比父结点小，则已经满足堆
                    break;
                }
            }

            // 调整顺序使堆满足大顶堆
            temp = largerChild;
            heap[largerChildIndex] = heap[start];
            heap[start] = temp;

            // 参与比较的堆的起始索引步进
            start = largerChildIndex;
        }

        // 排序时，只剩下最后两个元素时，2 * start + 2必大于end，所以要单独判断
        if (end == 1) {
            if (heap[2 * start + 1].compareTo(heap[start]) > 0) {
                temp = heap[2 * start + 1];
                heap[2 * start + 1] = heap[start];
                heap[start] = temp;
            }
        }
    }

    /**
     * @Description: 堆排序 - 在构建好的最大堆中，将末尾元素与第一个元素交换位置，并在除末尾元素外的堆中，重新调整为最大堆
     */
    public T[] sort(T[] source) {
        T[] heap = createHeap(source);

        // 用于交换的临时变量
        T temp;

        // 需要参与调整为最大堆的元素中，最大元素的索引
        int maxSortIndex = heap.length - 1;

        for (int i = heap.length - 1; i >= 1; i--) {
            // 交换第一个元素和最后一个元素
            temp = heap[maxSortIndex];
            heap[maxSortIndex] = heap[0];
            heap[0] = temp;

            // 参与调整为最大堆的元素个数减1，以使已经放到堆末端的最大元素不参与调整为最大堆
            maxSortIndex--;

            // 除了最后一个元素，其他元素全部重新下滤调整成最大堆
            // 从0开始是因为，实际排序中，不会像实现最大堆类一样需要保留一个位置用做哨兵
            percolateDown(heap, 0, maxSortIndex);
        }

        return heap;
    }
}
