package datastructure.graph.depthbreadthfirstsearch;

/**
 * @Description: 深度、广度优先搜索 - 测试类
 */
public class DepthBreadthFirstSearchTest {
    public static void main(String[] args) {
        // 《清华数据结构》 P168
        UnweightedUndigraph unweightedUndigraph = new UnweightedUndigraph(8);

        unweightedUndigraph.insertEdge(0, 1);
        unweightedUndigraph.insertEdge(0, 2);

        unweightedUndigraph.insertEdge(1, 3);
        unweightedUndigraph.insertEdge(1, 4);

        unweightedUndigraph.insertEdge(2, 5);
        unweightedUndigraph.insertEdge(2, 6);

        unweightedUndigraph.insertEdge(3, 7);

        unweightedUndigraph.insertEdge(4, 7);

        unweightedUndigraph.insertEdge(5, 6);

        System.out.println("深度优先搜索");
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(unweightedUndigraph);
        depthFirstSearch.dfs(unweightedUndigraph, 0);

        System.out.println("\n广度优先搜索");
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(unweightedUndigraph);
        breadthFirstSearch.bfs(unweightedUndigraph, 0);
    }
}
