package datastructure.graph.topologicalsort.zeroindegree;

/**
 * @Description: 无权有向图 - 邻接表
 */
public class UnweightedDigraph {
    /**
     * @Description: 顶点，包含顶点名字，指向该顶点的第一个邻接点的指针，和顶点的入度
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

        // 入度
        private int indegree;

        public int getIndegree() {
            return this.indegree;
        }

        public void setIndegree(int indegree) {
            this.indegree = indegree;
        }

        /**
         * @Description: 构造方法
         */
        public Vertex(String vertexName, Edge firstEdge) {
            this.vertexName = vertexName;
            this.firstEdge = firstEdge;
            this.indegree = 0;
        }
    }

    /**
     * @Description: 边，包含所指向的顶点在邻接表中的位置，指向下一个邻接点的指针
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
    public UnweightedDigraph(int vertexNum) {
        this.vertexNum = vertexNum;
        this.edgeNum = 0;

        // 创建邻接表
        adjacentList = new Vertex[this.vertexNum];

        // 顶点放入邻接表中
        for (int i = 0; i < vertexNum; i++) {
            adjacentList[i] = new Vertex("V" + (i + 1), null);
        }
    }

    /**
     * @Description: 有向图中让u顶点指向w顶点
     * @param u 添加联系的顶点的下标，0 ~ n - 1
     * @param w 添加联系的顶点的下标，0 ~ n - 1
     */
    public void insertEdge(int u, int w) {
        if (u < 0 || w < 0) {
            throw new RuntimeException("顶点下标需为0 ~ n - 1！");
        }

        if (u > this.vertexNum - 1 || w > this.vertexNum - 1) {
            throw new RuntimeException("顶点下标需为0 ~ n - 1！");
        }

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

        // w顶点的入度+1
        adjacentList[w].indegree++;

        // 边的数量+1
        this.edgeNum++;
    }
}
