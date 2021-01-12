package datastructure.graph.depthbreadthfirstsearch;

/**
 * @Description: 深度优先搜索
 */
public class DepthFirstSearch {
    // 辅助数组，用于记录顶点是否被访问过
    private boolean[] visited;

    /**
     * @param unweightedUndigraph 需要搜索的图
     * @Description: 构造方法
     */
    public DepthFirstSearch(UnweightedUndigraph unweightedUndigraph) {
        // 初始化辅助数组
        this.visited = new boolean[unweightedUndigraph.getVertexNum()];

        for (int i = 0; i < unweightedUndigraph.getVertexNum(); i++) {
            visited[i] = false;
        }
    }

    /**
     * @Description: 深度优先搜索
     */
    public void dfs(UnweightedUndigraph unweightedUndigraph, int start) {
        // 标记该顶点已访问
        visited[start] = true;

        // 取顶点
        UnweightedUndigraph.Vertex vertex = unweightedUndigraph.getAdjacentList()[start];
        System.out.print(vertex.getVertexName() + "\t");

        // 取邻接表中的边
        UnweightedUndigraph.Edge edge = vertex.getFirstEdge();

        while (edge != null) {
            if (!visited[edge.getAdjacentListPos()]) {
                // 如果该边指向的顶点没被访问过，就直接跳转到该顶点对应的链表
                dfs(unweightedUndigraph, edge.getAdjacentListPos());
            } else {
                // 如果该边指向的顶点被访问过，就步进到下一个edge进行判断
                edge = edge.getNextEdge();
            }
        }
    }
}
