# 第530题 二叉搜索树的最小绝对差

## 1 题目

给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。

示例：

输入：

```
   1
    \
     3
    /
   2
```

输出：
1

解释：
最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。

## 2 解法

### 2.1 递归

中序遍历有序。对于[0, 1, 3, 4]，3 - 1肯定比3 - 0小，所以最小差肯定出现在有序序列的相邻两个元素之间。同时有可能出现极限用例[2147483648]恰好为INT_MAX，因此需要用更大的值去判断，如LONG_MAX。此外，为了使上一个结点的值成为全局的lastVal，应该用引用的方式，否则会出错。

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
    int getMinimumDifference(TreeNode* root) {
        int lastVal = -1;
        long min = LONG_MAX;
        inorder(root, lastVal, min);
        return min;
    }
private:
    void inorder(TreeNode* root, int& lastVal, long& min) {
        if (root == nullptr) {
            return;
        }

        inorder(root->left, lastVal, min);

        if (lastVal != -1) {
            int currDiff = root->val - lastVal;
            if (currDiff < min) {
                min = currDiff;
            }
        }
        lastVal = root->val;

        inorder(root->right, lastVal, min);
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为树的深度O(logn)，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。

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
    int getMinimumDifference(TreeNode* root) {
        if (root == nullptr) {
            return -1;
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
            return vals[0];
        }
        long min = LONG_MAX;
        for (int i = 1; i < len; ++i) {
            if ((vals[i] - vals[i - 1]) < min) {
                min = vals[i] - vals[i - 1];
            }
        }

        return min;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：栈中最多结点数为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。

