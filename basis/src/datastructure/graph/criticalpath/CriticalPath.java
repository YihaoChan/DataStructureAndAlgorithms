package datastructure.graph.criticalpath;


import java.util.*;

/**
 * @Description: 带权有向图实现关键路径 - 邻接表
 */
public class CriticalPath {
    /**
     * @Description: 头结点，包含顶点名字，指向该顶点的第一个邻接点的指针，入度，和最早/最迟开始时间
     */
    private class Vertex {
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

        // 顶点的入度
        private int indegree;

        public int getIndegree() {
            return this.indegree;
        }

        // 最早/最迟开始时间
        private int time;

        public int getTime() {
            return this.time;
        }

        /**
         * @Description: 构造方法
         */
        public Vertex(String vertexName, Edge firstEdge, int indegree, int time) {
            this.vertexName = vertexName;
            this.firstEdge = firstEdge;
            this.indegree = indegree;
            this.time = time;
        }
    }

    /**
     * @Description: 表中顶点类，即边，包含所指向的顶点在邻接表中的位置，活动持续时间，和指向下一个邻接点的指针
     */
    private class Edge {
        // 所指向的顶点的在邻接表中的下标
        private int adjacentListPos;

        public int getAdjacentListPos() {
            return this.adjacentListPos;
        }

        // 活动持续时间
        private int duration;

        public int getDuration() {
            return this.duration;
        }

        // 指向下一个链表中的顶点的指针
        private Edge nextEdge;

        public Edge getNextEdge() {
            return this.nextEdge;
        }

        /**
         * @Description: 构造方法
         */
        public Edge(int adjacentListPos, int duration, Edge nextEdge) {
            this.adjacentListPos = adjacentListPos;
            this.duration = duration;
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
    public CriticalPath(int vertexNum) {
        this.vertexNum = vertexNum;
        this.edgeNum = 0;

        // 创建邻接表
        adjacentList = new Vertex[this.vertexNum];

        // 往邻接表中添加顶点名称
        for (int i = 0; i < vertexNum; i++) {
            adjacentList[i] = new Vertex("V" + (i + 1), null, 0, 0);
        }
    }

    /**
     * @param u     添加联系的顶点的下标，0 ~ n - 1
     * @param w     添加联系的顶点的下标，0 ~ n - 1
     * @param duration 活动所耗费时间
     * @Description: 有向图中让u顶点指向w顶点
     */
    public void insertEdge(int u, int w, int duration) {
        if (u < 0 || w < 0) {
            throw new RuntimeException("顶点下标需为0 ~ n - 1！");
        }

        if (u > this.vertexNum - 1 || w > this.vertexNum - 1) {
            throw new RuntimeException("顶点下标需为0 ~ n - 1！");
        }

        if (adjacentList[u].firstEdge == null) {
            adjacentList[u].firstEdge = new Edge(w, duration, null);
        } else {
            Edge tempU = adjacentList[u].firstEdge;

            // 插入到链表尾端
            while (tempU.nextEdge != null) {
                tempU = tempU.nextEdge;
            }

            tempU.nextEdge = new Edge(w, duration, null);
        }

        // w顶点的入度+1
        adjacentList[w].indegree++;

        // 边的数量+1
        this.edgeNum++;
    }

    /**
     * @return 逆拓扑有序，栈
     * @Description: 拓扑排序并输出每个顶点的最早开始时间，同时，制造逆拓扑有序序列
     */
    private Stack<Vertex> topologicalOrder() {
        // 存放入度为0的顶点，使拓扑有序
        Queue<Vertex> indegreeQueue = new LinkedList<>();

        // 存放出队的入度为0的顶点，获得逆拓扑有序序列
        Stack<Vertex> reverseOrder = new Stack<>();

        for (int i = 0; i < vertexNum; i++) {
            // 每个顶点
            Vertex vertex = adjacentList[i];

            // 入度为0的顶点，入队列
            if (vertex.indegree == 0) {
                indegreeQueue.add(vertex);
            }
        }

        int count = 0; // 统计输出顶点的个数

        while (!indegreeQueue.isEmpty()) {
            // 入度为0的顶点，出队列
            Vertex dequeueVertex = indegreeQueue.remove();

            // 出队的顶点入栈
            reverseOrder.push(dequeueVertex);

            count++;

            System.out.println(
                    " 顶点：" + dequeueVertex.vertexName + "\t最早开始时间：" + dequeueVertex.time
            );

            for (Edge edge = dequeueVertex.firstEdge; edge != null; edge = edge.nextEdge) {
                // 出队列的顶点的各个邻接点
                Vertex adjVertex = adjacentList[edge.adjacentListPos];

                // 邻接点入度-1，如果为0，也入队
                if (--adjVertex.indegree == 0) {
                    indegreeQueue.add(adjVertex);
                }

                // 如果自身时间 + 持续时间 > 指向的下一个点的时间，就更新指向的下一个点的时间
                if (dequeueVertex.time + edge.duration > adjVertex.time) {
                    adjVertex.time = dequeueVertex.time + edge.duration;
                }
            }
        }

        // 如果输出顶点的个数不等于图中顶点的个数，说明剩下的顶点入度都不为0，说明存在回路
        if (count != vertexNum) {
            throw new RuntimeException("图中有回路，无法进行拓扑排序！");
        }

        return reverseOrder;
    }

    /**
     * @Description: 根据逆拓扑有序序列计算最迟开始时间，并输出关键路径上的顶点
     */
    public void criticalPath() {
        // 逆拓扑有序序列
        Stack<Vertex> topologicalReverse = topologicalOrder();

        // 将每个顶点及其最早开始时间记录到表中
        Stack<Vertex> temp = (Stack<Vertex>) topologicalReverse.clone();

        Map<String, Integer> vertexToEarliest = new TreeMap<>();

        int count = 0;
        int size = temp.size();

        while (count != size) {
            Vertex vertex = temp.pop();

            vertexToEarliest.put(vertex.vertexName, vertex.time);

            count++;
        }

        // 初始化最迟开始时间
        int latestTime = adjacentList[vertexNum - 1].time;

        for (int i = 0; i < getVertexNum(); i++) {
            adjacentList[i].time = latestTime;
        }

        System.out.println("\n最迟发生时间和关键路径上的顶点：\n");

        while (!topologicalReverse.isEmpty()) {
            // 按逆拓扑有序序列从后往前弹出顶点
            Vertex popVertex = topologicalReverse.pop();

            for (Edge edge = popVertex.firstEdge; edge != null; edge = edge.nextEdge) {
                // 如果指向的下一个点的时间 - 持续时间 < 自身时间，就更新自身时间
                if (adjacentList[edge.adjacentListPos].time - edge.duration < popVertex.time) {
                    popVertex.time = adjacentList[edge.adjacentListPos].time - edge.duration;
                }
            }

            // 如果最早开始时间 == 最迟开始时间，说明是关键活动，标注 * 号
            String tag = popVertex.time == vertexToEarliest.get(popVertex.vertexName) ? "*" : " ";

            System.out.println(
                    tag + "顶点：" + popVertex.vertexName + "\t最迟发生时间：" + popVertex.time
            );
        }
    }
}
