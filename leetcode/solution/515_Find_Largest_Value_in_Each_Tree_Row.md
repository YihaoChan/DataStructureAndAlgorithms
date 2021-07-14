# 第515题 在每个树行中找最大值

## 1 题目

您需要在二叉树的每一行中找到最大的值。

示例：

输入: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

输出: [1, 3, 9]

## 2 解法

### 2.1 递归

res[depth]为当前深度这一层中的结点最大值。

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
    vector<int> largestValues(TreeNode* root) {
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

        if (depth == res.size()) {
            res.push_back(root->val);
        } else if (root->val > res[depth]){
            res[depth] = root->val;
        }

        ++depth;
        dfs(root->left, depth, res);
        dfs(root->right, depth, res);

        return;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。

### 2.2 迭代

注意用例[-2147483648]，意味着如果每一层的最大值的初始值初始化为INT_MIN，需要小心判断。

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
    vector<int> largestValues(TreeNode* root) {
        vector<int> res;
        if (root == nullptr) {
            return res;
        }

        queue<TreeNode*> q;
        q.push(root);
        int depth = 0;

        while (!q.empty()) {
            int count = q.size();
            int maxValue = INT_MIN;

            while (count > 0) {
                TreeNode* node = q.front();
                q.pop();

                if (node->val > maxValue) {
                    maxValue = node->val;
                }    
                if (depth == res.size()) {
                    res.push_back(maxValue);
                } else {
                    res[depth] = maxValue;
                }

                if (node->left != nullptr) {
                    q.push(node->left);
                }
                if (node->right != nullptr) {
                    q.push(node->right);
                }

                --count;
            }

            ++depth;
        }

        return res;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故总时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

