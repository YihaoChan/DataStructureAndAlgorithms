package datastructure.graph.depthbreadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 广度优先搜索
 */
public class BreadthFirstSearch {
    // 辅助数组，用于记录顶点是否被访问过
    private boolean[] visited;

    /**
     * @param unweightedUndigraph 需要搜索的图
     * @Description: 构造方法
     */
    public BreadthFirstSearch(UnweightedUndigraph unweightedUndigraph) {
        // 初始化辅助数组
        this.visited = new boolean[unweightedUndigraph.getVertexNum()];

        for (int i = 0; i < unweightedUndigraph.getVertexNum(); i++) {
            visited[i] = false;
        }
    }

    /**
     * @Description: 广度优先搜索
     */
    public void bfs(UnweightedUndigraph unweightedUndigraph, int start) {
        Queue<UnweightedUndigraph.Vertex> queue = new LinkedList<>();

        // 初始情况：起始点入队并标记被访问
        UnweightedUndigraph.Vertex vertex = unweightedUndigraph.getAdjacentList()[start];
        queue.add(vertex);
        visited[start] = true;

        while (!queue.isEmpty()) {
            // 访问顶点出队
            vertex = queue.remove();
            System.out.print(vertex.getVertexName() + "\t");

            // 获取访问顶点所在链表
            UnweightedUndigraph.Edge edge = vertex.getFirstEdge();

            while (edge != null) {
                // 如果链表中的顶点没有被访问过，就入队
                if (!visited[edge.getAdjacentListPos()]) {
                    queue.add(unweightedUndigraph.getAdjacentList()[edge.getAdjacentListPos()]);

                    visited[edge.getAdjacentListPos()] = true;
                }

                edge = edge.getNextEdge();
            }
        }
    }
}
