# 第107题 二叉树的层序遍历 II

# 1 题目

给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

例如：
给定二叉树 [3,9,20,null,null,15,7],

      3
     / \
    9  20
       /  \
      15   7

返回其自底向上的层序遍历为：

```
[
  [15,7],
  [9,20],
  [3]
]
```

## 2 解法

每一层采用头插法的方式插入。

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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();

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
                res.addFirst(level);
            }
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点入队、出队一次，链表头插花费O(1)，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

