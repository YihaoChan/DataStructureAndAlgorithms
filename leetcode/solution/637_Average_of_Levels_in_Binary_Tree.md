# 第637题 二叉树的层平均值

## 1 题目

给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。

示例 1：

输入：

```
	3
   / \
  9  20
    /  \
   15   7
```

输出：[3, 14.5, 11]
解释：
第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。

## 2 解法

### 2.1 迭代

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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = size;
            double levelSum = 0;

            while (count > 0) {
                TreeNode dequeueNode = queue.poll();

                levelSum += dequeueNode.val;

                if (dequeueNode.left != null) {
                    queue.offer(dequeueNode.left);
                }

                if (dequeueNode.right != null) {
                    queue.offer(dequeueNode.right);
                }

                count--;
            }

            res.add(levelSum / size);
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点入队、出队一次，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

### 2.2 递归

