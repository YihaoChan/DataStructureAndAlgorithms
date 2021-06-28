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
    vector<vector<int>> levelOrderBottom(TreeNode* root) {
        vector<vector<int>> res;
        if (root == nullptr) {
            return res;
        }

        queue<TreeNode*> q;
        q.push(root);

        while (!q.empty()) {
            int count = q.size();
            vector<int> level;

            while (count > 0) {
                TreeNode* node = q.front();
                q.pop();

                level.push_back(node->val);

                if (node->left != nullptr) {
                    q.push(node->left);
                }
                if (node->right != nullptr) {
                    q.push(node->right);
                }

                --count;
            }

            if (level.size() != 0) {
                res.push_back(level);
            }
        }

        reverse(res.begin(), res.end());
        return res;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点入队、出队一次，反转花费O(h)，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

