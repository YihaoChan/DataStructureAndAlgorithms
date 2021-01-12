package datastructure.graph.shortestpath.floyd;

/**
 * @Description: Floyd - 测试类
 */
public class FloydTest {
    private static void testEgOne() {
        WeightedDigraph weightedDigraph = new WeightedDigraph(4);

        weightedDigraph.insert(0, 3, 4);
        weightedDigraph.insert(0, 1, 2);
        weightedDigraph.insert(0, 2, 6);

        weightedDigraph.insert(1, 2, 3);

        weightedDigraph.insert(2, 0, 7);
        weightedDigraph.insert(2, 3, 1);

        weightedDigraph.insert(3, 0, 5);
        weightedDigraph.insert(3, 2, 12);

        System.out.println("原始邻接矩阵");
        weightedDigraph.printAdjMatrix();
        System.out.println();

        Floyd floyd = new Floyd(weightedDigraph);

        System.out.println("每两个顶点之间的最短路径长度");
        floyd.getVerticesShortestPath(weightedDigraph);
        System.out.println();

        System.out.println("查看两个顶点之间最短路径");
        floyd.printVerticesWhile(2, 1);
        floyd.printVerticesRecurse(2, 1);
    }

    private static void testEgTwo() {
        WeightedDigraph weightedDigraph = new WeightedDigraph(5);

        weightedDigraph.insert(0, 1, 1);
        weightedDigraph.insert(0, 4, 1);

        weightedDigraph.insert(2, 1, 10);
        weightedDigraph.insert(2, 3, 1);
        weightedDigraph.insert(2, 4, 10);

        weightedDigraph.insert(3, 0, 1);
        weightedDigraph.insert(3, 4, 10);

        weightedDigraph.insert(4, 1, 1);

        System.out.println("原始邻接矩阵(该图不是强连通图)");
        weightedDigraph.printAdjMatrix();
        System.out.println();

        Floyd floyd = new Floyd(weightedDigraph);

        System.out.println("每两个顶点之间的最短路径长度");
        floyd.getVerticesShortestPath(weightedDigraph);
        System.out.println();

        System.out.println("查看两个顶点之间最短路径");
        floyd.printVerticesWhile(2, 4);
        floyd.printVerticesRecurse(2, 4);
    }

    public static void main(String[] args) {
        testEgOne();

        System.out.println("\n-------------------------------------\n");

        testEgTwo();
    }
}
