# 第102题 二叉树的层序遍历

## 1 题目

给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

示例：
二叉树：[3,9,20,null,null,15,7],

        3
       / \
      9  20
        /  \
       15   7
返回其层序遍历结果：

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();

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

1. 时间复杂度：每个结点入队、出队各1次，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中的结点最多不超过n个，故空间复杂度为**O(n)**。