package datastructure.tree.splaytree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 伸展树
 */
public class SplayTree<T extends Comparable<T>> {
    /**
     * @Description: 结点类
     */
    private class Node {
        // 数据域
        public T item;

        // 左子结点
        public Node left;

        // 右子结点
        public Node right;

        // 父结点
        public Node parent;

        /**
         * @Description: 结点类构造方法
         */
        public Node(T item, Node left, Node right, Node parent) {
            this.item = item;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    // 根结点
    private Node root;

    // 结点个数
    private int size;

    /**
     * @Description: 伸展树的构造方法
     */
    public SplayTree() {
        root = null;
        size = 0;
    }

    /**
     * @Description: 获取结点个数
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @Description: 左旋
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node t3 = x.left;

        // 旋转
        x.left = y;
        y.right = t3;

        Node tempParent = y.parent; // 原来的根结点的父结点

        // 更新父结点指针
        y.parent = x;
        x.parent = tempParent;
        if (t3 != null) {
            t3.parent = y;
        }

        return x;
    }

    /**
     * @Description: 右旋
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t3 = x.right;

        // 旋转
        x.right = y;
        y.left = t3;

        Node tempParent = y.parent; // 原来的根结点的父结点

        // 更新父结点指针
        y.parent = x;
        x.parent = tempParent;
        if (t3 != null) {
            t3.parent = y;
        }

        return x;
    }

    /**
     * @Description: 伸展：循环旋转处理，分四种情况进行旋转
     */
    private Node splay(Node node) {
        while (true) {
            /* 当查找时，如果连续多次查找同一个结点，因为该结点已处于根结点位置，故直接返回该结点 */
            if (node.parent == null) {
                return node;
            }

            /* 伸展到最后一步，即触碰到了根结点时的情况，需要进行单旋转 */
            if (node.parent.parent == null) {
                if (node.parent.right == node) { // 如果根结点的右孩子为当前结点，即R型，需要左旋
                    return leftRotate(node.parent);
                } else if (node.parent.left == node) { // 如果根结点的左孩子为当前结点，即L型，需要右旋
                    return rightRotate(node.parent);
                }
            }

            /* 其余情况为双旋转 */
            boolean rightFlag = false; // 旋转后的新根结点的父结点的左右指针方向，取决于原来的曾祖父结点和祖父结点之间的关系

            assert node.parent.parent != null; // 还没有碰到根结点，所以断言祖父结点非空

            if (node.parent.parent.parent != null) {
                if (node.parent.parent.parent.right == node.parent.parent) {
                    // 如果原来的曾祖父结点的右孩子为祖父结点，则旋转后的新结点为新结点的父结点的右孩子
                    rightFlag = true;
                } else if (node.parent.parent.parent.left == node.parent.parent) {
                    // 如果原来的曾祖父结点的左孩子为祖父结点，则旋转后的新结点为新结点的父结点的左孩子
                    rightFlag = false;
                }
            }

            /* 分四种情况：LL型、RR型、RL型、LR型 */
            Node tempRoot; // 第一次旋转后得到的临时的根

            if (node.parent.parent.left == node.parent && node.parent.left == node) { // LL型，需要双右旋
                tempRoot = rightRotate(node.parent.parent); // 先对原来的祖父结点和原来的父结点进行右旋

                node = rightRotate(tempRoot); // 再对原来的父结点和原来的子结点进行右旋
            } else if (node.parent.parent.right == node.parent && node.parent.right == node) { // RR型，需要双左旋
                tempRoot = leftRotate(node.parent.parent); // 先对原来的祖父结点和原来的父结点进行左旋

                node = leftRotate(tempRoot); // 再对原来的父结点和原来的子结点进行左旋
            } else if (node.parent.parent.right == node.parent && node.parent.left == node) { // RL型，需要先右旋后左旋
                tempRoot = rightRotate(node.parent); // 先对原来的父结点和原来的子结点进行右旋

                tempRoot.parent.right = tempRoot; // 修改原来的祖父结点的right指针

                node = leftRotate(tempRoot.parent); // 再对原来的子结点和原来的祖父结点进行左旋
            } else if (node.parent.parent.left == node.parent && node.parent.right == node) { // LR型，需要先左旋后右旋
                tempRoot = leftRotate(node.parent); // 先对原来的父结点和原来的子结点进行左旋

                tempRoot.parent.left = tempRoot; // 修改原来的祖父结点的left指针

                node = rightRotate(tempRoot.parent); // 再对原来的子结点和原来的祖父结点进行右旋
            }

            if (node.parent == null) { // 刚好旋转成为根结点，直接跳出
                break;
            } else { // 修改原来的曾祖父结点和旋转后的新结点的左/右孩子指针
                if (rightFlag) {
                    node.parent.right = node;
                } else {
                    node.parent.left = node;
                }
            }
        }

        return node;
    }

    /**
     * @Description: 插入结点 - 逻辑
     */
    private Node insert(Node node, Node parent, T item) {
        if (node == null) {
            size++;

            Node newRoot = new Node(item, null, null, parent);

            /* 待插入结点为整棵树的根结点的情况，单独处理，直接返回这个结点 */
            if (parent == null) {
                return newRoot;
            }

            // 连接parent指针
            int cmpLinkParent = item.compareTo(parent.item);

            if (cmpLinkParent < 0) {
                newRoot.parent.left = newRoot;
            } else if (cmpLinkParent > 0) {
                newRoot.parent.right = newRoot;
            }

            // 自底向上伸展结点至根结点处
            return splay(newRoot);
        }

        /* 如果待插入结点比递归遍历到的结点的关键字小，就往左走，否则往右走 */
        int cmp = item.compareTo(node.item);

        if (cmp < 0) {
            return insert(node.left, node, item);
        } else if (cmp > 0) {
            return insert(node.right, node, item);
        } else {
            return node; // 不插入相同关键字的结点
        }
    }

    /**
     * @Description: 插入结点
     */
    public void insert(T item) {
        root = insert(root, root, item);
    }

    /**
     * @Description: 查找结点 - 逻辑
     */
    private Node search(Node node, T item) {
        // 结束条件：如果遍历到空结点，说明待查找结点不存在，返回空值
        if (node == null) {
            return null;
        }

        /* 如果待插入结点比递归遍历到的结点的关键字小，就往左走，否则往右走 */
        int cmp = item.compareTo(node.item);

        if (cmp < 0) {
            return search(node.left, item);
        } else if (cmp > 0) {
            return search(node.right, item);
        } else {
            return splay(node); // 找到待查找结点，将其伸展到根结点位置处
        }
    }

    /**
     * @Description: 查找结点
     */
    public void search(T item) {
        root = search(root, item);
    }

    /**
     * @Description: 查找最大结点并伸展到根结点处
     */
    private Node findMaxSplay(Node node) {
        // 结束条件：递归遍历当前结点的右子树为空，则找到最大结点，将其伸展到根结点处
        if (node.right == null) {
            return splay(node);
        }

        return findMaxSplay(node.right);
    }

    /**
     * @Description: 删除结点 - 逻辑
     */
    private Node remove(Node node, T item) {
        // 结束条件：如果遍历到空结点，说明待删除结点不存在，返回空值
        if (node == null) {
            return null;
        }

        /* 如果待插入结点比递归遍历到的结点的关键字小，就往左走，否则往右走 */
        int cmp = item.compareTo(node.item);

        if (cmp < 0) {
            return remove(node.left, item);
        } else if (cmp > 0) {
            return remove(node.right, item);
        } else {
            size--;
            node = splay(node); // 将待删除结点推向根结点处
        }

        Node newTree; // 新树

        // 只留下根结点的左子树和右子树，即删除根结点(待删除结点)
        Node leftTree = node.left;
        Node rightTree = node.right;

        if (leftTree != null) { // 左子树不为空
            leftTree.parent = null; // 断开左子树和待删除结点的联系

            // 将左子树的最大结点推向根成为新左子树，此时新左子树没有右孩子，因为原来的左子树的最大结点没有右孩子
            newTree = findMaxSplay(leftTree);

            // 将原来的右子树拼接过来
            newTree.right = rightTree;

            // 右子树拼接后的父结点为新树
            if (rightTree != null) {
                rightTree.parent = newTree;
            }
        } else { // 左子树为空
            // 右子树成为根结点，则它的父结点为空
            if (rightTree != null) {
                rightTree.parent = null;
            }

            newTree = rightTree; // 右子树直接成为完整的树
        }

        return newTree;
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
