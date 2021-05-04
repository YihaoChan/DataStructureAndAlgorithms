# 剑指 Offer 32 - II. 从上到下打印二叉树 II

## 1 题目

从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。

例如:
给定二叉树: [3,9,20,null,null,15,7],

        3
       / \
      9  20
        /  \
       15   7

返回其层次遍历结果：

```
[
  [3],
  [9,20],
  [15,7]
]
```

## 2 解法

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();

            List<Integer> level = new ArrayList<>();

            while (count > 0) {
                TreeNode dequeueNode = queue.poll();

                if (dequeueNode != null) {
                    queue.offer(dequeueNode.left);
                    queue.offer(dequeueNode.right);
                    level.add(dequeueNode.val);
                }

                count--;
            }

            if (level.size() > 0) {
                res.add(level);
            }
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点入队、出队一次，插入数组花费O(1)，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

