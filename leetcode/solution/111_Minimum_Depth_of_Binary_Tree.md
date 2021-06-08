# 第111题 二叉树的最小深度

## 1 题目

给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

说明：叶子节点是指没有子节点的节点。

示例 1：

![111-题图1](images/111-题图1.jpg)


输入：root = [3,9,20,null,null,15,7]
输出：2
示例 2：

输入：root = [2,null,3,null,4,null,5,null,6]
输出：5

## 2 解法

### 2.1 递归

```c++
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    int minDepth(TreeNode* root) {
        if (root == nullptr) {
            return 0;
        }

        int leftDepth = minDepth(root->left);
        int rightDepth = minDepth(root->right);

        if (root->left == nullptr) {
            return rightDepth + 1;
        } else if (root->right == nullptr) {
            return leftDepth + 1;
        }

        return min(1 + leftDepth, 1 + rightDepth);
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。

### 2.2 迭代

当搜索到叶子结点时，直接返回深度即可，不需要再向队列中添加后面的其他结点。

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
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;

        while (!queue.isEmpty()) {
            int count = queue.size();

            depth++;

            while (count > 0) {
                TreeNode dequeueNode = queue.poll();

                if (dequeueNode.left == null && 
                    dequeueNode.right == null) {
                    return depth;
                }

                if (dequeueNode.left != null) {
                    queue.offer(dequeueNode.left);
                }

                if (dequeueNode.right != null) {
                    queue.offer(dequeueNode.right);
                }

                count--;
            }
        }

        return -1;
    }
}
```

复杂度分析：

1. 时间复杂度：最坏情况下每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：最坏情况下二叉树为满二叉树，此时队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。