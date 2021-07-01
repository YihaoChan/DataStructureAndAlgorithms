# 第404题 左叶子之和

## 1 题目

计算给定二叉树的所有左叶子之和。

示例：

    	3
       / \
      9  20
        /  \
       15   7
在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24

## 2 解法

### 2.1 递归

如果左叶子是叶子结点，sum就为左叶子结点的值，否则为0。将当前得到的值和左子结点、右子结点的计算结果相加并返回即可。

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
    int sumOfLeftLeaves(TreeNode* root) {
        if (root == nullptr) {
            return 0;
        }

        int sum = 0;

        if (root->left != nullptr) {
            if (root->left->left == nullptr && root->left->right == nullptr) {
                sum = root->left->val; 
            }
        }

        return sum + sumOfLeftLeaves(root->left) + sumOfLeftLeaves(root->right);
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为树的深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。

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
    int sumOfLeftLeaves(TreeNode* root) {
        if (root == nullptr) {
            return 0;
        }

        int sum = 0;
        queue<TreeNode*> q;
        q.push(root);

        while (!q.empty()) {
            TreeNode* node = q.front();
            q.pop();

            if (
                node->left != nullptr &&
                node->left->left == nullptr && node->left->right == nullptr
            ) {
                sum += node->left->val;
            }

            if (node->left != nullptr) {
                q.push(node->left);
            }
            if (node->right != nullptr) {
                q.push(node->right);
            }
        }

        return sum;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点入队、出队一次，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

