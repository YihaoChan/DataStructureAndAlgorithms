package datastructure.graph.topologicalsort.depthfirstsearch;

/**
 * @Description: 无权有向图 - 邻接表
 */
public class UnweightedDigraph {
    /**
     * @Description: 顶点
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

        // 顶点颜色
        private Color color;

        public Color getColor() {
            return this.color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        // 顶点第一次被发现的时间
        private int discoverTime;

        public int getDiscoverTime() {
            return this.discoverTime;
        }

        public void setDiscoverTime(int discoverTime) {
            this.discoverTime = discoverTime;
        }

        // 扫描完顶点的所有邻接点之后的时间
        private int finishTime;

        public int getFinishTime() {
            return this.finishTime;
        }

        public void setFinishTime(int finishTime) {
            this.finishTime = finishTime;
        }

        public Vertex(String vertexName, Edge firstEdge, Color color, int discoverTime, int finishTime) {
            this.vertexName = vertexName;
            this.firstEdge = firstEdge;
            this.color = color;
            this.discoverTime = discoverTime;
            this.finishTime = finishTime;
        }
    }

    /**
     * @Description: 边
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

        // 往邻接表中添加顶点名称
        for (int i = 0; i < vertexNum; i++) {
            // 每个顶点涂上白色
            adjacentList[i] = new Vertex("V" + (i + 1), null, Color.WHITE, 0, 0);
        }
    }

    /**
     * @Description: 有向图中，对u顶点和w顶点之间添加一条边建立联系
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

        this.edgeNum++;
    }
}
