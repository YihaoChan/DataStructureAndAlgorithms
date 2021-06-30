# 第993题 二叉树的堂兄弟节点

## 1 题目

在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。

如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。

我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。

只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。

示例 1：

![993-题图1](images/993-题图1.png)

输入：root = [1,2,3,4], x = 4, y = 3
输出：false
示例 2：

![993-题图2](images/993-题图2.png)


输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
输出：true
示例 3：

![993-题图3](images/993-题图3.png)

输入：root = [1,2,3,null,4], x = 2, y = 3
输出：false

## 2 解法

### 2.1 递归

先求深度，再求父结点。

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
    bool isCousins(TreeNode* root, int x, int y) {
        int xDepth = getDepth(root, x, 0);
        int yDepth = getDepth(root, y, 0);

        if (xDepth != yDepth) {
            return false;
        }

        TreeNode* xParent = getParent(root, x);
        TreeNode* yParent = getParent(root, y);

        if (xParent == yParent) {
            return false;
        }

        return true;
    }

private:
    int getDepth(TreeNode* root, int target, int depth) {
        if (root == nullptr) {
            return -1;
        }

        if (root->val == target) {
            return depth;
        }

        int leftDepth = getDepth(root->left, target, depth + 1);
        int rightDepth = getDepth(root->right, target, depth + 1);

        return leftDepth == -1 ? rightDepth : leftDepth;
    }

    TreeNode* getParent(TreeNode* root, int target) {
        if (root == nullptr) {
            return nullptr;
        }

        if (
            root->left != nullptr && root->left->val == target ||
            root->right != nullptr && root->right->val == target
        ) {
            return root;
        }

        TreeNode* leftParent = getParent(root->left, target);
        TreeNode* rightParent = getParent(root->right, target);

        return leftParent == nullptr ? rightParent : leftParent;
    } 
};
```

复杂度分析：

1. 时间复杂度：计算深度和父结点在最坏情况下都需要访问每个结点一次，故时间复杂度为**O(n)**；
2. 空间复杂度：递归计算深度和父结点花费的栈空间都是树的深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。

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
    bool isCousins(TreeNode* root, int x, int y) {
        if (root == nullptr) {
            return false;
        }

        queue<TreeNode*> q;
        q.push(root);
        int xDepth, yDepth, depth = 0;
        TreeNode* xParent= root;
        TreeNode* yParent= root;

        while (!q.empty()) {
            int count = q.size();
            ++depth;
            while (count > 0) {
                TreeNode* node = q.front();
                q.pop();
                if (node->left != nullptr) {
                    q.push(node->left);
                    if (node->left->val == x) {
                        xParent = node;
                        xDepth = depth;
                    } else if (node->left->val == y) {
                        yParent = node;
                        yDepth = depth;
                    }
                }
                if (node->right != nullptr) {
                    q.push(node->right);
                    if (node->right->val == x) {
                        xParent = node;
                        xDepth = depth;
                    } else if (node->right->val == y) {
                        yParent = node;
                        yDepth = depth;
                    }
                }
                --count;
            }
        }

        if (xDepth != yDepth || xParent == yParent) {
            return false;
        }

        return true;
    }
};
```

复杂度分析：

1. 时间复杂度：计算深度和父结点在最坏情况下都需要访问每个结点一次，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

