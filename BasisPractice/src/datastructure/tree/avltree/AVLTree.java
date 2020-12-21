package datastructure.tree.avltree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: AVL树
 */
public class AVLTree<T extends Comparable<T>> {
    /* 结点类 */
    private class Node {
        // 数据域
        public T item;

        // 左子结点
        public Node left;

        // 右子结点
        public Node right;

        // 树的高度
        public int height;

        /**
         * @Description: 结点类的构造方法
         */
        public Node(T item) {
            this.item = item;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    }

    // 根结点
    private Node root;

    // 结点个数
    private int size;

    /**
     * @Description: AVL树的构造方法
     */
    public AVLTree() {
        root = null;
        size = 0;
    }

    /**
     * @Description: 获取某一结点的高度
     */
    public int getHeight(Node node) {
        if (node == null) {
            return -1;
        }

        return node.height;
    }

    /**
     * @Description: 获取结点个数
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @Description: 获取结点的平衡因子
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * @Description: 判断该子树是否为一棵平衡二叉树 - 逻辑
     */
    private boolean isBalanceTree(Node node) {
        if (node == null) {
            return true;
        }

        int balanceFactor = Math.abs(getBalanceFactor(node));

        if (balanceFactor > 1) {
            return false;
        }

        // 如果当前结点满足平衡二叉树，则递归判断它的左右子树是否满足平衡二叉树
        return isBalanceTree(node.left) && isBalanceTree(node.right);
    }

    /**
     * @Description: 判断该树是否为一棵平衡二叉树
     */
    public boolean isBalanceTree() {
        return isBalanceTree(root);
    }

    /**
     * @Description: 右旋
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t3 = x.right;

        x.right = y;
        y.left = t3;

        // 更新树的高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * @Description: 左旋
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node t3 = x.left;

        x.left = y;
        y.right = t3;

        // 更新树的高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * @Description: 根据传入的结点和平衡因子进行情况分析及旋转
     */
    public Node rotate(int balanceFactor, Node node) {
        // LL情况，需要右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // RR情况，需要左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // LR情况，先对左子结点左旋，再对根结点右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL情况，先对右子结点右旋，再对根结点左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * @Description: 向以node为根的二叉搜索树中插入元素
     * @return: 插入新结点并调整为平衡二叉树后二叉搜索树的根结点
     */
    public Node insert(Node node, T item) {
        // 结束条件：当递归到空结点时，在此处创建新结点
        if (node == null) {
            size++;
            return new Node(item);
        }

        // 比较待插入结点的关键字和当前结点的关键字
        int cmp = item.compareTo(node.item);

        // 如果待插入结点的关键字小于当前结点的关键字就往左走，否则往右走
        if (cmp < 0) {
            node.left = insert(node.left, item);
        } else if (cmp > 0){
            node.right = insert(node.right, item);
        } else { // 如果待插入结点的关键字和当前结点的关键字相等，则不进行插入
            return node;
        }

        /* 已经完成初步插入结点，之后完成平衡树调整 */

        // 更新树的高度
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // 计算当前结点的平衡因子并调整
        int balanceFactor = getBalanceFactor(node);

        return rotate(balanceFactor, node);
    }

    /**
     * @Description: 插入结点
     */
    public AVLTree insert(T item) {
        root = insert(root, item);

        return this;
    }

    /**
     * @Description: 删除结点 - 逻辑
     */
    private Node remove(Node node, T item) {
        // 结束条件：如果递归到空结点，说明待删除元素不存在，则返回空
        if (node == null) {
            return null;
        }

        // 比较待删除元素和递归到的结点的元素大小
        int cmp = item.compareTo(node.item);

        if (cmp > 0) {
            node.right = remove(node.right, item);
        } else if (cmp < 0) {
            node.left = remove(node.left, item);
        } else { // 找到待删除结点
            size--;

            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node minParentNode = node.right; // 最小结点的父结点，用于将左子树置空

                Node minNode; // 最小结点

                Node leftChild = node.left; // 替换后的结点的左子结点为当前结点的左子结点

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
                    rightChild = node.right;
                } else { // 右子树的左子结点有左孩子的左孩子的...
                    while (minParentNode.left.left != null) {
                        // 循环找到最小结点的父结点
                        minParentNode = minParentNode.left;
                    }

                    // 最小结点为其父结点的左子结点
                    minNode = minParentNode.left;

                    // 替换后的结点的右子结点为待删除结点的右子结点
                    rightChild = node.right;
                }

                // 断开最小结点和其父结点的连接
                minParentNode.left = null;

                // 将最小结点替换待删除结点
                node = minNode;

                // 替换后的结点的左子结点为原来待删除结点的左子结点不变
                node.left = leftChild;

                // 替换后的结点的右子结点
                node.right = rightChild;
            }
        }

        /* 已经完成初步删除结点，之后完成平衡树调整 */

        // 更新树的高度
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // 计算当前结点的平衡因子并调整
        int balanceFactor = getBalanceFactor(node);

        return rotate(balanceFactor, node);
    }

    /**
     * @Description: 删除结点
     */
    public void remove(T item) {
        root = remove(root, item);
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
}
