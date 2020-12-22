package datastructure.disjointset.unionbyscale;

/**
 * @Description: 合并时按照规模合并
 */
public class UnionFind {
    // 数组每个下标代表一个元素，数组中存放的内容为每个元素的父结点
    private int[] s;

    // 每棵树的规模大小
    private int[] scale;

    // 并查集中元素个数
    private int size;

    /**
     * @Description: 并查集构造方法
     */
    public UnionFind(int size) {
        this.size = size;

        s = new int[this.size];
        scale = new int[this.size];

        for (int i = 0; i < this.size; i++) {
            s[i] = -1; // 初始时每棵树都各自为一个集合
            scale[i] = 1; // 初始时每棵树的规模都是1
        }
    }

    /**
     * @Description: 查看元素属于哪个集合，直接查询到其根结点
     */
    public int find(int x) {
        if (s[x] == -1) {
            return x;
        } else {
            return find(s[x]);
        }
    }

    /**
     * @Description: 判断两个元素是否属于同一个集合
     */
    public boolean isConnected(int firstElement, int secondElement) {
        return find(firstElement) == find(secondElement);
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
            s[secondRoot] = firstRoot;
            scale[firstRoot] += scale[secondRoot];
        } else {
            // 如果第二个集合的规模大于等于第一个集合，就让第一个集合的父结点指向第二个集合
            s[firstRoot] = secondRoot;
            scale[secondRoot] += scale[firstRoot];
        }
    }

    /**
     * @Description: 遍历查看
     */
    public void printSet() {
        for (int item : s) {
            System.out.print(item + "\t");
        }

        System.out.println();
    }
}
