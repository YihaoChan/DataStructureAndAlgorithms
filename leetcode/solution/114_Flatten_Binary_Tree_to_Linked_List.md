# 第114题 二叉树展开为链表

## 1 题目

给你二叉树的根结点 root ，请你将它展开为一个单链表：

展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。

示例 1：

![114-题图](images/114-题图.jpg)


输入：root = [1,2,5,3,4,null,6]
输出：[1,null,2,null,3,null,4,null,5,null,6]
示例 2：

输入：root = []
输出：[]
示例 3：

输入：root = [0]
输出：[0]

## 2 解法

### 2.1 递归

先拷贝左子树和右子树，然后断开左子树连接，将右子树赋为已拷贝的左子树。之后向右一直遍历，将已拷贝的右子树接到最右结点的右子树。

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
    void flatten(TreeNode* root) {
        if (root == nullptr) {
            return;
        }

        flatten(root->left);
        flatten(root->right);

        TreeNode* tempRightNode = root->right;
        root->right = root->left;
        root->left = nullptr;
        
        TreeNode* node = root;
        while (node->right != nullptr) {
            node = node->right;
        }
        node->right = tempRightNode;

        return;
    }
};
```

复杂度分析：

1. 时间复杂度：对每个结点都执行了一次将左子树交换到右子树的过程，故时间复杂度为**O(n)**；
2. 空间复杂度：递归空间复杂度为递归树深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。其中，n为树的结点总个数。

### 2.2 迭代

核心思想：在当前结点和右子树之间**插入**左子树，插入方法和链表插入结点相同。先拷贝当前结点的右子树，然后将右子树赋为左子树，之后一直向右找到最右的结点，将之前已拷贝的树接到右边。之后，当前结点一直往右走即可。

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
    void flatten(TreeNode* root) {
        if (root == nullptr) {
            return;
        }

        while (root != nullptr) {
            TreeNode* tempRightNode = root->right;
            root->right = root->left;
            root->left = nullptr;

            TreeNode* node = root;
            while (node->right != nullptr) {
                node = node->right;
            }
            node->right = tempRightNode;

            root = root->right;
        }

        return;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点在逐层向下调整时被访问1次，在搜索最右结点用于连接已拷贝的树时被访问1次，故每个结点共被访问2次，因此，总时间复杂度为**O(n)**；
2. 空间复杂度：仅用到常数个额外空间，故空间复杂度为**O(1)**。