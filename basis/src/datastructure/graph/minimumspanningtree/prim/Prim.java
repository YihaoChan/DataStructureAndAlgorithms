package datastructure.graph.minimumspanningtree.prim;

/**
 * @Description: Prim算法实现最小生成树
 */
public class Prim {
    // 每一个生成树外的顶点到生成树中顶点的距离
    private int[] dist;

    // 最小生成树，以并查集形式存放
    private int[] parent;

    // 正无穷
    private static final int INFINITY = Integer.MAX_VALUE;

    /**
     * @Description: Prim算法的构造方法
     */
    public Prim(WeightedUndigraph weightedUndigraph) {
        dist = new int[weightedUndigraph.getVertexNum()];

        parent = new int[weightedUndigraph.getVertexNum()];

        for (int i = 0; i < weightedUndigraph.getVertexNum(); i++) {
            dist[i] = INFINITY;
            parent[i] = -1;
        }
    }

    /**
     * @Description: 找到数组中自然数最小值的下标(判断非0的目的就是防止回路)
     */
    private int findMinIndex(int[] arr) {
        int minimum = INFINITY; // 最小值

        int minEleIndex = -1; // 自然数中最小值的元素的下标

        int zeroEleIndex = 0; // 值为0的元素的下标

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                // 记下0的位置
                zeroEleIndex = i;
                continue;
            }

            if (arr[i] < minimum) {
                minimum = arr[i];
                minEleIndex = i;
            }
        }

        if (minimum == INFINITY) {
            // 如果除了0以外，都是正无穷，那么最小值就为0，0所在位置就为最小值下标
            minEleIndex = zeroEleIndex;
        }

        return minEleIndex;
    }

    /**
     * @Description: 判断是否只有0和正无穷两种
     */
    private boolean isInfiniteAndZero(int[] arr) {
        for (int item : arr) {
            if (item != 0 && item != INFINITY) {
                return false;
            }
        }

        return true;
    }

    /**
     * @Description: 统计数组中0的个数，即在生成树中的结点数
     */
    private int countZero(int[] arr) {
        int count = 0;

        for (int item : arr) {
            if (item == 0) {
                count++;
            }
        }

        return count;
    }

    /**
     * @Description: 构建生成树
     * @return 最小生成树的并查集
     */
    public int[] createMiniSpanTree(WeightedUndigraph weightedUndigraph, int start) {
        // 选择一个顶点作为起点，则该顶点到生成树的距离为0
        dist[start] = 0;

        // do-while的目的是先进行第一步，保证第一步能形成一棵树
        // 否则判断数组中只有0和正无穷两种元素时会报错
        do {
            // V为未收录顶点中dist的最小者
            int V = findMinIndex(dist);

            // 将V收录进最小生成树
            dist[V] = 0;

            // 新的最小生成树中的顶点所在链表
            WeightedUndigraph.Edge W = weightedUndigraph.getAdjacentList()[V].getFirstEdge();

            // 遍历V的每个邻接点W
            while (W != null) {
                // 如果W未被收录
                if (dist[W.getAdjacentListPos()] != 0) {
                    // 比较距离，如果发现更小的就更新
                    if (W.getWeight() < dist[W.getAdjacentListPos()]) {
                        dist[W.getAdjacentListPos()] = W.getWeight();
                        parent[W.getAdjacentListPos()] = V;
                    }
                }

                W = W.getNextEdge();
            }
            /*
            isInfiniteAndZero(dist)表示数组中是否只有0和正无穷两种元素：
            case 1: 如果最开始的图不连通，那么生成树中只有选中的起始点的dist为0，其他都是正无穷，会跳出；
            case 2: 在生成树构建的过程中，有0、正无穷和其他数，不会跳出；
            case 3: 如果生成树进行到最后，只剩下0和正无穷，说明图不连通，会跳出；
            case 4: 如果生成树成功生成，则数组中只有0，会跳出。
             */
        } while (!isInfiniteAndZero(dist));

        // 最小生成树中点的个数如果小于图的顶点个数，说明图不连通
        if (countZero(dist) < weightedUndigraph.getVertexNum()) {
            throw new RuntimeException("图不连通，生成树不存在！");
        }

        return parent;
    }

    /**
     * @Description: 查看最小生成树的结构
     */
    public void printMiniSpanTree(int[] arr) {
        for (int i = 0 ; i < arr.length; i++) {
            if (arr[i] == -1) {
                System.out.print("V" + (i + 1) + "为根结点" + "\n");
            } else {
                System.out.print("V" + (i + 1) + "---" + "V" + (arr[i] + 1) + "\n");
            }
        }
    }
}
