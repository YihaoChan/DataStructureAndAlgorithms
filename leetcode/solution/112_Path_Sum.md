# 第112题 路径总和

## 1 题目

给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。

叶子节点 是指没有子节点的节点。

示例 1：

![112-题图1](images/112-题图1.jpg)

输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
输出：true
示例 2：

![112-题图2](images/112-题图2.jpg)


输入：root = [1,2,3], targetSum = 5
输出：false
示例 3：

输入：root = [1,2], targetSum = 0
输出：false

## 2 解法

### 2.1 递归

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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        return hasPathSum(root.left, targetSum - root.val) ||
               hasPathSum(root.right, targetSum - root.val);
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间深度为树的深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。

### 2.2 迭代

用两个队列，一个存放结点，一个存放差值。当出队的叶子结点的值等于差值时，说明存在符合题意的路径。

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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);

        Queue<Integer> sumQueue = new LinkedList<>();
        sumQueue.offer(targetSum);

        while (!nodeQueue.isEmpty()) {
            int count = nodeQueue.size();

            while (count > 0) {
                TreeNode dequeueNode = nodeQueue.poll();
                Integer dequeueSum = sumQueue.poll();

                if (dequeueNode.left == null && 
                dequeueNode.right == null) {
                    if (dequeueNode.val == dequeueSum) {
                        return true;
                    }
                }

                Integer diff = dequeueSum - dequeueNode.val;

                if (dequeueNode.left != null) {
                    nodeQueue.offer(dequeueNode.left);
                    sumQueue.offer(diff);
                }

                if (dequeueNode.right != null) {
                    nodeQueue.offer(dequeueNode.right);
                    sumQueue.offer(diff);
                }

                count--;
            }   
        }

        return false;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

