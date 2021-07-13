# 第513题 找树左下角的值

## 1 题目

给定一个二叉树，在树的最后一行找到最左边的值。

示例 1:

输入:

      2
     / \
    1   3
输出:
1


示例 2:

输入:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

输出:
7

## 2 解法

### 2.1 递归

根结点深度为0，从左子树开始访问。如果当前深度大于最大深度，说明已经下沉到更深一层，此时更新最左结点的值，并更新最大深度。如果当前深度等于最大深度，说明和最左结点处于同一层，不需更新。

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
    int findBottomLeftValue(TreeNode* root) {
        if (root == nullptr) {
            return -1;
        }
        int res = -1;
        int depth = 0;
        int maxDepth = INT_MIN;
        dfs(root, res, depth, maxDepth);
        return res;
    }
private:
    void dfs(TreeNode* root, int& res, int depth, int& maxDepth) {
        if (root == nullptr) {
            return;
        }

        if (depth > maxDepth) {
            maxDepth = depth;
            res = root->val;
        }

        ++depth;
        dfs(root->left, res, depth, maxDepth);
        dfs(root->right, res, depth, maxDepth);

        return;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。

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
    int findBottomLeftValue(TreeNode* root) {
        if (root == nullptr) {
            return -1;
        }

        queue<TreeNode*> q;
        q.push(root);
        int res = -1;

        while (!q.empty()) {
            int count = q.size();
            res = q.front()->val;

            while (count > 0) {
                TreeNode* node = q.front();
                q.pop();

                if (node->left != nullptr) {
                    q.push(node->left);
                }
                if (node->right != nullptr) {
                    q.push(node->right);
                }

                --count;
            }
        }

        return res;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故总时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

