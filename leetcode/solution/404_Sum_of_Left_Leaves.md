# 第404题 左叶子之和

## 1 题目

计算给定二叉树的所有左叶子之和。

示例：

    	3
       / \
      9  20
        /  \
       15   7
在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24

## 2 解法

### 2.1 递归

不能把sum当做参数传递进getSum，因为基本类型的传递本质上是传值的拷贝，而无论怎么修改这个拷贝，原值都不会改变。

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
    int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        getSum(root);

        return sum;
    }

    private void getSum(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                sum += root.left.val;
            }
        }

        getSum(root.left);
        getSum(root.right);

        return;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为树的深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。

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
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;

        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();

            TreeNode node = queue.poll();

            if (node.left != null) {
                queue.offer(node.left);

                if (node.left.left == null && 
                    node.left.right == null) {
                    sum += node.left.val;
                }
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return sum;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点入队、出队一次，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

