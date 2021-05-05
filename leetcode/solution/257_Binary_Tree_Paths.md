# 第257题 二叉树的所有路径

## 1 题目

给定一个二叉树，返回所有从根节点到叶子节点的路径。

说明: 叶子节点是指没有子节点的节点。

示例:

输入:

```
   1
 /   \
2     3
 \
  5
```

输出: ["1->2->5", "1->3"]

解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3

## 2 解法

### 2.1 递归

注意每次递归时要重新new一个新的对象，不然原对象会一直覆盖。

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
    List<String> res = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return res;
        }

        addPath(root, new StringBuilder());

        return res;
    }

    private void addPath(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            res.add(sb.append(root.val).toString());
        }

        sb.append(root.val);

        if (root.left != null) {
            addPath(root.left, new StringBuilder(sb).append("->"));
        }

        if (root.right != null) {
            addPath(root.right, new StringBuilder(sb).append("->"));
        }

        return;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍花费O(n)，new对象的时候要拷贝一遍原字符串花费O(n)，因此，总时间复杂度为**O(n<sup>2</sup>)**；
2. 空间复杂度：递归深度为O(logn)~O(n)，StringBuilder在每次传递时花费O(n)空间，故空间复杂度为**O(nlogn)~O(n<sup>2</sup>)**。

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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);

        Queue<StringBuilder> strQueue = new LinkedList<>();
        strQueue.offer(new StringBuilder().append(root.val));

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            StringBuilder str = strQueue.poll();

            if (node == null) {
                continue;
            }

            if (node.left != null) {
                nodeQueue.offer(node.left);
                strQueue.offer(
                    new StringBuilder(str).
                    append("->").append(node.left.val)
                );
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
                strQueue.offer(
                    new StringBuilder(str).
                    append("->").append(node.right.val)
                );
            }

            if (node.left == null && node.right == null) {
                res.add(str.toString());
            }
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍花费O(n)，new对象的时候要拷贝一遍原字符串花费O(n)，因此，总时间复杂度为**O(n<sup>2</sup>)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数2<sup>h-1</sup>，StringBuilder在每次传递时花费O(n)空间，故空间复杂度为**O(n * 2<sup>h-1</sup>)**。

