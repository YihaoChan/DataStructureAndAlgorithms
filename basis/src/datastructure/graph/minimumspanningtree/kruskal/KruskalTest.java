package datastructure.graph.minimumspanningtree.kruskal;


public class KruskalTest {
    private static void testEgOne() {
        // 《清华数据结构》 P176
        WeightedUndigraph weightedUndigraph = new WeightedUndigraph(6);

        weightedUndigraph.insertEdge(0, 4, 6);
        weightedUndigraph.insertEdge(0, 2, 1);
        weightedUndigraph.insertEdge(0, 3, 5);

        weightedUndigraph.insertEdge(4, 2, 5);
        weightedUndigraph.insertEdge(4, 1, 3);

        weightedUndigraph.insertEdge(2, 3, 5);
        weightedUndigraph.insertEdge(2, 1, 6);
        weightedUndigraph.insertEdge(2, 5, 4);

        weightedUndigraph.insertEdge(3, 5, 2);

        weightedUndigraph.insertEdge(1, 5, 6);

        Kruskal kruskal = new Kruskal(weightedUndigraph);
        kruskal.printMiniSpanTree(kruskal.createMiniSpanTree(weightedUndigraph));
    }

    private static void testEgTwo() {
        // 《数据结构与算法分析》 P269
        WeightedUndigraph weightedUndigraph = new WeightedUndigraph(7);

        weightedUndigraph.insertEdge(0, 1, 2);
        weightedUndigraph.insertEdge(0, 2, 4);
        weightedUndigraph.insertEdge(0, 3, 1);

        weightedUndigraph.insertEdge(1, 3, 3);
        weightedUndigraph.insertEdge(1, 4, 10);

        weightedUndigraph.insertEdge(2, 3, 2);
        weightedUndigraph.insertEdge(2, 5, 5);

        weightedUndigraph.insertEdge(3, 4, 7);
        weightedUndigraph.insertEdge(3, 5, 8);
        weightedUndigraph.insertEdge(3, 6, 4);

        weightedUndigraph.insertEdge(4, 6, 6);

        weightedUndigraph.insertEdge(5, 6, 1);

        Kruskal kruskal = new Kruskal(weightedUndigraph);
        kruskal.printMiniSpanTree(kruskal.createMiniSpanTree(weightedUndigraph));
    }

    public static void main(String[] args) {
        testEgOne();

        System.out.println();

        testEgTwo();
    }
}
