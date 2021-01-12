package datastructure.graph.criticalpath;

/**
 * @Description: 关键路径 - 测试类
 */
public class CriticalPathTest {
    private static void testEgOne() {
        // 《清华数据结构》 P183
        CriticalPath weightedDigraph = new CriticalPath(9);

        weightedDigraph.insertEdge(0, 1, 6);
        weightedDigraph.insertEdge(0, 2, 4);
        weightedDigraph.insertEdge(0, 3, 5);

        weightedDigraph.insertEdge(1, 4, 1);
        weightedDigraph.insertEdge(2, 4, 1);
        weightedDigraph.insertEdge(3, 5, 2);

        weightedDigraph.insertEdge(5, 4, 0); // 增加一条权重为0的连接，加深理解最迟开始时间

        weightedDigraph.insertEdge(4, 6, 9);
        weightedDigraph.insertEdge(4, 7, 7);
        weightedDigraph.insertEdge(5, 7, 4);

        weightedDigraph.insertEdge(6, 8, 2);
        weightedDigraph.insertEdge(7, 8, 4);

        weightedDigraph.criticalPath();
    }

    private static void testEgTwo() {
        // 《清华数据结构》 P186
        CriticalPath weightedDigraph = new CriticalPath(6);

        weightedDigraph.insertEdge(0, 1, 3);
        weightedDigraph.insertEdge(0, 2, 2);

        weightedDigraph.insertEdge(1, 4, 3);
        weightedDigraph.insertEdge(1, 3, 2);

        weightedDigraph.insertEdge(2, 3, 4);
        weightedDigraph.insertEdge(2, 5, 3);

        weightedDigraph.insertEdge(3, 5, 2);

        weightedDigraph.insertEdge(4, 5, 1);

        weightedDigraph.criticalPath();
    }

    public static void main(String[] args) {
        testEgOne();

        System.out.println("--------------------------");

        testEgTwo();
    }
}
