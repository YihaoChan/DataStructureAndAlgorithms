package datastructure.graph.topologicalsort.zeroindegree;

/**
 * @Description: 零入度算法实现无权有向图拓扑排序 - 测试类
 */
public class TopologicalSortTest {
    private static void testEgOne() {
        // 《清华数据结构》 P182
        TopologicalSort graph = new TopologicalSort(6);

        graph.insertEdge(0, 1);
        graph.insertEdge(0, 2);
        graph.insertEdge(0, 3);

        graph.insertEdge(2, 1);

        graph.insertEdge(5, 3);
        graph.insertEdge(5, 4);

        graph.insertEdge(3, 4);

        graph.insertEdge(2, 4);

        graph.topologicalSort();
    }

    private static void testEgTwo() {
        // 《清华数据结构》 P181
        TopologicalSort graph = new TopologicalSort(12);

        graph.insertEdge(0, 3);
        graph.insertEdge(0, 1);
        graph.insertEdge(0, 2);
        graph.insertEdge(0, 11);

        graph.insertEdge(8, 11);
        graph.insertEdge(8, 9);
        graph.insertEdge(8, 10);

        graph.insertEdge(3, 4);

        graph.insertEdge(9, 11);

        graph.insertEdge(10, 5);

        graph.insertEdge(1, 2);

        graph.insertEdge(2, 4);
        graph.insertEdge(2, 6);
        graph.insertEdge(2, 7);

        graph.insertEdge(5, 7);

        graph.insertEdge(4, 6);

        graph.topologicalSort();
    }

    private static void testEgThree() {
        // 浙江大学慕课
        TopologicalSort graph = new TopologicalSort(15);

        graph.insertEdge(0, 2);

        graph.insertEdge(1, 2);
        graph.insertEdge(1, 12);

        graph.insertEdge(7, 8);

        graph.insertEdge(3, 4);

        graph.insertEdge(2, 6);

        graph.insertEdge(8, 9);
        graph.insertEdge(8, 10);

        graph.insertEdge(4, 5);

        graph.insertEdge(6, 11);
        graph.insertEdge(6, 9);
        graph.insertEdge(6, 10);

        graph.insertEdge(5, 14);

        graph.insertEdge(9, 13);

        graph.topologicalSort();
    }

    public static void main(String[] args) {
        testEgOne();

        System.out.println();

        testEgTwo();

        System.out.println();

        testEgThree();
    }
}
