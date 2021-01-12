package datastructure.graph.shortestpath.floyd;

/**
 * @Description: Floyd算法
 */
public class Floyd {
    // Floyd算法计算的邻接矩阵
    private int[][] floydMatrix;

    // 最短路径途中顶点
    private int[][] path;

    /**
     * @Description: 构造方法
     */
    public Floyd(WeightedDigraph weightedDigraph) {
        floydMatrix = new int[weightedDigraph.getVertexNum()][weightedDigraph.getVertexNum()];

        path = new int[weightedDigraph.getVertexNum()][weightedDigraph.getVertexNum()];

        for (int i = 0; i < weightedDigraph.getVertexNum(); i++) {
            for (int j = 0; j < weightedDigraph.getVertexNum(); j++) {
                floydMatrix[i][j] = weightedDigraph.getAdjacentMatrix()[i][j];
                path[i][j] = -1;
            }
        }
    }

    /**
     * @Description: 计算并输出每两个顶点之间的最短路径长度
     */
    public void getVerticesShortestPath(WeightedDigraph weightedDigraph) {
        // 顶点个数
        int vertexNum = weightedDigraph.getVertexNum();

        for (int k = 0; k < vertexNum; k++) {
            for (int i = 0; i < vertexNum; i++) {
                for (int j = 0; j < vertexNum; j++) {
                    if (floydMatrix[i][k] + floydMatrix[k][j] < floydMatrix[i][j]) {
                        floydMatrix[i][j] = floydMatrix[i][k] + floydMatrix[k][j];

                        // 记录途中顶点
                        path[i][j] = k;

                    }
                }
            }

        }

        // 打印矩阵
        for (int i = 0; i < vertexNum; i++) {
            for (int j = 0; j < vertexNum; j++) {
                System.out.print(floydMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * @Description: 输出两个顶点之间有最短路径时经过的顶点 - while循环
     */
    public void printVerticesWhile(int start, int end) {
        System.out.print("V" + (start + 1) + "-->");

        while (path[start][end] != -1) {
            System.out.print("V" + (path[start][end] + 1) + "-->");

            start = path[start][end];
        }

        System.out.println("V" + (end + 1));
    }

    /**
     * @Description: 输出两个顶点之间有最短路径时经过的顶点 - 递归
     * 无法打印起始顶点...
     */
    public void printVerticesRecurse(int start, int end) {
        if (path[start][end] == -1) {
            System.out.println("V" + (end + 1) + "\t(无法打印起点)");
            return;
        }

        System.out.print("V" + (path[start][end] + 1) + "-->");

        // 更新起点
        start = path[start][end];

        printVerticesRecurse(start, end);
    }
}
