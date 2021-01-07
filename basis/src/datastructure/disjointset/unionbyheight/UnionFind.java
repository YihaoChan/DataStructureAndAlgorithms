package datastructure.disjointset.unionbyheight;

/**
 * @Description: 合并时按照高度合并
 */
public class UnionFind {
    // 数组每个下标代表一个元素，数组中存放的内容为每个元素的父结点
    private int[] parent;

    // 每棵树的高度大小
    private int[] height;

    // 并查集中元素个数
    private int size;

    /**
     * @Description: 并查集构造方法
     */
    public UnionFind(int size) {
        this.size = size;

        parent = new int[this.size];
        height = new int[this.size];

        for (int i = 0; i < this.size; i++) {
            parent[i] = -1; // 初始时每棵树都各自为一个集合
            height[i] = 1; // 初始时每棵树的高度都是0
        }
    }

    /**
     * @Description: 查看元素所在集合，直接查询到其根结点
     */
    public int find(int x) {
        if (parent[x] == -1) {
            return x;
        } else {
            return find(parent[x]);
        }
    }

    /**
     * @Description: 判断两个元素是否属于同一个集合
     */
    public boolean isConnected(int firstElement, int secondElement) {
        return find(firstElement) == find(secondElement);
    }

    /**
     * @Description: 合并两个元素所在的集合，按高度合并
     */
    public void union(int firstElement, int secondElement) {
        int firstRoot = find(firstElement);
        int secondRoot = find(secondElement);

        if (firstRoot == secondRoot) {
            return;
        }

        if (height[firstRoot] > height[secondRoot]) {
            // 如果第一个集合的高度大于第二个集合，就让第二个集合的父结点指向第一个集合
            parent[secondRoot] = firstRoot;
        } else if (height[firstRoot] < height[secondRoot]) {
            // 如果第二个集合的高度大于第一个集合，就让第一个集合的父结点指向第二个集合
            parent[firstRoot] = secondRoot;
        } else {
            // 如果两个集合高度相等，就让第一个集合的父结点指向第二个集合，新集合深度+1
            parent[firstRoot] = secondRoot;
            height[secondRoot] += 1;
        }
    }

    /**
     * @Description: 遍历查看
     */
    public void printSet() {
        for (int item : parent) {
            System.out.print(item + "\t");
        }

        System.out.println();
    }

}
