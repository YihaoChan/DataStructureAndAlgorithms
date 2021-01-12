package datastructure.graph.minimumspanningtree.prim;

/**
 * @Description: Prim算法 - 测试类
 */
public class PrimTest {
    private static void testEgOne() {
        // 《清华数据结构》 P174
        WeightedUndigraph weightedUndigraph = new WeightedUndigraph(6);

        weightedUndigraph.insertEdge(1, 2, 6);
        weightedUndigraph.insertEdge(1, 3, 1);
        weightedUndigraph.insertEdge(1, 4, 5);

        weightedUndigraph.insertEdge(2, 3, 5);
        weightedUndigraph.insertEdge(2, 5, 3);

        weightedUndigraph.insertEdge(3, 4, 5);
        weightedUndigraph.insertEdge(3, 5, 6);
        weightedUndigraph.insertEdge(3, 6, 4);

        weightedUndigraph.insertEdge(4, 6, 2);

        weightedUndigraph.insertEdge(5, 6, 6);

        Prim prim = new Prim(weightedUndigraph);

        prim.printMiniSpanTree(prim.createMiniSpanTree(weightedUndigraph, 0));
    }

    private static void testEgTwo() {
        // 《数据结构与算法分析》 P267
        WeightedUndigraph weightedUndigraph = new WeightedUndigraph(7);

        weightedUndigraph.insertEdge(1, 2, 2);
        weightedUndigraph.insertEdge(1, 3, 4);
        weightedUndigraph.insertEdge(1, 4, 1);

        weightedUndigraph.insertEdge(2, 4, 3);
        weightedUndigraph.insertEdge(2, 5, 10);

        weightedUndigraph.insertEdge(3, 4, 2);
        weightedUndigraph.insertEdge(3, 6, 5);

        weightedUndigraph.insertEdge(4, 5, 7);
        weightedUndigraph.insertEdge(4, 6, 8);
        weightedUndigraph.insertEdge(4, 7, 4);

        weightedUndigraph.insertEdge(5, 7, 6);

        weightedUndigraph.insertEdge(6, 7, 1);

        Prim prim = new Prim(weightedUndigraph);

        prim.printMiniSpanTree(prim.createMiniSpanTree(weightedUndigraph, 0));
    }

    public static void main(String[] args) {
        testEgOne();

        System.out.println();

        testEgTwo();
    }
}
