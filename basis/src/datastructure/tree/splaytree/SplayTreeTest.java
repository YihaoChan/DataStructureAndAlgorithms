package datastructure.tree.splaytree;

/**
 * @Description: 伸展树 - 测试类
 */
public class SplayTreeTest {
    public static void main(String[] args) {
        SplayTree splayTree = new SplayTree();

        System.out.println("插入结点");
        splayTree.insert(10);
        splayTree.insert(8);
        splayTree.insert(12);
        splayTree.insert(12); // 测试插入相同关键字的结点
        splayTree.insert(6);
        splayTree.insert(9);
        splayTree.insert(11);
        System.out.println("插入结点完成");

        System.out.println("层序遍历");
        splayTree.levelOrderTraversal();

        System.out.println("查找结点8");
        splayTree.search(8);
        System.out.println("查找结点8完成，结点8已成为根结点");

        System.out.println("查找结点9");
        splayTree.search(9);
        System.out.println("查找结点9完成，结点9已成为根结点");

        System.out.println("再次查找结点9");
        splayTree.search(9);
        System.out.println("再次查找结点9完成，结点9仍然为根结点");

        System.out.println("查找结点10");
        splayTree.search(10);
        System.out.println("查找结点10完成，结点10已成为根结点");

        System.out.println("删除结点10");
        splayTree.remove(10);
        System.out.println("删除结点10完成");

        System.out.println("删除结点9");
        splayTree.remove(9);
        System.out.println("删除结点9完成");

        System.out.println("删除结点12");
        splayTree.remove(12);
        System.out.println("删除结点12完成");

        System.out.println("删除结点6");
        splayTree.remove(6);
        System.out.println("删除结点6完成");

        System.out.println("删除结点8");
        splayTree.remove(8);
        System.out.println("删除结点8完成");

        System.out.println("删除结点11");
        splayTree.remove(11);
        System.out.println("删除结点11完成");
    }
}
