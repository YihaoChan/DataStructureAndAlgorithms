# 第226题 翻转二叉树

## 1 题目

翻转一棵二叉树。

示例：

输入：

         4
       /   \
      2     7
     / \   / \
    1   3 6   9
输出：

```
     4
   /   \
  7     2
 / \   / \
9   6 3   1
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
    TreeNode* invertTree(TreeNode* root) {
        if (
            root == nullptr || 
           (root->left == nullptr && root->right == nullptr)
        ) {
            return root;
        }

        TreeNode* temp = root->right;
        root->right = root->left;
        root->left = temp;

        root->left = invertTree(root->left);
        root->right = invertTree(root->right);

        return root;
    }
};
```

复杂度分析：

1. 时间复杂度：每两个结点之间都进行了一次交换的操作，故时间复杂度为**O(n)**；
2. 空间复杂度：递归空间复杂度为递归树深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。其中，n为树的结点总个数。

