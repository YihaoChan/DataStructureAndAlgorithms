package datastructure.graph.shortestpath.dijkstra;

/**
 * @Description: Dijkstra算法
 */
public class Dijkstra {
    // dist[V]：起点到顶点V的最短路径长度
    private int[] dist;

    // path[W]：W路径上的最后一个点
    private int[] path;

    // 是否已将该顶点收录进最短路径
    private boolean[] collected;

    // 正无穷
    private static final int INFINITY = Integer.MAX_VALUE;

    /**
     * @Description: Dijkstra算法的构造方法
     */
    public Dijkstra(WeightedDigraph weightedDigraph) {
        this.dist = new int[weightedDigraph.getVertexNum()];

        this.path = new int[weightedDigraph.getVertexNum()];

        this.collected = new boolean[weightedDigraph.getVertexNum()];

        for (int i = 0; i < weightedDigraph.getVertexNum(); i++) {
            this.dist[i] = INFINITY;
            this.path[i] = -1;
            this.collected[i] = false;
        }
    }

    /**
     * @Description: 打印最短路径(并查集思路)
     */
    private int printShortest(int x) {
        if (path[x] == -1) {
            System.out.println("V" + (x + 1));
            return x;
        } else {
            System.out.print("V" + (x + 1) + "<---");
            return printShortest(path[x]);
        }
    }

    /**
     * @param start 起点的下标，0 ~ n - 1
     * @param end   终点的下标，0 ~ n - 1
     * @Description: 计算并输出最短路径
     */
    public void getShortestPath(WeightedDigraph weightedDigraph, int start, int end) {
        // 选择一个顶点作为起点，则起点到该顶点的最短路径为0
        dist[start] = 0;

        while (true) {
            // V为未收录顶点中dist的最小者
            int min = INFINITY;
            int V = 0;

            for (int i = 0; i < dist.length; i++) {
                if (collected[i]) continue;

                if (dist[i] < min) {
                    min = dist[i];
                    V = i;
                }
            }

            // 如果终点被收录过，就说明已经找到最短路径
            if (collected[end]) {
                break;
            }

            // 将V收录
            collected[V] = true;

            for (
                    WeightedDigraph.Edge edge = weightedDigraph.getAdjacentList()[V].getFirstEdge();
                    edge != null;
                    edge = edge.getNextEdge()
            ) {
                // V的每个邻接点W
                int W = edge.getAdjacentListPos();

                // 如果W没被收录，就计算它的最短路径，并记录途中顶点
                if (!collected[W]) {
                    if (dist[V] + edge.getWeight() < dist[W]) {
                        dist[W] = dist[V] + edge.getWeight();
                        path[W] = V;
                    }
                }
            }
        }

        // 输出最短路径
        printShortest(end);
    }
}
