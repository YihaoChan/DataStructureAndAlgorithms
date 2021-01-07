package datastructure.disjointset.unionsimplified;

/**
 * @Description: 并查集，数组里存放的数字代表该数的父结点。合并时直接合并，但会造成树越来越高。
 */
public class UnionFind {
    // 数组每个下标代表一个元素，数组中存放的内容为每个元素的父结点
    private int[] parent;

    // 并查集中元素个数
    private int size;

    /**
     * @Description: 并查集构造方法
     */
    public UnionFind(int size) {
        this.size = size;

        parent = new int[this.size];

        for (int i = 0; i <= this.size - 1; i++) {
            parent[i] = -1; // 初始时每个元素都各自为一个集合，初始化为-1
        }
    }

    /**
     * @Description: 查看元素所在集合，直接查询到根结点
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
     * @Description: 合并两个元素所在的集合，简单直接合并：让第一个集合的父结点指向第二个集合
     */
    public void union(int firstElement, int secondElement) {
        // 找到两个元素的根结点
        int firstRoot = find(firstElement);
        int secondRoot = find(secondElement);

        if (firstRoot == secondRoot) {
            return;
        }

        // 让第一个集合的父结点变成第二个集合的根结点
        parent[firstRoot] = secondRoot;
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
