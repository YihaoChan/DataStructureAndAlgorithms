# 第199题 二叉树的右视图

## 1 题目

给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:

输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

```
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
```

## 2 解法

### 2.1 递归

深度从0开始，并从右子树开始访问。如果当前结点的深度恰好等于结果数组中的元素个数，说明当前结点恰好为当前层最右边的结点，此时将结点添加至结果数组即可。如果当前结点的深度不等于数组中的元素个数，说明已经有更右边的结点已经加入过数组，证明当前结点不是最右结点。

```
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
    vector<int> rightSideView(TreeNode* root) {
        vector<int> res;
        if (root == nullptr) {
            return res;
        }
        dfs(root, 0, res);
        return res;
    }
private:
    void dfs(TreeNode* root, int depth, vector<int>& res) {
        if (root == nullptr) {
            return;
        }

        if (res.size() == depth) {
            res.push_back(root->val);
        }

        ++depth;
        dfs(root->right, depth, res);
        dfs(root->left, depth, res);

        return;
    }
};
```

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
    vector<int> rightSideView(TreeNode* root) {
        vector<int> res;
        if (root == nullptr) {
            return res;
        }

        queue<TreeNode*> q;
        q.push(root);

        while (!q.empty()) {
            int count = q.size();
            res.push_back(q.front()->val);

            while (count > 0) {
                TreeNode* node = q.front();
                q.pop();

                if (node->right != nullptr) {
                    q.push(node->right);
                }
                if (node->left != nullptr) {
                    q.push(node->left);
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



