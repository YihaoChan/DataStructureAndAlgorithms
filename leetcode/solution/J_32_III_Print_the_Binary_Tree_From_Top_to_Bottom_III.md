# 剑指 Offer 32 - III. 从上到下打印二叉树 III

## 1 题目

请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

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
  [20,9],
  [15,7]
]
```

## 2 解法

层序遍历时，每一层结点采取链表结构：当奇数行时采用尾插法，当偶数行时采用头插法。

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

        boolean oddFlag = true;

        while (!queue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();

            int count = queue.size();

            while (count > 0) {
                TreeNode dequeNode = queue.poll();

                if (dequeNode != null) {
                    queue.offer(dequeNode.left);
                    queue.offer(dequeNode.right);

                    if (oddFlag) {
                        level.addLast(dequeNode.val);
                    } else {
                        level.addFirst(dequeNode.val);
                    }
                }

                count--;
            }

            if (level.size() > 0) {
                res.add(level);
            }

            oddFlag = !oddFlag;
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点入队、出队一次，链表头插、尾插花费O(1)，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。



