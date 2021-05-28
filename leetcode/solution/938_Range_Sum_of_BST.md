# 第938题 二叉搜索树的范围和

## 1 题目

给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。

示例 1：

![938-题图1](images/938-题图1.jpg)

输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
输出：32
示例 2：

![938-题图2](images/938-题图2.jpg)


输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
输出：23

## 2 解法

### 2.1 递归 - 普通

没有利用到二叉搜索树的性质。

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
    private int sum = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        getSum(root, low, high);

        return sum;
    }

    private void getSum(TreeNode root, int low, int high) {
        if (root == null) {
            return;
        }

        if (low <= root.val && root.val <= high) {
            sum += root.val;
        }

        getSum(root.left, low, high);
        getSum(root.right, low, high);

        return;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间深度为树的深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。

### 2.2 递归 - 优化

如果当前结点的值如果不在[low, high]范围内，可以避免没必要的查找。

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
    private int sum = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        getSum(root, low, high);

        return sum;
    }

    private void getSum(TreeNode root, int low, int high) {
        if (root == null) {
            return;
        }

        if (root.val < low) {
            getSum(root.right, low, high);
        } else if (root.val > high) {
            getSum(root.left, low, high);
        } else {
            sum += root.val;
            getSum(root.left, low, high);
            getSum(root.right, low, high);
        }
        
        return;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间深度为树的深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。

### 2.3 迭代

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
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode dequeueNode = queue.poll();

            if (dequeueNode != null) {
                if (dequeueNode.val < low) {
                    queue.offer(dequeueNode.right);
                } else if (dequeueNode.val > high) {
                    queue.offer(dequeueNode.left);
                } else {
                    sum += dequeueNode.val;
                    queue.offer(dequeueNode.left);
                    queue.offer(dequeueNode.right);
                }
            }
        }

        return sum;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。