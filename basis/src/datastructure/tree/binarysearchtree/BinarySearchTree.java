package datastructure.tree.binarysearchtree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 二叉查找树
 * 左子结点的关键字一定小于父结点的关键字，右子结点的关键字一定大于父结点的关键字
 */
public class BinarySearchTree<T extends Comparable<T>> {
    /**
     * @Description: 结点类
     */
    class Node {
        // 数据域
        private T item;

        // 左子结点
        private Node left;

        // 右子结点
        private Node right;

        /* 构造方法 */
        public Node(T item) {
            this.item = item;
            this.left = null;
            this.right = null;
        }

        public T getItem() {
            return this.item;
        }
    }

    // 根结点
    private Node root;

    // 元素个数
    private int size;

    /* 构造方法 */
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return this.size;
    }


    /**
     * @Description: 添加元素 - 逻辑
     */
    private Node insert(Node curr, T item) {
        /* 递归寻找添加元素的位置 */

        // 结束条件：遍历到的结点为空，则将根据新元素创建新结点，然后返回
        if (curr == null) {
            size++;

            return new Node(item);
        }

        // 比较当前遍历到的结点的关键字和传入的关键字的大小
        int cmp = item.compareTo(curr.item);

        if (cmp > 0) {
            // 传入的关键字大于遍历到的结点的关键字，则向右继续找，并且要让当前结点的右子结点作为下一次递归的父结点
            curr.right = insert(curr.right, item);
        } else if (cmp < 0) {
            // 传入的关键字小于遍历到的结点的关键字，则向左继续找，并且要让当前结点的左子结点作为下一次递归的父结点
            curr.left = insert(curr.left, item);
        }

        return curr;
    }

    /**
     * @Description: 添加元素
     */
    public void insert(T item) {
        // 让添加元素后的根结点成为新的根结点
        root = insert(root, item);
    }

    /**
     * @Description: 删除元素 - 逻辑
     */
    private Node delete(Node curr, T item) {
        /* 递归寻找删除元素的位置 */

        // 结束条件：遍历到的结点为空，则说明没有对应的关键字，直接返回null
        if (curr == null) {
            return null;
        }

        // 比较传入的关键字和当前递归到的结点的关键字大小
        int cmp = item.compareTo(curr.item);

        if (cmp > 0) {
            // 传入的关键字大于遍历到的结点的关键字，则向右继续找，并且要让当前结点的右子结点作为下一次递归的父结点
            curr.right = delete(curr.right, item);
        } else if (cmp < 0) {
            // 传入的关键字大于遍历到的结点的关键字，则向左继续找，并且要让当前结点的左子结点作为下一次递归的父结点
            curr.left = delete(curr.left, item);
        } else {
            // 已经找到待删除结点，下一步进行结点之间的替换
            size--;

            if (curr.right == null) {
                // 如果没有右子树，则直接让左子树替换掉待删除结点
                return curr.left;
            } else if (curr.left == null) {
                // 如果没有左子树，则直接让右子树替换掉待删除结点
                return curr.right;
            } else { // 有左右子树
                Node minParentNode = curr.right; // 最小结点的父结点，用于将左子树置空

                Node minNode; // 最小结点

                Node leftChild = curr.left; // 替换后的结点的左子结点为当前结点的左子结点

                Node rightChild; // 替换后的结点的右子结点

                if (minParentNode.left == null) { // 右子树没有左子结点
                    // 则最小结点即为其父结点的初始值
                    minNode = minParentNode;

                    // 替换后的结点的右子结点需置空
                    rightChild = null;
                } else if (minParentNode.left.left == null) { // 右子树的左子结点没有左孩子
                    // 最小结点为其父结点初始值的左子结点
                    minNode = minParentNode.left;

                    // 替换后的结点的右子结点为待删除结点的右子结点
                    rightChild = curr.right;
                } else { // 右子树的左子结点有左孩子的左孩子的...
                    while (minParentNode.left.left != null) {
                        // 循环找到最小结点的父结点
                        minParentNode = minParentNode.left;
                    }

                    // 最小结点为其父结点的左子结点
                    minNode = minParentNode.left;

                    // 替换后的结点的右子结点为待删除结点的右子结点
                    rightChild = curr.right;
                }

                // 断开最小结点和其父结点的连接
                minParentNode.left = null;

                // 将最小结点替换待删除结点
                curr = minNode;

                // 替换后的结点的左子结点为原来待删除结点的左子结点不变
                curr.left = leftChild;

                // 替换后的结点的右子结点
                curr.right = rightChild;
            }
        }

        return curr;
    }

    /**
     * @Description: 删除元素
     */
    public void delete(T item) {
        // 让删除元素后的根结点成为新的根结点
        root = delete(root, item);
    }

    /**
     * @Description: 查找树中最小的结点 - 逻辑
     */
    private Node findMin(Node curr) {
        // 结束条件：递归遍历当前结点的左子树为空，则返回当前结点
        if (curr.left == null) {
            return curr;
        }

        return findMin(curr.left);
    }

    /**
     * @Description: 查找树中最小的结点
     */
    public Node findMin() {
        return findMin(root);
    }

    /**
     * @Description: 查找树中最大的结点 - 逻辑
     */
    private Node findMax(Node curr) {
        // 结束条件：递归遍历当前结点的右子树为空，则返回当前结点
        if (curr.right == null) {
            return curr;
        }

        return findMax(curr.right);
    }

    /**
     * @Description: 查找树中最大的结点
     */
    public Node findMax() {
        return findMax(root);
    }

    /**
     * @Description: 先序遍历 - 逻辑
     */
    private void preOrderTraversal(Node curr) {
        /* 递归遍历结点，按照根结点 - 左子结点 - 右子结点的顺序 */

        // 结束条件：遍历到的结点为空时，返回null
        if (curr == null) {
            return;
        }

        System.out.println(curr.item);
        preOrderTraversal(curr.left);
        preOrderTraversal(curr.right);
    }

    /**
     * @Description: 先序遍历
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    /**
     * @Description: 中序遍历 - 逻辑
     */
    private void inOrderTraversal(Node curr) {
        /* 递归遍历结点，按照左子结点 - 根结点 - 右子结点的顺序 */

        // 结束条件：遍历到的结点为空时，返回null
        if (curr == null) {
            return;
        }

        inOrderTraversal(curr.left);
        System.out.println(curr.item);
        inOrderTraversal(curr.right);
    }

    /**
     * @Description: 中序遍历
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    /**
     * @Description: 后序遍历 - 逻辑
     */
    private void postOrderTraversal(Node curr) {
        /* 递归遍历结点，按照左子结点 - 右子结点 -  根结点的顺序 */

        // 结束条件：遍历到的结点为空时，返回null
        if (curr == null) {
            return;
        }

        postOrderTraversal(curr.left);
        postOrderTraversal(curr.right);
        System.out.println(curr.item);
    }

    /**
     * @Description: 后序遍历
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    /**
     * @Description: 层序遍历 - 逻辑
     */
    private void levelOrderTraversal(Node curr) {
        if (curr == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();

        // 当前结点入队
        queue.add(curr);

        // 当前结点出队后，左右结点入队
        while (!queue.isEmpty()) {
            curr = queue.remove();

            System.out.println(curr.item);

            if (curr.left != null) {
                queue.add(curr.left);
            }

            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
    }

    /**
     * @Description: 层序遍历
     */
    public void levelOrderTraversal() {
        levelOrderTraversal(root);
    }

    /**
     * @Description: 计算树的最大深度 - 逻辑
     */
    private int maxDepth(Node curr) {
        /* 一棵树的最大深度，等于其左子树的最大深度和右子树的最大深度中的较大者，再加1 */

        // 结束条件：当递归遍历到的子结点为空时，说明该树是空树，则返回0
        if (curr == null) {
            return 0;
        }

        int maxDepthLeft = maxDepth(curr.left);
        int maxDepthRight = maxDepth(curr.right);

        // 根据树的深度的定义，需要+1
        return maxDepthLeft > maxDepthRight ? maxDepthLeft + 1 : maxDepthRight + 1;
    }

    /**
     * @Description: 计算树的最大深度
     */
    public int maxDepth() {
        return maxDepth(root);
    }

    /**
     * @Description: 找到二叉树的叶子结点 - 逻辑
     */
    private void findLeaf(Node curr) {
        // 结束条件：如果都没有左子结点和右子结点，那么它就是叶结点
        if (curr.left == null && curr.right == null) {
            System.out.println("叶子结点为：" + curr.item);
        }

        if (curr.left != null) {
            findLeaf(curr.left);
        }

        if (curr.right != null) {
            findLeaf(curr.right);
        }
    }

    /**
     * @Description: 找到二叉树的叶子结点
     */
    public void findLeaf() {
        findLeaf(root);
    }
}
