# 第783题 二叉搜索树节点最小距离

## 1 题目

示例 1：

![783-题图1](images/783-题图1.jpg)

输入：root = [4,2,6,1,3]
输出：1
示例 2：

![783-题图2](images/783-题图2.jpg)


输入：root = [1,0,48,null,null,12,49]
输出：1


提示：

树中节点数目在范围 [2, 100] 内
0 <= Node.val <= 105
差值是一个正数，其数值等于两值之差的绝对值

## 2 解法

中序遍历有序。对于[0, 1, 3, 4]，3 - 1肯定比3 - 0小，所以最小差肯定出现在有序序列的相邻两个元素之间。

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
    int minDiffInBST(TreeNode* root) {
        int lastRootVal = -1;
        int min = INT_MAX;
        minDiffInBST(root, lastRootVal, min);
        return min;
    }
private:
    void minDiffInBST(TreeNode* root, int& lastRootVal, int& min) {
        if (root == nullptr) {
            return;
        }

        minDiffInBST(root->left, lastRootVal, min);

        if (lastRootVal != -1) {
            int diff = root->val - lastRootVal;
            if (diff < min) {
                min = diff;
            }
        }
        lastRootVal = root->val;

        minDiffInBST(root->right, lastRootVal, min);
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为树的深度O(logn)，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。