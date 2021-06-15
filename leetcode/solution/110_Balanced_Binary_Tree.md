# 第110题 平衡二叉树

## 1 题目

给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。

示例 1：

![110-题图1](images/110-题图1.jpg)

输入：root = [3,9,20,null,null,15,7]
输出：true
示例 2：

![110-题图2](images/110-题图2.jpg)


输入：root = [1,2,2,3,3,null,null,4,4]
输出：false
示例 3：

输入：root = []
输出：true

## 2 解法

### 2.1 自顶向下

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
    bool isBalanced(TreeNode* root) {
        if (root == nullptr) {
            return true;
        }

        int leftHeight = topToBottom(root->left);
        int rightHeight = topToBottom(root->right);

        return abs(leftHeight - rightHeight) <= 1 &&
               isBalanced(root->left) &&
               isBalanced(root->right);
    }

private:
    int topToBottom(TreeNode* root) {
        if (root == nullptr) {
            return 0;
        }

        int leftHeight = topToBottom(root->left);
        int rightHeight = topToBottom(root->right);

        return max(leftHeight, rightHeight) + 1;
    }
};
```

复杂度分析：

1. 时间复杂度：对于每一个结点，都先求出它的左右子结点并判断是否平衡后，再判断下一个结点，故时间复杂度为**O(n<sup>2</sup>)**；
2. 空间复杂度：递归栈空间为二叉树的深度，故总空间复杂度为**O(logn)**，最坏情况下退化为链表，空间复杂度为O(n)。

### 2.2 自底向上

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
    bool isBalanced(TreeNode* root) {
        bool res = true;

        bottomToTop(root, res);

        return res;
    }

private:
    int bottomToTop(TreeNode* root, bool &res) {
        if (root == nullptr) {
            return 0;
        }

        int leftHeight = bottomToTop(root->left, res);
        int rightHeight = bottomToTop(root->right, res);

        res = res && abs(leftHeight - rightHeight) <= 1;

        return max(leftHeight, rightHeight) + 1;
    }    
};
```

复杂度分析：

1. 时间复杂度：回溯到哪个结点，就进行一次判断就能得出当前结果，因此，时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为二叉树的深度，故总空间复杂度为**O(logn)**，最坏情况下退化为链表，空间复杂度为O(n)。