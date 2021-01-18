package datastructure.graph.topologicalsort.zeroindegree;

/**
 * @Description: 零入度算法实现无权有向图拓扑排序 - 测试类
 */
public class TopologicalSortTest {
    private static void testEgOne() {
        // 《清华数据结构》 P182
        UnweightedDigraph unweightedDigraph = new UnweightedDigraph(6);

        unweightedDigraph.insertEdge(0, 1);
        unweightedDigraph.insertEdge(0, 2);
        unweightedDigraph.insertEdge(0, 3);

        unweightedDigraph.insertEdge(2, 1);
        unweightedDigraph.insertEdge(2, 4);

        unweightedDigraph.insertEdge(3, 4);

        unweightedDigraph.insertEdge(5, 3);
        unweightedDigraph.insertEdge(5, 4);

        TopologicalSort topologicalSort = new TopologicalSort();
        topologicalSort.zeroIndegreeSort(unweightedDigraph);
    }

    private static void testEgTwo() {
        // 《清华数据结构》 P181
        UnweightedDigraph unweightedDigraph = new UnweightedDigraph(12);

        unweightedDigraph.insertEdge(0, 3);
        unweightedDigraph.insertEdge(0, 1);
        unweightedDigraph.insertEdge(0, 2);
        unweightedDigraph.insertEdge(0, 11);

        unweightedDigraph.insertEdge(8, 11);
        unweightedDigraph.insertEdge(8, 9);
        unweightedDigraph.insertEdge(8, 10);

        unweightedDigraph.insertEdge(3, 4);

        unweightedDigraph.insertEdge(9, 11);

        unweightedDigraph.insertEdge(10, 5);

        unweightedDigraph.insertEdge(1, 2);

        unweightedDigraph.insertEdge(2, 4);
        unweightedDigraph.insertEdge(2, 6);
        unweightedDigraph.insertEdge(2, 7);

        unweightedDigraph.insertEdge(5, 7);

        unweightedDigraph.insertEdge(4, 6);

        TopologicalSort topologicalSort = new TopologicalSort();
        topologicalSort.zeroIndegreeSort(unweightedDigraph);
    }

    public static void main(String[] args) {
        testEgOne();

        System.out.println();

        testEgTwo();
    }
}
