# 第938题 二叉搜索树的范围和

## 1 题目

给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。

示例 1：

![938-题图1](images/938-题图1.jpg)

输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
输出：32
示例 2：

![938-题图2](images/938-题图2.jpg)


输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
输出：23

## 2 解法

### 2.1 递归

如果当前结点的值如果不在[low, high]范围内，可以避免没必要的查找。

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
    int rangeSumBST(TreeNode* root, int low, int high) {
        if (root == nullptr) {
            return 0;
        }

        if (low <= root->val && root->val <= high) {
            return root->val + 
                   rangeSumBST(root->left, low, high) + 
                   rangeSumBST(root->right, low, high);
        }

        if (root->val < low) {
            return rangeSumBST(root->right, low, high);
        } else if (root->val > high) {
            return rangeSumBST(root->left, low, high);
        }

        return -1;
    }
};
```

复杂度分析：

1. 时间复杂度：最坏情况下，每个结点的值都在[low, high]范围内，因此每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间深度为树的深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。

### 2.2 迭代

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
    int rangeSumBST(TreeNode* root, int low, int high) {
        if (root == nullptr) {
            return 0;
        }
        
        queue<TreeNode*> q;
        q.push(root);
        int sum = 0;

        while (!q.empty()) {
            TreeNode* node = q.front();
            q.pop();

            if (node == nullptr) {
                continue;
            }

            if (node->val < low) {
                q.push(node->right);
            } else if (node->val > high) {
                q.push(node->left);
            } else if (low <= node->val && node->val <= high) {
                q.push(node->left);
                q.push(node->right);
                sum += node->val;
            }
        }

        return sum;
    }
};
```

复杂度分析：

1. 时间复杂度：最坏情况下，每个结点的值都在[low, high]范围内，因此每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。