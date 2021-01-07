package datastructure.disjointset.unionbyscalefindbycompression;

/**
 * @Description: 合并时按照规模合并，并在查找时使用路径压缩
 */
public class UnionFind {
    // 数组每个下标代表一个元素，数组中存放的内容为每个元素的父结点
    private int[] parent;

    // 每棵树的规模大小
    private int[] scale;

    // 并查集中元素个数
    private int size;

    /**
     * @Description: 并查集构造方法
     */
    public UnionFind(int size) {
        this.size = size;

        parent = new int[this.size];
        scale = new int[this.size];

        for (int i = 0; i < this.size; i++) {
            parent[i] = -1; // 初始时每棵树都各自为根
            scale[i] = 1; // 初始时每棵树的规模都是1
        }
    }

    /**
     * @Description: 查看元素所在集合 - 路径压缩
     */
    public int find(int x) {
        if (parent[x] < 0) {
            return x;
        } else {
            parent[x] = find(parent[x]); // 将当前结点的父结点设置为它的父结点的父结点
            return parent[x]; // 返回父结点
        }
    }

    /**
     * @Description: 合并两个元素所在的集合，按规模合并
     */
    public void union(int firstElement, int secondElement) {
        int firstRoot = find(firstElement);
        int secondRoot = find(secondElement);

        if (firstRoot == secondRoot) {
            return;
        }

        if (scale[firstRoot] > scale[secondRoot]) {
            // 如果第一个集合的规模大于第二个集合，就让第二个集合的父结点指向第一个集合
            parent[secondRoot] = firstRoot;
            scale[firstRoot] += scale[secondRoot];
        } else {
            // 如果第二个集合的规模大于等于第一个集合，就让第一个集合的父结点指向第二个集合
            parent[firstRoot] = secondRoot;
            scale[secondRoot] += scale[firstRoot];
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
