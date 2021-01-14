package datastructure.tree.binarysearchtree;

/**
 * @Description: 二叉查找树 - 测试类
 */
public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();

        /* 添加元素 */
        binaryTree.insert(10);
        binaryTree.insert(9);
        binaryTree.insert(12);
        binaryTree.insert(7);
        binaryTree.insert(11);
        binaryTree.insert(15);
        binaryTree.insert(8);
        binaryTree.insert(14);
        binaryTree.insert(16);

        System.out.println("添加元素后的树结点个数：" + binaryTree.getSize());

        System.out.println("层序遍历查看初始二叉树");
        binaryTree.levelOrderTraversal();

        /* 删除元素 */
        binaryTree.delete(13);
        System.out.println("删除元素后的树结点个数：" + binaryTree.getSize());

        /* 最小结点 */
        System.out.println("最小结点为：" + binaryTree.findMin().getItem());

        /* 最大结点 */
        System.out.println("最大结点为：" + binaryTree.findMax().getItem());

        /* 前序遍历 */
        System.out.println("前序遍历");
        binaryTree.preOrderTraversal();

        /* 中序遍历 */
        System.out.println("中序遍历");
        binaryTree.inOrderTraversal();

        /* 后序遍历 */
        System.out.println("后序遍历");
        binaryTree.postOrderTraversal();

        /* 层序遍历 */
        System.out.println("层序遍历");
        binaryTree.levelOrderTraversal();

        /* 最大深度 */
        System.out.println("最大深度为：" + binaryTree.maxDepth());

        /* 叶子结点 */
        binaryTree.findLeaf();
    }
}
