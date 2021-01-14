package datastructure.tree.avltree;

/**
 * @Description: AVL树 - 测试类
 */
public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();

        avlTree.insert(10);
        avlTree.insert(10);
        avlTree.insert(9);
        avlTree.insert(13);
        avlTree.insert(7);
        avlTree.insert(11);
        avlTree.insert(15);
        avlTree.insert(14);
        avlTree.insert(16);

        System.out.println("原始二叉平衡树：");
        avlTree.levelOrderTraversal();

        avlTree.remove(13);
        System.out.println("删除13后的二叉平衡树：");
        avlTree.levelOrderTraversal();

        avlTree.remove(7);
        System.out.println("删除7后的二叉平衡树：");
        avlTree.levelOrderTraversal();

        avlTree.remove(16);
        System.out.println("删除16后的二叉平衡树：");
        avlTree.levelOrderTraversal();

        avlTree.remove(15);
        System.out.println("删除15后的二叉平衡树：");
        avlTree.levelOrderTraversal();

        System.out.println("-----测试LL-----");
        AVLTree<Integer> llTree = new AVLTree<>();

        llTree.insert(14);
        llTree.insert(10);
        llTree.insert(16);
        llTree.insert(9);
        llTree.insert(12);
        llTree.insert(18);
        llTree.insert(8);
        llTree.insert(13);

        System.out.println("原始二叉平衡树：");
        llTree.levelOrderTraversal();

        llTree.remove(18);
        System.out.println("删除18后的二叉平衡树：");
        llTree.levelOrderTraversal();

        System.out.println("-----测试RR-----");
        AVLTree<Integer> rrTree = new AVLTree<>();

        rrTree.insert(13);
        rrTree.insert(10);
        rrTree.insert(16);
        rrTree.insert(9);
        rrTree.insert(15);
        rrTree.insert(18);
        rrTree.insert(14);
        rrTree.insert(19);

        System.out.println("原始二叉平衡树：");
        rrTree.levelOrderTraversal();

        rrTree.remove(9);
        System.out.println("删除9后的二叉平衡树：");
        rrTree.levelOrderTraversal();

        rrTree.remove(14);
        System.out.println("删除14后的二叉平衡树：");
        rrTree.levelOrderTraversal();

        rrTree.remove(19);
        System.out.println("删除19后的二叉平衡树：");
        rrTree.levelOrderTraversal();

        rrTree.remove(18);
        System.out.println("删除18后的二叉平衡树：");
        rrTree.levelOrderTraversal();
    }
}
