package datastructure.graph.shortestpath.floyd;

/**
 * @Description: 带权有向图 - 邻接矩阵
 */
public class WeightedDigraph {
    // 顶点数量
    private int vertexNum;

    // 边的数量
    private int edgeNum;

    // 邻接矩阵
    private int[][] adjacentMatrix;

    // 正无穷，除以2是防止加了Integer的能取到的最大值后，最高位进位变成负数
    private static final int INFINITY = Integer.MAX_VALUE / 2;

    public int getVertexNum() {
        return this.vertexNum;
    }

    public int getEdgeNum() {
        return this.edgeNum;
    }

    public int[][] getAdjacentMatrix() {
        return this.adjacentMatrix;
    }

    /**
     * @Description: 图构造方法
     */
    public WeightedDigraph(int vertexNum) {
        this.vertexNum = vertexNum;

        adjacentMatrix = new int[vertexNum][vertexNum];

        for (int i = 0; i < vertexNum; i++) {
            for (int j = 0; j < vertexNum; j++) {
                if (i == j) {
                    adjacentMatrix[i][j] = 0;
                } else {
                    adjacentMatrix[i][j] = INFINITY;
                }
            }
        }
    }

    /**
     * @param u   添加联系的顶点的下标，0 ~ n - 1
     * @param w   添加联系的顶点的下标，0 ~ n - 1
     * @param weight 边的权重
     * @Description: 有向图中让u顶点指向w顶点
     */
    public void insert(int u, int w, int weight) {
        if (u < 0 || w < 0) {
            throw new RuntimeException("顶点下标需为0 ~ n - 1！");
        }

        if (u > this.vertexNum - 1 || w > this.vertexNum - 1) {
            throw new RuntimeException("顶点下标需为0 ~ n - 1！");
        }

        adjacentMatrix[u][w] = weight;

        this.edgeNum++;
    }

    /**
     * @Description: 打印邻接矩阵
     */
    public void printAdjMatrix() {
        for (int i = 0; i < vertexNum; i++) {
            for (int j = 0; j < vertexNum; j++) {
                System.out.print(adjacentMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
