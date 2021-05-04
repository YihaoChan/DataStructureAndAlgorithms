# 剑指 Offer 32 - I. 从上到下打印二叉树

## 1 题目

从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

例如:
给定二叉树: [3,9,20,null,null,15,7],

        3
       / \
      9  20
        /  \
       15   7

返回：

```
[3,9,20,15,7]
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
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        List<Integer> array = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode dequeueNode = queue.poll();

            if (dequeueNode != null) {
                queue.offer(dequeueNode.left);
                queue.offer(dequeueNode.right);
                array.add(dequeueNode.val);
            }
        }

        int[] res = new int[array.size()];
        int ptr = 0;

        for (Integer item : array) {
            res[ptr] = item;
            ptr++;
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点入队、出队一次，插入数组花费O(1)，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数2<sup>h-1</sup>，辅助ArrayList存放n个结点，故空间复杂度为**O(n)**。

