# 第1022题 从根到叶的二进制数之和

## 1 题目

给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。

对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。

返回这些数字之和。题目数据保证答案是一个 32 位 整数。

示例 1：

![1022-题图1](images/1022-题图1.png)


输入：root = [1,0,1,0,1,0,1]
输出：22
解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
示例 2：

输入：root = [0]
输出：0
示例 3：

输入：root = [1]
输出：1
示例 4：

输入：root = [1,1]
输出：3

## 2 解法

思考二进制数1000，从高位到低位的计算过程：

1. 0 * 2 + 1 = 1；
2. 1 * 2 + 0 = 2；
3. 2 * 2 + 0 = 4；
4. 4 * 2 + 0 = 8.

如果当前结点是叶子结点，就将计算结果返回。最终结果为左子树的计算结果+右子树的计算结果。

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
    int sumRootToLeaf(TreeNode* root) {
        return getSum(root, 0);
    }

private:
    int getSum(TreeNode* root, int sum) {
        if (root == nullptr) {
            return 0;
        }

        sum = sum * 2 + root->val;

        if (root->left == nullptr && root->right == nullptr) {
            return sum;
        }

        return getSum(root->left, sum) + getSum(root->right, sum);
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。