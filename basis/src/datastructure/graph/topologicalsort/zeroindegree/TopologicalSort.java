package datastructure.graph.topologicalsort.zeroindegree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 零入度算法实现无权有向图拓扑排序
 */
public class TopologicalSort {
    // 存放入度为0顶点的队列
    private Queue<UnweightedDigraph.Vertex> zeroIndegreeQueue;

    /**
     * @Description: 构造方法
     */
    public TopologicalSort() {
        zeroIndegreeQueue = new LinkedList<>();
    }

    /**
     * @Description: 拓扑排序 - 零入度
     */
    public void zeroIndegreeSort(UnweightedDigraph unweightedDigraph) {
        for (int i = 0; i < unweightedDigraph.getVertexNum(); i++) {
            UnweightedDigraph.Vertex vertex = unweightedDigraph.getAdjacentList()[i];

            // 入度为0的顶点，入队
            if (vertex.getIndegree() == 0) {
                zeroIndegreeQueue.add(vertex);
            }
        }

        int count = 0; // 统计输出顶点的个数

        while (!zeroIndegreeQueue.isEmpty()) {
            // 入度为0的顶点出队列
            UnweightedDigraph.Vertex dequeueVertex = zeroIndegreeQueue.remove();

            count++;

            System.out.print(dequeueVertex.getVertexName() + "\t");

            for (UnweightedDigraph.Edge edge = dequeueVertex.getFirstEdge(); edge != null; edge = edge.getNextEdge()) {
                // 出队列的顶点的各个邻接点
                UnweightedDigraph.Vertex adjVertex = unweightedDigraph.getAdjacentList()[edge.getAdjacentListPos()];

                // 邻接点入度-1，如果入度减后恰好为0，也入队
                adjVertex.setIndegree(adjVertex.getIndegree() - 1);
                if (adjVertex.getIndegree() == 0) {
                    zeroIndegreeQueue.add(adjVertex);
                }
            }
        }

        // 如果输出顶点的个数不等于图中顶点的个数，说明还未入队的顶点入度都不为0，证明图存在回路
        if (count != unweightedDigraph.getVertexNum()) {
            throw new RuntimeException("图中有回路，无法进行拓扑排序！");
        }
    }
}
