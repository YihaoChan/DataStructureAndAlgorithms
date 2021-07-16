# 第98题 验证二叉搜索树

## 1 题目

给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
示例 1:

输入:

```
  2
 / \
1   3
```

输出: true
示例 2:

输入:

```
  5
 / \
1   4
   / \
  3   6
```

输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
根节点的值为 5 ，但是其右子节点值为 4 。

## 2 解法

中序遍历有序。注意题意，二叉搜索树需满足**严格大于**，等于的情况应该判为false。同时注意极限用例[-2147483648]恰好为INT_MIN，因此需要用更小的值去判断，如LONG_MIN。此外，为了使上一个结点的值成为全局的lastVal，应该用引用的方式，否则会出错。

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
    bool isValidBST(TreeNode* root) {
        long lastVal = LONG_MIN;
        bool res = true;
        inorder(root, lastVal, res);
        return res;
    }
private:
    void inorder(TreeNode* root, long& lastVal, bool& res) {
        if (root == nullptr) {
            return;
        }
        if (!res) {
            return;
        }
        
        inorder(root->left, lastVal, res);

        res = res && (root->val > lastVal);
        lastVal = root->val;

        inorder(root->right, lastVal, res);
    }
};
```

复杂度分析：

1. 时间复杂度：最坏情况下，每个结点都被访问一遍，故时间复杂度为**O(n)**；
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
    bool isValidBST(TreeNode* root) {
        if (root == nullptr) {
            return false;
        }

        stack<TreeNode*> s;
        vector<int> vals;

        while (root != nullptr || !s.empty()) {
            while (root != nullptr) {
                s.push(root);
                root = root->left;
            }
            root = s.top();
            s.pop();
            vals.push_back(root->val);
            root = root->right;
        }

        int len = vals.size();
        if (len == 1) {
            return true;
        }
        for (int i = 1; i < len; ++i) {
            if (vals[i - 1] >= vals[i]) {
                return false;
            }
        }

        return true;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：栈中最多结点数为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。
