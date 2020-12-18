package datastructure.tree.binarysearchtree;

/**
 * @Description: 用链表实现二叉查找树 - 测试类
 */
public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<String, String> binaryTree = new BinarySearchTree<>();

        /* 添加元素 */
        binaryTree
                .put("E", "5")
                .put("B", "2")
                .put("A", "1")
                .put("D", "4")
                .put("F", "6")
                .put("H", "8")
                .put("C", "3")
                .put("I", "9");

        System.out.println("添加元素后的树结点个数：" + binaryTree.size());

        /* 修改元素 */
        binaryTree.put("C", "33");
        System.out.println("修改元素后的树结点个数：" + binaryTree.size());

        /* 查看元素 */
        System.out.println("查看的元素是：" + binaryTree.get("G"));

        /* 删除元素 */
        binaryTree.delete("I");
        System.out.println("删除元素后的树结点个数：" + binaryTree.size());

        /* 最小结点 */
        System.out.println("最小结点为：" + binaryTree.findMin().getKey());

        /* 最大结点 */
        System.out.println("最大结点为：" + binaryTree.findMax().getKey());

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
