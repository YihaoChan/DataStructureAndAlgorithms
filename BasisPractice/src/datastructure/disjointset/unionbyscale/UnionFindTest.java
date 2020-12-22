package datastructure.disjointset.unionbyscale;

/**
 * @Description: 按照规模合并 - 测试类
 */
public class UnionFindTest {
    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(9);

        System.out.println("初始状态：");
        unionFind.printSet();

        System.out.println("连接0、2");
        unionFind.union(0, 2);
        unionFind.printSet();

        System.out.println("连接1、2");
        unionFind.union(1, 2);
        unionFind.printSet();

        System.out.println("连接3、2");
        unionFind.union(3, 2);
        unionFind.printSet();

        System.out.println("连接4、2");
        unionFind.union(4, 2);
        unionFind.printSet();

        System.out.println("连接8、5");
        unionFind.union(8, 5);
        unionFind.printSet();

        System.out.println("连接7、6");
        unionFind.union(7, 6);
        unionFind.printSet();

        System.out.println("连接5、6");
        unionFind.union(5, 6);
        unionFind.printSet();

        System.out.println("连接2、6");
        unionFind.union(2, 6);
        unionFind.printSet();
    }
}
