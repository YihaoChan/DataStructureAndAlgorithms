# 第103题 二叉树的锯齿形层序遍历

## 1 题目

给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],

      3
     / \
    9  20
       / \
      15  7

 返回锯齿形层序遍历如下：

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean oddFlag = true;

        while (!queue.isEmpty()) {
            int count = queue.size();

            LinkedList<Integer> level = new LinkedList<>();

            while (count > 0) {
                TreeNode dequeueNode = queue.poll();

                if (dequeueNode != null) {
                    queue.offer(dequeueNode.left);
                    queue.offer(dequeueNode.right);

                    if (oddFlag) {
                        level.addLast(dequeueNode.val);
                    } else {
                        level.addFirst(dequeueNode.val);
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
2. 空间复杂度：队列中最多不超过n个结点，故空间复杂度为**O(n)**。

