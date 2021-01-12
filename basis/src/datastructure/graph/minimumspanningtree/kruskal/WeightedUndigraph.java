package datastructure.graph.minimumspanningtree.kruskal;

/**
 * @Description: 带权无向图 - 邻接表
 */
public class WeightedUndigraph {
    /**
     * @Description: 头结点，包含顶点名字和指向该顶点的第一个邻接点的指针
     */
    class Vertex {
        // 顶点名字
        private String vertexName;

        public String getVertexName() {
            return this.vertexName;
        }

        // 指向该顶点的第一个邻接点的指针
        private Edge firstEdge;

        public Edge getFirstEdge() {
            return this.firstEdge;
        }

        /**
         * @Description: 构造方法
         */
        public Vertex(String vertexName, Edge firstEdge) {
            this.vertexName = vertexName;
            this.firstEdge = firstEdge;
        }
    }

    /**
     * @Description: 表中顶点类，即边，包含所指向的顶点在邻接表中的位置，指向下一个邻接点的指针
     */
    class Edge {
        // 所指向的顶点的在邻接表中的下标
        private int adjacentListPos;

        public int getAdjacentListPos() {
            return this.adjacentListPos;
        }

        // 边的权重
        private int weight;

        public int getWeight() {
            return this.weight;
        }

        // 指向下一个链表中的顶点的指针
        private Edge nextEdge;

        public Edge getNextEdge() {
            return this.nextEdge;
        }

        /**
         * @Description: 构造方法
         */
        public Edge(int adjacentListPos, int weight, Edge nextEdge) {
            this.adjacentListPos = adjacentListPos;
            this.weight = weight;
            this.nextEdge = nextEdge;
        }
    }

    /**
     * @Description: 图中顶点的数量
     */
    private int vertexNum;

    public int getVertexNum() {
        return this.vertexNum;
    }

    /**
     * @Description: 图中边的数量
     */
    private int edgeNum;

    public int getEdgeNum() {
        return this.edgeNum;
    }

    /**
     * @Description: 邻接表
     */
    private Vertex[] adjacentList;

    public Vertex[] getAdjacentList() {
        return this.adjacentList;
    }

    /**
     * @Description: 图的构造方法
     */
    public WeightedUndigraph(int vertexNum) {
        this.vertexNum = vertexNum;
        this.edgeNum = 0;

        // 创建邻接表
        adjacentList = new Vertex[this.vertexNum];

        // 往邻接表中添加顶点名称
        for (int i = 0; i < vertexNum; i++) {
            adjacentList[i] = new Vertex("V" + (i + 1), null);
        }
    }

    /**
     * @Description: 对u顶点和w顶点之间添加一条边建立联系，并按权重从小到大排序插入
     * @param u 添加联系的顶点的下标，0 ~ n - 1
     * @param w 添加联系的顶点的下标，0 ~ n - 1
     * @param weight 边的权重
     */
    public void insertEdge(int u, int w, int weight) {
        if (u < 0 || w < 0) {
            throw new RuntimeException("顶点下标需为0 ~ n - 1！");
        }

        if (u > this.vertexNum - 1 || w > this.vertexNum - 1) {
            throw new RuntimeException("顶点下标需为0 ~ n - 1！");
        }

        /* 由于是无向图，所以两端都需要建立连接 */

        if (adjacentList[u].firstEdge == null) {
            adjacentList[u].firstEdge = new Edge(w, weight, null);
        } else if (weight < adjacentList[u].firstEdge.weight) {
            Edge prevFirstEdge = adjacentList[u].firstEdge;
            adjacentList[u].firstEdge = new Edge(w, weight, prevFirstEdge);
        } else {
            Edge tempU = adjacentList[u].firstEdge;

            while (tempU.nextEdge != null && weight > tempU.weight) {
                if (weight < tempU.nextEdge.weight) {
                    break;
                } else {
                    tempU = tempU.nextEdge;
                }
            }

            Edge nextEdge = tempU.nextEdge;
            tempU.nextEdge = new Edge(w, weight, nextEdge);
        }

        if (adjacentList[w].firstEdge == null) {
            adjacentList[w].firstEdge = new Edge(u, weight, null);
        } else if (weight < adjacentList[w].firstEdge.weight) {
            Edge prevFirstEdge = adjacentList[w].firstEdge;
            adjacentList[w].firstEdge = new Edge(u, weight, prevFirstEdge);
        } else {
            Edge tempW = adjacentList[w].firstEdge;

            while (tempW.nextEdge != null && weight > tempW.weight) {
                if (weight < tempW.nextEdge.weight) {
                    break;
                } else {
                    tempW = tempW.nextEdge;
                }
            }

            Edge nextEdge = tempW.nextEdge;
            tempW.nextEdge = new Edge(u, weight, nextEdge);
        }

        this.edgeNum++;
    }
}
