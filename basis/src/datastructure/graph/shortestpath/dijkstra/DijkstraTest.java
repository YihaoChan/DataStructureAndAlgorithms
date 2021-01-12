package datastructure.graph.shortestpath.dijkstra;

/**
 * @Description: Dijkstra算法 - 测试类
 */
public class DijkstraTest {
    public static void main(String[] args) {
        // 《数据结构与算法分析》 P255
        WeightedDigraph weightedDigraph = new WeightedDigraph(7);

        weightedDigraph.insertEdge(0, 3, 1);
        weightedDigraph.insertEdge(0, 1, 2);

        weightedDigraph.insertEdge(1, 4, 10);
        weightedDigraph.insertEdge(1, 3, 3);

        weightedDigraph.insertEdge(2, 0, 4);
        weightedDigraph.insertEdge(2, 5, 5);

        weightedDigraph.insertEdge(3, 2, 2);
        weightedDigraph.insertEdge(3, 4, 2);
        weightedDigraph.insertEdge(3, 5, 8);
        weightedDigraph.insertEdge(3, 6, 4);

        weightedDigraph.insertEdge(4, 6, 6);

        weightedDigraph.insertEdge(6, 5, 1);

        Dijkstra dijkstra = new Dijkstra(weightedDigraph);

        dijkstra.getShortestPath(weightedDigraph, 0, 5);
    }
}
