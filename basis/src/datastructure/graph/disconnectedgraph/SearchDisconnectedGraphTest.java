package datastructure.graph.disconnectedgraph;

/**
 * @Description: 遍历不连通图 - 测试类
 */
public class SearchDisconnectedGraphTest {
    public static void main(String[] args) {
        UnweightedUndigraph unweightedUndigraph = new UnweightedUndigraph(13);

        unweightedUndigraph.insertEdge(0, 1);
        unweightedUndigraph.insertEdge(0, 2);
        unweightedUndigraph.insertEdge(0, 5);
        unweightedUndigraph.insertEdge(0, 11);

        unweightedUndigraph.insertEdge(1, 12);

        unweightedUndigraph.insertEdge(3, 4);

        unweightedUndigraph.insertEdge(6, 7);
        unweightedUndigraph.insertEdge(6, 8);
        unweightedUndigraph.insertEdge(6, 10);

        unweightedUndigraph.insertEdge(7, 10);

        unweightedUndigraph.insertEdge(9, 11);
        unweightedUndigraph.insertEdge(9, 12);

        SearchDisconnectedGraph searchDisconnectedGraph = new SearchDisconnectedGraph(unweightedUndigraph);

        searchDisconnectedGraph.listComponents(unweightedUndigraph);
    }
}
