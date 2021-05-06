# 第993题 二叉树的堂兄弟节点

## 1 题目

在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。

如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。

我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。

只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。

示例 1：

![993-题图1](images/993-题图1.png)

输入：root = [1,2,3,4], x = 4, y = 3
输出：false
示例 2：

![993-题图2](images/993-题图2.png)


输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
输出：true
示例 3：

![993-题图3](images/993-题图3.png)

输入：root = [1,2,3,null,4], x = 2, y = 3
输出：false

## 2 解法

### 2.1 递归

先求深度，再求父结点。

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        int xDepth = findDepth(root, x, 0);
        int yDepth = findDepth(root, y, 0);

        if (xDepth != yDepth) {
            return false;
        }

        TreeNode xParent = findParent(root, x);
        TreeNode yParent = findParent(root, y);

        if (xParent == yParent) {
            return false;
        }

        return true;
    }

    private int findDepth(TreeNode root, int target, int depth) {
        if (root == null) {
            return -1;
        }

        if (root.val == target) {
            return depth;
        }

        int leftDepth = findDepth(root.left, target, depth + 1);
        int rightDepth = findDepth(root.right, target, depth + 1);

        if (leftDepth != -1) {
            return leftDepth;
        } else if (rightDepth != -1) {
            return rightDepth;
        }
        
        return -1;
    }

    private TreeNode findParent(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        if (
            root.left != null && root.left.val == target ||
            root.right != null && root.right.val == target
        ) {
            return root;
        }

        TreeNode leftParent = findParent(root.left, target);
        TreeNode rightParent = findParent(root.right, target);

        if (leftParent != null) {
            return leftParent;
        } else if (rightParent != null) {
            return rightParent;
        }

        return null;
    }
}
```

复杂度分析：

1. 时间复杂度：计算深度和父结点在最坏情况下都需要访问每个结点一次，故时间复杂度为**O(n)**；
2. 空间复杂度：递归计算深度和父结点花费的栈空间都是树的深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。

### 2.2 迭代

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;

        int xDepth = 0;
        int yDepth = 0;
        TreeNode xParent = null;
        TreeNode yParent = null;

        if (root.val == x) {
            xDepth = 0;
            xParent = null;
        } else if (root.val == y) {
            yDepth = 0;
            yParent = null;
        }

        while (!queue.isEmpty()) {
            int count = queue.size();

            depth++;

            while (count > 0) {
                TreeNode dequeueNode = queue.poll();

                if (dequeueNode.left != null) {
                    queue.offer(dequeueNode.left);
                    if (isParent(dequeueNode.left, x)) {
                        xDepth = depth;
                        xParent = dequeueNode;
                    }
                    if (isParent(dequeueNode.left, y)) {
                        yDepth = depth;
                        yParent = dequeueNode;
                    }
                }

                if (dequeueNode.right != null) {
                    queue.offer(dequeueNode.right);
                    if (isParent(dequeueNode.right, x)) {
                        xDepth = depth;
                        xParent = dequeueNode;
                    }
                    if (isParent(dequeueNode.right, y)) {
                        yDepth = depth;
                        yParent = dequeueNode;
                    }
                }

                count--;
            }
        }

        if (xDepth != yDepth) {
            return false;
        }

        if (xParent == yParent) {
            return false;
        }

        return true;
    }

    private boolean isParent(TreeNode root, int target) {
        if (root.val == target) {
            return true;
        }

        return false;
    }
}
```

复杂度分析：

1. 时间复杂度：计算深度和父结点在最坏情况下都需要访问每个结点一次，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

