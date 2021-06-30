# 第700题 二叉搜索树中的搜索

## 1 题目

给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。

例如，

给定二叉搜索树:

        4
       / \
      2   7
     / \
    1   3

和值: 2
你应该返回如下子树:

      2     
     / \   
    1   3
在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。

## 2 解法

### 2.1 递归

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
    TreeNode* searchBST(TreeNode* root, int val) {
        if (root == nullptr) {
            return nullptr;
        }

        int cmp = root->val - val;

        if (cmp > 0) {
            return searchBST(root->left, val);
        } else if (cmp < 0) {
            return searchBST(root->right, val);
        } else if (cmp == 0) {
            return root;
        }

        return nullptr;
    }
};
```

复杂度分析：

1. 时间复杂度：每次根据比较结果进行下一步递归，不需要访问全部结点，故时间复杂度为**O(logn)**；
2. 空间复杂度：递归栈空间为树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。

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
    TreeNode* searchBST(TreeNode* root, int val) {
        if (root == nullptr) {
            return root;
        }

        while (root != nullptr) {
            int cmp = val - root->val;
            
            if (cmp < 0) {
                root = root->left;
            } else if (cmp > 0) {
                root = root->right;
            } else if (cmp == 0) {
                return root;
            }
        }

        return nullptr;
    }
};
```

复杂度分析：

1. 时间复杂度：每次根据比较结果进行下一步递归，不需要访问全部结点，故时间复杂度为**O(logn)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。

