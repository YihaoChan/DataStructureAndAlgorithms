package datastructure.graph.disconnectedgraph;

/**
 * @Description: 无权无向图 - 邻接表
 */
public class UnweightedUndigraph {
    /**
     * @Description: 头结点，包含顶点名字和指向该顶点的第一个邻接点的指针
     */
    class Vertex {
        // 顶点名字
        private Character vertexName;

        public Character getVertexName() {
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
        public Vertex(Character vertexName, Edge firstEdge) {
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

        // 指向下一个链表中的顶点的指针
        private Edge nextEdge;

        public Edge getNextEdge() {
            return this.nextEdge;
        }

        /**
         * @Description: 构造方法
         */
        public Edge(int adjacentListPos, Edge nextEdge) {
            this.adjacentListPos = adjacentListPos;
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
    public UnweightedUndigraph(int vertexNum) {
        this.vertexNum = vertexNum;
        this.edgeNum = 0;

        // 创建邻接表
        adjacentList = new Vertex[this.vertexNum];

        // 往邻接表中添加顶点名称
        for (int i = 0; i < vertexNum; i++) {
            adjacentList[i] = new Vertex((char) (65 + i), null);
        }
    }

    /**
     * @param u 添加联系的顶点的下标，0 ~ n - 1
     * @param w 添加联系的顶点的下标，0 ~ n - 1
     * @Description: 无向图中，对u顶点和w顶点之间添加一条边建立联系
     */
    public void insertEdge(int u, int w) {
        if (u < 0 || w < 0) {
            throw new RuntimeException("顶点下标需为0 ~ n - 1！");
        }

        if (u > this.vertexNum - 1 || w > this.vertexNum - 1) {
            throw new RuntimeException("顶点下标需为0 ~ n - 1！");
        }

        /* 由于是无向图，所以两端都需要建立连接 */

        if (adjacentList[u].firstEdge == null) {
            adjacentList[u].firstEdge = new Edge(w, null);
        } else {
            Edge tempU = adjacentList[u].firstEdge;

            // 插入到链表尾端
            while (tempU.nextEdge != null) {
                tempU = tempU.nextEdge;
            }

            tempU.nextEdge = new Edge(w, null);
        }

        if (adjacentList[w].firstEdge == null) {
            adjacentList[w].firstEdge = new Edge(u, null);
        } else {
            Edge tempW = adjacentList[w].firstEdge;

            // 插入到链表尾端
            while (tempW.nextEdge != null) {
                tempW = tempW.nextEdge;
            }

            tempW.nextEdge = new Edge(u, null);
        }

        this.edgeNum++;
    }
}
