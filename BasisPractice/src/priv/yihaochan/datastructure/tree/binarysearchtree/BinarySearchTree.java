package priv.yihaochan.datastructure.tree.binarysearchtree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 用链表实现二叉查找树
 * 左子结点一定小于父结点，右子结点一定大于父结点
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    /**
     * @Description: 结点类
     */
    public class Node {
        // 键
        private Key key;

        // 值
        private Value value;

        // 左子结点
        private Node left;

        // 右子结点
        private Node right;

        /* 构造方法 */
        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Key getKey() {
            return key;
        }

        public void setKey(Key key) {
            this.key = key;
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    // 根结点
    private Node root;

    // 元素个数
    private int num;


    public int size() {
        return num;
    }


    /**
     * @Description: 添加元素 - 逻辑
     */
    private Node put(Node curr, Key key, Value value) {
        /* 递归寻找添加元素的位置 */

        // 结束条件：遍历到的结点为空，则将根据新元素创建新结点，然后返回
        if (curr == null) {
            num++;

            return new Node(key, value, null, null);
        }

        // 比较当前遍历到的结点的key和传入的key的大小
        int cmp = key.compareTo(curr.key);

        if (cmp > 0) {
            // 传入的key大于遍历到的结点的key，则向右继续找，并且要让当前结点的右子结点作为下一次递归的父结点
            // 不能返回，是因为这一层的功能是递归寻找下一个结点，目的就是直到找到null为止才返回
            curr.right = put(curr.right, key, value);
        } else if (cmp < 0) {
            // 传入的key小于遍历到的结点的key，则向左继续找，并且要让当前结点的左子结点作为下一次递归的父结点
            // 不能返回，是因为这一层的功能是递归寻找下一个结点，目的就是直到找到null为止才返回
            curr.left = put(curr.left, key, value);
        } else {
            // 如果传入的key和遍历到的结点的key相同，则将value进行覆盖
            curr.value = value;
        }

        return curr;
    }

    /**
     * @MethodName:
     * @Description: 添加元素
     */
    public BinarySearchTree put(Key key, Value value) {
        // 让添加元素后的根结点成为新的根结点
        root = put(root, key, value);

        return this;
    }

    /**
     * @Description: 查看传入key所对应的value - 逻辑
     */
    private Value get(Node curr, Key key) {
        /* 递归寻找查看元素的位置 */

        // 结束条件：遍历到的结点为空，则说明没有对应的key，直接返回null
        if (curr == null) {
            return null;
        }

        // 比较当前遍历到的结点的key和传入的key的大小
        int cmp = key.compareTo(curr.key);

        if (cmp > 0) {
            // 传入的key大于遍历到的结点的key，则说明key在右边，则返回右子树的value
            // 可以返回，是因为这一层的功能是找到下一个结点的value
            return get(curr.right, key);
        } else if (cmp < 0) {
            // 传入的key小于遍历到的结点的key，则说明key在左边，则返回左子树的value
            // 可以返回，是因为这一层的功能是找到下一个结点的value
            return get(curr.left, key);
        } else {
            // 如果传入的key和遍历到的结点的key相同，则将value返回
            return curr.value;
        }
    }

    /**
     * @Description: 查看传入key所对应的value
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * @Description: 删除元素 - 逻辑
     */
    private Node delete(Node curr, Key key) {
        /* 递归寻找删除元素的位置 */

        // 结束条件：遍历到的结点为空，则说明没有对应的key，直接返回null
        if (curr == null) {
            return null;
        }

        // 比较
        int cmp = key.compareTo(curr.key);

        if (cmp > 0) {
            // 传入的key大于遍历到的结点的key，则向右继续找，并且要让当前结点的右子结点作为下一次递归的父结点
            // 不能马上return该结点，因为找到该结点后还要进行边之间的操作、连接
            curr.right = delete(curr.right, key);
        } else if (cmp < 0) {
            // 传入的key大于遍历到的结点的key，则向左继续找，并且要让当前结点的左子结点作为下一次递归的父结点
            // 不能马上return该结点，因为找到该结点后还要进行边之间的操作、连接
            curr.left = delete(curr.left, key);
        } else {
            // 已经找到待删除结点，下一步进行结点之间的替换
            num--;

            if (curr.right == null) {
                // 如果没有右子树，则直接让左子树替换掉待删除结点
                return curr.left;
            } else {
                // 如果有右子树，则从该右子树开始，一直寻找左子树结点，直到循环到空时返回该结点
                // 因为已经找到待删除结点，所以不需要再递归delete，只需要遍历找到最小结点即可
                Node findMinNode = curr.right;

                // 当findMinNode的左子树结点的左子树结点为空时，说明findMinNode的左子树结点为最小结点
                while (findMinNode.left.left != null) {
                    findMinNode = findMinNode.left;
                }

                Node minNode = findMinNode.left;

                // 倒数第二个结点的左子树结点指向空，断开连接
                findMinNode.left = null;

                // 这层循环的功能是：在已经找到待删除结点的基础上，找一个能替换该待删除结点的最小结点，所以直接返回它即可
                curr = minNode;
            }
        }

        return curr;
    }

    /**
     * @Description: 删除元素
     */
    public void delete(Key key) {
        // 让删除元素后的根结点成为新的根结点
        root = delete(root, key);
    }

    /**
     * @Description: 查找树中最小的键 - 逻辑
     */
    private Node findMin(Node curr) {
        // 结束条件：递归遍历当前结点的左子树为空，则返回当前结点
        if (curr.left == null) {
            return curr;
        }

        return findMin(curr.left);
    }

    /**
     * @Description: 查找树中最小的键
     */
    public Node findMin() {
        return findMin(root);
    }

    /**
     * @Description: 查找树中最大的键 - 逻辑
     */
    private Node findMax(Node curr) {
        // 结束条件：递归遍历当前结点的右子树为空，则返回当前结点
        if (curr.right == null) {
            return curr;
        }

        return findMax(curr.right);
    }

    /**
     * @Description: 查找树中最大的键
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

        System.out.println(curr.key + "=" + curr.value);
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
        System.out.println(curr.key + "=" + curr.value);
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
        System.out.println(curr.key + "=" + curr.value);
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

            System.out.println(curr.key + "=" + curr.value);

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
            System.out.println("叶子结点为：" + curr.key);
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
