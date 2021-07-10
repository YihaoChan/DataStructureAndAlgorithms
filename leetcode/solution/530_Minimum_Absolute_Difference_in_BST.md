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

中序遍历有序。对于[0, 1, 3, 4]，3 - 1肯定比3 - 0小，所以最小差肯定出现在有序序列的相邻两个元素之间。

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
    int getMinimumDifference(TreeNode* root) {
        int lastRootVal = -1;
        int min = INT_MAX;
        getMinimumDifference(root, lastRootVal, min);
        return min;
    }
private:
    void getMinimumDifference(TreeNode* root, int& lastRootVal, int& min) {
        if (root == nullptr) {
            return;
        }

        getMinimumDifference(root->left, lastRootVal, min);

        if (lastRootVal != -1) {
            int diff = root->val - lastRootVal;
            if (diff < min) {
                min = diff;
            }
        }
        lastRootVal = root->val;

        getMinimumDifference(root->right, lastRootVal, min);

        return;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为树的深度O(logn)，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。

