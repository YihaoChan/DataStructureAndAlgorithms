package datastructure.graph.minimumspanningtree.kruskal;

/**
 * @Description: Kruskal算法实现最小生成树
 */
public class Kruskal {
    // 最小生成树，以并查集形式存放
    private int[] parent;

    // 每棵树的规模大小
    private int[] scale;

    /**
     * @Description: 构造方法
     */
    public Kruskal(WeightedUndigraph weightedUndigraph) {
        parent = new int[weightedUndigraph.getVertexNum()];
        scale = new int[weightedUndigraph.getVertexNum()];

        for (int i = 0; i < weightedUndigraph.getVertexNum(); i++) {
            parent[i] = -1;
            scale[i] = 1;
        }
    }

    /**
     * @Description: 查看元素所在集合，直接查询到根结点
     */
    private int find(int x) {
        if (parent[x] == -1) {
            return x;
        } else {
            return find(parent[x]);
        }
    }

    /**
     * @Description: 合并两个元素所在的集合，按规模比较后简单直接合并：让第一个结点直接指向第二个结点
     */
    private void union(int firstElement, int secondElement) {
        int firstRoot = find(firstElement);
        int secondRoot = find(secondElement);

        // 如果两个顶点属于同一个集合，则不合并
        if (firstRoot == secondRoot) {
            return;
        }

        if (scale[firstRoot] > scale[secondRoot]) {
            // 如果第一个集合的父结点的规模大于第二个集合，就让第二个结点指向第一个结点(直接合并，非合并到根)
            parent[secondElement] = firstElement;
            scale[firstRoot] += scale[secondRoot];
        } else {
            // 如果第一个集合的父结点的规模小于等于第二个集合，就让第一个结点指向第二个结点(直接合并，非合并到根)
            parent[firstElement] = secondElement;
            scale[secondRoot] += scale[firstRoot];
        }
    }

    /**
     * @Description: 判断同一条边是否已经在最小堆中出现过
     * 如(2, 0)与(0, 2)在无向图中算同一条边，则不进行添加
     */
    private boolean isExistSameEdge(int start, int end, MinBinaryHeap minBinaryHeap) {
        for (int i = 1; i <= minBinaryHeap.getHeap().length; i++) {
            MinBinaryHeap.Edge edge = minBinaryHeap.getHeap()[i];

            // 如果扫描完最小堆中的有效edge都没有发现重复，则跳出返回false
            if (edge == null) {
                break;
            }

            // 如果扫描到重复edge，就返回true，否则扫描下一条边
            if (edge.getStart() == end && edge.getEnd() == start) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return 根据权重排序的 边-权重(起点，终点，权重) 的最小堆
     * @Description: 用最小堆存放图中的边(包含起点和终点)和权重
     */
    private MinBinaryHeap buildHeap(WeightedUndigraph weightedUndigraph) {
        MinBinaryHeap minBinaryHeap = new MinBinaryHeap(weightedUndigraph.getEdgeNum());

        for (int i = 0; i < weightedUndigraph.getVertexNum(); i++) {
            WeightedUndigraph.Edge edge = weightedUndigraph.getAdjacentList()[i].getFirstEdge();

            while (edge != null) {
                // 不添加重复的边：无向图中，如果(0, 2)已经在最小堆中，则不添加(2, 0)
                if (!isExistSameEdge(i, edge.getAdjacentListPos(), minBinaryHeap)) {
                    minBinaryHeap.insert(minBinaryHeap.new Edge(i, edge.getAdjacentListPos(), edge.getWeight()));
                }

                edge = edge.getNextEdge();
            }
        }

        return minBinaryHeap;
    }

    /**
     * @Description: 统计并查集中生成树的结点数
     */
    private int countVertex(int[] arr) {
        // 初始结点个数为1，因为根结点没有父结点
        int count = 1;

        for (int item : arr) {
            if (item != -1) {
                count++;
            }
        }

        return count;
    }

    /**
     * @return 最小生成树的并查集
     * @Description: 构建生成树
     */
    public int[] createMiniSpanTree(WeightedUndigraph weightedUndigraph) {
        MinBinaryHeap edgeWeightHeap = buildHeap(weightedUndigraph);

        // 如果最小生成树还没完全形成，且堆中还有边，则循环弹出边并进行合并
        while (countVertex(parent) != weightedUndigraph.getVertexNum() && edgeWeightHeap.getSize() != 0) {
            // 取权重最小的边
            MinBinaryHeap.Edge minWeightEdge = edgeWeightHeap.getMinWeightEdge();

            // 如果这条边指向的起始点和终端点不属于同一个集合，即合并它们不会使生成树构成回路，就合并
            union(minWeightEdge.getStart(), minWeightEdge.getEnd());
        }

        // 如果最小生成树的结点个数小于图的顶点个数，说明图不连通
        if (countVertex(parent) < weightedUndigraph.getVertexNum()) {
            throw new RuntimeException("图不连通，生成树不存在！");
        }

        return parent;
    }

    /**
     * @Description: 查看最小生成树的结构
     */
    public void printMiniSpanTree(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                System.out.print("V" + (i + 1) + "为根结点" + "\n");
            } else {
                System.out.print("V" + (i + 1) + "---" + "V" + (arr[i] + 1) + "\n");
            }
        }
    }
}
