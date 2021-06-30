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
    vector<double> averageOfLevels(TreeNode* root) {
        vector<double> res;
        if (root == nullptr) {
            return res;
        }

        queue<TreeNode*> q;
        q.push(root);

        while (!q.empty()) {
            int size = q.size();
            int count = size;
            double sum = 0.0;

            while (count > 0) {
                TreeNode* node = q.front();
                q.pop();
                sum += node->val;

                if (node->left != nullptr) {
                    q.push(node->left);
                }
                if (node->right != nullptr) {
                    q.push(node->right);
                }

                --count;
            }

            res.push_back(sum / size);
        }

        return res;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点入队、出队一次，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

### 2.2 递归

