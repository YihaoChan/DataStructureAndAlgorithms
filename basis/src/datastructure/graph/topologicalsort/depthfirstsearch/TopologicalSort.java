package datastructure.graph.topologicalsort.depthfirstsearch;

/**
 * @Description: 深度优先搜索实现无权有向图拓扑排序
 */
public class TopologicalSort {
    /**
     * @Description: 结点类
     */
    private class Node {
        private String name;

        private Node next;

        public Node(String name, Node next) {
            this.name = name;
            this.next = next;
        }
    }

    // 存放回溯的结点的链表
    private Node head;

    // 记录顶点第一次被发现的时间和扫描完所有邻接点的时间
    private int time;

    public TopologicalSort() {
        time = 0;
        head = new Node(null, null);
    }

    /**
     * @Description: 拓扑排序 - 深度优先搜索
     */
    public void dfsSort(UnweightedDigraph unweightedDigraph) {
        UnweightedDigraph.Vertex vertex;

        // 扫描图中的每个连通分量
        for (int i = 0; i < unweightedDigraph.getVertexNum(); i++) {
            vertex = unweightedDigraph.getAdjacentList()[i];

            // 如果该顶点没被发现过，就进行深度优先搜索
            if (vertex.getColor() == Color.WHITE) {
                dfsVisit(unweightedDigraph, vertex);
            }
        }

        // 打印拓扑排序后的结果
        for (Node curr = head.next; curr != null; curr = curr.next) {
            System.out.print(curr.name + "\t");
        }
    }

    /**
     * @Description: 访问顶点的每个邻接点
     */
    private void dfsVisit(UnweightedDigraph unweightedDigraph, UnweightedDigraph.Vertex vertex) {
        vertex.setDiscoverTime(++time);

        vertex.setColor(Color.GREY); // 该顶点已被发现

        for (UnweightedDigraph.Edge edge = vertex.getFirstEdge(); edge != null; edge = edge.getNextEdge()) {
            UnweightedDigraph.Vertex adjVertex = unweightedDigraph.getAdjacentList()[edge.getAdjacentListPos()];

            if (adjVertex.getColor() == Color.WHITE) {
                dfsVisit(unweightedDigraph, adjVertex);
            } else if (adjVertex.getColor() == Color.GREY) {
                // 如果该顶点的邻接点在之前被访问过，说明存在回路
                throw new RuntimeException("存在后向边，图中有回路，无法进行拓扑排序！");
            }
        }

        vertex.setFinishTime(++time);

        vertex.setColor(Color.BLACK); // 该顶点的所有邻接点已经扫描完毕

        // 插入到链表最前端
        Node firstNode = head.next;
        head.next = new Node(vertex.getVertexName(), firstNode);
    }
}
